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

    private PlayerRocket playerRocket;
    private Random random;

    public Enemy(int playerRocketHeight, int playerRocketY, int landingAreaY) {
        Initialize();
        loadContent();
        resetXY(playerRocketHeight,playerRocketY,landingAreaY);


    }

    private void Initialize() { random = new Random(); }


    public void loadContent() {
        try {
            URL enemyImgUrl = this.getClass().getResource("/resources/images/enemy.png");
            enemyImg = ImageIO.read(enemyImgUrl);
        } catch (IOException ex) {
            Logger.getLogger(PlayerRocket.class.getName()).log(Level.SEVERE, null, ex);
        }

        enemyImgWidth = enemyImg.getWidth();
        enemyImgHeight = enemyImg.getHeight();
    }


    public void Draw(Graphics2D g2d) {
        g2d.drawImage(enemyImg, x, y, null);

    }

    public void resetXY(int playerRocketHeight, int playerRocketY, int landingAreaY) {
        x = random.nextInt(Framework.frameWidth - enemyImgWidth);
        y = random.nextInt(landingAreaY - 2*playerRocketHeight - playerRocketY - enemyImgHeight) + playerRocketHeight + playerRocketY;


    }

    public Boolean isCrashed(int rocketX1, int rocketY1, int width, int Height) {

        int rocketX2 = rocketX1 + width;
        int rocketY2 = rocketY1 + Height;
        if (rocketX1 >= x && rocketX1 <= x + enemyImgWidth || rocketX2 >= x && rocketX2 <= x + enemyImgWidth)
        {
            if (rocketY1 >= y && rocketY1 <= y + enemyImgHeight || rocketY2 >= y && rocketY2 <= y + enemyImgHeight)
            {
                return true;
            }
        }
        return false;
    }
}

