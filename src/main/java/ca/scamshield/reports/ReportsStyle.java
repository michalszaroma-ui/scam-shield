/*
 * ReportsStyle.java
 * Rev 1.0
 * 07/11/2026
 * @author Inès Khabkhabi 
 *
 * Holds the colours, fonts and reusable pieces (header bar, buttons, labels)
 * used by every Scam Reports screen, so all screens look the same.
 */
package ca.scamshield.reports;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class ReportsStyle {

    // Colours taken from the Scam Shield logo
    public static final Color BLUE = new Color(0x1B, 0x4B, 0x80);
    public static final Color GREEN = new Color(0x3F, 0x9A, 0x5C);
    public static final Color BACKGROUND = new Color(245, 247, 250);
    public static final Color TEXT = new Color(0x1F, 0x2A, 0x33);

    // One font family, three sizes
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);

    // Private constructor: this class is only a toolbox, nobody creates objects of it
    private ReportsStyle() {
    }

    /** Gives every screen the same title, size, position and background. */
    public static void setUpFrame(JFrame frame, String screenName) {
        frame.setTitle("Scam Shield - " + screenName);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);              // open in the centre of the screen
        frame.getContentPane().setBackground(BACKGROUND);
        frame.setLayout(new BorderLayout());
    }

    /** The dark blue bar at the top of each screen, with the logo and the screen title. */
    public static JPanel createHeader(String title) {
        JPanel header = new JPanel(new BorderLayout(14, 0));
        header.setBackground(BLUE);
        header.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));

        // The logo is optional: if the image file is missing, the header still works
        URL logoUrl = ReportsStyle.class.getResource("/images/ScamShield_Icon.png");
        if (logoUrl != null) {
            Image small = new ImageIcon(logoUrl).getImage().getScaledInstance(-1, 44, Image.SCALE_SMOOTH);
            header.add(new JLabel(new ImageIcon(small)), BorderLayout.WEST);
        }

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        header.add(titleLabel, BorderLayout.CENTER);
        return header;
    }

    /** A green button with white text, used for every button in the section. */
    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setBackground(GREEN);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    /** A label in the standard font and colour. */
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(LABEL_FONT);
        label.setForeground(TEXT);
        return label;
    }
}