package com.jswitch.pagos.vista;

import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import com.jswitch.configuracion.controlador.CoberturaLookupController;
import com.jswitch.configuracion.controlador.DiagnosticoLookupController;
import com.jswitch.pagos.controlador.DesgloseCoberturaGridInternalController;
import com.jswitch.pagos.controlador.DesgloseSumaAseguradaDetailFrameController;
import com.jswitch.pagos.controlador.DesgloseSumaAseguradaGridInternalController;
import com.jswitch.pagos.modelo.maestra.Pago;
import com.jswitch.pagos.modelo.transaccional.DesgloseCobertura;
import com.jswitch.pagos.modelo.transaccional.DesgloseSumaAsegurada;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author bc
 */
public class PagoDetailFrame extends DefaultDetailFrame {

    private DefaultGridInternalController desgloseSumaAsegurada;
    private DefaultGridInternalController desgloseCobertura;
//    private DefaultGridInternalController controllerBeneficiarios;

    public PagoDetailFrame() {
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
        labelControl3 = new org.openswing.swing.client.LabelControl();
        checkBoxControl1 = new org.openswing.swing.client.CheckBoxControl();
        textControl1 = new org.openswing.swing.client.TextControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        dateControl1 = new org.openswing.swing.client.DateControl();
        dateControl2 = new org.openswing.swing.client.DateControl();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        gridControl5 = new org.openswing.swing.client.GridControl();
        decimalColumn5 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        codLookupColumn2 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn1 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn3 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn6 = new org.openswing.swing.table.columns.client.DecimalColumn();
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
        jPanel12 = new javax.swing.JPanel();
        gridControl4 = new org.openswing.swing.client.GridControl();
        decimalColumn4 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn2 = new org.openswing.swing.table.columns.client.DecimalColumn();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
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

        setTitle("Pago");
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
                .addContainerGap(559, Short.MAX_VALUE))
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

        form1.setVOClassName(Pago.class.getName());
        form1.setEditButton(editButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridLayout(1, 2));

        labelControl1.setLabel("id");

        numericControl1.setAttributeName("id");
        numericControl1.setEnabledOnEdit(false);
        numericControl1.setEnabledOnInsert(false);

        labelControl2.setLabel("numeroFactura");

        labelControl3.setLabel("auditoria.activo");

        checkBoxControl1.setAttributeName("auditoria.activo");

        textControl1.setAttributeName("numeroFactura");
        textControl1.setRequired(true);

        textControl2.setAttributeName("numeroControl");
        textControl2.setRequired(true);

        labelControl4.setLabel("numeroControl");

        labelControl5.setLabel("fechaFactura");

        labelControl6.setLabel("fechaRecepcion");

        dateControl1.setAttributeName("fechaFactura");
        dateControl1.setRequired(true);

