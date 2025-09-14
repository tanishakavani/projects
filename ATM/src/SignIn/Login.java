package SignIn;

import SignUp.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame implements ActionListener {

    JButton signInButton, clearButton, signUpButton;
    JTextField cardTextField;
    JPasswordField pinTextField;
    static String cardNumber, pinNumber;
    static double balance;
    static long cardNo;
    static int pinNo;

    public Login() {

        this.setLayout(null);

        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("Icons\\atmLogo.png"));
        // check that image is there or not(at path we gave)

        Image image2 = image1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        // set scale of image

        ImageIcon image3 = new ImageIcon(image2); // convert image to image icon
        JLabel label = new JLabel(); // create a label
        label.setIcon(image3); // set icon of label
        label.setText("Welcome to ATM"); // set text of label
        label.setFont(new Font("Ariel", Font.BOLD, 32)); /// set font of label
        label.setBounds(150, 40, 375, 100); // set label
        label.setIconTextGap(10); // set icon and text gap

        JLabel cardNo = new JLabel("Enter Card No : ");
        cardNo.setFont(new Font("Ariel", Font.PLAIN, 17));
        cardNo.setBounds(160, 190, 150, 17);

        cardTextField = new JTextField(); // creates a text field
        cardTextField.setFont(new Font("Ariel", Font.PLAIN, 15));
        cardTextField.setBounds(300, 184, 200, 30);
        // Only allow digits and max 16 characters
        cardTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || cardTextField.getText().length() >= 16) {
                    e.consume();
                }
            }
        });

        JLabel pinNo = new JLabel("Enter PIN : ");
        pinNo.setFont(new Font("Ariel", Font.PLAIN, 17));
        pinNo.setBounds(160, 225, 150, 50);

        pinTextField = new JPasswordField(); // create a password field
        pinTextField.setFont(new Font("Ariel", Font.PLAIN, 17));
        pinTextField.setBounds(300, 237, 200, 30);
        // Only allow digits and max 4 characters
        pinTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || pinTextField.getPassword().length >= 4) {
                    e.consume();
                }
            }
        });

        signInButton = new JButton("Sign In"); // create a button
        signInButton.setFont(new Font("Ariel", Font.PLAIN, 17));
        signInButton.setBackground(Color.DARK_GRAY); // set background color of button
        signInButton.setForeground(Color.WHITE); // set font color of button
        signInButton.setFocusable(false); // false -> removes focusability
        signInButton.setBounds(160, 310, 150, 35);
        signInButton.addActionListener(this); // to perform an action on button

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Ariel", Font.PLAIN, 17));
        clearButton.setBackground(Color.DARK_GRAY);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusable(false);
        clearButton.setBounds(350, 310, 150, 35);
        clearButton.addActionListener(this);

        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Ariel", Font.PLAIN, 17));
        signUpButton.setBackground(Color.DARK_GRAY);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusable(false);
        signUpButton.setBounds(160, 370, 340, 35);
        signUpButton.addActionListener(this);

        this.add(label); // add components
        this.add(cardNo);
        this.add(pinNo);
        this.add(cardTextField);
        this.add(pinTextField);
        this.add(signInButton);
        this.add(clearButton);
        this.add(signUpButton);

        this.getContentPane().setBackground(Color.white); // set background color
        // getContentPane() --> returns object of the content pane

        // this.setSize(700, 500); // set size
        // this.setLocation(400, 170); // set frame location
        this.setBounds(400, 170, 700, 500); // instead of using size and location
        this.setTitle("Automated Teller Machine"); // set title
        this.setResizable(false); // true -> maximize and minimize the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set close operation
        this.setVisible(true); // make frame visible

    }

    public static void main(String[] args) {
        new Login();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == signInButton) {
            cardNumber = cardTextField.getText().trim();
            pinNumber = new String(pinTextField.getPassword()).trim();
            if (cardNumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Card Number");
            } else if (cardNumber.length() != 16) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Card Number");
            } else if (pinNumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid PIN number");
            } else if (pinNumber.length() != 4) {
                JOptionPane.showMessageDialog(null, "Please enter a valid pin number");
            } else {
                cardNo = Long.parseLong(cardNumber);
                pinNo = Integer.parseInt(pinNumber);
                try {
                    Conn c = new Conn();
                    String sqlQuery1 = "{call getBalance(?, ?)}";
                    CallableStatement cst = c.con.prepareCall(sqlQuery1);
                    cst.setLong(1, cardNo);
                    cst.execute();
                    balance = cst.getDouble(2);
                    Statement st = c.con.createStatement();
                    String sqlQuery2 = "select * from login where card_number = " + cardNo + " AND pin_number = "
                            + pinNo;
                    ResultSet rs = st.executeQuery(sqlQuery2);
                    if (rs.next()) {
                        this.setVisible(false);
                        new Transaction(cardNo, pinNo, balance).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Card Number or PIN");
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } else if (ae.getSource() == clearButton) {

            cardTextField.setText("");
            pinTextField.setText("");

        } else if (ae.getSource() == signUpButton) {

            this.setVisible(false);
            new SignUpOne("").setVisible(true);

        }
    }
}