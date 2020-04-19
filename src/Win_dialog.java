import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Win_dialog extends JDialog{
//        设置5秒消失
    int seconds=0;

    JDialog win_d=new JDialog();

//    放胜利图片
    JLabel label=new JLabel();

//    放倒计时
    JLabel label2=new JLabel();

//    放确定按钮
    JLabel label3=new JLabel();

    public Win_dialog(JFrame father,int sec){
        seconds=sec;

        win_d.setTitle("你胜利啦！！ヾ(￣ー￣)X(^▽^)ゞ");
        win_d.setLayout(new FlowLayout(FlowLayout.CENTER,300,5));
        win_d.setResizable(false);

//        倒计时
        label2.setFont(new Font("微软雅黑", Font.PLAIN, 10));

//      把图片添加进去
        ImageIcon win=new ImageIcon("imgs/win.png");
        win.setImage(win.getImage().getScaledInstance(247,179, Image.SCALE_DEFAULT));

        label.setSize(300,300);

//        新建在多少秒后消失,周期性执行
        ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();

        s.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Win_dialog.this.seconds--;
                if (Win_dialog.this.seconds==0){
                    Win_dialog.this.win_d.dispose();
                }
                else{
                    label2.setText("倒计时:"+seconds);
                    label.setIcon(win);
                }
            }
        },1,1, TimeUnit.SECONDS);

//        确定按钮
        ImageIcon sure=new ImageIcon("imgs/确定.png");
        sure.setImage(sure.getImage().getScaledInstance(80,28, Image.SCALE_DEFAULT));

        label3.setSize(100,40);
        label3.setIcon(sure);

//        对确定按钮添加点击事件
        label3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Win_dialog.this.win_d.dispose();
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

        win_d.setUndecorated(true);
        win_d.setBackground(new Color(0,0,0,0));

        win_d.getContentPane().add(label);
        win_d.getContentPane().add(label2);
        win_d.getContentPane().add(label3);

        win_d.setBounds((Game.game_width-300)/2,(Game.game_height-200)/2,300,400);

        win_d.setVisible(true);

    }

}
