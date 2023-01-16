package com.kleer.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {

    public RegisterJFrame(){
        this.setSize(488,500);
        this.setTitle("MyJigsaw Register");
        this.setAlwaysOnTop(true);
        //Set location in the center
        this.setLocationRelativeTo(null);
        //Set close mode, 0: do nothing on close, 1: hide on close, 2: dispose en close, 3: exit en close
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }
}
