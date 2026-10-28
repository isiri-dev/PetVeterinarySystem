/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Reusable Sidebar Navigation Panel for PawCare Veterinary System.
 * Consistent across Dashboard, Customers, Pets, Veterinarians, Appointments, Payments, and Reports.
 */
public class sideBarPanel extends JPanel {

    private String activePage;
    private final JFrame parentFrame;
    private java.util.function.Consumer<String> navigationHandler;

    private JLabel lblSidebarLogo;
    private JButton btnDashboard;
    private JButton btnCustomer;
    private JButton btnPet;
    private JButton btnVet;
    private JButton btnAppointments;
    private JButton btnPayment;
    private JButton btnReport;
    private JButton btnLogout;

    
    private final Color COLOR_SIDEBAR = new Color(24, 35, 59);     
    private final Color COLOR_BTN_NORMAL = new Color(24, 35, 56);   
    private final Color COLOR_BTN_ACTIVE = new Color(21, 101, 192); 
    private final Color COLOR_BTN_LOGOUT = new Color(220, 76, 76);  

    public sideBarPanel() {
        this("", null);
    }

    public sideBarPanel(String activePage) {
        this(activePage, null);
    }

    public sideBarPanel(String activePage, JFrame parentFrame) {
        this.activePage = activePage != null ? activePage : "";
        this.parentFrame = parentFrame;
        initComponents();
        highlightActivePage();
    }

    private ImageIcon loadIcon(String path) {
        try {
            java.net.URL url = getClass().getResource(path);
            if (url != null) {
                return new ImageIcon(url);
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    private JButton createNavButton(String text, String iconPath) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setForeground(Color.WHITE);
        btn.setBackground(COLOR_BTN_NORMAL);
        btn.setHorizontalAlignment(SwingConstants.LEADING);
        btn.setIconTextGap(16);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));
        btn.setPreferredSize(new Dimension(212, 42));
        btn.setMaximumSize(new Dimension(212, 42));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ImageIcon icon = loadIcon(iconPath);
        if (icon != null) {
            btn.setIcon(icon);
        }
        return btn;
    }

