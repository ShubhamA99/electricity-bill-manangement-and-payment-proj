/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Electricity;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Firstpage extends JFrame implements ActionListener{
    
    JButton b1,b2; 
    Firstpage(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Electricity\\icon\\logo.png"));
       
        JLabel l1 = new JLabel(i1);
        l1.setBounds(0,0,680,450);
        add(l1);
        
        
        
        
        b1 = new JButton("Login");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(250,410,150,30);
        b1.addActionListener(this);
        add(b1);
        
       
        
         setBounds(550,300,700,565);
        
        
        getContentPane().setBackground(Color.WHITE);
        
        setLayout(null);
       setVisible(true);
    }

public void actionPerformed(ActionEvent ae){
      new Login().setVisible(true);
      this.setVisible(false);
    }
public static void main(String[] args){
new Firstpage().setVisible(true);
}
}