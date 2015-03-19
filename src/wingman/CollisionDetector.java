/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wingman;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Kevin Soncuya
 */
public class CollisionDetector {
    
    //empty constructor
    CollisionDetector() {
    }

    public static boolean planeAndEnemy(MyPlane p, Enemy enemy) {
        if (p.collision(enemy.x, enemy.y, enemy.width, enemy.height)) {
            return true;
        }
        return false;
    }

    public static boolean bulletAndEnemy(ArrayList<Bullet> bullets, Enemy enemy) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet aBullet = bullets.get(i);
            if (aBullet.collision(enemy.x, enemy.y, enemy.width, enemy.height)) {
                bullets.remove(aBullet);
                return true;
            }
        }
        return false;
    }
    
    public static boolean enemyBulletAndPlane(ArrayList<Bullet> enemyBullets, MyPlane p) {
        for (int i = 0; i < enemyBullets.size(); i++) {
            Bullet aBullet = enemyBullets.get(i);
            if (aBullet.collision(p.x, p.y, p.width, p.height)) {
                enemyBullets.remove(aBullet);
                return true;
            }
        }
        return false;
    }

}
