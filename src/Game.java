import javafx.scene.text.TextAlignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game extends JFrame implements ActionListener, KeyListener {

//      窗体大小
    public static  final int game_width=400;
    public static final int game_height=600;

//       JFrame名字
    JFrame jFrame2=new JFrame();

//    生命
    JLabel heart1=new JLabel();
    JLabel heart2=new JLabel();
    JLabel heart3=new JLabel();

//    开始提示
    JLabel remind=new JLabel("按下 “空格” 开始游戏");

//    breakoutComponents大小
    public static  final int game_box_w=384;
    public static  final int game_box_h=510;

//    是否出窗口，1==isout,0==notout
    public static int out;

//    静态数据，记录生命值,初始值为3，每次失去生命就-1
    public static int count_heart=3;

//      添加砖块数量，每层数量和层数
    private static int brick_per_row=10;
    private static int brick_row=10;

//      每块砖的间隙
    private static final int brick_sep=4;

//        实例化游戏中的 板子、球、砖块、组建管理器
    Paddle paddle=new Paddle();
    Ball ball=new Ball();
    ArrayList<Brick> bricks=initBricks();
    BreakoutComponents breakoutComponents=new BreakoutComponents(paddle,ball,bricks);

//    分数
    JLabel result;
    public static int num=0;

//    菜单
    JMenuItem stop;
    JMenuItem back_game;
    JMenuItem again;
    JMenuItem back_home;

    JMenuItem all_pai;

    JMenuItem how_play;

//        添加定时器，使新建一个线程不会重绘所有组件
    Timer timer=new Timer();

//    音效
    Music music=new Music();
    Music music1=new Music();

//    增加生命标识
    public static int flag_1=1;
    public static int flag_2=1;
    public static int flag_3=1;

//    ——————————————————————————————————分——————割——————线—————————————————————————————————————————————————————————————————

    public Game(){

//        设置主界面大小
        jFrame2.setSize(game_width,game_height);

//        不允许拉伸
        jFrame2.setResizable(false);

//        设置界面名称
        jFrame2.setTitle("BreakOut-游戏中");

//        关闭按钮
        jFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        添加布局
        jFrame2.setLayout(null);

//        添加分数显示
        JLabel score=new JLabel("分数:");
        result=new JLabel("0");

//        添加菜单栏
//        一级菜单
        JMenuBar menu=new JMenuBar();
//        menu.setPreferredSize(new Dimension(game_box_w, 20));
        JMenu caoz=new JMenu("操作");
        JMenu paim=new JMenu("排行榜");
        JMenu help=new JMenu("帮助");
        menu.add(caoz);
        menu.add(paim);
        menu.add(help);

//        一级菜单的子菜单
        stop=new JMenuItem("暂停");
        back_game=new JMenuItem("继续");
        again=new JMenuItem("重来");
        back_home=new JMenuItem("返回主菜单");

        caoz.add(stop);
        caoz.addSeparator();  //添加分割线
        caoz.add(back_game);
        caoz.addSeparator();
        caoz.add(again);
        caoz.addSeparator();
        caoz.add(back_home);

        all_pai=new JMenuItem("总排行榜");
        paim.add(all_pai);

        how_play=new JMenuItem("玩法");
        help.add(how_play);

//        在界面中添加控件
        menu.setBounds(0,0,game_width,20);
        score.setBounds(150,25,30,20);
        result.setBounds(200,25,50,20);

        jFrame2.getContentPane().add(menu);
        jFrame2.getContentPane().add(score);
        jFrame2.getContentPane().add(result);

//        添加生命值*3

//        生命图标初始化
        ImageIcon icon=new ImageIcon("imgs/live.png");
        icon.setImage(icon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));

//        在面板中添加3个生命图标JLabel
        heart1.setIcon(icon);
        heart2.setIcon(icon);
        heart3.setIcon(icon);

        heart1.setBounds(300,25,25,20);
        heart2.setBounds(325,25,25,20);
        heart3.setBounds(350,25,25,20);

        jFrame2.getContentPane().add(heart1);
        jFrame2.getContentPane().add(heart2);
        jFrame2.getContentPane().add(heart3);

//        把实例化的组件添加到game_box
//        breakoutComponents.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        breakoutComponents.setBounds(0,45,game_box_w,game_box_h);

        jFrame2.getContentPane().add(breakoutComponents);

//        把提示文字添加到breakoutcompnent中
        remind.setBounds((Game.game_box_w-100)/2,Game.game_box_h-Paddle.paddle_button+Paddle.paddle_hight*2,200,20);
        breakoutComponents.add(remind);

//    对提示添加监听事件，按下空格后字消失
        remind.addKeyListener(this);

//        显示视图
        jFrame2.setVisible(true);

//        背景音乐
        music1.playMusic("mus/BGM.wav");

    }

