package com.example.apptcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

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

        String areaTrab= String.valueOf(filtroTecUsada.getSelectedItem());
        String NivelConclusao= String.valueOf(dropdownNivel.getSelectedItem());

        //recebe dados cod func
        Intent intent1 = getIntent();
        int codFunc = intent1.getIntExtra("codFunc",0);


        ImageView btnFechaAdd= (ImageView)findViewById(R.id.btnFechaAdd);
        btnFechaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Lista = new Intent(getApplicationContext(), ListaServ.class);
                Lista.putExtra("codFunc",codFunc);
                startActivity(Lista);
            }
        });

        Button btnFiltra= (Button)findViewById(R.id.btnFiltra);
        btnFiltra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Lista = new Intent(getApplicationContext(), ListaServ.class);
                Lista.putExtra("codFunc",codFunc);
                Lista.putExtra("areaTrab",areaTrab);
                Lista.putExtra("NivelConclusao",NivelConclusao);
                startActivity(Lista);
            }
        });
    }
}