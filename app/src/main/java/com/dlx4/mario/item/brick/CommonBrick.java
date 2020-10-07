package com.dlx4.mario.item.brick;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Suramire on 2017/12/9.
 */

public class CommonBrick extends Brick {
    private boolean canBroken;
    private Broken mBroken;

    public CommonBrick(int width, int height, List<Bitmap> bitmaps) {
        super(width, height, bitmaps);
    }

    public Broken getBroken() {
        return mBroken;
    }

    public void setBroken(Broken mBroken) {
        this.mBroken = mBroken;
    }

    public boolean isCanBroken() {
        return canBroken;
    }

    public void setCanBroken(boolean canBroken) {
        this.canBroken = canBroken;
    }

    @Override
    public void logic() {
        if (isCanBroken()) {
            if (isJumping()) {
                move(0, speedY++);
                if (speedY > 0) {
                    setJumping(false);
                    setVisible(false);
                    Broken broken = getBroken();
                    if (broken != null) {
                        broken.setVisible(true);
                        broken.setPosition(getX() - 73, getY() - 78);
                    }
                }
            }
        } else {
            if (isJumping()) {
                move(0, speedY++);
                if (speedY > 4) {
                    setJumping(false);
                }
            }
        }
    }
}
