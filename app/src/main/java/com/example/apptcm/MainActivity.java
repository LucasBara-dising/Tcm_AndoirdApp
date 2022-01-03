package com.example.apptcm;

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
import android.widget.EditText;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    //giroscopio
    SensorManager sensorManager;
    Sensor sensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recebe os dados da outra tela
        Intent intent = getIntent();
        int codFunc = intent.getIntExtra("codFunc",0);

        //instancianod giroscopio
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        EditText TxtEdtPesquisa = (EditText) findViewById(R.id.TxtEdtPesquisa);
        TxtEdtPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ListServ = new Intent(getApplicationContext(), ListaServ.class);
                ListServ.putExtra("codFunc",codFunc);
                startActivity(ListServ);
            }
        });

        ImageView ImgBtnConta = (ImageView) findViewById(R.id.ImgBtnConta);
        ImgBtnConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Conta = new Intent(getApplicationContext(), ContaFunc.class);
                Conta.putExtra("codFunc",codFunc);
                startActivity(Conta);
            }
        });

        ImageView imgFiltro = (ImageView) findViewById(R.id.imgFiltro);
        imgFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Filtro = new Intent(getApplicationContext(), Filtros.class);
                Filtro.putExtra("codFunc",codFunc);
                startActivity(Filtro);
            }
        });


        Button btnServ = (Button) findViewById(R.id.btnServ);
        btnServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ListServ = new Intent(getApplicationContext(), ListaServ.class);
                ListServ.putExtra("codFunc",codFunc);
                startActivity(ListServ);
            }
        });

        Button btnFunc = (Button) findViewById(R.id.btnFunc);
        btnFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Conta = new Intent(getApplicationContext(), ContaFunc.class);
                Conta.putExtra("codFunc",codFunc);
                startActivity(Conta);
            }
        });
    }

    //dados do sensor
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(gyroListener);
    }

    //recebe os valores do girosopio
    public SensorEventListener gyroListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) {
        }

        //se o sensor for a mais de 4 puxa a dela de adiconar serviço assim sendo um tipo de atalho
        public void onSensorChanged(SensorEvent event) {
            float y = event.values[1];

            if(y>4){
                Intent AddServ = new Intent(getApplicationContext(), AddServ.class);
                startActivity(AddServ);
            }
        }
    };

}