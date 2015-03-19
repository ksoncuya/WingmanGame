/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wingman;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Font;

/**
 *
 * @author Kevin Soncuya
 */

public class Wingman extends JApplet implements Runnable {

    private Thread thread;
    Image sea;
    Image myPlane, boss;
    Image bullet, bulletLeft, bulletRight, enemyBullet, bigBullet;
    Image enemy1, enemy2, enemy3, bottomEnemy;
    Image healthFull, health1, health2, health3, life;
    Image powerup;
//    Image[] expArr = new Image[3];
    Image explosion1_1, explosion1_3;
    Image explosion1_6;
    Image gameover, youWin;
    private BufferedImage bimg;
    Graphics2D g2;
    int speed = 2, move = 0;
    Random generator = new Random(1234567);
    Island I1, I2, I3;
    MyPlane plane1, plane2;
    Enemy enemy;
    SideEnemies se;
    Boss bossplane;
    GameEvents ge;
    Timeline t;
    Bullet b1, b2, bl1, br1, bl2, br2, eBul;
    HealthBar hb1, hb2, life1, life2;
    Sound music, snd_explosion1, snd_explosion2;
    Explosion exp;
    PowerUp powUp;
    int hp1 = 0;
    int hp2 = 0;
    int bosshp = 100;
    int score1 = 0;
    int score2 = 0;
    boolean pickedUp1 = false;
    boolean pickedUp2 = false;
    int w = 640, h = 480; // fixed size window game 

    boolean enemiesActive, gameOver;
    // Graphics2D g2 = null;
    ImageObserver observer;

    ArrayList<Enemy> enemies = new ArrayList();
    ArrayList<SideEnemies> sideEnemies = new ArrayList();
    ArrayList<Bullet> bullets1 = new ArrayList();
    ArrayList<Bullet> bullets2 = new ArrayList();
    ArrayList<Bullet> bulletsL1 = new ArrayList();
    ArrayList<Bullet> bulletsR1 = new ArrayList();
    ArrayList<Bullet> bulletsL2 = new ArrayList();
    ArrayList<Bullet> bulletsR2 = new ArrayList();
    ArrayList<Bullet> enemyBullets = new ArrayList();

    public void init() {

        setFocusable(true);

        setBackground(Color.white);
        Image island1, island2, island3;

        try {
            //sea = getSprite("Resources/water.png");
            sea = ImageIO.read(new File("Resources/water.png"));
            island1 = ImageIO.read(new File("Resources/island1.png"));
            island2 = ImageIO.read(new File("Resources/island2.png"));
            island3 = ImageIO.read(new File("Resources/island3.png"));
            myPlane = ImageIO.read(new File("Resources/myplane_1.png"));
            bullet = ImageIO.read(new File("Resources/bullet.png"));
            bulletLeft = ImageIO.read(new File("Resources/bulletLeft.png"));
            bulletRight = ImageIO.read(new File("Resources/bulletRight.png"));
            enemyBullet = ImageIO.read(new File("Resources/enemybullet1.png"));
            bigBullet = ImageIO.read(new File("Resources/bigBullet.png"));
            enemy1 = ImageIO.read(new File("Resources/enemy1_1.png")); //green enemy
            enemy2 = ImageIO.read(new File("Resources/enemy2_1.png")); //brown enemy
            enemy3 = ImageIO.read(new File("Resources/enemy3_1.png")); //white enemy
            boss = ImageIO.read(new File("Resources/boss.png"));
            bottomEnemy = ImageIO.read(new File("Resources/enemy4_1.png"));
            healthFull = ImageIO.read(new File("Resources/health.png"));
            health1 = ImageIO.read(new File("Resources/health1.png"));
            health2 = ImageIO.read(new File("Resources/health2.png"));
            health3 = ImageIO.read(new File("Resources/health3.png"));
            life = ImageIO.read(new File("Resources/life.png"));
            explosion1_1 = ImageIO.read(new File("Resources/explosion1_1.png"));
  //          explosion1_3 = ImageIO.read(new File("Resources/explosion1_3"));
            explosion1_6 = ImageIO.read(new File("Resources/explosion1_6.png"));
            powerup = ImageIO.read(new File("Resources/powerup.png"));
            gameover = ImageIO.read(new File("Resources/gameOver.png"));
            youWin = ImageIO.read(new File("Resources/youWin.png"));
            music = new Sound("Resources/background.wav");
            snd_explosion1 = new Sound("Resources/snd_explosion1.wav");
            snd_explosion2 = new Sound("Resources/snd_explosion2.wav");
            
            music.play();
            music.loop();

            enemiesActive = true;
            gameOver = false;
            observer = this;

            I1 = new Island(island1, 100, 100, speed, generator);
            I2 = new Island(island2, 200, 400, speed, generator);
            I3 = new Island(island3, 300, 200, speed, generator);
            hb1 = new HealthBar(healthFull, 500, 400, this);
            hb2 = new HealthBar(healthFull, 20, 400, this);
            life1 = new HealthBar(life, 590, 370, this);
            life2 = new HealthBar(life, 110, 370, this);

//            expArr[0] = explosion1_1;
//            expArr[1] = explosion1_3;
//            expArr[2] = explosion1_6;
            t = new Timeline(this, enemy1, enemy2, enemy3, bottomEnemy, boss, generator, powerup, enemyBullet);

            powUp = new PowerUp(powerup, 100, -500, speed, generator, this);
            plane1 = new MyPlane(myPlane, 425, 360, speed, this);
            plane2 = new MyPlane(myPlane, 150, 360, speed, this);

            KeyControl key = new KeyControl(this, ge);
            addKeyListener(key);
            ge = new GameEvents();
            ge.addObserver(plane1);
            ge.addObserver(plane2);
            ge.addObserver(enemy);
           

        } catch (Exception e) {
            System.out.print("No resources are found");
        }
    }
    
