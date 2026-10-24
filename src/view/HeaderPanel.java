/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HeaderPanel extends JPanel {

    private JLabel lblPageTitle;
    private JLabel lblSubtitle;
    private JLabel lblUserAvatar;
    private JLabel lblWelcomeGreeting;
    private JLabel lblTodayDate;

    private String currentPageTitle;
    private String currentRole;

    private static final Color COLOR_NAVY = new Color(27, 42, 73);      
    private static final Color COLOR_GRAY = new Color(100, 116, 139);   
    private static final Color COLOR_TEAL = new Color(13, 148, 136);    

    
    public HeaderPanel(String pageTitle, String role) {
        this.currentPageTitle = pageTitle != null ? pageTitle : "Dashboard";
        this.currentRole = role != null ? role : "Admin";
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1000, 75));
        setMinimumSize(new Dimension(600, 75));

        
        setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, COLOR_TEAL));

       
        JPanel pnlLeft = new JPanel();
        pnlLeft.setOpaque(false);
        pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
        pnlLeft.setBorder(BorderFactory.createEmptyBorder(14, 25, 14, 10));

        lblPageTitle = new JLabel(currentPageTitle);
        lblPageTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblPageTitle.setForeground(COLOR_NAVY);

        lblSubtitle = new JLabel("Pet Veterinary Management System");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitle.setForeground(COLOR_GRAY);

        pnlLeft.add(lblPageTitle);
        pnlLeft.add(Box.createVerticalStrut(2));
        pnlLeft.add(lblSubtitle);

        add(pnlLeft, BorderLayout.WEST);

       
        JPanel pnlRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 16));
        pnlRight.setOpaque(false);
        pnlRight.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));

        
        lblUserAvatar = new JLabel();
        ImageIcon userIcon = createCircularUserIcon("/view/icons8-user-64.png", 42);
        if (userIcon != null) {
            lblUserAvatar.setIcon(userIcon);
        }

        
        JPanel pnlUserText = new JPanel();
        pnlUserText.setOpaque(false);
        pnlUserText.setLayout(new BoxLayout(pnlUserText, BoxLayout.Y_AXIS));

        lblWelcomeGreeting = new JLabel(formatWelcomeGreeting(currentRole));
        lblWelcomeGreeting.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblWelcomeGreeting.setForeground(COLOR_NAVY);

        String dateString = new SimpleDateFormat("EEE, dd MMM yyyy").format(new Date());
        lblTodayDate = new JLabel(dateString);
        lblTodayDate.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblTodayDate.setForeground(COLOR_GRAY);

        pnlUserText.add(lblWelcomeGreeting);
        pnlUserText.add(Box.createVerticalStrut(2));
        pnlUserText.add(lblTodayDate);

        pnlRight.add(lblUserAvatar);
        pnlRight.add(pnlUserText);

        add(pnlRight, BorderLayout.EAST);
    }

    
    public void setPageTitle(String pageTitle) {
        this.currentPageTitle = pageTitle != null ? pageTitle : "Dashboard";
        if (lblPageTitle != null) {
            lblPageTitle.setText(currentPageTitle);
        }
    }

    
    public void setRole(String role) {
        this.currentRole = role != null ? role : "Admin";
        if (lblWelcomeGreeting != null) {
            lblWelcomeGreeting.setText(formatWelcomeGreeting(currentRole));
        }
    }

    private String formatWelcomeGreeting(String role) {
        if (role != null && role.trim().equalsIgnoreCase("Veterinarian")) {
            return "Welcome, Veterinarian!";
        }
        return "Welcome, Admin!";
    }

    
    private ImageIcon createCircularUserIcon(String resourcePath, int size) {
        try {
            java.net.URL url = getClass().getResource(resourcePath);
            if (url == null) {
                return null;
            }
            ImageIcon rawIcon = new ImageIcon(url);
            Image rawImg = rawIcon.getImage();

            BufferedImage output = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = output.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            
            g2.setColor(new Color(241, 245, 249));
            g2.fill(new Ellipse2D.Double(0, 0, size, size));

            
            g2.setClip(new Ellipse2D.Double(2, 2, size - 4, size - 4));
            g2.drawImage(rawImg, 4, 4, size - 8, size - 8, null);

            
            g2.setClip(null);
            g2.setColor(new Color(203, 213, 225));
            g2.draw(new Ellipse2D.Double(1, 1, size - 2, size - 2));

            g2.dispose();
            return new ImageIcon(output);
        } catch (Exception e) {
            return null;
        }
    }
}
