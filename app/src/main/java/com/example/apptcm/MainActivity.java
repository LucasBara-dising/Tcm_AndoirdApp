package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //giroscopio
    SensorManager sensorManager;
    Sensor sensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instancianod giroscopio
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        EditText TxtEdtPesquisa = (EditText) findViewById(R.id.TxtEdtPesquisa);
        TxtEdtPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaListaServ();
            }
        });

        ImageView ImgBtnConta = (ImageView) findViewById(R.id.ImgBtnConta);
        ImgBtnConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaConta();
            }
        });

        Button btnServ = (Button) findViewById(R.id.btnServ);
        btnServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaListaServ();
            }
        });

        Button btnFunc = (Button) findViewById(R.id.btnFunc);
        btnFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaConta();
            }
        });
    }

    public void TelaListaServ(){
        Intent ListServ = new Intent(getApplicationContext(), ListaServ.class);
        startActivity(ListServ);
    }

    public void TelaConta(){
        Intent Conta = new Intent(getApplicationContext(), ContaFunc.class);
        startActivity(Conta);
        finish();
    }

    public void AddServ(){
        Intent AddServ = new Intent(getApplicationContext(), AddServ.class);
        startActivity(AddServ);
        finish();
    }

    public void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(gyroListener);
    }

    public SensorEventListener gyroListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) {
        }

        public void onSensorChanged(SensorEvent event) {
            float y = event.values[1];

            if(y>4){
                AddServ();
            }
        }
    };

}