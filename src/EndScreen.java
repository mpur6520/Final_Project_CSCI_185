import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.FontFormatException;
import java.io.IOException;

// END SCREEN
public class EndScreen extends JFrame {

    Font pixelFont;

    public EndScreen() {

        // ================= WINDOW =================
        setTitle("GAME END");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        loadFont();

        // ================= TITLE =================
        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setBackground(Color.BLACK);
        topWrapper.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        JLabel title = new JLabel("END OF THE DAY", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(pixelFont.deriveFont(22f));

        topWrapper.add(title, BorderLayout.CENTER);

        // ================= CENTER CONTENT =================
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JTextArea message = new JTextArea();

        message.setText(
                "You made it through all 6 periods!\n\n" +
                        "Your perception held steady...\n" +
                        "or maybe it didn't.\n\n" +
                        "Either way, you're out. Probably.\n\n" +
                        "Hopefully. Definitely. I guess.\n\n" +
                        "Congrats!\n\n" +
                        "I think."
        );

        message.setEditable(false);
        message.setFocusable(false);
        message.setBackground(Color.BLACK);
        message.setForeground(Color.WHITE);
        message.setFont(pixelFont.deriveFont(14f));
        message.setLineWrap(true);
        message.setWrapStyleWord(true);

        // ================= EXIT PROMPT =================
        JLabel prompt = new JLabel("PRESS Z TO EXIT", SwingConstants.CENTER);
        prompt.setForeground(Color.WHITE);
        prompt.setFont(pixelFont.deriveFont(14f));

        // blinking effect (yes i did it again)
        new Timer(500, e -> prompt.setVisible(!prompt.isVisible())).start();

        // stack message + prompt
        JPanel stack = new JPanel(new BorderLayout());
        stack.setBackground(Color.BLACK);

        stack.add(message, BorderLayout.CENTER);
        stack.add(prompt, BorderLayout.SOUTH);

        centerPanel.add(stack, BorderLayout.CENTER);

        // ================= ADD TO FRAME =================
        add(topWrapper, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);

        // ================= INPUT =================
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_Z) {
                    System.exit(0);
                }
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    // ================= LOAD FONT =================
    public void loadFont() {

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
}