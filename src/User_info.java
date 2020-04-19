import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class User_info {

    JDialog dialog=new JDialog();
    JLabel n=new JLabel("用户名：");
    JTextField name=new JTextField(15);
    JLabel s=new JLabel("分数：",JLabel.CENTER);
    JLabel img=new JLabel();

    public static String score;
    public static String usname;
    public static int close_flag=0;

    //mysql驱动包名
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    //连接数据库aa
    private static final String URL = "jdbc:mysql://localhost:3306/aa";
    //用户名
    private static final String USER_NAME = "root";
    //密码
    private static final String PASSWORD = "123456";

    public User_info(){
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        dialog.setResizable(false);

        ImageIcon icon=new ImageIcon("imgs/确定.png");
        icon.setImage(icon.getImage().getScaledInstance(100,30, Image.SCALE_DEFAULT));

        n.setFont(new Font("微软雅黑",Font.BOLD,25));
        n.setForeground(Color.WHITE);

        name.setSize(150,50);
        name.setForeground(Color.BLACK);
        name.setHorizontalAlignment(JTextField.CENTER);
        name.setFont(new Font("微软雅黑",Font.PLAIN,15));
        name.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));

        s.setText("分数："+score);
        s.setFont(new Font("微软雅黑",Font.BOLD,25));
        s.setForeground(Color.WHITE);
        s.setPreferredSize(new Dimension(300,50));

        img.setIcon(icon);

        img.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                usname=name.getText();
                add();
                User_info.this.dialog.dispose();
                Home home=new Home();
                home.view1();
                Ball.vx=0;
                Ball.vy=0;
                Game.count_heart=3;
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                img.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                img.setCursor(Cursor.getDefaultCursor());
            }
        });

        dialog.setUndecorated(true);
        dialog.setBackground(new Color(0,0,0,70));

        dialog.getContentPane().add(n);
        dialog.getContentPane().add(name);
        dialog.getContentPane().add(s);
        dialog.getContentPane().add(img);

        dialog.setBounds((Game.game_width-300)/2,(Game.game_height-200)/2,300,250);
        dialog.setVisible(true);
    }

    public void add() {
        Connection connection = null;
        try {
            //加载mysql的驱动类
            Class.forName(DRIVER_NAME);
            //获取数据库连接
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

            //sql
            String sql = "insert into paihang(name,score) values(?,?);";

            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setString(1, usname);
            prst.setString(2, score);
            //结果集

            System.out.println(prst.executeUpdate());
            prst.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
