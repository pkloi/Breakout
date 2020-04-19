import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import java.util.Random;

public class Ball {

//    创建小球半径
    private static final int ball_r=10;

//    设置小球初始位置
    public int x=(Game.game_box_w)/2;
    public int y=Game.game_box_h-Paddle.paddle_button-Paddle.paddle_hight*2;

//    设置小球在x,y上的初始速度
    public static int vx=0;
    public static int vy=0;

//    恢复后的速度
    public static int re_vx=0;
    public static int re_vy=0;

//    添加绘制函数，画出小球
    public void draw(Graphics g){
        Graphics2D g2=(Graphics2D)g;

//        绘制小球,绘制完成后再组件管理类添加组件
//        g2.setColor(Color.magenta);
//        g2.fillOval(x,y,ball_r*2,ball_r*2);

//        用图标代替小球
        Image img = new ImageIcon("imgs/ball.png").getImage();
        g.drawImage(img,this.x,this.y,2*ball_r,2*ball_r,null);
    }

//    实现小球移动&反弹
    public void moveAndBounce(){

//        小球移动
        x+=vx;
        y+=vy;

//        小球的反弹；小球是基于矩形边框

//        左墙反弹
        if(x<0) {

//            当小球向左墙移动快要出窗口时，让小球补在移动，x=0，载进行反弹
            x = 0;
            BounceX();
        }

//        右墙反弹
        else if(x+ball_r*2>Game.game_box_w){
            x=Game.game_box_w-ball_r*2;
            BounceX();
        }
//        上墙反弹
        else if(y<=0){
            y=0;
            BounceY();
            vx=(int)(Math.random()*10-5);
        }

//        掉下去之后就归位
        else if(y+ball_r*2>Game.game_height){
            x=(Game.game_box_w)/2;;
            y=Game.game_box_h-Paddle.paddle_button-Paddle.paddle_hight*2;
            vx=0;
            vy=0;
        }
    }

//    对左右墙的反弹,vy不变,vx反响
    public void BounceX(){
        vx=-vx;
    }

//    对上墙反弹
    public void BounceY(){
        vy=-vy;
    }

//    获取小球目前的速度状态
    public int getVx(){
        return vx;
    }

    public int getVy(){
        return vy;
    }

//    把获取的值返回到暂停后开始的速度，保证速度前后一致
    public void setRe_vx(){
        int a;
        if ((getVx()+getVy())!=0) {
            a=getVx();
            re_vx=a;
        }
    }

    public void setRe_vy(){
        int a;
        if ((getVx()+getVy())!=0) {
            a=getVy();
            re_vy=a;
        }
    }

//    小球碰撞检测
    public boolean collide(int object_x,int object_y,int object_width,int object_height){
        if(this.x+2*ball_r>object_x && this.x<object_x+object_width && this.y+2*ball_r>object_y&&this.y<object_y+object_height){
            return true;
        }
        return false;
    }

//    判断小球是否到窗口外
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOUt(){
        if (getY()+ball_r*2==Game.game_box_h)
            return true;
        else
            return false;
    }

}
