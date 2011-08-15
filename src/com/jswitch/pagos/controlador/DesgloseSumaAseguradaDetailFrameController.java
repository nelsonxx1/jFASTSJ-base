package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.configuracion.controlador.TratamientoLookupController;
import com.jswitch.configuracion.modelo.dominio.patologias.Tratamiento;
import com.jswitch.pagos.modelo.maestra.Pago;
import com.jswitch.pagos.modelo.transaccional.DesgloseSumaAsegurada;
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
public class DesgloseSumaAseguradaDetailFrameController extends DefaultDetailFrameController {

    public DesgloseSumaAseguradaDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }
    private Pago p;

    public DesgloseSumaAseguradaDetailFrameController(String name, GridControl gridControl4, boolean b, Pago pago) {
        super(name, gridControl4, null, b);
        p = pago;
    }

    @Override
    public Response loadData(Class valueObjectClass) {

        System.out.println("load data DSA");
        System.out.println(beanVO);
        Session s = HibernateUtil.getSessionFactory().openSession();
        DesgloseSumaAsegurada sin = (DesgloseSumaAsegurada) s.get(DesgloseSumaAsegurada.class, ((DesgloseSumaAsegurada) beanVO).getId());
        Hibernate.initialize(sin.getTratamientos());
        System.out.println(sin);
        System.out.println("inicializados tratamientos");
        s.close();
        beanVO = sin;
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Response res = super.insertRecord(newPersistentObject);
        p.getDesgloseSumaAsegurada().add((DesgloseSumaAsegurada) newPersistentObject);
        insertAlGrid(p);
        return res;
    }

    private void insertAlGrid(Pago pago) {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.update(pago);
            s.getTransaction().commit();
        } catch (Exception ex) {
        } finally {
            s.close();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TratamientoLookupController t = new TratamientoLookupController(((DesgloseSumaAsegurada) getBeanVO()).getDiagnostico());
        t.addLookup2ParentLink("tratamiento");
        t.openLookupFrame(vista, new TratamientoLookupParent());
        vista.reloadGridsData();
    }

    private class TratamientoLookupParent implements LookupParent {

        @Override
        public Object getLookupCodeParentValue() {
            System.out.println("getcodeparentvalue");
            return "";
        }

        @Override
        public ValueObject getValueObject() {
            System.out.println("getvalue");
            return null;
        }

        @Override
        public void setValue(String string, Object o) {
            System.out.println(" entro");

            Tratamiento t = (Tratamiento) o;
            System.out.println(t);
            if (t != null) {

                ((DesgloseSumaAsegurada) getBeanVO()).getTratamientos().add(t);
            }
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                s.beginTransaction();
                s.update(getBeanVO());
                s.getTransaction().commit();
            } catch (Exception ex) {
            } finally {
                s.close();
            }
        }
    }
}
