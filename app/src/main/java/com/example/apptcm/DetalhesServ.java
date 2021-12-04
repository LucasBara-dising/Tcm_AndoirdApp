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
import android.widget.TextView;

public class DetalhesServ extends AppCompatActivity {

    BancoDeDados db=new BancoDeDados(this);
    TextView txtViewTitulo, txtViewEmpresa, txtViewPrazo, txtViewArea, txtViewDesc;

    int Codserv=1;

    //============================CRIAR A TELA E TESTAR E ADD CORES =====================
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_serv);

        View viewNivelConclusão=(View)findViewById(R.id.viewNivelConclusão);

         txtViewTitulo=(TextView)findViewById(R.id.txtViewTitulo);
         txtViewEmpresa=(TextView)findViewById(R.id.txtViewEmpresa);
         txtViewPrazo=(TextView)findViewById(R.id.txtViewPrazo);
         txtViewArea=(TextView)findViewById(R.id.txtViewArea);
         txtViewDesc=(TextView)findViewById(R.id.txtViewDesc);


        int Codserv=1;
        Servico servico= db.selecionarServico(Codserv);

        txtViewTitulo.setText(servico.getTituloServ());
        txtViewEmpresa.setText(servico.getNomeEmpresa());
        txtViewPrazo.setText(servico.getPrazo());
        txtViewArea.setText(servico.getAreaServ());
        txtViewDesc.setText(servico.getDescServ());

        String nivelConclusao=servico.getPrazo();

        switch (nivelConclusao){
            case "Terminado":
                viewNivelConclusão.setBackgroundColor(Color.parseColor("#33333333"));
                break;

            case "ão iniciado":
                viewNivelConclusão.setBackgroundColor(Color.parseColor("#33333333"));
                break;

            case "Em Andamento":
                viewNivelConclusão.setBackgroundColor(Color.parseColor("#33333333"));
                break;

            case "Prazo Proximo":
                viewNivelConclusão.setBackgroundColor(Color.parseColor("#33333333"));
                break;

            case "Atrasado":
                viewNivelConclusão.setBackgroundColor(Color.parseColor("#33333333"));
                break;

        }

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.salvaServ:
                SalveServ();
                return true;
            case R.id.AtualizaServ:
                TelaUpdateServ();
                return true;

            case R.id.deleteServ:
                DeleteServ();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    //salvar exeterno
    public void SalveServ(){
        Intent Home = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(Home);
    }

    public void TelaUpdateServ(){
        Intent Atualizaserv = new Intent(getApplicationContext(), AtualizaServico.class);
        startActivity(Atualizaserv);
    }

    public void DeleteServ(){
        Servico servico=new Servico();
        servico.setCodeServ(Codserv);
        db.ApagaServ(servico);
    }
}