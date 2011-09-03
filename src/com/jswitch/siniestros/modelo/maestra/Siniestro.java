package com.jswitch.siniestros.modelo.maestra;

import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.certificados.modelo.maestra.Certificado;
import com.jswitch.configuracion.modelo.dominio.Ramo;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author Adrian
 */
@Entity
@Table(name = "SINI_Siniestro")
public class Siniestro extends BeanVO implements Serializable, Auditable {

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
    private Long seq;
    /**
     *
     */
    @Column
    @BusinessKey
    private Integer ayo;
    /**
     *
     */
    @Column
    @BusinessKey
    private Integer mes;
    /**
     *
     */
    @Column
    private String numero;
    /**
     *
     */
    @ManyToOne()
    private Certificado certificado;
    /**
     *
     */
    @ManyToOne()
    private Asegurado asegurado;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double montoTotal;
    /**
     * Detalles
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "siniestro")
    @BusinessKey(exclude = Method.ALL)
    private Set<DetalleSiniestro> detalleSiniestro = new HashSet<DetalleSiniestro>(0);
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

    public Siniestro() {
    }

    @Override
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

    @Override
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    @Override
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public Asegurado getAsegurado() {
        return asegurado;
    }

    public void setAsegurado(Asegurado asegurado) {
        this.asegurado = asegurado;
    }

    public Certificado getCertificado() {
        return certificado;
    }

    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Set<DetalleSiniestro> getDetalleSiniestro() {
        return detalleSiniestro;
    }

    public void setDetalleSiniestro(Set<DetalleSiniestro> detalleSiniestro) {
        this.detalleSiniestro = detalleSiniestro;
    }

    public Integer getAyo() {
        return ayo;
    }

    public void setAyo(Integer ayo) {
        this.ayo = ayo;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}
