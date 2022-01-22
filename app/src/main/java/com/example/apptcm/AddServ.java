package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class AddServ extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText editNomeEmpresa, editTitulo, editTextDate, editDescserv,editAreaserv;
    BancoDeDados db=new BancoDeDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_serv);

        ImageView imgCalender = (ImageView) findViewById(R.id.imgCalender);
        imgCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostraCalendario();
            }
        });


        editNomeEmpresa= (EditText)findViewById(R.id.editNomeEmpresa);
        editTitulo=(EditText)findViewById(R.id.editTitulo);
        editTextDate=(EditText)findViewById(R.id.editTextDate);
        editDescserv=(EditText)findViewById(R.id.editDescserv);

        //Dropdown
        Spinner dropdownTecUsada=(Spinner) findViewById(R.id.dropdownTecUsada);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.TecUsadas));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownTecUsada.setAdapter(adapter);


        ImageView btnFechaAdd = (ImageView) findViewById(R.id.btnFechaAdd);
        btnFechaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ListServ = new Intent(getApplicationContext(), ListaServ.class);
                startActivity(ListServ);
            }
        });

        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recebe os valores dos campos
                String nomeEmpresa= editNomeEmpresa.getText().toString();
                String titulo=editTitulo.getText().toString();
                String prazo=editTextDate.getText().toString();
                String descricao=editDescserv.getText().toString();
                String areaTrab=dropdownTecUsada.getSelectedItem().toString();


                //se tiver algum vazio mostrra erro
                if(nomeEmpresa.isEmpty() || descricao.isEmpty() || prazo.isEmpty() || titulo.isEmpty()|| areaTrab.isEmpty()){
                    //mensagem de erro
                   Toast.makeText(AddServ.this, "Os campos são Obrigatoiros",Toast.LENGTH_LONG).show();

                }else {
                    //insert
                    db.addserv(new Servico(nomeEmpresa, titulo, prazo, descricao, areaTrab, "Não iniciado"));
                    //mensagem de sucesso
                    Toast.makeText(AddServ.this, "Serviço adicionado ", Toast.LENGTH_LONG).show();

                    Intent ListServ = new Intent(getApplicationContext(), ListaServ.class);
                    startActivity(ListServ);
                }
            }
        });
    }

    //calendaer
    private void MostraCalendario(){
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
        }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month++;
        String date=year + "/" + month + "/" +dayOfMonth;
        editTextDate.setText(date);
    }
}