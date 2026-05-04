import javax.sound.sampled.*;
import java.io.File;

// ================= SOUND MANAGER =================
public class SoundPlayer {

    Clip footstepClip;
    Clip doorClip;

    // ================= LOAD FOOTSTEPS =================
    public void loadFootsteps() {
        footstepClip = loadClip("assets/sounds/footsteps.wav");
    }

    // ================= LOAD DOOR SOUND =================
    public void loadDoorSound() {
        doorClip = loadClip("assets/sounds/door.wav");
    }

    // ================= LOADER =================
    private Clip loadClip(String path) {
        try {

            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File(path));

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            return clip;

        } catch (Exception e) {
            System.out.println("Sound load error: " + e.getMessage());
            return null;
        }
    }

    // ================= FOOTSTEPS =================
    public void startFootsteps() {
        if (footstepClip == null) return;
        footstepClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopFootsteps() {
        if (footstepClip == null) return;
        footstepClip.stop();
        footstepClip.setFramePosition(0);
    }

    // ================= DOOR SOUND =================
    public void playDoor() {
        if (doorClip == null) return;

        doorClip.setFramePosition(0);
        doorClip.start();
    }
}