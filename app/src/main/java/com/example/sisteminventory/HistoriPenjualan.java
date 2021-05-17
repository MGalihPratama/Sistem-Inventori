package com.example.sisteminventory;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoriPenjualan extends AppCompatActivity {
    ImageButton back;
    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    HisJualAdapter HisJualAdapter;
    ArrayList<String> id_jual, nama_barang1, harga_barang1, jual_barang, tanggal_jual;
    Button hbcari;
    EditText hbpetcari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histori_penjualan);
        back = findViewById(R.id.tombol_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
        hbcari = findViewById(R.id.hbbuttoncari);
        hbpetcari = findViewById(R.id.Ethbcari);

        recyclerView = findViewById(R.id.recyclerView);

        myDB = new MyDatabaseHelper(this);
        id_jual = new ArrayList<>();
        nama_barang1 = new ArrayList<>();
        harga_barang1 = new ArrayList<>();
        jual_barang = new ArrayList<>();
        tanggal_jual = new ArrayList<>();

        storeDataInArrays();

        HisJualAdapter = new HisJualAdapter(this,this, id_jual, nama_barang1, harga_barang1,
                jual_barang, tanggal_jual);
        recyclerView.setAdapter(HisJualAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        hbcari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HisJualAdapter.clear();
                id_jual.clear();
                nama_barang1.clear();
                harga_barang1.clear();
                jual_barang.clear();
                tanggal_jual.clear();
                searchdata(hbpetcari.getText().toString());
                HisJualAdapter = new HisJualAdapter(HistoriPenjualan.this,HistoriPenjualan.this, id_jual, nama_barang1, harga_barang1,
                        jual_barang, tanggal_jual);
                recyclerView.setAdapter(HisJualAdapter);
            }
        });

    }

    void storeDataInArrays(){
        Cursor cursor = myDB.historydata();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Empty Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id_jual.add(cursor.getString(0));
                nama_barang1.add(cursor.getString(1));
                harga_barang1.add(cursor.getString(2));
                jual_barang.add(cursor.getString(3));
                tanggal_jual.add(cursor.getString(4));
            }
        }
    }

    void searchdata(String text){
        Cursor cursor = myDB.searchhistory(text);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Empty Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id_jual.add(cursor.getString(0));
                nama_barang1.add(cursor.getString(1));
                harga_barang1.add(cursor.getString(2));
                jual_barang.add(cursor.getString(3));
                tanggal_jual.add(cursor.getString(4));
            }
        }
    }
    public void back (){
        Intent intent = new Intent (this, Histori.class);
        startActivity(intent);
    }
}