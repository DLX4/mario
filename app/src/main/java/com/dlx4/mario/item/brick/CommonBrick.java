package com.dlx4.mario.item.brick;

import android.graphics.Bitmap;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Suramire on 2017/12/9.
 */
@Setter
@Getter
public class CommonBrick extends Brick {
    private boolean canBroken;
    private Broken broken;

    public CommonBrick(int width, int height, List<Bitmap> bitmaps) {
        super(width, height, bitmaps);
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
