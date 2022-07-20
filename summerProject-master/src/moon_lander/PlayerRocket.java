package moon_lander;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * The space rocket with which player will have to land.
 * 
 * @author www.gametutorial.net
 */

public class PlayerRocket {
    
    /**
     * We use this to generate a random number for starting x coordinate of the rocket.
     */
    private Random random;
 
    /**
     * X coordinate of the rocket.
     */
    public int x;
    /**
     * Y coordinate of the rocket.
     */
    public int y;
    
    /**
     * Is rocket landed?
     */
    public boolean landed;
    
    /**
     * Has rocket crashed?
     */
    public boolean crashed;
        
    /**
     * Accelerating speed of the rocket.
     */
    private int speedAccelerating;
    /**
     * Stopping/Falling speed of the rocket. Falling speed because, the gravity pulls the rocket down to the moon.
     */
    private int speedStopping;
    
    /**
     * Maximum speed that rocket can have without having a crash when landing.
     */
    public int topLandingSpeed;
    
    /**
     * How fast and to which direction rocket is moving on x coordinate?
     */
    private int speedX;
    /**
     * How fast and to which direction rocket is moving on y coordinate?
     */
    public int speedY;
            
    /**
     * Image of the rocket in air.
     */
    private BufferedImage rocketImg;
    private BufferedImage redRocketImg;
    private BufferedImage yellowRocketImg;
    private BufferedImage greenRocketImg;
    private BufferedImage blueRocketImg;
    private BufferedImage pinkRocketImg;
    /**
     * Image of the rocket when landed.
     */
    private BufferedImage rocketLandedImg;
    private BufferedImage redRocketLandedImg;
    private BufferedImage yellowRocketLandedImg;
    private BufferedImage greenRocketLandedImg;
    private BufferedImage blueRocketLandedImg;
    private BufferedImage pinkRocketLandedImg;
    /**
     * Image of the rocket when crashed.
     */
    private BufferedImage rocketCrashedImg;
    private BufferedImage redRocketCrashedImg;
    private BufferedImage yellowRocketCrashedImg;
    private BufferedImage greenRocketCrashedImg;
    private BufferedImage blueRocketCrashedImg;
    private BufferedImage pinkRocketCrashedImg;
    /**
     * Image of the rocket fire.
     */
    private BufferedImage rocketFireImg;
    
    /**
     * Width of rocket.
     */
    public int rocketImgWidth;
    /**
     * Height of rocket.
     */
    public int rocketImgHeight;
    
    
    public PlayerRocket()
    {
        Initialize();
        LoadContent();
        
        // Now that we have rocketImgWidth we set starting x coordinate.
        x = random.nextInt(Framework.frameWidth - rocketImgWidth);
    }
    
    
    private void Initialize()
    {
        random = new Random();
        
        ResetPlayer();
        
        speedAccelerating = 5;
        speedStopping = 1;
        
        topLandingSpeed = 5;
    }
    
