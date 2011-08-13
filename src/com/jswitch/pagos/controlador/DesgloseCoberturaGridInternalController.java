/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.pagos.modelo.transaccional.DesgloseCobertura;
import java.util.ArrayList;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;

/**
 *
 * @author Adrian
 */
public class DesgloseCoberturaGridInternalController extends DefaultGridInternalController {

    public DesgloseCoberturaGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        for (Object object : persistentObjects) {
            DesgloseCobertura dc = (DesgloseCobertura) object;
            ((DesgloseCobertura) object).setMontoNoAmparado(dc.getMontoFacturado() - dc.getMontoAmparado());
        }

        return super.updateRecords(rowNumbers, oldPersistentObjects, persistentObjects);
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        for (Object object : newValueObjects) {
            DesgloseCobertura dc = (DesgloseCobertura) object;
            ((DesgloseCobertura) object).setMontoNoAmparado(dc.getMontoFacturado() - dc.getMontoAmparado());
        }

        return super.insertRecords(rowNumbers, newValueObjects);
    }

    
}
