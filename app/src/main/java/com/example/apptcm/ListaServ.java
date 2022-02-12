package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaServ extends AppCompatActivity {

    ListView listviewServ;
    ImageButton imgBtnVoltaHome1, BtnAddServ;

    BancoDeDados db=new BancoDeDados(this);

    ArrayAdapter<String> adpater;
    ArrayList<String> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_serv);


       //nivel conclusão
        Intent intent1 = getIntent();
        String NivelConclusao =intent1.getStringExtra("NivelConclusao");

       //Tecnlogia usada
        Intent intent2 = getIntent();
        String nomeTec = intent2.getStringExtra("areaTrab");

        //Palavra chave filtro
        Intent intent3 = getIntent();
        String nomeProj = intent3.getStringExtra("PlvChave");

        //Palavra chave home
        Intent intent4 = getIntent();
        String TituloProj = intent4.getStringExtra("TituloProj");


        //recebe os dados da outra tela
        Intent intent5 = getIntent();
        String ativa = intent5.getStringExtra("ativa");

        if(ativa==null){
            ativa="Todos";
        }

        Toast.makeText(ListaServ.this, "plavra:" + NivelConclusao, Toast.LENGTH_LONG).show();


        //definição do arry que lista os projetos
        arrayList = new ArrayList<String>();
        adpater = new ArrayAdapter<String>(ListaServ.this, android.R.layout.simple_list_item_1, arrayList);
        listviewServ = (ListView) findViewById(R.id.listviewServ);
        listviewServ.setAdapter(adpater);

        //----------------------Lista Projetos-------------------------
        switch (ativa){
            case "Todos":
                //lista todos
                List<Servico> TodosProj = db.ListaTodosServicos();

                //loop para mostrar tudo
                for (Servico c : TodosProj) {
                    //corpo do item list
                    arrayList.add(c.getCodeServ() + "-" + "Empresa: " + c.getNomeEmpresa() + "\n" +
                            "Titulo: " + c.getTituloServ() + "\n" + "Prazo: " + c.getPrazo() + "\n");
                    adpater.notifyDataSetChanged();
                }
                break;

            case "PalavraChave":
                //mostra por pesquisa da edit text
                List<Servico> PlvChaveProj = db.ListaProjetosPorPalavraChave(TituloProj);

                //loop para mostrar tudo o corpo do item list
                for (Servico c : PlvChaveProj) {
                    arrayList.add(c.getCodeServ() + "-" + "Empresa: " + c.getNomeEmpresa() + "\n" +
                            "Titulo: " + c.getTituloServ() + "\n" + "Prazo: " + c.getPrazo() + "\n");
                    adpater.notifyDataSetChanged();
                }
                break;

            case "Filtro":
                //mostra com filtro
                int NumeroProj = db.NumDeProjetos(nomeTec, NivelConclusao, nomeProj);

                if (NumeroProj > 0) {
                    List<Servico> FiltroProj = db.ListaTodosServicosComFiltro(nomeTec, NivelConclusao, nomeProj);

                    //loop para mostrar o corpo do item list
                    for (Servico c : FiltroProj) {
                        arrayList.add(c.getCodeServ() + "-" + "Empresa: " + c.getNomeEmpresa() + "\n" +
                                "Titulo: " + c.getTituloServ() + "\n" + "Prazo: " + c.getPrazo() + "\n");
                        adpater.notifyDataSetChanged();
                    }
                }
                //se com o filtro não tiver resultados mostra mensagem
                else {
                    ImageView imgVazio = (ImageView) findViewById(R.id.imgVazio);
                    imgVazio.setVisibility(View.VISIBLE);

                    TextView txtVazio = (TextView) findViewById(R.id.txtVazio);
                    txtVazio.setVisibility(View.VISIBLE);
                }

                break;

            default:
                //lista todos
                List<Servico> TodosProjs = db.ListaTodosServicos();

                //loop para mostrar tudo
                for (Servico c : TodosProjs) {
                    //corpo do item list
                    arrayList.add(c.getCodeServ() + "-" + "Empresa: " + c.getNomeEmpresa() + "\n" +
                            "Titulo: " + c.getTituloServ() + "\n" + "Prazo: " + c.getPrazo() + "\n");
                    adpater.notifyDataSetChanged();
                }
                break;

        }


        BtnAddServ= (ImageButton)findViewById(R.id.ImgBtnAddServ);
        BtnAddServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddServ = new Intent(getApplicationContext(), AddServ.class);
                startActivity(AddServ);
            }
        });

        imgBtnVoltaHome1= (ImageButton)findViewById(R.id.imgBtnVoltaHome1);
        imgBtnVoltaHome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddServ = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(AddServ);
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


        //ao clicar no item da lista
        listviewServ=(ListView)findViewById(R.id.listviewServ);
        listviewServ.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String conteudo=(String) listviewServ.getItemAtPosition(position);

                //vai pegar oq estiver entes do "-" na varaivel conteudo ouseja o cod
                String cod=conteudo.substring(0,conteudo.indexOf("-"));

                Servico servico= db.selecionarServico(Integer.parseInt(String.valueOf(cod)));

                int codServ= Integer.parseInt(String.valueOf(cod));

                //manda o cod pra tela de detalhes
                Intent intent = new Intent(ListaServ.this, DetalhesServ.class);
                intent.putExtra("codServ", codServ);
                startActivity(intent);
            }
        });
    }

}