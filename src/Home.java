import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

public class Home extends JFrame{

    public void view1()
    {
        Menu menu=new Menu();
    }

    public void view2(){
        Game game=new Game();
        game.setBreakoutComponents();
        game.updateBrickWidth();

//        重新开始游戏放在Home，以免因为timer的原因不停新建窗口关闭窗口
        game.again.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                game.timer.cancel();
                game.music1.stopmusic();
                view2();
                game.jFrame2.dispose();
                Ball.vx=0;
                Ball.vy=0;
                Paddle.speed=0;
                game.result.setText("0");
                game.num=0;
                game.back_game.setForeground(Color.BLACK);
            }
        });

//        回到主界面
        game.back_home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                game.timer.cancel();
                game.music1.stopmusic();
                game.jFrame2.dispose();
                view1();
                game.result.setText("0");
                game.num=0;
                Ball.vy=0;
                Ball.vx=0;
            }
        });

//                    游戏说明
        game.how_play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                How_play hp=new How_play(game.jFrame2);
            }
        });

//                    排行榜
        game.all_pai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Rank rank=new Rank();
            }
        });
    }
}
