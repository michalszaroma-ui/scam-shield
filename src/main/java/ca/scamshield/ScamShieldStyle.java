/*
 *  ScamShieldStyle.java
 *
 *  Rev 1
 *
 *  18/11/2026
 *
 *  @author Ines Khabkhabi (original design and ReportsStyle class)
 *  @author Michal Szaroma, YourStudentID (shared version for NetBeans forms)
 *
 *  Shared look for every Scam Shield screen, based on Ines's ReportsStyle:
 *  a white page with a dotted pattern, the logo, a blue title with a green
 *  line under it, a light-green information bar, blue/white buttons and a
 *  footer line.
 */
package ca.scamshield;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

public final class ScamShieldStyle {

    // ----- Colours (the same as the logo and the proposal document) -----
    public static final Color LOGO_BLUE = new Color(0x1B, 0x4B, 0x80);
    public static final Color LOGO_GREEN = new Color(0x3F, 0x9A, 0x5C);
    public static final Color INFO_GREEN = new Color(0xEA, 0xF4, 0xEC);
    public static final Color ROW_GREEN = new Color(0xF3, 0xF9, 0xF4);
    public static final Color GREY = new Color(0x5B, 0x67, 0x70);
    public static final Color DOTS = new Color(0xC8, 0xD5, 0xE2);
    public static final Color LINE = new Color(0x9F, 0xB3, 0xC8);
    public static final Color TEXT = new Color(0x1F, 0x2A, 0x33);
    public static final Color BACKGROUND = Color.WHITE;

    // ----- Fonts -----
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font HEADING_FONT = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font SMALL_FONT = new Font("Segoe UI", Font.PLAIN, 12);

    // Private constructor: this class is only a toolbox, nobody creates objects of it
    private ScamShieldStyle() {
    }

    // ================= For screens built as NetBeans forms =================

