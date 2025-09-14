package SignUp;

import DataStructures.*;
import SignIn.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.*;

// DocumentFilter to enforce PAN number format
class PanNumberDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        replace(fb, offset, 0, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) return;
        StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.replace(offset, offset + length, text);

        String candidate = sb.toString();
        if (isValidPan(candidate)) {
            // ensure uppercase inserted
            fb.replace(offset, length, text.toUpperCase(), attrs);
        }
    }

    private boolean isValidPan(String text) {
        if (text.length() > 10) return false;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (i < 5 || i == 9) {
                if (!Character.isUpperCase(c)) return false;
            } else if (i >= 5 && i < 9) {
                if (!Character.isDigit(c)) return false;
            }
        }
        return true;
    }
}

// DocumentFilter to enforce Aadhar number format
class AadharNumberDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        replace(fb, offset, 0, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) return;
        StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.replace(offset, offset + length, text);

        if (isValidAadhar(sb.toString())) {
            fb.replace(offset, length, text, attrs);
        }
    }

    private boolean isValidAadhar(String text) {
        if (text.length() > 12) return false;
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) return false;
        }
        return true;
    }
}

// DocumentFilter to enforce exactly up to 4 digits for PIN
class FourDigitDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        replace(fb, offset, 0, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) return;
        StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.replace(offset, offset + length, text);

        if (isValidPin(sb.toString())) {
            fb.replace(offset, length, text, attrs);
        }
    }

    private boolean isValidPin(String text) {
        if (text.length() > 4) return false;
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) return false;
        }
        return true;
    }
}

public class SignUpTwo extends JFrame implements ActionListener {

    static int number;
    public static long cardNo;
    public static int pinNo;
    JLabel formNo;
    JLabel cardNumber;
    JLabel pinNumber;
    JComboBox<String> religion, category;
    JTextField panTextField, aadharTextField, pinField, rePinField;
    JRadioButton savingAccount, currentAccount, fixedDepositAccount, recurringDepositAccount;
    JCheckBox facility1, facility2, facility3, facility4, facility5, checkBox;
    JButton submitButton;
    double balance = 10000;
    static SinglyLinkedList sll;

