import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

// MAIN GAME PANEL
public class GamePanel extends JPanel implements Runnable, KeyListener {

    // ================= SCREEN =================
    final int WIDTH = 500;
    final int HEIGHT = 500;

    Thread gameThread;
    JFrame window; // used for closing game on win

    // ================= PLAYER =================
    int playerX = 200;
    int playerY;
    int playerSpeed = 4;

    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean moving = false;
    boolean facingRight = true;

    // ================= PROGRESSION =================
    int currentPeriod = 0;

    boolean nearLeftEnd = false;
    boolean nearRightEnd = false;

    // ================= WORLD STATE =================
    BaseState currentBase = BaseState.NORMAL;
    ArrayList<Anomaly> overlays = new ArrayList<>();

    // ================= IMAGES =================
    Image playerIdle;
    Image playerWalk;
    Image arrowLeft;
    Image arrowRight;
    Image periodSign;

    Image baseNormal;
    Image baseEyes;
    Image baseNoBathroom;
    Image baseRed;
    Image baseRedEyes;

    int baseX = 0;
    int baseY;

    // ================= CONSTRUCTOR =================
    public GamePanel(JFrame window) {

        this.window = window;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        loadImages();
        calculateLayout();

        AnomalyManager.generate(this, currentPeriod);
    }

    // centers hallway
    public void calculateLayout() {
        baseY = (HEIGHT / 2) - 50;
        playerY = baseY + 35;
    }

    // load images
    public void loadImages() {
        try {

            playerIdle = new ImageIcon("assets/player_idle.gif").getImage();
            playerWalk = new ImageIcon("assets/player_walk.gif").getImage();

            arrowLeft = new ImageIcon("assets/arrow_left.png").getImage();
            arrowRight = new ImageIcon("assets/arrow_right.png").getImage();

            baseNormal = new ImageIcon("assets/base.png").getImage();
            baseEyes = new ImageIcon("assets/base_following_eyes.gif").getImage();
            baseNoBathroom = new ImageIcon("assets/base_no_bathroom.png").getImage();
            baseRed = new ImageIcon("assets/base_red.png").getImage();
            baseRedEyes = new ImageIcon("assets/base_red_following_eyes.gif").getImage();

        } catch (Exception e) {
            System.out.println("Image load error: " + e.getMessage());
        }
    }

    // ================= ANOMALY CHECK =================
    public boolean hasAnomaly() {

        if (currentBase != BaseState.NORMAL) {
            return true;
        }

        return !overlays.isEmpty();
    }

    // ================= GAME LOOP =================
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        while (gameThread != null) {

            update();
            repaint();

            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                System.out.println("Loop interrupted");
            }
        }
    }

    // ================= UPDATE =================
    public void update() {

        moving = false;

        if (leftPressed) {
            playerX -= playerSpeed;
            moving = true;
            facingRight = false;
        }

        if (rightPressed) {
            playerX += playerSpeed;
            moving = true;
            facingRight = true;
        }

        if (playerX < 0) playerX = 0;
        if (playerX > 450) playerX = 450;

        nearLeftEnd = playerX <= 10;
        nearRightEnd = playerX >= 450;
    }

    // ================= DRAW =================
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // ================= BASE RENDER =================
        Image baseToDraw = baseNormal;

        switch (currentBase) {
            case NORMAL -> baseToDraw = baseNormal;
            case EYES -> baseToDraw = baseEyes;
            case NO_BATHROOM -> baseToDraw = baseNoBathroom;
            case RED -> baseToDraw = baseRed;
            case RED_EYES -> baseToDraw = baseRedEyes;
        }

        g.drawImage(baseToDraw, baseX, baseY, 500, 100, null);

        // overlays
        for (Anomaly a : overlays) {
            a.render(g2d, this);
        }

        // player
        Image current = moving ? playerWalk : playerIdle;

        if (facingRight) {
            g.drawImage(current, playerX, playerY, 40, 60, null);
        } else {
            g2d.drawImage(current, playerX + 32, playerY, -40, 60, null);
        }

        // period UI
        switch (currentPeriod) {
            case 0 -> periodSign = new ImageIcon("assets/period0.png").getImage();
            case 1 -> periodSign = new ImageIcon("assets/period1.png").getImage();
            case 2 -> periodSign = new ImageIcon("assets/period2.png").getImage();
            case 3 -> periodSign = new ImageIcon("assets/period3.png").getImage();
            case 4 -> periodSign = new ImageIcon("assets/period4.png").getImage();
            case 5 -> periodSign = new ImageIcon("assets/period5.png").getImage();
            case 6 -> periodSign = new ImageIcon("assets/period6.png").getImage();
        }

        g.drawImage(periodSign, 0, baseY, 500, 100, null);

        // arrows
        if (nearRightEnd) {
            g.drawImage(arrowRight, 0, baseY - 40, 500, 100, null);
        }

        if (nearLeftEnd && currentPeriod > 0) {
            g.drawImage(arrowLeft, 0, baseY - 40, 500, 100, null);
        }
    }

    // ================= KEY INPUT =================
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = true;

        if (e.getKeyCode() == KeyEvent.VK_Z) {

            boolean forward = nearRightEnd;
            boolean backward = nearLeftEnd;
            boolean anomaly = hasAnomaly();

            // ================= WIN CONDITION =================
            if (currentPeriod >= 6) {

                gameThread = null;

                new EndScreen();

                if (window != null) {
                    window.dispose();
                }

                return;
            }

            // ================= PERIOD 0 =================
            if (currentPeriod == 0) {

                if (forward) {
                    currentPeriod = 1;
                    playerX = 20;
                    AnomalyManager.generate(this, currentPeriod);
                }

                return;
            }

            // ================= FORWARD =================
            if (forward) {

                if (!anomaly) {
                    currentPeriod++;
                } else {
                    currentPeriod = 0;
                }

                playerX = 20;
                AnomalyManager.generate(this, currentPeriod);
            }

            // ================= BACKWARD =================
            if (backward) {

                if (anomaly) {
                    currentPeriod++;
                } else {
                    currentPeriod = 0;
                }

                playerX = 200;
                AnomalyManager.generate(this, currentPeriod);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}