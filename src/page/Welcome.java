package page;

import myException.MyException;
import page.MyJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Welcome extends JFrame implements ActionListener {
    static String username = "";
    Container c = null;
    Font f1 = new Font("微软雅黑", Font.BOLD, 30);
    Font f2 = new Font("微软雅黑", Font.BOLD, 16);
    public Welcome(){
        initJFrame();

        initLayout();

        setVisible(true);
    }
    JButton exitQQ; // 退出登录按钮
    private void initLayout() {
        String picBCG = ""; // 背景图片路径

        String greetStr = "";

        // 取出时间的小时数
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        System.out.println("当前时间为：" + time);
        String hour = time.split(" ")[1].split(":")[0];
        int hourNum = Integer.parseInt(hour);

        // 判断时间
        if (hourNum > 4 && hourNum < 12){ // 早上好
            picBCG = "image/welcome/morning.jpg";
            greetStr = "早上好，活力满满~";
            System.out.println("早上好");
        } else if (hourNum >= 12 && hourNum < 15) { //中午好
            picBCG = "image/welcome/noon.jpg";
            greetStr = "中午好，心情暖暖~";
            System.out.println("中午好");
        } else { // 晚上好
            picBCG = "image/welcome/evening.jpg";
            greetStr = "晚上好，今天辛苦了~";
            System.out.println("晚上好");
        }

        exitQQ = new JButton("退出登录");
        exitQQ.setBounds(300, 5, 84, 25);
        exitQQ.setBorderPainted(false);
        c.add(exitQQ);
        exitQQ.addActionListener(this);

        JLabel user = new JLabel(username);
        user.setFont(f1);
        user.setBounds(20,20, 400, 50);
        c.add(user);

        JLabel greet = new JLabel(greetStr);
        greet.setFont(f1);
        greet.setBounds(20,70, 400, 50);
        c.add(greet);

        JLabel everyDay = new JLabel("每一天，乐在沟通");
        everyDay.setFont(f2);
        everyDay.setBounds(20, 300, 200, 30);
        c.add(everyDay);

        JLabel welQQ = new JLabel("WELCOME TO QQ");
        welQQ.setFont(f2);
        welQQ.setForeground(Color.WHITE);
        welQQ.setBounds(200, 600, 200, 30);
        c.add(welQQ);

        // 背景图片
        JLabel bcg = new JLabel(new ImageIcon(picBCG));
        bcg.setBounds(0, 0, 400, 710);
        c.add(bcg);
    }

    private void initJFrame() {
        c = this.getContentPane();
        setTitle("欢迎登陆");
        setSize(400, 710);
        setLocationRelativeTo(null); // 居中显示
        setLayout(null);
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == exitQQ){
            setVisible(false);
            try (
                    BufferedWriter bw = new BufferedWriter(new FileWriter("file/fileAutologin.txt"))
            ){
                bw.write("false");
            } catch (IOException ex) {
                throw new MyException("文件不存在");
            }
            new MyJFrame();
        }
    }
}
