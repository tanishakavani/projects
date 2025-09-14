package SignIn;

import DataStructures.*;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Transaction extends JFrame implements ActionListener {

    JButton deposit, cashWithdrawal, fastCash, balanceEnquiry, exit;
    static long cardNo = Login.cardNo;
    static int pinNo = Login.pinNo;
    static double balance;
    
    Transaction(long cardNo, int pinNo, double balance) {

        this.setLayout(null);

        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("Icons\\atmPhoto.jpg"));
        Image image2 = image1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel image = new JLabel();
        image.setIcon(image3);
        image.setBounds(0, 0, 800, 800);

        JLabel label = new JLabel("Please select your transaction");
        label.setFont(new Font("Ariel", Font.BOLD, 19));
        label.setForeground(Color.WHITE);
        label.setBounds(162, 280, 275, 30);
        image.add(label);


        deposit = new JButton("Cash Deposit");
        deposit.setFont(new Font("Ariel", Font.BOLD, 13));
        deposit.setBackground(Color.LIGHT_GRAY);
        deposit.setFocusable(false);
        deposit.addActionListener(this);
        deposit.setBounds(145, 325, 150, 30);
        image.add(deposit);


        cashWithdrawal = new JButton("Cash Withdrawal");
        cashWithdrawal.setFont(new Font("Ariel", Font.BOLD, 13));
        cashWithdrawal.setBackground(Color.LIGHT_GRAY);
        cashWithdrawal.setFocusable(false);
        cashWithdrawal.addActionListener(this);
        cashWithdrawal.setBounds(300, 325, 150, 30);
        image.add(cashWithdrawal);


        fastCash = new JButton("Fast Cash");
        fastCash.setFont(new Font("Ariel", Font.BOLD, 13));
        fastCash.setBackground(Color.LIGHT_GRAY);
        fastCash.setFocusable(false);
        fastCash.addActionListener(this);
        fastCash.setBounds(145, 375, 150, 30);
        image.add(fastCash);


        balanceEnquiry = new JButton("Balance Enquiry");
        balanceEnquiry.setFont(new Font("Ariel", Font.BOLD, 13));
        balanceEnquiry.setBackground(Color.LIGHT_GRAY);
        balanceEnquiry.setFocusable(false);
        balanceEnquiry.addActionListener(this);
        balanceEnquiry.setBounds(300, 375, 150, 30);
        image.add(balanceEnquiry);


        exit = new JButton("EXIT");
        exit.setFont(new Font("Ariel", Font.BOLD, 13));
        exit.setBackground(Color.LIGHT_GRAY);
        exit.setFocusable(false);
        exit.addActionListener(this);
        exit.setBounds(350, 490, 100, 30);
        image.add(exit);


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

        new Transaction(cardNo, pinNo, balance);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            Conn c = new Conn();
            String sqlQuery = "{call getBalance(?, ?)}";
            CallableStatement cst = c.con.prepareCall(sqlQuery);
            cst.setLong(1, cardNo);
            cst.execute();
            balance = cst.getDouble(2);
        } catch(Exception e) {
            System.out.println(e);
        }
        Stack transactionDetail = new Stack();
        if(ae.getSource() == deposit) {
            this.setVisible(false);
            new Deposit(cardNo, pinNo, balance, transactionDetail).setVisible(true);
        } else if(ae.getSource() == cashWithdrawal) {
            this.setVisible(false);
            new Withdraw(cardNo, pinNo, balance, transactionDetail).setVisible(true);
        } else if(ae.getSource() == fastCash) {
            this.setVisible(false);
            new FastCash(cardNo, pinNo, balance, transactionDetail).setVisible(true);
        } else if(ae.getSource() == balanceEnquiry) {
            this.setVisible(false);
            new BalanceEnquiry();
        } else if(ae.getSource() == exit) {
            System.exit(0);
        }
    }

}
