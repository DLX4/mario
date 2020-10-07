package com.dlx4.mario.item.brick;

import android.graphics.Bitmap;

import com.dlx4.mario.Sprite;

import java.util.List;

/**
 * Created by Suramire on 2017/12/24.
 */

public class Broken extends Sprite {
    public Broken(int width, int height, List<Bitmap> bitmaps) {
        super(width, height, bitmaps);
    }

    @Override
    public void logic() {
        if (isVisible()) {
            nextFrame();
            move(0, -3);
            if (getFrameSeqIndex() == 0) {
                setVisible(false);
            }
        }
    }
}
