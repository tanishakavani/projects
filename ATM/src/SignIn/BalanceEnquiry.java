package SignIn;

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

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton back;
    double balance;

    public BalanceEnquiry() {

        this.setLayout(null);

        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("Icons\\atmPhoto.jpg"));
        Image image2 = image1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel image = new JLabel();
        image.setIcon(image3);
        image.setBounds(0, 0, 800, 800);


        JLabel label1 = new JLabel("Current balance :");
        label1.setFont(new Font("Ariel", Font.BOLD, 19));
        label1.setForeground(Color.WHITE);
        label1.setBounds(170, 300, 350, 30);
        image.add(label1);


        JLabel label2 = new JLabel("" + Transaction.balance);
        label2.setFont(new Font("Ariel", Font.BOLD, 19));
        label2.setForeground(Color.BLACK);
        label2.setBackground(Color.WHITE);
        label2.setOpaque(true);
        label2.setBounds(170, 340, 200, 30);
        image.add(label2);


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
        new BalanceEnquiry();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.setVisible(false);
        long cardNo = Login.cardNo;
        int pinNo = Login.pinNo;
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
        new Transaction(cardNo, pinNo, balance);
        
    }
}
