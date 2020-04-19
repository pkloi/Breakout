import javax.sound.sampled.*;
import java.applet.*;
import java.io.*;
import java.net.*;

public class Music {
    Clip clip;
    public static int sound=0;

    public void playMusic(String filename) {// 背景音乐播放
        try
        {
            File musicPath = new File(filename);

            if(musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-sound);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            if(!clip.isRunning()){
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void sigleplayMusic(String filename) {// 背景音乐播放
        try
        {
            File musicPath = new File(filename);

            if(musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-sound);
                clip.start();
            }
            if(!clip.isRunning()){
                clip.start();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void stopmusic(){
        try
        {
            clip.stop();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
