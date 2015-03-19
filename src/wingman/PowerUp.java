/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wingman;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

public class PowerUp extends GameObject {

    int sizeX;
    int sizeY;
    Random gen;
    Wingman wm;

    PowerUp(Image img, int x, int y, int speed, Random gen, Wingman wm) {
        super(img, x, y, speed);
        
        this.wm = wm;
        this.gen = gen;
        sizeX = img.getWidth(null);
        sizeY = img.getHeight(null);
    }

    public void update() {
        y += speed;
        if (wm.plane1.collision(x, y, sizeX, sizeY)) {
            wm.pickedUp1 = true;
            System.out.println("Power Up!");
            this.reset();
        }
        if (wm.plane2.collision(x, y, sizeX, sizeY)) {
            wm.pickedUp2 = true;
            // You need to remove this one and increase score etc
            this.reset();
        }
        if (y >= wm.h) {
            y = -100;
            x = Math.abs(gen.nextInt() % (wm.w - 30));
        }
    }

    public void reset() {
        this.x = Math.abs(wm.generator.nextInt() % (600 - 30));
        this.y = -10;
    }

    public void draw(ImageObserver obs) {
        wm.g2.drawImage(img, x, y, obs);
    }
    
}
