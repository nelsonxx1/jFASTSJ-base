/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.pagos.modelo.transaccional.lote;

/**
 *
 * @author Adrian
 */
public class Total {
    
    /**
     * Identificador del Registro
     * Valor fijo: TOTAL
     */
    private String recordID;
    /**
     * Cantidad de Instrucciones de Débitos
     */
    private String debitCount;
    /**
     * Cantidad de Instrucciones de Créditos
     */
    private String creditCount;
    /**
     * Total monto lote
     */
    private Double totalMontoLote;

    public Total() {
        recordID="TOTAL   ";
        
    }

    /**
     * Cantidad de Instrucciones de Créditos
     * @return the creditCount
     */
    public String getCreditCount() {
        return creditCount;
    }

    /**
     * Cantidad de Instrucciones de Débitos
     * @return the debitCount
     */
    public String getDebitCount() {
        return debitCount;
    }

    /**
     * Identificador del Registro
     * Valor fijo: TOTAL
     * @return the recordID
     */
    public String getRecordID() {
        return recordID;
    }

    /**
     * Total monto lote
     * @return the totalMontoLote
     */
    public Double getTotalMontoLote() {
        return totalMontoLote;
    }

    /**
     * Cantidad de Instrucciones de Créditos
     * @param creditCount the creditCount to set
     */
    public void setCreditCount(String creditCount) {
        this.creditCount = creditCount;
    }

    /**
     * Cantidad de Instrucciones de Débitos
     * @param debitCount the debitCount to set
     */
    public void setDebitCount(String debitCount) {
        this.debitCount = debitCount;
    }

    /**
     * Identificador del Registro
     * Valor fijo: TOTAL
     * @param recordID the recordID to set
     */
    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    /**
     * Total monto lote
     * @param totalMontoLote the totalMontoLote to set
     */
    public void setTotalMontoLote(Double totalMontoLote) {
        this.totalMontoLote = totalMontoLote;
    }
}