package com.example.sisteminventory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PenjualanAdapter extends RecyclerView.Adapter<PenjualanAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id_barang, nama_barang, harga_barang, stok_barang;

    PenjualanAdapter(Activity activity, Context context, ArrayList id_barang, ArrayList nama_barang, ArrayList harga_barang,
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
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tv_id_barang.setText(String.valueOf(id_barang.get(position)));
        holder.tv_nama_barang.setText(String.valueOf(nama_barang.get(position)));
        holder.tv_harga_barang.setText("Harga: " + String.valueOf(harga_barang.get(position)));
        holder.tv_stok_barang.setText("Stok: " + String.valueOf(stok_barang.get(position)));
//        Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Jual.class);
                intent.putExtra("id_barang", String.valueOf(id_barang.get(position)));
                intent.putExtra("nama_barang", String.valueOf(nama_barang.get(position)));
                intent.putExtra("harga_barang", String.valueOf(harga_barang.get(position)));
                intent.putExtra("stok_barang", String.valueOf(stok_barang.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id_barang.size();
    }

    public void clear() {
        int size = id_barang.size();
        id_barang.clear();
        notifyItemRangeRemoved(0, size);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_id_barang, tv_nama_barang, tv_harga_barang, tv_stok_barang;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id_barang = itemView.findViewById(R.id.tv_id_barang);
            tv_nama_barang = itemView.findViewById(R.id.tv_nama_barang);
            tv_harga_barang = itemView.findViewById(R.id.tv_harga_barang);
            tv_stok_barang = itemView.findViewById(R.id.tv_stok_barang);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
//            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
//            mainLayout.setAnimation(translate_anim);
        }

    }

}