    /** Gives a screen the white dotted page. Call this BEFORE initComponents(). */
    public static void applyPage(JFrame frame) {
        JPanel page = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(DOTS);
                for (int y = 9; y < getHeight(); y += 18) {
                    for (int x = 9; x < getWidth(); x += 18) {
                        g.fillOval(x - 1, y - 1, 3, 3);
                    }
                }
            }
        };
        page.setBackground(BACKGROUND);
        page.setBorder(BorderFactory.createEmptyBorder(14, 24, 10, 24));
        frame.setContentPane(page);
    }
    
        // Every Scam Shield window is the same size
    public static final int FRAME_WIDTH = 860;
    public static final int FRAME_HEIGHT = 720;

    /** Call at the END of the constructor: same size for every screen, centred. */
    public static void finishFrame(JFrame frame) {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
    }

    /** Swaps the contents of a screen's old header panel for the logo, title and info bar. */
    public static void replaceHeader(JPanel oldHeader, String section, String title, String subtitle) {
        oldHeader.removeAll();
        oldHeader.setOpaque(false);
        oldHeader.setLayout(new BorderLayout());
        oldHeader.setPreferredSize(null);            // let it size itself to the new content
        oldHeader.add(createHeader(section, title, subtitle), BorderLayout.CENTER);
    }

    /** Swaps the contents of a screen's old footer panel for the thin line and footer text. */
    public static void replaceFooter(JPanel oldFooter, String section, String screenName) {
        oldFooter.removeAll();
        oldFooter.setOpaque(false);
        oldFooter.setLayout(new BorderLayout());
        oldFooter.setPreferredSize(null);
        oldFooter.add(createFooter(section, screenName), BorderLayout.CENTER);
    }

    /** The main action on a screen: blue button with white text. */
    public static void stylePrimaryButton(JButton button) {
        styleBase(button);
        button.setBackground(LOGO_BLUE);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(9, 22, 9, 22));
    }

    /** Other actions (Back, Clear...): white button with a blue outline. */
    public static void styleButton(JButton button) {
        styleBase(button);
        button.setBackground(Color.WHITE);
        button.setForeground(LOGO_BLUE);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LOGO_BLUE),
                BorderFactory.createEmptyBorder(8, 21, 8, 21)));
    }

    /** A framed box with a green title, like "Add a Question". */
    public static void styleBox(JPanel box, String title) {
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(LINE), title);
        border.setTitleFont(HEADING_FONT);
        border.setTitleColor(LOGO_GREEN);
        box.setBorder(border);
        box.setOpaque(false);
    }

    // ================= Building blocks (also used by hand-coded screens) =================

    /** Logo, title in blue with a green line under it, and a light-green info bar. */
    public static JPanel createHeader(String section, String title, String subtitle) {
        JPanel header = transparentPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel();
        URL logoUrl = ScamShieldStyle.class.getResource("/images/ScamShield_Logo.png");
        if (logoUrl != null) {
            Image small = new ImageIcon(logoUrl).getImage().getScaledInstance(-1, 46, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(small));
        } else {
            logo.setText("Scam Shield");             // if the logo file is missing, show the name
            logo.setFont(TITLE_FONT);
            logo.setForeground(LOGO_BLUE);
        }

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(LOGO_BLUE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 4, 0));

        JPanel greenLine = new JPanel();
        greenLine.setBackground(LOGO_GREEN);
        greenLine.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        greenLine.setPreferredSize(new Dimension(10, 2));

        JLabel infoBar = new JLabel("<html><b>" + section + "</b>&nbsp;&nbsp;" + subtitle + "</html>");
        infoBar.setFont(LABEL_FONT);
        infoBar.setForeground(LOGO_BLUE);
        infoBar.setOpaque(true);
        infoBar.setBackground(INFO_GREEN);
        infoBar.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        infoBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        for (JLabel l : new JLabel[]{logo, titleLabel, infoBar}) {
            l.setAlignmentX(Component.LEFT_ALIGNMENT);
        }
        greenLine.setAlignmentX(Component.LEFT_ALIGNMENT);

        header.add(logo);
        header.add(titleLabel);
        header.add(greenLine);
        header.add(Box.createVerticalStrut(10));
        header.add(infoBar);
        header.add(Box.createVerticalStrut(8));
        return header;
    }

    /** A thin line, then "Scam Shield · section" on the left and the screen name on the right. */
    public static JPanel createFooter(String section, String screenName) {
        JPanel footer = transparentPanel();
        footer.setLayout(new BorderLayout());
        footer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, LINE),
                BorderFactory.createEmptyBorder(6, 0, 0, 0)));
        JLabel left = new JLabel("Scam Shield · " + section);
        JLabel right = new JLabel(screenName);
        for (JLabel l : new JLabel[]{left, right}) {
            l.setFont(SMALL_FONT);
            l.setForeground(GREY);
        }
        footer.add(left, BorderLayout.WEST);
        footer.add(right, BorderLayout.EAST);
        return footer;
    }

    public static JLabel createHeading(String text) {
        JLabel heading = new JLabel(text);
        heading.setFont(HEADING_FONT);
        heading.setForeground(LOGO_GREEN);
        return heading;
    }

    public static JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        stylePrimaryButton(button);
        return button;
    }

    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        styleButton(button);
        return button;
    }

    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(LABEL_FONT);
        label.setForeground(TEXT);
        return label;
    }

    public static Border fieldBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE),
                BorderFactory.createEmptyBorder(3, 5, 3, 5));
    }

    /** Navy header with white text and light-green stripes, like the proposal. */
    public static void styleTable(JTable table) {
        table.setFont(LABEL_FONT);
        table.setRowHeight(26);
        table.setGridColor(LINE);
        table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value, boolean selected,
                                                           boolean focus, int row, int col) {
                JLabel cell = (JLabel) super.getTableCellRendererComponent(t, value, selected, focus, row, col);
                cell.setBackground(LOGO_BLUE);
                cell.setForeground(Color.WHITE);
                cell.setFont(BUTTON_FONT);
                cell.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
                return cell;
            }
        });
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value, boolean selected,
                                                           boolean focus, int row, int col) {
                JLabel cell = (JLabel) super.getTableCellRendererComponent(t, value, selected, focus, row, col);
                if (!selected) {
                    cell.setBackground(row % 2 == 0 ? Color.WHITE : ROW_GREEN);
                    cell.setForeground(TEXT);
                }
                cell.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                return cell;
            }
        });
    }

    /** A panel with no background of its own, so the dotted page shows through. */
    public static JPanel transparentPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        return panel;
    }

    private static void styleBase(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}