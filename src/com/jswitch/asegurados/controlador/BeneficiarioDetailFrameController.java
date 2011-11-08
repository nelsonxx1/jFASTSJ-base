package com.jswitch.asegurados.controlador;

import com.jswitch.asegurados.modelo.maestra.Beneficiario;
import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.certificados.modelo.maestra.Certificado;
import java.util.Date;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Adrian
 */
public class BeneficiarioDetailFrameController extends DefaultDetailFrameController {

    private Certificado familia;

    public Form getMainPanel() {
        return vista.getMainPanel();
    }

    public BeneficiarioDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);

        Double indem = 0d;
        if (beanVO != null) {
            Beneficiario b = (Beneficiario) this.beanVO;
            if (b != null) {
//                b.setPorcentajeDisponible(getPorcentajeDisponible() - b.getIndemnizacion());
                indem = b.getIndemnizacion();
                this.familia = b.getCertificado();
            }

        }
        vista.getMainPanel().getVOModel().setValue("porcentajeDisponible", getPorcentajeDisponible() - indem);
        vista.getMainPanel().pull("porcentajeDisponible");
    }

    public BeneficiarioDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Certificado familia) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        if (this.familia == null) {
            this.familia = familia;
        }

    }

    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Beneficiario benef = (Beneficiario) s.get(Beneficiario.class, ((Beneficiario) beanVO).getId());
        Hibernate.initialize(benef.getNotasTecnicas());
        Hibernate.initialize(benef.getObservaciones());
        Hibernate.initialize(benef.getDocumentos());
        s.close();
        beanVO = benef;
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Beneficiario b = (Beneficiario) newPersistentObject;
        b.setPorcentajeDisponible(getPorcentajeDisponible() - b.getIndemnizacion());
        b.setCertificado(familia);
        Response res = super.insertRecord(newPersistentObject);

        if (res instanceof VOResponse && familia != null) {
            familia.getBeneficiarios().add(b);
            if (familia instanceof Auditable) {
                AuditoriaBasica ab = ((Auditable) familia).getAuditoria();
                ab.setFechaUpdate(new Date());
                ab.setUsuarioUpdate(General.usuario.getUserName());
            }

            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = s.beginTransaction();
                s.update(familia);
                tx.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                s.close();
            }
            gridControl.reloadData();
        }
        return res;
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        Beneficiario b = (Beneficiario) persistentObject;
        b.setPorcentajeDisponible(getPorcentajeDisponible() - b.getIndemnizacion());

        return super.updateRecord(oldPersistentObject, b);
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        // return super.logicaNegocio(persistentObject);
        Beneficiario b = (Beneficiario) persistentObject;
        String errorMsj = "";
        if (getPorcentajeDisponible() - b.getIndemnizacion() < 0) {
            errorMsj += "Valor Superior al 100%";
        }
        if (errorMsj.length() > 0) {
            return new ErrorResponse(errorMsj);
        } else {
            return new VOResponse(persistentObject);
        }
    }

    private Double getPorcentajeDisponible() {
        Double porcentajeDisponible = 100d;
        if (familia.getBeneficiarios() != null) {
            for (Beneficiario beneficiario : familia.getBeneficiarios()) {
                if (beneficiario != (Beneficiario) beanVO) {
                    porcentajeDisponible -= beneficiario.getIndemnizacion();
                }
            }
        }
        return porcentajeDisponible;
    }
}
