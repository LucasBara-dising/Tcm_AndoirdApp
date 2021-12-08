package com.example.apptcm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class AddFunc extends AppCompatActivity {

    EditText editEmailFunc, editNomeFunc, editSenhaFunc,editCargoFunc, editTelFunc;

    BancoDeDados db=new BancoDeDados(this);

    //picker
    private static final int CONTACT_PERMISSION_CODE = 1;
    private static final int CONTACT_PICK_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_func);

        editEmailFunc= (EditText)findViewById(R.id.editEmailFunc);
        editNomeFunc=(EditText)findViewById(R.id.editNomeFunc);
        editSenhaFunc=(EditText)findViewById(R.id.editSenhaFunc);
        editTelFunc = (EditText) findViewById(R.id.editTelFunc);

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

        ImageView imgBtnListaContatos = (ImageView) findViewById(R.id.imgBtnListaContatos);
        imgBtnListaContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkContactPermission()){
                    //se permisão foi dada ablidat para pegar contato
                    pickContactIntent();
                }
                else {
                    //se não tiver permissão pede
                    requestContactPermission();
                }
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


                if(EmailFunc.isEmpty() || NomeFunc.isEmpty() || SenhaFunc.isEmpty() || CargoFunc.isEmpty()){
                    //mensagem de erro se algum campo não entiver  preenchido
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

//==========================picker contato
    //checa permissao
    private boolean checkContactPermission(){
        //verifica se tem permisão
        boolean result = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS) == (PackageManager.PERMISSION_GRANTED
        );

        return result;
    }

    //pede permissao
    private void requestContactPermission(){
        String[] permission = {Manifest.permission.READ_CONTACTS};
        ActivityCompat.requestPermissions(this, permission, CONTACT_PERMISSION_CODE);
    }

    //pega o contato
    private void pickContactIntent(){
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, CONTACT_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //verifica o resultado da permissão
        if (requestCode == CONTACT_PERMISSION_CODE){
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //permite escolher o contato
                pickContactIntent();
            }
            else {
                //fala que precisa de permissão
                Toast.makeText(this, "Permissão Nessaria", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == CONTACT_PICK_CODE) {
                Cursor cursor1, cursor2;
                //recebe os dados
                Uri uri = data.getData();
                cursor1 = getContentResolver().query(uri, null, null, null, null);

                if (cursor1.moveToFirst()) {
                    //get contact details
                    @SuppressLint("Range") String contactId = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts._ID));
                    @SuppressLint("Range") String idResults = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    int idResultHold = Integer.parseInt(idResults);

                    if (idResultHold == 1) {
                        cursor2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                                null,
                                null
                        );
                        while (cursor2.moveToNext()) {
                            //recebe numero
                            @SuppressLint("Range") String contactNumber = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            //add a edittext o numero
                            EditText editTelFunc = (EditText) findViewById(R.id.editTelFunc);
                            editTelFunc.setText(contactNumber);
                        }
                        cursor2.close();
                    }
                    cursor1.close();
                }
            }
        }

    }

    public void TelaConta(){
        Intent Conta = new Intent(getApplicationContext(), ContaFunc.class);
        startActivity(Conta);
        finish();
    }
}