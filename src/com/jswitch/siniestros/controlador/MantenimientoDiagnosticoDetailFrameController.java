package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.NotaTecnica;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.DiagnosticoSiniestro;
import com.jswitch.siniestros.modelo.transaccional.MantenimientoDiagnostico;
import com.jswitch.siniestros.vista.MantenimientoDiagnosticoDetailFrame;
import java.util.Date;
import org.hibernate.classic.Session;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author Adrian
 */
public class MantenimientoDiagnosticoDetailFrameController extends DefaultDetailFrameController {

    private DiagnosticoSiniestro diagnosticoSin;
    private DetalleSiniestro detalleSiniestro;
    private DefaultDetailFrame frame;

    public MantenimientoDiagnosticoDetailFrameController(DefaultDetailFrame frame,
            boolean b, DiagnosticoSiniestro diagnosticoSiniestro, DetalleSiniestro detalleSiniestro) {
        this.frame = frame;
        this.diagnosticoSin = diagnosticoSiniestro;
        this.detalleSiniestro = detalleSiniestro;
        vista = new MantenimientoDiagnosticoDetailFrame();
        ((MantenimientoDiagnosticoDetailFrame) vista).setDiagnosticoSiniestro(diagnosticoSiniestro);
        vista.inicializar(this, true);
        vista.getMainPanel().setMode(Consts.INSERT);
        vista.getMainPanel().getVOModel().setValue("diagnosticoSiniestro", diagnosticoSin);
        vista.getMainPanel().getVOModel().setValue("montoAnterior", diagnosticoSin.getMontoPendiente());
        vista.getMainPanel().pull("diagnosticoSiniestro");
        vista.getMainPanel().pull("montoAnterior");
    }

    @Override
    public void createPersistentObject(ValueObject persistentObject)
            throws Exception {
        ((MantenimientoDiagnostico) persistentObject).setDiagnosticoSiniestro(diagnosticoSin);
        ((MantenimientoDiagnostico) persistentObject).setMontoAnterior(diagnosticoSin.getMontoPendiente());
        super.createPersistentObject(persistentObject);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        diagnosticoSin.setMontoPendiente(((MantenimientoDiagnostico) newPersistentObject).getMontoActual());
        Date d = new Date();
        diagnosticoSin.getAuditoria().setFechaUpdate(d);
        diagnosticoSin.getAuditoria().setUsuarioUpdate(General.usuario.getUserName());
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        NotaTecnica nota = new NotaTecnica("Modificacion monto reservado: "
                + ((MantenimientoDiagnostico) newPersistentObject).getJustificacion(),
                new AuditoriaBasica(d, General.usuario.getUserName(), Boolean.TRUE));
        detalleSiniestro.getNotasTecnicas().add(nota);
        s.save(nota);
        s.update(diagnosticoSin);
        s.update(detalleSiniestro);
        s.getTransaction().commit();
        s.close();

        Response res = super.insertRecord(newPersistentObject);
        frame.reloadGridsData();
        return res;
    }
}
