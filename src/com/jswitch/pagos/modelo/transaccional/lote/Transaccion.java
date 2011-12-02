package com.jswitch.pagos.modelo.transaccional.lote;

import com.jswitch.base.controlador.General;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.pagos.modelo.maestra.OrdenDePago;
import com.jswitch.pagos.modelo.maestra.Remesa;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.persona.modelo.transac.CuentaBancariaPersona;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;

/**
 *
 * @author Adrian
 */
public class Transaccion {

    /**
     * conjunto de ordenes de pago
     */
    private Remesa remesa;
    /**
     * cabecera del archivo 
     */
    private Header header;
    /**
     * total de transaccion
     */
    private Total total;
    /**
     * lista de movimientos por transaccion
     */
    private List<Body> list = new ArrayList<Body>(0);
    /**
     * Lista con las ordenes de pago q no se pudieron reportar por error
     */
    private List<OrdenDePago> error = new ArrayList<OrdenDePago>(0);

    public Transaccion(Remesa remesa) {
        this.remesa = remesa;
        header = new Header();
        total = new Total();
        initTransaccion();

    }

    public void printReport(OutputStream stream) {
        PrintStream out = new PrintStream(stream);
        out.format("%8s", header.getRecordID());

    }

    public static void main(String[] args) {
//        ("%4$2s %3$2s %2$2s %1$2s", "a", "b", "c", "d")
//        System.out.format("%4$05d %3$02d %2$2s %1$2s", "a", "b", 0, 1);
        StringBuffer buf = new StringBuffer("V17931");
        if (buf.length() < 10) {
            char[] c = new char[10 - buf.length()];
            Arrays.fill(c, '0');
            buf.insert(1, c);
        }
        System.out.println("asjh-0".substring(0, "asjh-0".lastIndexOf("-")));
    }

    private void initTransaccion() {

        Double totalMontoLote = 0d;
        //<editor-fold defaultstate="collapsed" desc="Headers">
        header.setFechaEnvio(remesa.getFechaEnvio());
        header.setFechaPagoPropuesta(remesa.getFechaPropuestaPago());
        header.setNumNeg(remesa.getNumNeg());
        header.setNumRefLot(remesa.getNumRefLot());
        header.setRecordID("HEADER  ");
        header.setRif(General.empresa.getRif2().replaceAll("-", ""));
        //</editor-fold>

        for (OrdenDePago ordenDePago : remesa.getOrdenDePagos()) {
            //<editor-fold defaultstate="collapsed" desc="download">
            Session s = null;
            Persona personaPago = null;
            CuentaBancariaPersona bancariaPersona = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                personaPago = (Persona) s.get(Persona.class,
                        ordenDePago.getPersonaPago().getId());
                Hibernate.initialize(personaPago.getCuentasBancarias());
                for (CuentaBancariaPersona cuentaBancariaPersona : personaPago.getCuentasBancarias()) {
                    if (cuentaBancariaPersona.getDomicilio()) {
                        bancariaPersona = cuentaBancariaPersona;
                        break;
                    }
                }
            } catch (Exception ex) {
                bancariaPersona = null;
            } finally {
                s.close();
            }
            //</editor-fold>
            if (bancariaPersona != null) {
                //<editor-fold defaultstate="collapsed" desc="Body">
                Body body = new Body();
                //<editor-fold defaultstate="collapsed" desc="Credito">
                body.getCredito().setBanco(bancariaPersona.getBanco().getNombreCorto());
                body.getCredito().setNumCuent(bancariaPersona.getNumero());
                body.getCredito().setTipoCuenta(bancariaPersona.getTipoCuenta().getNumero());
                
                body.getCredito().setEmail(personaPago.getEmail());
                body.getCredito().setNombre(personaPago.getNombreCorto());
                body.getCredito().setRifBen(personaPago.getRif().getRif());

                switch (remesa.getTipoPago()) {
                    case ABONO_EN_CUENTA_BANCO_DE_VENEZUELA:
                        body.getCredito().setTipoPago("10");
                        break;
                    case CHEQUE_DE_GERENCIA:
                        body.getCredito().setTipoPago("20");
                        break;
                    case TRANSFERENCIA_SWIFT:
                        body.getCredito().setTipoPago("00");
                        break;
                }

                body.getCredito().setNumRefCre(remesa.getNumRefCre());
                body.getCredito().setMonto(ordenDePago.getMontoPagar());
                body.getCredito().setDuracionCheq(Integer.parseInt(
                        remesa.getDuracionCheque().toString().substring(1)));
                //</editor-fold>
                body.getDebito().setFechaValor(remesa.getFechaValor());
                body.getDebito().setMonto(ordenDePago.getMontoPagar());
                body.getDebito().setNombre(General.empresa.getNombre());
                body.getDebito().setNumCuent(remesa.getNumeroCuentaDebitar());
                body.getDebito().setNumRefDeb(remesa.getNumRefDeb());
                body.getDebito().setRif(header.getRif());
                body.getDebito().setTipoCuenta(remesa.getTipoCuenta().getNumero());
                totalMontoLote += ordenDePago.getMontoPagar();
                list.add(body);
                //</editor-fold>
            } else {
                error.add(ordenDePago);
            }
        }
        total.setTotalMontoLote(totalMontoLote);
        total.setCreditCount(list.size() + "");
        total.setDebitCount(list.size() + "");
    }
}