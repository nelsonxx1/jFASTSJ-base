package com.jswitch.configuracion.vista;

import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.util.DefaultGridFrame;
import com.jswitch.configuracion.controlador.SumaAseguradaPlanGridInternalController;
import com.jswitch.configuracion.controlador.DiagnosticoLookupController;
import com.jswitch.configuracion.modelo.transaccional.SumaAsegurada;
import com.jswitch.configuracion.modelo.maestra.Plan;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author adrian
 */
public class PlanesGridFrame extends DefaultGridFrame {

    private DefaultGridInternalController controllerSumaAsegurada;
    private DiagnosticoLookupController lookupDiagnostico;

    public PlanesGridFrame() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        gridControl1 = new org.openswing.swing.client.GridControl();
        integerColumn2 = new org.openswing.swing.table.columns.client.IntegerColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        decimalColumn1 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel8 = new javax.swing.JPanel();
        insertButton4 = new org.openswing.swing.client.InsertButton();
        editButton4 = new org.openswing.swing.client.EditButton();
        deleteButton4 = new org.openswing.swing.client.DeleteButton();
        saveButton4 = new org.openswing.swing.client.SaveButton();
        reloadButton4 = new org.openswing.swing.client.ReloadButton();
        exportButton4 = new org.openswing.swing.client.ExportButton();
        jPanel3 = new javax.swing.JPanel();
        gridData = new org.openswing.swing.client.GridControl();
        integerColumn1 = new org.openswing.swing.table.columns.client.IntegerColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel7 = new javax.swing.JPanel();
        insertButton3 = new org.openswing.swing.client.InsertButton();
        editButton3 = new org.openswing.swing.client.EditButton();
        deleteButton3 = new org.openswing.swing.client.DeleteButton();
        saveButton3 = new org.openswing.swing.client.SaveButton();
        reloadButton3 = new org.openswing.swing.client.ReloadButton();
        exportButton3 = new org.openswing.swing.client.ExportButton();

        setTitle("Planes");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("SumaAsegurada"));

        gridControl1.setDeleteButton(deleteButton4);
        gridControl1.setEditButton(editButton4);
        gridControl1.setExportButton(exportButton4);
        gridControl1.setInsertButton(insertButton4);
        gridControl1.setReloadButton(reloadButton4);
        gridControl1.setSaveButton(saveButton4);
        gridControl1.setValueObjectClassName(SumaAsegurada.class.getName());

        integerColumn2.setColumnName("id");
        integerColumn2.setColumnRequired(false);
        integerColumn2.setColumnVisible(false);
        integerColumn2.setPreferredWidth(60);
        gridControl1.getColumnContainer().add(integerColumn2);

        textColumn4.setColumnName("diagnostico.especialidad.ramo.nombre");
        gridControl1.getColumnContainer().add(textColumn4);

        textColumn2.setColumnName("diagnostico.especialidad.nombre");
        gridControl1.getColumnContainer().add(textColumn2);

        codLookupColumn1.setColumnName("diagnostico.nombre");
        codLookupColumn1.setControllerMethodName("getPatologias");
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        gridControl1.getColumnContainer().add(codLookupColumn1);

        decimalColumn1.setColumnName("sumaAsegurada");
        decimalColumn1.setDecimals(2);
        decimalColumn1.setEditableOnEdit(true);
        decimalColumn1.setEditableOnInsert(true);
        gridControl1.getColumnContainer().add(decimalColumn1);

        textColumn3.setColumnName("plan.nombre");
        textColumn3.setColumnRequired(false);
        gridControl1.getColumnContainer().add(textColumn3);

        jPanel8.setLayout(new java.awt.GridLayout(3, 2, 2, 2));
        jPanel8.add(insertButton4);
        jPanel8.add(editButton4);
        jPanel8.add(deleteButton4);
        jPanel8.add(saveButton4);
        jPanel8.add(reloadButton4);
        jPanel8.add(exportButton4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
            .addComponent(gridControl1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Plan"));

        gridData.setDeleteButton(deleteButton3);
        gridData.setEditButton(editButton3);
        gridData.setExportButton(exportButton3);
        gridData.setInsertButton(insertButton3);
        gridData.setReloadButton(reloadButton3);
        gridData.setSaveButton(saveButton3);
        gridData.setValueObjectClassName(Plan.class.getName());

        integerColumn1.setColumnName("id");
        integerColumn1.setColumnRequired(false);
        integerColumn1.setPreferredWidth(60);
        gridData.getColumnContainer().add(integerColumn1);

        textColumn1.setColumnFilterable(true);
        textColumn1.setColumnName("nombre");
        textColumn1.setColumnSortable(true);
        textColumn1.setEditableOnEdit(true);
        textColumn1.setEditableOnInsert(true);
        textColumn1.setMaxCharacters(120);
        textColumn1.setPreferredWidth(320);
        textColumn1.setSortVersus(org.openswing.swing.util.java.Consts.ASC_SORTED);
        textColumn1.setUpperCase(true);
        gridData.getColumnContainer().add(textColumn1);

        jPanel7.setLayout(new java.awt.GridLayout(3, 2, 2, 2));
        jPanel7.add(insertButton3);
        jPanel7.add(editButton3);
        jPanel7.add(deleteButton3);
        jPanel7.add(saveButton3);
        jPanel7.add(reloadButton3);
        jPanel7.add(exportButton3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
            .addComponent(gridData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void inicializar(GridDataLocator gridDataLocator, GridController gridController, String valueObjectClassName, String titulo, boolean addToMDIFrame) {
        initComponents();

        gridData.setGridDataLocator(gridDataLocator);
        gridData.setController(gridController);


        lookupDiagnostico = new DiagnosticoLookupController();
        lookupDiagnostico.addLookup2ParentLink("diagnostico");
        codLookupColumn1.setLookupController(lookupDiagnostico);

        controllerSumaAsegurada =
                new SumaAseguradaPlanGridInternalController(Plan.class.getName(), "getSumasAseguradas", gridControl1);
        gridControl1.setGridDataLocator(controllerSumaAsegurada);
        gridControl1.setController(controllerSumaAsegurada);

        if (addToMDIFrame) {
            pack();
        } else {
            setBounds(0, 0, 0, 0);
        }
        MDIFrame.add(this);
    }

    @Override
    public void reloadGridsData() {
        gridControl1.reloadData();
    }

    @Override
    public void clearGridsData() {
        gridControl1.clearData();
    }

    @Override
    public void setOwnerVO(BeanVO beanVO) {

        controllerSumaAsegurada.setBeanVO(beanVO);
        reloadGridsData();
    }

    @Override
    public GridControl getGridControl() {
        return gridData;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn1;
    private org.openswing.swing.client.DeleteButton deleteButton3;
    private org.openswing.swing.client.DeleteButton deleteButton4;
    private org.openswing.swing.client.EditButton editButton3;
    private org.openswing.swing.client.EditButton editButton4;
    private org.openswing.swing.client.ExportButton exportButton3;
    private org.openswing.swing.client.ExportButton exportButton4;
    private org.openswing.swing.client.GridControl gridControl1;
    private org.openswing.swing.client.GridControl gridData;
    private org.openswing.swing.client.InsertButton insertButton3;
    private org.openswing.swing.client.InsertButton insertButton4;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn1;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private org.openswing.swing.client.ReloadButton reloadButton3;
    private org.openswing.swing.client.ReloadButton reloadButton4;
    private org.openswing.swing.client.SaveButton saveButton3;
    private org.openswing.swing.client.SaveButton saveButton4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    // End of variables declaration//GEN-END:variables
}
