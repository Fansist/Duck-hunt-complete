import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Plays a short .wav sound effect (or a looping song).
 *
 * <pre>
 * Music gunShot = new Music("Gun.wav", false);   // false = play once
 * gunShot.play();
 * </pre>
 *
 * The sound file is looked up the same way Sprite looks up images: first as a
 * classpath resource in /sounds or the project root, then as a plain file in
 * the working directory (or a "sounds" folder inside it).
 *
 * If a file is missing or the computer has no usable audio device, the game
 * prints one message and keeps running silently instead of crashing.
 */
public class Music {
    private final boolean loop;
    private Clip clip;

    public Music(String soundFileName, boolean loop) {
        this.loop = loop;

        try {
            AudioInputStream stream = openStream(soundFileName);
            if (stream == null) {
                System.out.println("Could not find sound: " + soundFileName);
                return;
            }

            Clip newClip = AudioSystem.getClip();
            newClip.open(stream);
            stream.close();
            clip = newClip;
        } catch (Exception exception) {
            System.out.println("Could not load sound: " + soundFileName
                    + " (" + exception.getClass().getSimpleName() + ")");
            clip = null;
        }
    }

    /** Starts the sound from the beginning. Restarts it if it is already playing. */
    public void play() {
        if (clip == null) {
            return;
        }

        clip.stop();
        clip.setFramePosition(0);

        if (loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            clip.start();
        }
    }

    /** Stops the sound. */
    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    private AudioInputStream openStream(String soundFileName) throws Exception {
        URL soundURL = Music.class.getResource("/sounds/" + soundFileName);
        if (soundURL == null) {
            soundURL = Music.class.getResource("/" + soundFileName);
        }

        if (soundURL != null) {
            return AudioSystem.getAudioInputStream(soundURL);
        }

        File localFile = new File(soundFileName);
        if (localFile.exists()) {
            return AudioSystem.getAudioInputStream(localFile);
        }

        File nestedFile = new File("sounds", soundFileName);
        if (nestedFile.exists()) {
            return AudioSystem.getAudioInputStream(nestedFile);
        }

        return null;
    }
}
