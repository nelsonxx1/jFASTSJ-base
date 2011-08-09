package com.jswitch.certificados.vista.borrar;

import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.certificados.modelo.maestra.Certificado;
import com.jswitch.polizas.modelo.maestra.Poliza;
import com.jswitch.certificados.vista.CertificadoDetailFrame;
import com.jswitch.asegurados.vista.RifAseguradoDialog;
import com.jswitch.certificados.vista.borrar.BuscarCertificadoDetailFrame;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.client.InsertButton;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Adrian
 */
public class BuscarCertificadoDetailFrameController extends DefaultDetailFrameController {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((InsertButton) e.getSource()).getButtonId().equals("asegurado")) {
            new RifAseguradoDialog((CertificadoDetailFrame) vista);
        }
    }
    Poliza padre;

    public BuscarCertificadoDetailFrameController(
            String detailFramePath, GridControl gridControl, BeanVO beanVO, BeanVO parentVO, boolean aplicarLogicaNegocio) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        if (parentVO != null) {
            padre = (Poliza) parentVO;
        }
    }

    public BuscarCertificadoDetailFrameController(
            String detailFramePath, GridControl gridControl, BeanVO beanVO, boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        if (beanVO == null) {
            ((BuscarCertificadoDetailFrame) vista).setVisiblePaneles(false);
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        if (((Certificado) beanVO).getId() != null) {
            Session s = HibernateUtil.getSessionFactory().openSession();
            Certificado p =
                    (Certificado) s.get(Certificado.class, ((Certificado) beanVO).getId());
            Hibernate.initialize(p.getAsegurados());
            s.close();
            beanVO = p;
        }
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Session s = null;
        BigDecimal cedula = (BigDecimal) ((BuscarCertificadoDetailFrame) vista).getNumericControl1().getValue();
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("FROM " + Certificado.class.getName()
                    + " C WHERE C.titular.persona.rif.numeroCedula=?").setInteger(0, cedula.intValue());
            ArrayList<Certificado> l = new ArrayList<Certificado>(q.list());
            if (!l.isEmpty()) {
                beanVO = l.get(0);
                Hibernate.initialize(((Certificado) beanVO).getAsegurados());
                ((BuscarCertificadoDetailFrame) vista).setVisiblePaneles(true);
                return new VOResponse(beanVO);
            }
        } finally {
            s.close();
        }
        return new ErrorResponse("Titular ya posee su Certificado");

    }
}
