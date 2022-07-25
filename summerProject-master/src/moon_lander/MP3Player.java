package moon_lander;

        import javax.sound.sampled.*;
        import java.io.File;
        import java.io.IOException;

public class MP3Player {
    private Clip clip;
    private File audioFile;
    private AudioInputStream audioInputStream;
    private boolean isLoop;

    public MP3Player(String pathName, boolean isLoop) {
        this.isLoop = isLoop;
        try {
            clip = AudioSystem.getClip();
            audioFile = new File(pathName);
            audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        clip.setFramePosition(0);
        clip.start();
        if (isLoop)
            clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
