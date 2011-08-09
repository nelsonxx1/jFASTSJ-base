package com.jswitch.asegurados.vista;

import com.jswitch.asegurados.modelo.maestra.Titular;
import com.jswitch.asegurados.modelo.dominio.Departamento;
import com.jswitch.asegurados.modelo.dominio.TipoContrato;
import com.jswitch.base.controlador.util.DefaultDocumentosAnexosGridController;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import com.jswitch.persona.controlador.PersonaLookupControllerPorNombre;
import com.jswitch.persona.controlador.PersonasDetailController;
import com.jswitch.persona.modelo.maestra.Rif;
import com.jswitch.persona.vista.RifDialog;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author bc
 */
public class TitularDetailFrame extends DefaultDetailFrame {

    private DefaultGridInternalController controllerObservaciones;
    private DefaultDocumentosAnexosGridController controllerDocumentos;

    public TitularDetailFrame() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        editButton1 = new org.openswing.swing.client.EditButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        form1 = new org.openswing.swing.form.client.Form();
        jPanel2 = new javax.swing.JPanel();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        numericControl1 = new org.openswing.swing.client.NumericControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        codLookupControl1 = new org.openswing.swing.client.CodLookupControl();
        textControl1 = new org.openswing.swing.client.TextControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        jPanel3 = new javax.swing.JPanel();
        dateControl1 = new org.openswing.swing.client.DateControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        codLookupControl3 = new org.openswing.swing.client.CodLookupControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        dateControl2 = new org.openswing.swing.client.DateControl();

        setTitle("Titular");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(630, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        form1.setVOClassName(Titular.class.getName());
        form1.setEditButton(editButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridLayout(1, 2));

        labelControl1.setLabel("id");

        numericControl1.setAttributeName("id");
        numericControl1.setEnabledOnEdit(false);
        numericControl1.setEnabledOnInsert(false);

        labelControl2.setLabel("titular.persona.nombre");

        codLookupControl1.setAttributeName("persona.nombreLargo");
        codLookupControl1.setControllerMethodName("getPersonaNueva");
        codLookupControl1.setMaxCharacters(255);
        codLookupControl1.setRequired(true);

        textControl1.setAttributeName("codigoEmpleado");
        textControl1.setRequired(true);

        labelControl3.setLabel("titular.codigoEmpleado");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codLookupControl1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(numericControl1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(textControl1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelControl1, labelControl2, labelControl3});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numericControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(codLookupControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {labelControl1, labelControl2, labelControl3, numericControl1});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {codLookupControl1, textControl1});

        form1.add(jPanel2);

        dateControl1.setAttributeName("fechaIngresoEmpresa");
        dateControl1.setRequired(true);

        labelControl4.setLabel("titular.fechaIngresoEmpresa");

        labelControl5.setLabel("titular.departamento");

        labelControl6.setLabel("titular.tipoContato");

        codLookupControl2.setAllowOnlyNumbers(true);
        codLookupControl2.setAttributeName("departamento.id");
        codLookupControl2.setCodBoxVisible(false);
        codLookupControl2.setControllerMethodName("getDepartamento");

        codLookupControl3.setAllowOnlyNumbers(true);
        codLookupControl3.setAttributeName("tipoContrato.id");
        codLookupControl3.setCodBoxVisible(false);
        codLookupControl3.setControllerMethodName("getTipoContrato");

        textControl2.setAttributeName("departamento.nombre");
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        textControl2.setRequired(true);

        textControl3.setAttributeName("tipoContrato.nombre");
        textControl3.setEnabledOnEdit(false);
        textControl3.setEnabledOnInsert(false);
        textControl3.setRequired(true);

        labelControl7.setLabel("titular.fechaInduccion");

        dateControl2.setAttributeName("fechaInduccion");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelControl5, 0, 0, Short.MAX_VALUE)
                            .addComponent(labelControl6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(codLookupControl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(codLookupControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textControl3, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(textControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelControl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelControl4, labelControl5, labelControl6, labelControl7});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codLookupControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelControl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codLookupControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {codLookupControl2, dateControl1, labelControl4, labelControl5, labelControl6, labelControl7, textControl3});

        form1.add(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(form1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(form1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void inicializar(FormController formController, boolean addToMDIFrame) {
        initComponents();
        form1.setCreateInnerVO(false);
        form1.setFormController(formController);


        PersonaLookupControllerPorNombre lookupPersonas = new PersonaLookupControllerPorNombre(
                "TIT");
        lookupPersonas.addLookup2ParentLink("persona");
        codLookupControl1.setLookupController(lookupPersonas);
        codLookupControl1.setOpenDetail("persona", PersonasDetailController.class.getName(), new Class[]{GridControl.class, BeanVO.class, Rif.class}, new Object[]{null, null, null}, 1);
        codLookupControl1.setNewDetail("persona", RifDialog.class.getName(), new Class[]{Form.class, String.class, Object[].class}, new Object[]{null, null, new Object[]{"TIT","ASE"}}, 0);

        DefaultLookupController lookupDepartamento = new DefaultLookupController(
                Departamento.class.getName());
        lookupDepartamento.addLookup2ParentLink("departamento");
        codLookupControl2.setLookupController(lookupDepartamento);

        DefaultLookupController lookupTipoContrato = new DefaultLookupController(
                TipoContrato.class.getName());
        lookupTipoContrato.addLookup2ParentLink("tipoContrato");
        codLookupControl3.setLookupController(lookupTipoContrato);





        if (addToMDIFrame) {
            pack();
        } else {
            setBounds(0, 0, 0, 0);
        }

        MDIFrame.add(this);
    }

    @Override
    public void saveGridsData() {
    }

    @Override
    public void reloadGridsData() {
    }

    @Override
    public void clearGridsData() {
    }

    @Override
    public void setOwnerVO(BeanVO beanVO) {
        //controllerObservaciones.setBeanVO(beanVO);
        Long id = null;
        if (beanVO != null) {
            //id = ((Vehiculo) beanVO).getId();
        }
        //controllerDocumentos.setBeanVO(beanVO, id);
        reloadGridsData();
    }

    @Override
    public Form getMainPanel() {
        return form1;
    }

    @Override
    public void modeChanged(int currentMode) {
        if (currentMode == Consts.INSERT) {
            clearGridsData();
        }
        if (currentMode == Consts.INSERT) {
            setEnableGridInternalButtons(false);
        } else {
            setEnableGridInternalButtons(true);
        }
    }

    private void setEnableGridInternalButtons(boolean enabled) {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CodLookupControl codLookupControl1;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.CodLookupControl codLookupControl3;
    private org.openswing.swing.client.DateControl dateControl1;
    private org.openswing.swing.client.DateControl dateControl2;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.form.client.Form form1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.NumericControl numericControl1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.TextControl textControl1;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl3;
    // End of variables declaration//GEN-END:variables
}
