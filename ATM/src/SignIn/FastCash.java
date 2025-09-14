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

import DataStructures.Stack;

public class FastCash extends JFrame implements ActionListener {

    JButton amount1, amount2, amount3, amount4, amount5, amount6, back;
    static long cardNo = Login.cardNo;
    static int pinNo = Login.pinNo;
    static double balance;
    static Stack tranDetail;

    FastCash(long cardNo, int pinNo, double balance, Stack tranDetail) {
        
    FastCash.tranDetail = tranDetail;
        this.setLayout(null);

        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("Icons\\atmPhoto.jpg"));
        Image image2 = image1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel image = new JLabel();
        image.setIcon(image3);
        image.setBounds(0, 0, 800, 800);


        JLabel label = new JLabel("Select Withdwrawal Amount");
        label.setFont(new Font("Ariel", Font.BOLD, 19));
        label.setForeground(Color.WHITE);
        label.setBounds(170, 280, 350, 30);
        image.add(label);


        amount1 = new JButton("RS. 100");
        amount1.setFont(new Font("Ariel", Font.BOLD, 13));
        amount1.setBackground(Color.LIGHT_GRAY);
        amount1.setFocusable(false);
        amount1.addActionListener(this);
        amount1.setBounds(160, 320, 125, 30);
        image.add(amount1);


        amount2 = new JButton("RS. 500");
        amount2.setFont(new Font("Ariel", Font.BOLD, 13));
        amount2.setBackground(Color.LIGHT_GRAY);
        amount2.setFocusable(false);
        amount2.addActionListener(this);
        amount2.setBounds(315, 320, 125, 30);
        image.add(amount2);


        amount3 = new JButton("Rs. 1000");
        amount3.setFont(new Font("Ariel", Font.BOLD, 13));
        amount3.setBackground(Color.LIGHT_GRAY);
        amount3.setFocusable(false);
        amount3.addActionListener(this);
        amount3.setBounds(160, 360, 125, 30);
        image.add(amount3);


        amount4 = new JButton("Rs. 2000");
        amount4.setFont(new Font("Ariel", Font.BOLD, 13));
        amount4.setBackground(Color.LIGHT_GRAY);
        amount4.setFocusable(false);
        amount4.addActionListener(this);
        amount4.setBounds(315, 360, 125, 30);
        image.add(amount4);


        amount5 = new JButton("Rs. 5000");
        amount5.setFont(new Font("Ariel", Font.BOLD, 13));
        amount5.setBackground(Color.LIGHT_GRAY);
        amount5.setFocusable(false);
        amount5.addActionListener(this);
        amount5.setBounds(160, 400, 125, 30);
        image.add(amount5);


        amount6 = new JButton("Rs. 10000");
        amount6.setFont(new Font("Ariel", Font.BOLD, 13));
        amount6.setBackground(Color.LIGHT_GRAY);
        amount6.setFocusable(false);
        amount6.addActionListener(this);
        amount6.setBounds(315, 400, 125, 30);
        image.add(amount6);


        back = new JButton("BACK");
        back.setFont(new Font("Ariel", Font.BOLD, 13));
        back.setBackground(Color.LIGHT_GRAY);
        back.setFocusable(false);
        back.addActionListener(this);
        back.setBounds(350, 490, 100, 30);
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
        new FastCash(cardNo, pinNo, balance, tranDetail);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Date date = new Date();
        String sqlQuery1, sqlQuery2, sqlQuery3;
        try {
            Conn c = new Conn();
            sqlQuery1 = "{call getBalance(?, ?)}";
            CallableStatement cst = c.con.prepareCall(sqlQuery1);
            cst.setLong(1, cardNo);
            cst.execute();
            balance = cst.getDouble(2);
            Statement st = c.con.createStatement();
            if(ae.getSource() == amount1) {
                balance -= 100;
                String strDetail = "" + cardNo + " , " + date + " , Deposit, 100 , " + " , " + balance;
                tranDetail.PUSH(strDetail);
                sqlQuery2 = "insert into bank_detail(card_number, transaction_date, transaction_type, transaction_amount, acc_balance) values(" + cardNo + ", '" + date + "', 'Withdraw', 100, " + balance + ")";
                st.executeUpdate(sqlQuery2);
                JOptionPane.showMessageDialog(null, "Rs. 100 Withdrawal Successfully");

            } else if(ae.getSource() == amount2) {
                balance -= 500;
                String strDetail = "" + cardNo + " , " + date + " , Deposit, 100 , " + " , " + balance;
                tranDetail.PUSH(strDetail);
                sqlQuery2 = "insert into bank_detail(card_number, transaction_date, transaction_type, transaction_amount, acc_balance) values(" + cardNo + ", '" + date + "', 'Withdraw', 500, " + balance + ")";
                st.executeUpdate(sqlQuery2);
                JOptionPane.showMessageDialog(null, "Rs. 500 Withdrawal Successfully");

            } else if(ae.getSource() == amount3) {
                balance -= 1000;
                String strDetail = "" + cardNo + " , " + date + " , Deposit, 100 , " + " , " + balance;
                tranDetail.PUSH(strDetail);
                sqlQuery2 = "insert into bank_detail(card_number, transaction_date, transaction_type, transaction_amount, acc_balance) values(" + cardNo + ", '" + date + "', 'Withdraw', 1000, " + balance + ")";
                st.executeUpdate(sqlQuery2);
                JOptionPane.showMessageDialog(null, "Rs. 1000 Withdrawal Successfully");
                
            } else if(ae.getSource() == amount4) {
                balance -= 2000;
                String strDetail = "" + cardNo + " , " + date + " , Deposit, 100 , " + " , " + balance;
                tranDetail.PUSH(strDetail);
                sqlQuery2 = "insert into bank_detail(card_number, transaction_date, transaction_type, transaction_amount, acc_balance) values(" + cardNo + ", '" + date + "', 'Withdraw', 2000, " + balance + ")";
                st.executeUpdate(sqlQuery2);
                JOptionPane.showMessageDialog(null, "Rs. 2000 Withdrawal Successfully");
                
            } else if(ae.getSource() == amount5) {
                balance -= 5000;
                String strDetail = "" + cardNo + " , " + date + " , Deposit, 100 , " + " , " + balance;
                tranDetail.PUSH(strDetail);
                sqlQuery2 = "insert into bank_detail(card_number, transaction_date, transaction_type, transaction_amount, acc_balance) values(" + cardNo + ", '" + date + "', 'Withdraw', 5000, " + balance + ")";
                st.executeUpdate(sqlQuery2);
                JOptionPane.showMessageDialog(null, "Rs. 5000 Withdrawal Successfully");
                
            } else if(ae.getSource() == amount6) {
                balance -= 10000;
                String strDetail = "" + cardNo + " , " + date + " , Deposit, 100 , " + " , " + balance;
                tranDetail.PUSH(strDetail);
                sqlQuery2 = "insert into bank_detail(card_number, transaction_date, transaction_type, transaction_amount, acc_balance) values(" + cardNo + ", '" + date + "', 'Withdraw', 10000, " + balance + ")";
                st.executeUpdate(sqlQuery2);
                JOptionPane.showMessageDialog(null, "Rs. 10000 Withdrawal Successfully");
            }
            sqlQuery3 = "update login set balance = " + balance + " where card_number = " + cardNo;
            st.executeUpdate(sqlQuery3);
        } catch(Exception e) {
            System.out.println(e);
        }
        this.setVisible(false);
        new Transaction(cardNo, pinNo, balance).setVisible(true);
    }
}
