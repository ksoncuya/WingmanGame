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
public class BottomEnemies extends Enemy {

    BottomEnemies(Image img, int x, int y, int speed, Random gen, Wingman wm) {
        super(img, x, y, speed, gen, wm);

    }

    public void update() {
        y -= speed;

        if (wm.b1 != null) {
            if (wm.b1.collision(x, y, sizeX, sizeY)) {
                wm.ge.setValue("Explosion");
                wm.ge.setValue("");
                wm.score1 += 10;
                wm.snd_explosion1.play();
                show = false;
                this.reset();
                show = true;
                System.out.println("Score = " + wm.score1);
                wm.exp = new Explosion(wm.explosion1_1, wm.enemy.x, wm.enemy.y);
                wm.exp.draw(wm.g2, wm.observer);
                wm.bullets1.remove(wm.b1);
            } else {
                wm.ge.setValue("");
            }
        }

        if (wm.b2 != null) {
            if (wm.b2.collision(x, y, sizeX, sizeY)) {
                wm.exp = new Explosion(wm.explosion1_1, wm.enemy.x, wm.enemy.y);
                wm.exp.draw(wm.g2, wm.observer);
                wm.bullets2.remove(wm.b2);
                wm.snd_explosion1.play();
                wm.score2 += 10;
                show = false;
                this.reset();
                show = true;
                System.out.println("Score = " + wm.score1);

            }
        }

        if (wm.br1 != null) {
            if (wm.br1.collision(x, y, sizeX, sizeY)) {
                wm.score1 += 10;
                wm.snd_explosion1.play();
                wm.exp = new Explosion(wm.explosion1_1, wm.enemy.x, wm.enemy.y);
                wm.exp.draw(wm.g2, wm.observer);
                wm.bulletsR1.remove(wm.br1);
                show = false;
                this.reset();
                show = true;
                System.out.println("Score = " + wm.score1);
            }
        }
        if (wm.bl1 != null) {
            if (wm.bl1.collision(x, y, sizeX, sizeY)) {
                wm.score1 += 10;
                wm.exp = new Explosion(wm.explosion1_1, wm.enemy.x, wm.enemy.y);
                wm.exp.draw(wm.g2, wm.observer);
                wm.bulletsL1.remove(wm.bl1);
                wm.snd_explosion1.play();
                show = false;
                this.reset();
                show = true;
                System.out.println("Score = " + wm.score1);
            }
        }

        if (wm.br2 != null) {
            if (wm.br2.collision(x, y, sizeX, sizeY)) {
                wm.score2 += 10;
                wm.snd_explosion1.play();
                wm.exp = new Explosion(wm.explosion1_1, wm.enemy.x, wm.enemy.y);
                wm.exp.draw(wm.g2, wm.observer);
                wm.bulletsR2.remove(wm.br2);
                show = false;
                this.reset();
                show = true;
                System.out.println("Score = " + wm.score2);
            }
        }
        if (wm.bl2 != null) {
            if (wm.bl2.collision(x, y, sizeX, sizeY)) {
                wm.score2 += 10;
                wm.exp = new Explosion(wm.explosion1_1, wm.enemy.x, wm.enemy.y);
                wm.exp.draw(wm.g2, wm.observer);
                wm.bulletsL2.remove(wm.bl2);
                wm.snd_explosion1.play();
                show = false;
                this.reset();
                show = true;
                System.out.println("Score = " + wm.score2);
            }
        }

        if (wm.plane1.collision(x, y, sizeX, sizeY)) {
            wm.exp = new Explosion(wm.explosion1_1, wm.enemy.x, wm.enemy.y);
            wm.exp.draw(wm.g2, wm.observer);
            show = false;
            // You need to remove this one and increase score1 etc
            this.reset();
            show = true;
            wm.snd_explosion1.play();
            wm.hp1++;
            System.out.println(wm.hp1);
        }

        if (wm.plane2.collision(x, y, sizeX, sizeY)) {
            wm.exp = new Explosion(wm.explosion1_1, wm.enemy.x, wm.enemy.y);
            wm.exp.draw(wm.g2, wm.observer);
            show = false;
            // You need to remove this one and increase score1 etc
            this.reset();
            wm.snd_explosion1.play();
            show = true;
            wm.hp2++;
            System.out.println(wm.hp2);
        }
    }

    public void draw(Graphics g, ImageObserver obs) {
        if (show) {
            g.drawImage(img, x, y, obs);
        }
    }
}
