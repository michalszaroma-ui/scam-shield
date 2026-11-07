/*
 * ViewReportsGUI.java
 * Rev 2.0
 * 07/11/2026
 * @author Inès Khabkhabi 
 *
 * Form 2 of the Scam Reports section: the user searches the reports by
 * keyword or type, sees each report's risk level, and can delete a report.
 * The table shows example rows for now; real reports from ReportList and the
 * search / delete logic will be added in Checkpoint 3.
 */
package ca.scamshield.reports;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class ViewReportsGUI extends JFrame {

    private JTextField txtSearch;
    private JComboBox<String> cmbFilterType;
    private JButton btnSearch;
    private JTable tblReports;
    private DefaultTableModel tableModel;
    private JButton btnDelete;
    private JButton btnBack;

    public ViewReportsGUI() {
        ReportsStyle.setUpFrame(this, "View & Search Reports");
        initComponents();
    }

    private void initComponents() {
        add(ReportsStyle.createHeader("View & Search Reports", "See all reports, search or delete a report"),
                BorderLayout.NORTH);

        // ---------- search bar ----------
        txtSearch = new JTextField(18);
        txtSearch.setFont(ReportsStyle.LABEL_FONT);
        txtSearch.setBorder(ReportsStyle.fieldBorder());
        cmbFilterType = new JComboBox<>(new String[]{"All types", "Phishing email", "Phone scam", "Fake website"});
        btnSearch = ReportsStyle.createPrimaryButton("Search");

        JPanel searchBar = ReportsStyle.transparentPanel();
        searchBar.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 4));
        searchBar.add(ReportsStyle.createHeading("Search"));
        searchBar.add(ReportsStyle.createLabel("  Keyword"));
        searchBar.add(txtSearch);
        searchBar.add(ReportsStyle.createLabel("  Type"));
        searchBar.add(cmbFilterType);
        searchBar.add(btnSearch);

        // ---------- table of reports ----------
        String[] columns = {"Report ID", "Type", "Date", "Reporter", "Risk level"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;                         // the table is for viewing only
            }
        };
        // Example rows so the screen shows what it will look like (marked as examples)
        tableModel.addRow(new Object[]{"R001 (example)", "Phishing email", "02/11/2026", "Sample user", "High"});
        tableModel.addRow(new Object[]{"R002 (example)", "Phone scam", "03/11/2026", "Sample user", "Medium"});
        tableModel.addRow(new Object[]{"R003 (example)", "Fake website", "04/11/2026", "Sample user", "High"});

        tblReports = new JTable(tableModel);
        tblReports.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ReportsStyle.styleTable(tblReports);

        JScrollPane tableScroll = new JScrollPane(tblReports);
        tableScroll.setBorder(BorderFactory.createLineBorder(ReportsStyle.LINE));
        tableScroll.getViewport().setBackground(java.awt.Color.WHITE);

        JPanel centre = ReportsStyle.transparentPanel();
        centre.setLayout(new BorderLayout(0, 6));
        centre.add(searchBar, BorderLayout.NORTH);
        centre.add(tableScroll, BorderLayout.CENTER);
        add(centre, BorderLayout.CENTER);

        // ---------- buttons and footer ----------
        btnDelete = ReportsStyle.createButton("Delete Selected");
        btnBack = ReportsStyle.createButton("Back");
        JPanel buttons = ReportsStyle.transparentPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        buttons.add(btnBack);
        buttons.add(btnDelete);

        JPanel bottom = ReportsStyle.transparentPanel();
        bottom.setLayout(new BorderLayout());
        bottom.add(buttons, BorderLayout.NORTH);
        bottom.add(ReportsStyle.createFooter("View & Search Reports"), BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);

        // ---------- events ----------
        btnSearch.addActionListener(e -> searchReports());
        btnDelete.addActionListener(e -> deleteSelectedReport());
        btnBack.addActionListener(e -> backToReportsMenu());
    }

    private void searchReports() {
        // Placeholder: in Checkpoint 3 this will call ReportList.searchByKeyword / searchByType
        JOptionPane.showMessageDialog(this,
                "Searching will be added in Checkpoint 3.",
                "Search Reports", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteSelectedReport() {
        if (tblReports.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Select a report in the table first.",
                    "Delete Report", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Placeholder: in Checkpoint 3 this will call ReportList.removeReport(reportId)
        JOptionPane.showMessageDialog(this,
                "Deleting reports will be added in Checkpoint 3.",
                "Delete Report", JOptionPane.INFORMATION_MESSAGE);
    }

    private void backToReportsMenu() {
        new ReportsMenuFrame().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewReportsGUI().setVisible(true));
    }
}