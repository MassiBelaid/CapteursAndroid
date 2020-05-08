package com.example.tp3lescapteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;



public class Activite4 extends AppCompatActivity implements SensorEventListener {

    Sensor accel;
    private SensorManager mSensorManager;
    boolean m;
    private boolean allume = false;

    float force = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite4);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accel = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        m = mSensorManager.registerListener(Activite4.this,accel,SensorManager.SENSOR_DELAY_NORMAL);

        if(!m){
            mSensorManager.unregisterListener(Activite4.this,accel);
            Toast.makeText(this,"can't Monitoring acceleration linear.",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch(event.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                try {
                    onAccelerometerChanger(event);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
                break;
        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void onAccelerometerChanger (SensorEvent sensor) throws CameraAccessException {

        float x, y, z;
        x = sensor.values[0];
        y = sensor.values[1];
        z = sensor.values[2];



        if(Math.abs(x) +  Math.abs(y) + Math.abs(z) > 40 ){

            CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

            if(allume){

            try {
                String cameraId = cameraManager.getCameraIdList()[0];
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cameraManager.setTorchMode(cameraId, true);
                }
            } catch (CameraAccessException e) {
            }}else {

                try {
                    String cameraId = cameraManager.getCameraIdList()[0];
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        cameraManager.setTorchMode(cameraId, false);
                    }
                } catch (CameraAccessException e) {
                }

            }

            allume = !allume;
        }

    }



    @Override
    protected void onPause() {
        super.onPause();
        if(m){
            mSensorManager.unregisterListener(Activite4.this,accel);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(m){
            m = mSensorManager.registerListener(Activite4.this,accel,SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this,"Reprise.",Toast.LENGTH_SHORT).show();
        }
    }
}
