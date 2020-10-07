package com.dlx4.mario.enemy;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.dlx4.mario.Sprite;
import com.dlx4.mario.audio.SmSoundPool;
import com.dlx4.mario.item.EnemyBullet;

import java.util.ArrayList;
import java.util.List;

/**
 * 敌人类-大炮
 */

public class Cannon extends Enemy {

    private List<Sprite> bullets;
    private long delay2;
    private SmSoundPool soundPool;

    public Cannon(int width, int height, List<Bitmap> bitmaps, SmSoundPool soundPool) {
        super(width, height, bitmaps);
        this.soundPool = soundPool;
        bullets = new ArrayList<>();
    }

    public List<Sprite> getBullets() {
        return bullets;
    }

    public void setBullets(List<Sprite> bullets) {
        this.bullets = bullets;
    }

    @Override
    public void logic() {
        if (isJumping()) {
            move(0, speedY++);
        }
        if (delay2++ > 90) {
            fire();
            delay2 = 0;
        }
        if (bullets != null) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).logic();
            }
        }
    }

    public void fire() {
        if (bullets != null) {
            for (int i = 0; i < bullets.size(); i++) {
                EnemyBullet enemyBullet = (EnemyBullet) bullets.get(i);
                if (!enemyBullet.isVisible()) {
                    soundPool.play(soundPool.getCannonSound());
                    enemyBullet.setVisible(true);
                    enemyBullet.setDead(false);
                    if (enemyBullet.isMirror()) {
                        enemyBullet.setPosition(getX() + getWidth() - 5, getY() + 6);
                    } else {
                        enemyBullet.setPosition(getX() - 15, getY() + 6);
                    }

                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (bullets != null) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).draw(canvas);
            }
        }
    }
}
