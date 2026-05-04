import javax.swing.*;
import java.awt.*;

public class LockerBloodAnomaly extends Anomaly {

    Image img = new ImageIcon("assets/locker_blood.png").getImage();

    @Override
    public void apply(GamePanel panel) {
        panel.overlays.add(this);
    }

    @Override
    public void render(Graphics2D g, GamePanel panel) {

        // blood appears on lockers
        g.drawImage(img, 0, panel.baseY, 500, 100, null); // hi.
    }
}