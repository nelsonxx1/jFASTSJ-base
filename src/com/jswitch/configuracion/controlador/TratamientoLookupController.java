package com.jswitch.configuracion.controlador;

import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.controlador.util.DefaultLookupGridController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.configuracion.modelo.dominio.patologias.Diagnostico;
import com.jswitch.configuracion.modelo.dominio.patologias.Tratamiento;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.classic.Session;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.util.java.Consts;

/**
 * @author bc
 */
public class TratamientoLookupController extends DefaultLookupController  {

    Diagnostico d;

    public TratamientoLookupController(Diagnostico d) {
        this.d = d;
        this.setLookupDataLocator(new TratamientoLookupDataLocator(Tratamiento.class.getName()));
        this.setLookupGridController(new DefaultLookupGridController());
        setLookupValueObjectClassName(Tratamiento.class.getName());
        setAllColumnVisible(false);
        setVisibleColumn("nombre", true);
        setPreferredWidthColumn("nombre", 200);
        setSortedColumn("nombre", Consts.DESC_SORTED, 0);
        setSortableColumn("nombre", true);
        setFramePreferedSize(new java.awt.Dimension(200, 340));
        setCodeSelectionWindow(GRID_FRAME);
        setOnInvalidCode(ON_INVALID_CODE_RESTORE_FOCUS);


    }

    class TratamientoLookupDataLocator extends DefaultLookupDataLocator {

        public TratamientoLookupDataLocator(String classFullName) {
            super(classFullName);
        }

        @Override
        public Response loadData(int action,
                int startIndex,
                Map filteredColumns,
                ArrayList currentSortedColumns,
                ArrayList currentSortedVersusColumns,
                Class valueObjectType) {
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                List l = s.createQuery("FROM " + Tratamiento.class.getName() + " T "
                        + "WHERE T.diagnostico.id=?").setLong(0, d.getId()).list();
                return new VOListResponse(l, false, l.size());
            } catch (Exception e) {
                return new ErrorResponse(e.getMessage());
            } finally {
                s.close();
            }

        }
    }
}
