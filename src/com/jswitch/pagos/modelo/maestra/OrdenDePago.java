package com.jswitch.pagos.modelo.maestra;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.fas.modelo.Dominios;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Past;

/**
 * @author Personal
 */
@Entity
@Table(name = "PAGO_OrdenDePago")
public class OrdenDePago extends BeanVO implements Serializable, Auditable {

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
    private String numeroOrden;
    /**
     *
     */
    @Column
    private String referencia;
    /**
     *
     */
    @Column
    private String codigoSIGECOF;
    
    /**
     *
     */
    @ManyToOne()
    private Persona personaPago;
    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaPago;
    /**
     *
     */
    @Transient
    private transient Date fechaMinima;
    /**
     *
     */
    @Transient
    private transient Date FechaMaxima;
    /**
     *
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private Dominios.EstatusPago estatusPago;
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
    /**
     * 
     */
    @Transient
    private transient Boolean autoSearch;
    /**
     * Coleccion de etapas de siniestro y las fechas de los cambios
     */
    @OneToMany(fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<DetalleSiniestro> detalleSiniestros = new HashSet<DetalleSiniestro>(0);

    public OrdenDePago() {
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

    public Set<DetalleSiniestro> getDetalleSiniestros() {
        return detalleSiniestros;
    }

    public void setDetalleSiniestros(Set<DetalleSiniestro> detalleSiniestros) {
        this.detalleSiniestros = detalleSiniestros;
    }

    public EstatusPago getEstatusPago() {
        return estatusPago;
    }

    public void setEstatusPago(EstatusPago estatusPago) {
        this.estatusPago = estatusPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Persona getPersonaPago() {
        return personaPago;
    }

    public void setPersonaPago(Persona personaPago) {
        this.personaPago = personaPago;
    }

    public Boolean getAutoSearch() {
        return autoSearch;
    }

    public void setAutoSearch(Boolean autoSearch) {
        this.autoSearch = autoSearch;
    }

    public Date getFechaMaxima() {
        return FechaMaxima;
    }

    public void setFechaMaxima(Date FechaMaxima) {
        this.FechaMaxima = FechaMaxima;
    }

    public Date getFechaMinima() {
        return fechaMinima;
    }

    public void setFechaMinima(Date fechaMinima) {
        this.fechaMinima = fechaMinima;
    }

    public String getCodigoSIGECOF() {
        return codigoSIGECOF;
    }

    public void setCodigoSIGECOF(String codigoSIGECOF) {
        this.codigoSIGECOF = codigoSIGECOF;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
