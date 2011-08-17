package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.siniestros.vista.DiagnosticoSiniestroDetailFrame;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Adrian
 */
public class DiagnosticoSiniestroGridInternalController extends DefaultGridInternalController {

    public DiagnosticoSiniestroGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new DiagnosticoSiniestroDetailFrameController(DiagnosticoSiniestroDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, Boolean.FALSE);
    }
}
