package com.jswitch.siniestros.controlador;

import com.jswitch.siniestros.controlador.detalle.AyudaSocialDetailFrameController;
import com.jswitch.siniestros.controlador.detalle.FunerarioDetailFrameController;
import com.jswitch.siniestros.controlador.detalle.ReembolsoDetailFrameController;
import com.jswitch.siniestros.controlador.detalle.APSDetailFrameController;
import com.jswitch.siniestros.controlador.detalle.VidaDetailFrameController;
import com.jswitch.siniestros.controlador.detalle.CartaAvalDetailFrameController;
import com.jswitch.siniestros.controlador.detalle.EmergenciaDetailFrameController;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.APS;
import com.jswitch.siniestros.modelo.maestra.detalle.AyudaSocial;
import com.jswitch.siniestros.modelo.maestra.detalle.CartaAval;
import com.jswitch.siniestros.modelo.maestra.detalle.Emergencia;
import com.jswitch.siniestros.modelo.maestra.detalle.Funerario;
import com.jswitch.siniestros.modelo.maestra.detalle.Reembolso;
import com.jswitch.siniestros.modelo.maestra.detalle.Vida;
import com.jswitch.siniestros.vista.detalle.APSDetailFrame;
import com.jswitch.siniestros.vista.detalle.ReembolsoDetailFrame;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Luis Adrian Gonzalez Benavides
 */
public class DetalleSiniestroGridInternalController extends DefaultGridInternalController {

    public DetalleSiniestroGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        if (persistentObject instanceof APS) {
            //new APSDetailFrameController(APSDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, false);
        }
        if (persistentObject instanceof CartaAval) {
            //new CartaAvalDetailFrameController(CartaAvalDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, false);
        }
        if (persistentObject instanceof AyudaSocial) {
            //new AyudaSocialDetailFrameController(AyudaSocialDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, false);
        }
          if (persistentObject instanceof Emergencia) {
            //new EmergenciaDetailFrameController(EmergenciaDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, false);
        }
        if (persistentObject instanceof Funerario) {
            //new FunerarioDetailFrameController(FunerarioDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, false);
        }
        if (persistentObject instanceof Reembolso) {
            new ReembolsoDetailFrameController(ReembolsoDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, false);
        }
         if (persistentObject instanceof Vida) {
            //new VidaDetailFrameController(VidaDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, false);
        }
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {

        ArrayList<DetalleSiniestro> al = null;
        int max = 0;

        if (beanVO != null) {
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();

                Query q2 = s.createQuery("SELECT c.detalleSiniestro  FROM " + Siniestro.class.getName() + " c "
                        + "  WHERE c.id=?").setLong(0, ((Siniestro) beanVO).getId());
                al = new ArrayList<DetalleSiniestro>(q2.list());
                //s = HibernateUtil.getSessionFactory().openSession();
            } catch (Exception ex) {
                return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord", ex));
            } finally {
                s.close();
            }
            max = al.size();
        }
        return new VOListResponse(al, false, max);

    }
}
