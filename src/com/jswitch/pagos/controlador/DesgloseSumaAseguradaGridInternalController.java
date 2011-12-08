package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.NotaTecnica;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.pagos.modelo.maestra.Factura;
import com.jswitch.pagos.modelo.transaccional.DesgloseSumaAsegurada;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.DiagnosticoSiniestro;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.util.client.ClientSettings;

/**
 *
 * @author Adrian
 */
public class DesgloseSumaAseguradaGridInternalController extends DefaultGridInternalController {

    private DetalleSiniestro detalleSiniestro;

    public DesgloseSumaAseguradaGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        loadDetalleSiniestro(((Factura) beanVO).getDetalleSiniestro());
        DesgloseSumaAsegurada desgloseSumaAsegurada = ((DesgloseSumaAsegurada) persistentObjects.get(0));
        DesgloseSumaAsegurada oldDesgloseSumaAsegurada = ((DesgloseSumaAsegurada) oldPersistentObjects.get(0));
        if (!logicaNegocio(desgloseSumaAsegurada)) {
            for (DiagnosticoSiniestro ds : detalleSiniestro.getDiagnosticoSiniestros()) {
                if (ds.getId().compareTo(desgloseSumaAsegurada.getDiagnosticoSiniestro().getId()) == 0) {
                    Response res = pagarDiagnostico(ds,
                            desgloseSumaAsegurada.getMonto() - oldDesgloseSumaAsegurada.getMonto());
                    if (res instanceof ErrorResponse) {
                        return res;
                    }
                }
            }
        } else {
            return new ErrorResponse("Valor Supera a La Factura");
        }
        return super.updateRecords(rowNumbers, oldPersistentObjects, persistentObjects);
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        loadDetalleSiniestro(((Factura) beanVO).getDetalleSiniestro());
        Object object = persistentObjects.get(0);
        DesgloseSumaAsegurada desgloseSumaAsegurada = ((DesgloseSumaAsegurada) object);
        for (DiagnosticoSiniestro ds : detalleSiniestro.getDiagnosticoSiniestros()) {
            if (ds.getId().compareTo(desgloseSumaAsegurada.getDiagnosticoSiniestro().getId()) == 0) {
                Response res = pagarDiagnostico(ds,
                        desgloseSumaAsegurada.getMonto() * -1);
                if (res instanceof ErrorResponse) {
                    return res;
                }
            }
        }
        return super.deleteRecords(persistentObjects);
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        Factura factura = (Factura) beanVO;
        loadDetalleSiniestro(factura.getDetalleSiniestro());
        Object object = newValueObjects.get(0);
        DesgloseSumaAsegurada desgloseSumaAsegurada = ((DesgloseSumaAsegurada) object);
        if (!logicaNegocio(desgloseSumaAsegurada)) {
            desgloseSumaAsegurada.setFactura(factura);
            for (DiagnosticoSiniestro ds : detalleSiniestro.getDiagnosticoSiniestros()) {
                if (ds.getId().compareTo(desgloseSumaAsegurada.getDiagnosticoSiniestro().getId()) == 0) {
                    Response res = pagarDiagnostico(ds,
                            desgloseSumaAsegurada.getMonto());
                    if (res instanceof ErrorResponse) {
                        return res;
                    }
                }
            }
        } else {
            return new ErrorResponse("Valor Supera a La Factura");
        }
        return super.insertRecords(rowNumbers, newValueObjects);
    }

    private boolean logicaNegocio(DesgloseSumaAsegurada asegurada) {
        Factura liquidacion = (Factura) beanVO;
        Double liquidado = asegurada.getMonto();
        for (DesgloseSumaAsegurada desgloseSumaAsegurada : liquidacion.getDesgloseSumaAsegurada()) {
            if (asegurada.getId() == null || desgloseSumaAsegurada.getId().compareTo(asegurada.getId()) != 0) {
                liquidado += desgloseSumaAsegurada.getMonto();
            }
        }
        return liquidado > liquidacion.getTotalFacturado();
    }

    private Response pagarDiagnostico(DiagnosticoSiniestro diagnosticoSiniestro, Double monto) {

        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            diagnosticoSiniestro = (DiagnosticoSiniestro) s.get(DiagnosticoSiniestro.class, diagnosticoSiniestro.getId());
            Hibernate.initialize(diagnosticoSiniestro.getTratamientos());
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            s.close();
        }

        Double montoPendiente = diagnosticoSiniestro.getMontoPendiente(), montoPagado = diagnosticoSiniestro.getMontoPagado();
        montoPendiente -= monto;
        montoPagado += monto;
        NotaTecnica notaTecnica = null;
        if (montoPendiente < 0) {
            String nota = JOptionPane.showInputDialog(miGrid,
                    ClientSettings.getInstance().getResources().getResource("Pago.Exedido"),
                    "", JOptionPane.INFORMATION_MESSAGE);
            if (nota != null) {
                notaTecnica = new NotaTecnica("Modificacion de monto por: " + nota,
                        new AuditoriaBasica(new Date(), General.usuario.getUserName(), Boolean.TRUE));
            } else {
                return new ErrorResponse("user.aborted");
            }
        }
        diagnosticoSiniestro.setMontoPagado(montoPagado);
        diagnosticoSiniestro.setMontoPendiente(montoPendiente);
        s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.update(diagnosticoSiniestro);
            if (notaTecnica != null) {
                s.save(notaTecnica);
                detalleSiniestro.getNotasTecnicas().add(notaTecnica);
                s.update(detalleSiniestro);
            }
            s.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            s.close();
        }

        return new VOResponse(diagnosticoSiniestro);
    }

    private void loadDetalleSiniestro(DetalleSiniestro detalleSiniestro) {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            this.detalleSiniestro = (DetalleSiniestro) s.get(DetalleSiniestro.class, detalleSiniestro.getId());
            Hibernate.initialize(this.detalleSiniestro.getDiagnosticoSiniestros());
            Hibernate.initialize(this.detalleSiniestro.getNotasTecnicas());
            Hibernate.initialize(this.detalleSiniestro.getPagos());
        } catch (Exception exception) {
            System.out.println(exception);
        } finally {
            s.close();
        }
    }
}
