package com.jswitch.siniestros.controlador.detalle;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.pagos.controlador.DiagnosticoSiniestroDetailFrameController;
import com.jswitch.pagos.vista.DiagnosticoSiniestroDetailFrame;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.APS;
import com.jswitch.siniestros.vista.detalle.APSDetailFrame;
import java.awt.event.ActionEvent;
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
public class APSDetailFrameController extends DetalleSiniestroDetailFrameController {

    public APSDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    public APSDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Siniestro siniestro) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        this.siniestro = siniestro;
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        APS aps = (APS) persistentObject;
        TipoPersona tp = ((APSDetailFrame) vista).getLookupPersonaPago().getTipoPersona();
        if (tp != null) {
            aps.setTipoPersona(tp);
        }
        return super.updateRecord(oldPersistentObject, aps);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        APS aps = (APS) newPersistentObject;
        TipoPersona tp = ((APSDetailFrame) vista).getLookupPersonaPago().getTipoPersona();
        if (tp != null) {
            aps.setTipoPersona(tp);
        }
        aps.setSiniestro(siniestro);
        Session s = null;
        try {
            vista.saveGridsData();
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("FROM " + EtapaSiniestro.class.getName() + " C"
                    + " WHERE C.nombre='CARTA COMPROMISO'");
            EtapaSiniestro et = (EtapaSiniestro) q.uniqueResult();
            aps.setEtapaSiniestro(et);
            return super.insertRecord(aps);
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord", ex));
        } finally {
            s.close();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new DiagnosticoSiniestroDetailFrameController(((APSDetailFrame) vista).getGridDiagnosticos(), false, (DetalleSiniestro) beanVO);
    }
}
