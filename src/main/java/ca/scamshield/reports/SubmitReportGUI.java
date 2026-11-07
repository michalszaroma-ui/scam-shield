/*
 * SubmitReportGUI.java
 * Rev 1.0
 * 07/11/2026
 * @author Inès Khabkhabi - x________
 *
 * Form 1 of the Scam Reports section: the user enters the details of a scam.
 * The fields match the attributes of ScamReport and its three subclasses
 * (PhishingReport, PhoneScamReport, FakeWebsiteReport) from our class diagram.
 * Saving the report will be added in Checkpoint 3.
 */
package ca.scamshield.reports;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SubmitReportGUI extends JFrame {

    // Fields shared by every report (ScamReport)
    private JComboBox<String> cmbScamType;
    private JTextField txtReporterName;
    private JTextField txtDate;
    private JComboBox<String> cmbContactMethod;
    private JTextArea txtDescription;

    // PhishingReport fields
    private JTextField txtSenderEmail;
    private JTextField txtSuspiciousLink;
    // PhoneScamReport fields
    private JTextField txtPhoneNumber;
    private JTextField txtCallerClaimedToBe;
    // FakeWebsiteReport fields
    private JTextField txtWebsiteUrl;
    private JCheckBox chkMoneyPaid;

    private JPanel pnlExtraDetails;              // swaps between the three sets of extra fields
    private JButton btnSubmit;
    private JButton btnClear;
    private JButton btnBack;

    private static final String PHISHING = "Phishing email";
    private static final String PHONE = "Phone scam";
    private static final String WEBSITE = "Fake website";

    public SubmitReportGUI() {
        ReportsStyle.setUpFrame(this, "Submit a Report");
        initComponents();
    }

    private void initComponents() {
        add(ReportsStyle.createHeader("Submit a Scam Report"), BorderLayout.NORTH);

        // ---------- the form ----------
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(ReportsStyle.BACKGROUND);
        form.setBorder(BorderFactory.createEmptyBorder(14, 30, 6, 30));

        cmbScamType = new JComboBox<>(new String[]{PHISHING, PHONE, WEBSITE});
        txtReporterName = new JTextField(20);
        txtDate = new JTextField(10);
        txtDate.setToolTipText("dd/mm/yyyy");
        cmbContactMethod = new JComboBox<>(new String[]{"Email", "Phone call", "Text message", "Website", "Social media"});
        txtDescription = new JTextArea(4, 30);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);

        int row = 0;
        addRow(form, row++, "Scam type", cmbScamType);
        addRow(form, row++, "Your name", txtReporterName);
        addRow(form, row++, "Date (dd/mm/yyyy)", txtDate);
        addRow(form, row++, "How were you contacted?", cmbContactMethod);
        addRow(form, row++, "Description", new JScrollPane(txtDescription));

        // ---------- extra details: one card per report type ----------
        txtSenderEmail = new JTextField(20);
        txtSuspiciousLink = new JTextField(20);
        txtPhoneNumber = new JTextField(15);
        txtCallerClaimedToBe = new JTextField(20);
        txtWebsiteUrl = new JTextField(20);
        chkMoneyPaid = new JCheckBox("I paid money on this website");
        chkMoneyPaid.setBackground(ReportsStyle.BACKGROUND);
        chkMoneyPaid.setFont(ReportsStyle.LABEL_FONT);

        JPanel phishingCard = createCard();
        addRow(phishingCard, 0, "Sender email", txtSenderEmail);
        addRow(phishingCard, 1, "Suspicious link", txtSuspiciousLink);

        JPanel phoneCard = createCard();
        addRow(phoneCard, 0, "Phone number", txtPhoneNumber);
        addRow(phoneCard, 1, "Caller claimed to be", txtCallerClaimedToBe);

        JPanel websiteCard = createCard();
        addRow(websiteCard, 0, "Website URL", txtWebsiteUrl);
        addRow(websiteCard, 1, "Money paid?", chkMoneyPaid);

        pnlExtraDetails = new JPanel(new CardLayout());
        pnlExtraDetails.setBackground(ReportsStyle.BACKGROUND);
        pnlExtraDetails.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(ReportsStyle.GREEN), "Extra details"));
        pnlExtraDetails.add(phishingCard, PHISHING);
        pnlExtraDetails.add(phoneCard, PHONE);
        pnlExtraDetails.add(websiteCard, WEBSITE);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 0, 0, 0);
        form.add(pnlExtraDetails, c);

        add(form, BorderLayout.CENTER);

        // ---------- buttons ----------
        btnSubmit = ReportsStyle.createButton("Submit Report");
        btnClear = ReportsStyle.createButton("Clear");
        btnBack = ReportsStyle.createButton("Back");

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 12));
        buttons.setBackground(ReportsStyle.BACKGROUND);
        buttons.add(btnSubmit);
        buttons.add(btnClear);
        buttons.add(btnBack);
        add(buttons, BorderLayout.SOUTH);

        // ---------- events ----------
        cmbScamType.addActionListener(e -> showExtraDetailsFor((String) cmbScamType.getSelectedItem()));
        btnSubmit.addActionListener(e -> submitReport());
        btnClear.addActionListener(e -> clearForm());
        btnBack.addActionListener(e -> backToReportsMenu());
    }

    /** Puts a label in column 0 and a field in column 1 of the given row. */
    private void addRow(JPanel panel, int row, String labelText, JComponent field) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = row;
        c.insets = new Insets(5, 0, 5, 12);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        panel.add(ReportsStyle.createLabel(labelText), c);

        c.gridx = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(field, c);
    }

    private JPanel createCard() {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(ReportsStyle.BACKGROUND);
        return card;
    }

    /** Shows only the extra fields that belong to the chosen scam type. */
    private void showExtraDetailsFor(String scamType) {
        CardLayout cards = (CardLayout) pnlExtraDetails.getLayout();
        cards.show(pnlExtraDetails, scamType);
    }

    private void submitReport() {
        // Placeholder for Checkpoint 2. In Checkpoint 3 this will create a
        // PhishingReport / PhoneScamReport / FakeWebsiteReport and add it to ReportList.
        JOptionPane.showMessageDialog(this,
                "Report form complete. Saving reports will be added in Checkpoint 3.",
                "Submit Report", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearForm() {
        cmbScamType.setSelectedIndex(0);
        txtReporterName.setText("");
        txtDate.setText("");
        cmbContactMethod.setSelectedIndex(0);
        txtDescription.setText("");
        txtSenderEmail.setText("");
        txtSuspiciousLink.setText("");
        txtPhoneNumber.setText("");
        txtCallerClaimedToBe.setText("");
        txtWebsiteUrl.setText("");
        chkMoneyPaid.setSelected(false);
    }

    private void backToReportsMenu() {
        new ReportsMenuFrame().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SubmitReportGUI().setVisible(true));
    }
}