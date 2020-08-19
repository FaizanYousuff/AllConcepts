package com.example.faizan.allconcepts.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

public class MyService extends Service {
    private MediaPlayer player;
    private boolean isServiceRunning;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       player =  MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.start();
        isServiceRunning = true;

        //the system will not try to re-create your service after it is killed
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        isServiceRunning = false;
    }

    /**
     * Systerm will call this method wher service is started using bindService method
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public boolean isServiceRunning() {
        return isServiceRunning;
    }

    /**
     * this will be called whne application remooved from recent apps list
     * @param rootIntent
     */
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        stopSelf();
    }
}
