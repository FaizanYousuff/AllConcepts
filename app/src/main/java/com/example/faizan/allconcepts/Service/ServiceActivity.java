package com.example.faizan.allconcepts.Service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.faizan.allconcepts.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startService,stopService,boundServiceBtn,getMessageBservice;
    private TextView boundServiceTextView;

    private boolean isBind = false;
    private MyBoundService boundService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        startService = findViewById(R.id.btn_start_service);
        stopService = findViewById(R.id.btn_stop_service);
        boundServiceBtn = findViewById(R.id.btn_start_bound_service);
        getMessageBservice = findViewById(R.id.btn_stop_bound_service);
        boundServiceTextView = findViewById(R.id.bound_service_tv);


        stopService.setOnClickListener(this);
        startService.setOnClickListener(this);
        boundServiceBtn.setOnClickListener(this);
        getMessageBservice.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_start_service:
                startService(new Intent(this,MyService.class));
                break;

            case R.id.btn_stop_service:
                stopService(new Intent(this,MyService.class));
                break;

            case R.id.btn_start_bound_service:
                if(isBind){
                    unBindService();
                } else {
                    bindService();
                }

                break;

            case R.id.btn_stop_bound_service:
                getMessageFromBoundService();
                break;

        }

    }

    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            isBind = true;
            MyBoundService.MyBinder binder = (MyBoundService.MyBinder)iBinder;
            boundService = binder.getService();
            boundServiceBtn.setText("Unbind Service");
            boundServiceTextView.setText("Service Binded ");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBind = false;
        }
    };

    public void bindService(){
        Intent intent  = new Intent(ServiceActivity.this,MyBoundService.class );
        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
    }

    public void  unBindService(){

        unbindService(serviceConnection);
    }

    public void getMessageFromBoundService(){

        if(isBind){
            boundServiceTextView.setText(boundService.getFirstMessage());
        } else {
            boundServiceTextView.setText("Service not binded");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindService();
    }
}
