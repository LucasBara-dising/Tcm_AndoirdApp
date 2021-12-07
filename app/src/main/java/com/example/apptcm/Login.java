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

public class Login extends AppCompatActivity {

    EditText editUserLogin, EditSenhaLogin;
    Button btnLogin;
    BancoDeDados db = new BancoDeDados(this);

    //Não loga
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        db.addFunc(new Funcionario("admin","admin","Root","gerente"));

        editUserLogin = (EditText) findViewById(R.id.editUserLogin);
        EditSenhaLogin = (EditText) findViewById(R.id.EditSenhaLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String login = editUserLogin.getText().toString();
                String senha = EditSenhaLogin.getText().toString();

            }
        });


    }
}