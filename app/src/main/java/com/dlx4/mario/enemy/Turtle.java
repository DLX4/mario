package com.dlx4.mario.enemy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.SystemClock;

import java.util.List;

/**
 * 敌人类-乌龟
 */

public class Turtle extends Enemy {

    private final Thread zeroDamagThread;
    private boolean canFly;
    // 标志是否处于免伤状态
    private boolean isZeroDamage;
    // 免伤时间
    private int zeroDamageTime;
    // 标志进程是否开始
    private boolean isZeroDamageThreadStarted;
    private long delay2;

    public Turtle(int width, int height, List<Bitmap> bitmaps) {
        super(width, height, bitmaps);
        delay1 = 7;
        zeroDamagThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (zeroDamageTime >= 1) {
                    SystemClock.sleep(300);
                    zeroDamageTime--;
                }

            }
        });
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    public boolean isZeroDamage() {
        return isZeroDamage;
    }

    public void setZeroDamage(boolean zeroDamage) {
        if (zeroDamage) {
            zeroDamageTime = 1;
        }
        isZeroDamage = zeroDamage;
    }

    @Override
    public void logic() {
        if (!isDead()) {
            // 逻辑部分
            if (isZeroDamage) {
                if (!isZeroDamageThreadStarted) {
                    new Thread(zeroDamagThread).start();
                    isZeroDamageThreadStarted = true;
                }
                if (zeroDamageTime <= 0) {
                    isZeroDamage = false;
                    isZeroDamageThreadStarted = false;
                }
            }
            if (delay++ > 1) {
                if (isJumping()) {
                    move(0, speedY++);
                    move(0, speedY++);
                }
                if (!isMirror()) {
                    move(-2, 0);
                } else {
                    move(2, 0);
                }
                delay = 0;


            }
            // 贴图部分
            if (!isCanFly()) {
                if (delay1++ >= 7) {
                    nextFrame();
                    delay1 = 0;
                    // 循环跑动贴图
                    if (getFrameSeqIndex() > 3) {
                        setFrameSeqIndex(2);

                    }
                }

            } else {
                if (delay1++ >= 7) {
                    nextFrame();
                    delay1 = 0;
                    // 循环跑动贴图
                    if (getFrameSeqIndex() > 1) {
                        setFrameSeqIndex(0);
                    }
                }
            }
        } else {
            if (!isOverturn()) {
                if (isRunning()) {
                    if (isMirror()) {
                        move(10, 0);
                    } else {
                        move(-10, 0);
                    }
                }

            } else {
                // 被子弹打到 反转下落
                move(0, speedY++);
                if (isMirror()) {
                    move(1, 0);
                } else {
                    move(-1, 0);
                }
            }
            setFrameSeqIndex(4);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
