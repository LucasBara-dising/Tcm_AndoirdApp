package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    //giroscopio
    SensorManager sensorManager;
    Sensor sensor;

    ArrayList<String> projetoId, projetoTitulo, ProjetoEmpresa, tecUsada;

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
                String ativa="PalavraChave";
                String TituloProj = TxtEdtPesquisa.getText().toString();
                Intent ListServ = new Intent(getApplicationContext(), ListaServ.class);
                ListServ.putExtra("TituloProj",TituloProj);
                ListServ.putExtra("ativa",ativa);
                startActivity(ListServ);
            }
        });


        ImageView ImgBtnConta = (ImageView) findViewById(R.id.ImgBtnConta);
        ImgBtnConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Conta = new Intent(getApplicationContext(), ContaFunc.class);
                startActivity(Conta);
            }
        });

        ImageView imgFiltro = (ImageView) findViewById(R.id.imgFiltro);
        imgFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Filtro = new Intent(getApplicationContext(), Filtros.class);
                startActivity(Filtro);
            }
        });

        Button btnServ = (Button) findViewById(R.id.btnServ);
        btnServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ativa="Todos";
                Intent ListServ = new Intent(getApplicationContext(), ListaServ.class);
                ListServ.putExtra("ativa",ativa);
                startActivity(ListServ);
            }
        });

        Button btnFunc = (Button) findViewById(R.id.btnFunc);
        btnFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Conta = new Intent(getApplicationContext(), ContaFunc.class);
                startActivity(Conta);
            }
        });
    }
//============================== TENTATIVA DE COLOCAR RECYCLER==============================
    //        projetoId= new ArrayList<>();
//        projetoTitulo= new ArrayList<>();
//        ProjetoEmpresa= new ArrayList<>();
//        tecUsada= new ArrayList<>();

//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        RecyclerViewAdapter adpter= new RecyclerViewAdapter(MainActivity.this, projetoId, projetoTitulo, ProjetoEmpresa, tecUsada);
//        recyclerView.setAdapter(adpter);


    //configurarRecycler();

    /* void Mostradados() {
        BancoDeDados db=new BancoDeDados(this);
        Cursor cursor = db. mostraDadosHome();

        if(cursor.getCount()==0){
            Toast.makeText(this, "sem dados", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                ProjetoEmpresa.add(cursor.getString(1));
                projetoTitulo.add(cursor.getString(2));
                tecUsada.add(cursor.getString(5));
            }
        }
    }

    private void configurarRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        BancoDeDados db=new BancoDeDados(this);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
    }*/

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