    private void LoadContent()
    {
        try
        {
            URL rocketImgUrl = this.getClass().getResource("/resources/images/rocket.png");
            rocketImg = ImageIO.read(rocketImgUrl);
            rocketImgWidth = rocketImg.getWidth();
            rocketImgHeight = rocketImg.getHeight();

            URL rocketLandedImgUrl = this.getClass().getResource("/resources/images/rocket_landed.png");
            rocketLandedImg = ImageIO.read(rocketLandedImgUrl);


            URL rocketCrashedImgUrl = this.getClass().getResource("/resources/images/rocket_crashed.png");
            rocketCrashedImg = ImageIO.read(rocketCrashedImgUrl);


            URL rocketFireImgUrl = this.getClass().getResource("/resources/images/rocket_fire.png");
            rocketFireImg = ImageIO.read(rocketFireImgUrl);
        }
        catch (IOException ex) {
            Logger.getLogger(PlayerRocket.class.getName()).log(Level.SEVERE, null, ex);
        }

        try
        {
            URL redRocketImgUrl = this.getClass().getResource("/resources/images/red_rocket.png");
            redRocketImg = ImageIO.read(redRocketImgUrl);

            URL yellowRocketImgUrl = this.getClass().getResource("/resources/images/yellow_rocket.png");
            yellowRocketImg = ImageIO.read(yellowRocketImgUrl);

            URL greenRocketImgUrl = this.getClass().getResource("/resources/images/green_rocket.png");
            greenRocketImg = ImageIO.read(greenRocketImgUrl);

            URL blueRocketImgUrl = this.getClass().getResource("/resources/images/blue_rocket.png");
            blueRocketImg = ImageIO.read(blueRocketImgUrl);

            URL pinkRocketImgUrl = this.getClass().getResource("/resources/images/pink_rocket.png");
            pinkRocketImg = ImageIO.read(pinkRocketImgUrl);

        }
        catch (IOException ex) {
            Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
        }


        try
        {
            URL redRocketLandedImgUrl = this.getClass().getResource("/resources/images/red_rocketLanded.png");
            redRocketLandedImg = ImageIO.read(redRocketLandedImgUrl);

            URL yellowRocketLandedImgUrl = this.getClass().getResource("/resources/images/yellow_rocketLanded.png");
            yellowRocketLandedImg = ImageIO.read(yellowRocketLandedImgUrl);

            URL greenRocketLandedImgUrl = this.getClass().getResource("/resources/images/green_rocketLanded.png");
            greenRocketLandedImg = ImageIO.read(greenRocketLandedImgUrl);

            URL blueRocketLandedImgUrl = this.getClass().getResource("/resources/images/blue_rocketLanded.png");
            blueRocketLandedImg = ImageIO.read(blueRocketLandedImgUrl);

            URL pinkRocketLandedImgUrl = this.getClass().getResource("/resources/images/pink_rocketLanded.png");
            pinkRocketLandedImg = ImageIO.read(pinkRocketLandedImgUrl);
        }
        catch (IOException ex) {
            Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
        }

        try
        {
            URL redRocketCrashedImgUrl = this.getClass().getResource("/resources/images/red_rocketCrashed.png");
            redRocketCrashedImg = ImageIO.read(redRocketCrashedImgUrl);

            URL yellowRocketCrashedImgUrl = this.getClass().getResource("/resources/images/yellow_rocketCrashed.png");
            yellowRocketCrashedImg = ImageIO.read(yellowRocketCrashedImgUrl);

            URL greenRocketCrashedImgUrl = this.getClass().getResource("/resources/images/green_rocketCrashed.png");
            greenRocketCrashedImg = ImageIO.read(greenRocketCrashedImgUrl);

            URL blueRocketCrashedImgUrl = this.getClass().getResource("/resources/images/blue_rocketCrashed.png");
            blueRocketCrashedImg = ImageIO.read(blueRocketCrashedImgUrl);

            URL pinkRocketCrashedImgUrl = this.getClass().getResource("/resources/images/pink_rocketCrashed.png");
            pinkRocketCrashedImg = ImageIO.read(pinkRocketCrashedImgUrl);
        }
        catch (IOException ex) {
            Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    
    /**
     * Here we set up the rocket when we starting a new game.
     */
    public void ResetPlayer()
    {
        landed = false;
        crashed = false;
        
        x = random.nextInt(Framework.frameWidth - rocketImgWidth);
        y = 10;
        
        speedX = 0;
        speedY = 0;
    }
    
    
    /**
     * Here we move the rocket.
     */
    public void Update()
    {
        // Calculating speed for moving up or down.
        if(Canvas.keyboardKeyState(KeyEvent.VK_W))
            speedY -= speedAccelerating;
        else
            speedY += speedStopping;
        
        // Calculating speed for moving or stopping to the left.
        if(Canvas.keyboardKeyState(KeyEvent.VK_A))
            speedX -= speedAccelerating;
        else if(speedX < 0)
            speedX += speedStopping;
        
        // Calculating speed for moving or stopping to the right.
        if(Canvas.keyboardKeyState(KeyEvent.VK_D))
            speedX += speedAccelerating;
        else if(speedX > 0)
            speedX -= speedStopping;
        
        // Moves the rocket.
        x += speedX;
        y += speedY;
    }
    
    public void Draw(Graphics2D g2d)
    {
        g2d.setColor(Color.white);
        g2d.drawString("Rocket coordinates: " + x + " : " + y, 5, 15);
        
        // If the rocket is landed.
        if(landed)
        {
            switch (Framework.rocketChoice)
            {
                case ORI:
                    g2d.drawImage(rocketLandedImg, x, y, null);
                    break;
                case RED:
                    g2d.drawImage(redRocketLandedImg, x, y, null);
                    break;
                case YELLOW:
                    g2d.drawImage(yellowRocketLandedImg, x, y, null);
                    break;
                case GREEN:
                    g2d.drawImage(greenRocketLandedImg, x, y, null);
                    break;
                case BLUE:
                    g2d.drawImage(blueRocketLandedImg, x, y, null);
                    break;
                case PINK:
                    g2d.drawImage(pinkRocketLandedImg, x, y, null);
                    break;
            }
        }
        // If the rocket is crashed.
        else if(crashed)
        {
            switch (Framework.rocketChoice)
            {
                case ORI:
                    g2d.drawImage(rocketCrashedImg, x, y + rocketImgHeight - rocketCrashedImg.getHeight(), null);
                    break;
                case RED:
                    g2d.drawImage(redRocketCrashedImg, x, y + rocketImgHeight - rocketCrashedImg.getHeight(), null);
                    break;
                case YELLOW:
                    g2d.drawImage(yellowRocketCrashedImg, x, y + rocketImgHeight - rocketCrashedImg.getHeight(), null);
                    break;
                case GREEN:
                    g2d.drawImage(greenRocketCrashedImg, x, y + rocketImgHeight - rocketCrashedImg.getHeight(), null);
                    break;
                case BLUE:
                    g2d.drawImage(blueRocketCrashedImg, x, y + rocketImgHeight - rocketCrashedImg.getHeight(), null);
                    break;
                case PINK:
                    g2d.drawImage(pinkRocketCrashedImg, x, y + rocketImgHeight - rocketCrashedImg.getHeight(), null);
                    break;
            }
        }
        // If the rocket is still in the space.
        else
        {
            // If player hold down a W key we draw rocket fire.
            if(Canvas.keyboardKeyState(KeyEvent.VK_W))
                g2d.drawImage(rocketFireImg, x + 12, y + 66, null);
            switch (Framework.rocketChoice)
            {
                case ORI:
                    g2d.drawImage(rocketImg, x, y, null);
                    break;
                case RED:
                    g2d.drawImage(redRocketImg, x, y, null);
                    break;
                case YELLOW:
                    g2d.drawImage(yellowRocketImg, x, y, null);
                    break;
                case GREEN:
                    g2d.drawImage(greenRocketImg, x, y, null);
                    break;
                case BLUE:
                    g2d.drawImage(blueRocketImg, x, y, null);
                    break;
                case PINK:
                    g2d.drawImage(pinkRocketImg, x, y, null);
                    break;
            }


        }
    }
    
}
