/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.jswitch.certificados.modelo.utilitario;

import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.asegurados.modelo.maestra.Titular;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.certificados.modelo.maestra.Certificado;

/**
 *
 * @author adrian
 */
public class CertificadoNuevo extends BeanVO {

    private Certificado certificado;
    private Titular titular;
    private Asegurado asegurado;

    public CertificadoNuevo() {
    }

    public Asegurado getAsegurado() {
        return asegurado;
    }

    public void setAsegurado(Asegurado asegurado) {
        this.asegurado = asegurado;
    }

    public Certificado getCertificado() {
        return certificado;
    }

    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }
}
