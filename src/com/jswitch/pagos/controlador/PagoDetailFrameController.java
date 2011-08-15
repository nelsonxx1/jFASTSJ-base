package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.pagos.modelo.maestra.Pago;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
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
public class PagoDetailFrameController extends DefaultDetailFrameController {

    private DetalleSiniestro detalleSiniestro;

    public PagoDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    public PagoDetailFrameController(String detailFramePath, GridControl gridControl, DetalleSiniestro beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, null, aplicarLogicaNegocio);
        this.detalleSiniestro = beanVO;
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Pago pago = (Pago) newPersistentObject;
        pago.setEstatusPago(EstatusPago.PENDIENTE);
        Response res = super.insertRecord(pago);
        detalleSiniestro.getPagos().add(pago);
        insertAlGrid(detalleSiniestro);
        return res;
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Pago sin = (Pago) s.get(Pago.class, ((Pago) beanVO).getId());
        Hibernate.initialize(sin.getDesgloseSumaAsegurada());
        Hibernate.initialize(sin.getDesgloseCobertura());
        s.close();
        beanVO = sin;
        return new VOResponse(beanVO);
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

    private void insertAlGrid(DetalleSiniestro detalleSiniestro) {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.update(detalleSiniestro);
            s.getTransaction().commit();
        } catch (Exception ex) {
        } finally {
            s.close();
        }

    }
}
