package com.jswitch.certificados.vista;

import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.asegurados.modelo.maestra.Asegurado;
import com.jswitch.certificados.modelo.maestra.Certificado;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import com.jswitch.certificados.controlador.CertificadoDetailController;
import com.jswitch.asegurados.controlador.AseguradosGridInternalController;
import com.jswitch.asegurados.controlador.BeneficiariosGridInternalController;
import com.jswitch.asegurados.controlador.TitularLookupController;
import com.jswitch.asegurados.modelo.maestra.Beneficiario;
import com.jswitch.asegurados.vista.TitularDetailFrame;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author bc
 */
public class CertificadoDetailFrame extends DefaultDetailFrame {

    private DefaultGridInternalController controllerAsegurados;
    private DefaultGridInternalController controllerBeneficiarios;
//    private DefaultGridInternalController controllerBeneficiarios;
    private TitularLookupController lookupTitular;

    public CertificadoDetailFrame() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        editButton1 = new org.openswing.swing.client.EditButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        form1 = new org.openswing.swing.form.client.Form();
        jPanel2 = new javax.swing.JPanel();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        numericControl1 = new org.openswing.swing.client.NumericControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        codLookupControl1 = new org.openswing.swing.client.CodLookupControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        checkBoxControl1 = new org.openswing.swing.client.CheckBoxControl();
        textControl1 = new org.openswing.swing.client.TextControl();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        gridControl4 = new org.openswing.swing.client.GridControl();
        decimalColumn4 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        integerColumn1 = new org.openswing.swing.table.columns.client.IntegerColumn();
        checkBoxColumn1 = new org.openswing.swing.table.columns.client.CheckBoxColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn1 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        jPanel13 = new javax.swing.JPanel();
        insertButton6 = new org.openswing.swing.client.InsertButton();
        editButton6 = new org.openswing.swing.client.EditButton();
        deleteButton6 = new org.openswing.swing.client.DeleteButton();
        saveButton6 = new org.openswing.swing.client.SaveButton();
        reloadButton6 = new org.openswing.swing.client.ReloadButton();
        filterButton6 = new org.openswing.swing.client.FilterButton();
        jPanel14 = new javax.swing.JPanel();
        gridControl5 = new org.openswing.swing.client.GridControl();
        decimalColumn5 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        integerColumn2 = new org.openswing.swing.table.columns.client.IntegerColumn();
        decimalColumn1 = new org.openswing.swing.table.columns.client.DecimalColumn();
        checkBoxColumn2 = new org.openswing.swing.table.columns.client.CheckBoxColumn();
        textColumn9 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn2 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        jPanel15 = new javax.swing.JPanel();
        insertButton7 = new org.openswing.swing.client.InsertButton();
        editButton7 = new org.openswing.swing.client.EditButton();
        deleteButton7 = new org.openswing.swing.client.DeleteButton();
        saveButton7 = new org.openswing.swing.client.SaveButton();
        reloadButton7 = new org.openswing.swing.client.ReloadButton();
        filterButton7 = new org.openswing.swing.client.FilterButton();

