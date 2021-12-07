package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Login extends AppCompatActivity {

    EditText editUserLogin, EditSenhaLogin;
    Button btnLogin;
    BancoDeDados db = new BancoDeDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        db.addFunc(new Funcionario("adm","adm","Root","Lider"));

        editUserLogin = (EditText) findViewById(R.id.editUserLogin);
        EditSenhaLogin = (EditText) findViewById(R.id.EditSenhaLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUserLogin.setText("adm");
                EditSenhaLogin.setText("adm");

                String login = editUserLogin.getText().toString();
                String senha = EditSenhaLogin.getText().toString();

                //verifica se os campos estão prenchidos
                if(login.isEmpty() ||senha.isEmpty()){
                    Toast.makeText(Login.this, "Campos Obrigatorios", Toast.LENGTH_LONG).show();
                }
                else{
                    Funcionario funcionario= db.ValidaFunc(login,senha);

                    if(funcionario !=null){
                        int codFunc=funcionario.getIdFunc();

                        //mandando o cod para tela de conta
                        Intent intent = new Intent(getApplicationContext(), ContaFunc.class);
                        intent.putExtra("codFunc",codFunc);
                        startActivity(intent);

                        //abre home
                        Intent home = new Intent(getApplicationContext(), ContaFunc.class);
                        startActivity(home);
                    }
                    else{
                        Toast.makeText(Login.this, "Login E Senha não existe", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}