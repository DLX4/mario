package com.dlx4.mario.item;

import android.graphics.Bitmap;

import java.util.List;


/**
 * Created by Suramire on 2017/11/29.
 */

public class Mushroom extends ItemSprite {

    public Mushroom(Bitmap bitmap) {
        super(bitmap);
    }

    public Mushroom(int width, int height, List<Bitmap> bitmaps) {
        super(width, height, bitmaps);
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
                    move(4, 0);
                } else {
                    // 左
                    move(-4, 0);
                }
            }

        }
    }
}
