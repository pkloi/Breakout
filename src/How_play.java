import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class How_play extends JDialog{
    JDialog how_p=new JDialog();

//    放图片
    JLabel left1=new JLabel();
    JLabel left2=new JLabel();
    JLabel right1=new JLabel();
    JLabel right2=new JLabel();
    JLabel space=new JLabel();

//    文字
    JLabel l=new JLabel("左移：");
    JLabel r=new JLabel("右移：");
    JLabel ss=new JLabel("开始：");

//    空
    JLabel kong =new JLabel();

//    放确定按钮
    JLabel label3=new JLabel();

    public How_play(JFrame father){

        how_p.setTitle("玩法");
        how_p.setLayout(new FlowLayout(FlowLayout.CENTER,5,10));
        how_p.setResizable(false);

//        文字样式
        l.setFont(new Font("微软雅黑",Font.BOLD,15));
        l.setForeground(Color.WHITE);
        r.setFont(new Font("微软雅黑",Font.BOLD,15));
        r.setForeground(Color.WHITE);
        ss.setFont(new Font("微软雅黑",Font.BOLD,15));
        ss.setForeground(Color.WHITE);

//      把图片添加进去
        ImageIcon l1=new ImageIcon("imgs/left.png");
        l1.setImage(l1.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));

        left1.setSize(50,50);
        left1.setIcon(l1);

        ImageIcon l2=new ImageIcon("imgs/A.png");
        l2.setImage(l2.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));

        left2.setSize(50,50);
        left2.setIcon(l2);

        ImageIcon r1=new ImageIcon("imgs/right.png");
        r1.setImage(r1.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));

        right1.setSize(50,50);
        right1.setIcon(r1);

        ImageIcon r2=new ImageIcon("imgs/D.png");
        r2.setImage(r2.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));

        right2.setSize(50,50);
        right2.setIcon(r2);

        ImageIcon s=new ImageIcon("imgs/space.png");
        s.setImage(s.getImage().getScaledInstance(100,40, Image.SCALE_DEFAULT));

        space.setSize(110,50);
        space.setIcon(s);


//        确定按钮
        ImageIcon sure=new ImageIcon("imgs/确定.png");
        sure.setImage(sure.getImage().getScaledInstance(80,28, Image.SCALE_DEFAULT));

        label3.setSize(100,40);
        label3.setIcon(sure);

//        对确定按钮添加点击事件
        label3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                How_play.this.how_p.dispose();
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

        how_p.setUndecorated(true);
        how_p.setBackground(new Color(0,0,0,70));

        how_p.getContentPane().add(l);
        how_p.getContentPane().add(left1);
        how_p.getContentPane().add(left2);

        how_p.getContentPane().add(r);
        how_p.getContentPane().add(right1);
        how_p.getContentPane().add(right2);

        how_p.getContentPane().add(ss);
        how_p.getContentPane().add(space);

        how_p.getContentPane().add(label3);

        how_p.setBounds((Game.game_width-180)/2,(Game.game_height-200)/2,180,200);

        how_p.setVisible(true);

    }

}
