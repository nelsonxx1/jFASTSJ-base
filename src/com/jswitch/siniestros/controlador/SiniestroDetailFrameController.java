package com.jswitch.siniestros.controlador;

import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.siniestros.modelo.maestra.Siniestro;
import com.jswitch.siniestros.vista.SiniestroDetailFrame;
import com.jswitch.siniestros.vista.detalle.APSDetailFrame;
import com.jswitch.siniestros.vista.detalle.AyudaSocialDetailFrame;
import com.jswitch.siniestros.vista.detalle.CartaAvalDetailFrame;
import com.jswitch.siniestros.vista.detalle.DetalleSiniestroChousser;
import com.jswitch.siniestros.vista.detalle.EmergenciaDetailFrame;
import com.jswitch.siniestros.vista.detalle.FunerarioDetailFrame;
import com.jswitch.siniestros.vista.detalle.ReembolsoDetailFrame;
import com.jswitch.siniestros.vista.detalle.VidaDetailFrame;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author Luis Adrian
 */
public class SiniestroDetailFrameController extends DefaultDetailFrameController {

    public SiniestroDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    public SiniestroDetailFrame getVista() {

        return (SiniestroDetailFrame) vista;
    }

    public SiniestroDetailFrameController(String detailFramePath, GridControl gridControl, Boolean aplicarLogicaNegocio, Asegurado asegurado) {

        super(detailFramePath,
                gridControl,
                null, aplicarLogicaNegocio);
        vista.getMainPanel().getVOModel().setValue("asegurado", asegurado);
        vista.getMainPanel().pull("asegurado");

        vista.getMainPanel().getVOModel().setValue("certificado", asegurado.getCertificado());
        vista.getMainPanel().pull("certificado");
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Siniestro s = (Siniestro) newPersistentObject;
        s.setCertificado(s.getAsegurado().getCertificado());
        return super.insertRecord(s);
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        return new VOResponse(persistentObject);
    }

    @Override
    public Response logicaNegocioDespuesSave(ValueObject persistentObject, Session s) {
        Long seq = null;
        try {
            seq= ((BigInteger) s.createSQLQuery("SELECT nextval('seq_siniestro');").uniqueResult()).longValue();
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "logicaNegocioDespuesSave", ex));
        }
        Calendar c = Calendar.getInstance();
        DecimalFormat nf = new DecimalFormat("00000");
        SimpleDateFormat df = new SimpleDateFormat("yyMM-");
        Siniestro siniestro = (Siniestro) persistentObject;
        
        siniestro.setNumero(df.format(c.getTime()) + nf.format(seq));
        siniestro.setAyo(c.get(Calendar.YEAR));
        siniestro.setMes(c.get(Calendar.MONTH + 1));
        siniestro.setSeq(seq);
        return new VOResponse(siniestro);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (beanVO != null) {
            switch (DetalleSiniestroChousser.showDialog()) {
                case 0:
                    new APSDetailFrameController(APSDetailFrame.class.getName(), gridControl, null, false, (Siniestro) beanVO);
                    break;
                case 1:
                    new AyudaSocialDetailFrameController(AyudaSocialDetailFrame.class.getName(), gridControl, null, false, (Siniestro) beanVO);
                    break;
                case 2:
                    new CartaAvalDetailFrameController(CartaAvalDetailFrame.class.getName(), gridControl, null, false, (Siniestro) beanVO);
                    break;
                case 3:
                    new EmergenciaDetailFrameController(EmergenciaDetailFrame.class.getName(), gridControl, null, false, (Siniestro) beanVO);
                    break;
                case 4:
                    new FunerarioDetailFrameController(FunerarioDetailFrame.class.getName(), gridControl, null, false, (Siniestro) beanVO);
                    break;
                case 5:
                    new ReembolsoDetailFrameController(ReembolsoDetailFrame.class.getName(), gridControl, null, false, (Siniestro) beanVO);
                    break;
                case 6:
                    new VidaDetailFrameController(VidaDetailFrame.class.getName(), gridControl, null, false, (Siniestro) beanVO);
                    break;
            }
        }
    }
}
