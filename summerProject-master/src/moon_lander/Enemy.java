package moon_lander;

import javax.swing.*;
import java.awt.*;

public class Enemy {
    Image enemyImg = new ImageIcon("src/images/enemy.png").getImage();
    int x, y;
    int width = enemyImg.getWidth(null);
    int height = enemyImg.getHeight(null);

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
