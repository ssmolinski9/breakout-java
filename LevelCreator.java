import java.awt.Color;
import java.util.ArrayList;

/**
 * LevelCreator creates levels for the game. It contains every level's elements mapping
 * @author Sebastian Smolinski
 * @version 0.5
 */
class LevelCreator {
    private ArrayList<Level> levels;

    public LevelCreator() {
        levels = new ArrayList<>();
    }

    public void createFirstLevel() {
        Brick[] bricks = new Brick[18];

        bricks[0] = new Brick(10, 40, Color.RED);
        bricks[1] = new Brick(95, 40, Color.YELLOW);
        bricks[2] = new Brick(180, 40, Color.BLUE);
        bricks[3] = new Brick(265, 40, Color.GREEN);
        bricks[4] = new Brick(350, 40, Color.CYAN);
        bricks[5] = new Brick(435, 40, Color.ORANGE);
        bricks[6] = new Brick(520, 40, Color.PINK);
        bricks[7] = new Brick(605, 40, Color.MAGENTA);
        bricks[8] = new Brick(690, 40, Color.LIGHT_GRAY);

        bricks[9] = new Brick(10, 65, Color.LIGHT_GRAY);
        bricks[10] = new Brick(95, 65, Color.MAGENTA);
        bricks[11] = new Brick(180, 65, Color.PINK);
        bricks[12] = new Brick(265, 65, Color.ORANGE);
        bricks[13] = new Brick(350, 65, Color.CYAN);
        bricks[14] = new Brick(435, 65, Color.GREEN);
        bricks[15] = new Brick(520, 65, Color.BLUE);
        bricks[16] = new Brick(605, 65, Color.YELLOW);
        bricks[17] = new Brick(690, 65, Color.RED);

        levels.add(new Level(bricks));
    }

    public void createSecondLevel() {
        Brick[] bricks = new Brick[20];
        bricks[0] = new Brick(10, 40, Color.RED);
        bricks[1] = new Brick(95, 40, Color.YELLOW);
        bricks[2] = new Brick(180, 40, Color.BLUE);
        bricks[3] = new Brick(265, 40, Color.GREEN);
        bricks[4] = new Brick(350, 40, Color.CYAN);
        bricks[5] = new Brick(435, 40, Color.ORANGE);
        bricks[6] = new Brick(520, 40, Color.PINK);
        bricks[7] = new Brick(605, 40, Color.MAGENTA);
        bricks[8] = new Brick(690, 40, Color.LIGHT_GRAY);

        bricks[9] = new Brick(10, 65, Color.LIGHT_GRAY);
        bricks[10] = new Brick(95, 65, Color.MAGENTA);
        bricks[11] = new Brick(180, 65, Color.PINK);
        bricks[12] = new Brick(265, 65, Color.ORANGE);
        bricks[13] = new Brick(350, 65, Color.CYAN);
        bricks[14] = new Brick(435, 65, Color.GREEN);
        bricks[15] = new Brick(520, 65, Color.BLUE);
        bricks[16] = new Brick(605, 65, Color.YELLOW);
        bricks[17] = new Brick(690, 65, Color.RED);

        bricks[18] = new HardBrick(265, 90);
        bricks[19] = new HardBrick(435, 90);

        levels.add(new Level(bricks));
    }

    public void createThirdLevel() {
        Brick[] bricks = new Brick[14];

        bricks[0] = new Brick(30, 100, Color.RED);
        bricks[1] = new Brick(90, 55, Color.GREEN);
        bricks[2] = new Brick(90, 150, Color.BLUE);
        bricks[3] = new Brick(160, 100, Color.YELLOW);
        bricks[4] = new Brick(230, 55, Color.MAGENTA);
        bricks[5] = new Brick(230, 150, Color.PINK);
        bricks[6] = new Brick(300, 100, Color.ORANGE);

        bricks[7] = new Brick(400, 100, Color.RED);
        bricks[8] = new Brick(450, 55, Color.GREEN);
        bricks[9] = new Brick(450, 150, Color.BLUE);
        bricks[10] = new Brick(520, 100, Color.YELLOW);
        bricks[11] = new Brick(590, 55, Color.MAGENTA);
        bricks[12] = new Brick(590, 150, Color.PINK);
        bricks[13] = new Brick(660, 100, Color.ORANGE);

        levels.add(new Level(bricks));
    }

    public void createForthLevel() {
        Brick[] bricks = new Brick[14];

        bricks[0] = new SmallBrick(30, 100, Color.RED);
        bricks[1] = new SmallBrick(90, 55, Color.GREEN);
        bricks[2] = new SmallBrick(90, 150, Color.BLUE);
        bricks[3] = new SmallBrick(160, 100, Color.YELLOW);
        bricks[4] = new SmallBrick(230, 55, Color.MAGENTA);
        bricks[5] = new SmallBrick(230, 150, Color.PINK);
        bricks[6] = new SmallBrick(300, 100, Color.ORANGE);

        bricks[7] = new SmallBrick(400, 100, Color.RED);
        bricks[8] = new SmallBrick(450, 55, Color.GREEN);
        bricks[9] = new SmallBrick(450, 150, Color.BLUE);
        bricks[10] = new SmallBrick(520, 100, Color.YELLOW);
        bricks[11] = new SmallBrick(590, 55, Color.MAGENTA);
        bricks[12] = new SmallBrick(590, 150, Color.PINK);
        bricks[13] = new SmallBrick(660, 100, Color.ORANGE);

        levels.add(new Level(bricks));
    }

    public void createFifthLevel() {
        Brick[] bricks = new Brick[20];
        bricks[0] = new HardBrick(10, 40);
        bricks[1] = new HardBrick(95, 40);
        bricks[2] = new HardBrick(180, 40);
        bricks[3] = new HardBrick(265, 40);
        bricks[4] = new HardBrick(350, 40);
        bricks[5] = new HardBrick(435, 40);
        bricks[6] = new HardBrick(520, 40);
        bricks[7] = new HardBrick(605, 40);
        bricks[8] = new HardBrick(690, 40);

        bricks[9] = new HardBrick(10, 65);
        bricks[10] = new HardBrick(95, 65);
        bricks[11] = new HardBrick(180, 65);
        bricks[12] = new HardBrick(265, 65);
        bricks[13] = new HardBrick(350, 65);
        bricks[14] = new HardBrick(435, 65);
        bricks[15] = new HardBrick(520, 65);
        bricks[16] = new HardBrick(605, 65);
        bricks[17] = new HardBrick(690, 65);

        bricks[18] = new HardBrick(265, 90);
        bricks[19] = new HardBrick(435, 90);

        levels.add(new Level(bricks));
    }

    public void createSixthLevel() {
        Brick[] bricks = new Brick[12];

        bricks[0] = new HardBrick(220, 150);
        bricks[1] = new HardBrick(520, 150);
        bricks[2] = new HardBrick(370, 60);
        bricks[3] = new HardBrick(370, 230);
        bricks[4] = new Brick(325, 100, Color.MAGENTA);
        bricks[5] = new Brick(415, 100, Color.GREEN);
        bricks[6] = new Brick(325, 130, Color.ORANGE);
        bricks[7] = new Brick(415, 130, Color.CYAN);
        bricks[8] = new Brick(325, 160, Color.GRAY);
        bricks[9] = new Brick(415, 160, new Color(0xAB02FF));
        bricks[10] = new Brick(325, 190, new Color(0xFF0505));
        bricks[11] = new Brick(415, 190, new Color(0x021284));

        levels.add(new Level(bricks));
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }
}
