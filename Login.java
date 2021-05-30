package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JLabel l1,l2,l3, l4;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2, b3;
    JPanel p1,p2,p3,p4;
    Choice c1;
    Login(){
        super("Login Page");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        l1 = new JLabel("Username");
        l1.setBounds(100, 20, 100, 20);
        add(l1);
        l2 = new JLabel("Password");
        l2.setBounds(100, 60, 100, 20);
        add(l2);
        
        tf1 = new JTextField(15);
        tf1.setBounds(200, 20, 150, 20);
        add(tf1);
        pf2 = new JPasswordField(15);
        pf2.setBounds(200, 60, 150, 20);
        add(pf2);
        
        l4 = new JLabel("Logging in as");
        l4.setBounds(100, 100, 100, 20);
        add(l4);
        
        c1 = new Choice();
        c1.add("Admin");
        c1.add("Customer");
        c1.setBounds(200, 100, 150, 20);
        add(c1);
        
        
        b1 = new JButton("Login");
        b1.setBounds(130, 160, 100, 20);
        add(b1);
        
        
        b2 = new JButton("Cancel");
        b2.setBounds(250, 160, 100, 20);
        add(b2);
        
        
        b3 = new JButton("Signup");
        b3.setBounds(180, 200, 130, 20);
        add(b3);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);


        
        setLayout(new BorderLayout());
    
     
        setSize(400,300);
        setLocation(600,300);
        setVisible(true);
        

        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            try{        
                Conn c = new Conn();
                String a  = tf1.getText();
                String b  = pf2.getText();
                String user = c1.getSelectedItem();
                String q  = "select * from login where username = '"+a+"' and password = '"+b+"' and user = '"+user+"'";
                ResultSet rs = c.s.executeQuery(q);
                if(rs.next()){
                    String meter = rs.getString("meter_no");
                    new Project(meter, user).setVisible(true);
                    this.setVisible(false);

                }else{
                    JOptionPane.showMessageDialog(null, "Invalid login");
                    tf1.setText("");
                    pf2.setText("");
                }
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("error: "+e);
            }
        }else if(ae.getSource() == b2){
            this.setVisible(false);
        }else if(ae.getSource() == b3){
            this.setVisible(false);
            new Signup().setVisible(true);
            
        }
    }
    
    public static void main(String[] args){
        new Login().setVisible(true);
    }

    
}



