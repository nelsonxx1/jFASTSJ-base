/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.pagos.modelo.maestra.Pago;
import java.util.Date;
import javax.swing.JOptionPane;
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

    public PagoDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Pago pago = (Pago) newPersistentObject;
        pago.setEstatusPago(EstatusPago.PENDIENTE);
        return super.insertRecord(pago);
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        Pago pago = (Pago) persistentObject;
        Date fF = pago.getFechaFactura();
        Date fR = pago.getFechaRecepcion();
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

        return new VOResponse(pago);
    }
}
