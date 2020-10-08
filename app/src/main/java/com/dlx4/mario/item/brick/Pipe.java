package com.dlx4.mario.item.brick;


import android.graphics.Bitmap;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Suramire on 2017/12/25.
 */
@Getter
@Setter
public class Pipe extends Brick {
    private boolean isTransfer;

    public Pipe(int width, int height, List<Bitmap> bitmaps) {
        super(width, height, bitmaps);
    }

    @Override
    public void logic() {
    }
}