        dateControl2.setAttributeName("fechaRecepcion");
        dateControl2.setRequired(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                    .addComponent(dateControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                    .addComponent(numericControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                    .addComponent(textControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                    .addComponent(textControl2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                    .addComponent(checkBoxControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelControl1, labelControl2, labelControl3, labelControl4, labelControl5, labelControl6});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numericControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelControl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(dateControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dateControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkBoxControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {checkBoxControl1, labelControl1, labelControl2, labelControl3, labelControl4, labelControl5, labelControl6, numericControl1});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dateControl1, dateControl2, textControl2});

        form1.add(jPanel2);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        gridControl5.setDefaultQuickFilterCriteria(org.openswing.swing.util.java.Consts.CONTAINS);
        gridControl5.setDeleteButton(deleteButton7);
        gridControl5.setEditButton(editButton7);
        gridControl5.setFilterButton(filterButton7);
        gridControl5.setInsertButton(insertButton7);
        gridControl5.setMaxNumberOfRowsOnInsert(4);
        gridControl5.setReloadButton(reloadButton7);
        gridControl5.setSaveButton(saveButton7);
        gridControl5.setValueObjectClassName(DesgloseCobertura.class.getName());

        decimalColumn5.setColumnName("id");
        decimalColumn5.setColumnRequired(false);
        decimalColumn5.setColumnVisible(false);
        decimalColumn5.setPreferredWidth(40);
        gridControl5.getColumnContainer().add(decimalColumn5);

        textColumn2.setColumnName("cobertura.ramo.nombre");
        gridControl5.getColumnContainer().add(textColumn2);

        codLookupColumn2.setColumnName("cobertura.nombre");
        codLookupColumn2.setEditableOnEdit(true);
        codLookupColumn2.setEditableOnInsert(true);
        gridControl5.getColumnContainer().add(codLookupColumn2);

        textColumn5.setColumnName("detalle");
        textColumn5.setColumnRequired(false);
        textColumn5.setEditableOnEdit(true);
        textColumn5.setEditableOnInsert(true);
        gridControl5.getColumnContainer().add(textColumn5);

        decimalColumn1.setColumnName("montoFacturado");
        decimalColumn1.setDecimals(2);
        decimalColumn1.setEditableOnEdit(true);
        decimalColumn1.setEditableOnInsert(true);
        gridControl5.getColumnContainer().add(decimalColumn1);

        decimalColumn3.setColumnName("montoAmparado");
        decimalColumn3.setDecimals(2);
        decimalColumn3.setEditableOnEdit(true);
        decimalColumn3.setEditableOnInsert(true);
        gridControl5.getColumnContainer().add(decimalColumn3);

        decimalColumn6.setColumnName("montoNoAmparado");
        decimalColumn6.setColumnRequired(false);
        gridControl5.getColumnContainer().add(decimalColumn6);

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
                .addComponent(gridControl5, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
            .addComponent(gridControl5, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Desglose Cobertura", jPanel14);
        jPanel14.getAccessibleContext().setAccessibleName("Beneficiarios");

        gridControl4.setDefaultQuickFilterCriteria(org.openswing.swing.util.java.Consts.CONTAINS);
        gridControl4.setDeleteButton(deleteButton6);
        gridControl4.setEditButton(editButton6);
        gridControl4.setEditOnSingleRow(true);
        gridControl4.setFilterButton(filterButton6);
        gridControl4.setMaxNumberOfRowsOnInsert(4);
        gridControl4.setReloadButton(reloadButton6);
        gridControl4.setSaveButton(saveButton6);
        gridControl4.setValueObjectClassName(DesgloseSumaAsegurada.class.getName());

        decimalColumn4.setColumnName("id");
        decimalColumn4.setColumnRequired(false);
        decimalColumn4.setColumnVisible(false);
        decimalColumn4.setPreferredWidth(40);
        gridControl4.getColumnContainer().add(decimalColumn4);

        decimalColumn2.setColumnName("monto");
        decimalColumn2.setColumnRequired(false);
        decimalColumn2.setDecimals(2);
        decimalColumn2.setEditableOnEdit(true);
        decimalColumn2.setEditableOnInsert(true);
        gridControl4.getColumnContainer().add(decimalColumn2);

        codLookupColumn1.setColumnName("diagnostico.nombre");
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        gridControl4.getColumnContainer().add(codLookupColumn1);

        textColumn3.setColumnName("diagnostico.especialidad.nombre");
        gridControl4.getColumnContainer().add(textColumn3);

        textColumn4.setColumnName("diagnostico.especialidad.ramo.nombre");
        gridControl4.getColumnContainer().add(textColumn4);

        textColumn1.setColumnName("detalle");
        textColumn1.setEditableOnEdit(true);
        textColumn1.setEditableOnInsert(true);
        gridControl4.getColumnContainer().add(textColumn1);

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
        insertButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButton6ActionPerformed(evt);
            }
        });
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
                .addComponent(gridControl4, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
            .addComponent(gridControl4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Desglose Suma Asegurada", jPanel12);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                    .addComponent(form1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(form1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void insertButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButton6ActionPerformed
    new DesgloseSumaAseguradaDetailFrameController(DesgloseSumaAseguradaDetailFrame.class.getName(), gridControl4, false,(Pago)getBeanVO());
}//GEN-LAST:event_insertButton6ActionPerformed

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
        desgloseSumaAsegurada =
                new DesgloseSumaAseguradaGridInternalController(Pago.class.getName(), "getDesgloseSumaAsegurada", gridControl4);
        gridControl4.setGridDataLocator(desgloseSumaAsegurada);
        gridControl4.setController(desgloseSumaAsegurada);

        desgloseCobertura =
                new DesgloseCoberturaGridInternalController(Pago.class.getName(), "getDesgloseCobertura", gridControl5);
        gridControl5.setGridDataLocator(desgloseCobertura);
        gridControl5.setController(desgloseCobertura);

        DiagnosticoLookupController lookupDiagnostico = new DiagnosticoLookupController();
        lookupDiagnostico.addLookup2ParentLink("diagnostico");
        codLookupColumn1.setLookupController(lookupDiagnostico);

        CoberturaLookupController lookupCobertura = new CoberturaLookupController();
        lookupCobertura.addLookup2ParentLink("cobertura");
        codLookupColumn2.setLookupController(lookupCobertura);

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
//gridControl4.save();
//gridControl5.save();
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
        desgloseCobertura.setBeanVO(beanVO);
        desgloseSumaAsegurada.setBeanVO(beanVO);
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
        jPanel13.setVisible(enabled);
        jPanel15.setVisible(enabled);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn1;
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn2;
    private org.openswing.swing.client.CheckBoxControl checkBoxControl1;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn2;
    private org.openswing.swing.client.DateControl dateControl1;
    private org.openswing.swing.client.DateControl dateControl2;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn1;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn2;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn1;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn2;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn3;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn4;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn5;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn6;
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
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
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
    private org.openswing.swing.client.TextControl textControl2;
    // End of variables declaration//GEN-END:variables
}
