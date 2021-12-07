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

    EditText editEmailFunc, editNomeFunc, editSenhaFunc,editCargoFunc, editTelFunc;

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
        int codFunc = intent.getIntExtra("codFunc",0);

        Funcionario funcionario= db.selecionarFunc(codFunc);

        editEmailFunc.setText(funcionario.getEmailFunc());
        editNomeFunc.setText(funcionario.getNomeFunc());
        editSenhaFunc.setText(funcionario.getSenhaFunc());
        editCargoFunc.setText(funcionario.getCargoFunc());
        //editTelFunc.setText(funcionario.getTelFunc());

        //Dropdown
        Spinner dropdownCargo=(Spinner)findViewById(R.id.dropdownCargo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.CargosFunc));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownCargo.setAdapter(adapter);

        ImageView btnFechaAdd = (ImageView) findViewById(R.id.btnFechaAdd);
        btnFechaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaConta();
            }
        });

        Button btnSalvarFunc = (Button) findViewById(R.id.btnSalvarFunc);
        btnSalvarFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String EmailFunc= editEmailFunc.getText().toString();
                String NomeFunc=editNomeFunc.getText().toString();
                String SenhaFunc=editSenhaFunc.getText().toString();
                String CargoFunc=dropdownCargo.getSelectedItem().toString();
                String TelFunc=editTelFunc.getText().toString();

                    //update
                    db.updateServ(new Funcionario(Integer.parseInt(String.valueOf(codFunc)),EmailFunc, SenhaFunc, NomeFunc, CargoFunc));
                    //mensagem de sucesso
                    Toast.makeText(AtualizarFunc.this, "Funcionario Atualizado ", Toast.LENGTH_LONG).show();
                    TelaConta();

            }
        });
    }

    public void TelaConta(){
        Intent Conta = new Intent(getApplicationContext(), ContaFunc.class);
        startActivity(Conta);
    }
}