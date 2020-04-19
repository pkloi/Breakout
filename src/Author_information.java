import javax.swing.*;
import java.awt.*;

public class Author_information extends JDialog {
    public Author_information(Frame owner, String title) {
        super(owner, title);

//        设置布局
        setLayout(new FlowLayout(FlowLayout.CENTER,5,5));

//        信息
        JLabel label1=new JLabel("Made by 计科N171 卢思敏",JLabel.CENTER);
        JLabel label2=new JLabel(new ImageIcon("imgs/1.jpg"));
        JLabel label3=new JLabel("©2020",JLabel.CENTER);
        add(label1);
        add(label2);
        add(label3);

//        可视化
        setVisible(false);

//        弹窗大小
        setBounds(100,100,200,180);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
