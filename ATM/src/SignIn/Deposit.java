package SignIn;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DataStructures.Stack;

public class Deposit extends JFrame implements ActionListener {

    JButton deposit, back;
    static long cardNo = Login.cardNo;
    static int pinNo = Login.pinNo;
    JTextField amountTextField;
    static double balance;
    static Stack tranDetail;

    Deposit(long cardNo, int pinNo, double balance, Stack tranDetail) {

    Deposit.tranDetail = tranDetail;
        this.setLayout(null);

        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("Icons\\atmPhoto.jpg"));
        Image image2 = image1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel image = new JLabel();
        image.setIcon(image3);
        image.setBounds(0, 0, 800, 800);


        JLabel label = new JLabel("Enter amount you want to deposit:");
        label.setFont(new Font("Ariel", Font.BOLD, 19));
        label.setForeground(Color.WHITE);
        label.setBounds(140, 300, 350, 30);
        image.add(label);


        amountTextField = new JTextField();
        amountTextField.setFont(new Font("Ariel", Font.BOLD, 17));
        amountTextField.setBounds(150, 345, 150, 30);
        image.add(amountTextField);


        deposit = new JButton("Deposit");
        deposit.setFont(new Font("Ariel", Font.BOLD, 13));
        deposit.setBounds(350, 450, 100, 30);
        deposit.setBackground(Color.LIGHT_GRAY);
        deposit.setFocusable(false);
        deposit.addActionListener(this);
        image.add(deposit);


        back = new JButton("BACK");
        back.setFont(new Font("Ariel", Font.BOLD, 13));
        back.setBounds(350, 490, 100, 30);
        back.setBackground(Color.LIGHT_GRAY);
        back.setFocusable(false);
        back.addActionListener(this);
        image.add(back);


        this.add(image);


        this.getContentPane().setBackground(Color.WHITE);
        this.setBounds(350, 7, 800, 800);
        this.setTitle("Automated Teller Machine");
        this.setResizable(false);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new Deposit(cardNo, pinNo, balance, tranDetail);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String sqlQuery1, sqlQuery2;
        Statement st;
        try {
            Conn c = new Conn();
            sqlQuery1 = "{call getBalance(?, ?)}";
            CallableStatement cst = c.con.prepareCall(sqlQuery1);
            cst.setLong(1, cardNo);
            cst.execute();
            balance = cst.getDouble(2);
        } catch(Exception e) {
            System.out.println(e);
        }
        if(ae.getSource() == deposit) {
            String samount = amountTextField.getText();
            double amount = Double.parseDouble(samount);
            Date date = new Date();
            if(samount.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter amount you want to deposit");
            } else {
                balance += amount;
                String strDetail = "" + cardNo + " , " + date + " , Deposit, " + amount + " , " + balance;
                tranDetail.PUSH(strDetail);
                sqlQuery1 = "insert into bank_detail(card_number, transaction_date, transaction_type, transaction_amount, acc_balance) values(" + cardNo  + ", '" + date + "', 'Deposit', " + amount + ", " + balance + ")";
                sqlQuery2 = "update login set balance = " + balance + " where card_number = " + cardNo;
                try {
                    Conn c= new Conn();
                    st = c.con.createStatement();
                    st.executeUpdate(sqlQuery1);
                    st.executeUpdate(sqlQuery2);
                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");
                    this.setVisible(false);
                    new Transaction(cardNo, pinNo, balance).setVisible(true);
                } catch(Exception e) {
                    System.out.println(e);
                }
            }
        } else if(ae.getSource() == back) {
            this.setVisible(false);
            new Transaction(cardNo, pinNo, balance).setVisible(true);
        }
    }
}
