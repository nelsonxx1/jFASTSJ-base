package com.jswitch.siniestros.controlador.detalle;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.configuracion.modelo.maestra.Plan;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.Vida;
import com.jswitch.siniestros.vista.detalle.VidaDetailFrame;
import javax.swing.JOptionPane;
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
public class VidaDetailFrameController extends DetalleSiniestroDetailFrameController {

    public VidaDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    public VidaDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Siniestro siniestro) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        this.siniestro = siniestro;
        if (!checkRamo()) {
            vista.dispose();
            JOptionPane.showMessageDialog(gridControl, "Detalle no Valido\n"
                    + "para este Asegurado", "Caution", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        Vida vida = (Vida) persistentObject;
        TipoPersona tp = ((VidaDetailFrame) vista).getLookupPersonaPago().getTipoPersona();
        if (tp != null) {
            vida.setTipoPersona(tp);
        }
        return super.updateRecord(oldPersistentObject, vida);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Vida vida = (Vida) newPersistentObject;
        TipoPersona tp = ((VidaDetailFrame) vista).getLookupPersonaPago().getTipoPersona();
        if (tp != null) {
            vida.setTipoPersona(tp);
        }
        //TODO HACER EL PAGO A LOS Q SON
        vida.setSiniestro(siniestro);
        Session s = null;
        try {
            vista.saveGridsData();
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("FROM " + EtapaSiniestro.class.getName() + " C"
                    + " WHERE C.nombre='CARTA COMPROMISO'");
            EtapaSiniestro et = (EtapaSiniestro) q.uniqueResult();
            vida.setEtapaSiniestro(et);
            return super.insertRecord(vida);
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord", ex));
        } finally {
            s.close();
        }
    }

    private boolean checkRamo() {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("SELECT 1 AS SW FROM " + Plan.class.getName() + " P "
                    + " JOIN P.sumasAseguradas S "
                    + " WHERE S.diagnostico.especialidad.ramo.nombre='VIDA'"
                    + " AND P.id=?");
            int r = q.setLong(0, siniestro.getAsegurado().getPlan().getId()).list().size();
            return (r == 1)
                    ? true : false;
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "checkRamo", ex);
            return false;
        } finally {
            s.close();
        }
    }
}
