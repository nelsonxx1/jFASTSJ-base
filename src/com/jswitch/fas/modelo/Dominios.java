package com.jswitch.fas.modelo;

import java.util.Hashtable;
import org.openswing.swing.domains.java.Domain;

/**
 *
 * @author Orlando Becerra
 */
public class Dominios {

    public static Hashtable getDominios() {
        Hashtable domains = new Hashtable();
        domains.put(
                Dominios.CategoriaReporte().getDomainId(),
                Dominios.CategoriaReporte());
        domains.put(
                Dominios.TipoBusqueda().getDomainId(),
                Dominios.TipoBusqueda());
        domains.put(
                Dominios.CategoriaSiniestro().getDomainId(),
                Dominios.CategoriaSiniestro());
        domains.put(
                Dominios.EstatusPago().getDomainId(),
                Dominios.EstatusPago());
        domains.put(
                Dominios.TipoTramiteSiniestro().getDomainId(),
                Dominios.TipoTramiteSiniestro());

        domains.put(
                Dominios.TratamientoEfectuado().getDomainId(),
                Dominios.TratamientoEfectuado());
        domains.put(
                Dominios.TipoEnfermedad().getDomainId(),
                Dominios.TipoEnfermedad());
        return domains;
    }

    public static enum TipoBusqueda {

        TITULARES, ASEGURADOS, GRUPO_FAMILIAR
    }

    public static Domain TipoBusqueda() {
        Domain dominio = new Domain("TipoBusqueda");
        TipoBusqueda o[] = TipoBusqueda.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }

    public static enum CategoriaReporte {

        PERSONAS, POLIZAS, RECIBOS, ASEGURADOS, SINIESTROS, PAGOS, AUDITORIAS
    }

    public static Domain CategoriaReporte() {
        Domain dominio = new Domain("CategoriaReporte");
        CategoriaReporte o[] = CategoriaReporte.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }

    public static enum CategoriaSiniestro {

        EMERGENCIA, CARTA_AVAL, REEMBOLSO, APS
    }

    public static Domain CategoriaSiniestro() {
        Domain dominio = new Domain("CategoriaSiniestro");
        CategoriaSiniestro o[] = CategoriaSiniestro.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }

    public static enum EstatusPago {

        PENDIENTE, SELECCIONADO, PAGADO, ANULADO
    }

    public static Domain EstatusPago() {
        Domain dominio = new Domain("EstatusPago");
        EstatusPago o[] = EstatusPago.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }

    public static enum TipoTramiteSiniestro {

        MEDICO, QUIRURGICO
    }

    public static Domain TipoTramiteSiniestro() {
        Domain dominio = new Domain("TipoTramiteSiniestro");
        TipoTramiteSiniestro o[] = TipoTramiteSiniestro.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }

    public static enum TratamientoEfectuado {

        MEDICO, QUIRURGICO
    }

    public static Domain TratamientoEfectuado() {
        Domain dominio = new Domain("TratamientoEfectuado");
        TratamientoEfectuado o[] = TratamientoEfectuado.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }

    public static enum TipoEnfermedad {

        DESCONOCIDO, AGUDA, PREEXISTENTE, AGUDA_PREEXISTENTE
    }

    public static Domain TipoEnfermedad() {
        Domain dominio = new Domain("TipoEnfermedad");
        TipoEnfermedad o[] = TipoEnfermedad.values();
        for (int i = 0; i
                < o.length; i++) {
            dominio.addDomainPair(o[i], o[i].toString());
        }
        return dominio;
    }
}
