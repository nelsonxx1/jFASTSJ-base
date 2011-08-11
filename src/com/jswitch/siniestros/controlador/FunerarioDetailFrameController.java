package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.Funerario;
import com.jswitch.siniestros.vista.detalle.FunerarioDetailFrame;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author orlandobcrra
 */
public class FunerarioDetailFrameController extends DefaultDetailFrameController {

    public FunerarioDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }
    private Siniestro siniestro;

    public FunerarioDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Siniestro siniestro) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        this.siniestro = siniestro;
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        Funerario fune = (Funerario) persistentObject;
        TipoPersona tp = ((FunerarioDetailFrame) vista).getLookupPersonaPago().getTipoPersona();
        if (tp != null) {
            fune.setTipoPersona(tp);
        }
        return super.updateRecord(oldPersistentObject, fune);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Funerario fune = (Funerario) newPersistentObject;
        TipoPersona tp = ((FunerarioDetailFrame) vista).getLookupPersonaPago().getTipoPersona();
        if (tp != null) {
            fune.setTipoPersona(tp);
        }
        fune.setSiniestro(siniestro);
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("FROM " + EtapaSiniestro.class.getName() + " C"
                    + " WHERE C.nombre='CARTA COMPROMISO'");
            EtapaSiniestro et = (EtapaSiniestro) q.uniqueResult();
            fune.setEtapaSiniestro(et);            
            return super.insertRecord(fune);            
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord", ex));
        } finally {
            s.close();
        }
    }
}
