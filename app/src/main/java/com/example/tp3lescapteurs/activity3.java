package com.example.tp3lescapteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class activity3 extends AppCompatActivity implements SensorEventListener {

    TextView textInfo;
    Sensor accel;
    private SensorManager mSensorManager;
    boolean m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3);

        textInfo = (TextView) findViewById(R.id.textInfo);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accel = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        m = mSensorManager.registerListener(activity3.this,accel,SensorManager.SENSOR_DELAY_NORMAL);

        if(!m){
            mSensorManager.unregisterListener(activity3.this,accel);
            Toast.makeText(this,"can't Monitoring acceleration linear.",Toast.LENGTH_LONG).show();
        }

        (findViewById(R.id.buttonE4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( activity3.this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
                Intent i = new Intent(activity3.this, Activite4.class);
                startActivity(i);}
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch(event.sensor.getType()){
            case Sensor.TYPE_LINEAR_ACCELERATION:
                onAccelerometerChanger(event);
                break;
        }
        }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void onAccelerometerChanger (SensorEvent sensor){

        float x,y,z;
        x = sensor.values[0];
        y = sensor.values[1];
        z = sensor.values[2];

        if(z > 5 ){
            textInfo.setText("BAS");
        }else{
            if(z < -5 ){
            textInfo.setText("HAUT");}
        }

        if(x > 5 ){
            textInfo.setText("GAUCHE");
        }else{
            if(x < -5 ){
            textInfo.setText("DROITE");}
        }


    }



    @Override
    protected void onPause() {
        super.onPause();
        if(m){
            mSensorManager.unregisterListener(activity3.this,accel);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(m){
            m = mSensorManager.registerListener(activity3.this,accel,SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this,"Reprise.",Toast.LENGTH_SHORT).show();
        }
    }
}
