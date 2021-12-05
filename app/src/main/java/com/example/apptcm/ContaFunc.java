package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ContaFunc extends AppCompatActivity {

    ImageButton imgBtnVoltaHome;
    Button novoFunc, updateConta, btnSalvaDados;
    BancoDeDados db=new BancoDeDados(this);

    final String filename = "DadosFunc.txt";

    int codFunc=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta_func);

        Funcionario funcionario= db.selecionarFunc(codFunc);

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

        btnSalvaDados= (Button)findViewById(R.id.btnSalvaDados);
        btnSalvaDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    public void TelaAddFunc(){
        Intent AddFunc = new Intent(getApplicationContext(), AddFunc.class);
        startActivity(AddFunc);
    }

    public void TelaUpdateFunc(){
        Intent UpdadeServ = new Intent(getApplicationContext(), AtualizarFunc.class);
        startActivity(UpdadeServ);

        Intent intent = new Intent(ContaFunc.this, AtualizarFunc.class);
        intent.putExtra("codFunc", codFunc);
        startActivity(intent);
    }

    public void TelaHome(){
        Intent home = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(home);
    }

    public void salvar() {
        try {
            Funcionario funcionario= db.selecionarFunc(codFunc);

            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            String Dados = "Nome: " + funcionario.getNomeFunc() + "\n" +"Email: " + funcionario.getEmailFunc();
            //grava na memoria
            fos.write(Dados.getBytes());
            //Mensagem de sucesso
            Toast.makeText(ContaFunc.this, "seus dados foram salvos", Toast.LENGTH_SHORT).show();
            fos.close();

            //le os dados gravados
            FileInputStream fis = openFileInput(filename);
            int i = 0;
            String DadoFund = " ";
            //loop para escrever os dados gravados
            while ((i = fis.read()) != -1) {
                DadoFund = DadoFund + (char) i;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}