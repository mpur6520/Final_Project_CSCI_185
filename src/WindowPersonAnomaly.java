import javax.swing.*;
import java.awt.*;

public class WindowPersonAnomaly extends Anomaly {

    Image img = new ImageIcon("assets/window_person.png").getImage();

    @Override
    public void apply(GamePanel panel) {
        panel.overlays.add(this);
    }

    @Override
    public void render(Graphics2D g, GamePanel panel) {

        // friendly friend appears in background window
        g.drawImage(img, 0, panel.baseY, 500, 100, null);
    }
}