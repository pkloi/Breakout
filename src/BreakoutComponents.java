import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//组件管理
public class BreakoutComponents extends JComponent {
    Paddle paddle;
    Ball ball;
    ArrayList<Brick> bricks;

    public BreakoutComponents(Paddle paddle,Ball ball,ArrayList<Brick> bricks){
        this.paddle=paddle;
        this.ball=ball;
        this.bricks=bricks;
    }

    @Override
    protected void paintComponent(Graphics g) {
        paddle.draw(g);
        ball.draw(g);

//        用for循环绘制砖块
        for(Brick brick:bricks){
            brick.draw(g);
        }

    }

}