package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import com.jswitch.configuracion.modelo.dominio.Cobertura;
import com.jswitch.configuracion.modelo.maestra.ConfiguracionCobertura;
import com.jswitch.pagos.modelo.maestra.Factura;
import com.jswitch.pagos.modelo.transaccional.DesgloseCobertura;
import com.jswitch.pagos.modelo.transaccional.DesgloseSumaAsegurada;
import com.jswitch.pagos.vista.FacturaDetailFrame;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.util.Collection;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Adrian
 */
public class FacturaDetailFrameController extends DefaultDetailFrameController {

    private DetalleSiniestro detalleSiniestro;

    public FacturaDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        detalleSiniestro = ((Factura) beanVO).getDetalleSiniestro();
        ((FacturaDetailFrame) vista).createDiagnostocoCodLookup(detalleSiniestro);

    }

    public FacturaDetailFrameController(String detailFramePath, GridControl gridControl, DetalleSiniestro beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, (BeanVO) null, aplicarLogicaNegocio);
        this.detalleSiniestro = beanVO;
        ((FacturaDetailFrame) vista).createDiagnostocoCodLookup(detalleSiniestro);

    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        Response res = super.updateRecord(oldPersistentObject, persistentObject);
        if (res instanceof VOResponse) {
            updateDetalleSiniestro();
        }
        return res;
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Factura liquidacion = (Factura) newPersistentObject;
        liquidacion.setDetalleSiniestro(detalleSiniestro);
        Response res = super.insertRecord(newPersistentObject);
        if (res instanceof VOResponse) {
            detalleSiniestro.getPagos().add(liquidacion);
            updateDetalleSiniestro();
        }
        return res;
    }

    @Override
    public void afterInsertData() {
        vista.getMainPanel().getReloadButton().doClick();
    }

    @Override
    public void afterEditData() {
        vista.getMainPanel().getReloadButton().doClick();
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Factura sin = (Factura) s.get(Factura.class, ((Factura) beanVO).getId());
        Hibernate.initialize(sin.getDesgloseSumaAsegurada());
        Hibernate.initialize(sin.getDesgloseCobertura());
        s.close();
        beanVO = sin;
        return new VOResponse(beanVO);
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        Factura factura = (Factura) persistentObject;
        Date fF = factura.getFechaFactura();
        Date fR = factura.getFechaRecepcion();
        if (fF.compareTo(fR) > 0) {
            return new ErrorResponse("errorFechaFacturaRecepcion");
        }
        long l = ((fR.getTime() - fF.getTime())
                / 1000 / 60 / 60 / 24);
        if (l > 90) {
            int s = JOptionPane.showConfirmDialog(vista,
                    "Han transcurrido " + l + " dias desde la factura desea Continuar ", "",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (s != JOptionPane.YES_OPTION) {
                return new ErrorResponse("user.aborted");
            }
        }

        Double x = 0d;
        for (DesgloseCobertura li : factura.getDesgloseCobertura()) {
            x += li.getMontoFacturado();
        }
        if (x.doubleValue() > factura.getTotalFacturado().doubleValue()) {
            return new ErrorResponse("monto menor al reflejado");
        }
        x = 0d;
        for (DesgloseSumaAsegurada li : factura.getDesgloseSumaAsegurada()) {
            x += li.getMonto();
        }
        if (x.doubleValue() > factura.getTotalFacturado().doubleValue()) {
            return new ErrorResponse("monto menor al reflejado");
        }
        Double islr = factura.getTipoConceptoSeniat().getPorcentajeRetencionIslr() ;
        Double montoNoAmparado = 0d;
        Double montoSujeto = 0d;
        Double montoIva = 0d;

        for (DesgloseCobertura dc : factura.getDesgloseCobertura()) {
            if (dc.getAuditoria().getActivo()) {
                ConfiguracionCobertura c = getConfiCober(dc.getCobertura());
                double iva = !c.getIva() ? 0
                        : (factura.getPorcentajeIva());
                montoNoAmparado += dc.getMontoNoAmparado() * (1 - iva);
                montoSujeto += dc.getMontoAmparado() * (1 - iva);
                montoIva += dc.getMontoFacturado() * iva;
            }
        }

        factura.setMontoNoAmparado(montoNoAmparado);
        factura.setMontoSujetoRetencion(montoSujeto);
        factura.setMontoIva(montoIva);

        factura.setMontoRetencionIva(factura.getMontoIva() * factura.getPorcentajeRetencionIva());
        factura.setMontoReteniconIsrl(montoSujeto * islr);

        factura.setTotalRetenido(factura.getMontoRetencionIva() + factura.getMontoReteniconIsrl());
        factura.setTotalLiquidado(montoIva + montoSujeto);
        factura.setTotalACancelar(factura.getTotalLiquidado() - factura.getTotalRetenido());

        return new VOResponse(factura);
    }

    public void updateDetalleSiniestro() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        DetalleSiniestro sin = null;
        try {
            sin = (DetalleSiniestro) s.get(DetalleSiniestro.class, detalleSiniestro.getId());

            Hibernate.initialize(sin.getPagos());
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            s.close();
            s = null;
        }
        detalleSiniestro = sin;

        s = HibernateUtil.getSessionFactory().openSession();
        try {
            Collection<Factura> fac = detalleSiniestro.getPagos();
            Double facturado = 0d;
            for (Factura factura : fac) {
                facturado += factura.getTotalFacturado();
            }
            detalleSiniestro.setMontoFacturado(facturado);
            s.beginTransaction();
            s.update(detalleSiniestro);
            s.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            s.close();
        }
    }

    public DefaultDetailFrame getVista() {
        return vista;
    }

    /**
     * configuracion cobertura para una cobertura
     * @param cobertura
     * @return the ConfiguracionCobertura
     */
    private ConfiguracionCobertura getConfiCober(Cobertura cobertura) {
        Session s = null;
        ConfiguracionCobertura c = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            c = (ConfiguracionCobertura) s.createQuery("FROM " + ConfiguracionCobertura.class.getName() + " C "
                    + "WHERE C.cobertura.id = ?").setLong(0, cobertura.getId()).uniqueResult();

        } catch (Exception e) {
            LoggerUtil.error(DesgloseCoberturaGridInternalController.class,
                    "getConfiCober", e);
        } finally {
            s.close();
        }
        return c;
    }
}
