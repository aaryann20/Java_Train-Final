package ticket.booking.util;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

/**
 * Manages UI theme and colors for the application
 */
public class UIThemeManager {

    // Primary colors
    public static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    public static final Color PRIMARY_DARK = new Color(44, 62, 80);
    public static final Color PRIMARY_LIGHT = new Color(52, 152, 219);

    // Secondary colors
    public static final Color ACCENT_COLOR = new Color(230, 126, 34);
    public static final Color SUCCESS_COLOR = new Color(46, 204, 113);
    public static final Color ERROR_COLOR = new Color(231, 76, 60);
    public static final Color WARNING_COLOR = new Color(241, 196, 15);

    // Background and text colors
    public static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    public static final Color CARD_BACKGROUND = new Color(255, 255, 255);
    public static final Color TEXT_COLOR = new Color(44, 62, 80);
    public static final Color TEXT_LIGHT = new Color(149, 165, 166);

    /**
     * Sets up the application theme with custom colors
     */
    public static void setupTheme() {
        // Set Nimbus colors
        UIManager.put("nimbusBase", new ColorUIResource(PRIMARY_DARK));
        UIManager.put("nimbusBlueGrey", new ColorUIResource(BACKGROUND_COLOR));
        UIManager.put("control", new ColorUIResource(BACKGROUND_COLOR));

        // Button colors
        UIManager.put("Button.background", PRIMARY_COLOR);
        UIManager.put("Button.foreground", Color.WHITE);

        // Text field colors
        UIManager.put("TextField.background", CARD_BACKGROUND);
        UIManager.put("TextField.foreground", TEXT_COLOR);
        UIManager.put("TextField.caretForeground", PRIMARY_COLOR);

        // Table colors
        UIManager.put("Table.background", CARD_BACKGROUND);
        UIManager.put("Table.foreground", TEXT_COLOR);
        UIManager.put("Table.selectionBackground", PRIMARY_LIGHT);
        UIManager.put("Table.selectionForeground", Color.WHITE);
        UIManager.put("Table.gridColor", new ColorUIResource(new Color(189, 195, 199)));

        // List colors
        UIManager.put("List.background", CARD_BACKGROUND);
        UIManager.put("List.foreground", TEXT_COLOR);
        UIManager.put("List.selectionBackground", PRIMARY_LIGHT);
        UIManager.put("List.selectionForeground", Color.WHITE);

        // Menu colors
        UIManager.put("MenuBar.background", PRIMARY_DARK);
        UIManager.put("MenuBar.foreground", Color.WHITE);
        UIManager.put("Menu.foreground", Color.WHITE);
        UIManager.put("MenuItem.foreground", TEXT_COLOR);
    }

    /**
     * Creates a styled button with consistent appearance
     */
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        return button;
    }

    /**
     * Creates a styled button with an icon
     */
    public static JButton createStyledButton(String text, Icon icon) {
        JButton button = createStyledButton(text);
        button.setIcon(icon);
        return button;
    }

    /**
     * Creates a panel with a card-like appearance
     */
    public static JPanel createCardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(CARD_BACKGROUND);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        return panel;
    }

    /**
     * Creates a styled header label
     */
    public static JLabel createHeaderLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(PRIMARY_DARK);
        return label;
    }

    /**
     * Creates a styled title label
     */
    public static JLabel createTitleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(PRIMARY_COLOR);
        return label;
    }
}