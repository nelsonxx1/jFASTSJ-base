package com.jswitch.siniestros.modelo.maestra.detalle;

import com.jswitch.base.controlador.General;
import com.jswitch.base.modelo.Dominios;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.reporte.modelo.Reporte;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
    @Transient
    protected static transient Set<Reporte> reportes = new HashSet<Reporte>(0);

    public APS() {
        this.fechaEmision = new Date();
        if (General.parametros != null && General.parametros.get("aps.diasVencimiento") != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(this.fechaEmision);
            c.add(Calendar.DAY_OF_MONTH, General.parametros.get("aps.diasVencimiento").getValorInteger());
            setFechaVencimiento(c.getTime());
        }
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Set<Reporte> getReportes() {
        if (reportes.isEmpty()) {
            reportes.add(new Reporte(Dominios.CategoriaReporte.PERSONAS, 0,
                    "SINI_D_APS_001", "SINI_D_APS_001", "SINI_D_APS_001", null, "Carta 8½ x 11 Vertical",false));
        }
        return reportes;
    }
}
