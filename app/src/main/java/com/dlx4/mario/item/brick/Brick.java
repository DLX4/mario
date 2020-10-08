package com.dlx4.mario.item.brick;

import android.graphics.Bitmap;

import com.dlx4.mario.enums.ItemType;
import com.dlx4.mario.item.Coin;
import com.dlx4.mario.item.Flower;
import com.dlx4.mario.item.ItemSprite;
import com.dlx4.mario.item.Mushroom;
import com.dlx4.mario.item.Star;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 砖块类
 */
@Setter
@Getter
public class Brick extends ItemSprite {
    // 表示道具类型 枚举
    protected ItemType itemType;
    protected ItemSprite itemSprite;
    // 标志道具是否已显示
    private boolean hasItem;
    private int delay;

    public Brick(int width, int height, List<Bitmap> bitmaps) {
        super(width, height, bitmaps);
    }

    public Brick(Bitmap bitmap) {
        super(bitmap);
    }


    @Override
    public void logic() {
        if (isJumping()) {
            if (hasItem) {
                itemSprite.setVisible(true);
                itemSprite.setPosition(getX(), getY() - getHeight());
                hasItem = false;
            }
            move(0, speedY++);
            if (speedY > 4) {
                setJumping(false);
            }
        }
        if (!hasItem) {
            setFrameSeqIndex(4);
        } else {
            if (delay++ > 10) {
                nextFrame();
                if (getFrameSeqIndex() >= 4) {
                    setFrameSeqIndex(0);
                }
                delay = 0;

            }

        }
    }

    /**
     * 为砖块添加道具
     *
     * @param e      是否添加标志位
     * @param bitmap 道具图片（单帧方式）
     */
    public void createItem(boolean e, Bitmap bitmap, ItemType type) {
        setItemType(type);
        if (e) {
            switch (type) {
                case Mushroom: {
                    // 蘑菇默认往右移动
                    itemSprite = new Mushroom(bitmap);
                    itemSprite.setMirror(true);
                }
                break;
                case Coin: {

                }
                break;

            }

            hasItem = true;
        }
    }

    /**
     * 为砖块添加道具
     *
     * @param e       是否添加标志位
     * @param bitmaps 道具图片（多帧方式）
     */
    public void createItem(boolean e, List<Bitmap> bitmaps, ItemType type) {
        setItemType(type);
        if (e) {
            switch (type) {

                case Coin: {
                    itemSprite = new Coin(40, 40, bitmaps);
                    itemSprite.setRunning(false);
                }
                break;
                case Star: {
                    itemSprite = new Star(28, 30, bitmaps);
                    itemSprite.setRunning(true);
                    itemSprite.setMirror(true);
                }
                break;

                case Flower: {
                    // 花默认不移动
                    itemSprite = new Flower(32, 32, bitmaps);
                    itemSprite.setPosition(getX() + 4, getY() - 32);
                    itemSprite.setRunning(false);
                }
                break;

            }

            hasItem = true;
        }
    }
}
