
 import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.event.KeyAdapter;
        import java.awt.event.KeyEvent;
        import java.util.ArrayList;
        import java.util.Random;

class SnakeGame extends JPanel implements ActionListener {
    private final int WIDTH = 300;
    private final int HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 150;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int appleX;
    private int appleY;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private boolean isEatingApple = false;

    private Timer timer;

    public SnakeGame() {
        initGame();
    }

    private void initGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new TAdapter());

        initializeSnake();
        placeApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initializeSnake() {
        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
    }

    private void placeApple() {
        Random rand = new Random();
        appleX = rand.nextInt(RAND_POS) * DOT_SIZE;
        appleY = rand.nextInt(RAND_POS) * DOT_SIZE;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            move();
            checkApple();
            checkCollision();
            repaint();
        }
    }

    private void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[z - 1];
            y[z] = y[z - 1];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            dots++;
            isEatingApple = true;
            placeApple();
        } else {
            isEatingApple = false;
        }
    }

    private void checkCollision() {
        for (int z = dots; z > 0; z--) {
            if (z > 3 && x[0] == x[z] && y[0] == y[z]) {
                inGame = false;
            }
        }

        if (x[0] >= WIDTH || x[0] < 0 || y[0] >= HEIGHT || y[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        if (!inGame) {
            gameOver(g);
        }
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            drawApple(g);
            drawSnake(g);
        }
    }

    private void drawApple(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(appleX, appleY, DOT_SIZE, DOT_SIZE);
    }

    private void drawSnake(Graphics g) {
        for (int z = 0; z < dots; z++) {
            if (z == 0) {
                g.setColor(Color.green);
            } else {
                g.setColor(Color.orange);
            }
            g.fillRect(x[z], y[z], DOT_SIZE, DOT_SIZE);
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over";
        String scoreMsg = "Score: " + (dots - 3);

        Font font = new Font("Helvetica", Font.BOLD, 24);
        FontMetrics metrics = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(msg, (WIDTH - metrics.stringWidth(msg)) / 2, HEIGHT / 2);
        g.drawString(scoreMsg, (WIDTH - metrics.stringWidth(scoreMsg)) / 2, HEIGHT / 2 + 30);
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SnakeGame());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}