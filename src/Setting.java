import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Setting{
    JDialog set_window=new JDialog();

    JLabel label3=new JLabel();

    JLabel l1=new JLabel("设置音量",JLabel.CENTER);
    JLabel l2=new JLabel();
    JLabel l3=new JLabel();
    JLabel l4=new JLabel();
    JLabel l5=new JLabel("（默认音量为最大,切换界面即可改变声音）",JLabel.CENTER);

    public Setting(JFrame father){
        set_window.setLayout(new FlowLayout());
        set_window.setResizable(false);

//      把图片添加进去
        ImageIcon big=new ImageIcon("imgs/音量大.png");
        big.setImage(big.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));

        ImageIcon middle=new ImageIcon("imgs/音量中.png");
        middle.setImage(middle.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));

        ImageIcon small=new ImageIcon("imgs/音量小.png");
        small.setImage(small.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));

//        设置label
        l1.setPreferredSize(new Dimension(300,50));
        l1.setFont(new Font("微软雅黑",Font.BOLD,20));
        l1.setForeground(new Color(255,255,255));

        l2.setIcon(big);
        l3.setIcon(middle);
        l4.setIcon(small);

        l5.setPreferredSize(new Dimension(300,50));
        l5.setFont(new Font("微软雅黑",Font.BOLD,12));
        l5.setForeground(new Color(255,255,255));

//        确定按钮
        ImageIcon sure=new ImageIcon("imgs/确定.png");
        sure.setImage(sure.getImage().getScaledInstance(80,28, Image.SCALE_DEFAULT));

        label3.setIcon(sure);

//        对确定按钮添加点击事件
        label3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Setting.this.set_window.dispose();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                label3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                label3.setCursor(Cursor.getDefaultCursor());
            }
        });

        l2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Music.sound=0;
                System.out.println("声音最大，修改成功！");
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                l2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                l2.setCursor(Cursor.getDefaultCursor());
            }
        });

        l3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Music.sound=18;
                System.out.println("声音中等，修改成功！");
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                l3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                l3.setCursor(Cursor.getDefaultCursor());
            }
        });

        l4.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Music.sound=25;
                System.out.println("声音最小，修改成功！");
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                l4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                l4.setCursor(Cursor.getDefaultCursor());
            }
        });

        set_window.setUndecorated(true);
        set_window.setBackground(new Color(0,0,0,70));

        set_window.getContentPane().add(l1);
        set_window.getContentPane().add(l2);
        set_window.getContentPane().add(l3);
        set_window.getContentPane().add(l4);
        set_window.getContentPane().add(l5);
        set_window.getContentPane().add(label3);

        set_window.setBounds((Game.game_width-300)/2,(Game.game_height-200)/2,300,200);

        set_window.setVisible(true);

    }
}