    SignUpTwo(int formNumber, SinglyLinkedList sll) {

        SignUpTwo.sll = sll;
        SignUpTwo.number = formNumber;

        this.setLayout(null);

        formNo = new JLabel("APPLICATION FORM NO : " + number);
        formNo.setFont(new Font("Ariel", Font.BOLD, 32));
        formNo.setBounds(165, 10, 470, 32);

        JLabel additionalDetails = new JLabel("Page 2 : Additional Details");
        additionalDetails.setFont(new Font("Ariel", Font.BOLD, 25));
        additionalDetails.setBounds(247, 45, 306, 35);

        JLabel religionLabel = new JLabel("Religion :");
        religionLabel.setFont(new Font("Ariel", Font.PLAIN, 17));
        religionLabel.setBackground(Color.LIGHT_GRAY);
        religionLabel.setBounds(80, 110, 120, 30);

        String religionOption[] = { "Hindu", "Muslim", "Sikh", "Christian", "Other" };
        religion = new JComboBox<>(religionOption);
        religion.setBounds(240, 110, 225, 30);

        JLabel categoryLabel = new JLabel("Category :");
        categoryLabel.setFont(new Font("Ariel", Font.PLAIN, 17));
        categoryLabel.setBackground(Color.LIGHT_GRAY);
        categoryLabel.setBounds(80, 155, 120, 30);

        String categoryOption[] = { "General", "OBC", "SC", "ST", "Other" };
        category = new JComboBox<>(categoryOption);
        category.setBounds(240, 155, 225, 30);

        JLabel pan = new JLabel("PAN Number :");
        pan.setFont(new Font("Ariel", Font.PLAIN, 17));
        pan.setBounds(80, 200, 150, 30);

        panTextField = new JTextField();
        panTextField.setFont(new Font("Ariel", Font.PLAIN, 17));
        panTextField.setBounds(240, 200, 450, 30);
        // Enforce PAN format: 5 uppercase letters, 4 digits, 1 uppercase letter
        ((AbstractDocument) panTextField.getDocument()).setDocumentFilter(new PanNumberDocumentFilter());
        JLabel panHint = new JLabel("(e.g. ABCDE1234F, 5 letters, 4 digits, 1 letter, UPPERCASE only)");
        panHint.setFont(new Font("Ariel", Font.PLAIN, 12));
        panHint.setForeground(Color.GRAY);
        panHint.setBounds(240, 230, 400, 20);


        JLabel aadhar = new JLabel("Aadhar Number :");
        aadhar.setFont(new Font("Ariel", Font.PLAIN, 17));
        aadhar.setBounds(80, 260, 150, 30);

        aadharTextField = new JTextField();
        aadharTextField.setFont(new Font("Ariel", Font.PLAIN, 17));
        aadharTextField.setBounds(240, 260, 450, 30);
        // Enforce Aadhar format: 12 digits
        ((AbstractDocument) aadharTextField.getDocument()).setDocumentFilter(new AadharNumberDocumentFilter());
        JLabel aadharHint = new JLabel("(12 digit Aadhar number, digits only)");
        aadharHint.setFont(new Font("Ariel", Font.PLAIN, 12));
        aadharHint.setForeground(Color.GRAY);
        aadharHint.setBounds(240, 290, 400, 20);

        JLabel accType = new JLabel("Account Type :");
        accType.setFont(new Font("Ariel", Font.PLAIN, 17));
        accType.setBounds(80, 320, 150, 30);

        savingAccount = new JRadioButton("Saving Account");
        savingAccount.setFont(new Font("Ariel", Font.PLAIN, 17));
        savingAccount.setBackground(Color.WHITE);
        savingAccount.setFocusable(false);
        savingAccount.setBounds(240, 320, 150, 30);

        fixedDepositAccount = new JRadioButton("Fixed Deposit Account");
        fixedDepositAccount.setFont(new Font("Ariel", Font.PLAIN, 17));
        fixedDepositAccount.setBackground(Color.WHITE);
        fixedDepositAccount.setFocusable(false);
        fixedDepositAccount.setBounds(450, 320, 200, 30);

        currentAccount = new JRadioButton("Current Account");
        currentAccount.setFont(new Font("Ariel", Font.PLAIN, 17));
        currentAccount.setBackground(Color.WHITE);
        currentAccount.setFocusable(false);
        currentAccount.setBounds(240, 355, 150, 30);

        recurringDepositAccount = new JRadioButton("Recurring Deposit Account");
        recurringDepositAccount.setFont(new Font("Ariel", Font.PLAIN, 17));
        recurringDepositAccount.setBackground(Color.WHITE);
        recurringDepositAccount.setFocusable(false);
        recurringDepositAccount.setBounds(450, 355, 225, 30);

        ButtonGroup accTypeGroup = new ButtonGroup();
        accTypeGroup.add(savingAccount);
        accTypeGroup.add(fixedDepositAccount);
        accTypeGroup.add(currentAccount);
        accTypeGroup.add(recurringDepositAccount);

        // --- Card Number Section ---
        JLabel card = new JLabel("Card Number :");
        card.setFont(new Font("Ariel", Font.PLAIN, 17));
        card.setBounds(80, 395, 150, 30);

        // Generate card number at form load and display it
        Random random = new Random();
        cardNo = Math.abs(random.nextLong() % 10000000000000000L);
        String cardNoStr = String.format("%016d", cardNo);
        String maskedCard = "XXXX-XXXX-XXXX-" + cardNoStr.substring(12);
        cardNumber = new JLabel(maskedCard);
        cardNumber.setFont(new Font("Ariel", Font.PLAIN, 17));
        cardNumber.setBounds(240, 395, 450, 30);

        JLabel cardLabel = new JLabel("(16 digit card number)");
        cardLabel.setFont(new Font("Ariel", Font.PLAIN, 12));
        cardLabel.setForeground(Color.GRAY);
        cardLabel.setBounds(450, 395, 150, 30);

        JLabel pinFieldLabel = new JLabel("Set PIN :");
        pinFieldLabel.setFont(new Font("Ariel", Font.PLAIN, 17));
        pinFieldLabel.setBounds(80, 440, 150, 30);

        pinField = new JTextField();
        pinField.setFont(new Font("Ariel", Font.PLAIN, 17));
        pinField.setBounds(240, 440, 200, 30);
        // Enforce 4-digit input only
        ((AbstractDocument) pinField.getDocument()).setDocumentFilter(new FourDigitDocumentFilter());

        JLabel pinFieldHint = new JLabel("(4 digit PIN, digits only)");
        pinFieldHint.setFont(new Font("Ariel", Font.PLAIN, 12));
        pinFieldHint.setForeground(Color.GRAY);
        pinFieldHint.setBounds(450, 440, 200, 30);

        JLabel rePinFieldLabel = new JLabel("Re-enter PIN :");
        rePinFieldLabel.setFont(new Font("Ariel", Font.PLAIN, 17));
        rePinFieldLabel.setBounds(80, 485, 150, 30);

        rePinField = new JTextField();
        rePinField.setFont(new Font("Ariel", Font.PLAIN, 17));
        rePinField.setBounds(240, 485, 200, 30);
        ((AbstractDocument) rePinField.getDocument()).setDocumentFilter(new FourDigitDocumentFilter());

        // Attach KeyListeners (input restrictions) here in constructor so they are active immediately
        panTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                String text = panTextField.getText();
                if (text.length() < 5) {
                    if (!Character.isLetter(c) || !Character.isUpperCase(c))
                        e.consume();
                } else if (text.length() < 9) {
                    if (!Character.isDigit(c))
                        e.consume();
                } else if (text.length() < 10) {
                    if (!Character.isLetter(c) || !Character.isUpperCase(c))
                        e.consume();
                } else {
                    e.consume();
                }
            }
        });
        aadharTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || aadharTextField.getText().length() >= 12)
                    e.consume();
            }
        });
        pinField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || pinField.getText().length() >= 4)
                    e.consume();
            }
        });
        rePinField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || rePinField.getText().length() >= 4)
                    e.consume();
            }
        });

        // --- Facility Section (moved below PIN fields) ---
        JLabel facility = new JLabel("Facility :");
        facility.setFont(new Font("Ariel", Font.PLAIN, 17));
        facility.setBounds(80, 530, 150, 30);

        facility1 = new JCheckBox("ATM Card");
        facility1.setFont(new Font("Ariel", Font.PLAIN, 17));
        facility1.setBackground(Color.WHITE);
        facility1.setFocusable(false);
        facility1.setBounds(240, 530, 110, 30);

        facility2 = new JCheckBox("Internet Banking");
        facility2.setFont(new Font("Ariel", Font.PLAIN, 17));
        facility2.setBackground(Color.WHITE);
        facility2.setFocusable(false);
        facility2.setBounds(450, 530, 150, 30);

        facility3 = new JCheckBox("Email & SMS alerts");
        facility3.setFont(new Font("Ariel", Font.PLAIN, 17));
        facility3.setBackground(Color.WHITE);
        facility3.setFocusable(false);
        facility3.setBounds(240, 565, 170, 30);

        facility4 = new JCheckBox("Cheque Book");
        facility4.setFont(new Font("Ariel", Font.PLAIN, 17));
        facility4.setBackground(Color.WHITE);
        facility4.setFocusable(false);
        facility4.setBounds(450, 565, 150, 30);

        facility5 = new JCheckBox("E-Statement");
        facility5.setFont(new Font("Ariel", Font.PLAIN, 17));
        facility5.setBackground(Color.WHITE);
        facility5.setFocusable(false);
        facility5.setBounds(240, 600, 150, 30);

        checkBox = new JCheckBox("I hearby declares that above entered details are correct.");
        checkBox.setFont(new Font("Ariel", Font.PLAIN, 14));
        checkBox.setBackground(Color.WHITE);
        checkBox.setFocusable(false);
        checkBox.setBounds(100, 650, 400, 30);

        submitButton = new JButton("SUBMIT");
        submitButton.setFocusable(false);
        submitButton.setBackground(Color.DARK_GRAY);
        submitButton.setForeground(Color.WHITE);
        submitButton.setBounds(650, 710, 100, 30);
        submitButton.addActionListener(this);

        // --- Add components to JFrame in correct order ---
        this.add(formNo);
        this.add(additionalDetails);

        this.add(religionLabel);
        this.add(religion);

        this.add(categoryLabel);
        this.add(category);

        this.add(pan);
        this.add(panTextField);

        this.add(aadhar);
        this.add(aadharTextField);

        this.add(accType);
        this.add(savingAccount);
        this.add(currentAccount);
        this.add(fixedDepositAccount);
        this.add(recurringDepositAccount);

        this.add(panHint);
        this.add(aadharHint);

        this.add(card);
        this.add(cardLabel);
        this.add(cardNumber);

        // Add PIN fields below card number
        this.add(pinFieldLabel);
        this.add(pinField);
        this.add(pinFieldHint);
        this.add(rePinFieldLabel);
        this.add(rePinField);

        // Add facility section below PIN fields
        this.add(facility);
        this.add(facility1);
        this.add(facility2);
        this.add(facility3);
        this.add(facility4);
        this.add(facility5);

        this.add(checkBox);
        this.add(submitButton);

        this.getContentPane().setBackground(Color.WHITE);
        this.setBounds(350, 0, 800, 800);
        this.setTitle("Automated Teller Machine");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        number = SignUpOne.number;
        new SignUpTwo(number, sll);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String sreligion = (String) religion.getSelectedItem();
        String scategory = (String) category.getSelectedItem();
        String panNumber = panTextField.getText().trim();
        String saadharNumber = aadharTextField.getText().trim();
        String pinValue = pinField.getText().trim();
        String rePinValue = rePinField.getText().trim();
        String saccType = "";
        if (savingAccount.isSelected()) {
            saccType = "Saving Account";
        } else if (currentAccount.isSelected()) {
            saccType = "Current Account";
        } else if (fixedDepositAccount.isSelected()) {
            saccType = "Fixed Deposit Account";
        } else if (recurringDepositAccount.isSelected()) {
            saccType = "Recurring Deposit Account";
        }

        // Allow multiple facilities
        String facility = "";
        if (facility1.isSelected()) {
            facility += "ATM Card, ";
        }
        if (facility2.isSelected()) {
            facility += "Internet Banking, ";
        }
        if (facility3.isSelected()) {
            facility += "Email & SMS alerts, ";
        }
        if (facility4.isSelected()) {
            facility += "Cheque Book, ";
        }
        if (facility5.isSelected()) {
            facility += "E-Statement, ";
        }
        // remove trailing comma + space if present
        if (facility.endsWith(", ")) {
            facility = facility.substring(0, facility.length() - 2);
        }

        try {
            if (sreligion == null || sreligion.equals("")) {
                JOptionPane.showMessageDialog(null, "Religion is required");
            } else if (scategory == null || scategory.equals("")) {
                JOptionPane.showMessageDialog(null, "Category is required");
            } else if (panNumber.equals("")) {
                JOptionPane.showMessageDialog(null, "PAN Number is required");
            } else if (!panNumber.matches("[A-Z]{5}[0-9]{4}[A-Z]")) {
                JOptionPane.showMessageDialog(null,
                        "Invalid PAN format! Format: 5 letters, 4 digits, 1 letter, all uppercase (e.g. ABCDE1234F)");
            } else if (saadharNumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Aadhar Number is required");
            } else if (!saadharNumber.matches("\\d{12}")) {
                JOptionPane.showMessageDialog(null, "Aadhar must be exactly 12 digits");
            } else if (saccType.equals("")) {
                JOptionPane.showMessageDialog(null, "Account type is required");
            } else if (pinValue.length() != 4 || !pinValue.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(null, "Enter a valid PIN (4 digits)");
            } else if (rePinValue.length() != 4 || !rePinValue.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(null, "Enter a valid Re-enter PIN (4 digits)");
            } else if (!pinValue.equals(rePinValue)) {
                JOptionPane.showMessageDialog(null, "PIN does not match");
            } else if (!checkBox.isSelected()) {
                JOptionPane.showMessageDialog(null, "Kindly select the check box");
            } else {
                // Check PIN uniqueness in DB
                Conn c = new Conn();
                Statement st = c.con.createStatement();
                java.sql.ResultSet rs = st
                        .executeQuery("SELECT pin_number FROM login WHERE pin_number = '" + pinValue + "'");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "This PIN already exists. Please choose a different PIN.");
                    return;
                }
                long aadharNumber = Long.parseLong(saadharNumber);
                CustomerDetails cus = sll.getFirst();
                String sqlQuery1 = "insert into signupone values(" + number + ", '" + cus.name + "', '" + cus.fname
                        + "', '" + cus.dob + "', '" + cus.gender + "', '" + cus.email + "', '" + cus.marital + "', '"
                        + cus.address + "', '" + cus.city + "', '" + cus.state + "', " + cus.pinCode + ")";
                st.executeUpdate(sqlQuery1);
                String sqlQuery2 = "insert into signuptwo values(" + number + ", '" + cus.seducation + "', '"
                        + cus.soccupation + "', '" + cus.sincome + "', '" + sreligion + "', '" + scategory + "', '"
                        + panNumber + "', " + aadharNumber + ", " + cardNo + ", " + pinValue + ", '" + facility + "', '"
                        + saccType + "')";
                st.executeUpdate(sqlQuery2);
                String sqlQuery3 = "insert into login (form_number, card_number, pin_number, balance) values(" + number
                        + ", " + cardNo + ", " + pinValue + ", " + balance + ")";
                st.executeUpdate(sqlQuery3);
                JOptionPane.showMessageDialog(null, "Card Number : " + cardNo + "\n PIN : " + pinValue);
                this.setVisible(false);
                new Login().setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}