package com.dlx4.mario.audio;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class SmMusic {

    private final Context context;
    private final MediaPlayer mediaPlayer;
    private String fileName = "";

    public SmMusic(Context mContext) {
        super();
        this.context = mContext;
        mediaPlayer = new MediaPlayer();
    }

    public void play(String fileName, boolean looping) {
        if (this.fileName.equals(fileName)) {
            return;
        } else {
            try {
                mediaPlayer.reset();
                this.fileName = fileName;
                AssetFileDescriptor fd = context.getAssets().openFd(fileName);
                mediaPlayer.setDataSource(fd.getFileDescriptor(),
                        fd.getStartOffset(),
                        fd.getLength());
                mediaPlayer.setLooping(looping);
                mediaPlayer.prepare();
                mediaPlayer.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    public void pause() {
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        } catch (IllegalStateException e) {
        }
    }


}
