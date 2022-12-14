/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.Computer;

/**
 *
 * @author novil
 */
public class ComputerPanel extends javax.swing.JPanel {
    
    private int operatorId;
    private int indexRowSelected;

    /**
     * Creates new form Komputer
     */
    public ComputerPanel(int operatorId) {
        initComponents();
        this.operatorId = operatorId;
        
        ComputerPanel self = this;
        getTableSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                self.indexRowSelected = computersTable.getSelectedRow();
                if (!e.getValueIsAdjusting() && self.indexRowSelected >= 0) { // This line prevents double events
                    setToEditMode();
                }
            }
        });
        
        fetchComputersData();
    }
    
    public void fetchComputersData() {
        DefaultTableModel model = (DefaultTableModel) computersTable.getModel();
        model.setRowCount(0);
        for (Computer computer : Computer.get()) {
            model.addRow(new Object[] { computer.getId(), computer.getName() });
        }
        setToCreateMode();
    }
    
    public ListSelectionModel getTableSelectionModel() {
        return computersTable.getSelectionModel();
    }
    
    public int getSelectedId() {
        return Integer.parseInt(computersTable.getValueAt(indexRowSelected, 0).toString());
    }
    
    public String getSelectedName() {
        return computersTable.getValueAt(indexRowSelected, 1).toString();
    }
    
    public void setToCreateMode() {
        this.indexRowSelected = -1;
        computerNameTextField.setText("");
        addComputerButton.setText("Tambah");
        getTableSelectionModel().clearSelection();
    }
    
    public void setToEditMode() {
        computerNameTextField.setText(getSelectedName());
        addComputerButton.setText("Edit");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        computerNameLabel = new javax.swing.JLabel();
        computerNameTextField = new javax.swing.JTextField();
        addComputerButton = new javax.swing.JButton();
        deleteComputerButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        computersTable = new javax.swing.JTable();
        clearSelectionButton = new javax.swing.JButton();

        computerNameLabel.setText("Nama Komputer");

        addComputerButton.setText("Tambah");
        addComputerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addComputerButtonActionPerformed(evt);
            }
        });

        deleteComputerButton.setText("Hapus");
        deleteComputerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteComputerButtonActionPerformed(evt);
            }
        });

        computersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "No", "Nama"
            }
        ));
        jScrollPane1.setViewportView(computersTable);

        clearSelectionButton.setText("Lepas Pilihan");
        clearSelectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearSelectionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(computerNameLabel)
                        .addGap(30, 30, 30)
                        .addComponent(computerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(addComputerButton)
                        .addGap(10, 10, 10)
                        .addComponent(deleteComputerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearSelectionButton)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addComputerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(computerNameLabel)
                        .addComponent(computerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deleteComputerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(clearSelectionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addComputerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addComputerButtonActionPerformed
        String name = computerNameTextField.getText();
        Computer computer = new Computer();
        computer.setOperatorId(operatorId);
        
        if (name.isBlank()) {
            JOptionPane.showMessageDialog(null, "Nama komputer belum diisi");
            return;
        }
        
        // create
        if (indexRowSelected < 0) {
            computer.setName(name);

            if (computer.create()) {
                JOptionPane.showMessageDialog(null, "Komputer berhasil ditambahkan");
            } else {
                JOptionPane.showMessageDialog(null, "Komputer gagal ditambahkan");
            }
        // update
        } else {
            computer.setId(getSelectedId());
            computer.setName(name);
            
            if (computer.update()) {
                JOptionPane.showMessageDialog(null, "Komputer berhasil diedit");
            } else {
                JOptionPane.showMessageDialog(null, "Komputer gagal diedit");
            }
        }
        
        fetchComputersData();
    }//GEN-LAST:event_addComputerButtonActionPerformed

    private void deleteComputerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteComputerButtonActionPerformed
        if (indexRowSelected < 0) {
            JOptionPane.showMessageDialog(null, "Mohon pilih data yang ingin dihapus");
            return;
        }
        
        String confirmMessage = String.format("Apakah anda yakin ingin menghapus data \"%s\"?", getSelectedName());
        if (JOptionPane.showConfirmDialog(null, confirmMessage) == JOptionPane.OK_OPTION) {
            Computer computer = new Computer();
            computer.setId(getSelectedId());

            if (computer.delete()) {
                JOptionPane.showMessageDialog(null, "Komputer berhasil dihapus");
            } else {
                JOptionPane.showMessageDialog(null, "Komputer gagal dihapus");
            }

            fetchComputersData();
        }
    }//GEN-LAST:event_deleteComputerButtonActionPerformed

    private void clearSelectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearSelectionButtonActionPerformed
        getTableSelectionModel().clearSelection();
        setToCreateMode();
    }//GEN-LAST:event_clearSelectionButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addComputerButton;
    private javax.swing.JButton clearSelectionButton;
    private javax.swing.JLabel computerNameLabel;
    private javax.swing.JTextField computerNameTextField;
    private javax.swing.JTable computersTable;
    private javax.swing.JButton deleteComputerButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
