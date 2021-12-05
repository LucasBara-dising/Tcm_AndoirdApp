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

    //===================== TERMIANADO===============

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_serv);

        editNomeEmpresa= (EditText)findViewById(R.id.editNomeEmpresa);
        editTitulo=(EditText)findViewById(R.id.editTitulo);
        editTextDate=(EditText)findViewById(R.id.editTextDate);
        editDescserv=(EditText)findViewById(R.id.editDescserv);
        editAreaserv=(EditText) findViewById(R.id.editAreaserv);


        ImageView btnFechaAdd = (ImageView) findViewById(R.id.btnFechaAdd);
        btnFechaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaListaServ();
            }
        });

        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomeEmpresa= editNomeEmpresa.getText().toString();
                String titulo=editTitulo.getText().toString();
                String prazo=editTextDate.getText().toString();
                String descricao=editDescserv.getText().toString();
                String areaTrab=editAreaserv.getText().toString();


                if(nomeEmpresa.isEmpty() || descricao.isEmpty() || prazo.isEmpty() || titulo.isEmpty()|| areaTrab.isEmpty()){
                    //mensagem de erro
                   Toast.makeText(AddServ.this, "Os campos são Obrigatoiros",Toast.LENGTH_LONG).show();

                }else {
                    //insert
                    db.addserv(new Servico(nomeEmpresa, titulo, prazo, descricao, areaTrab, "Não iniciado"));
                    //mensagem de sucesso
                    Toast.makeText(AddServ.this, "Serviço adicionado ", Toast.LENGTH_LONG).show();
                    TelaListaServ();
                }
            }
        });
    }

    public void TelaListaServ(){
        Intent ListServ = new Intent(getApplicationContext(), ListaServ.class);
        startActivity(ListServ);
        finish();
    }

}