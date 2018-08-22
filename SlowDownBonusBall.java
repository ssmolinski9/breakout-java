import java.awt.Color;

class SlowDownBonusBall extends BonusBall {
    public SlowDownBonusBall() {
        this.r = generator.nextInt(5) + 10;
        this.x = generator.nextInt(GamePanel.WIDTH - r);
        this.y = 0;
        this.isDestroyed = true;
        this.color = Color.BLUE;
    }
}
