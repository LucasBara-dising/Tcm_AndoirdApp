package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
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
    Button novoFunc, updateConta, btnSalvaDados, btnLogout;
    BancoDeDados db=new BancoDeDados(this);

    //nome do arquivo do armazenamneto
    final String filename = "DadosFunc.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta_func);

        //recebe os dados da outra tela
        Intent intent = getIntent();
        int codFunc = intent.getIntExtra("codFunc",0);


        //faz o select para mostrar o nome e o emial do user
        Funcionario funcionario= db.selecionarFunc(codFunc);

        TextView txtViewNameFunc=(TextView)findViewById(R.id.txtViewNameFunc);
        txtViewNameFunc.setText("Nome: " + funcionario.getNomeFunc());

        TextView txtViewEmailFunc=(TextView)findViewById(R.id.txtViewEmailFunc);
        txtViewEmailFunc.setText("Email: " + funcionario.getEmailFunc());

        imgBtnVoltaHome= (ImageButton)findViewById(R.id.imgBtnVoltaHome);
        imgBtnVoltaHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                home.putExtra("codFunc",codFunc);
                startActivity(home);
            }
        });

        //add func
        novoFunc= (Button)findViewById(R.id.novoFunc);
        novoFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(funcionario.getCargoFunc().equals("Lider")){
                    Intent AddFunc = new Intent(getApplicationContext(), AddFunc.class);
                    AddFunc.putExtra("codFunc",codFunc);
                    startActivity(AddFunc);
                }
                else{
                    //mensagem de sem permissão
                    Toast.makeText(ContaFunc.this, "Vocé não tem permissão para isso", Toast.LENGTH_LONG).show();
                }
            }
        });

        //update conta
        updateConta= (Button)findViewById(R.id.updateConta);
        updateConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UpdadeServ = new Intent(getApplicationContext(), AtualizarFunc.class);
                UpdadeServ.putExtra("codFunc",codFunc);
                startActivity(UpdadeServ);
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

    //armazenamento interno
    //vai salvar alguns dados do user
    public void salvar() {
        try {
            //recebe o codigo
            Intent intent = getIntent();
            int codFunc = intent.getIntExtra("codFunc",0);

            //seleciona
            Funcionario funcionario= db.selecionarFunc(codFunc);

            //faz o texto
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