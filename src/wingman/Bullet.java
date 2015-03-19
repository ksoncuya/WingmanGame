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

/**
 *
 * @author Kevin Soncuya
 */

   public class Bullet extends Weapon {

        KeyControl key;
        int sizeX, sizeY, move = 0;
        //Enemy e1, e2, e3;
        Wingman wm;
        boolean show;

        Bullet(Image img, int x, int y, int speed, Wingman wm) {
            super(img, x, y, speed);

            this.wm = wm;
            this.show = true;
            sizeX = img.getWidth(null);
            sizeY = img.getHeight(null);
            width = img.getWidth(null);
            height = img.getHeight(null);

            //addKeyListener(key);
        }

        public void draw(Graphics g, ImageObserver obs) {
            if(show) {
                g.drawImage(img, this.x, this.y, obs);
            }
        }
        
         public void update() {  
            if(wm.pickedUp1) {
                y -= speed +2;
            } 
            else if(wm.pickedUp2) {
                y -= speed +2;
            }
            else {
                y -= speed;  
            }
         }    

        public void reset() {
            this.x = Math.abs(wm.generator.nextInt() % (600 - 30));
            this.y = -10;
        }

        public boolean collision(int x, int y, int w, int h) {
            bbox = new Rectangle(this.x, this.y, this.width, this.height);
            Rectangle otherBBox = new Rectangle(x, y, w, h);
            if (this.bbox.intersects(otherBBox)) {
                return true;
            }
            return false;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

    }
