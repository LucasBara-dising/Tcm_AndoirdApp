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

    EditText editEmailFunc, editNomeFunc, editSenhaFunc,editCargoFunc;

    BancoDeDados db=new BancoDeDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_func);


        editEmailFunc= (EditText)findViewById(R.id.editEmailFunc);
        editNomeFunc=(EditText)findViewById(R.id.editNomeFunc);
        editSenhaFunc=(EditText)findViewById(R.id.editSenhaFunc);
        editCargoFunc=(EditText)findViewById(R.id.editCargoFunc);

        //recebe o codigo da tela conta
        Intent intent = getIntent();
        int codFunc = intent.getIntExtra("codFunc",0);

        Funcionario funcionario= db.selecionarFunc(codFunc);

        editEmailFunc.setText(funcionario.getEmailFunc());
        editNomeFunc.setText(funcionario.getNomeFunc());
        editSenhaFunc.setText(funcionario.getSenhaFunc());
        editCargoFunc.setText(funcionario.getCargoFunc());

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
                String CargoFunc=editCargoFunc.getText().toString();

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