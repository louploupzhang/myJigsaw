package com.kleer.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements MouseListener {
    //Create users
    static ArrayList<User> list = new ArrayList<>();

    //Create buttons
    JButton login = new JButton();
    JButton register = new JButton();

    //Input area
    JTextField username = new JTextField();
    JTextField password = new JPasswordField();
    JTextField code = new JTextField();
    JLabel rightCode = new JLabel();

    static {
        list.add(new User("zhangsan", "123"));
        list.add(new User("lisi", "1234"));
    }

    public LoginJFrame() {
        initFrame();
        initView();
        this.setVisible(true);
    }

    public void initView() {
        //1. 添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("image\\login\\用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("image\\login\\密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordText);

        //4.密码输入框
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);

        //验证码提示
        JLabel codeText = new JLabel(new ImageIcon("image\\login\\验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //验证码的输入框
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);
        String codeStr = CodeUtil.getCode();
        //设置内容
        rightCode.setText(codeStr);
        //位置和宽高
        rightCode.setBounds(300, 256, 50, 30);
        //添加到界面
        this.getContentPane().add(rightCode);

        //5.添加登录按钮
        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        //去除按钮的默认边框
        login.setBorderPainted(false);
        //去除按钮的默认背景
        login.setContentAreaFilled(false);
        this.getContentPane().add(login);

        //6.添加注册按钮
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        //去除按钮的默认边框
        register.setBorderPainted(false);
        //去除按钮的默认背景
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);

        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);

        //Bind events
        login.addMouseListener(this);
        register.addMouseListener(this);
    }

    public void initFrame() {
        this.setSize(488, 430);
        this.setTitle("MyJigsaw V1.0 Login");
        this.setAlwaysOnTop(true);
        //Set location in the center
        this.setLocationRelativeTo(null);
        //Set close mode, 0: do nothing on close, 1: hide on close, 2: dispose en close, 3: exit en close
        this.setDefaultCloseOperation(3);
        //Disable the default layout
        this.setLayout(null);
    }

    //要展示用户名或密码错误
    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == login) {
            //Get login info
            String usrLogin = username.getText();
            String pwdLogin = password.getText();
            User usrInfo = new User(usrLogin, pwdLogin);
            //Check code
            if (!checkCode(rightCode.getText())) {
                showJDialog("Invalid code!");
            } else {
                //Check login info
                if (!checkLoginInfo(usrInfo)) {
                    showJDialog("Invalid username or password!");
                } else {
                    //Switch to game UI if login info are right
                    this.setVisible(false);
                    new GameJFrame();
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == login) {
            login.setIcon(new ImageIcon("image\\login\\登录按下.png"));
        } else if (obj == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        } else if (obj == rightCode){
            rightCode.setText(CodeUtil.getCode());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == login) {
            login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        } else if (obj == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private boolean checkCode(String c) {
        if (c.equals(code.getText())) {
            return true;
        }
        return false;
    }

    private boolean checkLoginInfo(User u) {
        if (u.getName().equals("") || u.getPwd().equals("")) {
            showJDialog("Username or password cannot be empty!");
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (u.getName().equals(list.get(i).getName()) && u.getPwd().equals(list.get(i).getPwd())) {
                return true;
            }
        }
        return false;
    }
}
