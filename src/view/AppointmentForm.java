/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import database.DBConnection;
import model.Appointment;
import model.AppointmentDAO;
import model.Pet;
import model.PetDAO;
import model.Veterinarian;
import model.VeterinarianDAO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class AppointmentForm extends javax.swing.JFrame {

    private final AppointmentDAO appointmentDAO = new AppointmentDAO();
    private final PetDAO petDAO = new PetDAO();
    private final VeterinarianDAO vetDAO = new VeterinarianDAO();

    private DefaultTableModel tableModel;
    private List<Appointment> currentAppointmentList = new ArrayList<>();

    
    public static class PetItem {
        private final int petId;
        private final String petName;
        private final String species;

        public PetItem(int petId, String petName, String species) {
            this.petId = petId;
            this.petName = petName;
            this.species = species;
        }

        public int getPetId() {
            return petId;
        }

        public String getPetName() {
            return petName;
        }

        @Override
        public String toString() {
            if (species != null && !species.trim().isEmpty()) {
                return petName + " (ID: " + petId + ", " + species + ")";
            }
            return petName + " (ID: " + petId + ")";
        }
    }

    
    public static class VetItem {
        private final int vetId;
        private final String vetName;
        private final String specialization;

        public VetItem(int vetId, String vetName, String specialization) {
            this.vetId = vetId;
            this.vetName = vetName;
            this.specialization = specialization;
        }

        public int getVetId() {
            return vetId;
        }

        public String getVetName() {
            return vetName;
        }

        @Override
        public String toString() {
            if (specialization != null && !specialization.trim().isEmpty()) {
                return vetName + " (ID: " + vetId + " - " + specialization + ")";
            }
            return vetName + " (ID: " + vetId + ")";
        }
    }

    
    public AppointmentForm() {
        initComponents();
        sideBarPanel1.setActivePage("Appointments");
        initStatusDropdown();
        setupTableProperties();
        loadPetDropdown();
        loadVeterinarianDropdown();
        loadAppointments();
        setLocationRelativeTo(null);
    }

    
    private void initStatusDropdown() {
        if (cmbStatus.getItemCount() == 0) {
            cmbStatus.addItem("Scheduled");
            cmbStatus.addItem("Completed");
            cmbStatus.addItem("Cancelled");
            cmbStatus.addItem("Pending");
        }
    }

    
    private void setupTableProperties() {
        tableModel = (DefaultTableModel) tblAppointments.getModel();

        JTableHeader header = tblAppointments.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(24, 35, 59));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 32));
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        tblAppointments.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblAppointments.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        tblAppointments.getColumnModel().getColumn(1).setPreferredWidth(130);
        tblAppointments.getColumnModel().getColumn(2).setPreferredWidth(170);

        tblAppointments.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblAppointments.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        tblAppointments.getColumnModel().getColumn(4).setPreferredWidth(70);
        tblAppointments.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        tblAppointments.getColumnModel().getColumn(5).setPreferredWidth(260);

        tblAppointments.getColumnModel().getColumn(6).setPreferredWidth(100);
        tblAppointments.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);

        tblAppointments.getColumnModel().getColumn(7).setPreferredWidth(110);
        tblAppointments.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
    }

   
    private void loadPetDropdown() {
        try {
            cmbPet.removeAllItems();
            List<Pet> pets = petDAO.getAllPets();
            for (Pet pet : pets) {
                cmbPet.addItem(new PetItem(pet.getPetId(), pet.getPetName(), pet.getSpecies()));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to load pets from database: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void loadVeterinarianDropdown() {
        try {
            cmbVeterinarian.removeAllItems();
            List<Veterinarian> vets = vetDAO.getAllVeterinarians();
            for (Veterinarian vet : vets) {
                cmbVeterinarian.addItem(new VetItem(vet.getVetId(), vet.getName(), vet.getSpecialization()));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to load veterinarians from database: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadAppointments() {
        try {
            tableModel.setRowCount(0);
            currentAppointmentList = appointmentDAO.getAllAppointments();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            for (Appointment app : currentAppointmentList) {
                String dateStr = "";
                String timeStr = "";
                if (app.getAppointmentDate() != null) {
                    dateStr = dateFormat.format(app.getAppointmentDate());
                    timeStr = timeFormat.format(app.getAppointmentDate());
                }

                tableModel.addRow(new Object[]{
                    app.getAppointmentId(),
                    app.getPetName() != null ? app.getPetName() : "Pet #" + app.getPetId(),
                    app.getVetName() != null ? app.getVetName() : "Vet #" + app.getVetId(),
                    dateStr,
                    timeStr,
                    app.getServiceDescription(),
                    String.format("%.2f", app.getServiceFee()),
                    app.getStatus()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading appointments: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sideBarPanel1 = new view.sideBarPanel();
        pnlRightContent = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblHeaderIcon = new javax.swing.JLabel();
        lblMainHeading = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        lblAdminGreeting = new javax.swing.JLabel();
        pnlMain = new javax.swing.JPanel();
        pnlFormCard = new javax.swing.JPanel();
        lblDetailsHeading = new javax.swing.JLabel();
        lblAppointmentId = new javax.swing.JLabel();
        txtAppointmentId = new javax.swing.JTextField();
        lblPet = new javax.swing.JLabel();
        cmbPet = new javax.swing.JComboBox();
        lblVeterinarian = new javax.swing.JLabel();
        cmbVeterinarian = new javax.swing.JComboBox();
        lblAppointmentDate = new javax.swing.JLabel();
        txtAppointmentDate = new javax.swing.JTextField();
        lblAppointmentTime = new javax.swing.JLabel();
        txtAppointmentTime = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox();
        lblServiceFee = new javax.swing.JLabel();
        txtServiceFee = new javax.swing.JTextField();
        lblDescription = new javax.swing.JLabel();
        jScrollPaneDesc = new javax.swing.JScrollPane();
        txtServiceDescription = new javax.swing.JTextArea();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        lblSearchPrompt = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAppointments = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PawCare - Appointment Management");
        setPreferredSize(new java.awt.Dimension(1280, 800));
        getContentPane().add(sideBarPanel1, java.awt.BorderLayout.WEST);

        pnlRightContent.setLayout(new java.awt.BorderLayout());

        pnlHeader.setBackground(new java.awt.Color(24, 35, 59));
        pnlHeader.setPreferredSize(new java.awt.Dimension(1000, 75));

        lblHeaderIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons8-calendar-16.png"))); // NOI18N

        lblMainHeading.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblMainHeading.setForeground(new java.awt.Color(255, 255, 255));
        lblMainHeading.setText("Appointment Management");

        lblSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSubtitle.setForeground(new java.awt.Color(203, 213, 225));
        lblSubtitle.setText("Schedule, manage, and track veterinary appointments");

        lblAdminGreeting.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAdminGreeting.setForeground(new java.awt.Color(203, 213, 225));
        lblAdminGreeting.setText("Welcome, Admin!");

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblHeaderIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMainHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSubtitle, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAdminGreeting, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHeaderIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlHeaderLayout.createSequentialGroup()
                        .addComponent(lblMainHeading)
                        .addGap(3, 3, 3)
                        .addComponent(lblSubtitle))
                    .addGroup(pnlHeaderLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblAdminGreeting)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pnlRightContent.add(pnlHeader, java.awt.BorderLayout.NORTH);

        pnlMain.setBackground(new java.awt.Color(245, 247, 250));

        pnlFormCard.setBackground(new java.awt.Color(255, 255, 255));

        lblDetailsHeading.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDetailsHeading.setForeground(new java.awt.Color(24, 35, 59));
        lblDetailsHeading.setText("Appointment Details");

        lblAppointmentId.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblAppointmentId.setForeground(new java.awt.Color(24, 35, 59));
        lblAppointmentId.setText("Appointment ID:");

        txtAppointmentId.setEditable(false);
        txtAppointmentId.setBackground(new java.awt.Color(241, 245, 249));
        txtAppointmentId.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtAppointmentId.setForeground(new java.awt.Color(71, 85, 105));

        lblPet.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPet.setForeground(new java.awt.Color(24, 35, 59));
        lblPet.setText("Pet:");

        cmbPet.setBackground(new java.awt.Color(255, 255, 255));
        cmbPet.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        lblVeterinarian.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblVeterinarian.setForeground(new java.awt.Color(24, 35, 59));
        lblVeterinarian.setText("Veterinarian:");

        cmbVeterinarian.setBackground(new java.awt.Color(255, 255, 255));
        cmbVeterinarian.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        lblAppointmentDate.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblAppointmentDate.setForeground(new java.awt.Color(24, 35, 59));
        lblAppointmentDate.setText("Date (YYYY-MM-DD):");

        txtAppointmentDate.setBackground(new java.awt.Color(255, 255, 255));
        txtAppointmentDate.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        lblAppointmentTime.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblAppointmentTime.setForeground(new java.awt.Color(24, 35, 59));
        lblAppointmentTime.setText("Time (HH:MM):");

        txtAppointmentTime.setBackground(new java.awt.Color(255, 255, 255));
        txtAppointmentTime.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        lblStatus.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(24, 35, 59));
        lblStatus.setText("Status:");

        cmbStatus.setBackground(new java.awt.Color(255, 255, 255));
        cmbStatus.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        lblServiceFee.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblServiceFee.setForeground(new java.awt.Color(24, 35, 59));
        lblServiceFee.setText("Service Fee (Rs.):");

        txtServiceFee.setBackground(new java.awt.Color(255, 255, 255));
        txtServiceFee.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        lblDescription.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblDescription.setForeground(new java.awt.Color(24, 35, 59));
        lblDescription.setText("Description:");

        txtServiceDescription.setColumns(20);
        txtServiceDescription.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtServiceDescription.setLineWrap(true);
        txtServiceDescription.setRows(2);
        txtServiceDescription.setWrapStyleWord(true);
        jScrollPaneDesc.setViewportView(txtServiceDescription);

        btnAdd.setBackground(new java.awt.Color(21, 101, 192));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Add Appointment");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnUpdate.setBackground(new java.awt.Color(4, 120, 87));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update Appointment");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnDelete.setBackground(new java.awt.Color(220, 53, 69));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete Appointment");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnClear.setBackground(new java.awt.Color(108, 117, 125));
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear Form");
        btnClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClear.addActionListener(this::btnClearActionPerformed);

        javax.swing.GroupLayout pnlFormCardLayout = new javax.swing.GroupLayout(pnlFormCard);
        pnlFormCard.setLayout(pnlFormCardLayout);
        pnlFormCardLayout.setHorizontalGroup(
            pnlFormCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormCardLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlFormCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDetailsHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFormCardLayout.createSequentialGroup()
                        .addGroup(pnlFormCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAppointmentId)
                            .addComponent(lblAppointmentDate)
                            .addComponent(lblServiceFee))
                        .addGap(15, 15, 15)
                        .addGroup(pnlFormCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAppointmentId, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(txtAppointmentDate)
                            .addComponent(txtServiceFee))
                        .addGap(35, 35, 35)
                        .addGroup(pnlFormCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPet)
                            .addComponent(lblAppointmentTime)
                            .addComponent(lblDescription))
                        .addGap(15, 15, 15)
                        .addGroup(pnlFormCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlFormCardLayout.createSequentialGroup()
                                .addGroup(pnlFormCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbPet, 0, 180, Short.MAX_VALUE)
                                    .addComponent(txtAppointmentTime))
                                .addGap(35, 35, 35)
                                .addGroup(pnlFormCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblVeterinarian)
                                    .addComponent(lblStatus))
                                .addGap(15, 15, 15)
                                .addGroup(pnlFormCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbVeterinarian, 0, 200, Short.MAX_VALUE)
                                    .addComponent(cmbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPaneDesc))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormCardLayout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        pnlFormCardLayout.setVerticalGroup(
            pnlFormCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormCardLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblDetailsHeading)
                .addGap(18, 18, 18)
                .addGroup(pnlFormCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAppointmentId)
                    .addComponent(txtAppointmentId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPet)
                    .addComponent(cmbPet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVeterinarian)
                    .addComponent(cmbVeterinarian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(pnlFormCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAppointmentDate)
                    .addComponent(txtAppointmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAppointmentTime)
                    .addComponent(txtAppointmentTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(pnlFormCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblServiceFee)
                        .addComponent(txtServiceFee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDescription))
                    .addComponent(jScrollPaneDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(pnlFormCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        lblSearchPrompt.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblSearchPrompt.setForeground(new java.awt.Color(24, 35, 59));
        lblSearchPrompt.setText("Search Appointments:");

        txtSearch.setBackground(new java.awt.Color(255, 255, 255));
        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtSearch.addActionListener(this::txtSearchActionPerformed);

        btnSearch.setBackground(new java.awt.Color(41, 98, 255));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons8-search-16.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        btnRefresh.setBackground(new java.awt.Color(71, 85, 105));
        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("Refresh");
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.addActionListener(this::btnRefreshActionPerformed);

        tblAppointments.setBackground(new java.awt.Color(255, 255, 255));
        tblAppointments.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblAppointments.setForeground(new java.awt.Color(24, 35, 59));
        tblAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Pet Name", "Veterinarian", "Date", "Time", "Service Description", "Fee (Rs.)", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAppointments.setRowHeight(28);
        tblAppointments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAppointmentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAppointments);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlFormCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblSearchPrompt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(pnlFormCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchPrompt)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        pnlRightContent.add(pnlMain, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlRightContent, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Appointment appointment = validateAndBuildAppointment(0);
        if (appointment == null) {
            return;
        }

        try {
            boolean isConflict = appointmentDAO.isVetDoubleBooked(
                    appointment.getVetId(),
                    appointment.getAppointmentDate(),
                    0
            );

            if (isConflict) {
                VetItem selectedVet = (VetItem) cmbVeterinarian.getSelectedItem();
                String vetName = selectedVet != null ? selectedVet.getVetName() : "The selected veterinarian";
                JOptionPane.showMessageDialog(this,
                        "Scheduling Conflict:\n" + vetName + " is already booked on "
                        + txtAppointmentDate.getText().trim() + " at " + txtAppointmentTime.getText().trim() + ".\n"
                        + "Please choose a different date, time, or veterinarian.",
                        "Appointment Conflict", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int generatedId = appointmentDAO.addAppointmentAndGetId(appointment);

            if (generatedId > 0) {
                txtAppointmentId.setText(String.valueOf(generatedId));
                JOptionPane.showMessageDialog(this,
                        "Appointment scheduled successfully!\nAppointment ID: #" + generatedId,
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                loadAppointments();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to save appointment. Please check your inputs.",
                        "Save Failed", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database Error while adding appointment: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String idStr = txtAppointmentId.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select an appointment from the table to update.",
                    "No Appointment Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int appointmentId;
        try {
            appointmentId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Appointment ID. Please select an appointment from the table.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Appointment appointment = validateAndBuildAppointment(appointmentId);
        if (appointment == null) {
            return;
        }

        try {
            boolean isConflict = appointmentDAO.isVetDoubleBooked(
                    appointment.getVetId(),
                    appointment.getAppointmentDate(),
                    appointmentId
            );

            if (isConflict) {
                VetItem selectedVet = (VetItem) cmbVeterinarian.getSelectedItem();
                String vetName = selectedVet != null ? selectedVet.getVetName() : "The selected veterinarian";
                JOptionPane.showMessageDialog(this,
                        "Scheduling Conflict:\n" + vetName + " is already booked for another appointment on "
                        + txtAppointmentDate.getText().trim() + " at " + txtAppointmentTime.getText().trim() + ".\n"
                        + "Please choose a different date, time, or veterinarian.",
                        "Appointment Conflict", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean success = appointmentDAO.updateAppointment(appointment);

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "Appointment #" + appointmentId + " updated successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                loadAppointments();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Appointment #" + appointmentId + " could not be updated.",
                        "Update Failed", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database Error while updating appointment: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String idStr = txtAppointmentId.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select an appointment from the table to delete.",
                    "No Appointment Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int appointmentId;
        try {
            appointmentId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Appointment ID. Please select an appointment from the table.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (appointmentDAO.hasPaymentRecords(appointmentId)) {
                JOptionPane.showMessageDialog(this,
                        "Cannot delete Appointment #" + appointmentId + " because payment records are linked to it.\n"
                        + "To preserve accounting records, appointments with payments cannot be deleted.",
                        "Deletion Prevented", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete Appointment #" + appointmentId + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            boolean success = appointmentDAO.deleteAppointment(appointmentId);

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "Appointment #" + appointmentId + " deleted successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                clearForm();
                loadAppointments();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Appointment #" + appointmentId + " could not be deleted.",
                        "Delete Failed", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database Error while deleting appointment: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchAppointments();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        txtSearch.setText("");
        loadPetDropdown();
        loadVeterinarianDropdown();
        loadAppointments();
        clearForm();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        searchAppointments();
    }//GEN-LAST:event_txtSearchActionPerformed

    private void tblAppointmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAppointmentsMouseClicked
        int selectedRow = tblAppointments.getSelectedRow();
        if (selectedRow < 0 || selectedRow >= currentAppointmentList.size()) {
            return;
        }

        Appointment app = currentAppointmentList.get(selectedRow);

        txtAppointmentId.setText(String.valueOf(app.getAppointmentId()));

        for (int i = 0; i < cmbPet.getItemCount(); i++) {
            PetItem item = (PetItem) cmbPet.getItemAt(i);

            if (item != null && item.getPetId() == app.getPetId()) {
                 cmbPet.setSelectedIndex(i);
                 break;
             }
        }

        for (int i = 0; i < cmbVeterinarian.getItemCount(); i++) {
             VetItem item = (VetItem) cmbVeterinarian.getItemAt(i);

            if (item != null && item.getVetId() == app.getVetId()) {
                 cmbVeterinarian.setSelectedIndex(i);
                 break;
             }
        }

        if (app.getAppointmentDate() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            txtAppointmentDate.setText(dateFormat.format(app.getAppointmentDate()));
            txtAppointmentTime.setText(timeFormat.format(app.getAppointmentDate()));
        } else {
            txtAppointmentDate.setText("");
            txtAppointmentTime.setText("");
        }

        txtServiceDescription.setText(app.getServiceDescription() != null ? app.getServiceDescription() : "");
        txtServiceFee.setText(String.format("%.2f", app.getServiceFee()));

        if (app.getStatus() != null && cmbStatus != null) {
            cmbStatus.setSelectedItem(app.getStatus());
        }
    }//GEN-LAST:event_tblAppointmentsMouseClicked

    
    private void clearForm() {
        txtAppointmentId.setText("");
        if (cmbPet != null && cmbPet.getItemCount() > 0) {
            cmbPet.setSelectedIndex(0);
        }
        if (cmbVeterinarian != null && cmbVeterinarian.getItemCount() > 0) {
            cmbVeterinarian.setSelectedIndex(0);
        }
        txtAppointmentDate.setText("");
        txtAppointmentTime.setText("");
        txtServiceDescription.setText("");
        txtServiceFee.setText("");
        if (cmbStatus != null && cmbStatus.getItemCount() > 0) {
            cmbStatus.setSelectedIndex(0);
        }
        txtSearch.setText("");
        if (tblAppointments != null) {
            tblAppointments.clearSelection();
        }
    }

    private void searchAppointments() {
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            loadAppointments();
            return;
        }

        try {
            tableModel.setRowCount(0);
            currentAppointmentList = appointmentDAO.searchAppointments(keyword);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            for (Appointment app : currentAppointmentList) {
                String dateStr = "";
                String timeStr = "";
                if (app.getAppointmentDate() != null) {
                    dateStr = dateFormat.format(app.getAppointmentDate());
                    timeStr = timeFormat.format(app.getAppointmentDate());
                }

                tableModel.addRow(new Object[]{
                    app.getAppointmentId(),
                    app.getPetName() != null ? app.getPetName() : "Pet #" + app.getPetId(),
                    app.getVetName() != null ? app.getVetName() : "Vet #" + app.getVetId(),
                    dateStr,
                    timeStr,
                    app.getServiceDescription(),
                    String.format("%.2f", app.getServiceFee()),
                    app.getStatus()
                });
            }

            if (currentAppointmentList.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No appointments found matching: \"" + keyword + "\"",
                        "Search Results", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database Error while searching: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Appointment validateAndBuildAppointment(int appointmentId) {
        PetItem selectedPet = (PetItem) cmbPet.getSelectedItem();
        if (selectedPet == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a Pet from the dropdown.",
                    "Input Required", JOptionPane.WARNING_MESSAGE);
            cmbPet.requestFocus();
            return null;
        }

        VetItem selectedVet = (VetItem) cmbVeterinarian.getSelectedItem();
        if (selectedVet == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a Veterinarian from the dropdown.",
                    "Input Required", JOptionPane.WARNING_MESSAGE);
            cmbVeterinarian.requestFocus();
            return null;
        }

        String dateStr = txtAppointmentDate.getText().trim();
        if (dateStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter the Appointment Date in format YYYY-MM-DD (e.g., 2026-10-25).",
                    "Input Required", JOptionPane.WARNING_MESSAGE);
            txtAppointmentDate.requestFocus();
            return null;
        }

        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("uuuu-MM-dd"));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Appointment Date format.\nPlease enter a valid date in format YYYY-MM-DD (e.g., 2026-10-25).",
                    "Invalid Date", JOptionPane.WARNING_MESSAGE);
            txtAppointmentDate.requestFocus();
            return null;
        }

        String timeStr = txtAppointmentTime.getText().trim();
        if (timeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter the Appointment Time in format HH:MM (e.g., 10:00 or 14:30).",
                    "Input Required", JOptionPane.WARNING_MESSAGE);
            txtAppointmentTime.requestFocus();
            return null;
        }

        try {
            LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Appointment Time format.\nPlease enter a valid time in 24-hour format HH:MM (00:00 to 23:59).",
                    "Invalid Time", JOptionPane.WARNING_MESSAGE);
            txtAppointmentTime.requestFocus();
            return null;
        }

        String combinedDateTime = dateStr + " " + timeStr + ":00";
        Timestamp appointmentTimestamp;
        try {
            appointmentTimestamp = Timestamp.valueOf(combinedDateTime);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid date and time combination: " + combinedDateTime,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String feeStr = txtServiceFee.getText().trim();
        if (feeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter the Service Fee in rupees (e.g., 1500.00).",
                    "Input Required", JOptionPane.WARNING_MESSAGE);
            txtServiceFee.requestFocus();
            return null;
        }

        double serviceFee;
        try {
            serviceFee = Double.parseDouble(feeStr);
            if (serviceFee < 0) {
                JOptionPane.showMessageDialog(this,
                        "Service Fee cannot be negative.",
                        "Invalid Fee", JOptionPane.WARNING_MESSAGE);
                txtServiceFee.requestFocus();
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Service Fee. Please enter numbers only (e.g., 1500.00).",
                    "Invalid Fee", JOptionPane.WARNING_MESSAGE);
            txtServiceFee.requestFocus();
            return null;
        }

        String description = txtServiceDescription.getText().trim();
        if (description.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a Service Description.",
                    "Input Required", JOptionPane.WARNING_MESSAGE);
            txtServiceDescription.requestFocus();
            return null;
        }

        String status = (String) cmbStatus.getSelectedItem();
        if (status == null || status.trim().isEmpty()) {
            status = "Scheduled";
        }

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(appointmentId);
        appointment.setPetId(selectedPet.getPetId());
        appointment.setVetId(selectedVet.getVetId());
        appointment.setAppointmentDate(appointmentTimestamp);
        appointment.setServiceDescription(description);
        appointment.setServiceFee(serviceFee);
        appointment.setStatus(status);
        appointment.setPetName(selectedPet.getPetName());
        appointment.setVetName(selectedVet.getVetName());

        return appointment;
    }

   
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AppointmentForm.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }

        
        java.awt.EventQueue.invokeLater(() -> new AppointmentForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cmbPet;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JComboBox cmbVeterinarian;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneDesc;
    private javax.swing.JLabel lblAdminGreeting;
    private javax.swing.JLabel lblAppointmentDate;
    private javax.swing.JLabel lblAppointmentId;
    private javax.swing.JLabel lblAppointmentTime;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblDetailsHeading;
    private javax.swing.JLabel lblHeaderIcon;
    private javax.swing.JLabel lblMainHeading;
    private javax.swing.JLabel lblPet;
    private javax.swing.JLabel lblSearchPrompt;
    private javax.swing.JLabel lblServiceFee;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblVeterinarian;
    private javax.swing.JPanel pnlFormCard;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlRightContent;
    private view.sideBarPanel sideBarPanel1;
    private javax.swing.JTable tblAppointments;
    private javax.swing.JTextField txtAppointmentDate;
    private javax.swing.JTextField txtAppointmentId;
    private javax.swing.JTextField txtAppointmentTime;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextArea txtServiceDescription;
    private javax.swing.JTextField txtServiceFee;
    // End of variables declaration//GEN-END:variables
}
