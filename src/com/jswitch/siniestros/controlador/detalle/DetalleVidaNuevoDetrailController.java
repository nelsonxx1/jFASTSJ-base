package com.jswitch.siniestros.controlador.detalle;

import com.jswitch.asegurados.modelo.maestra.Beneficiario;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.certificados.modelo.maestra.Certificado;
import com.jswitch.configuracion.modelo.dominio.patologias.Diagnostico;
import com.jswitch.configuracion.modelo.transaccional.SumaAsegurada;
import com.jswitch.fas.modelo.Dominios.TratamientoEfectuado;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import com.jswitch.siniestros.modelo.maestra.DiagnosticoSiniestro;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.modelo.maestra.detalle.Vida;
import com.jswitch.siniestros.modelo.utilitario.DetalleVida;
import com.jswitch.siniestros.vista.detalle.DetalleVidaNuevoDetailFrame;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author orlandobcrra
 */
public class DetalleVidaNuevoDetrailController extends DefaultDetailFrameController {

    private Siniestro siniestro;

    public DetalleVidaNuevoDetrailController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Siniestro siniestro, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        this.siniestro = siniestro;
        ((DetalleVidaNuevoDetailFrame) vista).setPlanToLookup(siniestro.getAsegurado().getPlan());
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Certificado sin = (Certificado) s.get(Certificado.class, (siniestro.getCertificado()).getId());
        Hibernate.initialize(sin.getBeneficiarios());
        s.close();
        List<Beneficiario> beneficiarios = new ArrayList<Beneficiario>(sin.getBeneficiarios());
        DetalleVida detalleVida = (DetalleVida) newPersistentObject;

        for (Beneficiario beneficiario : beneficiarios) {
            Vida vida = new Vida();
            vida.setPersonaPago(beneficiario.getPersona());
            vida.setFechaDefuncion(detalleVida.getFechaDefuncion());
            vida.setTipoSiniestro(detalleVida.getTipoSiniestro());
            vida.setSiniestro(siniestro);
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("FROM " + EtapaSiniestro.class.getName() + " C"
                    + " WHERE C.nombre='VIDA_INICIAL'");
            EtapaSiniestro et = (EtapaSiniestro) q.uniqueResult();
            q = s.createQuery("FROM " + TipoPersona.class.getName() + " C"
                    + " WHERE C.idPropio='BEN'");
            TipoPersona tp = (TipoPersona) q.uniqueResult();
            s.close();
            vida.setEtapaSiniestro(et);
            vida.setTipoPersona(tp);
            vida.setTratamientoEfectuado(TratamientoEfectuado.DESCONOCIDO);
            super.insertRecord(vida);
            DiagnosticoSiniestro ds =
                    new DiagnosticoSiniestro(vida,
                    detalleVida.getDiagnostico(),
                    getIndemValue(beneficiario, detalleVida.getDiagnostico()));
            vida.getDiagnosticoSiniestros().add(ds);
            super.insertRecord(ds);
        }
        vista.dispose();
        return new VOResponse(newPersistentObject);
    }

    private Double getIndemValue(Beneficiario beneficiario, Diagnostico diagnostico) {

        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            String sql = "FROM " + SumaAsegurada.class.getName()
                    + " S WHERE S.diagnostico.id= ?"
                    + " AND S.plan.id= ?";
            List<SumaAsegurada> l = s.createQuery(sql).
                    setLong(0, diagnostico.getId()).
                    setLong(1, siniestro.getAsegurado().
                    getPlan().getId()).
                    list();
            if (l != null && l.size() > 0) {
                return l.get(0).getSumaAsegurada() * beneficiario.getIndemnizacion();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            s.close();
        }
        return 0d;
    }
}
