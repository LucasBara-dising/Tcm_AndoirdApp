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

        //recebe os dados da outra tela
        Intent intent = getIntent();
        int codFunc = intent.getIntExtra("codFunc",0);

        ListaServico();

        imgBtnVoltaHome1= (ImageButton)findViewById(R.id.imgBtnVoltaHome1);
        imgBtnVoltaHome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Home = new Intent(getApplicationContext(), MainActivity.class);
                Home.putExtra("codFunc",codFunc);
                startActivity(Home);
            }
        });

        BtnAddServ= (ImageButton)findViewById(R.id.ImgBtnAddServ);
        BtnAddServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddServ = new Intent(getApplicationContext(), AddServ.class);
                AddServ.putExtra("codFunc",codFunc);
                startActivity(AddServ);
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
                //manda cod func
                intent.putExtra("codFunc", codFunc);
                startActivity(intent);

            }
        });
    }

    //Lista de serviços
    public void ListaServico(){
        List<Servico> servicos = db.ListaTodosServicos();

        arrayList= new ArrayList<String>();
        adpater=new ArrayAdapter<String>(ListaServ.this, android.R.layout.simple_list_item_1, arrayList);
        listviewServ=(ListView)findViewById(R.id.listviewServ);
        listviewServ.setAdapter(adpater);

        //loop para mostrar tudo
        for(Servico c:servicos){
            //corpo do item list
            arrayList.add(c.getCodeServ()+ "-"+"Empresa: " +c.getNomeEmpresa()+"\n"+
                    "Titulo: "+c.getTituloServ()+"\n"+ "Prazo: "+c.getPrazo()+"\n");
            adpater.notifyDataSetChanged();
        }
    }
}