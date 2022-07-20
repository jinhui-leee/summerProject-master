package moon_lander;

import com.sun.source.tree.TryTree;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Enemy {
    BufferedImage enemyImg;
    int x, y;
    int width = enemyImg.getWidth(null);
    int height = enemyImg.getHeight(null);

    int x2 = x + width;
    int y2 = y + height;

    public Enemy(int x, int y) {
        loadContent();

        this.x = x;
        this.y = y;
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
}
