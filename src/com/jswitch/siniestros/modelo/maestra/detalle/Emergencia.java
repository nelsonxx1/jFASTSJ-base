package com.jswitch.siniestros.modelo.maestra.detalle;

import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

/**
 *
 * @author Adrian
 */
@Entity
public class Emergencia extends DetalleSiniestro {

    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaEntrada;
    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Future
    @BusinessKey
    private Date fechaSalida;
    /**
     *
     */
    @Column
    @BusinessKey
    private String clave;
    /**
     *
     */
    @Column
    @BusinessKey
    private String nombreContacto;
    /**
     *
     */
    @Column
    @BusinessKey
    private String telefonoContacto;

    public Emergencia() {
        fechaEntrada = new Date();
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }
}
