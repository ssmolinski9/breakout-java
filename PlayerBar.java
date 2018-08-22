import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Represents player character and contains its behavior
 * @author Sebastian Smolinski
 * @version 0.5
 */
class PlayerBar {
    private int x, y;
    private int w, h;
    private int dx, speed;
    private int score;

    private boolean left, right;
    private int lives;

    private boolean recovering;
    private long recoveryTimer;

    public PlayerBar() {
        this.w = 60;
        this.h = 20;
        this.x = GamePanel.WIDTH / 2 - w;
        this.y = GamePanel.HEIGHT - h;

        this.dx = 0;
        this.speed = 5;
        this.lives = 3;
        this.score = 0;

        this.recovering = false;
        this.recoveryTimer = 0;
    }

    public void update() {
        if(left) {
            dx = -speed;
        }
        if(right) {
            dx = speed;
        }

        x += dx;

        if(x < 0) x = 0;
        if(x > GamePanel.WIDTH - w) x = GamePanel.WIDTH - w;

        dx = 0;

        long elapsed = (System.nanoTime() - recoveryTimer) / 1000000;
        if(elapsed > 2000) {
            recovering = false;
            recoveryTimer = 0;
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, w, h);
        g.setStroke(new BasicStroke(3));
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getLives() {
        return lives;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void loseLife() {
        lives--;
        recovering = true;
        recoveryTimer = System.nanoTime();
    }

    public void addLife() {
        lives++;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isRecovering() {
        return recovering;
    }
}
