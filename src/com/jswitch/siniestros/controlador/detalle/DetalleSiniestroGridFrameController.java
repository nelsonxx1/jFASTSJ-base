/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.siniestros.controlador.detalle;

import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.siniestros.modelo.maestra.detalle.APS;
import com.jswitch.siniestros.modelo.maestra.detalle.AyudaSocial;
import com.jswitch.siniestros.modelo.maestra.detalle.CartaAval;
import com.jswitch.siniestros.modelo.maestra.detalle.Emergencia;
import com.jswitch.siniestros.modelo.maestra.detalle.Funerario;
import com.jswitch.siniestros.modelo.maestra.detalle.Reembolso;
import com.jswitch.siniestros.modelo.maestra.detalle.Vida;
import com.jswitch.siniestros.vista.detalle.APSDetailFrame;
import com.jswitch.siniestros.vista.detalle.AyudaSocialDetailFrame;
import com.jswitch.siniestros.vista.detalle.CartaAvalDetailFrame;
import com.jswitch.siniestros.vista.detalle.EmergenciaDetailFrame;
import com.jswitch.siniestros.vista.detalle.FunerarioDetailFrame;
import com.jswitch.siniestros.vista.detalle.ReembolsoDetailFrame;
import com.jswitch.siniestros.vista.detalle.VidaDetailFrame;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Adrian
 */
public class DetalleSiniestroGridFrameController extends DefaultGridFrameController {

    public DetalleSiniestroGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

//DetalleSiniestroDetailFrameController    
    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        if (detailFramePath != null) {

            if (detailFramePath.equals(
                    APSDetailFrame.class.getName())) {
                new APSDetailFrameController(APSDetailFrame.class.getName(), gridFrame.getGridControl(), (BeanVO) persistentObject, false);
            }
            if (detailFramePath.equals(
                    CartaAvalDetailFrame.class.getName())) {
                new CartaAvalDetailFrameController(CartaAvalDetailFrame.class.getName(), gridFrame.getGridControl(), (BeanVO) persistentObject, false);
            }
            if (detailFramePath.equals(
                    AyudaSocialDetailFrame.class.getName())) {
                new AyudaSocialDetailFrameController(AyudaSocialDetailFrame.class.getName(), gridFrame.getGridControl(), (BeanVO) persistentObject, false);
            }
            if (detailFramePath.equals(
                    EmergenciaDetailFrame.class.getName())) {
                new EmergenciaDetailFrameController(EmergenciaDetailFrame.class.getName(), gridFrame.getGridControl(), (BeanVO) persistentObject, false);
            }
            if (detailFramePath.equals(
                    FunerarioDetailFrame.class.getName())) {
                new FunerarioDetailFrameController(FunerarioDetailFrame.class.getName(), gridFrame.getGridControl(), (BeanVO) persistentObject, false);
            }
            if (detailFramePath.equals(
                    ReembolsoDetailFrame.class.getName())) {
                new ReembolsoDetailFrameController(ReembolsoDetailFrame.class.getName(), gridFrame.getGridControl(), (BeanVO) persistentObject, false);
            }
            if (detailFramePath.equals(
                    VidaDetailFrame.class.getName())) {
                new VidaDetailFrameController(VidaDetailFrame.class.getName(), gridFrame.getGridControl(), (BeanVO) persistentObject, false);
            }

        }
    }
}
