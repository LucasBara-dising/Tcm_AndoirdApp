package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DetalhesServ extends AppCompatActivity {

    BancoDeDados db=new BancoDeDados(this);
    TextView txtViewTitulo, txtViewEmpresa, txtViewPrazo, txtViewArea, txtViewDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_serv);

        //recebe  cod func
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("SalvaCodfunc", Context.MODE_PRIVATE);
        int codFunc = preferences.getInt("codFunc", 1);

        //recebe cod prod
        Intent intent = getIntent();
        int codServ = intent.getIntExtra("codServ",0);

        //seleciona
        Funcionario funcionario= db.selecionarFunc(codFunc);

        //volta pra lista
        ImageView imgBtnVoltaHome1=(ImageView)findViewById(R.id.imgBtnVoltaHome1);
        imgBtnVoltaHome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ListServ = new Intent(getApplicationContext(), ListaServ.class);
                startActivity(ListServ);
            }
        });


        //salva no armazenamneto
        ImageView imgBtnsalva=(ImageView)findViewById(R.id.imgBtnsalva);
        imgBtnsalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalveServ();
            }
        });

        //update
        Button btnUpdateServ=(Button)findViewById(R.id.btnUpdateServ);
        btnUpdateServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(funcionario.getCargoFunc().equals("Lider")){
                    //manda cod Func e cod serv para outra tela
                    Intent Atualizaserv = new Intent(DetalhesServ.this, AtualizaServico.class);
                    Atualizaserv.putExtra("codServ", codServ);
                    startActivity(Atualizaserv);
                }
                else{
                    //mensagem de sem permissão
                    Toast.makeText(DetalhesServ.this, "Vocé não tem permissão para isso", Toast.LENGTH_LONG).show();
                }
            }
        });

        //exluir
        Button btnExluir=(Button)findViewById(R.id.btnExluir);
        btnExluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(funcionario.getCargoFunc().equals("Lider")){
                    Servico servico=new Servico();
                    servico.setCodeServ(codServ);
                    //apaga
                    db.ApagaServ(servico);
                    //mensagem de sucesso
                    Toast.makeText(DetalhesServ.this, "Serviço Foi Deletado ", Toast.LENGTH_LONG).show();

                    Intent ListServ = new Intent(getApplicationContext(), ListaServ.class);
                    startActivity(ListServ);

                }else{
                    //mensagem de sem permissão
                    Toast.makeText(DetalhesServ.this, "Vocé não tem permissão para isso", Toast.LENGTH_LONG).show();
                }
            }
        });

         txtViewTitulo=(TextView)findViewById(R.id.txtViewTitulo);
         txtViewEmpresa=(TextView)findViewById(R.id.txtViewEmpresa);
         txtViewPrazo=(TextView)findViewById(R.id.txtViewPrazo);
         txtViewArea=(TextView)findViewById(R.id.txtViewArea);
         txtViewDesc=(TextView)findViewById(R.id.txtViewDesc);


        //seleciona
        Servico servico= db.selecionarServico(codServ);

        //define os texto dos campos
        txtViewTitulo.setText(servico.getTituloServ());
        txtViewEmpresa.setText(servico.getNomeEmpresa());
        txtViewDesc.setText(servico.getPrazo());
        txtViewArea.setText("Linguagem Usada: " + servico.getAreaServ());
        txtViewPrazo.setText(servico.getDescServ());

        String nivelConclusao=servico.getNivelConclusao();

        TextView txtPrazo=(TextView) findViewById(R.id.txtPrazo);
        View viewNivelConclusão=(View) findViewById(R.id.viewNivelConclusão);

        //switch para definir a cor do btn de nivel de conclusão
        switch (nivelConclusao){
            case "Terminado":
                viewNivelConclusão.setBackgroundColor(Color.parseColor("#5CB85C"));
                txtPrazo.setText("Terminado");
                break;

            case "Não iniciado":
                viewNivelConclusão.setBackgroundColor(Color.parseColor("#595555"));
                txtPrazo.setText("Não iniciado");
                break;

            case "Em Andamento":
                viewNivelConclusão.setBackgroundColor(Color.parseColor("#156C9A"));
                txtPrazo.setText("Em andamento");
                break;

            case "Prazo Proximo":
                viewNivelConclusão.setBackgroundColor(Color.parseColor("#F0AD4E"));
                txtPrazo.setText("Prazo proximo");
                break;

            case "Atrasado":
                viewNivelConclusão.setBackgroundColor(Color.parseColor("#D9534F"));
                txtPrazo.setText("Atrasado");
                break;
        }
    }

    //salvar exeterno
    public void SalveServ(){
        //recebe valor
        Intent intent = getIntent();
        int codServ = intent.getIntExtra("codServ",0);

        //seleciona dados
        Servico servico= db.selecionarServico(codServ);

        //monsta texto que vai ser salvo
        String  nomeDoAquivo = "DestalhesServ.txt";
        String  pasta = "servico";
        String  conteudo = "Empresa: "+servico.getNomeEmpresa() + "\n" +
                            "Titulo" + servico.getTituloServ()+ "\n" +
                            "Prazo: " + servico.getDescServ()+ "\n" +
                            "Descrição: " + servico.getPrazo();

        //se não receber o texto entoa salve nulo
        if(!conteudo.equals("")) {
            File myExternalFile = new File(getExternalFilesDir(pasta), nomeDoAquivo);
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(myExternalFile);
                fos.write(conteudo.getBytes());
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            catch (IOException e) {
                e.printStackTrace();
            }
            //mensagem de sucesso
            Toast.makeText(DetalhesServ.this, "Os Dados Foram Salvos", Toast.LENGTH_SHORT).show();
        }
    }
}