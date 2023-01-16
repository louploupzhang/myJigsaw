package com.kleer.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //Create a 2-dimensional array as the container for images
    int[][] data = new int[4][4];
    //Create a 2D array as the win check object
    int[][] win = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    //Variable for win check
    boolean v = false;
    //Declare the variables for the axis of element 0
    int x = 0, y = 0;
    //Declare the variable to store the image's path
    String path = "image\\animal\\animal3\\";
    //Declare the variable for the step count
    int step = 0;
    //Create sub tab entries
    JMenuItem girl = new JMenuItem("Girls");
    JMenuItem animal = new JMenuItem("Animals");
    JMenuItem sport = new JMenuItem("Sports");
    JMenuItem replayItem = new JMenuItem("Restart");
    JMenuItem reLoginItem = new JMenuItem("Re-login");
    JMenuItem closeItem = new JMenuItem("Exit");
    JMenuItem aboutMeItem = new JMenuItem("About Me");

    public GameJFrame() {
        //Initialize the UI
        initJFrame();

        //Initialize the menu
        initJMenuBar();

        //Initialize data (shuffle)
        initData();

        //Initialize the images (after the shuffle)
        initImage();

        this.setVisible(true);
    }

    private void initData() {
        //Create a 1-dimensional temp array for images
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        //Shuffle the elements by swapping the current element with a random index during the traversal
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        //Print 1D array
        for (int i = 0; i < tempArr.length; i++) {
            System.out.print(tempArr[i] + " ");
        }
        System.out.println();

        //Fill the 2-dimensional array
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tempArr[i];

        }
        //Print 2D array
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }

    }

    private void initImage() {
        //Clear panel
        this.getContentPane().removeAll();

        //Check if win
        if (victory()) {
            JLabel winJLabel = new JLabel(new ImageIcon("image\\win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        //Step count UI
        JLabel stepCount = new JLabel("Steps: " + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);

        //Outer loop: loop the inner loop for 4 times
        for (int i = 0; i < 4; i++) {
            //Inner loop: add 4 images in one row
            for (int j = 0; j < 4; j++) {
                //Get the number of current image
                int num = data[i][j];
                //Create the object of the ImageIcon and create a JLabel as a manager
                JLabel jLabel = new JLabel(new ImageIcon(path + num + ".jpg"));
                //Set position of the image
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //Add border (0:raised, 1:lowered)
                jLabel.setBorder(new BevelBorder(1));
                //Add the JLabel to UI
                this.getContentPane().add(jLabel);
            }
        }

        //Add background image
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        //Refresh panel
        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        //Create the object of the whole menu
        JMenuBar jMenuBar = new JMenuBar();
        //Create tabs
        JMenu optionMenu = new JMenu("Option");
        JMenu aboutMenu = new JMenu("About Us");
        JMenu changeImage = new JMenu("Change Image");


        //Make connections in the menu
        optionMenu.add(replayItem);
        optionMenu.add(reLoginItem);
        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);
        optionMenu.add(changeImage);
        optionMenu.add(closeItem);
        aboutMenu.add(aboutMeItem);

        //Bind event to the items
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        aboutMeItem.addActionListener(this);
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);

        //Make connections in the menu bar
        jMenuBar.add(optionMenu);
        jMenuBar.add(aboutMenu);

        //Set menu for the UI
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603, 680);
        this.setTitle("MyJigsaw V1.0");
        this.setAlwaysOnTop(true);
        //Set location in the center
        this.setLocationRelativeTo(null);
        //Set close mode, 0: do nothing on close, 1: hide on close, 2: dispose en close, 3: exit en close
        this.setDefaultCloseOperation(3);
        //Cancel the default layout mode (always in center)
        this.setLayout(null);
        //Add event listener
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //Call this method when holding keys
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            //Clear the panel
            this.getContentPane().removeAll();
            //Load the full image
            JLabel full = new JLabel(new ImageIcon(path + "all.jpg"));
            full.setBounds(83, 134, 420, 420);
            this.getContentPane().add(full);
            //Load the background
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            //Refresh panel
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Lock the panel if win already
        if (victory()) {
            return;
        }

        //left:37, up:38, right:39, down:40
        int code = e.getKeyCode();
        switch (code) {
            case 37 -> { //Move left
                //Bound check
                if (y >= data[0].length - 1) {
                    return;
                }
                System.out.println("Move left");
                //Element 0: y + 1
                data[x][y] = data[x][y + 1];
                data[x][y + 1] = 0;
                y++;
                step++;
                //Reload
                initImage();
            }
            case 38 -> { //Move up
                //Bound check
                if (x >= data.length - 1) {
                    return;
                }
                System.out.println("Move up");
                //Element 0: x + 1
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                x++;
                step++;
                //Reload
                initImage();
            }
            case 39 -> { //Move right
                //Bound check
                if (y <= 0) {
                    return;
                }
                System.out.println("Move right");
                //Element 0: y - 1
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                y--;
                step++;
                //Reload
                initImage();
            }
            case 40 -> { //Move down
                //Bound check
                if (x <= 0) {
                    return;
                }
                System.out.println("Move down");
                //Element 0: x - 1
                data[x][y] = data[x - 1][y];
                data[x - 1][y] = 0;
                x--;
                step++;
                //Reload
                initImage();
            }
            case 65 -> { //Show the full image
                //Reload
                initImage();
            }
            case 87 -> { //Cheat mode
                data = win;
                //Reload
                initImage();
            }
            default -> System.out.println("Invalid input!");
        }
    }

    //Check if data matches win
    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == replayItem) {
            System.out.println("Restart");
            //Reset steps, must reset before initImage()
            step = 0;
            //Shuffle
            initData();
            //Reload images
            initImage();
        } else if (obj == reLoginItem) {
            System.out.println("Re-login");
            //Close current UI
            this.setVisible(false);
            //Back to log-in UI
            new LoginJFrame();
        } else if (obj == closeItem) {
            System.out.println("Exit");
            System.exit(0);
        } else if (obj == aboutMeItem) {
            System.out.println("About Me");
            //Create a pop-up object
            JDialog jDialog = new JDialog();

            JLabel jLabel = new JLabel("Email: zhang.sy@hotmail.com");
            jLabel.setBounds(0,0, 255, 100);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(255,100);
            jDialog.setAlwaysOnTop(true);
            //Center the pop-up
            jDialog.setLocationRelativeTo(null);
            //Disable the following actions if the current pop-up window is not closed
            jDialog.setModal(true);

            jDialog.setVisible(true);
        } else if (obj == girl) {
            System.out.println("Changing image to girls");
            //Change path
            Random r = new Random();
            path = "image\\girl\\girl" + r.nextInt(1,12) + "\\";

            //Reset steps, must reset before initImage()
            step = 0;
            //Shuffle
            initData();
            //Reload images
            initImage();
        }else if (obj == animal) {
            System.out.println("Changing image to animals");
            //Change path
            Random r = new Random();
            path = "image\\animal\\animal" + r.nextInt(1, 9) + "\\";

            //Reset steps, must reset before initImage()
            step = 0;
            //Shuffle
            initData();
            //Reload images
            initImage();
        }else if (obj == sport) {
            System.out.println("Changing image to sports");
            //Change path
            Random r = new Random();
            path = "image\\sport\\sport" + r.nextInt(1, 11) + "\\";

            //Reset steps, must reset before initImage()
            step = 0;
            //Shuffle
            initData();
            //Reload images
            initImage();
        }
    }
}
