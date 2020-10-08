package com.dlx4.mario.audio;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;

import lombok.Getter;

@Getter
public class SmSoundPool {

    private final Context context;
    private final SoundPool soundPool;
    private final int hitBrickSound;
    private final int coinSound;
    private final int hurryUpSound;
    private final int hitEnemySound;
    private final int jumpSound;
    private final int cannotBreakSound;
    private final int hurtSound;
    private final int cannonSound;
    private final int transferSound;
    private final int brokenSound;
    private final int itemSound;

    public SmSoundPool(Context mContext) {
        super();
        this.context = mContext;
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        cannotBreakSound = getSoundId("sounds/cannotbreak.mp3");
        itemSound = getSoundId("sounds/mushroom.mp3");
        hitBrickSound = getSoundId("sounds/duang.mp3");
        coinSound = getSoundId("sounds/coin.mp3");
        hurryUpSound = getSoundId("sounds/hurryup.mp3");
        jumpSound = getSoundId("sounds/jump.mp3");
        hitEnemySound = getSoundId("sounds/hitenemy.mp3");
        hurtSound = getSoundId("sounds/hurt.mp3");
        cannonSound = getSoundId("sounds/cannon.mp3");
        transferSound = getSoundId("sounds/transfer.mp3");
        brokenSound = getSoundId("sounds/broken.mp3");
    }

    public void play(int soundID) {
        soundPool.play(soundID, 1, 1, 1, 1, 1);
    }

    public int getSoundId(String fileName) {
        int soundId = 0;
        try {
            soundId = soundPool.load(context.getAssets().openFd(fileName), 1);
        } catch (IOException e) {
            Log.d("MySoundPool", e.getMessage());
        }
        return soundId;
    }
}
