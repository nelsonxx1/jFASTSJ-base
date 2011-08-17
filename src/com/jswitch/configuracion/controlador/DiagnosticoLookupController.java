

package com.jswitch.configuracion.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.configuracion.modelo.dominio.Ramo;
import com.jswitch.configuracion.modelo.dominio.patologias.Diagnostico;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.send.java.FilterWhereClause;
import org.openswing.swing.tree.java.OpenSwingTreeNode;
import org.openswing.swing.util.java.Consts;

/**
 * @author bc
 */
public class DiagnosticoLookupController extends DefaultLookupController {

    public DiagnosticoLookupController() {
        this.setLookupDataLocator(new MarcaModeloLookupDataLocator(Diagnostico.class.getName()));
        this.setFramePreferedSize(new Dimension(500, 500));
        this.setCodeSelectionWindow(DiagnosticoLookupController.TREE_GRID_FRAME);
        this.getLookupDataLocator().setNodeNameAttribute("nombreLargo");
        this.setLookupValueObjectClassName(Diagnostico.class.getName());
        //this.addLookup2ParentLink("marcaModelo");
        this.setAllColumnVisible(false);
        // this.setVisibleColumn("id", true);
//        this.setVisibleColumn("ramo.nombre", true);

        this.setVisibleColumn("nombreLargo", true);
        //this.setPreferredWidthColumn("id", 50);
        this.setPreferredWidthColumn("nombreLargo", 300);
        //this.setPreferredWidthColumn("marca.nombre", 150);
        this.setFilterableColumn("nombreLargo", true);
        //this.setFilterableColumn("marca.nombre", true);
        //this.setSortableColumn("marca.nombre", true);
        this.setSortableColumn("nombreLargo", true);
        this.setSortedColumn("nombreLargo", Consts.ASC_SORTED);

    }

    class MarcaModeloLookupDataLocator extends DefaultLookupDataLocator {

        public MarcaModeloLookupDataLocator(String classFullName) {
            super(classFullName);
        }

        @Override
        public Response loadData(int action,
                int startIndex,
                Map filteredColumns,
                ArrayList currentSortedColumns,
                ArrayList currentSortedVersusColumns,
                Class valueObjectType) {
            try {
                Map map = getLookupFrameParams();
                if (map.get(Consts.TREE_FILTER) != null) {
                    Ramo ramo = (Ramo) map.get(Consts.TREE_FILTER);
                    if (ramo.getId() != -1) {
                        filteredColumns.put(
                                "ramo.id",
                                new FilterWhereClause[]{
                                    new FilterWhereClause("ramo.id", "=", ramo.getId()),
                                    null
                                });
                        //return new VOListResponse(new ArrayList(marca.getModelos()), false, marca.getModelos().size());
                        Session s = null;
                        try {
                            String sql = "FROM " + Diagnostico.class.getName()
                                    + " C WHERE C.especialidad.ramo.id =?  AND C.auditoria.activo=? "
                                    + "ORDER BY C.especialidad.nombre, C.nombre";
                            SessionFactory sf = HibernateUtil.getSessionFactory();
                            s = sf.openSession();
                            List l = s.createQuery(sql).setLong(0, ramo.getId()).setBoolean(1, Boolean.TRUE).list();
                            return new VOListResponse(l, false, l.size());
                        } catch (Exception ex) {
                            LoggerUtil.error(this.getClass(), "loadData", ex);
                            return new ErrorResponse(ex.getMessage());
                        } finally {
                            s.close();
                        }
                    } else {
                        return new VOListResponse(new ArrayList(0), false, 0);
                    }
                }
                return new VOListResponse(new ArrayList(0), false, 0);
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "loadData", ex);
                return new ErrorResponse(ex.getMessage());
            }

        }

        @Override
        public Response getTreeModel(JTree tree) {
            Session s = null;
            try {
                DefaultMutableTreeNode root = new OpenSwingTreeNode(null);
                s = HibernateUtil.getSessionFactory().openSession();
                List ramos = s.createQuery("FROM " + Ramo.class.getName()
                        + " M ORDER BY M.nombre") //.setCacheable(true)
                        .list();
                for (Object ramo : ramos) {
                    root.add(new OpenSwingTreeNode(ramo));
                }
                return new VOResponse(new DefaultTreeModel(root));
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "getTreeModel", ex);
                return new VOResponse(new DefaultTreeModel(new OpenSwingTreeNode()));
            } finally {
                s.close();
            }
        }
    }
}
