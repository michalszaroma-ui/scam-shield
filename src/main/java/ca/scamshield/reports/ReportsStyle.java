/*
 * ReportsStyle.java
 * Rev 4.0
 * 07/11/2026
 * @author Inès Khabkhabi 
 *
 * Holds the colours, fonts and reusable pieces used by every Scam Reports screen.
 * The look matches our Scam Shield project proposal: a white page with a light
 * dotted pattern, the logo at the top, titles in the logo's blue with a green line
 * under them, green sub-headings, a light-green information bar, a navy table
 * header and a footer line.
 */
package ca.scamshield.reports;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

public final class ReportsStyle {

    // ----- Colours (the same as the logo and the proposal document) -----
    public static final Color LOGO_BLUE = new Color(0x1B, 0x4B, 0x80);   // titles, table header, main buttons
    public static final Color LOGO_GREEN = new Color(0x3F, 0x9A, 0x5C);  // sub-headings and the line under titles
    public static final Color INFO_GREEN = new Color(0xEA, 0xF4, 0xEC);  // light-green information bar
    public static final Color ROW_GREEN = new Color(0xF3, 0xF9, 0xF4);   // every second table row
    public static final Color GREY = new Color(0x5B, 0x67, 0x70);        // subtitles and footer
    public static final Color DOTS = new Color(0xC8, 0xD5, 0xE2);        // the dotted background
    public static final Color LINE = new Color(0x9F, 0xB3, 0xC8);        // footer line and field borders
    public static final Color TEXT = new Color(0x1F, 0x2A, 0x33);
    public static final Color BACKGROUND = Color.WHITE;

    // ----- Fonts -----
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font HEADING_FONT = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font SMALL_FONT = new Font("Segoe UI", Font.PLAIN, 12);

    // Private constructor: this class is only a toolbox, nobody creates objects of it
    private ReportsStyle() {
    }

    /** Same title, size and position for every screen, on a white page with a dotted pattern. */
    public static void setUpFrame(JFrame frame, String screenName) {
        frame.setTitle("Scam Shield - " + screenName);
        frame.setSize(820, 640);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);               // open in the centre of the screen

        JPanel page = new JPanel(new BorderLayout()) {
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
        page.setBorder(BorderFactory.createEmptyBorder(14, 34, 10, 34));
        frame.setContentPane(page);
    }
        /**
     * Top of each screen: the logo, the screen title in blue with a green line under it,
     * and a light-green bar saying which section this is and what the screen does.
     */
    public static JPanel createHeader(String title, String subtitle) {
        JPanel header = transparentPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel();
        URL logoUrl = ReportsStyle.class.getResource("/images/ScamShield_Logo.png");
        if (logoUrl != null) {
            Image small = new ImageIcon(logoUrl).getImage().getScaledInstance(-1, 46, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(small));
        } else {
            logo.setText("Scam Shield");                 // if the logo file is missing, show the name
            logo.setFont(TITLE_FONT);
            logo.setForeground(LOGO_BLUE);
        }

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(LOGO_BLUE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 4, 0));

        JPanel greenLine = new JPanel();
        greenLine.setBackground(LOGO_GREEN);
        greenLine.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 2));
        greenLine.setPreferredSize(new java.awt.Dimension(10, 2));

        JLabel infoBar = new JLabel("<html><b>Scam Reports</b>&nbsp;&nbsp;" + subtitle + "</html>");
        infoBar.setFont(LABEL_FONT);
        infoBar.setForeground(LOGO_BLUE);
        infoBar.setOpaque(true);
        infoBar.setBackground(INFO_GREEN);
        infoBar.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        infoBar.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 30));

        for (JLabel l : new JLabel[]{logo, titleLabel, infoBar}) {
            l.setAlignmentX(Component.LEFT_ALIGNMENT);
        }
        greenLine.setAlignmentX(Component.LEFT_ALIGNMENT);

        header.add(logo);
        header.add(titleLabel);
        header.add(greenLine);
        header.add(javax.swing.Box.createVerticalStrut(10));
        header.add(infoBar);
        header.add(javax.swing.Box.createVerticalStrut(8));
        return header;
    }

    /** Bottom of each screen: a thin line, then "Scam Shield · Scam Reports" and the screen name. */
    public static JPanel createFooter(String screenName) {
        JPanel footer = transparentPanel();
        footer.setLayout(new BorderLayout());
        footer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, LINE),
                BorderFactory.createEmptyBorder(6, 0, 0, 0)));
        JLabel left = new JLabel("Scam Shield · Scam Reports");
        JLabel right = new JLabel(screenName);
        for (JLabel l : new JLabel[]{left, right}) {
            l.setFont(SMALL_FONT);
            l.setForeground(GREY);
        }
        footer.add(left, BorderLayout.WEST);
        footer.add(right, BorderLayout.EAST);
        return footer;
    }

    /** A green sub-heading, like "Summary" or "User stories" in the proposal. */
    public static JLabel createHeading(String text) {
        JLabel heading = new JLabel(text);
        heading.setFont(HEADING_FONT);
        heading.setForeground(LOGO_GREEN);
        return heading;
    }

    /** The main action on a screen: a blue button with white text. */
    public static JButton createPrimaryButton(String text) {
        JButton button = baseButton(text);
        button.setBackground(LOGO_BLUE);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(9, 22, 9, 22));
        return button;
    }

    /** Other actions (Clear, Back...): a white button with a blue outline. */
    public static JButton createButton(String text) {
        JButton button = baseButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(LOGO_BLUE);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LOGO_BLUE),
                BorderFactory.createEmptyBorder(8, 21, 8, 21)));
        return button;
    }

    private static JButton baseButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setOpaque(true);
        button.setFocusPainted(false);
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

    /** A thin grey-blue border for text fields and boxes. */
    public static Border fieldBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE),
                BorderFactory.createEmptyBorder(3, 5, 3, 5));
    }

    /** Table like the one in the proposal: navy header with white text, light-green stripes. */
    public static void styleTable(JTable table) {
        table.setFont(LABEL_FONT);
        table.setRowHeight(26);
        table.setGridColor(LINE);
        table.getTableHeader().setFont(BUTTON_FONT);
        table.getTableHeader().setBackground(LOGO_BLUE);
        table.getTableHeader().setForeground(Color.WHITE);
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
}