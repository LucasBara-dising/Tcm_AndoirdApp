package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
        editUserLogin = (EditText) findViewById(R.id.editUserLogin);
        EditSenhaLogin = (EditText) findViewById(R.id.EditSenhaLogin);

        //define SharedPreferences
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("SalvaCodfunc", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        //adciona o user adm
        int AdmCad =db.numeroDeUsers("admin@gmail.com","admin");
        if(AdmCad==0){
            db.addFunc(new Funcionario("admin@gmail.com","admin","Luiz Carlos","Lider"));
        }


            btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                //recebe os valores dos editText
                String login = editUserLogin.getText().toString();
                String senha = EditSenhaLogin.getText().toString();

                //verifica se os campos estão prenchidos
                if(login.isEmpty() ||senha.isEmpty()){
                    Toast.makeText(Login.this, "Campos Obrigatorios", Toast.LENGTH_LONG).show();
                }

                else{
                    //verifica quantos user exitem
                    int NumeroUsers =db.numeroDeUsers(login,senha);

                    //se tiver mais que um user, valida
                    if(NumeroUsers>0){
                        //valida o login e senha
                        Funcionario funcionario= db.ValidaFunc(login,senha);

                        int codFunc=funcionario.getIdFunc();

                        //salvando cod na memoria
                        editor.putInt("codFunc",codFunc);
                        editor.apply();

                        //mandando o cod para tela de conta e abrindo tela conta
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }
                    //se não tiver nme um user exibe mensagem
                    else{
                        Toast.makeText(Login.this, "Login ou Senha não existe", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}