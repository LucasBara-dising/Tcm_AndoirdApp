package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class AddServ extends AppCompatActivity {

    EditText editNomeEmpresa, editTitulo, editTextDate, editDescserv,editAreaserv;
    BancoDeDados db=new BancoDeDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_serv);

        //recebe os dados da outra tela
        Intent intent = getIntent();
        int codFunc = intent.getIntExtra("codFunc",0);

        editNomeEmpresa= (EditText)findViewById(R.id.editNomeEmpresa);
        editTitulo=(EditText)findViewById(R.id.editTitulo);
        editTextDate=(EditText)findViewById(R.id.editTextDate);
        editDescserv=(EditText)findViewById(R.id.editDescserv);
        editAreaserv=(EditText) findViewById(R.id.editAreaserv);


        ImageView btnFechaAdd = (ImageView) findViewById(R.id.btnFechaAdd);
        btnFechaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ListServ = new Intent(getApplicationContext(), ListaServ.class);
                ListServ.putExtra("codFunc",codFunc);
                startActivity(ListServ);
            }
        });

        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recebe os valores dos campos
                String nomeEmpresa= editNomeEmpresa.getText().toString();
                String titulo=editTitulo.getText().toString();
                String prazo=editTextDate.getText().toString();
                String descricao=editDescserv.getText().toString();
                String areaTrab=editAreaserv.getText().toString();


                //se tiver algum vazio mostrra erro
                if(nomeEmpresa.isEmpty() || descricao.isEmpty() || prazo.isEmpty() || titulo.isEmpty()|| areaTrab.isEmpty()){
                    //mensagem de erro
                   Toast.makeText(AddServ.this, "Os campos são Obrigatoiros",Toast.LENGTH_LONG).show();

                }else {
                    //insert
                    db.addserv(new Servico(nomeEmpresa, titulo, prazo, descricao, areaTrab, "Não iniciado"));
                    //mensagem de sucesso
                    Toast.makeText(AddServ.this, "Serviço adicionado ", Toast.LENGTH_LONG).show();

                    Intent ListServ = new Intent(getApplicationContext(), ListaServ.class);
                    ListServ.putExtra("codFunc",codFunc);
                    startActivity(ListServ);
                }
            }
        });
    }
}