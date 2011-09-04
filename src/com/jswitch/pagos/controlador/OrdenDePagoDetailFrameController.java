package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.util.List;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Adrian
 */
public class OrdenDePagoDetailFrameController extends DefaultDetailFrameController {

    public OrdenDePagoDetailFrameController(String detailFramePath,
            GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        OrdenDePago p = (OrdenDePago) newPersistentObject;
        if (p.getAutoSearch()) {
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                List l = s.createQuery("FROM "
                        + DetalleSiniestro.class.getName() + " C WHERE "
                        + "C.personaPago.id=?").
                        setLong(0, p.getPersonaPago().getId()).list();
                for (Object detalleSiniestro : l) {
                    p.getDetalleSiniestros().add(
                            (DetalleSiniestro) detalleSiniestro);
                }
            } finally {
                s.close();
            }
        }
        return super.insertRecord(newPersistentObject);
    }
}
