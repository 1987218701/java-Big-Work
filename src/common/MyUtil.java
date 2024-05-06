package common;

import myException.MyException;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyUtil {
    private MyUtil() {
    }

    public static void createDiag(Window owner, String title, String content) {
        JDialog jd = new JDialog(owner, title, Dialog.ModalityType.APPLICATION_MODAL);
        jd.setLocationRelativeTo(null);
        jd.setSize(300, 200);
        jd.setAlwaysOnTop(true);

        JLabel message = new JLabel(content);
        message.setSize(300, 200);
        message.setHorizontalAlignment(0);
        message.setVerticalAlignment(0);
        jd.add(message);

        jd.setVisible(true);
    }

    public static void createFile(String path, String filename, String str) {
        File pathname = new File(path);
        File file = new File(path, filename);

        pathname.mkdirs();

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new MyException("文件创建失败");
        }

        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(path + "/" + filename))
        ) {
            bw.write(str);
        } catch (IOException e) {
            throw new MyException("文件写入失败");
        }
    }

}
