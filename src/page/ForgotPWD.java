package page;

import common.MyUtil;
import common.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ForgotPWD extends JFrame implements ActionListener, FocusListener, WindowListener {
    Container c;

    public ForgotPWD(){
        addWindowListener(this);
        initJFrame();

        initLayout();

        setVisible(true);
    }

    JTextField qqNumber;
    JTextField phoneNumber;
    JButton submit;
    private void initLayout() {
        c.removeAll();

        // QQ号输入框
        qqNumber = new JTextField("请输入QQ号码");
        qqNumber.setBounds(185, 130, 250, 30);
        c.add(qqNumber);
        qqNumber.addFocusListener(this);

        // 手机号输入框
        phoneNumber = new JTextField("请输入手机号");
        phoneNumber.setBounds(185, 230, 250, 30);
        c.add(phoneNumber);
        phoneNumber.addFocusListener(this);

        submit = new JButton("找回");
        submit.setBounds(250, 330, 100, 30);
        c.add(submit);
        submit.addActionListener(this);

        // 背景图片
        JLabel bcg = new JLabel(new ImageIcon("image/forgotPWD/ForgotBCG.jpg"));
        bcg.setBounds(0,0,600, 500);
        c.add(bcg);

        c.repaint();
    }

    private void initJFrame() {
        c = this.getContentPane();
        setTitle("忘记密码");
        setSize(600, 500);
        setLocationRelativeTo(null); // 居中显示
        this.setLayout(null);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == submit){
            String qq = qqNumber.getText();
            String phone = phoneNumber.getText();

            ArrayList<User> users = MyJFrame.users;
            String pwd = "";
            for (User u : users) {
                String qqNum = u.getQqNumber();
                String phoneNum = u.getPhoneNumber();

                if (qqNum.equals(qq) && phoneNum.equals(phone)) {
                    pwd = u.getPassword();
                }
            }

            if (pwd.equals("")){
                MyUtil.createDiag(this, "提示信息", "输入的信息有误，找回失败");
            } else {
                MyUtil.createDiag(this, "密码", "您的密码为：" + pwd);
            }

        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        Object obj = e.getSource();
        if (obj == qqNumber){
            if (qqNumber.getText().equals("请输入QQ号码")){
                qqNumber.setText("");
            }
        } else if (obj == phoneNumber){
            if (phoneNumber.getText().equals("请输入手机号")){
                phoneNumber.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        Object obj = e.getSource();
        if (obj == qqNumber){
            if (qqNumber.getText().equals("")){
                qqNumber.setText("请输入QQ号码");
            }
        } else if (obj == phoneNumber){
            if (phoneNumber.getText().equals("")){
                phoneNumber.setText("请输入手机号");
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        new MyJFrame();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
