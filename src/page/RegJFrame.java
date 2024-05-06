package page;

import common.MyUtil;
import common.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RegJFrame extends JFrame implements ActionListener, FocusListener, WindowListener {
    Container c;
    public RegJFrame(){
        addWindowListener(this);
        initJFrame();

        initImg();
    }

    JTextField username;
    JTextField phoneNumber;
    JTextField pwdInput;
    JButton reg;
    JRadioButton agree;
    private void initImg() {
        c.removeAll();

        // 文字描述
        JPanel display = new JPanel(null);
        display.setBounds(185, 30, 600, 200);
        display.setBackground(new Color(0,0,0,0));

        JLabel welcome = new JLabel("欢迎注册QQ");
        welcome.setBounds(0, 0, 200, 30);
        welcome.setFont(new Font("微软雅黑", Font.BOLD, 30));
        display.add(welcome);
        JLabel everyDay = new JLabel("每一天，乐在沟通");
        everyDay.setBounds(0, 30, 200, 30);
        everyDay.setFont(new Font("微软雅黑", Font.BOLD, 14));
        display.add(everyDay);
        JLabel hi = new JLabel("Hi~");
        hi.setBounds(200, 0, 200, 60);
        hi.setFont(new Font("微软雅黑", Font.BOLD, 32));
        display.add(hi);

        c.add(display);

        // 昵称输入框
        username = new JTextField("昵称");
        username.setBounds(185, 130, 250, 30);
        c.add(username);
        username.addFocusListener(this);

        // 手机号输入框
        phoneNumber = new JTextField("手机号码");
        phoneNumber.setBounds(185, 180, 250, 30);
        c.add(phoneNumber);
        phoneNumber.addFocusListener(this);

        // 密码输入框
        pwdInput = new JTextField("密码");
        pwdInput.setBounds(185, 230, 250, 30);
        c.add(pwdInput);
        pwdInput.addFocusListener(this);

        // 单选框同意协议
        agree = new JRadioButton("我已阅读并同意服务协议和QQ隐私保护指引");
        agree.setBounds(185, 270, 250, 30);
        agree.setFont(new Font("楷体", Font.PLAIN, 10));
        agree.setBackground(new Color(191, 207, 180));
        c.add(agree);

        // 立即注册按钮
        reg = new JButton(new ImageIcon("image/Reg/regist.png"));
        reg.setBounds(185, 310, 238, 34);
        c.add(reg);
        reg.addActionListener(this);

        // 背景图片
        JLabel bgi = new JLabel(new ImageIcon("image/Reg/regBCI.jpg"));
        bgi.setBounds(0, 0, 600, 600);
        c.add(bgi);

        c.repaint();
    }

    private void initJFrame() {
        c = this.getContentPane();
        setTitle("QQ注册页面");
        setSize(600, 500);
        setLocationRelativeTo(null); // 居中显示
        setResizable(false);
        this.setLayout(null);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    private String createQQ(){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            int num = r.nextInt(10);
            sb.append(num);
        }
        return sb.toString();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == reg){
            if (agree.isSelected()){
                // 选中
                String uname = username.getText();
                String pwd = pwdInput.getText();
                String pNum = phoneNumber.getText();

                boolean isUname = uname.equals("") || uname.equals("昵称");
                boolean isPwd = pwd.equals("") || pwd.equals("密码");
                boolean isPNum = pNum.equals("") || pNum.equals("手机号码");

                String qq = "";
                if (!isUname && !isPwd && !isPNum){
                    qq = createQQ();
                    User user = new User(uname, qq, pwd, pNum);
                    MyJFrame.users.add(user);
                    MyUtil.createDiag(this, "注册成功",  "您的QQ号码为：" + qq);
                    dispose();
                        new MyJFrame();
                } else {
                    MyUtil.createDiag(this, "注册提示", "请将信息输入完整");
                }
            }else {
               // 未选中
                MyUtil.createDiag(this, "注册提示", "请同意协议后再进行操作");
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        Object obj = e.getSource();
        if (obj == username){
            if (username.getText().equals("昵称")){
                username.setText("");
            }
        } else if (obj == phoneNumber){
            if (phoneNumber.getText().equals("手机号码")){
                phoneNumber.setText("");
            }
        } else if (obj == pwdInput) {
            if (pwdInput.getText().equals("密码")){
                pwdInput.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        Object obj = e.getSource();
        if (obj == username){
            if (username.getText().equals("")){
                username.setText("昵称");
            }
        } else if (obj == phoneNumber){
            if (phoneNumber.getText().equals("")){
                phoneNumber.setText("手机号码");
            }
        } else if (obj == pwdInput) {
            if (pwdInput.getText().equals("")){
                pwdInput.setText("密码");
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
