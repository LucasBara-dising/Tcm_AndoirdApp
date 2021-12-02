package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaServ extends AppCompatActivity {

    ListView listviewServ;
    ImageView BtnAddServ;

    BancoDeDados db=new BancoDeDados(this);

    ArrayAdapter<String> adpater;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_serv);


        BtnAddServ= (ImageView)findViewById(R.id.ImgBtnAddServ);
        BtnAddServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaAddServ();
            }
        });

        listviewServ=(ListView)findViewById(R.id.listviewServ);
        ListaServico();
    }

    //Lista de serviços
    public void ListaServico(){
        List<Servico> servicos = db.ListaTodosServicos();

        arrayList= new ArrayList<String>();
        adpater=new ArrayAdapter<String>(ListaServ.this, android.R.layout.simple_list_item_1, arrayList);
        listviewServ.setAdapter(adpater);

        for(Servico c:servicos){
            arrayList.add(c.getCodeServ()+ "-"+"Empresa: " +c.getNomeEmpresa()+"\n\n"+
                    "Titulo: "+c.getTituloServ()+"\n\n"+ "Prazo: "+c.getPrazo());
            adpater.notifyDataSetChanged();
        }
    }

    public void TelaAddServ(){
        Intent AddServ = new Intent(getApplicationContext(), AddServ.class);
        startActivity(AddServ);
    }
}