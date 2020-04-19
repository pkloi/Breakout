import java.awt.*;

public class Brick {

//    砖块宽度
    private int brick_width;

//    砖块的高
    public static final int brick_height=8;

//    砖块是否存活
    private boolean isAlive=true;

//    砖块坐标
    private int x,y;

//    颜色
    private Color color;

//    添加方法 alt+insert

    public void setBrick_width(int brick_width) {
        this.brick_width = brick_width;
    }

    public int getBrick_width(){
        return brick_width;
    }

//    是否存活
    public boolean isAlive(){
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX(){
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY(){
        return y;
    }

//    设置颜色
    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

//    把获取的颜色转换成字符
    public String getColor_name(){
        String s=String.valueOf(getColor());
        return s;
    }

//    绘图
    public void draw(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        if(isAlive){
            g2.setColor(color);
            g2.fillRect(x,y,brick_width,brick_height);
        }
    }


}