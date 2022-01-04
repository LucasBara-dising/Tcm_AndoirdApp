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

public class Filtros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);

        //dropdown nivel conclusão
        Spinner dropdownNivel=(Spinner)findViewById(R.id.filtroNivelConclusao);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ItensDropdown));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownNivel.setAdapter(adapter);

        //dropdown tecnologia usada
        Spinner filtroTecUsada=(Spinner)findViewById(R.id.filtroTecUsada);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.TecUsadas));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filtroTecUsada.setAdapter(adapter1);

        EditText editPalavraChave=(EditText)findViewById(R.id.editPalavraChave);
        String PlvChave = editPalavraChave.getText().toString();

        ImageView btnFechaAdd= (ImageView)findViewById(R.id.btnFechaAdd);
        btnFechaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Lista = new Intent(getApplicationContext(), ListaServ.class);
                startActivity(Lista);
            }
        });

        Button btnFiltra= (Button)findViewById(R.id.btnFiltra);
        btnFiltra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ativa=1;
                String areaTrab=filtroTecUsada.getSelectedItem().toString();
                String NivelConclusao= dropdownNivel.getSelectedItem().toString();
                String PlvChave = editPalavraChave.getText().toString();

                Intent Lista = new Intent(getApplicationContext(), ListaServ.class);
                Lista.putExtra("areaTrab",areaTrab);
                Lista.putExtra("NivelConclusao",NivelConclusao);
                Lista.putExtra("PlvChave",PlvChave);
                Lista.putExtra("ativa",ativa);

                startActivity(Lista);
            }
        });
    }
}