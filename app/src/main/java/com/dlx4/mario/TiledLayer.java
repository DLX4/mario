package com.dlx4.mario;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Suramire on 2017/10/18.
 */
@Setter
@Getter
public class TiledLayer {
    private int x;
    private int y;
    private int width;
    private int height;
    private Bitmap bitmap;
    private Rect dest;
    private Rect src;
    private int rows;
    private int cols;
    private int[][] tiledCell;
    private int[] tiledX;
    private int[] tiledY;

    public TiledLayer(Bitmap bitmap, int cols, int rows, int width, int height) {
        super();
        setBitmap(bitmap);
        setHeight(height);
        setWidth(width);
        setRows(rows);
        setCols(cols);
        tiledCell = new int[rows][cols];
        int w = bitmap.getWidth() / width;
        int h = bitmap.getHeight() / height;
        tiledX = new int[w * h + 1];
        tiledY = new int[w * h + 1];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                tiledX[i * w + j + 1] = j * width;
                tiledY[i * w + j + 1] = i * height;
            }
        }
        src = new Rect();
        dest = new Rect();

    }

    public int getTiledCell(int cols, int row) {
        return tiledCell[row][cols];
    }

    public void setTiledCell(int[][] tiledCell) {
        this.tiledCell = tiledCell;
    }

    public void move(float x, float y) {
        this.y += y;
        this.x += x;
        outOfBounds();
    }

    public void draw(Canvas canvas) {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                int tiledIndex = getTiledCell(j, i);
                if (tiledIndex == 0) {
                    continue;
                }
                int x = tiledX[tiledIndex];
                int y = tiledY[tiledIndex];
                int ix = getX() + j * getWidth();
                int iy = getY() + i * getHeight();
                src.set(x, y, x + getWidth(), y + getHeight());
                dest.set(ix, iy,
                        ix + getWidth(), iy + getHeight());
                canvas.drawBitmap(bitmap, src, dest, null);
            }
        }
    }

    /**
     * 地图的边界处理
     */
    private void outOfBounds() {
        if (getX() > 0) {
            setX(0);
        } else if (getX() < 800 - getCols() * getWidth()) {
            setX(800 - getCols() * getWidth());
        }
        if (getY() > 0) {
            setY(0);
        } else if (getY() < 480 - getRows() * getWidth()) {
            setY(480 - getRows() * getWidth());
        }
    }

}
