package com.jswitch.siniestros.modelo.maestra.detalle;

import com.jswitch.base.modelo.Dominios;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.reporte.modelo.Reporte;
import com.jswitch.siniestros.modelo.maestra.DetalleSiniestro;
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
public class Reembolso extends DetalleSiniestro {

    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaOcurrencia;
    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaNotificacion;
    /**
     *
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaConstitucion;
    /**
     * 
     */
    @Transient
    protected static transient Set<Reporte> reportes = new HashSet<Reporte>(0);
    
    public Reembolso() {
        setPresupuestadoAjustado(0d);
        setPresupuestadoInicial(0d);
    }

    public Date getFechaConstitucion() {
        return fechaConstitucion;
    }

    public void setFechaConstitucion(Date fechaConstitucion) {
        this.fechaConstitucion = fechaConstitucion;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaOcurrencia() {
        return fechaOcurrencia;
    }

    public void setFechaOcurrencia(Date fechaOcurrencia) {
        this.fechaOcurrencia = fechaOcurrencia;
    }

    public Set<Reporte> getReportes() {
        if (reportes.isEmpty()) {
            reportes.add(new Reporte(Dominios.CategoriaReporte.PERSONAS, 0, "Reembolso", "Reembolso", "Reembolso", null, "Carta 8½ x 11 Vertical"));
        }
        return reportes;
    }
}
