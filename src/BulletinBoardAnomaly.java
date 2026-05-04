import javax.swing.*;
import java.awt.*;

// OVERLAY ANOMALY: bulletin board changes or appears altered
public class BulletinBoardAnomaly extends Anomaly {

    Image img = new ImageIcon("assets/bulletin_board.png").getImage();

    @Override
    public void apply(GamePanel panel) {
        // registers anomaly as an overlay
        panel.overlays.add(this);
    }

    @Override
    public void render(Graphics2D g, GamePanel panel) {

        // draws friendly face on bulletin board
        g.drawImage(img, 0, panel.baseY, 500, 100, null);
    }
}