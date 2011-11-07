package com.jswitch.certificados.modelo.maestra;

import com.jswitch.asegurados.modelo.maestra.Beneficiario;
import com.jswitch.asegurados.modelo.maestra.Titular;
import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;

/**
 *
 * @author Personal
 */
@Entity
@Table(name = "ASEG_Certificado")
public class Certificado extends BeanVO implements Serializable, Auditable {

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
    @ManyToOne()
    private Titular titular;
    /**
     * HCM
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "certificado")
    @BusinessKey(exclude = Method.ALL)
    private Set<Asegurado> asegurados = new HashSet<Asegurado>(0);
    /**
     * Vida
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<Beneficiario> beneficiarios = new HashSet<Beneficiario>(0);
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.TIME)
    @BusinessKey
    private Date fechaPrimaHCM;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.TIME)
    @BusinessKey
    private Date fechaPrimaVida;
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

    public Certificado() {
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

    public Set<Asegurado> getAsegurados() {
        return asegurados;
    }

    public void setAsegurados(Set<Asegurado> asegurados) {
        this.asegurados = asegurados;
    }

    public Set<Beneficiario> getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(Set<Beneficiario> beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public Date getFechaPrimaHCM() {
        return fechaPrimaHCM;
    }

    public void setFechaPrimaHCM(Date fechaPrimaHCM) {
        this.fechaPrimaHCM = fechaPrimaHCM;
    }

    public Date getFechaPrimaVida() {
        return fechaPrimaVida;
    }

    public void setFechaPrimaVida(Date fechaPrimaVida) {
        this.fechaPrimaVida = fechaPrimaVida;
    }
}
