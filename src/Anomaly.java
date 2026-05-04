import java.awt.Graphics2D;

// BASE CLASS FOR ALL OVERLAY ANOMALIES (W/O BASE CHANGE)

public abstract class Anomaly {

    // adds anomaly into game state
    public abstract void apply(GamePanel panel);

    // renders anomaly visually
    public abstract void render(Graphics2D g, GamePanel panel);
}