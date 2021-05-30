package Electricity;

import Electricity.Conn;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener{
    JLabel l1,l2,l3,l4,l5,l6,l7;
    JTextField t1;
    Choice c1,c2;
    JButton b1,b2;
   
    CalculateBill(){
        
       
        
         JLabel name = new JLabel("NAME");
        name.setFont(new Font ("Tahoma",Font.PLAIN,17));
        name.setBounds(60, 120, 100, 30);
        add(name);
        
        
         JLabel meterno = new JLabel("METER NO");
        meterno.setFont(new Font ("Tahoma",Font.PLAIN,17));
        meterno.setBounds(60, 70, 100, 30);
        add(meterno);
        
         c1 = new Choice();
        c1.setBounds(200, 70, 180, 20);
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){
                c1.add(rs.getString("meter"));
            }
        }catch(Exception e){}
        add(c1);
        
         JLabel addr = new JLabel("Address");
        addr.setFont(new Font ("Tahoma",Font.PLAIN,17));
        addr.setBounds(60, 170, 100, 30);
        add(addr);
        
        JLabel uc = new JLabel("Units Consumed");
        uc.setFont(new Font ("Tahoma",Font.PLAIN,17));
        uc.setBounds(60, 220, 100, 30);
        add(uc);
        
        t1 = new JTextField();
        t1.setBounds(200, 220, 180, 20);
        add(t1);
        
        JLabel m = new JLabel("Months");
        m.setFont(new Font ("Tahoma",Font.PLAIN,17));
        m.setBounds(60, 270, 100, 30);
        add(m);
        
         c2 = new Choice();
        c2.setBounds(200, 270, 180, 20);
        c2.add("January");
        c2.add("February");
        c2.add("March");
        c2.add("April");
        c2.add("May");
        c2.add("June");
        c2.add("July");
        c2.add("August");
        c2.add("September");
        c2.add("October");
        c2.add("November");
        c2.add("December");
        add(c2);
        
         
        JLabel l1 = new JLabel();
        l1.setBounds(200, 120, 180, 20);
        add(l1);
        
         JLabel l2 = new JLabel();
        l2.setBounds(200, 170, 180, 20);
        add(l2);
          try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter = '"+c1.getSelectedItem()+"'");
            while(rs.next()){
                l1.setText(rs.getString("name"));
                l2.setText(rs.getString("address"));
            }
        }catch(Exception e){}
        
        c1.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ae){
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from customer where meter = '"+c1.getSelectedItem()+"'");
                    while(rs.next()){
                        l1.setText(rs.getString("name"));
                        l2.setText(rs.getString("address"));
                    }
                }catch(Exception e){}
            }
        });
        
        b1 = new JButton("Submit");
        b1.addActionListener(this);
        b1.setBounds(100, 350, 100, 25);
        add(b1);
        
        b2 = new JButton("Cancel");
        b2.addActionListener(this);
        b2.setBounds(230, 350, 100, 25);
        add(b2);
      
        
        
       
        

        getContentPane().setBackground(Color.WHITE);        
       setLayout(null);
        setBounds(550,300,600,540);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            String meter_no = c1.getSelectedItem();
            String units = t1.getText();
            String month = c2.getSelectedItem();

            int units_consumed = Integer.parseInt(units);

            int total_bill = 0;
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from tax");
                while(rs.next()){
                    total_bill = units_consumed * Integer.parseInt(rs.getString("cost_per_unit")); // 120 * 7
                    total_bill += Integer.parseInt(rs.getString("meter_rent"));
                    total_bill += Integer.parseInt(rs.getString("service_charge"));
                    total_bill += Integer.parseInt(rs.getString("service_tax"));
                    total_bill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    total_bill += Integer.parseInt(rs.getString("fixed_tax"));
                }
            }catch(Exception e){}

            String q = "insert into bill values('"+meter_no+"','"+month+"','"+units+"','"+total_bill+"', 'Not Paid')";

            try{
                Conn c1 = new Conn();
                c1.s.executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Customer Bill Updated Successfully  : - ₹ (rupees)" + total_bill);
                this.setVisible(false);
            }catch(Exception aee){
                aee.printStackTrace();
            }

        }else if(ae.getSource()== b2){
            this.setVisible(false);
        }        
    }
    
       
    public static void main(String[] args){
        new CalculateBill().setVisible(true);
    }
}
