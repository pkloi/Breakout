import javax.swing.*;
import java.awt.*;

//弹球的控制板
public class Paddle extends JComponent {

//    设置板子大小
    public static int paddle_width=60;
    public static int paddle_hight=10;

//    设置距离底部的距离
    public static final int paddle_button=60;

//    设置板子的坐标
    private int x=0;
    private int y=0;

//    板子移动速度
    public static int speed=0;

//    画图函数，在窗口中绘制paddle
    public void draw(Graphics g){
        Graphics2D g2=(Graphics2D)g;

//        设置板子颜色
//        g2.setColor(Color.blue);
//        设置填充与否;fillRect(起始坐标x，起始坐标y，宽度，高度）
//        g2.fillRect(0,0,paddle_width,paddle_hight);
//        g2.fillRect(x,y,paddle_width,paddle_hight);

//        设置板子的图标
        Image img = new ImageIcon("imgs/paddle.png").getImage();
        g.drawImage(img,this.x,this.y,paddle_width,paddle_hight,null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        draw(g);
    }

    //    设置paddle的初始位置
    public void setStartPosition(){
        x=(Game.game_box_w-paddle_width)/2;
        y=Game.game_box_h-paddle_button;
    }

    //    添加键盘移动函数
    public void moveLeft() {
        if(x>=speed)
            x-=speed;
        else
            x=0;
    }

    public void moveRight() {
        if(x+speed<=Game.game_box_w-paddle_width)
            x+=speed;
        else
            x=Game.game_box_w-paddle_width;;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}