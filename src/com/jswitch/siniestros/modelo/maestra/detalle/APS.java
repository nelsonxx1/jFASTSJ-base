package com.jswitch.siniestros.modelo.maestra.detalle;

import com.jswitch.base.modelo.Dominios;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.reporte.modelo.Reporte;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.util.Date;
import java.util.Set;
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
public class APS extends DetalleSiniestro {

    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaEmision;
    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Future
    @BusinessKey
    private Date fechaVencimiento;

    public APS() {
        this.fechaEmision = new Date();
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public static Set<Reporte> getReportes() {
        if (reportes.isEmpty()) {
            reportes.add(new Reporte(Dominios.CategoriaReporte.PERSONAS, 0, "APSprueba", "Reporte1--", "Reporte1", null, "Carta 8½ x 11 Vertical"));
        }
        return reportes;
    }
}
