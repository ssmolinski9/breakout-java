import java.awt.Color;

/**
 * Special small version of basic brick
 * @author Sebastian Smolinski
 * @version 0.5
 * @see Brick
 */
class SmallBrick extends Brick {
    public SmallBrick(int x, int y, Color color) {
        super(x, y, color);
        this.w = 40;
        this.h = 20;
    }
}
