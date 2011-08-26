package com.jswitch.pagos.modelo.maestra;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.pagos.modelo.dominio.ConceptoSENIAT;
import com.jswitch.pagos.modelo.transaccional.DesgloseCobertura;
import com.jswitch.pagos.modelo.transaccional.DesgloseSumaAsegurada;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 *
 * @author Personal
 */
@Entity
public class Pago extends BeanVO implements Serializable, Auditable {

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
    @BusinessKey
    private DetalleSiniestro detalleSiniestro;
    /**
     *
     */
    @Column
    @BusinessKey
    private String numeroFactura;
    /**
     *
     */
    @Column
    @BusinessKey
    private String numeroControl;
    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaFactura;
    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaRecepcion;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private ConceptoSENIAT tipoConceptoSeniat;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double sustraendo;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double posentajeReteniconIsrl;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double montoReteniconIsrl;
    /**
     * UT Unidad Tributaria
     */
    @Column
    @BusinessKey
    private Double valorUT;
    /**
     * TM Timbre Municipal
     * //TODO tabla de configuracion dependiendo de cuantas ut pasa y q tasa aplica
     */
    @Column
    @BusinessKey
    private Double porcentajeTM;
    /**
     * TM Timbre Municipal
     * 
     */
    @Column
    @BusinessKey
    private Double montoTM;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double procentajeIva;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double montoIva;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double porcenajeRetencionIva;
    /**
     * base imponible
     */
    @Column
    @BusinessKey
    private Double montoRetencionIva;
    /**
     * base imponible
     */
    @Column
    @BusinessKey
    private Double montoDescuentoProntoPago;
    /**
     * base imponible
     */
    @Column
    @BusinessKey
    private Double montoDescuentoDesducible;
    /**
     * base imponible
     */
    @Column
    @BusinessKey
    private Double montoSujetoRetencion;
    /**
     * //esto es a lo que se le retenie impuesto
     */
    @Column
    @BusinessKey
    private Double gastosClinicos;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double honorariosMedicos;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double montoNoAmparado;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double totalFacturado;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double totalLiquidado;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double totalRetenido;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double totalACancelar;
    /**
     *
     */
    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private EstatusPago estatusPago;
    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaSeleccionado;
    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaPagado;
    /**
     * Coleccion de etapas de siniestro y las fechas de los cambios
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pago")
    @BusinessKey(exclude = Method.ALL)
    private Set<DesgloseSumaAsegurada> desgloseSumaAsegurada = new HashSet<DesgloseSumaAsegurada>();
    /**
     * Coleccion de etapas de siniestro y las fechas de los cambios
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<DesgloseCobertura> desgloseCobertura = new HashSet<DesgloseCobertura>();
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

    public Pago() {
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

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getNumeroControl() {
        return numeroControl;
    }

    public void setNumeroControl(String numeroControl) {
        this.numeroControl = numeroControl;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Set<DesgloseCobertura> getDesgloseCobertura() {
        return desgloseCobertura;
    }

    public void setDesgloseCobertura(Set<DesgloseCobertura> desgloseCobertura) {
        this.desgloseCobertura = desgloseCobertura;
    }

    public Set<DesgloseSumaAsegurada> getDesgloseSumaAsegurada() {
        return desgloseSumaAsegurada;
    }

    public void setDesgloseSumaAsegurada(Set<DesgloseSumaAsegurada> desgloseSumaAsegurada) {
        this.desgloseSumaAsegurada = desgloseSumaAsegurada;
    }

    public EstatusPago getEstatusPago() {
        return estatusPago;
    }

    public void setEstatusPago(EstatusPago estatusPago) {
        this.estatusPago = estatusPago;
    }

    public Date getFechaPagado() {
        return fechaPagado;
    }

    public void setFechaPagado(Date fechaPagado) {
        this.fechaPagado = fechaPagado;
    }

    public Date getFechaSeleccionado() {
        return fechaSeleccionado;
    }

    public void setFechaSeleccionado(Date fechaSeleccionado) {
        this.fechaSeleccionado = fechaSeleccionado;
    }

    public Double getGastosClinicos() {
        return gastosClinicos;
    }

    public void setGastosClinicos(Double gastosClinicos) {
        this.gastosClinicos = gastosClinicos;
    }

    public Double getHonorariosMedicos() {
        return honorariosMedicos;
    }

    public void setHonorariosMedicos(Double honorariosMedicos) {
        this.honorariosMedicos = honorariosMedicos;
    }

    public Double getMontoDescuentoDesducible() {
        return montoDescuentoDesducible;
    }

    public void setMontoDescuentoDesducible(Double montoDescuentoDesducible) {
        this.montoDescuentoDesducible = montoDescuentoDesducible;
    }

    public Double getMontoDescuentoProntoPago() {
        return montoDescuentoProntoPago;
    }

    public void setMontoDescuentoProntoPago(Double montoDescuentoProntoPago) {
        this.montoDescuentoProntoPago = montoDescuentoProntoPago;
    }

    public Double getMontoIva() {
        return montoIva;
    }

    public void setMontoIva(Double montoIva) {
        this.montoIva = montoIva;
    }

    public Double getMontoNoAmparado() {
        return montoNoAmparado;
    }

    public void setMontoNoAmparado(Double montoNoAmparado) {
        this.montoNoAmparado = montoNoAmparado;
    }

    public Double getMontoRetencionIva() {
        return montoRetencionIva;
    }

    public void setMontoRetencionIva(Double montoRetencionIva) {
        this.montoRetencionIva = montoRetencionIva;
    }

    public Double getMontoReteniconIsrl() {
        return montoReteniconIsrl;
    }

    public void setMontoReteniconIsrl(Double montoReteniconIsrl) {
        this.montoReteniconIsrl = montoReteniconIsrl;
    }

    public Double getMontoSujetoRetencion() {
        return montoSujetoRetencion;
    }

    public void setMontoSujetoRetencion(Double montoSujetoRetencion) {
        this.montoSujetoRetencion = montoSujetoRetencion;
    }

    public Double getMontoTM() {
        return montoTM;
    }

    public void setMontoTM(Double montoTM) {
        this.montoTM = montoTM;
    }

    public Double getPorcenajeRetencionIva() {
        return porcenajeRetencionIva;
    }

    public void setPorcenajeRetencionIva(Double porcenajeRetencionIva) {
        this.porcenajeRetencionIva = porcenajeRetencionIva;
    }

    public Double getPorcentajeTM() {
        return porcentajeTM;
    }

    public void setPorcentajeTM(Double porcentajeTM) {
        this.porcentajeTM = porcentajeTM;
    }

    public Double getPosentajeReteniconIsrl() {
        return posentajeReteniconIsrl;
    }

    public void setPosentajeReteniconIsrl(Double posentajeReteniconIsrl) {
        this.posentajeReteniconIsrl = posentajeReteniconIsrl;
    }

    public Double getProcentajeIva() {
        return procentajeIva;
    }

    public void setProcentajeIva(Double procentajeIva) {
        this.procentajeIva = procentajeIva;
    }

    public Double getSustraendo() {
        return sustraendo;
    }

    public void setSustraendo(Double sustraendo) {
        this.sustraendo = sustraendo;
    }

    public ConceptoSENIAT getTipoConceptoSeniat() {
        return tipoConceptoSeniat;
    }

    public void setTipoConceptoSeniat(ConceptoSENIAT tipoConceptoSeniat) {
        this.tipoConceptoSeniat = tipoConceptoSeniat;
    }

    public Double getTotalACancelar() {
        return totalACancelar;
    }

    public void setTotalACancelar(Double totalACancelar) {
        this.totalACancelar = totalACancelar;
    }

    public Double getTotalFacturado() {
        return totalFacturado;
    }

    public void setTotalFacturado(Double totalFacturado) {
        this.totalFacturado = totalFacturado;
    }

    public Double getTotalLiquidado() {
        return totalLiquidado;
    }

    public void setTotalLiquidado(Double totalLiquidado) {
        this.totalLiquidado = totalLiquidado;
    }

    public Double getTotalRetenido() {
        return totalRetenido;
    }

    public void setTotalRetenido(Double totalRetenido) {
        this.totalRetenido = totalRetenido;
    }

    public Double getValorUT() {
        return valorUT;
    }

    public void setValorUT(Double valorUT) {
        this.valorUT = valorUT;
    }

    public DetalleSiniestro getDetalleSiniestro() {
        return detalleSiniestro;
    }

    public void setDetalleSiniestro(DetalleSiniestro detalleSiniestro) {
        this.detalleSiniestro = detalleSiniestro;
    }
}
