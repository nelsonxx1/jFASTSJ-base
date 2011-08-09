package com.jswitch.siniestros.modelo.maestra.detalle;

import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

/**
 *
 * @author Adrian
 */
@Entity
public class Funerario extends DetalleSiniestro {

    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaDefuncion;

    public Funerario() {
    }

    public Date getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(Date fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }
}
