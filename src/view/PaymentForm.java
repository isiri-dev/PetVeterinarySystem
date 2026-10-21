/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import model.Payment;
import model.PaymentDAO;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class PaymentForm extends javax.swing.JFrame {
    
    private final PaymentDAO paymentDAO = new PaymentDAO();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PaymentForm.class.getName());

    /**
     * Creates new form PaymentForm
     */
    public PaymentForm() {
        initComponents();
        
         setLocationRelativeTo(null);

    
    txtPaymentId.setEditable(false);
    txtPaymentId.setText("");

    
    txtAppointmentID.setText("");
    txtAmount.setText("");

    
    setupPaymentTable();

    
    btnAdd.addActionListener(evt -> addPayment());
    btnUpdate.addActionListener(evt -> updatePayment());
    btnDelete.addActionListener(evt -> deletePayment());
    btnClear.addActionListener(evt -> clearFields());

    
    loadPayments();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPaymentId = new javax.swing.JTextField();
        txtAppointmentID = new javax.swing.JTextField();
        txtAmount = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Cashcmb = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        Paymentcmb = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Payment Management");
        setPreferredSize(new java.awt.Dimension(1100, 700));

        jPanel1.setBackground(new java.awt.Color(24, 35, 59));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 90));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Payment Management");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons8-wallet-50 (1) (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(245, 247, 250));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(24, 35, 59));
        jLabel2.setText("Payment ID");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(24, 35, 59));
        jLabel4.setText("Appointment ID");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(24, 35, 59));
        jLabel5.setText("Amount");

        txtPaymentId.setBackground(new java.awt.Color(255, 255, 255));
        txtPaymentId.setText("Payment ID");

        txtAppointmentID.setBackground(new java.awt.Color(255, 255, 255));
        txtAppointmentID.setText("Appointment ID");

        txtAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtAmount.setText("Amount");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(24, 35, 59));
        jLabel6.setText("Payment Method");

        Cashcmb.setBackground(new java.awt.Color(255, 255, 255));
        Cashcmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Card", "Bank Transfer" }));

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(24, 35, 59));
        jLabel7.setText("Payment Status");

        Paymentcmb.setBackground(new java.awt.Color(255, 255, 255));
        Paymentcmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Paid", "Pending", " " }));

        btnAdd.setBackground(new java.awt.Color(21, 101, 192));
        btnAdd.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.setPreferredSize(new java.awt.Dimension(130, 40));
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnUpdate.setBackground(new java.awt.Color(4, 120, 87));
        btnUpdate.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setPreferredSize(new java.awt.Dimension(130, 40));

        btnDelete.setBackground(new java.awt.Color(220, 53, 69));
        btnDelete.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setPreferredSize(new java.awt.Dimension(130, 40));

        btnClear.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnClear.setText("Clear");
        btnClear.setPreferredSize(new java.awt.Dimension(130, 40));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtPaymentId)
                                .addComponent(txtAppointmentID, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Cashcmb, 0, 194, Short.MAX_VALUE)
                            .addComponent(Paymentcmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPaymentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(Cashcmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAppointmentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(Paymentcmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.setForeground(new java.awt.Color(24, 35, 59));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Payment ID", "Appointment ID", "Amount", "Payment Method", "Payment Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1162, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       try {
     
           for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            javax.swing.UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
}       
       catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
    logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new PaymentForm().setVisible(true));
    }
    
    
private void setupPaymentTable() {
    DefaultTableModel model = new DefaultTableModel(
        new String[]{
            "Payment ID",
            "Appointment ID",
            "Amount",
            "Payment Method",
            "Payment Status"
        }, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    jTable1.setModel(model);

    jTable1.getSelectionModel().addListSelectionListener(event -> {
        if (!event.getValueIsAdjusting()
                && jTable1.getSelectedRow() != -1) {

            int row = jTable1.getSelectedRow();

            txtPaymentId.setText(
                jTable1.getValueAt(row, 0).toString()
            );

            txtAppointmentID.setText(
                jTable1.getValueAt(row, 1).toString()
            );

            txtAmount.setText(
                jTable1.getValueAt(row, 2).toString()
            );

            Cashcmb.setSelectedItem(
                jTable1.getValueAt(row, 3).toString()
            );

            Paymentcmb.setSelectedItem(
                jTable1.getValueAt(row, 4).toString()
            );
        }
    });
}


// Load payments
private void loadPayments() {
    try {
        DefaultTableModel model =
            (DefaultTableModel) jTable1.getModel();

        model.setRowCount(0);

        List<Payment> payments = paymentDAO.getAllPayments();

        for (Payment payment : payments) {
            model.addRow(new Object[]{
                payment.getPaymentId(),
                payment.getAppointmentId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getPaymentStatus()
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
            this,
            "Unable to load payments: " + e.getMessage()
        );
    }
}



private void addPayment() {
    try {
        String appointmentText =
            txtAppointmentID.getText().trim();

        String amountText =
            txtAmount.getText().trim();

        if (appointmentText.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Please enter Appointment ID and Amount."
            );
            return;
        }

        int appointmentId = Integer.parseInt(appointmentText);
        double amount = Double.parseDouble(amountText);

        if (appointmentId <= 0 || !Double.isFinite(amount)
                || amount <= 0) {
            JOptionPane.showMessageDialog(
                this, "Enter a valid Appointment ID and positive Amount."
            );
            return;
        }

        String method = Cashcmb.getSelectedItem().toString();
        String status = Paymentcmb.getSelectedItem().toString().trim();

        if (status.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Please select a payment status."
            );
            return;
        }

        Payment payment = new Payment();
        payment.setAppointmentId(appointmentId);
        payment.setAmount(amount);
        payment.setPaymentDate(
            new Timestamp(System.currentTimeMillis())
        );
        payment.setPaymentMethod(method);
        payment.setPaymentStatus(status);

        if (paymentDAO.addPayment(payment)) {
            JOptionPane.showMessageDialog(
                this, "Payment added successfully!"
            );
            loadPayments();
            clearFields();
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(
            this, "Appointment ID and Amount must be valid numbers."
        );
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
            this, "Unable to add payment: " + e.getMessage()
        );
    }
}



private void updatePayment() {
    try {
        if (txtPaymentId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Please select a payment from the table first."
            );
            return;
        }

        int paymentId = Integer.parseInt(
            txtPaymentId.getText().trim()
        );

        int appointmentId = Integer.parseInt(
            txtAppointmentID.getText().trim()
        );

        double amount = Double.parseDouble(
            txtAmount.getText().trim()
        );

        if (appointmentId <= 0 || !Double.isFinite(amount)
                || amount <= 0) {
            JOptionPane.showMessageDialog(
                this, "Enter a valid Appointment ID and positive Amount."
            );
            return;
        }

        String method = Cashcmb.getSelectedItem().toString();
        String status = Paymentcmb.getSelectedItem().toString().trim();

        if (status.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Please select a payment status."
            );
            return;
        }

        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        payment.setAppointmentId(appointmentId);
        payment.setAmount(amount);

        
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow >= 0) {
            
            payment.setPaymentDate(null);
        }

        payment.setPaymentMethod(method);
        payment.setPaymentStatus(status);

        if (paymentDAO.updatePayment(payment)) {
            JOptionPane.showMessageDialog(
                this, "Payment updated successfully!"
            );
            loadPayments();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(
                this, "Payment not found."
            );
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(
            this, "Please enter valid numeric values."
        );
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
            this, "Unable to update payment: " + e.getMessage()
        );
    }
}



private void deletePayment() {
    try {
        if (txtPaymentId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Please select a payment from the table first."
            );
            return;
        }

        int paymentId = Integer.parseInt(
            txtPaymentId.getText().trim()
        );

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this payment?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            if (paymentDAO.deletePayment(paymentId)) {
                JOptionPane.showMessageDialog(
                    this, "Payment deleted successfully!"
                );
                loadPayments();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(
                    this, "Payment not found."
                );
            }
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(
            this, "Please select a valid payment."
        );
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
            this, "Unable to delete payment: " + e.getMessage()
        );
    }
}



private void clearFields() {
    txtPaymentId.setText("");
    txtAppointmentID.setText("");
    txtAmount.setText("");

    Cashcmb.setSelectedIndex(0);
    Paymentcmb.setSelectedIndex(0);

    jTable1.clearSelection();
    txtAppointmentID.requestFocusInWindow();
}
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Cashcmb;
    private javax.swing.JComboBox<String> Paymentcmb;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtAppointmentID;
    private javax.swing.JTextField txtPaymentId;
    // End of variables declaration//GEN-END:variables
}
