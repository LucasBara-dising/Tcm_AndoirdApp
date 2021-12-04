package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AtualizaServico extends AppCompatActivity {

    //DROPDWON DE NIVEL DE CONCLUÇÃO
    EditText editNomeEmpresa, editTitulo, editTextDate, editDescserv, editAreaserv;
    Button btnAtualiza;

    BancoDeDados db=new BancoDeDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualiza_servico);

        Spinner dropdownNivel=(Spinner)findViewById(R.id.dropdownNivel);
        //ArrayAdapter<CharSequence> adapter = new ArrayAdapter.createFromResource(this, R.array.ItensDropdown, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //dropdownNivel.setAdapter(adapter);

        editNomeEmpresa= (EditText)findViewById(R.id.editNomeEmpresa);
        editTitulo=(EditText)findViewById(R.id.editTitulo);
        editTextDate=(EditText)findViewById(R.id.editTextDate);
        editDescserv=(EditText)findViewById(R.id.editDescserv);
        editAreaserv=(EditText) findViewById(R.id.editAreaserv);

        int CodigoServ=1;
        Servico servico= db.selecionarServico(CodigoServ);

        editNomeEmpresa.setText(servico.getNomeEmpresa());
        editTitulo.setText(servico.getTituloServ());
        editTextDate.setText(servico.getPrazo());
        editDescserv.setText(servico.getDescServ());
        editAreaserv.setText(servico.getAreaServ());

        btnAtualiza= (Button)findViewById(R.id.btnAtualiza);
        btnAtualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomeEmpresa= editNomeEmpresa.getText().toString();
                String titulo=editTitulo.getText().toString();
                String prazo=editTextDate.getText().toString();
                String descricao=editDescserv.getText().toString();
                String areaTrab=editAreaserv.getText().toString();

                //update
                    db.updateServ(new Funcionario(Integer.parseInt(String.valueOf(CodigoServ)),nomeEmpresa, titulo,prazo, descricao));
                    //mensagem de sucesso
                    Toast.makeText(AtualizaServico.this, "Serviço Atualizado ", Toast.LENGTH_LONG).show();
                    TelaDetalhes();
            }
        });
    }

    private void TelaDetalhes() {
        Intent Detalhes = new Intent(getApplicationContext(), DetalhesServ.class);
        startActivity(Detalhes);
    }
}