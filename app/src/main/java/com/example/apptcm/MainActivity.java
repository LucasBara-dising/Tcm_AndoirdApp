package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText TxtEdtPesquisa = (EditText) findViewById(R.id.TxtEdtPesquisa);
        TxtEdtPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaListaServ();
            }
        });
    }

    public void TelaListaServ(){
        Intent ListServ = new Intent(getApplicationContext(), ListaServ.class);
        startActivity(ListServ);
    }

}