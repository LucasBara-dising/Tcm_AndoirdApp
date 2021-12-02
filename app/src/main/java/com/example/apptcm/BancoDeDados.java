package com.example.apptcm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper {

    //Variaveis de nome de tabela e campos
    //servicos
    public static final String Tabela_servico = "TbServicos";
    public static final String Coluna_CodServ = "Codserv";
    public static final String Coluna_NomeEmpresa = "nomeEmpresa";
    public static final String Coluna_TituloServ = "tituloServ";
    public static final String Coluna_Prazo = "Prazo";
    public static final String Coluna_DescServ = "DescServ";
    public static final String Coluna_AreaServ = "areaServ";
    public static final String Coluna_NivelConclusao = "nivelConclusao";

    //funcionario
    public static final String Tabela_Funcionario = "TbFuncioario";
    public static final String Coluna_idFunc = "idFunc";
    public static final String Coluna_EmailFunc = "EmailFunc";
    public static final String Coluna_SenhaFunc = "SenhaFunc";
    public static final String Coluna_NomeFunc = "NomeFunc";
    public static final String Coluna_CargoFunc = "CargoFunc";

    private static final String DATABASE_Nome = "BdTcmApp.db";
    private static final int DATABASE_VERSION = 1;


    public BancoDeDados(Context context) {
        super(context, DATABASE_Nome, null, DATABASE_VERSION);
    }

    //criação de tabelas
    @Override
    public void onCreate(SQLiteDatabase db) {
        String Criacao_tabela = "create table " + Tabela_servico + "( "
                + Coluna_CodServ + " integer primary key autoincrement, "
                + Coluna_NomeEmpresa + " text not null,"
                + Coluna_TituloServ + " text not null,"
                + Coluna_Prazo + " Date not null,"
                + Coluna_DescServ + " text not null,"
                + Coluna_AreaServ + " text not null,"
                + Coluna_NivelConclusao + " text not null );";


        String Criacao_tabelaFunc ="create table " + Tabela_Funcionario + "( "
                + Coluna_idFunc + " integer primary key autoincrement, "
                + Coluna_EmailFunc + " text not null,"
                + Coluna_SenhaFunc + " text not null,"
                + Coluna_NomeFunc + " text not null,"
                + Coluna_CargoFunc + " text not null);";


        db.execSQL(Criacao_tabela);
        db.execSQL(Criacao_tabelaFunc);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //====================================== Serviços==========================
    //Insert serv
    void addserv(Servico servico){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(Coluna_NomeEmpresa, servico.getNomeEmpresa());
        values.put(Coluna_TituloServ, servico.getTituloServ());
        values.put(Coluna_Prazo, servico.getPrazo());
        values.put(Coluna_DescServ, servico.getDescServ());
        values.put(Coluna_AreaServ, servico.getAreaServ());
        values.put(Coluna_NivelConclusao, servico.getNivelConclusao());

        db.insert(Tabela_servico, null, values);
        db.close();
    }

    //delete
    void ApagaServ(Servico servico){
        SQLiteDatabase db=this.getWritableDatabase();

        db.delete(Tabela_servico, Coluna_CodServ+"= ?", new String[]{String.valueOf(servico.getCodeServ())});
        db.close();
    }

    //select
    Servico selecionarServico(int codigo){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(Tabela_servico,
                new String[]{Coluna_CodServ, Coluna_NomeEmpresa, Coluna_TituloServ, Coluna_Prazo, Coluna_DescServ, Coluna_AreaServ,Coluna_NivelConclusao},
                Coluna_CodServ+"=?",new String[]{String.valueOf(codigo)},null, null, null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }

        Servico Servico1= new Servico(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));

        return Servico1;
    }

    //update
    void updateServ(Servico servico){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values= new ContentValues();

        values.put(Coluna_NomeEmpresa, servico.getNomeEmpresa());
        values.put(Coluna_TituloServ, servico.getTituloServ());
        values.put(Coluna_Prazo, servico.getPrazo());
        values.put(Coluna_DescServ, servico.getDescServ());
        values.put(Coluna_AreaServ, servico.getAreaServ());
        values.put(Coluna_NivelConclusao, servico.getNivelConclusao());

        db.update(Tabela_servico, values, Coluna_CodServ + "= ?",
                new String[]{String.valueOf(servico.getCodeServ()) });
    }

    //lista Todos
    public List<Servico> ListaTodosServicos(){
        List<Servico>  ListaServicos= new ArrayList<Servico>();

        String query= "SELECT * FROM " + Tabela_servico;

        SQLiteDatabase db=this.getWritableDatabase();

        Cursor c =db.rawQuery(query,null);

        if(c.moveToFirst()){
            do{
                Servico servico= new Servico();
                servico.setCodeServ(Integer.parseInt(c.getString(0)));
                servico.setNomeEmpresa(c.getString(1));
                servico.setTituloServ(c.getString(2));
                servico.setPrazo(c.getString(3));
                servico.setDescServ(c.getString(4));
                servico.setAreaServ(c.getString(5));
                servico.setNivelConclusao(c.getString(6));

                ListaServicos.add(servico);

            }while (c.moveToNext());
        }
        return  ListaServicos;
    }



    //=================================Funcionario
    //Insert serv
    void addFunc(Funcionario funcionario){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(Coluna_EmailFunc, funcionario.getEmailFunc());
        values.put(Coluna_SenhaFunc, funcionario.getSenhaFunc());
        values.put(Coluna_NomeFunc, funcionario.getNomeFunc());
        values.put(Coluna_CargoFunc, funcionario.getCargoFunc());

        db.insert(Tabela_Funcionario, null, values);
        db.close();
    }

    //select
    Funcionario selecionarFunc(int codigoFunc){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(Tabela_Funcionario,
                new String[]{Coluna_idFunc, Coluna_EmailFunc, Coluna_SenhaFunc, Coluna_NomeFunc, Coluna_CargoFunc},
                Coluna_idFunc+"=?",new String[]{String.valueOf(codigoFunc)},null, null, null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }

        Funcionario funcionario1= new Funcionario(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                cursor.getString(3),cursor.getString(4));

        return funcionario1;
    }

    //update
    void updateServ(Funcionario funcionario){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values= new ContentValues();

        values.put(Coluna_EmailFunc, funcionario.getEmailFunc());
        values.put(Coluna_SenhaFunc, funcionario.getSenhaFunc());
        values.put(Coluna_NomeFunc, funcionario.getNomeFunc());
        values.put(Coluna_CargoFunc, funcionario.getCargoFunc());

        db.update(Tabela_Funcionario, values, Coluna_idFunc + "= ?",
                new String[]{String.valueOf(funcionario.getIdFunc()) });
    }


}
