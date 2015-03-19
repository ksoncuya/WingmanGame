/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wingman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Random;

/**
 *
 * @author Kevin Soncuya
 */
public class Enemy extends GameObject {

    int sizeX, sizeY, width, height;
    Random gen;
    Rectangle bbox;
    boolean show;
    Wingman wm;

    Enemy(Image img, int x, int y, int speed, Random gen, Wingman wm) {
        super(img, x, y, speed);

        this.gen = gen;
        this.show = true;
        this.wm = wm;
        sizeX = img.getWidth(null);
        sizeY = img.getHeight(null);
        System.out.println("w:" + sizeX + " y:" + sizeY);
        width = img.getWidth(null);
        height = img.getWidth(null);
    }

    public void update() {
        y += 3;

        if (wm.b1 != null) {
            if (wm.b1.collision(x, y, sizeX, sizeY)) {
                wm.exp = new Explosion(wm.explosion1_1, wm.enemy.x, wm.enemy.y);
                wm.exp.draw(wm.g2, wm.observer);
                wm.bullets1.remove(wm.b1);
                wm.snd_explosion1.play();
                wm.score1 += 10;
                show = false;
                this.reset();
                show = true;
                System.out.println("Score = " + wm.score1);

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
//            wm.ge.setValue("Explosion");
//            wm.ge.setValue("");
            show = false;
            // You need to remove this one and increase score1 etc
            this.reset();
            show = true;
            wm.hp1++;
            wm.snd_explosion1.play();
            System.out.println(wm.hp1);
        }
//        else {
//                wm.ge.setValue("");
//        }
//
        if (wm.plane2.collision(x, y, sizeX, sizeY)) {
            show = false;
            // You need to remove this one and increase score1 etc
            this.reset();
            show = true;
            wm.hp2++;
            wm.snd_explosion1.play();
            System.out.println(wm.hp2);
        }

    }

    public boolean collision(int x, int y, int w, int h) {
        bbox = new Rectangle(this.x, this.y, this.width, this.height);
        Rectangle otherBBox = new Rectangle(x, y, w, h);
        if (this.bbox.intersects(otherBBox)) {
            return true;
        }
        return false;
    }

    public void reset() {
        this.x = Math.abs(wm.generator.nextInt() % (600 - 30));
        this.y = -10;
    }

    public void draw(Graphics g, ImageObserver obs) {
        if (show) {
            g.drawImage(img, x, y, obs);
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
