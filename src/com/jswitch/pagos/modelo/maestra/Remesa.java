package com.jswitch.pagos.modelo.maestra;

import com.jswitch.base.modelo.entidades.Documento;
import com.jswitch.base.modelo.entidades.NotaTecnica;
import com.jswitch.base.modelo.entidades.Observacion;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.fas.modelo.Dominios;
import com.jswitch.fas.modelo.Dominios.DuracionCheque;
import com.jswitch.fas.modelo.Dominios.EstatusPago;
import com.jswitch.fas.modelo.Dominios.TipoDetalleSiniestro;
import com.jswitch.persona.modelo.dominio.TipoCuentaBancaria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
@Table(name = "PAGO_Remesa")
public class Remesa extends BeanVO implements Serializable, Auditable {

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
    private String numeroRemesa;
    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaPagoRemesa;
    /**
     *
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private Dominios.EstatusPago estatusPago;
    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @BusinessKey
    private Date fechaEnvio;
    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @BusinessKey
    private Date fechaPropuestaPago;
    /**
     *Numero de negociacion 
     * Valor asignado por el Banco.  Será informado por éste a la implantación del servicio.
     * Ej. 00002100
     */
    @Column
    @BusinessKey
    private Integer numNeg;
    /**
     *Numero de referencia de lote 
     * Valor asignado por el Banco.  Será informado por éste a la implantación del servicio.
     * Ej. 00002100
     */
    @Column
    @BusinessKey
    private Integer numRefLot;
    /**
     *
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private Dominios.TipoPago tipoPago;
    /**
     * duracion del cheque
     * puede ser 30 60 120 180
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private Dominios.DuracionCheque duracionCheque;
    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @BusinessKey
    private Date fechaValor;
    /**
     * cuenta de la empresa
     */
    @Column
    @BusinessKey
    private String numeroCuentaDebitar;
    /**
     * numero referencia Credito
     * Número asignado por la empresa que identifica el crédito. Es
     * utilizado para identificar la nota de crédito en el estado de
     * cuenta del beneficiario.
     * Ej. 00000015
     */
    private Integer numRefCre;
    /**
     * Número de Referencia del Debito
     * Número asignado por la empresa que identifica al débito. Es utilizado
     * para reconocer la Nota de Débito en el Estado de Cuenta del ordenante.
     * Ej. 00000015
     */
    private Integer numRefDeb;
    /**
     * tipo de cuenta
     * corriente 00
     * ahorro    10
     * default   00
     * 
     */
    @ManyToOne
    @BusinessKey
    private TipoCuentaBancaria tipoCuenta;
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
     *
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private Dominios.TipoDetalleSiniestro tipoDetalleSiniestro;
    /**
     * Coleccion de etapas de siniestro y las fechas de los cambios
     */
    @OneToMany(fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<OrdenDePago> ordenDePagos = new HashSet<OrdenDePago>(0);
    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private List<Observacion> observaciones = new ArrayList<Observacion>(0);
    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private List<NotaTecnica> notasTecnicas = new ArrayList<NotaTecnica>(0);
    /**
     * Coleccion de documentos anexos
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<Documento> documentos = new HashSet<Documento>(0);

    public Remesa() {
        estatusPago = EstatusPago.PENDIENTE;

    }

    /**
     *
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     *
     * @return the autoSearch
     */
    public Boolean getAutoSearch() {
        return autoSearch;
    }

    /**
     *
     * @return the estatusPago
     */
    public Dominios.EstatusPago getEstatusPago() {
        return estatusPago;
    }

    /**
     *
     * @return the fechaEnvio
     */
    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     *
     * @return the fechaPagoRemesa
     */
    public Date getFechaPagoRemesa() {
        return fechaPagoRemesa;
    }

    /**
     *
     * @return the fechaPropuestaPago
     */
    public Date getFechaPropuestaPago() {
        return fechaPropuestaPago;
    }

    /**
     *
     * @return the fechaValor
     */
    public Date getFechaValor() {
        return fechaValor;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Numero de negociacion
     * Valor asignado por el Banco.  Será informado por éste a la implantación del servicio.
     * Ej. 00002100
     * @return the numNeg
     */
    public Integer getNumNeg() {
        return numNeg;
    }

    /**
     * Numero de referencia de lote
     * Valor asignado por el Banco.  Será informado por éste a la implantación del servicio.
     * Ej. 00002100
     * @return the numRefLot
     */
    public Integer getNumRefLot() {
        return numRefLot;
    }

    /**
     * cuenta de la empresa
     * @return the numeroCuentaDebitar
     */
    public String getNumeroCuentaDebitar() {
        return numeroCuentaDebitar;
    }

    /**
     *
     * @return the numeroRemesa
     */
    public String getNumeroRemesa() {
        return numeroRemesa;
    }

    /**
     *
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * Coleccion de etapas de siniestro y las fechas de los cambios
     * @return the ordenDePagos
     */
    public Set<OrdenDePago> getOrdenDePagos() {
        return ordenDePagos;
    }

    /**
     *
     * @return the tipoPago
     */
    public Dominios.TipoPago getTipoPago() {
        return tipoPago;
    }

    /**
     *
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     *
     * @param autoSearch the autoSearch to set
     */
    public void setAutoSearch(Boolean autoSearch) {
        this.autoSearch = autoSearch;
    }

    /**
     *
     * @param estatusPago the estatusPago to set
     */
    public void setEstatusPago(Dominios.EstatusPago estatusPago) {
        this.estatusPago = estatusPago;
    }

    /**
     *
     * @param fechaEnvio the fechaEnvio to set
     */
    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    /**
     *
     * @param fechaPagoRemesa the fechaPagoRemesa to set
     */
    public void setFechaPagoRemesa(Date fechaPagoRemesa) {
        this.fechaPagoRemesa = fechaPagoRemesa;
    }

    /**
     *
     * @param fechaPropuestaPago the fechaPropuestaPago to set
     */
    public void setFechaPropuestaPago(Date fechaPropuestaPago) {
        this.fechaPropuestaPago = fechaPropuestaPago;
    }

    /**
     *
     * @param fechaValor the fechaValor to set
     */
    public void setFechaValor(Date fechaValor) {
        this.fechaValor = fechaValor;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Numero de negociacion
     * Valor asignado por el Banco.  Será informado por éste a la implantación del servicio.
     * Ej. 00002100
     * @param numNeg the numNeg to set
     */
    public void setNumNeg(Integer numNeg) {
        this.numNeg = numNeg;
    }

    /**
     * Numero de referencia de lote
     * Valor asignado por el Banco.  Será informado por éste a la implantación del servicio.
     * Ej. 00002100
     * @param numRefLot the numRefLot to set
     */
    public void setNumRefLot(Integer numRefLot) {
        this.numRefLot = numRefLot;
    }

    /**
     * cuenta de la empresa
     * @param numeroCuentaDebitar the numeroCuentaDebitar to set
     */
    public void setNumeroCuentaDebitar(String numeroCuentaDebitar) {
        this.numeroCuentaDebitar = numeroCuentaDebitar;
    }

    /**
     *
     * @param numeroRemesa the numeroRemesa to set
     */
    public void setNumeroRemesa(String numeroRemesa) {
        this.numeroRemesa = numeroRemesa;
    }

    /**
     *
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * Coleccion de etapas de siniestro y las fechas de los cambios
     * @param ordenDePagos the ordenDePagos to set
     */
    public void setOrdenDePagos(Set<OrdenDePago> ordenDePagos) {
        this.setOrdenDePagos(ordenDePagos);
    }

    /**
     *
     * @param tipoPago the tipoPago to set
     */
    public void setTipoPago(Dominios.TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    /**
     * 
     * @return duracionCheque
     */
    public DuracionCheque getDuracionCheque() {
        return duracionCheque;
    }

    /**
     * @param duracionCheque the duracionCheque to 
     */
    public void setDuracionCheque(DuracionCheque duracionCheque) {
        this.duracionCheque = duracionCheque;
    }

    /**
     * numero referencia Credito
     * Número asignado por la empresa que identifica el crédito. Es
     * utilizado para identificar la nota de crédito en el estado de
     * cuenta del beneficiario.
     * Ej. 00000015
     * @return the numRefCre
     */
    public Integer getNumRefCre() {
        return numRefCre;
    }

    /**
     * Número de Referencia del Debito
     * Número asignado por la empresa que identifica al débito. Es utilizado
     * para reconocer la Nota de Débito en el Estado de Cuenta del ordenante.
     * Ej. 00000015
     * @return the numRefDeb
     */
    public Integer getNumRefDeb() {
        return numRefDeb;
    }

    /**
     * numero referencia Credito
     * Número asignado por la empresa que identifica el crédito. Es
     * utilizado para identificar la nota de crédito en el estado de
     * cuenta del beneficiario.
     * Ej. 00000015
     * @param numRefCre the numRefCre to set
     */
    public void setNumRefCre(Integer numRefCre) {
        this.numRefCre = numRefCre;
    }

    /**
     * Número de Referencia del Debito
     * Número asignado por la empresa que identifica al débito. Es utilizado
     * para reconocer la Nota de Débito en el Estado de Cuenta del ordenante.
     * Ej. 00000015
     * @param numRefDeb the numRefDeb to set
     */
    public void setNumRefDeb(Integer numRefDeb) {
        this.numRefDeb = numRefDeb;
    }

    /**
     * tipo de cuenta
     * corriente 00
     * ahorro    10
     * default   00
     * @return tipoCuenta
     */
    public TipoCuentaBancaria getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * tipo de cuenta
     * corriente 00
     * ahorro    10
     * default   00
     * @param tipoCuenta the TipoCuenta to set
     */
    public void setTipoCuenta(TipoCuentaBancaria tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public List<NotaTecnica> getNotasTecnicas() {
        return notasTecnicas;
    }

    public void setNotasTecnicas(List<NotaTecnica> notasTecnicas) {
        this.notasTecnicas = notasTecnicas;
    }

    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    public TipoDetalleSiniestro getTipoDetalleSiniestro() {
        return tipoDetalleSiniestro;
    }

    public void setTipoDetalleSiniestro(TipoDetalleSiniestro tipoDetalleSiniestro) {
        this.tipoDetalleSiniestro = tipoDetalleSiniestro;
    }
}
