public class MainGame {
    public static void main(String[] args) {
//        事件分发现线程
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Home home=new Home();
                home.view1();
            }
        });

    }
}