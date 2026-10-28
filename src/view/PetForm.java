/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Customer;
import model.CustomerDAO;
import model.Pet;
import model.PetDAO;


public class PetForm extends javax.swing.JFrame {

    private final PetDAO petDAO = new PetDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private DefaultTableModel tableModel;
    private final Map<Integer, String> customerNameCache = new HashMap<>();
    
    private void setupSidebar() {

    getContentPane().removeAll();

    getContentPane().setLayout(new java.awt.BorderLayout());

    sideBarPanel sidebar = new sideBarPanel("Pets", this);

    getContentPane().add(sidebar, java.awt.BorderLayout.WEST);

    javax.swing.JPanel rightPanel =
            new javax.swing.JPanel(new java.awt.BorderLayout());

    rightPanel.add(jPanel1, java.awt.BorderLayout.NORTH);

    rightPanel.add(pnlMain, java.awt.BorderLayout.CENTER);

    getContentPane().add(rightPanel, java.awt.BorderLayout.CENTER);

    getContentPane().revalidate();

    getContentPane().repaint();
    
    }
    
    public static class CustomerItem {
        private final int customerId;
        private final String customerName;

        public CustomerItem(int customerId, String customerName) {
            this.customerId = customerId;
            this.customerName = customerName;
        }

        public int getCustomerId() {
            return customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        @Override
        public String toString() {
            return customerName + " (ID: " + customerId + ")";
        }
    }

    public PetForm() {
        initComponents();
        setupVisuals();
        setupSidebar();
        loadCustomerDropdown();
        loadPets();
        setSize(1280, 800);
        setLocationRelativeTo(null);
    }

    
    public JPanel getContentPanel() {
        return pnlMain;
    }