    private void initComponents() {
        setBackground(COLOR_SIDEBAR);
        setPreferredSize(new Dimension(240, 720));
        setMinimumSize(new Dimension(240, 600));

       
        lblSidebarLogo = new JLabel(" PawCare");
        lblSidebarLogo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblSidebarLogo.setForeground(Color.WHITE);
        lblSidebarLogo.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon logoIcon = loadIcon("/view/icons8-paw-48.png");
        if (logoIcon != null) {
            lblSidebarLogo.setIcon(logoIcon);
        }

        
        btnDashboard = createNavButton("Dashboard", "/view/icons8-dashboard-16.png");
        btnDashboard.addActionListener(evt -> navigate("Dashboard"));

        btnCustomer = createNavButton("Customers", "/view/icons8-customers-16.png");
        btnCustomer.addActionListener(evt -> navigate("Customers"));

        btnPet = createNavButton("Pets", "/view/icons8-paw-16.png");
        btnPet.addActionListener(evt -> navigate("Pets"));

        btnVet = createNavButton("Veterinarians", "/view/icons8-stethoscope-16.png");
        btnVet.addActionListener(evt -> navigate("Veterinarians"));

        btnAppointments = createNavButton("Appointments", "/view/icons8-calendar-16.png");
        btnAppointments.addActionListener(evt -> navigate("Appointments"));

        btnPayment = createNavButton("Payments", "/view/icons8-wallet-16.png");
        btnPayment.addActionListener(evt -> navigate("Payments"));

        btnReport = createNavButton("Reports", "/view/icons8-chart-16.png");
        btnReport.addActionListener(evt -> navigate("Reports"));

       
        btnLogout = new JButton("Logout");
        btnLogout.setBackground(COLOR_BTN_LOGOUT);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.setPreferredSize(new Dimension(212, 42));
        btnLogout.setMaximumSize(new Dimension(212, 42));
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogout.addActionListener(evt -> performLogout());

        // GroupLayout layout for sidebar matching DashboardView2
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSidebarLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(btnCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(btnPet, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(btnVet, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(btnAppointments, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(btnPayment, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(btnReport, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblSidebarLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPet, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVet, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAppointments, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }

    public void setActivePage(String activePage) {
        this.activePage = activePage != null ? activePage : "";
        
        btnDashboard.setBackground(COLOR_BTN_NORMAL);
        btnDashboard.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnDashboard.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));

        btnCustomer.setBackground(COLOR_BTN_NORMAL);
        btnCustomer.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnCustomer.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));

        btnPet.setBackground(COLOR_BTN_NORMAL);
        btnPet.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnPet.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));

        btnVet.setBackground(COLOR_BTN_NORMAL);
        btnVet.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnVet.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));

        btnAppointments.setBackground(COLOR_BTN_NORMAL);
        btnAppointments.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnAppointments.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));

        btnPayment.setBackground(COLOR_BTN_NORMAL);
        btnPayment.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnPayment.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));

        btnReport.setBackground(COLOR_BTN_NORMAL);
        btnReport.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnReport.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));

        highlightActivePage();
    }

    private void highlightActivePage() {
        if (activePage.equalsIgnoreCase("Dashboard")) {
            setActiveStyle(btnDashboard);
        } else if (activePage.equalsIgnoreCase("Customers")) {
            setActiveStyle(btnCustomer);
        } else if (activePage.equalsIgnoreCase("Pets")) {
            setActiveStyle(btnPet);
        } else if (activePage.equalsIgnoreCase("Veterinarians") || activePage.equalsIgnoreCase("Vets")) {
            setActiveStyle(btnVet);
        } else if (activePage.equalsIgnoreCase("Appointments")) {
            setActiveStyle(btnAppointments);
        } else if (activePage.equalsIgnoreCase("Payments")) {
            setActiveStyle(btnPayment);
        } else if (activePage.equalsIgnoreCase("Reports")) {
            setActiveStyle(btnReport);
        }
    }

    private void setActiveStyle(JButton btn) {
        btn.setBackground(COLOR_BTN_ACTIVE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 4, 0, 0, Color.WHITE),
            BorderFactory.createEmptyBorder(0, 12, 0, 0)
        ));
    }

    public void setNavigationHandler(java.util.function.Consumer<String> navigationHandler) {
        this.navigationHandler = navigationHandler;
    }

    private void navigate(String target) {
        if (target.equalsIgnoreCase(activePage)) {
            return; 
        }

        
        if (navigationHandler != null) {
            setActivePage(target);
            navigationHandler.accept(target);
            return;
        }

        Window currentWindow = parentFrame != null ? parentFrame : SwingUtilities.getWindowAncestor(this);

        switch (target) {
        
            case "Dashboard" -> {
        DashboardView2 dashboard = new DashboardView2();
        dashboard.setLocationRelativeTo(null);
        dashboard.setVisible(true);
        if (currentWindow != null) currentWindow.dispose();
    }

            case "Customers" -> {
        CustomerForm customerForm = new CustomerForm();
        customerForm.setLocationRelativeTo(null);
        customerForm.setVisible(true);
        if (currentWindow != null) currentWindow.dispose();
    }

             case "Appointments" -> {
        AppointmentForm appointmentForm = new AppointmentForm();
        appointmentForm.setLocationRelativeTo(null);
        appointmentForm.setVisible(true);
        if (currentWindow != null) currentWindow.dispose();
    }

             case "Payments" -> {
        PaymentForm paymentForm = new PaymentForm();
        paymentForm.setLocationRelativeTo(null);
        paymentForm.setVisible(true);
        if (currentWindow != null) currentWindow.dispose();
    }

             case "Pets" -> {
        PetForm petForm = new PetForm();
        petForm.setLocationRelativeTo(null);
        petForm.setVisible(true);
        if (currentWindow != null) currentWindow.dispose();
    }

             case "Veterinarians" -> {
        VeterinarianForm vetForm = new VeterinarianForm();
        vetForm.setLocationRelativeTo(null);
        vetForm.setVisible(true);
        if (currentWindow != null) currentWindow.dispose();
    }

             case "Reports" -> {
        ReportsForm reportsForm = new ReportsForm();
        reportsForm.setLocationRelativeTo(null);
        reportsForm.setVisible(true);
        if (currentWindow != null) currentWindow.dispose();
    }

    default -> {
        JOptionPane.showMessageDialog(this, "Page not found!");
    }
}
   
    }

    private void performLogout() {
        Window currentWindow = parentFrame != null ? parentFrame : SwingUtilities.getWindowAncestor(this);
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            model.UserSession.clear();
            LoginForm loginForm = new LoginForm();
            loginForm.setLocationRelativeTo(null);
            loginForm.setVisible(true);
            if (currentWindow != null) {
                currentWindow.dispose();
            }
        }
    }
}
