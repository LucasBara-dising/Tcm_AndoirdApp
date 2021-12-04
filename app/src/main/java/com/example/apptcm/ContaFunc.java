package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ContaFunc extends AppCompatActivity {

    ImageButton imgBtnVoltaHome;
    Button novoFunc, updateConta;
    BancoDeDados db=new BancoDeDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta_func);

        Funcionario funcionario= db.selecionarFunc(1);

        TextView txtViewNameFunc=(TextView)findViewById(R.id.txtViewNameFunc);
        txtViewNameFunc.setText("Nome: " + funcionario.getNomeFunc());

        TextView txtViewEmailFunc=(TextView)findViewById(R.id.txtViewEmailFunc);
        txtViewEmailFunc.setText("Email: " + funcionario.getEmailFunc());

        imgBtnVoltaHome= (ImageButton)findViewById(R.id.imgBtnVoltaHome);
        imgBtnVoltaHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaHome();
            }
        });

        novoFunc= (Button)findViewById(R.id.novoFunc);
        novoFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaAddFunc();
            }
        });

        updateConta= (Button)findViewById(R.id.updateConta);
        updateConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaUpdateFunc();
            }
        });
    }

    public void TelaAddFunc(){
        Intent AddFunc = new Intent(getApplicationContext(), AddFunc.class);
        startActivity(AddFunc);
    }

    public void TelaUpdateFunc(){
        Intent AddServ = new Intent(getApplicationContext(), AtualizarFunc.class);
        startActivity(AddServ);
    }

    public void TelaHome(){
        Intent home = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(home);
    }
}