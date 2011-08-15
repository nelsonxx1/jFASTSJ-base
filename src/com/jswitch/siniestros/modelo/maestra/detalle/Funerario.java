package com.jswitch.siniestros.modelo.maestra.detalle;

import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.reporte.modelo.Reporte;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.util.Date;
import java.util.Set;
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

    public static Set<Reporte> getReportes() {
        if (reportes.isEmpty()) {
            //reportes.add(new Reporte(Dominios.CategoriaReporte.PERSONAS, 0, "APSprueba", "Reporte1--", "Reporte1", null, "Carta 8½ x 11 Vertical"));
        }
        return reportes;
    }
}
