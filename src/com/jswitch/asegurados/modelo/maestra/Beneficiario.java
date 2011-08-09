package com.jswitch.asegurados.modelo.maestra;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.persona.modelo.maestra.PersonaNatural;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 *
 * @author Personal
 */
@Entity
public class Beneficiario extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double indemnizacion;
    /**
     */
    @Transient
    private transient Double porcentajeDisponible;
    /**
     *
     */
    @ManyToOne
    private PersonaNatural persona;
    /**
     */
    @Version
    @Column
    private Integer optLock;
    /**
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public Beneficiario() {
        indemnizacion = 0d;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public PersonaNatural getPersona() {
        return persona;
    }

    public void setPersona(PersonaNatural persona) {
        this.persona = persona;
    }

    public Double getIndemnizacion() {
        return indemnizacion;
    }

    public Double getPorcentajeDisponible() {
        return porcentajeDisponible;
    }

    public void setPorcentajeDisponible(Double porcentajeDisponible) {
        this.porcentajeDisponible = porcentajeDisponible;
    }

    public void setIndemnizacion(Double indemnizacion) {
        this.indemnizacion = indemnizacion;
    }
}
