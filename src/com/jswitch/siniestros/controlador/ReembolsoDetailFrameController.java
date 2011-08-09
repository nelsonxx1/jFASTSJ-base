/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.siniestros.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.Reembolso;
import com.jswitch.siniestros.vista.detalle.ReembolsoDetailFrame;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author orlandobcrra
 */
public class ReembolsoDetailFrameController extends DefaultDetailFrameController {

    public ReembolsoDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }
    private Siniestro siniestro;

    public ReembolsoDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Siniestro siniestro) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        this.siniestro = siniestro;
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Reembolso reem = (Reembolso) newPersistentObject;
        reem.setPersonaPago(siniestro.getCertificado().getTitular().getPersona());

        reem.setSiniestro(siniestro);
        Session s = null;
        try {
            vista.saveGridsData();
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("FROM " + EtapaSiniestro.class.getName() + " C"
                    + " WHERE C.nombre='CARTA COMPROMISO'");
            EtapaSiniestro et = (EtapaSiniestro) q.uniqueResult();
            reem.setEtapaSiniestro(et);
            q = s.createQuery("FROM " + TipoPersona.class.getName() + " C"
                    + " WHERE C.idPropio='TIT'");
            TipoPersona tp = (TipoPersona) q.uniqueResult();
            reem.setTipoPersona(tp);

            //s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            if (newPersistentObject instanceof Auditable) {
                AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
                ((Auditable) newPersistentObject).setAuditoria(ab);
            }
            if (aplicarLogicaNegocio) {
                Response response = logicaNegocioConCambioEnVista(newPersistentObject, false);
                if (response.isError()) {
                    return response;
                }
                newPersistentObject = (ValueObject) ((VOResponse) response).getVo();
            }
            s.save(newPersistentObject);

            if (aplicarLogicaNegocio) {
                Response response = logicaNegocioDespuesSave(newPersistentObject);
                if (response.isError()) {
                    return response;
                }
                newPersistentObject = (ValueObject) ((VOResponse) response).getVo();
            }

            t.commit();

            if (linkForm != null && attributeName != null) {
                linkForm.getVOModel().setValue(attributeName, newPersistentObject);
                linkForm.pull(attributeName);
            }

            vista.setOwnerVO((BeanVO) newPersistentObject);
            beanVO = (BeanVO) newPersistentObject;
            if (gridControl != null) {
                gridControl.reloadData();
            }
            return new VOResponse(newPersistentObject);
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord", ex));
        } finally {
            s.close();
        }
    }
}
