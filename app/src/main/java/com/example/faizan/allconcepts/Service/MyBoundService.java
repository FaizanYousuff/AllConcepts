package com.example.faizan.allconcepts.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyBoundService extends Service {

    private IBinder binder = new MyBinder();
    /**
     * Systerm will call this method wher service is started using bindService method
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MyBinder extends Binder{

        MyBoundService getService(){
           return MyBoundService.this;
        }
    }

    public  String getFirstMessage(){
        return "Message received from BOUND service";
    }


}
