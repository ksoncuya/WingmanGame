/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wingman;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Kevin Soncuya
 */
public class KeyControl extends KeyAdapter {

//        public int x, y, w, h;
//        int speed;
    Wingman wm;
    GameEvents ge;

//        KeyControl(int x, int y, int speed) {
    KeyControl(Wingman wm, GameEvents ge) {
//            this.x = x;
//            this.y = y;
//            this.speed = speed;
        this.wm = wm;
        this.ge = ge;
    }

    public void keyPressed(KeyEvent e) {
        wm.ge.setValue(e);

//        int key = e.getKeyCode();

//        switch (e.getKeyCode()) {
//
//            case KeyEvent.VK_LEFT:
//                if (wm.plane1.getX() > 0) {
//                    wm.plane1.move(-12, 0);
//                }
//                break;
//            case KeyEvent.VK_RIGHT:
//                if (wm.plane1.getX() < (wm.w - 70)) {
//                    wm.plane1.move(+12, 0);
//                }
//                break;
//
//            case KeyEvent.VK_UP:
//                if (wm.plane1.getY() > 0) {
//                    wm.plane1.move(0, -12);
//                }
//                break;
//
//            case KeyEvent.VK_DOWN:
//                if (wm.plane1.getY() < (wm.h - 90)) {
//                    wm.plane1.move(0, +12);
//                }
//                break;
//
//            case KeyEvent.VK_A:
//                if (wm.plane2.getX() > 0) {
//                    wm.plane2.move(-12, 0);
//                }
//                break;
//            case KeyEvent.VK_D:
//                if (wm.plane2.getX() < (wm.w - 70)) {
//                    wm.plane2.move(+12, 0);
//                }
//                break;
//
//            case KeyEvent.VK_W:
//                if (wm.plane2.getY() > 0) {
//                    wm.plane2.move(0, -12);
//                }
//                break;
//
//            case KeyEvent.VK_S:
//                if (wm.plane2.getY() < (wm.h - 90)) {
//                    wm.plane2.move(0, +12);
//                }
//                break;
//
//            case KeyEvent.VK_M:
//                wm.bullets1.add(new Bullet(wm.bullet, wm.plane1.getX() + 17, wm.plane1.getY(), 5, wm));
//                if (wm.pickedUp1) {
//                    wm.bulletsL1.add(new BulletLeft(wm.bulletLeft, wm.plane1.getX() - 20, wm.plane1.getY(), 5, wm));
//                    wm.bulletsR1.add(new BulletRight(wm.bulletRight, wm.plane1.getX() + 30, wm.plane1.getY(), 5, wm));
//                }
//                break;
//
//            case KeyEvent.VK_V:
//                wm.bullets1.add(new Bullet(wm.bullet, wm.plane2.getX() + 17, wm.plane2.getY(), 5, wm));
//                if (wm.pickedUp2) {
//                    wm.bulletsL1.add(new BulletLeft(wm.bulletLeft, wm.plane2.getX() - 20, wm.plane2.getY(), 5, wm));
//                    wm.bulletsR1.add(new BulletRight(wm.bulletRight, wm.plane2.getX() + 30, wm.plane2.getY(), 5, wm));
//                }
//                break;
//
//            case KeyEvent.VK_ESCAPE:
//                System.exit(0);
//                break;
//
//            default:
//                if (e.getKeyChar() == ' ') {
//                    System.out.println("FIRE!!");
//                }
//        }
    }

}
