package page;

import common.DragEvent;
import common.MyUtil;
import common.User;
import myException.MyException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.util.ArrayList;

public class MyJFrame extends JFrame implements ActionListener, FocusListener {

    // 用户集合
    static ArrayList<User> users = new ArrayList<>();
    JLabel banner;
    Container c;

    static {
        User u1 = new User("夏天", "53689", "r123456", "13456816358");
        User u2 = new User("士兵突击", "23456", "r1234", "15935684589");
        User u3 = new User("鳄鱼", "56489", "r123", "15686412589");

        users.add(u1);
        users.add(u2);
        users.add(u3);
    }

    public MyJFrame() {
        initJFrame();

        initLayout();

        autoLogin();


    }

    JButton zuixiao; // 最小化按钮
    JButton close; // 关闭按钮
    JTextField username; // 账号
    JPasswordField password; // 密码
    JButton registration; // 注册按钮
    JButton login; // 登录按钮
    JCheckBox memoryPWD; // 记住密码
    JCheckBox autoLogin; // 自动登录
    JButton forgetPWD; // 找回密码

    private void initLayout() {
        c.removeAll();

        // banner图容器
        JPanel jp2 = new JPanel(null);
        jp2.setBounds(0, 0, 414, 176);

        // 右上角下拉菜单
        JMenuBar xiala = new JMenuBar();
        JMenu jMenu = new JMenu("▼");
        JMenuItem setting = new JMenuItem("设置");
        JMenuItem qhtx = new JMenuItem("切换头像");
        xiala.setBounds(330, 0, 28, 17);
        xiala.setBackground(new Color(0, 130, 188));
        xiala.setBorderPainted(false);
        jMenu.add(setting);
        jMenu.add(qhtx);
        xiala.add(jMenu);
        jp2.add(xiala);

        // 最小化
        zuixiao = new JButton(new ImageIcon("image/main/zuixiao.jpg"));
        zuixiao.setBounds(358, 0, 28, 17);
        zuixiao.setBackground(Color.YELLOW);
        zuixiao.setBorderPainted(false);
        jp2.add(zuixiao);
        zuixiao.addActionListener(this);

        // 关闭按钮
        close = new JButton(new ImageIcon("image/main/close.jpg"));
        close.setBounds(386, 0, 28, 17);
        close.setBorderPainted(false);
        jp2.add(close);
        close.addActionListener(this);

        // QQ-banner图
        banner = new JLabel(new ImageIcon("image/main/banner.jpg"));
        banner.setLayout(null);
        banner.setBounds(0, 0, 414, 176);
        banner.setVisible(true);
        jp2.add(banner);
        DragEvent.initDragEvent(this, banner);

        // 下面面板容器
        JPanel jp1 = new JPanel(null);
        jp1.setBounds(0, 176, 414, (331 - 176));
        jp1.setBackground(Color.WHITE);

        // 头像
        JLabel img = new JLabel(new ImageIcon("image/head/head1.jpg"));
        img.setBounds(25, 10, 100, 100);
        jp1.add(img);

        // 状态
        JComboBox<String> status = new JComboBox<>();
        status.setBounds(10, 80, 80, 20);
        status.addItem("我在线上");
        status.addItem("Q我吧");
        status.addItem("离开");
        status.addItem("忙碌");
        status.addItem("请勿打扰");
        status.addItem("隐身");
        img.add(status);

        String uName = "QQ号码";
        String uPwd = "密码";

        //MyUtil.createFile("file", "filerememberPsw.txt", "");
        try {
            BufferedReader br = new BufferedReader(new FileReader("file/filerememberPsw.txt"));

            String row = "";
            while (true) {
                try {
                    if ((row = br.readLine()) == null) break;
                } catch (IOException e) {
                    throw new MyException("记住密码文件读取为空");
                }
                uName = row;
                try {
                    uPwd = br.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileNotFoundException e) {
            MyUtil.createFile("file", "fileremeberPsw.txt", "");
        }



        // 账号输入框
        username = new JTextField(uName);
        username.setBounds(150, 20, 155, 25);
        jp1.add(username);
        username.addFocusListener(this);

        // 密码输入框
        password = new JPasswordField();
        if (uPwd.equals("密码")) {
            password.setEchoChar('\0');
        } else {
            password.setEchoChar('*');
        }
        password.setText(uPwd);
        password.setBounds(150, 55, 155, 25);
        jp1.add(password);
        password.addFocusListener(this);

        // 注册账号
        registration = new JButton("注册账号");
        registration.setBounds(315, 20, 85, 25);
        registration.setBorderPainted(false);
        jp1.add(registration);
        registration.addActionListener(this);

        // 找回密码
        forgetPWD = new JButton("找回密码");
        forgetPWD.setBounds(315, 55, 85, 25);
        forgetPWD.setBorderPainted(false);
        jp1.add(forgetPWD);
        forgetPWD.addActionListener(this);


        boolean flag = false;
        while (true) {
            try (
                    BufferedReader br1 = new BufferedReader(new FileReader("file/fileAutologin.txt"))
            ) {
                String s = br1.readLine();
                if (s.equals("true")) {
                    flag = true;
                } else if (s.equals("false")) {
                    flag = false;
                }

                break;
            } catch (FileNotFoundException e) {
                MyUtil.createFile("file", "fileAutologin.txt", "false");
            } catch (IOException e) {
                throw new MyException("文件读取异常");
            }
        }
        // 自动登录
        autoLogin = new JCheckBox("自动登录");
        autoLogin.setBounds(150, 90, 77, 25);
        autoLogin.setBackground(Color.WHITE);
        if (flag) {
            autoLogin.setSelected(true);
        } else {
            autoLogin.setSelected(false);
        }
        jp1.add(autoLogin);

        // 记住密码
        memoryPWD = new JCheckBox("记住密码");
        memoryPWD.setBounds(237, 90, 77, 25);
        memoryPWD.setBackground(Color.WHITE);

        if (!uName.equals("QQ号码")) {
            memoryPWD.setSelected(true);
        }

        jp1.add(memoryPWD);

        // 登录按钮
        login = new JButton(new ImageIcon("image/main/login.png"));
        login.setBounds(132, 120, 150, 25);
        jp1.add(login);
        login.addActionListener(this);

        c.add(jp2);
        c.add(jp1);

        c.repaint();
    }

    private void initJFrame() {
        c = this.getContentPane();
        setTitle("QQ");
        setSize(414, 331);
        setUndecorated(true); // 取消默认边框
        setLocationRelativeTo(null); // 居中显示
        this.setLayout(null);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void autoLogin() {
        try (
                BufferedReader br = new BufferedReader(new FileReader("file/fileAutologin.txt"))
        ) {
            String con = br.readLine();
            if (con.equals("true")) { // 自动登录
                MyUtil.createDiag(this, "登陆提示", "自动登陆成功！");
                loginMethod();
                setVisible(false);
            } else if (con.equals("false")) { // 不自动登录
                setVisible(true);
            }
        } catch (FileNotFoundException e) {
            MyUtil.createFile("file", "fileAutologin.txt", "false");
        } catch (IOException e) {
            throw new MyException("文件读取失败");
        }
    }

    // 动作事件
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == zuixiao) {
            this.setExtendedState(JFrame.ICONIFIED); // 窗口最小化
        } else if (obj == close) {
            System.exit(0);
        } else if (obj == registration) {
            new RegJFrame();
            setVisible(false);
        } else if (obj == login) {
            loginMethod();
        } else if (obj == forgetPWD) {
            dispose();
            new ForgotPWD();
        }
    }

    public void loginMethod() {

        String uname = username.getText();
        String pwd = new String(password.getPassword());
        String uName = "";

        boolean flag = false;

        for (User user : users) {
            String currentQQNumber = user.getQqNumber();
            String currentPwd = user.getPassword();
            uName = user.getUsername();
            Welcome.username = uName;

            if (currentQQNumber.equals(uname) && currentPwd.equals(pwd)) {
                flag = true;
                break;
            }
        }

        if (flag) {
            // 自动登录是否选中
            if (autoLogin.isSelected()) {
                memoryPWD.setSelected(true);
                while (true) {
                    try (
                            BufferedWriter bw = new BufferedWriter(new FileWriter("file/fileAutologin.txt"));
                    ) {

                        bw.write("true");
                        break;
                    } catch (IOException ex) {
                        MyUtil.createFile("file", "fileAutologin.txt", "true");
                    }
                }
            } else { // 自动登录未选中
                while (true) {
                    try (
                            BufferedWriter bw = new BufferedWriter(new FileWriter("file/fileAutologin.txt"));
                    ) {

                        bw.write("false");
                        break;
                    } catch (IOException ex) {
                        MyUtil.createFile("file", "fileAutologin.txt", "false");
                    }
                }
            }

            // 记住密码是否选中
            if (memoryPWD.isSelected()) {
                try (
                        BufferedWriter bw = new BufferedWriter(new FileWriter("file/filerememberPsw.txt"))
                ) {
                    bw.write(uname);
                    bw.newLine();
                    bw.write(pwd);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                try {
                    new BufferedWriter(new FileWriter("file/filerememberPsw.txt")).write("");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            dispose();
            MyUtil.createDiag(this, "登陆成功", "尊敬的 " + uName + " 用户，欢迎使用QQ~~");
            new Welcome();
        } else {
            MyUtil.createDiag(this, "登陆失败", "账户或密码错误，请重试！");
        }
    }

    // 焦点获取事件
    @Override
    public void focusGained(FocusEvent e) {
        Object obj = e.getSource();
        if (obj == username) {
            if ("QQ号码".equals(username.getText())) {
                username.setText("");
            }
        } else if (obj == password) {
            String pwd = new String(password.getPassword());
            if ("密码".equals(pwd)) {
                password.setText("");
                password.setEchoChar('*');
            }
        }
    }

    // 焦点丢失事件
    @Override
    public void focusLost(FocusEvent e) {
        Object obj = e.getSource();
        if (obj == username) {
            if ("".equals(username.getText())) {
                username.setText("QQ号码");
            }
        } else if (obj == password) {
            String pwd = new String(password.getPassword());
            if ("".equals(pwd)) {
                password.setEchoChar('\0');
                password.setText("密码");
            }
        }
    }
}
