package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddFunc extends AppCompatActivity {

    EditText editEmailFunc, editNomeFunc, editSenhaFunc,editCargoFunc;

    BancoDeDados db=new BancoDeDados(this);

    //=====================Terminado=======================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_func);

        editEmailFunc= (EditText)findViewById(R.id.editEmailFunc);
        editNomeFunc=(EditText)findViewById(R.id.editNomeFunc);
        editSenhaFunc=(EditText)findViewById(R.id.editSenhaFunc);
        editCargoFunc=(EditText)findViewById(R.id.editCargoFunc);

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


                if(EmailFunc.isEmpty() || NomeFunc.isEmpty() || SenhaFunc.isEmpty() || CargoFunc.isEmpty()){
                    //mensagem de erro
                    Toast.makeText(AddFunc.this, "Os campos são Obrigatoiros",Toast.LENGTH_LONG).show();

                }else {
                    //insert
                    db.addFunc(new Funcionario(EmailFunc, SenhaFunc, NomeFunc, CargoFunc));
                    //mensagem de sucesso
                    Toast.makeText(AddFunc.this, "Funcionario adicionado ", Toast.LENGTH_LONG).show();
                    TelaConta();
                }
            }
        });
    }
    public void TelaConta(){
        Intent Conta = new Intent(getApplicationContext(), ContaFunc.class);
        startActivity(Conta);
        finish();
    }
}