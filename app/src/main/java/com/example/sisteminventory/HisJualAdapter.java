package com.example.sisteminventory;

import android.app.Activity;
import android.content.Context;
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

public class HisJualAdapter extends RecyclerView.Adapter<HisJualAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id_barang, nama_barang, harga_barang, stok_barang, tanggal_jual;

    HisJualAdapter(Activity activity, Context context, ArrayList id_barang, ArrayList nama_barang, ArrayList harga_barang,
                     ArrayList stok_barang, ArrayList tanggal_jual){
        this.activity = activity;
        this.context = context;
        this.id_barang = id_barang;
        this.nama_barang = nama_barang;
        this.harga_barang = harga_barang;
        this.stok_barang = stok_barang;
        this.tanggal_jual = tanggal_jual;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_histori, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.his_jual_0.setText(String.valueOf(id_barang.get(position)));
        holder.his_jual_1.setText(String.valueOf(nama_barang.get(position)));
        holder.his_jual_2.setText(String.valueOf(harga_barang.get(position)));
        holder.his_jual_3.setText(String.valueOf(stok_barang.get(position)));
        holder.his_jual_4.setText(String.valueOf(tanggal_jual.get(position)));
//        Recyclerview onClickListener
//        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, Jual.class);
//                intent.putExtra("id_barang", String.valueOf(id_barang.get(position)));
//                intent.putExtra("nama_barang", String.valueOf(nama_barang.get(position)));
//                intent.putExtra("harga_barang", String.valueOf(harga_barang.get(position)));
//                intent.putExtra("stok_barang", String.valueOf(stok_barang.get(position)));
//                activity.startActivityForResult(intent, 1);
//            }
//        });
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

        TextView his_jual_0, his_jual_1, his_jual_2, his_jual_3, his_jual_4;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            his_jual_0 = itemView.findViewById(R.id.his_jual_0);
            his_jual_1 = itemView.findViewById(R.id.his_jual_1);
            his_jual_2 = itemView.findViewById(R.id.his_jual_2);
            his_jual_3 = itemView.findViewById(R.id.his_jual_3);
            his_jual_4 = itemView.findViewById(R.id.his_jual_4);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
//            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
//            mainLayout.setAnimation(translate_anim);
        }

    }

}