package MusicPlayer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.net.ServerSocket;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {

    public static void main(String[] args) throws   LineUnavailableException,
                                                    UnsupportedAudioFileException,
                                                    IOException,
                                                    InterruptedException {
        (new Thread() {
            public void run(){
                try {
                    ServerSocket ss = new ServerSocket(5050);
                    boolean connection = true;
                    while (connection) {

                        Socket s = ss.accept();

                        System.out.println("connection established");
                        try {
                            FileWriter fileWriter = new FileWriter("bufferedWriter.sh");
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            bufferedWriter.write("osascript -e " + '"' + "set volume 7" + '"');

                            bufferedWriter.close();
                        } catch (Exception e) {
                            System.err.println("Error: " + e.getMessage());
                        }

                        long currentTimeMillis = System.currentTimeMillis();
                        while (System.currentTimeMillis() < (currentTimeMillis + 20000)) {
                            Runtime.getRuntime().exec("sh out.sh");
                            Thread.sleep(100);
                        }

                    }
                } catch (InterruptedException e) {
                    Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, e);
                } catch (IOException e) {
                    Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, e);
                }
        }}).start();

        (new Thread(){public void run(){try {
                    ServerSocket ss=new ServerSocket(5050);
                    while(true){
                        ss.accept();
                        playClip(new File(System.getProperty("user.home")+"/Downloads/Music.wav"));
                    }
                } catch (UnsupportedAudioFileException e) {
                    Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, e);
                } catch (LineUnavailableException e) {
                    Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, e);
                } catch (InterruptedException e) {
                    Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, e);
                } catch (IOException e) {
                    Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, e);
                }
        }}).start();

        while(true)
        {
            playClip(new File(System.getProperty("user.home")+"/Downloads/Music.wav"));
        }       
    }
    public static void playClip(File clipFile) throws   IOException,
                                                        UnsupportedAudioFileException,
                                                        LineUnavailableException,
                                                        InterruptedException {
    class AudioListener implements LineListener {
        private boolean done = false;
        @Override public synchronized void update(LineEvent event) {
              Type eventType = event.getType();
              if (eventType == Type.STOP || eventType == Type.CLOSE) {
                    done = true;
                    notifyAll();
                }
        }
        public synchronized void waitUntilDone() throws InterruptedException {
            while (!done) { wait(); }
        }
    }

        AudioListener audioListener = new AudioListener();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile);
        try {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(audioListener);
            clip.open(audioInputStream);
        try {
            clip.start();
            audioListener.waitUntilDone();
        } finally {
            clip.close();
        }

        } finally {
            audioInputStream.close();
        }
    }
}
