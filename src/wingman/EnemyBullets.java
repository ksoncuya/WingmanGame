/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wingman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 *
 * @author Kevin Soncuya
 */

public class EnemyBullets extends Bullet {
    
    EnemyBullets(Image img, int x, int y, int speed, Wingman wm) {
        super(img, x, y, speed, wm);
    }
    
    public void update() {
        y += speed;
        
    }

    public void draw(Graphics g, ImageObserver obs) {
            g.drawImage(img, this.x, this.y, obs);
    }
    
}
