/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wingman;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.awt.Graphics;

/**
 *
 * @author Kevin Soncuya
 */

    /* 
     * Island 
     * 
     */
   public class Island extends GameObject {

        Random gen;

        Island(Image img, int x, int y, int speed, Random gen) {
            
            super(img, x, y, speed);
            
            this.speed = speed;
            this.gen = gen;
        }

        public void update(int w, int h) {
            y += speed;
            if (y >= h) {
                y = -100;
                x = Math.abs(gen.nextInt() % (w - 30));
            }
        }

        public void draw(Graphics g, ImageObserver obs) {
            g.drawImage(img, x, y, obs);
        }
    }
