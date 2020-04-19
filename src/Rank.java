import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.ArrayList;

public class Rank{

    JDialog dialog=new JDialog();

    //mysql驱动包名
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    //连接数据库aa
    private static final String URL = "jdbc:mysql://localhost:3306/aa";
    //用户名
    private static final String USER_NAME = "root";
    //密码
    private static final String PASSWORD = "123456";

    ArrayList<String> list=new ArrayList<>();

    public Rank(){
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER));

//        空白
        JLabel kong=new JLabel("  ");

//        ×
        ImageIcon icon=new ImageIcon("imgs/叉.png");
        icon.setImage(icon.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
        JLabel cha=new JLabel();
        cha.setIcon(icon);
        cha.setPreferredSize(new Dimension(300,50));
        cha.setHorizontalAlignment(JLabel.RIGHT);
        dialog.getContentPane().add(cha);

        cha.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Rank.this.dialog.dispose();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                dialog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                dialog.setCursor(Cursor.getDefaultCursor());
            }
        });

        list.add("名次");
        list.add("用户名");
        list.add("成绩");

        select();

//        字

        JLabel jLabels[]=new JLabel[list.size()];

        if (list!=null){
            for (int i=0;i<list.size();i++){
                jLabels[i]=new JLabel(list.get(i));
                if(i<3){
                    jLabels[i].setFont(new Font("微软雅黑",Font.BOLD,20));
                }
                else
                    jLabels[i].setFont(new Font("微软雅黑",Font.PLAIN,17));
                jLabels[i].setPreferredSize(new Dimension(80,50));
//                jLabels[i].setBorder(BorderFactory.createLineBorder(Color.RED,1));
                jLabels[i].setHorizontalAlignment(0);
                if(i<12&&i>2){
                    jLabels[i].setForeground(Color.RED);
                }
                else
                    jLabels[i].setForeground(Color.WHITE);
                dialog.add(jLabels[i]);
            }
        }

        dialog.setUndecorated(true);
        dialog.setBackground(new Color(0,0,0,70));


        dialog.setBounds((Game.game_width-300)/2,(Game.game_height-430)/2,300,430);
        dialog.setVisible(true);

    }

    public void select(){
        Connection connection = null;
        try {
            //加载mysql的驱动类
            Class.forName(DRIVER_NAME);
            //获取数据库连接
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

            //sql
            String sql = "select name,score from paihang order by score*1 desc limit 5;";

            PreparedStatement prst = connection.prepareStatement(sql);
            //结果集
            ResultSet rs=prst.executeQuery();
            System.out.println("用户名\t成绩");
            String s[]={"1","2","3","4","5"};
            int i=0;
            while(rs.next()){
                list.add(s[i]);
                i++;
                list.add(rs.getString("name"));
                list.add(rs.getString("score"));
                System.out.println(rs.getString("name")+"\t"+rs.getString("score"));
            }
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
