package com.example.tp3lescapteurs;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private SensorManager mSensorManager ;

    ListView lSesor;
    ArrayList<String> capteurs = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    List<Sensor> sensorList;
    Button bExercice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lSesor = (ListView) findViewById(R.id.lSensor);
        bExercice = (Button) findViewById(R.id.bExercice3);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) == null){
            Toast.makeText(this,"can't creating compass.",Toast.LENGTH_LONG).show();
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null){
            Toast.makeText(this,"can't determining phone position during call.",Toast.LENGTH_LONG).show();
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) == null){
            Toast.makeText(this,"can't monitoring air presure.",Toast.LENGTH_LONG).show();
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) == null){
            Toast.makeText(this,"can't Monitoring dewpoint absolute relative humidity.",Toast.LENGTH_LONG).show();
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR) == null){
            Toast.makeText(this,"can't detect motion and rotation.",Toast.LENGTH_LONG).show();
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) == null){
            Toast.makeText(this,"can't Monitoring the temperature.",Toast.LENGTH_LONG).show();
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) == null){
            Toast.makeText(this,"don't have gyroscope.",Toast.LENGTH_LONG).show();
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) == null){
            Toast.makeText(this,"can't Monitoring the light.",Toast.LENGTH_LONG).show();
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) == null){
            Toast.makeText(this,"can't Monitoring acceleration linear.",Toast.LENGTH_LONG).show();
        }






        for(int i=0 ; i<sensorList.size() ;i++){
            capteurs.add(i+". "+sensorList.get(i).getName());
        }


        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,capteurs);
        lSesor.setAdapter(adapter);

        bExercice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null){
                    Toast.makeText(MainActivity.this,"can't Monitoring acceleration linear.",Toast.LENGTH_LONG).show();
                }else{
                Intent i = new Intent(MainActivity.this, Accelerometre.class);
                startActivity(i);
            }}
        });

    }
}
