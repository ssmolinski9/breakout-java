import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Represents ball and its behavior
 * @author Sebastian Smolinski
 * @version 0.5
 */
class Ball {
    private int x, y;
    private final int r;
    private double velX, velY;

    public Ball() {
        this.r = 10;
        this.x = GamePanel.WIDTH / 2;
        this.y = GamePanel.HEIGHT / 2;
        this.velX = 3;
        this.velY = 3;
    }

    public void update() {
        if(x < 0 || x > GamePanel.WIDTH - 2*r) {
            velX = -velX;
            GamePanel.playSound("hit.wav");
        }
        if(y + 2*r > GamePanel.HEIGHT || y < 0) {
            velY = -velY;
            GamePanel.playSound("hit.wav");
        }


        x += velX;
        y += velY;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 2*r, 2*r);
        g.setStroke(new BasicStroke(3));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public void increaseVel() {
        velX += (velX > 0) ? 0.1 : -0.1;
        velY += (velY > 0) ? 0.1 : -0.1;
    }
}
