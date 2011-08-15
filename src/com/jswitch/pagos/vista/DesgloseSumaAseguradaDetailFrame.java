package com.jswitch.pagos.vista;

import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultDetailFrame;
import com.jswitch.configuracion.controlador.DiagnosticoLookupController;
import com.jswitch.configuracion.modelo.dominio.patologias.Tratamiento;
import com.jswitch.pagos.controlador.DesgloseSumaAseguradaDetailFrameController;
import com.jswitch.pagos.controlador.TratamientoGridInternalController2;
import com.jswitch.pagos.modelo.transaccional.DesgloseSumaAsegurada;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.lookup.client.LookupParent;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author bc
 */
public class DesgloseSumaAseguradaDetailFrame extends DefaultDetailFrame {

    private DefaultGridInternalController tratamientos;

    public DesgloseSumaAseguradaDetailFrame() {
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
        labelControl3 = new org.openswing.swing.client.LabelControl();
        checkBoxControl1 = new org.openswing.swing.client.CheckBoxControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        textControl1 = new org.openswing.swing.client.TextControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        textAreaControl1 = new org.openswing.swing.client.TextAreaControl();
        codLookupControl1 = new org.openswing.swing.client.CodLookupControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        gridControl4 = new org.openswing.swing.client.GridControl();
        decimalColumn4 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        checkBoxColumn1 = new org.openswing.swing.table.columns.client.CheckBoxColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn1 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        jPanel13 = new javax.swing.JPanel();
        insertButton6 = new org.openswing.swing.client.InsertButton();
        deleteButton6 = new org.openswing.swing.client.DeleteButton();
        filterButton6 = new org.openswing.swing.client.FilterButton();

        setTitle("Desglose de Suma Asegurada");
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

        form1.setVOClassName(DesgloseSumaAsegurada.class.getName());
        form1.setEditButton(editButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridLayout(1, 2));

        labelControl1.setLabel("monto");

        numericControl1.setAttributeName("monto");
        numericControl1.setRequired(true);

        labelControl3.setLabel("auditoria.activo");

        checkBoxControl1.setAttributeName("auditoria.activo");

        labelControl2.setLabel("diagnostico.nombre");

        textControl1.setAttributeName("diagnostico.especialidad.nombre");
        textControl1.setEnabled(false);
        textControl1.setEnabledOnEdit(false);
        textControl1.setEnabledOnInsert(false);

        textControl2.setAttributeName("diagnostico.especialidad.ramo.nombre");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);

        textAreaControl1.setAttributeName("detalle");

        codLookupControl1.setAttributeName("diagnostico.nombre");
        codLookupControl1.setEnableCodBox(false);
        codLookupControl1.setRequired(true);

        labelControl4.setLabel("especialidad.nombre");

        labelControl5.setLabel("ramo.nombre");

        labelControl6.setLabel("detalle");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelControl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textAreaControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                    .addComponent(textControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                    .addComponent(textControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                    .addComponent(codLookupControl1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                    .addComponent(numericControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codLookupControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelControl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textAreaControl1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkBoxControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {checkBoxControl1, labelControl1, labelControl2, labelControl3, labelControl4, labelControl5, labelControl6, numericControl1});

        form1.add(jPanel2);

        gridControl4.setDefaultQuickFilterCriteria(org.openswing.swing.util.java.Consts.CONTAINS);
        gridControl4.setDeleteButton(deleteButton6);
        gridControl4.setEditOnSingleRow(true);
        gridControl4.setFilterButton(filterButton6);
        gridControl4.setMaxNumberOfRowsOnInsert(4);
        gridControl4.setValueObjectClassName(Tratamiento.class.getName());

        decimalColumn4.setColumnName("id");
        decimalColumn4.setColumnRequired(false);
        decimalColumn4.setColumnVisible(false);
        decimalColumn4.setPreferredWidth(40);
        gridControl4.getColumnContainer().add(decimalColumn4);

        textColumn1.setColumnName("nombre");
        gridControl4.getColumnContainer().add(textColumn1);

        textColumn2.setColumnName("diagnostico.nombre");
        gridControl4.getColumnContainer().add(textColumn2);

        textColumn3.setColumnName("diagnostico.especialidad.nombre");
        gridControl4.getColumnContainer().add(textColumn3);

        textColumn4.setColumnName("diagnostico.especialidad.ramo.nombre");
        gridControl4.getColumnContainer().add(textColumn4);

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
        jPanel13.add(deleteButton6);
        jPanel13.add(filterButton6);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridControl4, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gridControl4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tratamientos", jPanel12);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(form1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(form1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public BeanVO getBeanVO() {
        return (BeanVO) form1.getVOModel().getValueObject();
    }

    public GridControl getGridAsegurado() {
        return gridControl4;
    }

    @Override
    public void inicializar(FormController formController, boolean addToMDIFrame) {
        initComponents();

        DiagnosticoLookupController lookupDiagnostico = new DiagnosticoLookupController();
        lookupDiagnostico.addLookup2ParentLink("diagnostico");
        codLookupControl1.setLookupController(lookupDiagnostico);
        insertButton6.addActionListener((DesgloseSumaAseguradaDetailFrameController) formController);
        tratamientos =
                new TratamientoGridInternalController2(DesgloseSumaAsegurada.class.getName(), "getTratamientos", gridControl4);
        gridControl4.setGridDataLocator(tratamientos);
        gridControl4.setController(tratamientos);

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
    }

    @Override
    public void clearGridsData() {
        gridControl4.clearData();
    }

    @Override
    public void setOwnerVO(BeanVO beanVO) {
        tratamientos.setBeanVO(beanVO);
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
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn1;
    private org.openswing.swing.client.CheckBoxControl checkBoxControl1;
    private org.openswing.swing.client.CodLookupControl codLookupControl1;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn1;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn4;
    private org.openswing.swing.client.DeleteButton deleteButton6;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.FilterButton filterButton6;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.GridControl gridControl4;
    private org.openswing.swing.client.InsertButton insertButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.NumericControl numericControl1;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.TextAreaControl textAreaControl1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn8;
    private org.openswing.swing.client.TextControl textControl1;
    private org.openswing.swing.client.TextControl textControl2;
    // End of variables declaration//GEN-END:variables
}
