/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wingman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Kevin Soncuya
 */

/* 
 * Health Bar 
 * Reusable
 */
public class HealthBar extends GameObject {

    Wingman wm;

    HealthBar(Image img, int x, int y, Wingman wm) {
        super(img, x, y, 0);
        this.wm = wm;
    }

    public void draw(Graphics g, ImageObserver obs) {
        g.drawImage(img, this.x, this.y, obs);
    }

    public void update() {
        //replace health bar if get hit
        //hp is health points 
        //hb is health bar
        //life is the the amount of lives of the player

        if (wm.hp1 < 3) {
            wm.life1 = new HealthBar(wm.life, 590, 370, wm);
        }

        if (wm.hp1 == 1) {
            wm.hb1 = new HealthBar(wm.health1, 500, 400, wm);
        }
        if (wm.hp1 == 2) {
            wm.hb1 = new HealthBar(wm.health2, 500, 400, wm);
        }
        if (wm.hp1 == 3) {
            wm.hb1 = new HealthBar(wm.health3, 500, 400, wm);
        }

        if (wm.hp1 == 4) {
            wm.hb1 = new HealthBar(wm.healthFull, 500, 400, wm);
            wm.pickedUp1 = false;
        }
        if (wm.hp1 == 5) {
            wm.hb1 = new HealthBar(wm.health1, 500, 400, wm);
        }
        if (wm.hp1 == 6) {
            wm.hb1 = new HealthBar(wm.health2, 500, 400, wm);
        }
        if (wm.hp1 == 7) {
            wm.hb1 = new HealthBar(wm.health3, 500, 400, wm);
        }
        if (wm.hp1 == 8) {
            wm.plane1.x = 1000;
        }

        /**
         * ***********************************************
         */
        if (wm.hp2 < 3) {
            wm.life2 = new HealthBar(wm.life, 110, 370, wm);
        }

        if (wm.hp2 == 1) {
            wm.hb2 = new HealthBar(wm.health1, 20, 400, wm);
        }
        if (wm.hp2 == 2) {
            wm.hb2 = new HealthBar(wm.health2, 20, 400, wm);
        }
        if (wm.hp2 == 3) {
            wm.hb2 = new HealthBar(wm.health3, 20, 400, wm);
        }
        if (wm.hp2 == 4) {
            wm.hb2 = new HealthBar(wm.healthFull, 20, 400, wm);
            wm.pickedUp2 = false;
        }
        if (wm.hp2 == 5) {
            wm.hb2 = new HealthBar(wm.health1, 20, 400, wm);
        }
        if (wm.hp2 == 6) {
            wm.hb2 = new HealthBar(wm.health2, 20, 400, wm);
        }
        if (wm.hp2 == 7) {
            wm.hb2 = new HealthBar(wm.health3, 20, 400, wm);
        }
        if (wm.hp2 == 8) {
            wm.plane2.x = 1000;
        }

        if (wm.hp1 > 7 && wm.hp2 > 7) {
            wm.gameOver = true;
        }
    }
}
