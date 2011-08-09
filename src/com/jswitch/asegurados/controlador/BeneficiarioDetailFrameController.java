
package com.jswitch.asegurados.controlador;

import com.jswitch.asegurados.modelo.maestra.Beneficiario;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.certificados.modelo.maestra.Certificado;
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

    }

    public BeneficiarioDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Certificado familia) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        this.familia = familia;
        Double indem = 0d;
        if (beanVO != null) {
            Beneficiario b = (Beneficiario) this.beanVO;
            if (b != null) {
//                b.setPorcentajeDisponible(getPorcentajeDisponible() - b.getIndemnizacion());
                indem = b.getIndemnizacion();
            }

        }
        vista.getMainPanel().getVOModel().setValue("porcentajeDisponible", getPorcentajeDisponible() - indem);
        vista.getMainPanel().pull("porcentajeDisponible");
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Beneficiario b = (Beneficiario) newPersistentObject;
        b.setPorcentajeDisponible(getPorcentajeDisponible() - b.getIndemnizacion());

        Response res = super.insertRecord(newPersistentObject);

        if (res instanceof VOResponse && familia != null) {
            familia.getBeneficiarios().add(b);
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

        return super.updateRecord(oldPersistentObject, persistentObject);
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
