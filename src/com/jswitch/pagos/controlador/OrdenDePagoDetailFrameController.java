package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.vista.OrdenDePagoDetailFrame;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.util.List;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author Adrian
 */
public class OrdenDePagoDetailFrameController extends DefaultDetailFrameController {

    public OrdenDePagoDetailFrameController() {
    }

    public OrdenDePagoDetailFrameController(String detailFramePath,
            GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    public void init(GridControl gridControl, BeanVO beanVO,
            Boolean aplicarLogicaNegocio) {
        this.gridControl = gridControl;
        this.beanVO = beanVO;
        this.aplicarLogicaNegocio = aplicarLogicaNegocio;
        try {
            vista = new OrdenDePagoDetailFrame();
            vista.inicializar(this, true);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "new", ex);
        }
        vista.getMainPanel().setMode(Consts.INSERT);
        if (beanVO != null) {
            vista.getMainPanel().getVOModel().setValue("personaPago",
                    ((OrdenDePago) beanVO).getPersonaPago());
            vista.getMainPanel().pull("personaPago");
           // vista.getMainPanel().setMode(Consts.INSERT);
        }
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
