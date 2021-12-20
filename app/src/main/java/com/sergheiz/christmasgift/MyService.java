package com.sergheiz.christmasgift;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class MyService extends Service {
    private  MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(player == null) {
            player = MediaPlayer.create(this, R.raw.christmas_no_copyright_music);
        }

        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopPlayer();
            }
        });
        return START_STICKY;
    }

    public void stopPlayer(){
        if (player != null){
            player.release();
            player = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPlayer();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        stopPlayer();
        this.stopSelf();
    }
}

