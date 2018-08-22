/**
 * Single game level with its elements
 * @author Sebastian Smolinski
 * @version 0.5
 */
class Level {
    private Brick[] bricks;

    public Level(Brick[] bricks) {
        this.bricks = bricks;
    }

    public Brick[] getBricks() {
        return bricks;
    }
}
