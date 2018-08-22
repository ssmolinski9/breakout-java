import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * JPanel with game content
 * @author Sebastian Smolinski
 * @version 0.5
 * @see JPanel
 */
class GamePanel extends JPanel implements Runnable, KeyListener {
    public static final int WIDTH = 800;
    public static int HEIGHT = 600;

    private Thread thread;
    private boolean running;

    private BufferedImage image;
    private Graphics2D g;

    private int FPS = 30;
    private double averageFPS;

    private LevelCreator levelCreator;
    private PlayerBar player;
    private Ball ball;
    private ArrayList<BonusBall> bonusBalls;

    private int levelCount;

    private boolean isMenuVisible;
    private int menuItemID;

    private boolean isPaused;

    private long musicTimer;
    private long bonusTimer;
    private long specialEffectTimer;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        addKeyListener(this);
    }

    public void run() {
        Random generator = new Random();

        running = true;
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D)image.getGraphics();

        player = new PlayerBar();
        ball = new Ball();
        levelCount = 0;
        isMenuVisible = true;
        menuItemID = 0;
        isPaused = false;
        musicTimer = System.nanoTime();
        bonusTimer = 0;

        bonusBalls = new ArrayList<>();

        levelCreator = new LevelCreator();
        levelCreator.createFirstLevel();
        levelCreator.createSecondLevel();
        levelCreator.createThirdLevel();
        levelCreator.createForthLevel();
        levelCreator.createFifthLevel();
        levelCreator.createSixthLevel();

        bonusBalls.add(new HPBonusBall());
        bonusBalls.add(new PointsBonusBall());
        bonusBalls.add(new BigPlayerBonusBall());
        bonusBalls.add(new SlowDownBonusBall());

        long startTime;
        long URDTimeMillis;
        long waitTime;
        long totalTime = 0;

        int frameCount = 0;
        int maxFrameCount = 30;

        long targetTime = 1000 / FPS;
        playSound("bgm.wav");

        while(running) {
            startTime = System.nanoTime();

            if(!isPaused) {
                if (!isMenuVisible) {
                    gameUpdate();
                    gameRender();
                    gameDraw();
                } else {
                    menuRender();
                    gameDraw();
                }
            } else {
                g.setColor(new Color(192, 192, 192, 5));
                g.fillRect(0, 0, WIDTH, HEIGHT);

                g.setColor(Color.BLACK);
                g.setFont(new Font(getFont().getName(), getFont().getStyle(), 18));
                String s = "P A U S E";
                int length = (int)g.getFontMetrics().getStringBounds(s, g).getWidth();
                g.drawString(s, (WIDTH - length) / 2, HEIGHT / 2);
                g.setFont(new Font(getFont().getName(), getFont().getStyle(), 12));
                s = "Press P to continue";
                length = (int)g.getFontMetrics().getStringBounds(s, g).getWidth();
                g.drawString(s, (WIDTH - length) / 2, HEIGHT / 2 + 25);
                gameDraw();
            }

            URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - URDTimeMillis;

            try {
                Thread.sleep(waitTime);
            } catch(Exception ignored) { }

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if(frameCount == maxFrameCount) {
                averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
            }

            if((System.nanoTime() - musicTimer) / 1000000 > 204000) {
                playSound("bgm.wav");
                musicTimer = System.nanoTime();
            }

            if(bonusTimer != 0)
                if( ((System.nanoTime() - bonusTimer) / 1000000) > generator.nextInt(45000) + 20000) {
                    int pos = generator.nextInt(4);
                    bonusBalls.get(pos).resetBall();
                    bonusBalls.get(pos).setDestroyed(false);
                    bonusTimer = System.nanoTime();
                }

            if(specialEffectTimer != 0)
                if( ((System.nanoTime() - specialEffectTimer) / 1000000) > 10000) {
                    resetEffects();
                    specialEffectTimer = 0;
                }
        }

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        String s = "G A M E  O V E R";
        int length = (int)g.getFontMetrics().getStringBounds(s, g).getWidth();
        g.drawString(s, (WIDTH - length) / 2, HEIGHT / 2);

        String p = "Score: " + player.getScore();
        length = (int)g.getFontMetrics().getStringBounds(p, g).getWidth();
        g.drawString(p, (WIDTH - length) / 2, HEIGHT / 2 + 25);

        gameDraw();
        playSound("gameover.wav");
    }

    public void keyTyped(KeyEvent key) {}

    public void keyPressed(KeyEvent key) {
        int keyCode = key.getKeyCode();

        if(keyCode == KeyEvent.VK_A)
            player.setLeft(true);
        if(keyCode == KeyEvent.VK_D)
            player.setRight(true);

        if(isMenuVisible) {
            if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_UP) {
                if(menuItemID == 0) menuItemID = 1;
                else if(menuItemID == 1) menuItemID = 0;
            }

            if(keyCode == KeyEvent.VK_ENTER && menuItemID == 0) {
                isMenuVisible = false;
                bonusTimer = System.nanoTime();
            }
            else if(keyCode == KeyEvent.VK_ENTER && menuItemID == 1)
                System.exit(0);
        } else {
            if(keyCode == KeyEvent.VK_P)
                isPaused = !isPaused;
        }

        if(keyCode == KeyEvent.VK_ESCAPE)
            System.exit(0);
    }

    public void keyReleased(KeyEvent key) {
        int keyCode = key.getKeyCode();

        if(keyCode == KeyEvent.VK_A)
            player.setLeft(false);
        if(keyCode == KeyEvent.VK_D)
            player.setRight(false);
    }

    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource("res/audio/" + url));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    private void gameUpdate() {
        if(ball.getY() + ball.getR() * 2 >= player.getY() && ball.getY() + ball.getR() * 2 <= player.getY() + player.getH())
            if(ball.getX() + ball.getR() * 2 >= player.getX() && ball.getX() + ball.getR() * 2 <= player.getX() + player.getW()) {
                ball.setVelY(-ball.getVelY());
                ball.increaseVel();
                playSound("hit.wav");
            }

        for(BonusBall bonusBall : bonusBalls) {
            if(!bonusBall.isDestroyed())
                if(bonusBall.getY() + bonusBall.getR() * 2 >= player.getY() && bonusBall.getY() + bonusBall.getR() * 2 <= player.getY() + player.getH())
                    if(bonusBall.getX() + bonusBall.getR() * 2 >= player.getX() && bonusBall.getX() + bonusBall.getR() * 2 <= player.getX() + player.getW()) {
                        bonusBall.setDestroyed(true);
                        if(bonusBall instanceof HPBonusBall)
                            player.addLife();
                        if(bonusBall instanceof PointsBonusBall)
                            player.setScore(player.getScore() + 50);
                        if(bonusBall instanceof SlowDownBonusBall) {
                            ball.setVelY( (ball.getVelY() > 0) ? 3 : -3 );
                            ball.setVelX( (ball.getVelX() > 0) ? 3 : -3 );
                        }
                        if(bonusBall instanceof BigPlayerBonusBall) {
                            specialEffectTimer = System.nanoTime();
                            player.setW(100);
                        }
                    }
        }

        if(ball.getY() >= HEIGHT - ball.getR() * 2 && !player.isRecovering()) {
            player.loseLife();
            ball.setX(WIDTH/2 - ball.getR());
            ball.setY(HEIGHT/2 - ball.getR());
            playSound("loselife.wav");
        }

        if(player.isRecovering()) {
            ball.setVelY(0);
            ball.setVelX(0);
        } else if(!player.isRecovering() && ball.getVelY() == 0 && ball.getVelX() == 0) {
            ball.setVelY(3);
            ball.setVelX(3);
        }

        if(player.getLives() == 0)
            running = false;

        boolean endLevel = true;
        for(Brick brick : levelCreator.getLevels().get(levelCount).getBricks()) {
            if (!brick.isDestroyed())
                if (ball.getX() + ball.getR() * 2 >= brick.getX() && ball.getX() <= brick.getX() + brick.getW()) {
                    if (ball.getY() + ball.getR() * 2 >= brick.getY() && ball.getY() + ball.getR() * 2 <= brick.getY() + brick.getH() + ball.getR() * 2) {
                        if(brick instanceof HardBrick) {
                            ((HardBrick) brick).setLives(((HardBrick) brick).getLives() - 1);
                            if(((HardBrick) brick).getLives() == 0) {
                                brick.setDestroyed(true);
                                playSound("shoot.wav");
                            } else {
                                brick.setColor(Color.RED);
                                playSound("hardshoot.wav");
                            }
                        } else {
                            brick.setDestroyed(true);
                            playSound("shoot.wav");
                        }

                        if(ball.getX() + ball.getR() * 2 == brick.getX() + 1 || ball.getX() == brick.getX() + brick.getW() - 1)
                            ball.setVelX(-ball.getVelX());
                        else
                            ball.setVelY(-ball.getVelY());

                        player.setScore(player.getScore() + 10);
                        ball.increaseVel();
                    } else {
                        endLevel = false;
                    }
                } else {
                    endLevel = false;
                }
        }

        if(endLevel && levelCreator.getLevels().size() > levelCount +1) {
            levelCount++;
            ball.setX(WIDTH / 2);
            ball.setY(HEIGHT / 2);
            ball.setVelY(3);
            ball.setVelX(3);
            playSound("nextlevel.wav");
        } else if(endLevel && levelCreator.getLevels().size() == levelCount +1) {
            running = false;
        }

        player.update();
        ball.update();

        for(BonusBall bonusBall : bonusBalls)
            if(!bonusBall.isDestroyed())
                bonusBall.update();
    }

    private void gameRender() {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.drawString("FPS: " + (int)averageFPS, 10, 10);
        g.drawString("Lives: " + player.getLives(), 10, 25);
        g.drawString("Score: " + player.getScore(), WIDTH - 70, 10);
        g.drawString("Level: " + (levelCount +1), WIDTH - 70, 25);

        player.draw(g);
        ball.draw(g);
        for(BonusBall bonusBall : bonusBalls)
            if(!bonusBall.isDestroyed())
                bonusBall.draw(g);

        for(Brick brick : levelCreator.getLevels().get(levelCount).getBricks()) {
            if(!brick.isDestroyed()) {
                Rectangle2D brick2D;
                if(brick instanceof SmallBrick)
                    brick2D = new Rectangle(brick.getX(), brick.getY(), 40, 20);
                else
                    brick2D = new Rectangle(brick.getX(), brick.getY(), 80, 20);

                g.setPaint(brick.getColor());
                g.fill(brick2D);
            }
        }
    }

    private void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }

    private void menuRender() {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        String s = "B R E A K O U T";
        int length = (int)g.getFontMetrics().getStringBounds(s, g).getWidth();
        g.drawString(s, (WIDTH - length) / 2, HEIGHT / 2 - 40);

        if(menuItemID == 0) g.setColor(Color.YELLOW);
        else g.setColor(Color.WHITE);
        s = "Start Game";
        length = (int)g.getFontMetrics().getStringBounds(s, g).getWidth();
        g.drawString(s, (WIDTH - length) / 2, HEIGHT / 2);

        if(menuItemID == 1) g.setColor(Color.YELLOW);
        else g.setColor(Color.WHITE);
        s = "Exit Game";
        length = (int)g.getFontMetrics().getStringBounds(s, g).getWidth();
        g.drawString(s, (WIDTH - length) / 2, HEIGHT / 2 + 20);
    }

    private void resetEffects() {
        if(player.getW() > 60)
            player.setW(60);
    }
}
