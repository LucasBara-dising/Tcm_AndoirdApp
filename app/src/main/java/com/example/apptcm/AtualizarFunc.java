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


public class AtualizarFunc extends AppCompatActivity {

    EditText editEmailFunc, editNomeFunc, editSenhaFunc, editTelFunc;
    BancoDeDados db=new BancoDeDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_func);

        editEmailFunc= (EditText)findViewById(R.id.editEmailFunc);
        editNomeFunc=(EditText)findViewById(R.id.editNomeFunc);
        editSenhaFunc=(EditText)findViewById(R.id.editSenhaFunc);
        editTelFunc = (EditText) findViewById(R.id.editTelFunc);

        //recebe o codigo da tela conta
        Intent intent = getIntent();
        int codFunc = intent.getIntExtra("codFunc",1);

        Funcionario funcionario= db.selecionarFunc(codFunc);

        //prenche todos os campos com dados
        editEmailFunc.setText(funcionario.getEmailFunc());
        editNomeFunc.setText(funcionario.getNomeFunc());
        editSenhaFunc.setText(funcionario.getSenhaFunc());

        //Dropdown
        Spinner dropdownCargoUpdate=(Spinner)findViewById(R.id.dropdownCargoUpdate);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.CargosFunc));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownCargoUpdate.setAdapter(adapter);

        ImageView btnFechaAdd = (ImageView) findViewById(R.id.btnFechaAdd);
        btnFechaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //leva pra tela conta
                Intent Conta = new Intent(getApplicationContext(), ContaFunc.class);
                Conta.putExtra("codFunc",codFunc);
                startActivity(Conta);
            }
        });

        //ao clicar no btn salvar vai p
        Button btnSalvarFunc = (Button) findViewById(R.id.btnSalvarFunc);
        btnSalvarFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recebe os valores
                String EmailFunc= editEmailFunc.getText().toString();
                String NomeFunc=editNomeFunc.getText().toString();
                String SenhaFunc=editSenhaFunc.getText().toString();
                String CargoFunc=dropdownCargoUpdate.getSelectedItem().toString();

                    //faz o update
                    db.updateFunc(new Funcionario(Integer.parseInt(String.valueOf(codFunc)),EmailFunc, SenhaFunc, NomeFunc, CargoFunc));
                    //mensagem de sucesso
                    Toast.makeText(AtualizarFunc.this, "Funcionario Atualizado ", Toast.LENGTH_LONG).show();
                    //leva pra tela conta
                    Intent Conta = new Intent(getApplicationContext(), ContaFunc.class);
                    startActivity(Conta);
            }
        });
    }

}