/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.DashboardDAO;
import javax.swing.JOptionPane;


/**
 *
 * @author LENOVO
 */
public class DashboardView2 extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DashboardView2.class.getName());

    
   public DashboardView2() {
    initComponents();
    model.AppConfig config = model.AppConfig.getInstance();

    System.out.println(config.getApplicationName()
        + " Version " + config.getVersion());
    setupSidebarNavigation();
    setLocationRelativeTo(null);
    loadDashboardData();
    loadUpcomingAppointments();
}

    private void setupSidebarNavigation() {
        btnDashboard.setBackground(new java.awt.Color(21, 101, 192));
        btnDashboard.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        btnDashboard.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, java.awt.Color.WHITE),
            javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 0)
        ));

        btnVet.addActionListener(evt -> {
            new VeterinarianForm().setVisible(true);
            this.dispose();
        });

       btnReport.addActionListener((var evt) -> {
        ReportsForm reportsForm = new ReportsForm();
        reportsForm.setLocationRelativeTo(null);
        reportsForm.setVisible(true);
        this.dispose();
        
        });

        btnLogout.addActionListener(evt -> {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE
            );
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                LoginForm login = new LoginForm();
                login.setLocationRelativeTo(null);
                login.setVisible(true);
                this.dispose();
            }
        });
    }
   
private void loadDashboardData() {

    DashboardDAO dao = new DashboardDAO();

    lblCustomersCount.setText(
        String.valueOf(dao.getTotalCustomers())
    );

    lblPetsCount.setText(
        String.valueOf(dao.getTotalPets())
    );

    lblVetCount.setText(
        String.valueOf(dao.getTotalVeterinarians())
    );

    lblAppCount.setText(
        String.valueOf(dao.getTodaysAppointments())
    );
}

