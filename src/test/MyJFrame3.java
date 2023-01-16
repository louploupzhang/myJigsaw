package test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJFrame3 extends JFrame implements KeyListener {

    public MyJFrame3(){
        this.setSize(603,680);
        this.setTitle("Jigsaw v1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.addKeyListener(this);

        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Pressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Released");
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case 65 -> System.out.println("Key A");
            case 66 -> System.out.println("Key B");
        }
    }
}
