package SignUp;

import DataStructures.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class SignUpOne extends JFrame implements ActionListener {

    JButton nextButton, backButton;
    JLabel formNo;
    JTextField nameTextField, fnameTextField, emailTextField, addressTextField, cityTextField, stateTextField,
            pinCodeTextField;
    JDateChooser dateChooser;
    JRadioButton male, female, other, married, unMarried;
    JComboBox<String> education, occupation, income;
    CustomerDetails customer;
    public static int number;

    public SignUpOne(String formNumber) {

        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
        }

        backButton = new JButton("BACK");
        backButton.setFocusable(false);
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(10, 10, 100, 35);
        backButton.addActionListener(this);

        Random random = new Random();
        int number = (int) Math.abs(random.nextLong() % 10000);

        SignUpOne.number = number;
        this.setLayout(null);

        formNo = new JLabel("APPLICATION FORM NO : " + number);
        formNo.setFont(new Font("Ariel", Font.BOLD, 32));
        formNo.setBounds(165, 10, 470, 32);

        JLabel personDetails = new JLabel("Page 1 : Person Details");
        personDetails.setFont(new Font("Ariel", Font.BOLD, 25));
        personDetails.setBounds(265, 45, 270, 35);

        JLabel name = new JLabel("Full Name :");
        name.setFont(new Font("Ariel", Font.PLAIN, 17));
        name.setBackground(Color.LIGHT_GRAY);
        name.setBounds(80, 110, 120, 30);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Ariel", Font.PLAIN, 17));
        nameTextField.setBounds(240, 110, 450, 30);

        JLabel fname = new JLabel("Father's Name :");
        fname.setFont(new Font("Ariel", Font.PLAIN, 17));
        fname.setBounds(80, 155, 150, 30);

        fnameTextField = new JTextField();
        fnameTextField.setFont(new Font("Ariel", Font.PLAIN, 17));
        fnameTextField.setBounds(240, 155, 450, 30);

        JLabel dob = new JLabel("Date of Birth :");
        dob.setFont(new Font("Ariel", Font.PLAIN, 17));
        dob.setBounds(80, 200, 150, 30);

        dateChooser = new JDateChooser();
        dateChooser.setFont(new Font("Ariel", Font.PLAIN, 15));
        dateChooser.setBounds(240, 200, 450, 30);
        javax.swing.JTextField dateField = (javax.swing.JTextField) dateChooser.getDateEditor().getUiComponent();
        dateField.setEditable(false);
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateField.setForeground(Color.BLACK);
        dateField.setCaretColor(Color.BLACK);
        dateChooser.getDateEditor().addPropertyChangeListener(evt -> {
            dateField.setForeground(Color.BLACK);
        });

        try {
            com.toedter.calendar.JYearChooser yearChooser = dateChooser.getJCalendar().getYearChooser();
            for (java.awt.Component comp : yearChooser.getComponents()) {
                if (comp instanceof javax.swing.JSpinner) {
                    javax.swing.JSpinner spinner = (javax.swing.JSpinner) comp;
                    java.awt.Component editor = spinner.getEditor();
                    if (editor instanceof javax.swing.JFormattedTextField) {
                        ((javax.swing.JFormattedTextField) editor).setForeground(Color.BLACK);
                        ((javax.swing.JFormattedTextField) editor).getDocument()
                                .addDocumentListener(new javax.swing.event.DocumentListener() {
                                    public void insertUpdate(javax.swing.event.DocumentEvent e) {
                                        ((javax.swing.JFormattedTextField) editor).setForeground(Color.BLACK);
                                    }

                                    public void removeUpdate(javax.swing.event.DocumentEvent e) {
                                        ((javax.swing.JFormattedTextField) editor).setForeground(Color.BLACK);
                                    }

                                    public void changedUpdate(javax.swing.event.DocumentEvent e) {
                                        ((javax.swing.JFormattedTextField) editor).setForeground(Color.BLACK);
                                    }
                                });
                    } else if (editor instanceof javax.swing.JPanel) {
                        for (java.awt.Component sub : ((javax.swing.JPanel) editor).getComponents()) {
                            if (sub instanceof javax.swing.JFormattedTextField) {
                                ((javax.swing.JFormattedTextField) sub).setForeground(Color.BLACK);
                                ((javax.swing.JFormattedTextField) sub).getDocument()
                                        .addDocumentListener(new javax.swing.event.DocumentListener() {
                                            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                                                ((javax.swing.JFormattedTextField) sub).setForeground(Color.BLACK);
                                            }

                                            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                                                ((javax.swing.JFormattedTextField) sub).setForeground(Color.BLACK);
                                            }

                                            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                                                ((javax.swing.JFormattedTextField) sub).setForeground(Color.BLACK);
                                            }
                                        });
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            // ignore if not available
        }

        dateField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                dateField.setForeground(Color.BLACK);
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                dateField.setForeground(Color.BLACK);
            }
        });

        JLabel gender = new JLabel("Gender :");
        gender.setFont(new Font("Ariel", Font.PLAIN, 17));
        gender.setBounds(80, 245, 150, 30);

        male = new JRadioButton("Male");
        male.setFont(new Font("Ariel", Font.PLAIN, 17));
        male.setFocusable(false);
        male.setBackground(Color.WHITE);
        male.setBounds(240, 245, 100, 30);

        female = new JRadioButton("Female");
        female.setFont(new Font("Ariel", Font.PLAIN, 17));
        female.setFocusable(false);
        female.setBackground(Color.WHITE);
        female.setBounds(370, 245, 100, 30);

        other = new JRadioButton("Other");
        other.setFont(new Font("Ariel", Font.PLAIN, 17));
        other.setFocusable(false);
        other.setBackground(Color.WHITE);
        other.setBounds(510, 245, 100, 30);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        JLabel email = new JLabel("Email Address :");
        email.setFont(new Font("Ariel", Font.PLAIN, 17));
        email.setBounds(80, 290, 150, 30);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Ariel", Font.PLAIN, 17));
        emailTextField.setBounds(240, 290, 450, 30);

        JLabel marital = new JLabel("Marital Status :");
        marital.setFont(new Font("Ariel", Font.PLAIN, 17));
        marital.setBounds(80, 335, 150, 30);

        married = new JRadioButton("Married");
        married.setFont(new Font("Ariel", Font.PLAIN, 17));
        married.setFocusable(false);
        married.setBackground(Color.WHITE);
        married.setBounds(240, 335, 130, 30);

        unMarried = new JRadioButton("Unmarried");
        unMarried.setFont(new Font("Ariel", Font.PLAIN, 17));
        unMarried.setFocusable(false);
        unMarried.setBackground(Color.WHITE);
        unMarried.setBounds(370, 335, 130, 30);

        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(married);
        maritalGroup.add(unMarried);

        JLabel address = new JLabel("Address :");
        address.setFont(new Font("Ariel", Font.PLAIN, 17));
        address.setBounds(80, 380, 150, 30);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Ariel", Font.PLAIN, 17));
        addressTextField.setBounds(240, 380, 450, 30);

        JLabel city = new JLabel("City :");
        city.setFont(new Font("Ariel", Font.PLAIN, 17));
        city.setBounds(80, 425, 150, 30);

        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Ariel", Font.PLAIN, 17));
        cityTextField.setBounds(240, 425, 225, 30);

        JLabel state = new JLabel("State :");
        state.setFont(new Font("Ariel", Font.PLAIN, 17));
        state.setBounds(80, 470, 150, 30);

        stateTextField = new JTextField();
        stateTextField.setFont(new Font("Ariel", Font.PLAIN, 17));
        stateTextField.setBounds(240, 470, 225, 30);

        JLabel pinCode = new JLabel("Pin Code :");
        pinCode.setFont(new Font("Ariel", Font.PLAIN, 17));
        pinCode.setBounds(80, 515, 150, 30);

        pinCodeTextField = new JTextField();
        pinCodeTextField.setFont(new Font("Ariel", Font.PLAIN, 17));
        pinCodeTextField.setBounds(240, 515, 225, 30);
        
        java.awt.event.KeyAdapter alphaOnly = new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && c != '\b')
                    e.consume();
            }
        };
        nameTextField.addKeyListener(alphaOnly);
        fnameTextField.addKeyListener(alphaOnly);
        addressTextField.addKeyListener(alphaOnly);
        cityTextField.addKeyListener(alphaOnly);
        stateTextField.addKeyListener(alphaOnly);

        pinCodeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || pinCodeTextField.getText().length() >= 6)
                    e.consume();
            }
        });

        JLabel educationLabel = new JLabel("Education");
        educationLabel.setFont(new Font("Ariel", Font.PLAIN, 17));
        educationLabel.setBounds(80, 560, 225, 30);
        JLabel qualificationLabel = new JLabel("Qualification :");
        qualificationLabel.setFont(new Font("Ariel", Font.PLAIN, 17));
        qualificationLabel.setBounds(80, 590, 225, 30);

        String educationOption[] = { "Non-Graduate", "Graduate", "Post-Graduate", "Other" };
        education = new JComboBox<>(educationOption);
        education.setBounds(240, 590, 225, 30);

        JLabel occupationLabel = new JLabel("Occupation :");
        occupationLabel.setFont(new Font("Ariel", Font.PLAIN, 17));
        occupationLabel.setBounds(80, 645, 225, 30);

        String occupationOption[] = { "Self-Employed", "Bussiness", "Student", "Retired", "Other" };
        occupation = new JComboBox<>(occupationOption);
        occupation.setBounds(240, 645, 225, 30);

        JLabel incomeLabel = new JLabel("Income :");
        incomeLabel.setFont(new Font("Ariel", Font.PLAIN, 17));
        incomeLabel.setBounds(80, 690, 225, 30);

        String incomeOption[] = { "Null", "<= 1,50,000", "<= 3,00,000", "<= 4,50,000", "> 4,50,000" };
        income = new JComboBox<>(incomeOption);
        income.setBounds(240, 690, 225, 30);

        nextButton = new JButton("NEXT");
        nextButton.setFocusable(false);
        nextButton.setBackground(Color.DARK_GRAY);
        nextButton.setForeground(Color.WHITE);
        nextButton.setBounds(650, 710, 100, 30);
        nextButton.addActionListener(this);

        this.add(formNo);
        this.add(personDetails);
        this.add(backButton);

        this.add(name);
        this.add(nameTextField);

        this.add(fname);
        this.add(fnameTextField);

        this.add(dob);
        this.add(dateChooser);

        this.add(gender);
        this.add(male);
        this.add(female);
        this.add(other);

        this.add(email);
        this.add(emailTextField);

        this.add(marital);
        this.add(married);
        this.add(unMarried);

        this.add(address);
        this.add(addressTextField);

        this.add(city);
        this.add(cityTextField);

        this.add(state);
        this.add(stateTextField);

        this.add(pinCode);
        this.add(pinCodeTextField);

        this.add(educationLabel);
        this.add(qualificationLabel);
        this.add(education);

        this.add(occupationLabel);
        this.add(occupation);

        this.add(incomeLabel);
        this.add(income);

        this.add(nextButton);

        this.getContentPane().setBackground(Color.white);
        this.setBounds(350, 10, 800, 850);
        this.setTitle("Automated Teller Machine");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public static void main(String[] args) {

        new SignUpOne("");

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            this.setVisible(false);
            new SignIn.Login().setVisible(true);
            return;
        }
        String name = nameTextField.getText().trim();
        String fname = fnameTextField.getText().trim();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = "";
        if (male.isSelected()) {
            gender = "male";
        } else if (female.isSelected()) {
            gender = "female";
        } else if (other.isSelected()) {
            gender = "other";
        }
        String email = emailTextField.getText();
        String marital = "";
        if (married.isSelected()) {
            marital = "married";
        } else if (unMarried.isSelected()) {
            marital = "unmarried";
        }
        String address = addressTextField.getText().trim();
        String city = cityTextField.getText().trim();
        String state = stateTextField.getText().trim();
        String spinCode = pinCodeTextField.getText().trim();
        String seducation = (String) education.getSelectedItem();
        String soccupation = (String) occupation.getSelectedItem();
        String sincome = (String) income.getSelectedItem();

        try {
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name is required");
            } else if (!name.matches("[A-Za-z ]+")) {
                JOptionPane.showMessageDialog(null, "Name must contain alphabets and spaces only");
            } else if (fname.equals("")) {
                JOptionPane.showMessageDialog(null, "Father's Name is required");
            } else if (!fname.matches("[A-Za-z ]+")) {
                JOptionPane.showMessageDialog(null, "Father's Name must contain alphabets and spaces only");
            } else if (dob.equals("")) {
                JOptionPane.showMessageDialog(null, "Date of Birth is required");
            } else if (gender.equals("")) {
                JOptionPane.showMessageDialog(null, "Gender is required");
            } else if (email.equals("")) {
                JOptionPane.showMessageDialog(null, "Email is required");
            } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                JOptionPane.showMessageDialog(null, "Invalid email format");
            } else if (marital.equals("")) {
                JOptionPane.showMessageDialog(null, "Marital Status is required");
            } else if (address.equals("")) {
                JOptionPane.showMessageDialog(null, "Address is required");
            } else if (!address.matches("[A-Za-z ]+")) {
                JOptionPane.showMessageDialog(null, "Address must contain alphabets and spaces only");
            } else if (city.equals("")) {
                JOptionPane.showMessageDialog(null, "City is required");
            } else if (!city.matches("[A-Za-z ]+")) {
                JOptionPane.showMessageDialog(null, "City must contain alphabets and spaces only");
            } else if (state.equals("")) {
                JOptionPane.showMessageDialog(null, "State is required");
            } else if (!state.matches("[A-Za-z ]+")) {
                JOptionPane.showMessageDialog(null, "State must contain alphabets and spaces only");
            } else if (spinCode.equals("")) {
                JOptionPane.showMessageDialog(null, "Pin Code is required");
            } else if (!spinCode.matches("\\d{6}")) {
                JOptionPane.showMessageDialog(null, "Pin Code must be exactly 6 digits");
            } else if (seducation.equals("")) {
                JOptionPane.showMessageDialog(null, "Education Qaulification is required");
            } else if (soccupation.equals("")) {
                JOptionPane.showMessageDialog(null, "Occupation is required");
            } else if (sincome.equals("")) {
                JOptionPane.showMessageDialog(null, "Income is required");
            } else {
                int pinCode = Integer.parseInt(spinCode);
                customer = new CustomerDetails(name, fname, dob, gender, email, marital, address, city, state, pinCode,
                        seducation, soccupation, sincome);
                this.setVisible(false);
                SinglyLinkedList sll = new SinglyLinkedList();
                sll.insertLast(customer);
                new SignUpTwo(number, sll).setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
