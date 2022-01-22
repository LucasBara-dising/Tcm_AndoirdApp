package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

public class AtualizaServico extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText editNomeEmpresa, editTitulo, editTextDate, editDescserv, editTecUsda;
    Button btnAtualiza;

    BancoDeDados db=new BancoDeDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualiza_servico);

        //dropdown nivel conclusão
        Spinner dropdownNivel=(Spinner)findViewById(R.id.dropdownNivel);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ItensDropdown));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownNivel.setAdapter(adapter);


        editNomeEmpresa= (EditText)findViewById(R.id.editNomeEmpresa);
        editTitulo=(EditText)findViewById(R.id.editTitulo);
        editTextDate=(EditText)findViewById(R.id.editTextDate);
        editDescserv=(EditText)findViewById(R.id.editDescserv);
        editTecUsda=(EditText)findViewById(R.id.editTecUsda);


        //cod projeto
        Intent intent = getIntent();
        int CodigoServ = intent.getIntExtra("codServ",0);

        Servico servico= db.selecionarServico(CodigoServ);

        //puxa os valores do banco e coloca nos campos
        editNomeEmpresa.setText(servico.getNomeEmpresa());
        editTitulo.setText(servico.getTituloServ());
        editTextDate.setText(servico.getPrazo());
        editDescserv.setText(servico.getDescServ());
        editTecUsda.setText(servico.getAreaServ());

        btnAtualiza= (Button)findViewById(R.id.btnAtualiza);
        btnAtualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recebe os dados
                String nomeEmpresa= editNomeEmpresa.getText().toString();
                String titulo=editTitulo.getText().toString();
                String prazo=editTextDate.getText().toString();
                String descricao=editDescserv.getText().toString();
                String areaTrab=editTecUsda.getText().toString();
                String NivelConclusao=dropdownNivel.getSelectedItem().toString();

                if(areaTrab.equals("Html Css") || areaTrab.equals("PHP") || areaTrab.equals("Asp.net")|| areaTrab.equals("Android/Ios")|| areaTrab.equals("Servidor")||
                        areaTrab.equals("Java")){
                    //update
                    db.updateServ(new Servico(Integer.parseInt(String.valueOf(CodigoServ)),nomeEmpresa, titulo,prazo, descricao,areaTrab,NivelConclusao));
                    //mensagem de sucesso
                    Toast.makeText(AtualizaServico.this, "Serviço Atualizado ", Toast.LENGTH_LONG).show();
                    Intent Lista = new Intent(getApplicationContext(), ListaServ.class);
                    startActivity(Lista);
                }
                else{
                    Toast.makeText(AtualizaServico.this, "A area do projeto esta errada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView btnFechaAdd= (ImageView)findViewById(R.id.btnFechaAdd);
        btnFechaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Lista = new Intent(getApplicationContext(), ListaServ.class);
                startActivity(Lista);
            }
        });

        ImageView imgCalender = (ImageView) findViewById(R.id.imgCalender);
        imgCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostraCalendario();
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