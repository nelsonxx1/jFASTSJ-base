/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.pagos.vista.DesgloseSumaAseguradaDetailFrame;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Adrian
 */
public class DesgloseSumaAseguradaGridInternalController extends DefaultGridInternalController {

    public DesgloseSumaAseguradaGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new DesgloseSumaAseguradaDetailFrameController(DesgloseSumaAseguradaDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, Boolean.FALSE);
    }
}
