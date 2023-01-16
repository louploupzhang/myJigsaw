package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyJFrame extends JFrame implements ActionListener {
    JButton jtb1 = new JButton("Click Me");
    JButton jtb2 = new JButton("Click Me Again");

    public MyJFrame(){
        this.setSize(603,680);
        this.setTitle("Jigsaw v1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);

        jtb1.setBounds(0,0,100,50);
        jtb1.addActionListener(this);

        jtb2.setBounds(100,0,100,50);
        jtb2.addActionListener(this);

        this.getContentPane().add(jtb1);
        this.getContentPane().add(jtb2);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == jtb1){
            jtb1.setSize(200,200);
        }else if (source == jtb2){
            Random r = new Random();
            jtb2.setLocation(r.nextInt(500),r.nextInt(500));
        }
    }
}
