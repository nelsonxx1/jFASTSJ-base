package com.jswitch.pagos.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.configuracion.modelo.dominio.Cobertura;
import com.jswitch.configuracion.modelo.maestra.ConfiguracionCobertura;
import com.jswitch.pagos.modelo.maestra.Factura;
import com.jswitch.pagos.modelo.transaccional.DesgloseCobertura;
import java.util.ArrayList;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;

/**
 *
 * @author Adrian
 */
public class DesgloseCoberturaGridInternalController extends DefaultGridInternalController {

    public DesgloseCoberturaGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        for (Object object : persistentObjects) {
            DesgloseCobertura dc = (DesgloseCobertura) object;
            ((DesgloseCobertura) object).setMontoNoAmparado(dc.getMontoFacturado() - dc.getMontoAmparado());
        }
        return super.updateRecords(rowNumbers, oldPersistentObjects, persistentObjects);
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        for (Object object : newValueObjects) {
            DesgloseCobertura dc = (DesgloseCobertura) object;
            ((DesgloseCobertura) object).setMontoNoAmparado(dc.getMontoFacturado() - dc.getMontoAmparado());
        }
        return super.insertRecords(rowNumbers, newValueObjects);
    }

    @Override
    public void afterDeleteGrid() {
        updateFactura();
    }

    @Override
    public void afterInsertGrid(GridControl grid) {
        updateFactura();
    }

    @Override
    public void afterEditGrid(GridControl grid) {
        updateFactura();
    }

    /**
     * actualiza los valores de la factura
     */
    private void updateFactura() {
        Factura factura = (Factura) beanVO;
        Double montoNoAmparado = 0d;
        Double montoSujeto = 0d;
        Double montoIva = 0d;
        for (DesgloseCobertura dc : factura.getDesgloseCobertura()) {
            if (dc.getAuditoria().getActivo()) {
                ConfiguracionCobertura c = getConfiCober(dc.getCobertura());
                double iva = c.getIva() ? 0
                        : (factura.getPorcentajeIva() / 100);
                montoNoAmparado += dc.getMontoNoAmparado() * (1 - iva);
                montoSujeto += dc.getMontoAmparado() * (1 - iva);
                montoIva += dc.getMontoFacturado() * iva;
            }
        }
        factura.setMontoNoAmparado(montoNoAmparado);
        factura.setMontoSujetoRetencion(montoSujeto);
        factura.setMontoIva(montoIva);
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.update(factura);
            s.getTransaction().commit();
        } catch (Exception e) {
            LoggerUtil.error(DesgloseCoberturaGridInternalController.class,
                    "updateFactura", e);
        } finally {
            s.close();
        }
    }

    private ConfiguracionCobertura getConfiCober(Cobertura cobertura) {
        Session s = null;
        ConfiguracionCobertura c = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            c = (ConfiguracionCobertura) s.createQuery("FROM " + ConfiguracionCobertura.class.getName() + " C "
                    + "WHERE C.cobertura.id = ?").setLong(0, cobertura.getId()).uniqueResult();

        } catch (Exception e) {
            LoggerUtil.error(DesgloseCoberturaGridInternalController.class,
                    "getConfiCober", e);
        } finally {
            s.close();
        }
        return c;
    }
}
