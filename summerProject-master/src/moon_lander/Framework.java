package moon_lander;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.BorderUIResource;

/**
 * Framework that controls the game (Game.java) that created it, update it and draw it on the screen.
 *
 * @author www.gametutorial.net
 */

public class Framework extends Canvas {

    /**
     * Width of the frame.
     */
    public static int frameWidth;
    /**
     * Height of the frame.
     */
    public static int frameHeight;

    /**
     * Time of one second in nanoseconds.
     * 1 second = 1 000 000 000 nanoseconds
     */
    public static final long secInNanosec = 1000000000L;

    /**
     * Time of one millisecond in nanoseconds.
     * 1 millisecond = 1 000 000 nanoseconds
     */
    public static final long milisecInNanosec = 1000000L;

    /**
     * FPS - Frames per second
     * How many times per second the game should update?
     */
    private final int GAME_FPS = 16;
    /**
     * Pause between updates. It is in nanoseconds.
     */
    private final long GAME_UPDATE_PERIOD = secInNanosec / GAME_FPS;

    /**
     * Possible states of the game
     */
    public static enum GameState{STARTING, VISUALIZING, GAME_CONTENT_LOADING, MAIN_MENU, DESCRIPTION, OPTIONS, PLAYING, GAMEOVER, DESTROYED} //열거체 GameState
    /**
     * Current state of the game
     */
    public static GameState gameState;

    /**
     * Elapsed game time in nanoseconds.
     */
    static long gameTime;
    // It is used for calculating elapsed time.
    private long lastTime;

    // The actual game
    private Game game;

    //Background music player
    MP3Player mp3Background;


    /**
     * Image for menu.
     */
    private BufferedImage moonLanderMenuImg;

    private BufferedImage moonLanderDescriptionImg;

    private BufferedImage moonLanderOptionImg;


    private ImageIcon startButtonImg;

    private ImageIcon descriptionButtonImg;

    private JButton startButton;

    private JButton descriptionButton;

    //로켓 선택 결과
    public static enum RocketChoice{ORI, RED, YELLOW, GREEN, BLUE, PINK}

    public static RocketChoice rocketChoice;

    //로켓(캐릭터) 선택 버튼 image
    ImageIcon rocketImg;

    ImageIcon redRocketImg;

    ImageIcon yellowRocketImg;

    ImageIcon greenRocketImg;

    ImageIcon blueRocketImg;

    ImageIcon pinkRocketImg;

    //로켓 선택 버튼
    private JButton rocketButton;

    private JButton redRocketButton;

    private JButton yellowRocketButton;

    private JButton greenRocketButton;

    private JButton blueRocketButton;

    private JButton pinkRocketButton;



    public Framework ()
    {
        super(); //슈퍼클래스의 생성자 호출 : Canvas()

        gameState = GameState.VISUALIZING;

        //We start game in new thread.
        Thread gameThread = new Thread() {
            @Override
            public void run(){
                GameLoop();
            }
        };
        gameThread.start();
    }


    /**
     * Set variables and objects.
     * This method is intended to set the variables and objects for this class, variables and objects for the actual game can be set in Game.java.
     */
    private void Initialize()
    {
        //background music player
        mp3Background = new MP3Player("C:\\Users\\user\\IdeaProjects\\moon_lander\\moon_lander\\src\\resources\\mp3\\background.wav", true);
        mp3Background.start();

    }

