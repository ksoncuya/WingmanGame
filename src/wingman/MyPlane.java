/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wingman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;

public class MyPlane extends Player {

    int sizeX, sizeY, width, height;
    int boom;
    Rectangle bbox;
    Wingman wm;
    
    MyPlane(Image img, int x, int y, int speed, Wingman wm) {

        super(img, x, y, speed);

        width = img.getWidth(null);
        height = img.getHeight(null);
        sizeX = img.getWidth(null);
        sizeY = img.getHeight(null);
        System.out.println("plane w: " + width + "," + "plane h: " + height);
        boom = 0;
        this.wm = wm;

    }

    public boolean collision(int x, int y, int w, int h) {
        bbox = new Rectangle(this.x, this.y, this.width, this.height);
        Rectangle otherBBox = new Rectangle(x, y, w, h);
        if (this.bbox.intersects(otherBBox)) {
            return true;
        }
        return false;
    }

    public void update(Observable obj, Object arg) {
        GameEvents ge = (GameEvents) arg;
        if (ge.type == 1) {
            KeyEvent e = (KeyEvent) ge.event;

            switch (e.getKeyCode()) {

                case KeyEvent.VK_LEFT:
                    if (wm.plane1.getX() > 0) {
                        wm.plane1.x -= speed + 5;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (wm.plane1.getX() < (wm.w - 70)) {
                        wm.plane1.x += speed + 5;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (wm.plane1.getY() > 0) {
                        wm.plane1.y -= speed + 5;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (wm.plane1.getY() < (wm.h - 90)) {
                        wm.plane1.y += speed + 5;
                    }
                    break;
                case KeyEvent.VK_M:
                        wm.bullets1.add(new Bullet(wm.bullet, wm.plane1.getX() + 17, wm.plane1.getY(), 5, wm));
                       
                        System.out.println("FIRE");
                        if (wm.pickedUp1) {
                            wm.bulletsL1.add(new BulletLeft(wm.bulletLeft, wm.plane1.getX() - 20, wm.plane1.getY(), 5, wm));
                            wm.bulletsR1.add(new BulletRight(wm.bulletRight, wm.plane1.getX() + 30, wm.plane1.getY(), 5, wm));
                        }
                    break;

                case KeyEvent.VK_A:
                    if (wm.plane2.getX() > 0) {
                        wm.plane2.x -= speed + 5;
                    }
                    break;
                case KeyEvent.VK_D:
                    if (wm.plane2.getX() < (wm.w - 70)) {
                        wm.plane2.x += speed + 5;
                    }
                    break;
                case KeyEvent.VK_W:
                    if (wm.plane2.getY() > 0) {
                        wm.plane2.y -= speed + 5;
                    }
                    break;
                case KeyEvent.VK_S:
                    if (wm.plane2.getY() < (wm.h - 90)) {
                        wm.plane2.y += speed + 5;
                    }
                    break;
                case KeyEvent.VK_V:
                    wm.bullets2.add(new Bullet(wm.bullet, wm.plane2.getX() + 17, wm.plane2.getY(), 5, wm));
                    if (wm.pickedUp2) {
                        wm.bulletsL2.add(new BulletLeft(wm.bulletLeft, wm.plane2.getX() - 20, wm.plane2.getY(), 5, wm));
                        wm.bulletsR2.add(new BulletRight(wm.bulletRight, wm.plane2.getX() + 30, wm.plane2.getY(), 5, wm));
                    }
                    break;

                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    wm.music.stop();
                    break;
                default:
                    if (e.getKeyChar() == ' ') {
                        System.out.println("Fire");
                    }
            }
        }
    }

}
