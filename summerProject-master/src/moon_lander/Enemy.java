package moon_lander;

import com.sun.source.tree.TryTree;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Enemy {
    BufferedImage enemyImg;

    int x, y;
    private Random random;

    public Enemy() {
        Initialize();
        loadContent();
        x = random.nextInt(Framework.frameWidth - enemyImg.getWidth());
        y = random.nextInt(Framework.frameHeight - enemyImg.getHeight());

    }

    private void Initialize() {
        random = new Random();
    }


    public void loadContent() {
        try
        {
            URL enemyImgUrl = this.getClass().getResource("/resources/images/enemy.png");
            enemyImg = ImageIO.read(enemyImgUrl);
        }
        catch (IOException ex) {
            Logger.getLogger(PlayerRocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void Draw(Graphics2D g2d) {
        g2d.drawImage(enemyImg, x, y, null);

    }

    public void resetXY() {
        x = random.nextInt(Framework.frameWidth - enemyImg.getWidth());
        y = random.nextInt(Framework.frameHeight - enemyImg.getHeight());


    }



}