        setTitle("Certificado");
        setPreferredSize(new java.awt.Dimension(675, 542));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(561, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        form1.setVOClassName(Certificado.class.getName());
        form1.setEditButton(editButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridLayout(1, 2));

        labelControl1.setLabel("id");

        numericControl1.setAttributeName("id");
        numericControl1.setEnabledOnEdit(false);
        numericControl1.setEnabledOnInsert(false);

        labelControl2.setLabel("certificado.titular");

        codLookupControl1.setAttributeName("titular.persona.rif.rif");
        codLookupControl1.setControllerMethodName("getNuevoTitular");
        codLookupControl1.setMaxCharacters(12);
        codLookupControl1.setRequired(true);

        labelControl3.setLabel("auditoria.activo");

        checkBoxControl1.setAttributeName("auditoria.activo");

        textControl1.setAttributeName("titular.persona.nombreLargo");
        textControl1.setEnabledOnEdit(false);
        textControl1.setEnabledOnInsert(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numericControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                            .addComponent(codLookupControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBoxControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelControl1, labelControl2, labelControl3});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(numericControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codLookupControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {checkBoxControl1, codLookupControl1, labelControl1, labelControl2, labelControl3, numericControl1});

        form1.add(jPanel2);

        gridControl4.setDefaultQuickFilterCriteria(org.openswing.swing.util.java.Consts.CONTAINS);
        gridControl4.setDeleteButton(deleteButton6);
        gridControl4.setEditButton(editButton6);
        gridControl4.setEditOnSingleRow(true);
        gridControl4.setFilterButton(filterButton6);
        gridControl4.setMaxNumberOfRowsOnInsert(4);
        gridControl4.setReloadButton(reloadButton6);
        gridControl4.setSaveButton(saveButton6);
        gridControl4.setValueObjectClassName(Asegurado.class.getName());
        gridControl4.setVisibleStatusPanel(false);

        decimalColumn4.setColumnName("id");
        decimalColumn4.setColumnRequired(false);
        decimalColumn4.setPreferredWidth(40);
        gridControl4.getColumnContainer().add(decimalColumn4);

        textColumn1.setColumnName("persona.rif.rif");
        gridControl4.getColumnContainer().add(textColumn1);

        textColumn3.setColumnName("persona.nombreLargo");
        gridControl4.getColumnContainer().add(textColumn3);

        textColumn4.setColumnName("parentesco.nombre");
        gridControl4.getColumnContainer().add(textColumn4);

        integerColumn1.setColumnName("persona.edad");
        gridControl4.getColumnContainer().add(integerColumn1);

        checkBoxColumn1.setColumnName("auditoria.activo");
        gridControl4.getColumnContainer().add(checkBoxColumn1);

        textColumn8.setColumnName("auditoria.usuarioInsert");
        textColumn8.setColumnRequired(false);
        gridControl4.getColumnContainer().add(textColumn8);

        dateTimeColumn1.setColumnName("auditoria.fechaInsert");
        dateTimeColumn1.setColumnRequired(false);
        gridControl4.getColumnContainer().add(dateTimeColumn1);

        jPanel13.setLayout(new java.awt.GridLayout(3, 2, 2, 2));

        insertButton6.setButtonId("asegurado");
        jPanel13.add(insertButton6);
        jPanel13.add(editButton6);
        jPanel13.add(deleteButton6);
        jPanel13.add(saveButton6);
        jPanel13.add(reloadButton6);
        jPanel13.add(filterButton6);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridControl4, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
            .addComponent(gridControl4, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Asegurados", jPanel12);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        gridControl5.setDefaultQuickFilterCriteria(org.openswing.swing.util.java.Consts.CONTAINS);
        gridControl5.setDeleteButton(deleteButton7);
        gridControl5.setEditButton(editButton7);
        gridControl5.setFilterButton(filterButton7);
        gridControl5.setMaxNumberOfRowsOnInsert(4);
        gridControl5.setReloadButton(reloadButton7);
        gridControl5.setSaveButton(saveButton7);
        gridControl5.setValueObjectClassName(Beneficiario.class.getName());
        gridControl5.setVisibleStatusPanel(false);

        decimalColumn5.setColumnName("id");
        decimalColumn5.setColumnRequired(false);
        decimalColumn5.setPreferredWidth(40);
        gridControl5.getColumnContainer().add(decimalColumn5);

        textColumn2.setColumnName("persona.rif.rif");
        textColumn2.setEditableOnEdit(true);
        textColumn2.setEditableOnInsert(true);
        textColumn2.setMaxCharacters(1024);
        gridControl5.getColumnContainer().add(textColumn2);

        textColumn5.setColumnName("persona.nombreLargo");
        gridControl5.getColumnContainer().add(textColumn5);

        integerColumn2.setColumnName("persona.edad");
        gridControl5.getColumnContainer().add(integerColumn2);

        decimalColumn1.setColumnName("indemnizacion");
        gridControl5.getColumnContainer().add(decimalColumn1);

        checkBoxColumn2.setColumnName("auditoria.activo");
        gridControl5.getColumnContainer().add(checkBoxColumn2);

        textColumn9.setColumnName("auditoria.usuarioInsert");
        textColumn9.setColumnRequired(false);
        gridControl5.getColumnContainer().add(textColumn9);

        dateTimeColumn2.setColumnName("auditoria.fechaInsert");
        dateTimeColumn2.setColumnRequired(false);
        gridControl5.getColumnContainer().add(dateTimeColumn2);

        jPanel15.setLayout(new java.awt.GridLayout(3, 2, 2, 2));

        insertButton7.setButtonId("beneficiario");
        jPanel15.add(insertButton7);
        jPanel15.add(editButton7);
        jPanel15.add(deleteButton7);
        jPanel15.add(saveButton7);
        jPanel15.add(reloadButton7);
        jPanel15.add(filterButton7);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridControl5, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
            .addComponent(gridControl5, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Beneficiarios", jPanel14);
        jPanel14.getAccessibleContext().setAccessibleName("Beneficiarios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                    .addComponent(form1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(form1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setVisiblePaneles(boolean visible) {
        jPanel13.setVisible(visible);
        jPanel15.setVisible(visible);
    }

    public BeanVO getBeanVO() {
        return (BeanVO) form1.getVOModel().getValueObject();
    }

    public GridControl getGridAsegurado() {
        return gridControl4;
    }

    public GridControl getGridBeneficiario() {
        return gridControl5;
    }

    @Override
    public void inicializar(FormController formController, boolean addToMDIFrame) {
        initComponents();
        lookupTitular = new TitularLookupController();
        lookupTitular.addLookup2ParentLink("titular");
        codLookupControl1.setLookupController(lookupTitular);
        codLookupControl1.setOpenDetail("titular"
                , DefaultDetailFrameController.class.getName()
                , new Class[]{String.class, GridControl.class
                        , BeanVO.class, Boolean.class}
        , new Object[]{TitularDetailFrame.class.getName(), null, null, Boolean.FALSE}
        , 2);
//        codLookupControl1.setNewDetail("titular"
//                , DefaultDetailFrameController.class.getName()
//                , new Class[]{String.class , GridControl.class, BeanVO.class , Boolean.class}
//        , new Object[]{TitularDetailFrame.class.getName(), null, null, Boolean.FALSE}
//        , 2);
       
        controllerAsegurados =
                new AseguradosGridInternalController(Certificado.class.getName(), "getAsegurados", gridControl4);
        gridControl4.setGridDataLocator(controllerAsegurados);
        gridControl4.setController(controllerAsegurados);

        insertButton6.addActionListener((CertificadoDetailController) formController);

        controllerBeneficiarios =
                new BeneficiariosGridInternalController(Certificado.class.getName(), "getBeneficiarios", gridControl5, null);
        gridControl5.setGridDataLocator(controllerBeneficiarios);
        gridControl5.setController(controllerBeneficiarios);

        insertButton7.addActionListener((CertificadoDetailController) formController);

        form1.setCreateInnerVO(false);
        form1.setFormController(formController);

        if (addToMDIFrame) {
            pack();
        } else {
            setBounds(0, 0, 0, 0);
        }

        MDIFrame.add(this);
    }

    @Override
    public void saveGridsData() {
        setVisiblePaneles(true);
    }

    @Override
    public void reloadGridsData() {
        gridControl4.reloadData();
        gridControl5.reloadData();
    }

    @Override
    public void clearGridsData() {
        gridControl4.clearData();
        gridControl5.clearData();
    }

    @Override
    public void setOwnerVO(BeanVO beanVO) {
        controllerBeneficiarios.setBeanVO(beanVO);
        controllerAsegurados.setBeanVO(beanVO);
        Long id = null;
        if (beanVO != null) {
            System.out.println("beanVO - diferente de NULL");
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
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn1;
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn2;
    private org.openswing.swing.client.CheckBoxControl checkBoxControl1;
    private org.openswing.swing.client.CodLookupControl codLookupControl1;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn1;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn2;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn1;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn4;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn5;
    private org.openswing.swing.client.DeleteButton deleteButton6;
    private org.openswing.swing.client.DeleteButton deleteButton7;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButton6;
    private org.openswing.swing.client.EditButton editButton7;
    private org.openswing.swing.client.FilterButton filterButton6;
    private org.openswing.swing.client.FilterButton filterButton7;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.GridControl gridControl4;
    private org.openswing.swing.client.GridControl gridControl5;
    private org.openswing.swing.client.InsertButton insertButton6;
    private org.openswing.swing.client.InsertButton insertButton7;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn1;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.NumericControl numericControl1;
    private org.openswing.swing.client.ReloadButton reloadButton6;
    private org.openswing.swing.client.ReloadButton reloadButton7;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButton6;
    private org.openswing.swing.client.SaveButton saveButton7;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn8;
    private org.openswing.swing.table.columns.client.TextColumn textColumn9;
    private org.openswing.swing.client.TextControl textControl1;
    // End of variables declaration//GEN-END:variables
}
