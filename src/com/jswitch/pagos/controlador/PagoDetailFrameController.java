package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.configuracion.modelo.dominio.Cobertura;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.pagos.modelo.maestra.Liquidacion;
import com.jswitch.pagos.modelo.transaccional.DesgloseCobertura;
import com.jswitch.pagos.vista.PagoDetailFrame;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.Funerario;
import com.jswitch.siniestros.modelo.maestra.detalle.Vida;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
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
public class PagoDetailFrameController extends DefaultDetailFrameController {
    
    private DetalleSiniestro detalleSiniestro;
    
    public PagoDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        detalleSiniestro = ((Liquidacion) beanVO).getDetalleSiniestro();
        ((PagoDetailFrame) vista).createDiagnostocoCodLookup(detalleSiniestro);
        ((DesgloseSumaAseguradaGridInternalController) (((PagoDetailFrame) vista).getGridDesgloseSumaAsegurada()).getController()).setDetalleSiniestro(detalleSiniestro);
    }
    
    public PagoDetailFrameController(String detailFramePath, GridControl gridControl, DetalleSiniestro beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, (BeanVO) null, aplicarLogicaNegocio);
        this.detalleSiniestro = beanVO;
        ((PagoDetailFrame) vista).createDiagnostocoCodLookup(detalleSiniestro);
        ((DesgloseSumaAseguradaGridInternalController) (((PagoDetailFrame) vista).getGridDesgloseSumaAsegurada()).getController()).setDetalleSiniestro(detalleSiniestro);
    }
    
    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Liquidacion liquidacion = (Liquidacion) newPersistentObject;
        liquidacion.setEstatusPago(EstatusPago.PENDIENTE);
        liquidacion.setDetalleSiniestro(detalleSiniestro);
        detalleSiniestro.getPagos().add(liquidacion);
        agregarDesgloseCobertura(liquidacion.getDesgloseCobertura());
        Response res = super.insertRecord(newPersistentObject);
        gridControl.getReloadButton().doClick();
        return res;
    }
    
    private void agregarDesgloseCobertura(Set<DesgloseCobertura> des) {
        String ramo = "HCM";
        if (detalleSiniestro instanceof Vida) {
            ramo = "VIDA";
        } else if (detalleSiniestro instanceof Funerario) {
            ramo = "FUNE";
        }
        Session s = null;
        try {
            String sql = "FROM " + Cobertura.class.getName()
                    + " C WHERE C.ramo.idPropio = ? AND C.auditoria.activo=?";
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            List<Cobertura> l = s.createQuery(sql).setString(0, ramo).setBoolean(1, Boolean.TRUE).list();
            for (Cobertura cobertura : l) {
                des.add(new DesgloseCobertura(cobertura, new AuditoriaBasica(new Date(), General.usuario.getUserName(), Boolean.TRUE)));
            }
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "loadData", ex);
        } finally {
            s.close();
        }
        
    }
    
    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Liquidacion sin = (Liquidacion) s.get(Liquidacion.class, ((Liquidacion) beanVO).getId());
        Hibernate.initialize(sin.getDesgloseSumaAsegurada());
        Hibernate.initialize(sin.getDesgloseCobertura());
        s.close();
        beanVO = sin;
        return new VOResponse(beanVO);
    }
    
    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        Liquidacion liquidacion = (Liquidacion) persistentObject;
        Date fF = liquidacion.getFechaFactura();
        Date fR = liquidacion.getFechaRecepcion();
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
        
        return new VOResponse(liquidacion);
    }
}
