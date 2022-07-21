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

    public int x, y;

    public int enemyImgWidth;

    public int enemyImgHeight;
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

        enemyImgWidth = enemyImg.getWidth();
        enemyImgHeight = enemyImg.getHeight();
    }


    public void Draw(Graphics2D g2d) {
        g2d.drawImage(enemyImg, x, y, null);

    }

    public void resetXY() {
        x = random.nextInt(Framework.frameWidth - enemyImg.getWidth());
        y = random.nextInt(Framework.frameHeight - enemyImg.getHeight());


    }

    public Boolean isCrashed(int rocketX1, int rocketY1, int width, int Height) {

        int rocketX2 = rocketX1 + width;
        int rocketY2 = rocketY1 + Height;
        if (rocketX1 >= x && rocketX1 <= x + enemyImgWidth) {
            if (rocketY1 >= y && rocketY1 <= y + enemyImgHeight) {
                return true;
            }
            if (rocketY2 >= y && rocketY2 <= y + enemyImgHeight) {
                return true;
            }
        }
        if (rocketX2 >= x && rocketX2 <= x + enemyImgWidth) {
            if (rocketY1 >= y && rocketY1 <= y + enemyImgHeight) {
                return true;
            }
            if (rocketY2 >= y && rocketY2 <= y + enemyImgHeight) {
                return true;
            }
        }

        return false;
    }

    public int getX() {
        return x;
    }

    public  int getY(){
        return y;
    }
}
