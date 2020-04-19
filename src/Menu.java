import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu extends JFrame implements ActionListener {

//    窗口大小
    public static  final int Window_width=400;
    public static  final int Window_height=600;

//        JFrame名字
    JFrame jFrame1 = new JFrame();

//    背景音乐
    Music music=new Music();
    Music music1=new Music();


    public Menu(){
//        设置主界面大小
        jFrame1.setSize(Window_width,Window_height);

//        不允许拉伸
        jFrame1.setResizable(false);

//        设置界面名称
        jFrame1.setTitle("BreakOut-主界面");

//        关闭按钮
        jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        添加布局
        jFrame1.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));

//      主菜单上的按钮
        JButton start=new JButton("开始");
        JButton setting=new JButton("设置");
        JButton quite=new JButton("退出");

//        设置按钮大小
        Dimension buttonsize = new Dimension(200,30);
        start.setPreferredSize(buttonsize);
        setting.setPreferredSize(buttonsize);
        quite.setPreferredSize(buttonsize);

//        设置按钮样式
        start.setBackground(new Color(255 ,240 ,245));
        setting.setBackground(new Color(255 ,240 ,245));
        quite.setBackground(new Color(255 ,240 ,245));

//      标题图片，点击图片可以显示作者信息
        JLabel title_img = new JLabel();
        title_img.setIcon(new ImageIcon("imgs/3.png"));
        title_img.setSize(Window_width,300);
        title_img.setLocation(0,0);

//        点击标题图片可以查看作者信息
        Author_information author_information=new Author_information(jFrame1,"作者信息：");
        title_img.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                author_information.setModal(true);
                author_information.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                title_img.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                title_img.setCursor(Cursor.getDefaultCursor());
            }
        });

//        把按钮和标题图添加到jFrame1中
        jFrame1.add(title_img);

        jFrame1.add(start);
        jFrame1.add(setting);
        jFrame1.add(quite);

//        给按钮添加点击监听
        start.addActionListener(this);
        setting.addActionListener(this);
        quite.addActionListener(this);

//        背景
        jFrame1.getContentPane().setBackground(new Color(100 ,149 ,237));

//        视图可视化
        jFrame1.setVisible(true);

        music1.playMusic("mus/rony.wav");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object object=actionEvent.getSource();
        switch (actionEvent.getActionCommand()){
            case "开始":
                music1.stopmusic();
                jFrame1.dispose();
                music.sigleplayMusic("mus/button.wav");
                Home home=new Home();
                home.view2();
                break;
            case "设置":
                music.sigleplayMusic("mus/button.wav");
                Setting setting=new Setting(jFrame1);
                break;
            case "退出":
                music.sigleplayMusic("mus/bye.wav");
                System.exit(-1);
                break;
        }

    }
}
