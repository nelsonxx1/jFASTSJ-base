package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.controlador.util.DefaultLookupGridController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.DiagnosticoSiniestro;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.BooleanType;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.util.java.Consts;
import org.openswing.swing.util.server.HibernateUtils;

/**
 * @author bc
 */
public class DiagnosticoSiniestroLookupController extends DefaultLookupController {

    DetalleSiniestro detalleSiniestro;

    public DiagnosticoSiniestroLookupController() {
        this.setLookupDataLocator(new DiagnosticoSiniestroLookupDataLocator(DiagnosticoSiniestro.class.getName()));
        this.setLookupGridController(new DefaultLookupGridController());
        setLookupValueObjectClassName(DiagnosticoSiniestro.class.getName());
        setAllColumnVisible(false);
        setVisibleColumn("diagnostico.nombre", true);
        setVisibleColumn("montoPendiente", true);
        setVisibleColumn("montoPagado", true);
        setPreferredWidthColumn("diagnostico.nombre", 250);
        setPreferredWidthColumn("montoPendiente", 50);
        setPreferredWidthColumn("montoPagado", 50);
        setSortableColumn("diagnostico.nombre", true);
        setSortedColumn("diagnostico.nombre", Consts.ASC_SORTED, 0);
        setSortableColumn("montoPendiente", true);
        setSortableColumn("montoPagado", true);
        setFilterableColumn("montoPendiente", true);
        setFilterableColumn("montoPagado", true);
        setFramePreferedSize(new java.awt.Dimension(380, 340));
        setCodeSelectionWindow(GRID_FRAME);
        setOnInvalidCode(ON_INVALID_CODE_RESTORE_FOCUS);

    }

    public void setDetalleSiniestro(DetalleSiniestro detalleSiniestro) {
        this.detalleSiniestro = detalleSiniestro;
    }

    class DiagnosticoSiniestroLookupDataLocator extends DefaultLookupDataLocator {

        public DiagnosticoSiniestroLookupDataLocator(String classFullName) {
            super(classFullName);
        }

        @Override
        public Response loadData(int action,
                int startIndex,
                Map filteredColumns,
                ArrayList currentSortedColumns,
                ArrayList currentSortedVersusColumns,
                Class valueObjectType) {
            if (detalleSiniestro != null) {
                Session s = null;
                try {
                    String sql = "FROM " + DiagnosticoSiniestro.class.getName()
                            + " C WHERE C.detalleSiniestro.id = ?";// + detalleSiniestro.getId() ;
                    sql += " AND C.auditoria.activo=?";
                    SessionFactory sf = HibernateUtil.getSessionFactory();
                    s = sf.openSession();
                    Response res = HibernateUtils.getAllFromQuery(
                            filteredColumns,
                            currentSortedColumns,
                            currentSortedVersusColumns,
                            valueObjectType,
                            sql,
                            new Object[]{detalleSiniestro.getId(), new Boolean(true)},
                            new Type[]{new LongType(), new BooleanType()},
                            "C",
                            sf,
                            s);
                    return res;


                } catch (Exception ex) {
                    LoggerUtil.error(this.getClass(), "loadData", ex);
                    return new ErrorResponse(ex.getMessage());
                } finally {
                    s.close();
                }
            } else {
                return new VOListResponse();
            }
        }

        @Override
        public Response validateCode(String code) {

            Session s = null;
            try {
                String sql = "FROM " + DiagnosticoSiniestro.class.getName()
                        + " C WHERE C.detalleSiniestro.id = ?";// + detalleSiniestro.getId() ;
                sql += " AND C.auditoria.activo=? AND upper(C.diagnosticoSiniestro.diagnostico.nombre) like ?";
                s = HibernateUtil.getSessionFactory().openSession();
                List list = s.createQuery(sql).
                        setLong(0, detalleSiniestro.getId()).
                        setBoolean(1, true).
                        setString(2, "%" + code.toUpperCase().trim() + "%").list();
                return new VOListResponse(list, false, list.size());
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "validateCode", ex);
                return new ErrorResponse(ex.getMessage());
            } finally {
                s.close();
            }
        }
    }
}
