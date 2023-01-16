package test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyJFrame2 extends JFrame implements MouseListener {
    JButton jtb1 = new JButton("Click Me");

    public MyJFrame2(){
        this.setSize(603,680);
        this.setTitle("Jigsaw v1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);

        jtb1.setBounds(0,0,100,50);
        jtb1.addMouseListener(this);

        this.getContentPane().add(jtb1);
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Cursor entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Cursor exited");
    }
}
