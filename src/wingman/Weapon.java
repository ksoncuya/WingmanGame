/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wingman;

import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Kevin
 */
public class Weapon extends GameObject {
    int width;
    int height = 0;
    Rectangle bbox;

    public Weapon(Image img, int x, int y, int speed) {
        super(img, x, y, speed);
    }
    
}
