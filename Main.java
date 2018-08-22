import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Breakout");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new GamePanel());
        window.setResizable(false);

        String imagePath = "res/img/icon.png";
        InputStream imgStream = Main.class.getResourceAsStream(imagePath);
        try {
            BufferedImage myImg = ImageIO.read(imgStream);
            window.setIconImage(myImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        window.pack();
        window.setVisible(true);
    }
}