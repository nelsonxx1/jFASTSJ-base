package com.jswitch.siniestros.controlador.detalle;

import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.pagos.controlador.PagoDetailFrameController;
import com.jswitch.pagos.modelo.maestra.Pago;
import com.jswitch.pagos.vista.PagoDetailFrame;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import java.awt.event.ActionEvent;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;

/**
 *
 * @author orlandobcrra
 */
public class DetalleSiniestroDetailFrameController extends DefaultDetailFrameController {

    protected Siniestro siniestro;

    public DetalleSiniestroDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    public DetalleSiniestroDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Siniestro siniestro) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        this.siniestro = siniestro;
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        DetalleSiniestro sin = (DetalleSiniestro) s.get(DetalleSiniestro.class, ((DetalleSiniestro) beanVO).getId());
        Hibernate.initialize(sin.getNotasTecnicas());
        Hibernate.initialize(sin.getObservaciones());
        Hibernate.initialize(sin.getPagos());
        Hibernate.initialize(sin.getDocumentos());
        s.close();
        beanVO = sin;
        return new VOResponse(beanVO);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new PagoDetailFrameController(PagoDetailFrame.class.getName(), gridControl, (DetalleSiniestro) beanVO, true);
    }
}
