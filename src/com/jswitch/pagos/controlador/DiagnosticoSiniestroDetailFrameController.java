package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.configuracion.controlador.TratamientoLookupController;
import com.jswitch.configuracion.modelo.dominio.patologias.Tratamiento;
import com.jswitch.pagos.modelo.transaccional.DesgloseSumaAsegurada;
import com.jswitch.pagos.vista.DiagnosticoSiniestroDetailFrame;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import com.jswitch.siniestros.modelo.maestra.DiagnosticoSiniestro;
import java.awt.event.ActionEvent;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.lookup.client.LookupParent;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Adrian
 */
public class DiagnosticoSiniestroDetailFrameController extends DefaultDetailFrameController {

    public DiagnosticoSiniestroDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }
    private DetalleSiniestro detalleSin;

    public DiagnosticoSiniestroDetailFrameController(GridControl migrid, boolean b, DetalleSiniestro detalleSin) {
        super(DiagnosticoSiniestroDetailFrame.class.getName(), migrid, null, b);
        this.detalleSin = detalleSin;
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        DiagnosticoSiniestro sin = (DiagnosticoSiniestro) s.get(DiagnosticoSiniestro.class, ((DiagnosticoSiniestro) beanVO).getId());
        Hibernate.initialize(sin.getTratamientos());
        s.close();
        beanVO = sin;
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        DiagnosticoSiniestro ds = (DiagnosticoSiniestro) newPersistentObject;
        ds.setDetalleSiniestro(detalleSin);
        Response res = super.insertRecord(ds);
//        detalleSin.getDiagnosticoSiniestros().add(ds);
//        insertAlGrid(detalleSin);
        return res;
    }

//    private void insertAlGrid(BeanVO bean) {
//        Session s = null;
//        try {
//            s = HibernateUtil.getSessionFactory().openSession();
//            s.beginTransaction();
//            s.update(bean);
//            s.getTransaction().commit();
//        } catch (Exception ex) {
//        } finally {
//            s.close();
//        }
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TratamientoLookupController t = new TratamientoLookupController(((DiagnosticoSiniestro) getBeanVO()).getDiagnostico());
        t.addLookup2ParentLink("tratamiento");
        t.openLookupFrame(vista, new TratamientoLookupParent());
        vista.reloadGridsData();
    }

    private class TratamientoLookupParent implements LookupParent {

        @Override
        public Object getLookupCodeParentValue() {
            return "";
        }

        @Override
        public ValueObject getValueObject() {
            return null;
        }

        @Override
        public void setValue(String string, Object o) {

            Tratamiento t = (Tratamiento) o;
            System.out.println("--------");
            System.out.println(t);
            if (t != null) {
                System.out.println("antes");
                System.out.println(((DiagnosticoSiniestro) getBeanVO()).getTratamientos().size());
                ((DiagnosticoSiniestro) getBeanVO()).getTratamientos().add(t);
                System.out.println("add rido");
                System.out.println(((DiagnosticoSiniestro) getBeanVO()).getTratamientos().size());
            }
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                s.beginTransaction();
                s.update(getBeanVO());
                System.out.println("update");
                System.out.println(((DiagnosticoSiniestro) getBeanVO()).getTratamientos().size());
                s.getTransaction().commit();
            } catch (Exception ex) {
            } finally {
                s.close();
            }
        }
    }
}
