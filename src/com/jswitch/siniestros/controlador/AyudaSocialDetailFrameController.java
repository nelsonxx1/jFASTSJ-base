package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.AyudaSocial;
import com.jswitch.siniestros.vista.detalle.AyudaSocialDetailFrame;
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
public class AyudaSocialDetailFrameController extends DefaultDetailFrameController {

    Siniestro siniestro;

    public AyudaSocialDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Siniestro siniestro) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        this.siniestro = siniestro;
    }

    public AyudaSocialDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        AyudaSocial ca = (AyudaSocial) persistentObject;

        TipoPersona tp = ((AyudaSocialDetailFrame) vista).getLookupPersonaPago().getTipoPersona();
        if (tp != null) {
            ca.setTipoPersona(tp);
        }
        return super.updateRecord(oldPersistentObject, ca);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        AyudaSocial ca = (AyudaSocial) newPersistentObject;
        TipoPersona tp = ((AyudaSocialDetailFrame) vista).getLookupPersonaPago().getTipoPersona();
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
