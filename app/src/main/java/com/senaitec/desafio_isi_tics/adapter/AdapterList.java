package com.senaitec.desafio_isi_tics.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.senaitec.desafio_isi_tics.R;
import com.senaitec.desafio_isi_tics.activity.DetalhesActivity;
import com.senaitec.desafio_isi_tics.model.Usuario;
import com.squareup.picasso.Picasso;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterList extends RecyclerView.Adapter<AdapterList.MyViewHolder>{

    public static final String KEY_NOME = "nome";
    public  static final String KEY_EMAIL = "email";
    public static final String KEY_IMAGEM = "imagem";
    public static final String KEY_ENDERECO = "endereco";
    public static final String KEY_TELEFONE = "telefone";
    public static final String KEY_DATANASC = "dataNascimento";
    public static final String KEY_SOBRENOME = "sobrenome";
    public static final String KEY_CIDADE = "cidade";
    public static final String KEY_ESTADO = "estado";
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";
    private List<Usuario> listaUsuario;
    private Context context;


    public AdapterList(List<Usuario>listaUsuario, Context context){
        this.listaUsuario = listaUsuario;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pesquisa_usuario, parent, false);

        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        Usuario usuario =  listaUsuario.get(position);

        holder.nome.setText(usuario.getNome());
        holder.email.setText(usuario.getEmail());


        if(usuario.getFoto()!= null) {
            Picasso.with(context)
                    .load(usuario.getFoto())
                    .into(holder.imagem);
        }
        else{
            holder.imagem.setImageResource(R.drawable.avatar);
        }


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario1 = listaUsuario.get(position);
                Intent intent = new Intent(view.getContext(), DetalhesActivity.class);
                intent.putExtra(KEY_NOME, usuario1.getNome());
                intent.putExtra(KEY_EMAIL, usuario1.getEmail());
                intent.putExtra(KEY_IMAGEM, usuario1.getFoto());
                intent.putExtra(KEY_SOBRENOME, usuario1.getSobrenome());
                intent.putExtra(KEY_CIDADE, usuario1.getCidade());
                intent.putExtra(KEY_DATANASC, usuario1.getNascimento());
                intent.putExtra(KEY_ENDERECO, usuario1.getEndereco());
                intent.putExtra(KEY_ESTADO, usuario1.getEstado());
                intent.putExtra(KEY_TELEFONE, usuario1.getTelefone());
                intent.putExtra(KEY_LATITUDE, usuario1.getLatitude());
                intent.putExtra(KEY_LONGITUDE, usuario1.getLongitude());

                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView nome;
        public TextView email;
        public CircleImageView imagem;
        public LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textNome);
            email = itemView.findViewById(R.id.textEmail);
            imagem = itemView.findViewById(R.id.imageUsuario);
            linearLayout = itemView.findViewById(R.id.linearLayoutUsuario);

        }
    }
}
