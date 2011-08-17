package com.jswitch.siniestros.controlador.detalle;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.configuracion.modelo.maestra.Plan;
import com.jswitch.pagos.controlador.PagoDetailFrameController;
import com.jswitch.pagos.vista.PagoDetailFrame;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.APS;
import com.jswitch.siniestros.modelo.maestra.detalle.AyudaSocial;
import com.jswitch.siniestros.modelo.maestra.detalle.CartaAval;
import com.jswitch.siniestros.modelo.maestra.detalle.Emergencia;
import com.jswitch.siniestros.modelo.maestra.detalle.Funerario;
import com.jswitch.siniestros.modelo.maestra.detalle.Reembolso;
import com.jswitch.siniestros.modelo.maestra.detalle.Vida;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroDetailFrame;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author orlandobcrra
 */
public class DetalleSiniestroDetailFrameController extends DefaultDetailFrameController {

    private HashMap<Class, String> etapaInicial;
    private Siniestro siniestro;

    public DetalleSiniestroDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Class tipoDetalle) {
        if (tipoDetalle.equals(Vida.class) && !checkRamo("VIDA")) {
            JOptionPane.showMessageDialog(gridControl, "Ramo Vida no aplica\n"
                    + "para este Asegurado", "Caution", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (tipoDetalle.equals(Funerario.class) && !checkRamo("FUNERARIO")) {
            JOptionPane.showMessageDialog(gridControl, "Ramo Funerario no aplica\n"
                    + "para este Asegurado", "Caution", JOptionPane.INFORMATION_MESSAGE);
            return;
        }


        this.gridControl = gridControl;
        this.beanVO = beanVO;
        this.aplicarLogicaNegocio = aplicarLogicaNegocio;


        vista = new DetalleSiniestroDetailFrame();
        ((DetalleSiniestroDetailFrame) vista).setTipo(tipoDetalle);
        vista.inicializar(this, true);



        if (beanVO != null) {
            vista.getMainPanel().reload();
            vista.getMainPanel().setMode(Consts.READONLY);
        } else {
            vista.getMainPanel().setMode(Consts.INSERT);
        }

        etapaInicial = new HashMap<Class, String>(0);
        etapaInicial.put(APS.class, "CARTA COMPROMISO");
        etapaInicial.put(AyudaSocial.class, "CARTA COMPROMISO");
        etapaInicial.put(CartaAval.class, "CARTA COMPROMISO");
        etapaInicial.put(Emergencia.class, "CARTA COMPROMISO");
        etapaInicial.put(Funerario.class, "CARTA COMPROMISO");
        etapaInicial.put(Reembolso.class, "CARTA COMPROMISO");
        etapaInicial.put(Vida.class, "CARTA COMPROMISO");


    }

    public DetalleSiniestroDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Siniestro siniestro, Class tipoDetalle) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio, tipoDetalle);
        this.siniestro = siniestro;
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        DetalleSiniestro sin = (DetalleSiniestro) s.get(DetalleSiniestro.class, ((DetalleSiniestro) beanVO).getId());
        Hibernate.initialize(sin.getNotasTecnicas());
        Hibernate.initialize(sin.getObservaciones());
        Hibernate.initialize(sin.getPagos());
        Hibernate.initialize(sin.getDiagnosticoSiniestros());
        Hibernate.initialize(sin.getDocumentos());
        s.close();
        beanVO = sin;
        return new VOResponse(beanVO);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new PagoDetailFrameController(PagoDetailFrame.class.getName(), gridControl, (DetalleSiniestro) beanVO, true);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        TipoPersona tp = ((DetalleSiniestroDetailFrame) vista).getTipoPersonaSelected();
        if (tp != null) {
            ((DetalleSiniestro) persistentObject).setTipoPersona(tp);
        }
        return super.updateRecord(oldPersistentObject, persistentObject);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Session s = null;
        try {
            vista.saveGridsData();
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("FROM " + EtapaSiniestro.class.getName() + " C"
                    + " WHERE C.nombre='" + etapaInicial.get(newPersistentObject.getClass()) + "'");
            EtapaSiniestro et = (EtapaSiniestro) q.uniqueResult();
            ((DetalleSiniestro) newPersistentObject).setEtapaSiniestro(et);

            if (newPersistentObject instanceof Reembolso) {
                q = s.createQuery("FROM " + TipoPersona.class.getName() + " C"
                        + " WHERE C.idPropio='TIT'");
                TipoPersona tp = (TipoPersona) q.uniqueResult();
                ((DetalleSiniestro) newPersistentObject).setTipoPersona(tp);
                ((DetalleSiniestro) newPersistentObject).setPersonaPago(siniestro.getCertificado().getTitular().getPersona());

            } else {
                TipoPersona tp = ((DetalleSiniestroDetailFrame) vista).getTipoPersonaSelected();
                if (tp != null) {
                    ((DetalleSiniestro) newPersistentObject).setTipoPersona(tp);
                }
            }
            ((DetalleSiniestro) newPersistentObject).setSiniestro(siniestro);
            return super.insertRecord((newPersistentObject));
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord", ex));
        } finally {
            s.close();
        }
    }

    private boolean checkRamo(String nombreRamo) {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("SELECT 1 AS SW FROM " + Plan.class.getName() + " P "
                    + " JOIN P.sumasAseguradas S "
                    + " WHERE S.diagnostico.especialidad.ramo.nombre='" + nombreRamo + "'"
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
