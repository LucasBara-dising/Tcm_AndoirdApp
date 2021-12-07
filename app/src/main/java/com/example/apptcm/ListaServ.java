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


        ListaServico();


        imgBtnVoltaHome1= (ImageButton)findViewById(R.id.imgBtnVoltaHome1);
        imgBtnVoltaHome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaHome();
            }
        });

        BtnAddServ= (ImageButton)findViewById(R.id.ImgBtnAddServ);
        BtnAddServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaAddServ();
            }
        });


        listviewServ=(ListView)findViewById(R.id.listviewServ);
        listviewServ.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String conteudo=(String) listviewServ.getItemAtPosition(position);

                //vai pegar oq estiver entes do "-" na varaivel conteudo
                String cod=conteudo.substring(0,conteudo.indexOf("-"));

                Servico servico= db.selecionarServico(Integer.parseInt(String.valueOf(cod)));

                int codServ= Integer.parseInt(String.valueOf(cod));

                Intent intent = new Intent(ListaServ.this, DetalhesServ.class);
                intent.putExtra("codServ", codServ);
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

        for(Servico c:servicos){
            arrayList.add(c.getCodeServ()+ "-"+"Empresa: " +c.getNomeEmpresa()+"\n"+
                    "Titulo: "+c.getTituloServ()+"\n"+ "Prazo: "+c.getPrazo()+"\n");
            adpater.notifyDataSetChanged();
        }
    }


    public void TelaAddServ(){
        Intent AddServ = new Intent(getApplicationContext(), AddServ.class);
        startActivity(AddServ);
        finish();
    }

    public void TelaHome(){
        Intent Home = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(Home);
    }
}