    public void drawBackGroundWithTileImage() {
        int TileWidth = sea.getWidth(this);
        int TileHeight = sea.getHeight(this);

        int NumberX = (int) (w / TileWidth);
        int NumberY = (int) (h / TileHeight);

        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {
                g2.drawImage(sea, j * TileWidth,
                        i * TileHeight + (move % TileHeight), TileWidth,
                        TileHeight, this);
            }
        }

        move += speed;
    }

    public void drawDemo() {

        if (!gameOver) {
            drawBackGroundWithTileImage();
            I1.update(w, h);
            I2.update(w, h);
            I3.update(w, h);
            t.update();
            powUp.update();
            hb1.update();
            hb2.update();
            life1.update();
            life2.update();

            I1.draw(g2, this);
            I2.draw(g2, this);
            I3.draw(g2, this);
            t.draw(g2);
            powUp.draw(this);

            if (hp1 <= 7) {
                plane1.draw(g2, this);
                hb1.draw(g2, this);
            }
            if (hp2 <= 7) {
                plane2.draw(g2, this);
                hb2.draw(g2, this);
            }

            if (hp1 <= 3) {
                life1.draw(g2, this);
            }
            if (hp2 <= 3) {
                life2.draw(g2, this);
            }

            for (int i = 0; i < bullets1.size(); i++) {
                b1 = bullets1.get((i));
                if (b1.getY() < 0) {
                    bullets1.remove(i);
                } else {
                    b1.update();
                    b1.draw(g2, this);
                }
            }
            for (int i = 0; i < bullets2.size(); i++) {
                b2 = bullets2.get((i));
                if (b2.getY() < 0) {
                    bullets2.remove(i);
                } else {
                    b2.update();
                    b2.draw(g2, this);
                }
            }
            for (int i = 0; i < bulletsL1.size(); i++) {
                bl1 = bulletsL1.get(i);
                if (bl1.getX() < 0) {
                    bulletsL1.remove(i);
                } else {
                    bl1.update();
                    bl1.draw(g2, this);
                }
            }
            for (int i = 0; i < bulletsR1.size(); i++) {
                br1 = bulletsR1.get(i);
                if (br1.getX() < 0) {
                    bulletsR1.remove(i);
                } else {
                    br1.update();
                    br1.draw(g2, this);
                }
            }
             for (int i = 0; i < bulletsL2.size(); i++) {
                bl2 = bulletsL2.get(i);
                if (bl2.getX() < 0) {
                    bulletsL2.remove(i);
                } else {
                    bl2.update();
                    bl2.draw(g2, this);
                }
            }
            for (int i = 0; i < bulletsR2.size(); i++) {
                br2 = bulletsR2.get(i);
                if (br2.getX() < 0) {
                    bulletsR2.remove(i);
                } else {
                    br2.update();
                    br2.draw(g2, this);
                }
            }
            
            for(int i = 0; i < enemyBullets.size(); i++) {
                eBul = enemyBullets.get(i);
                if(eBul.getY() < 0) {
                    enemyBullets.remove(i);
                } else {
                    eBul.update();
                    eBul.draw(g2, this);
                }
            }

            g2.drawString("Player 1 Score: " + score1 + " points", 500, 10);
            g2.drawString("Player 2 Score: " + score2 + " points", 20, 10);

        }
        if (gameOver) {
            drawBackGroundWithTileImage();
            I1.update(w, h);
            I2.update(w, h);
            I3.update(w, h);
            I1.draw(g2, this);
            I2.draw(g2, this);
            I3.draw(g2, this);
            int gameOverW = gameover.getWidth(this);
            int gameOverH = gameover.getHeight(this);
            if(bosshp == 0) {
                g2.drawImage(youWin, 200, 100, this);
            }else {
                g2.drawImage(gameover, 200, 200, this);
            }
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Garamond", Font.BOLD, 20));
            g2.drawString("Player 1 Score: " + score1 + " points", 400, 15);
            g2.drawString("Player 2 Score: " + score2 + " points", 20, 15);
            music.stop();

        }
    }

    public void paint(Graphics g) {
        if (bimg == null) {
            Dimension windowSize = getSize();
            bimg = (BufferedImage) createImage(windowSize.width,
                    windowSize.height);
            g2 = bimg.createGraphics();
        }
        drawDemo();
        g.drawImage(bimg, 0, 0, this);
    }

    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    public void run() {

        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();
            try {
                thread.sleep(15);
            } catch (InterruptedException e) {
                break;
            }

        }
    }

    public static void main(String argv[]) {
        final Wingman demo = new Wingman();
        demo.init();
        JFrame f = new JFrame("Wingman Game");
        f.addWindowListener(new WindowAdapter() {
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(640, 480));
        f.setVisible(true);
        f.setResizable(false);
        demo.start();
    }

}
