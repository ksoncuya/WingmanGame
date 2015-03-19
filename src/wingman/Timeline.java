/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wingman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Kevin Soncuya
 */

public class Timeline {

//    Bullet b1;
//    Enemy enemy;
//    PowerUp powUp;
    ImageObserver obs;
    Image enemy1, enemy2, enemy3, boss, bottomEnemy, powerup, enemyBullet;
    Wingman wm;
    GameEvents ge;
    Random gen;
    int time = 0;

//    ArrayList<Enemy> enemies = new ArrayList();
//    ArrayList<PowerUp> powerups = new ArrayList();
    Timeline(Wingman wm, Image enemy1, Image enemy2, Image enemy3,
            Image bottomEnemy, Image boss, Random gen, Image powerup, Image enemyBullet) {
        this.gen = gen;
        this.wm = wm;
        this.enemy1 = enemy1;
        this.enemy2 = enemy2;
        this.enemy3 = enemy3;
        this.bottomEnemy = bottomEnemy;
        this.boss = boss;
        this.powerup = powerup;
        this.enemyBullet = enemyBullet;

    }

    public void update() {
        time++;

        for (int i = 0; i < wm.enemies.size(); i++) {
            wm.enemy = wm.enemies.get(i);
            if (wm.enemy.getY() > wm.h) {
                wm.enemies.remove(wm.enemy);
            } else if (CollisionDetector.planeAndEnemy(wm.plane1, wm.enemy)) {
                wm.enemies.remove(i);
            } else if (CollisionDetector.planeAndEnemy(wm.plane2, wm.enemy)) {
                wm.enemies.remove(i);
            } else if (CollisionDetector.bulletAndEnemy(wm.bullets1, wm.enemy)
                    || CollisionDetector.bulletAndEnemy(wm.bullets2, wm.enemy)) {
                wm.exp = new Explosion(wm.explosion1_1, wm.enemy.x, wm.enemy.y);
                wm.exp.draw(wm.g2, wm.observer);
                wm.enemies.remove(i);
                System.out.println("test");
            } else if (CollisionDetector.enemyBulletAndPlane(wm.enemyBullets, wm.plane1)) {
                wm.hp1++;
            } else if (CollisionDetector.enemyBulletAndPlane(wm.enemyBullets, wm.plane2)) {
                wm.hp2++;
            }
            wm.enemy.update();
        }

        for (int i = 0; i < wm.sideEnemies.size(); i++) {
            wm.se = wm.sideEnemies.get(i);
            if (wm.se.getY() > wm.h) {
                wm.sideEnemies.remove(wm.se);
            }
            if (CollisionDetector.bulletAndEnemy(wm.bullets1, wm.se)
                    || CollisionDetector.bulletAndEnemy(wm.bullets2, wm.se)) {
                wm.exp = new Explosion(wm.explosion1_1, wm.enemy.x, wm.enemy.y);
                wm.exp.draw(wm.g2, wm.observer);
                wm.sideEnemies.remove(i);
            }
            wm.se.update();
        }

//        for(int i = 0; i < powerups.size(); i++) {
//            powUp = powerups.get(i);
//            powUp.update();
//       }
        if (time < 500) {
            if (time % 60 == 0) {
                wm.enemies.add(new Enemy(enemy1, Math.abs(gen.nextInt() % (600 - 30)), 0, 2, gen, wm));
                wm.enemies.add(new Enemy(enemy2, Math.abs(gen.nextInt() % (600 - 30)), 0, 2, gen, wm));

                //powerups.add(new PowerUp(powerup, 100, 100, wm.speed, wm.generator));
            }

        } else if (time > 500 && time < 1000) {
            if (time % 50 == 0) {
                wm.enemies.add(new Enemy(enemy1, Math.abs(gen.nextInt() % (600 - 30)), 0, 2, gen, wm));
                wm.enemies.add(new BottomEnemies(bottomEnemy, Math.abs(gen.nextInt() % (600 - 30)), 420, 2, gen, wm));
            }
        } else if (time > 1000 && time < 2000) {
            if (time % 60 == 0) {
                wm.sideEnemies.add(new SideEnemies(enemy3, 600, Math.abs(gen.nextInt() % (200)), 2, gen, wm));
                wm.enemies.add(new Enemy(enemy2, Math.abs(gen.nextInt() % (600 - 30)), -50, 2, gen, wm));
            }
            if (time % 15 == 0) {
                wm.enemyBullets.add(new EnemyBullets(wm.enemyBullet, wm.enemy.getX(), wm.enemy.getY(), 5, wm));
            }

        } else if (time > 2000 && time < 3000) {
            if (time % 50 == 0) {
                wm.enemies.add(new SideEnemies(enemy3, 0, 30, 2, gen, wm));
                wm.enemies.add(new Enemy(enemy1, Math.abs(gen.nextInt() % (600 - 30)), 0, 2, gen, wm));
                wm.enemies.add(new Enemy(enemy2, Math.abs(gen.nextInt() % (600 - 30)), 0, 2, gen, wm));
            }
        }
        else if (time == 3000) {
            //add boss here
            wm.bossplane = new Boss(boss, 200, 0, 2, gen, wm);
        }
        else if(time >= 3000) {
            wm.bossplane.update();
            if(time % 70 == 0) {
                wm.enemyBullets.add(new EnemyBullets(wm.bigBullet, wm.bossplane.getX()+57, wm.bossplane.getY()+85, 5, wm));
                wm.enemyBullets.add(new EnemyBullets(wm.bigBullet, wm.bossplane.getX()+200, wm.bossplane.getY()+85, 5, wm));
            }
        }
    }

    public void draw(Graphics g) {
        g.drawString("Time: " + time, 285, 15);
        for (int i = 0; i < wm.enemies.size(); i++) {
            wm.enemy = wm.enemies.get(i);
            wm.enemy.draw(g, obs);
        }
        for (int i = 0; i < wm.sideEnemies.size(); i++) {
            wm.se = wm.sideEnemies.get(i);
            wm.se.draw(g, obs);
        }
        if(time >= 3000) {
            wm.bossplane.draw(g, obs);
        }
//        for(int i = 0; i < powerups.size(); i++) {
//            powUp = powerups.get(i);
//            powUp.draw(g, obs);
//        }
    }

}
