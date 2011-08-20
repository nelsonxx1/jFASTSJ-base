/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.pagos.modelo.maestra.Pago;
import com.jswitch.pagos.modelo.transaccional.DesgloseSumaAsegurada;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.DiagnosticoSiniestro;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
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
        for (Object object :
                persistentObjects) {
            DesgloseSumaAsegurada desgloseSumaAsegurada = ((DesgloseSumaAsegurada) object);
            for (DiagnosticoSiniestro diagnosticoSiniestro :
                    detalleSiniestro.getDiagnosticoSiniestros()) {
                if (desgloseSumaAsegurada.getDiagnosticoSiniestro().getId().compareTo(diagnosticoSiniestro.getId()) == 0) {
                    pagarDiagnostico(diagnosticoSiniestro, desgloseSumaAsegurada.getMonto());
                    break;
                }
            }
        }
        return super.updateRecords(rowNumbers, oldPersistentObjects, persistentObjects);
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        Pago pago = (Pago) beanVO;
        for (Object object :
                newValueObjects) {
            DesgloseSumaAsegurada desgloseSumaAsegurada = ((DesgloseSumaAsegurada) object);
            desgloseSumaAsegurada.setPago(pago);
            pago.getDesgloseSumaAsegurada().add(desgloseSumaAsegurada);
            for (DiagnosticoSiniestro diagnosticoSiniestro :
                    detalleSiniestro.getDiagnosticoSiniestros()) {
                if (desgloseSumaAsegurada.getDiagnosticoSiniestro().getId().compareTo(diagnosticoSiniestro.getId()) == 0) {
                    pagarDiagnostico(diagnosticoSiniestro, desgloseSumaAsegurada.getMonto());
                    break;
                }
            }
        }
        return super.insertRecords(rowNumbers, newValueObjects);
    }

    private void pagarDiagnostico(DiagnosticoSiniestro diagnosticoSiniestro, Double monto) {
        Double montoPendiente = diagnosticoSiniestro.getMontoPendiente(), montoPagado = diagnosticoSiniestro.getMontoPagado();
        montoPendiente -= monto;
        montoPagado += monto;
        if (montoPendiente < 0) {
            JOptionPane.showMessageDialog(miGrid,
                    ClientSettings.getInstance().getResources().getResource("Pago.Exedido"),
                    "", JOptionPane.INFORMATION_MESSAGE);
        }
        diagnosticoSiniestro.setMontoPagado(montoPagado);
        diagnosticoSiniestro.setMontoPendiente(montoPendiente);
        Session s = null;
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(diagnosticoSiniestro);
        s.getTransaction().commit();
        s.close();
    }
}
