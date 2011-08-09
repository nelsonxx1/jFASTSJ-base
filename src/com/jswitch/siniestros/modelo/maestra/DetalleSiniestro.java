package com.jswitch.siniestros.modelo.maestra;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.fas.modelo.Dominios.TipoEnfermedad;
import com.jswitch.fas.modelo.Dominios.TratamientoEfectuado;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.siniestros.modelo.dominio.TipoSiniestro;
import com.jswitch.pagos.modelo.transaccional.Pago;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.siniestros.modelo.dominio.EtapaSiniestro;
import java.io.Serializable;
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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 *
 * EMERGENCIA, CARTA_AVAL, REEMBOLSO, ATENCION_MEDICA_PRIMARIA, ATENCION_MEDICA_HOGAR, ODONTOLOGIA, FUNERARIO, VIDA
 *
 * @author Personal
 */
/**
 *
 * @author Adrian
 */
@Entity
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
     * Coleccion de etapas de siniestro y las fechas de los cambios de la persona
     */
    @ManyToOne
    private EtapaSiniestro etapaSiniestro;
    /**
     *
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<Pago> pagos = new HashSet<Pago>();
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

    public Set<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(Set<Pago> pagos) {
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
}
