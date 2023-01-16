package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestEvent {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(603,680);
        jFrame.setTitle("Event Demo");
        jFrame.setAlwaysOnTop(true);
        jFrame.setLocationRelativeTo(null); //Center
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(null); //Disable the default setting

        //Create a button object
        JButton jtb = new JButton("Click Me");
        jtb.setBounds(0,0,100,50);
        //jtb.addActionListener(new MyActionListener()); //Listener: left click & space
        jtb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked.");
            }
        });

        //Put button in to the UI
        jFrame.getContentPane().add(jtb);

        jFrame.setVisible(true);
    }
}