private void loadUpcomingAppointments() {

    controller.DashboardDAO dao = new controller.DashboardDAO();

    java.util.List<Object[]> appointments =
            dao.getUpcomingAppointments();

    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel)
            tblUpcomingAppointments.getModel();

    model.setRowCount(0);

    for (Object[] appointment : appointments) {
        model.addRow(appointment);
    }
}
   

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnlContent = new javax.swing.JPanel();
        pnlSidebar = new javax.swing.JPanel();
        lblSidebarLogo = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnCustomer = new javax.swing.JButton();
        btnPet = new javax.swing.JButton();
        btnVet = new javax.swing.JButton();
        btnAppointments = new javax.swing.JButton();
        btnPayment = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        pnlCustomersCard = new javax.swing.JPanel();
        lblCustomersTitle = new javax.swing.JLabel();
        lblCustomersCount = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnlPetscard = new javax.swing.JPanel();
        lblPetsTitle = new javax.swing.JLabel();
        lblPetsCount = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pnlVetCard = new javax.swing.JPanel();
        lblVetTitle = new javax.swing.JLabel();
        lblVetCount = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pnlAppointment = new javax.swing.JPanel();
        lblAppointments = new javax.swing.JLabel();
        lblAppCount = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnlUpcomingAppointments = new javax.swing.JPanel();
        lblUpcomingTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUpcomingAppointments = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1100, 700));

        pnlMain.setLayout(new java.awt.BorderLayout());

        pnlHeader.setBackground(new java.awt.Color(204, 204, 204));
        pnlHeader.setPreferredSize(new java.awt.Dimension(880, 70));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(24, 35, 56));
        lblTitle.setText("Pet Veterinary Management System");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(100, 116, 139));
        jLabel1.setText("Welcome !");

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap(514, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle)
                    .addComponent(jLabel1))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pnlMain.add(pnlHeader, java.awt.BorderLayout.PAGE_START);

        pnlContent.setBackground(new java.awt.Color(245, 247, 250));

        pnlSidebar.setBackground(new java.awt.Color(24, 35, 59));
        pnlSidebar.setPreferredSize(new java.awt.Dimension(100, 220));

        lblSidebarLogo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSidebarLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblSidebarLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSidebarLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons8-paw-48.png"))); // NOI18N
        lblSidebarLogo.setText(" PawCare");

        btnDashboard.setBackground(new java.awt.Color(24, 35, 56));
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons8-dashboard-16.png"))); // NOI18N
        btnDashboard.setText("Dashboard");
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnDashboard.setIconTextGap(18);
        btnDashboard.setPreferredSize(new java.awt.Dimension(215, 42));

        btnCustomer.setBackground(new java.awt.Color(24, 35, 56));
        btnCustomer.setForeground(new java.awt.Color(255, 255, 255));
        btnCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons8-customers-16.png"))); // NOI18N
        btnCustomer.setText("Customers");
        btnCustomer.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnCustomer.setIconTextGap(18);
        btnCustomer.setPreferredSize(new java.awt.Dimension(215, 42));
        btnCustomer.addActionListener(this::btnCustomerActionPerformed);

        btnPet.setBackground(new java.awt.Color(24, 35, 56));
        btnPet.setForeground(new java.awt.Color(255, 255, 255));
        btnPet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons8-paw-16.png"))); // NOI18N
        btnPet.setText("Pets");
        btnPet.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnPet.setIconTextGap(18);
        btnPet.setPreferredSize(new java.awt.Dimension(215, 42));
        btnPet.addActionListener(this::btnPetActionPerformed);

        btnVet.setBackground(new java.awt.Color(24, 35, 56));
        btnVet.setForeground(new java.awt.Color(255, 255, 255));
        btnVet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons8-stethoscope-16.png"))); // NOI18N
        btnVet.setText("Veterinarians");
        btnVet.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnVet.setIconTextGap(18);
        btnVet.setPreferredSize(new java.awt.Dimension(215, 42));

        btnAppointments.setBackground(new java.awt.Color(24, 35, 56));
        btnAppointments.setForeground(new java.awt.Color(255, 255, 255));
        btnAppointments.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons8-calendar-16.png"))); // NOI18N
        btnAppointments.setText("Appointments");
        btnAppointments.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnAppointments.setIconTextGap(18);
        btnAppointments.setPreferredSize(new java.awt.Dimension(215, 42));
        btnAppointments.addActionListener(this::btnAppointmentsActionPerformed);

        btnPayment.setBackground(new java.awt.Color(24, 35, 56));
        btnPayment.setForeground(new java.awt.Color(255, 255, 255));
        btnPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons8-wallet-16.png"))); // NOI18N
        btnPayment.setText("Payments");
        btnPayment.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnPayment.setIconTextGap(18);
        btnPayment.setPreferredSize(new java.awt.Dimension(215, 42));
        btnPayment.addActionListener(this::btnPaymentActionPerformed);

        btnReport.setBackground(new java.awt.Color(24, 35, 56));
        btnReport.setForeground(new java.awt.Color(255, 255, 255));
        btnReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons8-chart-16.png"))); // NOI18N
        btnReport.setText("Reports");
        btnReport.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnReport.setIconTextGap(18);
        btnReport.setPreferredSize(new java.awt.Dimension(215, 42));

        btnLogout.setBackground(new java.awt.Color(220, 76, 76));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.setPreferredSize(new java.awt.Dimension(215, 42));

        javax.swing.GroupLayout pnlSidebarLayout = new javax.swing.GroupLayout(pnlSidebar);
        pnlSidebar.setLayout(pnlSidebarLayout);
        pnlSidebarLayout.setHorizontalGroup(
            pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSidebarLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSidebarLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAppointments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        pnlSidebarLayout.setVerticalGroup(
            pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSidebarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblSidebarLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(btnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(btnPet, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(btnVet, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(btnAppointments, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(btnPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCustomersCard.setBackground(new java.awt.Color(227, 242, 253));
        pnlCustomersCard.setPreferredSize(new java.awt.Dimension(200, 120));

        lblCustomersTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCustomersTitle.setForeground(new java.awt.Color(24, 35, 56));
        lblCustomersTitle.setText("Total Customers");

        lblCustomersCount.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblCustomersCount.setForeground(new java.awt.Color(21, 101, 192));
        lblCustomersCount.setText(" 0");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons8-user-64 (1).png"))); // NOI18N
        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout pnlCustomersCardLayout = new javax.swing.GroupLayout(pnlCustomersCard);
        pnlCustomersCard.setLayout(pnlCustomersCardLayout);
        pnlCustomersCardLayout.setHorizontalGroup(
            pnlCustomersCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCustomersCardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCustomersCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCustomersTitle)
                    .addGroup(pnlCustomersCardLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblCustomersCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        pnlCustomersCardLayout.setVerticalGroup(
            pnlCustomersCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCustomersCardLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(56, 56, 56))
            .addGroup(pnlCustomersCardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCustomersTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCustomersCount)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlPetscard.setBackground(new java.awt.Color(209, 250, 229));
        pnlPetscard.setPreferredSize(new java.awt.Dimension(200, 120));

        lblPetsTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPetsTitle.setForeground(new java.awt.Color(24, 35, 56));
        lblPetsTitle.setText("Total Pets");

        lblPetsCount.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblPetsCount.setForeground(new java.awt.Color(4, 120, 87));
        lblPetsCount.setText(" 0");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/paw - Copy (1) (1).png"))); // NOI18N
        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout pnlPetscardLayout = new javax.swing.GroupLayout(pnlPetscard);
        pnlPetscard.setLayout(pnlPetscardLayout);
        pnlPetscardLayout.setHorizontalGroup(
            pnlPetscardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPetscardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPetscardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPetsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlPetscardLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblPetsCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlPetscardLayout.setVerticalGroup(
            pnlPetscardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPetscardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPetscardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(pnlPetscardLayout.createSequentialGroup()
                        .addComponent(lblPetsTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPetsCount)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pnlVetCard.setBackground(new java.awt.Color(237, 233, 254));
        pnlVetCard.setPreferredSize(new java.awt.Dimension(200, 120));

        lblVetTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblVetTitle.setForeground(new java.awt.Color(24, 35, 56));
        lblVetTitle.setText("Total Veterinarians");

        lblVetCount.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblVetCount.setForeground(new java.awt.Color(109, 40, 217));
        lblVetCount.setText(" 0");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons8-stethoscope-50 (1).png"))); // NOI18N
        jLabel13.setText("jLabel13");

        javax.swing.GroupLayout pnlVetCardLayout = new javax.swing.GroupLayout(pnlVetCard);
        pnlVetCard.setLayout(pnlVetCardLayout);
        pnlVetCardLayout.setHorizontalGroup(
            pnlVetCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVetCardLayout.createSequentialGroup()
                .addGroup(pnlVetCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVetCardLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblVetTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlVetCardLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblVetCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        pnlVetCardLayout.setVerticalGroup(
            pnlVetCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVetCardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlVetCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVetCardLayout.createSequentialGroup()
                        .addComponent(lblVetTitle)
                        .addGap(18, 18, 18)
                        .addComponent(lblVetCount))
                    .addComponent(jLabel13))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pnlAppointment.setBackground(new java.awt.Color(255, 237, 213));
        pnlAppointment.setPreferredSize(new java.awt.Dimension(200, 120));

        lblAppointments.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAppointments.setForeground(new java.awt.Color(24, 35, 56));
        lblAppointments.setText("Today's Appointments");

        lblAppCount.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblAppCount.setForeground(new java.awt.Color(194, 65, 12));
        lblAppCount.setText(" 0");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/calendar (1) (1) (1).png"))); // NOI18N
        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout pnlAppointmentLayout = new javax.swing.GroupLayout(pnlAppointment);
        pnlAppointment.setLayout(pnlAppointmentLayout);
        pnlAppointmentLayout.setHorizontalGroup(
            pnlAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAppointmentLayout.createSequentialGroup()
                .addGroup(pnlAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAppointmentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAppointments, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAppointmentLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lblAppCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlAppointmentLayout.setVerticalGroup(
            pnlAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAppointmentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lblAppointments))
                .addGap(7, 7, 7)
                .addComponent(lblAppCount)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pnlUpcomingAppointments.setBackground(new java.awt.Color(255, 255, 255));
        pnlUpcomingAppointments.setPreferredSize(new java.awt.Dimension(820, 300));

        lblUpcomingTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblUpcomingTitle.setForeground(new java.awt.Color(24, 35, 56));
        lblUpcomingTitle.setText("Upcoming Appointments");

        tblUpcomingAppointments.setBackground(new java.awt.Color(255, 255, 255));
        tblUpcomingAppointments.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblUpcomingAppointments.setForeground(new java.awt.Color(24, 35, 56));
        tblUpcomingAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Appointment ID", "Pet Name", "Customer", "Veterinarian", "Date & Time"
            }
        ));
        tblUpcomingAppointments.setGridColor(new java.awt.Color(229, 231, 235));
        tblUpcomingAppointments.setIntercellSpacing(new java.awt.Dimension(1, 1));
        tblUpcomingAppointments.setPreferredSize(new java.awt.Dimension(780, 220));
        tblUpcomingAppointments.setRowHeight(30);
        tblUpcomingAppointments.setSelectionBackground(new java.awt.Color(227, 242, 253));
        tblUpcomingAppointments.setSelectionForeground(new java.awt.Color(24, 35, 56));
        jScrollPane1.setViewportView(tblUpcomingAppointments);

        javax.swing.GroupLayout pnlUpcomingAppointmentsLayout = new javax.swing.GroupLayout(pnlUpcomingAppointments);
        pnlUpcomingAppointments.setLayout(pnlUpcomingAppointmentsLayout);
        pnlUpcomingAppointmentsLayout.setHorizontalGroup(
            pnlUpcomingAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpcomingAppointmentsLayout.createSequentialGroup()
                .addGroup(pnlUpcomingAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUpcomingAppointmentsLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lblUpcomingTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlUpcomingAppointmentsLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlUpcomingAppointmentsLayout.setVerticalGroup(
            pnlUpcomingAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpcomingAppointmentsLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblUpcomingTitle)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addComponent(pnlSidebar, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlCustomersCard, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                            .addComponent(pnlVetCard, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                        .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlPetscard, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                            .addComponent(pnlAppointment, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
                        .addGap(38, 38, 38))
                    .addComponent(pnlUpcomingAppointments, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE))
                .addGap(59, 59, 59))
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSidebar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlCustomersCard, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(pnlPetscard, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                .addGap(49, 49, 49)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlVetCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(pnlUpcomingAppointments, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMain.add(pnlContent, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetActionPerformed
        
        PetForm petForm = new PetForm();
        petForm.setLocationRelativeTo(null);
        petForm.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btnPetActionPerformed

    private void btnAppointmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAppointmentsActionPerformed
        AppointmentForm form = new AppointmentForm();
        form.setLocationRelativeTo(null);
        form.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAppointmentsActionPerformed

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        PaymentForm form = new PaymentForm();
        form.setLocationRelativeTo(null);
        form.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPaymentActionPerformed

    private void btnCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerActionPerformed
         CustomerForm form = new CustomerForm();
         form.setLocationRelativeTo(null);
         form.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_btnCustomerActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new DashboardView2().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAppointments;
    private javax.swing.JButton btnCustomer;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPayment;
    private javax.swing.JButton btnPet;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnVet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAppCount;
    private javax.swing.JLabel lblAppointments;
    private javax.swing.JLabel lblCustomersCount;
    private javax.swing.JLabel lblCustomersTitle;
    private javax.swing.JLabel lblPetsCount;
    private javax.swing.JLabel lblPetsTitle;
    private javax.swing.JLabel lblSidebarLogo;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUpcomingTitle;
    private javax.swing.JLabel lblVetCount;
    private javax.swing.JLabel lblVetTitle;
    private javax.swing.JPanel pnlAppointment;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlCustomersCard;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlPetscard;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlUpcomingAppointments;
    private javax.swing.JPanel pnlVetCard;
    private javax.swing.JTable tblUpcomingAppointments;
    // End of variables declaration//GEN-END:variables
}
