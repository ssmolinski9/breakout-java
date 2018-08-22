import java.awt.Color;

/**
 * Special hard version of brick. It takes two shots to destroy it.
 * @author Sebastian Smolinski
 * @version 0.5
 * @see Brick
 */
class HardBrick extends Brick {
    private int lives;

    public HardBrick(int x, int y) {
        super(x, y, Color.BLACK);
        this.h = 20;
        this.w = 80;
        this.lives = 2;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
