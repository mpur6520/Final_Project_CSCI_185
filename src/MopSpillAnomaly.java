import javax.swing.*;
import java.awt.*;

// OVERLAY ANOMALY: mop water / cleaning spill appears on floor
public class MopSpillAnomaly extends Anomaly {

    Image img = new ImageIcon("assets/mop_spill.png").getImage();

    @Override
    public void apply(GamePanel panel) {
        // adds this overlay to active anomaly list
        panel.overlays.add(this);
    }

    @Override
    public void render(Graphics2D g, GamePanel panel) {

        // draws spill on floor area of hallway
        g.drawImage(img, 0, panel.baseY+5, 500, 100, null);
    }
}