    /**
     * Load files - images, sounds, ...
     * This method is intended to load files for this class, files for the actual game can be loaded in Game.java.
     */
    private void LoadContent()
    {
        //시작 배경화면
        try
        {
            URL moonLanderMenuImgUrl = this.getClass().getResource("/resources/images/menu.jpg");
            moonLanderMenuImg = ImageIO.read(moonLanderMenuImgUrl);

            URL moonLanderOptionImgUrl = this.getClass().getResource("/resources/images/rocket_select.png");
            moonLanderOptionImg = ImageIO.read(moonLanderOptionImgUrl);

            URL moonLanderDescriptionUrl = this.getClass().getResource("/resources/images/game_description.png");
            moonLanderDescriptionImg = ImageIO.read(moonLanderDescriptionUrl);


        }
        catch (IOException ex) {
            Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
        }


        startButtonImg = new ImageIcon("C:\\Users\\user\\IdeaProjects\\summerProject-master\\summerProject-master\\src\\resources\\images\\start.png");
        descriptionButtonImg = new ImageIcon("C:\\Users\\user\\IdeaProjects\\summerProject-master\\summerProject-master\\src\\resources\\images\\description.png");

        startButton = new JButton(startButtonImg);
        descriptionButton = new JButton(descriptionButtonImg);

        //시작버튼
        startButton.setBounds(210,350,135,100);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                startButton.setVisible(false);
                descriptionButton.setVisible(false);
                descriptionButton.setFocusable(false);
                startButton.setFocusable(false);
                setLayout(null);
                gameState = GameState.OPTIONS;
                selectCharacter();
            }
        });

        add(startButton);

        //설명 버튼
        descriptionButton.setBounds(410,350,135,100);
        descriptionButton.setBorderPainted(false);
        descriptionButton.setContentAreaFilled(false);
        descriptionButton.setFocusPainted(false);

        descriptionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                startButton.setVisible(false);
                descriptionButton.setVisible(false);
                descriptionButton.setFocusable(false);
                startButton.setFocusable(false);
                setLayout(null);
                gameState = GameState.DESCRIPTION;

            }
        });

        add(descriptionButton);

    }

    private void rocketRemove()
    {
        rocketButton.setVisible(false);
        redRocketButton.setVisible(false);
        yellowRocketButton.setVisible(false);
        greenRocketButton.setVisible(false);
        blueRocketButton.setVisible(false);
        pinkRocketButton.setVisible(false);

    }

    private void buttonLoseFocus() {

        rocketButton.setFocusable(false);
        redRocketButton.setFocusable(false);
        yellowRocketButton.setFocusable(false);
        greenRocketButton.setFocusable(false);
        blueRocketButton.setFocusable(false);
        pinkRocketButton.setFocusable(false);
    }

    private void selectCharacter()
    {
        //rocket(기본)
        rocketImg = new ImageIcon("C:\\Users\\user\\IdeaProjects\\summerProject-master\\summerProject-master\\src\\resources\\images\\rocket.png");
        rocketButton = new JButton(rocketImg);
        rocketButton.setBounds(200,200,50,100);
        rocketButton.setBorderPainted(false);
        rocketButton.setContentAreaFilled(false);
        rocketButton.setFocusPainted(false);
        rocketButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                rocketChoice = RocketChoice.ORI;
                buttonLoseFocus();
                rocketRemove();
                newGame();
            }
        });

        add(rocketButton);

        //red rocket
        redRocketImg = new ImageIcon("C:\\Users\\user\\IdeaProjects\\summerProject-master\\summerProject-master\\src\\resources\\images\\red_rocket.png");
        redRocketButton = new JButton(redRocketImg);
        redRocketButton.setBounds(350,200,50,100);
        redRocketButton.setBorderPainted(false);
        redRocketButton.setContentAreaFilled(false);
        redRocketButton.setFocusPainted(false);
        redRocketButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                rocketChoice = RocketChoice.RED;
                buttonLoseFocus();
                rocketRemove();
                newGame();
            }
        });
        add(redRocketButton);

        //yellow rocket
        yellowRocketImg = new ImageIcon("C:\\Users\\user\\IdeaProjects\\summerProject-master\\summerProject-master\\src\\resources\\images\\yellow_rocket.png");
        yellowRocketButton = new JButton(yellowRocketImg);
        yellowRocketButton.setBounds(500,200,50,100);
        yellowRocketButton.setBorderPainted(false);
        yellowRocketButton.setContentAreaFilled(false);
        yellowRocketButton.setFocusPainted(false);
        yellowRocketButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                rocketChoice = RocketChoice.YELLOW;
                buttonLoseFocus();
                rocketRemove();
                newGame();
            }
        });
        add(yellowRocketButton);

        //green rocket
        greenRocketImg = new ImageIcon("C:\\Users\\user\\IdeaProjects\\summerProject-master\\summerProject-master\\src\\resources\\images\\green_rocket.png");
        greenRocketButton = new JButton(greenRocketImg);
        greenRocketButton.setBounds(200,350,50,100);
        greenRocketButton.setBorderPainted(false);
        greenRocketButton.setContentAreaFilled(false);
        greenRocketButton.setFocusPainted(false);
        greenRocketButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                rocketChoice = RocketChoice.GREEN;
                buttonLoseFocus();
                rocketRemove();
                newGame();
            }
        });
        add(greenRocketButton);

        //blue rocket
        blueRocketImg = new ImageIcon("C:\\Users\\user\\IdeaProjects\\summerProject-master\\summerProject-master\\src\\resources\\images\\blue_rocket.png");
        blueRocketButton = new JButton(blueRocketImg);
        blueRocketButton.setBounds(350,350,50,100);
        blueRocketButton.setBorderPainted(false);
        blueRocketButton.setContentAreaFilled(false);
        blueRocketButton.setFocusPainted(false);
        blueRocketButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                rocketChoice = RocketChoice.BLUE;
                buttonLoseFocus();
                rocketRemove();
                newGame();
            }
        });
        add(blueRocketButton);

        //pink rocket
        pinkRocketImg = new ImageIcon("C:\\Users\\user\\IdeaProjects\\summerProject-master\\summerProject-master\\src\\resources\\images\\pink_rocket.png");
        pinkRocketButton = new JButton(pinkRocketImg);
        pinkRocketButton.setBounds(500,350,50,100);
        pinkRocketButton.setBorderPainted(false);
        pinkRocketButton.setContentAreaFilled(false);
        pinkRocketButton.setFocusPainted(false);
        pinkRocketButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                rocketChoice = RocketChoice.PINK;
                buttonLoseFocus();
                rocketRemove();
                newGame();
            }
        });
        add(pinkRocketButton);

    }



    /**
     * In specific intervals of time (GAME_UPDATE_PERIOD) the game/logic is updated and then the game is drawn on the screen.
     */
    private void GameLoop()
    {
        // This two variables are used in VISUALIZING state of the game. We used them to wait some time so that we get correct frame/window resolution.
        long visualizingTime = 0, lastVisualizingTime = System.nanoTime();

        // This variables are used for calculating the time that defines for how long we should put threat to sleep to meet the GAME_FPS.
        long beginTime, timeTaken, timeLeft;

        while(true)
        {
            beginTime = System.nanoTime();

            switch (gameState)
            {
                case PLAYING:
                    gameTime += System.nanoTime() - lastTime;

                    game.UpdateGame(gameTime, mousePosition());

                    lastTime = System.nanoTime();
                    break;
                case GAMEOVER:
                    //...
                    break;
                case MAIN_MENU:
                    //...
                    break;
                case OPTIONS:
                    //...
                    break;
                case GAME_CONTENT_LOADING:
                    //...
                    break;
                case STARTING:
                    // Sets variables and objects.
                    Initialize();
                    // Load files - images, sounds, ...
                    LoadContent();

                    // When all things that are called above finished, we change game status to main menu.
                    gameState = GameState.MAIN_MENU;
                    break;
                case VISUALIZING:
                    // On Ubuntu OS (when I tested on my old computer) this.getWidth() method doesn't return the correct value immediately (eg. for frame that should be 800px width, returns 0 than 790 and at last 798px).
                    // So we wait one second for the window/frame to be set to its correct size. Just in case we
                    // also insert 'this.getWidth() > 1' condition in case when the window/frame size wasn't set in time,
                    // so that we although get approximately size.
                    if(this.getWidth() > 1 && visualizingTime > secInNanosec)
                    {
                        frameWidth = this.getWidth();
                        frameHeight = this.getHeight();

                        // When we get size of frame we change status.
                        gameState = GameState.STARTING;
                    }
                    else
                    {
                        visualizingTime += System.nanoTime() - lastVisualizingTime;
                        lastVisualizingTime = System.nanoTime();
                    }
                    break;
            }

            // Repaint the screen.
            repaint();

            // Here we calculate the time that defines for how long we should put threat to sleep to meet the GAME_FPS.
            timeTaken = System.nanoTime() - beginTime;
            timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / milisecInNanosec; // In milliseconds
            // If the time is less than 10 milliseconds, then we will put thread to sleep for 10 millisecond so that some other thread can do some work.
            if (timeLeft < 10)
                timeLeft = 10; //set a minimum
            try {
                //Provides the necessary delay and also yields control so that other thread can do work.
                Thread.sleep(timeLeft);
            } catch (InterruptedException ex) { }
        }
    }

    /**
     * Draw the game to the screen. It is called through repaint() method in GameLoop() method.
     */
    @Override
    public void Draw(Graphics2D g2d)
    {
        switch (gameState)
        {
            case PLAYING:
                game.Draw(g2d, mousePosition());
                break;
            case GAMEOVER:

                game.DrawGameOver(g2d, mousePosition(), gameTime);
                break;
            case MAIN_MENU:
                g2d.drawImage(moonLanderMenuImg, 0, 0, frameWidth, frameHeight, null);
                g2d.setColor(Color.white);
                g2d.drawString("시작과 설명 중 하나를 눌러주세요",600,frameHeight - 5);
                g2d.drawString("WWW.GAMETUTORIAL.NET", 7, frameHeight - 5);
                break;
            case DESCRIPTION:
                g2d.drawImage(moonLanderDescriptionImg, 0, 0, frameWidth, frameHeight, null);
                break;
            case OPTIONS:
                g2d.drawImage(moonLanderOptionImg, 0, 0, frameWidth, frameHeight, null);
                g2d.setColor(Color.white);
                g2d.drawString("스페이스 바를 누르면 기본 로켓으로 시작합니다",270, frameHeight - 5);
                break;
            case GAME_CONTENT_LOADING:
                g2d.setColor(Color.white);
                g2d.drawString("GAME is LOADING", frameWidth / 2 - 50, frameHeight / 2);
                break;
        }
    }

    /**
     * Starts new game.
     */
    private void newGame()
    {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        lastTime = System.nanoTime();

        game = new Game();
    }

    /**
     *  Restart game - reset game time and call RestartGame() method of game object so that reset some variables.
     */
    private void restartGame()
    {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        lastTime = System.nanoTime();

        game.RestartGame();

        // We change game status so that the game can start.
        gameState = GameState.PLAYING;
    }

    /**
     * Returns the position of the mouse pointer in game frame/window.
     * If mouse position is null than this method return 0,0 coordinate.
     *
     * @return Point of mouse coordinates.
     */
    private Point mousePosition()
    {
        try
        {
            Point mp = this.getMousePosition();

            if(mp != null)
                return this.getMousePosition();
            else
                return new Point(0, 0);
        }
        catch (Exception e)
        {
            return new Point(0, 0);
        }
    }

    /**
     * This method is called when keyboard key is released.
     *
     * @param e KeyEvent
     */
    @Override
    public void keyReleasedFramework(KeyEvent e)
    {
        switch (gameState)
        {
            case MAIN_MENU:
                break;
            case DESCRIPTION:
                gameState = GameState.OPTIONS;
                selectCharacter();
                break;
            case OPTIONS:
                break;
            case GAMEOVER:
                if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER)
                    restartGame();
                break;
        }
    }

    /**
     * This method is called when mouse button is clicked.
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {

    }
}