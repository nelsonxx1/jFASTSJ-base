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
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
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

    public void setDetalleSiniestro(DetalleSiniestro detalleSiniestro) {
        this.detalleSiniestro = detalleSiniestro;
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        for (int i = 0; i < rowNumbers.length; i++) {
            DesgloseSumaAsegurada desgloseSumaAsegurada = ((DesgloseSumaAsegurada) persistentObjects.get(i));
            DesgloseSumaAsegurada oldDesgloseSumaAsegurada = ((DesgloseSumaAsegurada) oldPersistentObjects.get(i));
            if (logicaNegocio(desgloseSumaAsegurada)) {
                persistentObjects.remove(i);
                oldPersistentObjects.remove(i);
                i--;
                continue;
            }
            for (DiagnosticoSiniestro ds : detalleSiniestro.getDiagnosticoSiniestros()) {
                if (ds.getId().compareTo(desgloseSumaAsegurada.getDiagnosticoSiniestro().getId()) == 0) {
                    pagarDiagnostico(ds,
                            desgloseSumaAsegurada.getMonto() - oldDesgloseSumaAsegurada.getMonto());
                }
            }
        }
        if (persistentObjects.isEmpty()) {
            return new ErrorResponse("logicaNegocioDSA");
        }
        return super.updateRecords(rowNumbers, oldPersistentObjects, persistentObjects);
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        for (int i = 0; i < persistentObjects.size(); i++) {
            Object object = persistentObjects.get(i);
            DesgloseSumaAsegurada desgloseSumaAsegurada = ((DesgloseSumaAsegurada) object);
            if (logicaNegocio(desgloseSumaAsegurada)) {
                persistentObjects.remove(object);
                i--;
                continue;
            }
            for (DiagnosticoSiniestro ds : detalleSiniestro.getDiagnosticoSiniestros()) {
                if (ds.getId().compareTo(desgloseSumaAsegurada.getDiagnosticoSiniestro().getId()) == 0) {
                    pagarDiagnostico(ds,
                            desgloseSumaAsegurada.getMonto() * -1);
                }
            }
        }
        if (persistentObjects.isEmpty()) {
            return new ErrorResponse("logicaNegocioDSA");
        }
        return super.deleteRecords(persistentObjects);

    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        Factura factura = (Factura) beanVO;
        for (int i = 0; i < newValueObjects.size(); i++) {
            Object object = newValueObjects.get(i);
            DesgloseSumaAsegurada desgloseSumaAsegurada = ((DesgloseSumaAsegurada) object);
            if (logicaNegocio(desgloseSumaAsegurada)) {
                newValueObjects.remove(object);
                i--;
                continue;
            }
            desgloseSumaAsegurada.setFactura(factura);
            factura.getDesgloseSumaAsegurada().add(desgloseSumaAsegurada);
            for (DiagnosticoSiniestro ds : detalleSiniestro.getDiagnosticoSiniestros()) {
                if (ds.getId().compareTo(desgloseSumaAsegurada.getDiagnosticoSiniestro().getId()) == 0) {
                    pagarDiagnostico(ds,
                            desgloseSumaAsegurada.getMonto());
                }
            }

        }
        if (newValueObjects.isEmpty()) {
            return new ErrorResponse("logicaNegocioDSA");
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

    private void pagarDiagnostico(DiagnosticoSiniestro diagnosticoSiniestro, Double monto) {
        Double montoPendiente = diagnosticoSiniestro.getMontoPendiente(), montoPagado = diagnosticoSiniestro.getMontoPagado();
        montoPendiente -= monto;
        montoPagado += monto;
        NotaTecnica notaTecnica = null;
        if (montoPendiente < 0) {
            do {
                String nota = JOptionPane.showInputDialog(miGrid,
                        ClientSettings.getInstance().getResources().getResource("Pago.Exedido"),
                        "", JOptionPane.INFORMATION_MESSAGE);
                if (nota != null) {
                    notaTecnica = new NotaTecnica(nota,
                            new AuditoriaBasica(new Date(), General.usuario.getUserName(), Boolean.TRUE));
                }
            } while (notaTecnica == null);
        }
        diagnosticoSiniestro.setMontoPagado(montoPagado);
        diagnosticoSiniestro.setMontoPendiente(montoPendiente);
        Session s = null;
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(diagnosticoSiniestro);
        if (notaTecnica != null) {
            s.save(notaTecnica);
            detalleSiniestro.getNotasTecnicas().add(notaTecnica);
            s.update(detalleSiniestro);
        }
        s.getTransaction().commit();
        s.close();
    }
}
