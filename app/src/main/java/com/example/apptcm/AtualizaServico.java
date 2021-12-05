package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ItensDropdown));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownNivel.setAdapter(adapter);

        editNomeEmpresa= (EditText)findViewById(R.id.editNomeEmpresa);
        editTitulo=(EditText)findViewById(R.id.editTitulo);
        editTextDate=(EditText)findViewById(R.id.editTextDate);
        editDescserv=(EditText)findViewById(R.id.editDescserv);
        editAreaserv=(EditText) findViewById(R.id.editAreaserv);

        Intent intent = getIntent();
        int CodigoServ = intent.getIntExtra("codServ",0);

        Servico servico= db.selecionarServico(CodigoServ);

        editNomeEmpresa.setText(servico.getNomeEmpresa());
        editTitulo.setText(servico.getTituloServ());
        editTextDate.setText(servico.getPrazo());
        editDescserv.setText(servico.getDescServ());
        editAreaserv.setText(servico.getAreaServ());
        //dropdownNivel.setSelection(Integer.parseInt(servico.getAreaServ()));

        btnAtualiza= (Button)findViewById(R.id.btnAtualiza);
        btnAtualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomeEmpresa= editNomeEmpresa.getText().toString();
                String titulo=editTitulo.getText().toString();
                String prazo=editTextDate.getText().toString();
                String descricao=editDescserv.getText().toString();
                String areaTrab=editAreaserv.getText().toString();
                String NivelConclusao=dropdownNivel.getSelectedItem().toString();

                //update
                    db.updateServ(new Servico(Integer.parseInt(String.valueOf(CodigoServ)),nomeEmpresa, titulo,prazo, descricao,areaTrab,NivelConclusao));
                    //mensagem de sucesso
                    Toast.makeText(AtualizaServico.this, "Serviço Atualizado ", Toast.LENGTH_LONG).show();
                    TelaDetalhes();
            }
        });

        ImageView btnFechaAdd= (ImageView)findViewById(R.id.btnFechaAdd);
        btnFechaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaDetalhes();
            }
        });
    }

    private void TelaDetalhes() {
        Intent Lista = new Intent(getApplicationContext(), ListaServ.class);
        startActivity(Lista);
    }

}