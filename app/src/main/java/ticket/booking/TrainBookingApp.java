package ticket.booking;

import ticket.booking.gui.LoginPanel;
import ticket.booking.gui.MainFrame;

import javax.swing.*;
import java.io.IOException;

/**
 * Main entry point for the Train Booking Application
 */
public class TrainBookingApp {
    public static void main(String[] args) {
        try {
            // Set System look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Could not set system look and feel: " + e.getMessage());
        }

        // Start the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Create main application frame
                MainFrame mainFrame = new MainFrame("Train Booking System");
                mainFrame.setContentPane(new LoginPanel(mainFrame));
                mainFrame.setVisible(true);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error initializing application: " + e.getMessage(),
                        "Application Error",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
}