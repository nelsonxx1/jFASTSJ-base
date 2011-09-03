package com.jswitch.siniestros.modelo.maestra;

import com.jswitch.pagos.modelo.maestra.Liquidacion;
import com.jswitch.base.modelo.entidades.Documento;
import com.jswitch.base.modelo.entidades.NotaTecnica;
import com.jswitch.base.modelo.entidades.Observacion;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.configuracion.modelo.dominio.Ramo;
import com.jswitch.fas.modelo.Dominios.TipoEnfermedad;
import com.jswitch.fas.modelo.Dominios.TratamientoEfectuado;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.siniestros.modelo.dominio.TipoSiniestro;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.reporte.modelo.Reporte;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 *
 * EMERGENCIA, CARTA_AVAL, REEMBOLSO, ATENCION_MEDICA_PRIMARIA, ATENCION_MEDICA_HOGAR, ODONTOLOGIA, FUNERARIO, VIDA
 */
/**
 *
 * @author Adrian
 * 
 */
@Entity
@Table(name = "SINI_DetalleSiniestro")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class DetalleSiniestro extends BeanVO implements Serializable, Auditable {

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
    private Integer numero;
    /**
     * para filtar la persona a la que se le va a pagar
     */
    @ManyToOne()
    private TipoPersona tipoPersona;
    /**
     *
     */
    @ManyToOne
    @BusinessKey
    private Persona personaPago;
    /**
     *
     */
    @ManyToOne
    private Siniestro siniestro;
    /**
     *
     */
    @Column
    private Double montoFacturado;
    /**
     *
     */
    @Column
    private Double montoLiquidado;
    /**
     *
     */
    @Column
    @Enumerated(EnumType.STRING)
    private TipoEnfermedad tipoEnfermedad;
    /**
     *
     */
    @Column
    @Enumerated(EnumType.STRING)
    private TratamientoEfectuado tratamientoEfectuado;
    /**
     *
     */
    @ManyToOne
    private TipoSiniestro tipoSiniestro;
    /**
     *
     */
    //TODO agregar a tipo persona NEGOCIADOR
    @ManyToOne()
    private Persona analistaNegociador;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double presupuestadoInicial;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double presupuestadoAjustado;
    /**
     * 
     */
    @ManyToOne
    @BusinessKey(exclude = Method.ALL)
    private Ramo ramo;
    /**
     * Coleccion de etapas de siniestro y las fechas de los cambios de la persona
     */
    @ManyToOne
    private EtapaSiniestro etapaSiniestro;
    /**
     * 
     */
    @Transient
    private transient String tipoDetalle;
    /**
     *
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detalleSiniestro")
    @BusinessKey(exclude = Method.ALL)
    private Set<Liquidacion> pagos = new HashSet<Liquidacion>();
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
    /**
     * Coleccion de documentos anexos
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detalleSiniestro")
    @BusinessKey(exclude = Method.ALL)
    private Set<DiagnosticoSiniestro> diagnosticoSiniestros = new HashSet<DiagnosticoSiniestro>(0);
    /**
     * 
     */
    @Transient
    protected static transient Set<Reporte> reportes = new HashSet<Reporte>(0);
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

    public DetalleSiniestro() {
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

    public EtapaSiniestro getEtapaSiniestro() {
        return etapaSiniestro;
    }

    public void setEtapaSiniestro(EtapaSiniestro etapaSiniestro) {
        this.etapaSiniestro = etapaSiniestro;
    }

    public Double getMontoFacturado() {
        return montoFacturado;
    }

    public void setMontoFacturado(Double montoFacturado) {
        this.montoFacturado = montoFacturado;
    }

    public Double getMontoLiquidado() {
        return montoLiquidado;
    }

    public void setMontoLiquidado(Double montoLiquidado) {
        this.montoLiquidado = montoLiquidado;
    }

    public Persona getPersonaPago() {
        return personaPago;
    }

    public void setPersonaPago(Persona personaPago) {
        this.personaPago = personaPago;
    }

    public Set<Liquidacion> getPagos() {
        return pagos;
    }

    public void setPagos(Set<Liquidacion> pagos) {
        this.pagos = pagos;
    }

    public Persona getAnalistaNegociador() {
        return analistaNegociador;
    }

    public void setAnalistaNegociador(Persona analistaNegociador) {
        this.analistaNegociador = analistaNegociador;
    }

    public Double getPresupuestadoAjustado() {
        return presupuestadoAjustado;
    }

    public void setPresupuestadoAjustado(Double presupuestadoAjustado) {
        this.presupuestadoAjustado = presupuestadoAjustado;
    }

    public Double getPresupuestadoInicial() {
        return presupuestadoInicial;
    }

    public void setPresupuestadoInicial(Double presupuestadoInicial) {
        this.presupuestadoInicial = presupuestadoInicial;
    }

    public TipoEnfermedad getTipoEnfermedad() {
        return tipoEnfermedad;
    }

    public void setTipoEnfermedad(TipoEnfermedad tipoEnfermedad) {
        this.tipoEnfermedad = tipoEnfermedad;
    }

    public TipoSiniestro getTipoSiniestro() {
        return tipoSiniestro;
    }

    public void setTipoSiniestro(TipoSiniestro tipoSiniestro) {
        this.tipoSiniestro = tipoSiniestro;
    }

    public TratamientoEfectuado getTratamientoEfectuado() {
        return tratamientoEfectuado;
    }

    public void setTratamientoEfectuado(TratamientoEfectuado tratamientoEfectuado) {
        this.tratamientoEfectuado = tratamientoEfectuado;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Siniestro getSiniestro() {
        return siniestro;
    }

    public void setSiniestro(Siniestro siniestro) {
        this.siniestro = siniestro;
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

    public Set<DiagnosticoSiniestro> getDiagnosticoSiniestros() {
        return diagnosticoSiniestros;
    }

    public void setDiagnosticoSiniestros(Set<DiagnosticoSiniestro> diagnosticoSiniestros) {
        this.diagnosticoSiniestros = diagnosticoSiniestros;
    }

    public Ramo getRamo() {
        return ramo;
    }

    public void setRamo(Ramo ramo) {
        this.ramo = ramo;
    }

    public String getTipoDetalle() {
        String s = this.getClass().getName();
        return s.substring(s.lastIndexOf(".") + 1);
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    
}