//    实时更新组件参数
    public void setBreakoutComponents(){

//        每次启动随机砖块
        set_brick();

        paddle.setStartPosition();

//        设置键盘的监听事件
        breakoutComponents.addKeyListener(this);

//      将组件设置为焦点
        breakoutComponents.setFocusable(true);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
//                实时更新，重绘
                    breakoutComponents.repaint();

//                小球移动
                    ball.moveAndBounce();
//                判断与paddle碰撞
                    if(ball.collide(paddle.getX(),paddle.getY(),Paddle.paddle_width,Paddle.paddle_hight)){
                        ball.BounceY();
                    }

//                    实时判断生命值，当因为分数增长生命，显示心
                    add_heart();//只进行一次
                    if(count_heart==3){
                        heart1.setVisible(true);
                        heart2.setVisible(true);
                        heart3.setVisible(true);
                    }
                    if (count_heart==2){
                        heart3.setVisible(false);
                        heart2.setVisible(true);
                        heart1.setVisible(true);
                    }

//                    返回游戏
                    if(ball.getX()!=(Game.game_box_w)/2||ball.getY()!=Game.game_box_h-Paddle.paddle_button-Paddle.paddle_hight*2){
                        back_game.setForeground(Color.BLACK);
                    }
                    else {
                        back_game.setForeground(Color.GRAY);
                    }

//                判断小球是否低于板子高度，低于则失去一条生命
                    if(ball.isOUt()==true){
                        count_heart--;
                        if (count_heart==2){
                            heart3.setVisible(false);
                            heart2.setVisible(true);
                            heart1.setVisible(true);
                        }
                        else if(count_heart==1){
                            heart2.setVisible(false);
                            heart3.setVisible(false);

                        }
                        else if(count_heart==0){
                            heart1.setVisible(false);
                            heart2.setVisible(false);
                            heart1.setVisible(false);
                            music1.stopmusic();
                            Lose_dialog lose_dialog=new Lose_dialog(jFrame2,5);
                            music.sigleplayMusic("mus/lose.wav");
                            this.cancel();
                            User_info.score=result.getText();
                            try {
                                Thread.sleep(5000);
                                User_info user_info=new User_info();
                                System.out.println("Close_flag:"+User_info.close_flag);
                                if (User_info.close_flag==0){
                                    jFrame2.dispose();
                                    result.setText("0");
                                    num=0;
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
//                        板子复位
                        paddle.setStartPosition();
                        Paddle.speed=0;

//                        提示出现
                        remind.setVisible(true);

                        setOUt(1);
                        System.out.println(getout()+"\n生命:"+count_heart);
                    }

//                判断是否和brick碰撞
                    for(Brick brick:bricks){
                        if(brick.isAlive()&&ball.collide(brick.getX(),brick.getY(),brick.getBrick_width(),Brick.brick_height)){
//                            撞击音效
                            music.sigleplayMusic("mus/peng.wav");

                            if (Ball.vx==0){
                                Ball.vx=5;
                            }

                            ball.BounceY();

//                            测试撞击砖块的颜色
//                            System.out.println(brick.getColor()+"\n"+brick.getColor_name());

//                            不同颜色撞击的次数不同
                            switch (brick.getColor_name()){

//                                cyan  1次，1分/个
                                case "java.awt.Color[r=0,g=255,b=255]":{
                                    brick.setAlive(false);
                                    num+=1;
                                    result.setText(" "+num);
                                    break;
                                }

//                                green 2次，2分/个
                                case "java.awt.Color[r=0,g=255,b=0]":{
                                    brick.setColor(Color.RED);
                                    num+=2;
                                    result.setText(" "+num);
                                    break;
                                }

//                                blue 1次，1分/个
                                case "java.awt.Color[r=0,g=0,b=255]":{
                                    brick.setAlive(false);
                                    num+=1;
                                    result.setText(" "+num);
                                    break;
                                }

//                                red 1次，1分/个
                                case "java.awt.Color[r=255,g=0,b=0]":{
                                    brick.setAlive(false);
                                    num+=1;
                                    result.setText(" "+num);
                                    break;
                                }

//                                orange 3次，6分/个
                                case "java.awt.Color[r=255,g=200,b=0]":{
                                    brick.setColor(Color.GREEN);
                                    num+=6;
                                    result.setText(" "+num);


                                    break;
                                }

//                                 yellow  2次，3分/个
                                case "java.awt.Color[r=255,g=255,b=0]":{
                                    brick.setColor(Color.CYAN);
                                    num+=3;
                                    result.setText(" "+num);
                                    break;
                                }
                            }
                        }
                    }

//                    判断界面已经没有砖块
//                    System.out.println(All_die());

                    if (All_die()){
//                        如果胜利，则显示胜利图片
                        music1.stopmusic();
                        Win_dialog win_dialog=new Win_dialog(jFrame2,5);
                        music.sigleplayMusic("mus/win.wav");
                        this.cancel();
                        User_info.score=result.getText();
                        try {
                            Thread.sleep(5000);
                            User_info user_info=new User_info();
                            System.out.println("Close_flag:"+User_info.close_flag);
                            if (User_info.close_flag==0){
                                jFrame2.dispose();
                                result.setText("0");
                                num=0;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

//                    对菜单添加点击事件，实时监听

//                    暂停
                    stop.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            Ball.vx=0;
                            Ball.vy=0;
                            Paddle.speed=0;
                        }
                    });

//                            测试现在小球速度状态
//                    在暂停前，设置保存最后不为0的速度
                    ball.setRe_vx();
                    ball.setRe_vy();
//                    System.out.println("现在获取到的小球的速度："+ball.getVx()+","+ball.getVy());
//                    System.out.println("暂停前的小球速度："+Ball.re_vx+","+Ball.re_vy);

//                    返回游戏
                    back_game.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            if (Ball.vy==0&&Ball.vx==0){
                                if (ball.getX()!=(Game.game_box_w)/2||ball.getY()!=Game.game_box_h-Paddle.paddle_button-Paddle.paddle_hight*2){
                                    Ball.vx=Ball.re_vx;
                                    Ball.vy=Ball.re_vy;
                                    Paddle.speed=20;
                                }
                            }
                        }
                    });

                }
            },0,20);
        }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()) {

//		方向左键或A
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                paddle.moveLeft();
                break;

//		方向右键或D
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                paddle.moveRight();
                break;
            case KeyEvent.VK_SPACE:
                Ball.vy=5;
                remind.setVisible(false);
                Paddle.speed=20;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

//    出窗口的返回值
    public static void setOUt(int a){
    out=a;
    }
    public int getout(){
        return out;
    }

//    实例化所有砖块
    private ArrayList<Brick> initBricks(){

//        实例化一个list
        ArrayList<Brick> bricks=new ArrayList<>();

//        通过for循环将所有砖块实例化后添加到list中
        for(int i=0;i<brick_row;i++){
            for(int j=0;j<brick_per_row;j++){
                Brick brick=new Brick();

//                设置空白砖块
                int a=(int)(Math.random()*9+1);
                int b=(int)(Math.random()*9+1);
                if (j==b||i==b){
                    brick.setAlive(false);
                }

//                用switch来初始化颜色
                switch (a){
                    case 1:
                        brick.setColor(Color.GREEN);
                        break;
                    case 2:
                        brick.setColor(Color.RED);
                        break;
                    case 3:
                        brick.setColor(Color.BLUE);
                        break;
                    case 4:
                        brick.setColor(Color.ORANGE);
                        break;
                    case 5:
                        brick.setColor(Color.BLUE);
                        break;
                    case 6:
                        brick.setColor(Color.YELLOW);
                        break;
                    case 7:
                        brick.setColor(Color.RED);
                        break;
                    case 8:
                        brick.setColor(Color.GREEN);
                        break;
                    case 9:
                        brick.setColor(Color.BLUE);
                        break;
                    case 10:
                        brick.setColor(Color.CYAN);
                        break;
                }

//                添加进list中
                bricks.add(brick);
            }
        }
        return bricks;
    }

//    砖块
    public void updateBrickWidth(){

//        用i,j记录砖块位置，（i,j）
        int i=0,j=0;

//        实时更新窗口大小与砖块的关系，的到砖块的宽度
        int brick_width=(game_box_w-(brick_per_row-1)*brick_sep)/brick_per_row;
        for(Brick brick:bricks){
            brick.setBrick_width(brick_width);

//            x,y计算砖块坐标
            int x=j*brick_width+4*(j+1);
            brick.setX(x);
            int y=i* Brick.brick_height+4*i;
            brick.setY(y);
            j++;
            if(j==10){
                j=0;
                i++;
            }
        }
    }

//    判断所有砖块是否全部死掉
    public boolean All_die(){

//        循环所有砖块
        int flag1=0;
        int i=0,j=0;

//        如果有砖块存活flag1=1,当flag1=0时砖块没有，跳出胜利
        for(Brick brick:bricks){
            if(brick.isAlive()==true){
                flag1=1;
                break;
            }
        }
        if (flag1==1){
            return false;
        }
        else return true;
    }

//    设置随机层数和每层数
    public void set_brick(){
        brick_per_row=(int)(Math.random()*10+2);
        brick_row=(int)(Math.random()*10+2);
    }

//    获取分数，当分数超过一定分值，增加生命
    public void add_heart(){
        int s=20;
        if(count_heart<3&&num>s&&flag_1==1){
            count_heart+=1;
            flag_1=0;
        }
        else if(count_heart<3&&num>s*2&&flag_2==0){
            count_heart+=1;
            flag_2=1;
        }
        else if(count_heart<3&&num>s*8&&flag_3==1){
            count_heart+=1;
            flag_3=0;
        }
    }

}
