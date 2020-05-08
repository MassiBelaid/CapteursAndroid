package com.example.tp3lescapteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Accelerometre extends AppCompatActivity implements SensorEventListener {

    Sensor accel;
    private SensorManager mSensorManager;
    boolean m;
    View v;
    Button bActivity3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometre);
        v = this.getWindow().getDecorView();
        bActivity3 = (Button)findViewById(R.id.bActivity3);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accel = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

         m = mSensorManager.registerListener(Accelerometre.this,accel,SensorManager.SENSOR_DELAY_NORMAL);

        if(!m){
            mSensorManager.unregisterListener(Accelerometre.this,accel);
            Toast.makeText(this,"can't Monitoring acceleration linear.",Toast.LENGTH_LONG).show();
        }

        bActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Accelerometre.this,activity3.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch(event.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
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

        if(Math.abs(x) < 1 || Math.abs(y) < 1 || Math.abs(z) < 1){
            v.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        }else {
            if(Math.abs(x) < 4 || Math.abs(y) < 4 || Math.abs(z) < 4){
                v.setBackgroundColor(getResources().getColor(R.color.colorBlack));
            }else{
                v.setBackgroundColor(getResources().getColor(R.color.colorRed));
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(m){
            mSensorManager.unregisterListener(Accelerometre.this,accel);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(m){
            m = mSensorManager.registerListener(Accelerometre.this,accel,SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this,"Reprise.",Toast.LENGTH_SHORT).show();
        }
    }
}
