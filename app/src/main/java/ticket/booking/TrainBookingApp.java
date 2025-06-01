package ticket.booking;

import ticket.booking.gui.LoginPanel;
import ticket.booking.gui.MainFrame;
import ticket.booking.service.UserBookingService;
import ticket.booking.util.DatabaseManager;
import ticket.booking.util.UIThemeManager;

import javax.swing.*;
import java.awt.*;

/**
 * Main entry point for the Train Booking Application
 */
public class TrainBookingApp {
    public static void main(String[] args) {
        // Set application look and feel
        try {
            // Use Nimbus look and feel for a modern appearance
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            // Fall back to system look and feel if Nimbus is not available
            if (!UIManager.getLookAndFeel().getName().equals("Nimbus")) {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }

            // Set custom colors
            UIThemeManager.setupTheme();

            // Initialize the database
            DatabaseManager.initDatabase();

        } catch (Exception e) {
            System.err.println("Could not set look and feel: " + e.getMessage());
        }

        // Start the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Display splash screen
                showSplashScreen();

                // Create user booking service
                UserBookingService userBookingService = new UserBookingService();

                // Create main application frame with the service
                MainFrame mainFrame = new MainFrame(userBookingService);

                // Set the login panel as the content pane
                mainFrame.setContentPane(new LoginPanel(mainFrame, userBookingService));
                mainFrame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Error initializing application: " + e.getMessage(),
                        "Application Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                System.exit(1);
            }
        });
    }

    /**
     * Shows a splash screen for 2 seconds when the application starts
     */
    private static void showSplashScreen() {
        JWindow splashScreen = new JWindow();
        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(BorderFactory.createLineBorder(new Color(41, 128, 185), 2));

        // Create a panel with gradient background
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(41, 128, 185);
                Color color2 = new Color(52, 152, 219);
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        background.setLayout(new BorderLayout());

        // Add title
        JLabel title = new JLabel("Train Booking System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        background.add(title, BorderLayout.CENTER);

        // Add version
        JLabel version = new JLabel("Version 1.0", JLabel.CENTER);
        version.setFont(new Font("Arial", Font.PLAIN, 12));
        version.setForeground(Color.WHITE);
        background.add(version, BorderLayout.SOUTH);

        content.add(background, BorderLayout.CENTER);
        splashScreen.setContentPane(content);
        splashScreen.setSize(400, 300);
        splashScreen.setLocationRelativeTo(null);
        splashScreen.setVisible(true);

        // Close splash after 2 seconds
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                splashScreen.dispose();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}