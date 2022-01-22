package com.example.apptcm;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/*public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
//============================== TENTATIVA DE COLOCAR RECYCLER==============================
    private Context context;
    //private ArrayList  projetoId, projetoTitulo, ProjetoEmpresa, tecUsada;
    private List<Servico> servicos;


    public RecyclerViewAdapter(Context context) {
        BancoDeDados db = new BancoDeDados(context);
        List<Servico> ListaTodosServicos = db.ListaTodosServicos();
    }

//    RecyclerViewAdapter(Context context, ArrayList  projetoId, ArrayList  projetoTitulo, ArrayList ProjetoEmpresa,ArrayList tecUsada){
//        this.context=context;
//
//        this.projetoId=projetoId;
//        this.projetoTitulo=projetoTitulo;
//        this.ProjetoEmpresa=ProjetoEmpresa;
//        this.tecUsada=tecUsada;
//    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_recycler,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.projetoId.setText(String.valueOf(projetoId.get(position)));
        holder.projetoTitulo.setText(String.valueOf(projetoTitulo.get(position)));
        holder.ProjetoEmpresa.setText(String.valueOf(ProjetoEmpresa.get(position)));
        holder.tecUsada.setText(String.valueOf(tecUsada.get(position)));

        holder.projetoId.setText(servicos.get(position).getCodeServ());
        holder.projetoTitulo.setText(servicos.get(position).getTituloServ());
        holder.ProjetoEmpresa.setText(servicos.get(position).getNomeEmpresa());
        holder.tecUsada.setText(servicos.get(position).getAreaServ());
    }

    @Override
    public int getItemCount() {return projetoId.size();}

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView projetoId, projetoTitulo, ProjetoEmpresa, tecUsada;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            projetoId=itemView.findViewById(R.id.txtprojetoid);
            projetoTitulo=itemView.findViewById(R.id.txtTitulo);
            ProjetoEmpresa=itemView.findViewById(R.id.txtNomeEmpresa);
            tecUsada=itemView.findViewById(R.id.TtxTecUsada);

        }
    }
}*/


