package com.dlx4.mario.item;

import android.graphics.Bitmap;

import com.dlx4.mario.Sprite;

import java.util.List;

/**
 * Created by Suramire on 2017/11/30.
 * 用于道具显示与逻辑处理
 * 边界处理
 */

public class ItemSprite extends Sprite {

    public ItemSprite(Bitmap bitmap) {
        super(bitmap);
        setRunning(true);
    }

    public ItemSprite(int width, int height, List<Bitmap> bitmaps) {
        super(width, height, bitmaps);
        // 道具默认是运动状态
        setRunning(true);
    }

    @Override
    public void logic() {
        if (!isDead() && isVisible()) {
            if (isJumping()) {
                move(0, speedY++);
            }
            if (isRunning()) {
                if (isMirror()) {
                    // 右
                    move(2, 0);
                } else {
                    // 左
                    move(-2, 0);
                }
            }

        }
    }

    @Override
    protected void outOfBounds() {
        // 在超出左边界 以及掉入坑里的是否表示为不可见
        if (getX() < -getWidth() || getY() > 400) {
            setVisible(false);
        }
    }
}
