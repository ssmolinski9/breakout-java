import java.awt.Color;

/**
 * Basic brick to destroy in levels
 * @author Sebastian Smolinski
 * @version 0.5
 */
public class Brick {
    private final int x;
    private int y;
    private Color color;
    private boolean isDestroyed;
    int w;
    int h;

    public Brick(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.isDestroyed = false;
        this.w = 80;
        this.h = 20;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public int getW() {
        return w;
    }
    public int getH() {
        return h;
    }
}
