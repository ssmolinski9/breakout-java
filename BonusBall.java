import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/**
 * Bonus Ball represents randomly generated ball to catch by player
 * @author Sebastian Smolinski
 * @version 0.5
 */
abstract class BonusBall {
    int x;
    int y;
    int r;
    boolean isDestroyed;
    final Random generator = new Random();
    Color color;

    BonusBall() {
        this.r = generator.nextInt(5) + 10;
        this.x = generator.nextInt(GamePanel.WIDTH - r);
        this.y = 0;
        this.isDestroyed = true;
    }

    public void update() {
        if(y + r <= 0) {
            isDestroyed = true;
        }

        y += 3;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval(x, y, 2*r, 2*r);
        g.setStroke(new BasicStroke(3));
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public void resetBall() {
        this.r = generator.nextInt(5) + 10;
        this.x = generator.nextInt(GamePanel.WIDTH - r);
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }
}
