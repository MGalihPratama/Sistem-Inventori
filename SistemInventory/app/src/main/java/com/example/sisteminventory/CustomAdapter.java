package com.example.sisteminventory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id_barang, nama_barang, harga_barang, stok_barang;

    CustomAdapter(Activity activity, Context context, ArrayList id_barang, ArrayList nama_barang, ArrayList harga_barang,
                  ArrayList stok_barang){
        this.activity = activity;
        this.context = context;
        this.id_barang = id_barang;
        this.nama_barang = nama_barang;
        this.harga_barang = harga_barang;
        this.stok_barang = stok_barang;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleview, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tv_id.setText(String.valueOf(id_barang.get(position)));
        holder.tv_nb.setText(String.valueOf(nama_barang.get(position)));
        holder.tv_hb.setText(String.valueOf(harga_barang.get(position)));
        holder.tv_sb.setText(String.valueOf(stok_barang.get(position)));
        //Recyclerview onClickListener
//        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, UpdateActivity.class);
//                intent.putExtra("id", String.valueOf(book_id.get(position)));
//                intent.putExtra("title", String.valueOf(book_title.get(position)));
//                intent.putExtra("author", String.valueOf(book_author.get(position)));
//                intent.putExtra("pages", String.valueOf(book_pages.get(position)));
//                activity.startActivityForResult(intent, 1);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return id_barang.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_id, tv_nb, tv_hb, tv_sb;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_nb = itemView.findViewById(R.id.tv_nb);
            tv_hb = itemView.findViewById(R.id.tv_hb);
            tv_sb = itemView.findViewById(R.id.tv_sb);
            mainLayout = itemView.findViewById(R.id.recycle_view);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
