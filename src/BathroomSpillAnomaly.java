import javax.swing.*;
import java.awt.*;

public class BathroomSpillAnomaly extends Anomaly {

    Image img = new ImageIcon("assets/bathroom_spill.png").getImage();

    @Override
    public void apply(GamePanel panel) {
        panel.overlays.add(this);
    }

    @Override
    public void render(Graphics2D g, GamePanel panel) {

        // draws spill on hallway floor
        g.drawImage(img, 0, panel.baseY, 500, 100, null);
    }
}