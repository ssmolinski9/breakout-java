import java.awt.Color;

class HPBonusBall extends BonusBall {
    public HPBonusBall() {
        this.r = generator.nextInt(5) + 10;
        this.x = generator.nextInt(GamePanel.WIDTH - r);
        this.y = 0;
        this.isDestroyed = true;
        this.color = Color.RED;
    }
}
