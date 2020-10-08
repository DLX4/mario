package com.dlx4.mario.enemy;

import android.graphics.Bitmap;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 敌人类-板栗
 */
public class Chestnut extends Enemy {

    public Chestnut(int width, int height, List<Bitmap> bitmaps) {
        super(width, height, bitmaps);
    }

    @Override
    public void logic() {
        super.logic();
        if (isDead()) {
            if (!isOverturn()) {
                setFrameSeqIndex(2);
            }
        } else if (isJumping()) {
            // 使敌人落地
            move(0, speedY++);
            setFrameSeqIndex(0);
        } else {
            if (delay1++ >= 7) {
                nextFrame();
                delay1 = 0;
                // 循环跑动贴图
                if (getFrameSeqIndex() >= 2) {
                    setFrameSeqIndex(0);

                }
            }
            if (isMirror()) {
                move(2, 0);
            } else {
                move(-2, 0);
            }

        }
    }
}