    private void setupVisuals() {
    tableModel = (DefaultTableModel) tblPets.getModel();

    JTableHeader header = tblPets.getTableHeader();
    header.setFont(new Font("Segoe UI", Font.BOLD, 13));
    header.setBackground(new Color(24, 35, 59));
    header.setForeground(Color.WHITE);
    header.setPreferredSize(
            new Dimension(header.getPreferredSize().width, 32)
    );
    header.setReorderingAllowed(false);

    tblPets.setRowHeight(28);
    tblPets.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    tblPets.setBackground(Color.WHITE);
    tblPets.setForeground(new Color(24, 35, 59));
    tblPets.setSelectionBackground(new Color(41, 98, 255));
    tblPets.setSelectionForeground(Color.WHITE);

    btnAdd.addActionListener(evt -> addPet());
    btnUpdate.addActionListener(evt -> updatePet());
    btnDelete.addActionListener(evt -> deletePet());
    btnClear.addActionListener(evt -> clearForm());
    btnSearch.addActionListener(evt -> searchPets());

    btnRefresh.addActionListener(evt -> {
        txtSearch.setText("");
        loadCustomerDropdown();
        loadPets();
        clearForm();
    });

    txtSearch.addActionListener(evt -> searchPets());

    tblPets.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblPetsMouseClicked(evt);
        }
    });
}

    private void loadCustomerDropdown() {
        cmbCustomer.removeAllItems();
        customerNameCache.clear();
        try {
            List<Customer> customers = customerDAO.getAllCustomers();
            for (Customer c : customers) {
                customerNameCache.put(c.getCustomerId(), c.getName());
                cmbCustomer.addItem(new CustomerItem(c.getCustomerId(), c.getName()));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to load customer list: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadPets() {
        try {
            tableModel.setRowCount(0);
            List<Pet> pets = petDAO.getAllPets();
            for (Pet p : pets) {
                String ownerName = customerNameCache.getOrDefault(p.getCustomerId(), "ID: " + p.getCustomerId());
                tableModel.addRow(new Object[]{
                    p.getPetId(),
                    p.getPetName(),
                    p.getSpecies(),
                    p.getBreed(),
                    p.getAge(),
                    ownerName
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to load pets: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchPets() {
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            loadPets();
            return;
        }

        try {
            tableModel.setRowCount(0);
            List<Pet> pets = petDAO.searchPets(keyword);
            for (Pet p : pets) {
                String ownerName = customerNameCache.getOrDefault(p.getCustomerId(), "ID: " + p.getCustomerId());
                tableModel.addRow(new Object[]{
                    p.getPetId(),
                    p.getPetName(),
                    p.getSpecies(),
                    p.getBreed(),
                    p.getAge(),
                    ownerName
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Search failed: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addPet() {
        String name = txtPetName.getText().trim();
        String species = cmbSpecies.getSelectedItem() != null ? cmbSpecies.getSelectedItem().toString() : "";
        String breed = txtBreed.getText().trim();
        String ageStr = txtAge.getText().trim();

        if (name.isEmpty() || breed.isEmpty() || ageStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all pet details (Name, Breed, and Age).",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

       int age;

try {
age = Integer.parseInt(ageStr);


    if (age < 0 || age > 100) {
    throw new exception.InvalidAgeException(
            "Please enter a realistic age between 0 and 100.");
}


    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
        "Age must be a valid integer number.",
        "Validation Error",
        JOptionPane.WARNING_MESSAGE);
        return;

    } catch (exception.InvalidAgeException e) {
        JOptionPane.showMessageDialog(this,
        e.getMessage(),
        "Validation Error",
        JOptionPane.WARNING_MESSAGE);
        return;
}


        CustomerItem selectedCustomer = (CustomerItem) cmbCustomer.getSelectedItem();
        if (selectedCustomer == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a customer/owner for this pet.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Pet pet = new Pet(0, name, species, breed, age, selectedCustomer.getCustomerId());

        try {
            if (petDAO.addPet(pet)) {
                JOptionPane.showMessageDialog(this,
                        "Pet '" + name + "' added successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                loadPets();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Could not add pet record.",
                        "Operation Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database error while adding pet: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePet() {
        String idStr = txtPetId.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select a pet from the table to update.",
                    "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int petId = Integer.parseInt(idStr);
        String name = txtPetName.getText().trim();
        String species = cmbSpecies.getSelectedItem() != null ? cmbSpecies.getSelectedItem().toString() : "";
        String breed = txtBreed.getText().trim();
        String ageStr = txtAge.getText().trim();

        if (name.isEmpty() || breed.isEmpty() || ageStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all required fields.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
            if (age < 0 || age > 100) {
                JOptionPane.showMessageDialog(this,
                        "Age must be between 0 and 100.",
                        "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Age must be a valid number.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        CustomerItem selectedCustomer = (CustomerItem) cmbCustomer.getSelectedItem();
        if (selectedCustomer == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a customer/owner.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Pet pet = new Pet(petId, name, species, breed, age, selectedCustomer.getCustomerId());

        try {
            if (petDAO.updatePet(pet)) {
                JOptionPane.showMessageDialog(this,
                        "Pet #" + petId + " updated successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                loadPets();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to update pet record.",
                        "Operation Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database error while updating pet: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletePet() {
        String idStr = txtPetId.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select a pet from the table to delete.",
                    "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int petId = Integer.parseInt(idStr);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete Pet #" + petId + "?\nThis action cannot be undone.",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            if (petDAO.deletePet(petId)) {
                JOptionPane.showMessageDialog(this,
                        "Pet #" + petId + " deleted successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                loadPets();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to delete pet record.",
                        "Operation Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Cannot delete this pet because appointments or medical records are linked to it.\nDetails: " + e.getMessage(),
                    "Deletion Prevented", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearForm() {
        txtPetId.setText("");
        txtPetName.setText("");
        txtBreed.setText("");
        txtAge.setText("");
        if (cmbSpecies.getItemCount() > 0) {
            cmbSpecies.setSelectedIndex(0);
        }
        if (cmbCustomer.getItemCount() > 0) {
            cmbCustomer.setSelectedIndex(0);
        }
        txtSearch.setText("");
        tblPets.clearSelection();
    }

    private void tblPetsMouseClicked(java.awt.event.MouseEvent evt) {
        int row = tblPets.getSelectedRow();
        if (row < 0) {
            return;
        }

        txtPetId.setText(tblPets.getValueAt(row, 0).toString());
        txtPetName.setText(tblPets.getValueAt(row, 1).toString());

        String species = tblPets.getValueAt(row, 2).toString();

        for (int i = 0; i < cmbSpecies.getItemCount(); i++) {
        if (cmbSpecies.getItemAt(i).toString().equalsIgnoreCase(species)) {
        cmbSpecies.setSelectedIndex(i);
        break;
    }
}

        txtBreed.setText(tblPets.getValueAt(row, 3) != null ? tblPets.getValueAt(row, 3).toString() : "");
        txtAge.setText(tblPets.getValueAt(row, 4) != null ? tblPets.getValueAt(row, 4).toString() : "");

        String ownerCol = tblPets.getValueAt(row, 5).toString();
        for (int i = 0; i < cmbCustomer.getItemCount(); i++) {
            CustomerItem item = (CustomerItem) cmbCustomer.getItemAt(i);
            if (item != null && (ownerCol.contains(item.getCustomerName()) || ownerCol.contains(String.valueOf(item.getCustomerId())))) {
                cmbCustomer.setSelectedIndex(i);
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlCard = new javax.swing.JPanel();
        lblCardTitle = new javax.swing.JLabel();
        lblPetId = new javax.swing.JLabel();
        txtPetId = new javax.swing.JTextField();
        lblPetName = new javax.swing.JLabel();
        txtPetName = new javax.swing.JTextField();
        lblAge = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        lblSpecies = new javax.swing.JLabel();
        cmbSpecies = new javax.swing.JComboBox();
        lblBreed = new javax.swing.JLabel();
        txtBreed = new javax.swing.JTextField();
        lblCustomer = new javax.swing.JLabel();
        cmbCustomer = new javax.swing.JComboBox();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPets = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PawCare - Pet Management");
        setPreferredSize(new java.awt.Dimension(1280, 800));

        pnlMain.setBackground(new java.awt.Color(245, 247, 250));

        pnlCard.setBackground(new java.awt.Color(255, 255, 255));

        lblCardTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblCardTitle.setForeground(new java.awt.Color(24, 35, 59));
        lblCardTitle.setText("Pet Information");

        lblPetId.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPetId.setText("Pet ID:");

        txtPetId.setEditable(false);
        txtPetId.setBackground(new java.awt.Color(241, 245, 249));

        lblPetName.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPetName.setText("Pet Name:");

        lblAge.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblAge.setText("Age (Years):");

        lblSpecies.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSpecies.setText("Species:");

        cmbSpecies.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dog", "Cat", "Bird", "Rabbit", "Hamster", "Other" }));

        lblBreed.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblBreed.setText("Breed:");

        lblCustomer.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblCustomer.setText("Owner / Customer:");

        btnAdd.setBackground(new java.awt.Color(21, 101, 192));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Add Pet");

        btnUpdate.setBackground(new java.awt.Color(4, 120, 87));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update Pet");

        btnDelete.setBackground(new java.awt.Color(220, 53, 69));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete Pet");

        btnClear.setBackground(new java.awt.Color(108, 117, 125));
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear Form");

        javax.swing.GroupLayout pnlCardLayout = new javax.swing.GroupLayout(pnlCard);
        pnlCard.setLayout(pnlCardLayout);
        pnlCardLayout.setHorizontalGroup(
            pnlCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCardLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCardTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCardLayout.createSequentialGroup()
                        .addGroup(pnlCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPetId)
                            .addComponent(lblSpecies))
                        .addGap(20, 20, 20)
                        .addGroup(pnlCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPetId, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(cmbSpecies, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(pnlCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPetName)
                            .addComponent(lblBreed))
                        .addGap(20, 20, 20)
                        .addGroup(pnlCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlCardLayout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addComponent(txtPetName, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                            .addComponent(txtBreed))
                        .addGap(35, 35, 35)
                        .addGroup(pnlCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAge)
                            .addComponent(lblCustomer))
                        .addGap(20, 20, 20)
                        .addGroup(pnlCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCustomer, 0, 260, Short.MAX_VALUE))))
                .addContainerGap(263, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCardLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCardLayout.setVerticalGroup(
            pnlCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCardLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblCardTitle)
                .addGap(18, 18, 18)
                .addGroup(pnlCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPetId)
                    .addComponent(txtPetId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPetName)
                    .addComponent(txtPetName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAge)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(pnlCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSpecies)
                    .addComponent(cmbSpecies, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBreed)
                    .addComponent(txtBreed, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCustomer)
                    .addComponent(cmbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnlCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        lblSearch.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblSearch.setText("Search Pet:");

        btnSearch.setBackground(new java.awt.Color(21, 101, 192));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search");

        btnRefresh.setBackground(new java.awt.Color(108, 117, 125));
        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("Refresh");

        tblPets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pet ID", "Pet Name", "Species", "Breed", "Age", "Owner / Customer"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPets);

        jPanel1.setBackground(new java.awt.Color(24, 35, 59));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 75));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons8-pets-50 (1).png"))); // NOI18N
        jLabel1.setText("Pet Management");
        jLabel1.setIconTextGap(15);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(928, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearch)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new PetForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cmbCustomer;
    private javax.swing.JComboBox cmbSpecies;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblBreed;
    private javax.swing.JLabel lblCardTitle;
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblPetId;
    private javax.swing.JLabel lblPetName;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSpecies;
    private javax.swing.JPanel pnlCard;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTable tblPets;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtBreed;
    private javax.swing.JTextField txtPetId;
    private javax.swing.JTextField txtPetName;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
