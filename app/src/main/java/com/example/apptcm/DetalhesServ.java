package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

        Button btnPrazo=(Button)findViewById(R.id.btnPrazo);

        //volta pra lista
        ImageButton imgBtnVoltaHome1=(ImageButton)findViewById(R.id.imgBtnVoltaHome1);
        imgBtnVoltaHome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaListaServ();
            }
        });


        //salva no armazenamneto
        ImageButton imgBtnsalva=(ImageButton)findViewById(R.id.imgBtnsalva);
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
                //recebe dados
                Intent intent1 = getIntent();
                int codServ = intent1.getIntExtra("codServ",0);

                TelaUpdateServ();

                //manda pra tela de update
                Intent intent = new Intent(DetalhesServ.this, AtualizaServico.class);
                intent.putExtra("codServ", codServ);
                startActivity(intent);

            }
        });

        //exluir
        Button btnExluir=(Button)findViewById(R.id.btnExluir);
        btnExluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteServ();
            }
        });

         txtViewTitulo=(TextView)findViewById(R.id.txtViewTitulo);
         txtViewEmpresa=(TextView)findViewById(R.id.txtViewEmpresa);
         txtViewPrazo=(TextView)findViewById(R.id.txtViewPrazo);
         txtViewArea=(TextView)findViewById(R.id.txtViewArea);
         txtViewDesc=(TextView)findViewById(R.id.txtViewDesc);

         //recebe cod
        Intent intent = getIntent();
        int codServ = intent.getIntExtra("codServ",0);

        //seleciona
        Servico servico= db.selecionarServico(codServ);

        //define os texto dos campos
        txtViewTitulo.setText(servico.getTituloServ());
        txtViewEmpresa.setText(servico.getNomeEmpresa());
        txtViewDesc.setText(servico.getPrazo());
        txtViewArea.setText("Linguagem Usada: "+servico.getAreaServ());
        txtViewPrazo.setText(servico.getDescServ());

        String nivelConclusao=servico.getNivelConclusao();

        //switch para definir a cor do btn de nivel de conclusão
        switch (nivelConclusao){
            case "Terminado":
                btnPrazo.setBackgroundColor(Color.parseColor("#42f575"));
                btnPrazo.setTextColor(Color.parseColor("#ffffff"));
                btnPrazo.setText("Terminado");
                break;

            case "Não iniciado":
                btnPrazo.setBackgroundColor(Color.parseColor("#595555"));
                btnPrazo.setTextColor(Color.parseColor("#ffffff"));
                btnPrazo.setText("Não iniciado");
                break;

            case "Em Andamento":
                btnPrazo.setBackgroundColor(Color.parseColor("#d6cb33"));
                btnPrazo.setTextColor(Color.parseColor("#ffffff"));
                btnPrazo.setText("Em Andamento");
                break;

            case "Prazo Proximo":
                btnPrazo.setBackgroundColor(Color.parseColor("#db851d"));
                btnPrazo.setTextColor(Color.parseColor("#ffffff"));
                btnPrazo.setText("Prazo Proximo");
                break;

            case "Atrasado":
                btnPrazo.setBackgroundColor(Color.parseColor("#db261d"));
                btnPrazo.setTextColor(Color.parseColor("#ffffff"));
                btnPrazo.setText("Atrasado");
                break;

        }

    }

    public void TelaListaServ(){
        Intent ListServ = new Intent(getApplicationContext(), ListaServ.class);
        startActivity(ListServ);
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

    public void TelaUpdateServ(){
        Intent Atualizaserv = new Intent(getApplicationContext(), AtualizaServico.class);
        startActivity(Atualizaserv);
    }

    public void DeleteServ(){
        //recebe valor
        Intent intent = getIntent();
        int codServ = intent.getIntExtra("codServ",0);

        Servico servico=new Servico();
        servico.setCodeServ(codServ);
        //apaga
        db.ApagaServ(servico);
        //mensagem de sucesso
        Toast.makeText(DetalhesServ.this, "Serviço Foi Deletado ", Toast.LENGTH_LONG).show();
        TelaListaServ();
    }

}