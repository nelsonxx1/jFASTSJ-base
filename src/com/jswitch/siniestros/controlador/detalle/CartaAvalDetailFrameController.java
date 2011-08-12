package com.jswitch.siniestros.controlador.detalle;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.CartaAval;
import com.jswitch.siniestros.vista.detalle.CartaAvalDetailFrame;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author orlandobcrra
 */
public class CartaAvalDetailFrameController extends DetalleSiniestroDetailFrameController {

    public CartaAvalDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Siniestro siniestro) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        this.siniestro = siniestro;
    }

    public CartaAvalDetailFrameController(String name, GridControl miGrid, BeanVO beanVO, boolean b) {
        super(name, miGrid, beanVO, b);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        CartaAval ca = (CartaAval) persistentObject;

        TipoPersona tp = ((CartaAvalDetailFrame) vista).getLookupPersonaPago().getTipoPersona();
        if (tp != null) {
            ca.setTipoPersona(tp);
        }
        return super.updateRecord(oldPersistentObject, ca);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        CartaAval ca = (CartaAval) newPersistentObject;
        TipoPersona tp = ((CartaAvalDetailFrame) vista).getLookupPersonaPago().getTipoPersona();
        ca.setTipoPersona(tp);
        ca.setSiniestro(siniestro);
        Session s = null;
        try {
            vista.saveGridsData();
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("FROM " + EtapaSiniestro.class.getName() + " C"
                    + " WHERE C.nombre='CARTA COMPROMISO'");
            EtapaSiniestro et = (EtapaSiniestro) q.uniqueResult();
            ca.setEtapaSiniestro(et);
            return super.insertRecord(ca);
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord", ex));
        } finally {
            s.close();
        }
    }
}
