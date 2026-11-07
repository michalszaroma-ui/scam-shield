/*
 * SubmitReportGUI.java
 * Rev 2.0
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
        ReportsStyle.setUpFrame(this, "Submit Report");
        initComponents();
    }

    private void initComponents() {
        add(ReportsStyle.createHeader("Submit Report", "Report a new scam so others are warned"), BorderLayout.NORTH);

        // ---------- the form ----------
        JPanel form = ReportsStyle.transparentPanel();
        form.setLayout(new GridBagLayout());

        cmbScamType = new JComboBox<>(new String[]{PHISHING, PHONE, WEBSITE});
        txtReporterName = textField(20);
        txtDate = textField(10);
        txtDate.setToolTipText("dd/mm/yyyy");
        cmbContactMethod = new JComboBox<>(new String[]{"Email", "Phone call", "Text message", "Website", "Social media"});
        txtDescription = new JTextArea(3, 30);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        txtDescription.setFont(ReportsStyle.LABEL_FONT);
        JScrollPane descriptionScroll = new JScrollPane(txtDescription);
        descriptionScroll.setBorder(ReportsStyle.fieldBorder());

        int row = 0;
        addHeading(form, row++, "Report details");
        addRow(form, row++, "Scam type", cmbScamType);
        addRow(form, row++, "Your name", txtReporterName);
        addRow(form, row++, "Date (dd/mm/yyyy)", txtDate);
        addRow(form, row++, "How were you contacted?", cmbContactMethod);
        addRow(form, row++, "Description", descriptionScroll);

        // ---------- extra details: one card per report type ----------
        txtSenderEmail = textField(20);
        txtSuspiciousLink = textField(20);
        txtPhoneNumber = textField(15);
        txtCallerClaimedToBe = textField(20);
        txtWebsiteUrl = textField(20);
        chkMoneyPaid = new JCheckBox("I paid money on this website");
        chkMoneyPaid.setOpaque(false);
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

        pnlExtraDetails = ReportsStyle.transparentPanel();
        pnlExtraDetails.setLayout(new CardLayout());
        pnlExtraDetails.add(phishingCard, PHISHING);
        pnlExtraDetails.add(phoneCard, PHONE);
        pnlExtraDetails.add(websiteCard, WEBSITE);

        addHeading(form, row++, "Extra details for this type of scam");
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        form.add(pnlExtraDetails, c);
                JPanel formHolder = ReportsStyle.transparentPanel();       // keeps the form at the top
        formHolder.setLayout(new BorderLayout());
        formHolder.add(form, BorderLayout.NORTH);
        add(formHolder, BorderLayout.CENTER);

        // ---------- buttons and footer ----------
        btnSubmit = ReportsStyle.createPrimaryButton("Submit Report");
        btnClear = ReportsStyle.createButton("Clear");
        btnBack = ReportsStyle.createButton("Back");

        JPanel buttons = ReportsStyle.transparentPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        buttons.add(btnBack);
        buttons.add(btnClear);
        buttons.add(btnSubmit);

        JPanel bottom = ReportsStyle.transparentPanel();
        bottom.setLayout(new BorderLayout());
        bottom.add(buttons, BorderLayout.NORTH);
        bottom.add(ReportsStyle.createFooter("Submit Report"), BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);

        // ---------- events ----------
        cmbScamType.addActionListener(e -> showExtraDetailsFor((String) cmbScamType.getSelectedItem()));
        btnSubmit.addActionListener(e -> submitReport());
        btnClear.addActionListener(e -> clearForm());
        btnBack.addActionListener(e -> backToReportsMenu());
    }

    /** A text field with the standard font and border. */
    private JTextField textField(int columns) {
        JTextField field = new JTextField(columns);
        field.setFont(ReportsStyle.LABEL_FONT);
        field.setBorder(ReportsStyle.fieldBorder());
        return field;
    }

    /** A green sub-heading across both columns. */
    private void addHeading(JPanel panel, int row, String text) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(row == 0 ? 0 : 10, 0, 4, 0);
        panel.add(ReportsStyle.createHeading(text), c);
    }

    /** Puts a label in column 0 and a field in column 1 of the given row. */
    private void addRow(JPanel panel, int row, String labelText, JComponent field) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = row;
        c.insets = new Insets(4, 0, 4, 14);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        JComponent label = ReportsStyle.createLabel(labelText);
        label.setPreferredSize(new java.awt.Dimension(190, 24));
        panel.add(label, c);

        c.gridx = 1;
        c.weightx = 1;
        c.insets = new Insets(4, 0, 4, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(field, c);
    }

    private JPanel createCard() {
        JPanel card = ReportsStyle.transparentPanel();
        card.setLayout(new GridBagLayout());
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