/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wingman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

/**
 *
 * @author Kevin Soncuya
 */
public class Boss extends Enemy {

    Boss(Image img, int x, int y, int speed, Random gen, Wingman wm) {
        super(img, x, y, speed, gen, wm);

    }

    public void update() {

        x -= speed;

        if (x <= 0) {
            speed = -speed;
        } else if (x >= 325) {
            speed = -speed;
        }

        if (wm.bosshp == 0) {
            show = false;
            x = 1000;
            wm.gameOver = true;
        }

        if (wm.b1 != null) {
            if (wm.b1.collision(x, y, sizeX, sizeY)) {
                                wm.bosshp -= 1;
                wm.bullets1.remove(wm.b1);
                wm.snd_explosion1.play();
                wm.score1 += 10;
            }
        }
        if (wm.b2 != null) {
            if (wm.b2.collision(x, y, sizeX, sizeY)) {
                                wm.bosshp -= 1;
                wm.bullets2.remove(wm.b2);
                wm.snd_explosion1.play();
                wm.score2 += 10;


            }
        }

        if (wm.br1 != null) {
            if (wm.br1.collision(x, y, sizeX, sizeY)) {
                wm.score1 += 10;
                                wm.bosshp -= 1;
                wm.bulletsR1.remove(wm.br1);
                wm.snd_explosion1.play();

            }
        }
        if (wm.bl1 != null) {
            if (wm.bl1.collision(x, y, sizeX, sizeY)) {
                                wm.bosshp -= 1;
                wm.bulletsL1.remove(wm.bl1);
                wm.snd_explosion1.play();
                wm.score1 += 10;
            }
        }
        if (wm.br2 != null) {
            if (wm.br2.collision(x, y, sizeX, sizeY)) {
                wm.score2 += 10;
                                wm.bosshp -= 1;
                wm.bulletsR2.remove(wm.br2);
                wm.snd_explosion1.play();
            }
        }
        if (wm.bl2 != null) {
            if (wm.bl2.collision(x, y, sizeX, sizeY)) {
                wm.score2 += 10;
                                wm.bosshp -= 1;
                wm.snd_explosion1.play();
                wm.bulletsL2.remove(wm.bl2);
            }
        }

    }

    public void draw(Graphics g, ImageObserver obs) {
        if (show) {
            g.drawImage(img, x, y, obs);
        }
    }

}
