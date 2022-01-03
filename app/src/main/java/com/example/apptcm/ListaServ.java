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

    //recebe os dados da outra tela
    Intent intent = getIntent();
    int codFunc = intent.getIntExtra("codFunc",0);

    //nivel conclusão
    Intent intent1 = getIntent();
    String NivelConclusao = String.valueOf(intent1.getIntExtra("NivelConclusao",0));

    //nivel conslução
    Intent intent2 = getIntent();
    String nomeTec = String.valueOf(intent2.getIntExtra("areaTrab",0));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_serv);

        //Lista serviços-----------------------------------------
        ListaServico();

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
    public void ListaServico() {
        //----------------------Lista serviços-------------------------
        if (nomeTec==null || NivelConclusao==null) {
            List<Servico> servicos = db.ListaTodosServicos();

            arrayList = new ArrayList<String>();
            adpater = new ArrayAdapter<String>(ListaServ.this, android.R.layout.simple_list_item_1, arrayList);
            listviewServ = (ListView) findViewById(R.id.listviewServ);
            listviewServ.setAdapter(adpater);

            //loop para mostrar tudo
            for (Servico c : servicos) {
                //corpo do item list
                arrayList.add(c.getCodeServ() + "-" + "Empresa: " + c.getNomeEmpresa() + "\n" +
                        "Titulo: " + c.getTituloServ() + "\n" + "Prazo: " + c.getPrazo() + "\n");
                adpater.notifyDataSetChanged();
            }
        } else {
            List<Servico> servicos = db.ListaTodosServicosComplvChave(nomeTec, NivelConclusao);

            arrayList = new ArrayList<String>();
            adpater = new ArrayAdapter<String>(ListaServ.this, android.R.layout.simple_list_item_1, arrayList);
            listviewServ = (ListView) findViewById(R.id.listviewServ);
            listviewServ.setAdapter(adpater);

            //loop para mostrar tudo
            for (Servico c : servicos) {
                //corpo do item list
                arrayList.add(c.getCodeServ() + "-" + "Empresa: " + c.getNomeEmpresa() + "\n" +
                        "Titulo: " + c.getTituloServ() + "\n" + "Prazo: " + c.getPrazo() + "\n");
                adpater.notifyDataSetChanged();
            }
        }
    }
}