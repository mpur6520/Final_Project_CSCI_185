import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.FontFormatException;
import java.io.IOException;

// INTRO SCREEN
public class IntroScreen extends JFrame {

    Font pixelFont;

    public IntroScreen() {

        // ================= WINDOW =================
        setTitle("GAME INTRO");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        loadFont();

        // ================= WRAPPER PANEL (FOR MARGINS) =================
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);

        // padding to make it pretty
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // ================= TITLE =================
        JLabel title = new JLabel("GAME INTRO", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(pixelFont.deriveFont(22f));

        // couldn't figure out a better way to make it pretty
        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setBackground(Color.BLACK);
        topWrapper.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        topWrapper.add(title, BorderLayout.CENTER);

        // ================= RULES TEXT =================
        JTextArea rules = new JTextArea();

        rules.setText(
                "RULES:\n\n" +
                        "Observe the hallway carefully.\n\n" +
                        "If everything looks NORMAL:\n" +
                        "-> Go FORWARD (RIGHT)\n\n" +
                        "If something looks WRONG:\n" +
                        "-> Go BACKWARD (LEFT)\n\n" +
                        "Wrong decision = RESET TO 0\n\n\n" +
                        "Period 0 is always normal.\n\n" +
                        "You have 6 periods to get through.\n\n"
        );

        rules.setEditable(false);
        rules.setFocusable(false);
        rules.setBackground(Color.BLACK);
        rules.setForeground(Color.WHITE);
        rules.setFont(pixelFont.deriveFont(14f));
        rules.setLineWrap(true);
        rules.setWrapStyleWord(true);

        // ================= CONTINUE PROMPT =================
        JLabel prompt = new JLabel("PRESS Z TO CONTINUE", SwingConstants.CENTER);
        prompt.setForeground(Color.WHITE);
        prompt.setFont(pixelFont.deriveFont(14f));

        // blinking effect (look it's just like a real game!!)
        new Timer(500, e -> {
            prompt.setVisible(!prompt.isVisible());
        }).start();

        // ================= CENTER STACK =================
        JPanel stack = new JPanel(new BorderLayout());
        stack.setBackground(Color.BLACK);

        stack.add(rules, BorderLayout.CENTER);
        stack.add(prompt, BorderLayout.SOUTH);

        centerPanel.add(stack, BorderLayout.CENTER);

        // ================= ADD TO WINDOW =================
        add(topWrapper, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);

        // key listener for Z
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_Z) {
                    dispose();
                    startGame();
                }
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    // ================= LOAD PIXEL FONT =================
    public void loadFont() {
        // load font (thank you again reddit)
        try {
            pixelFont = Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("assets/fonts/PressStart2P-Regular.ttf")
            ).deriveFont(16f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);

        } catch (FontFormatException | IOException e) {

            System.out.println("Font load failed: " + e.getMessage());
            pixelFont = new Font("Arial", Font.PLAIN, 16);
        }
    }

    // ================= START GAME =================
    public void startGame() {

        JFrame window = new JFrame("GAME START");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel panel = new GamePanel(window);

        window.add(panel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.startGameThread(); // today is a wonderful day
    }
}