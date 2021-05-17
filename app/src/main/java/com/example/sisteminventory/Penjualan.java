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

public class Penjualan extends AppCompatActivity {
    ImageButton tombol_home;
    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    PenjualanAdapter penjualanadapter;
    ArrayList<String> id_barang, nama_barang, harga_barang, stok_barang;
    Button pbcari;
    EditText petcari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan);
        tombol_home = (ImageButton) findViewById(R.id.tombol_home);
        pbcari = findViewById(R.id.pbcari);
        petcari = findViewById(R.id.petcari);

        recyclerView = findViewById(R.id.recyclerView);

        myDB = new MyDatabaseHelper(this);
        id_barang = new ArrayList<>();
        nama_barang = new ArrayList<>();
        harga_barang = new ArrayList<>();
        stok_barang = new ArrayList<>();
        tombol_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });
        storeDataInArrays();

        penjualanadapter = new PenjualanAdapter(this,this, id_barang, nama_barang, harga_barang,
                stok_barang);
        recyclerView.setAdapter(penjualanadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pbcari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                penjualanadapter.clear();
                id_barang.clear();
                nama_barang.clear();
                harga_barang.clear();
                stok_barang.clear();
                searchdata(petcari.getText().toString());
                penjualanadapter = new PenjualanAdapter(Penjualan.this,Penjualan.this, id_barang, nama_barang, harga_barang,
                        stok_barang);
                recyclerView.setAdapter(penjualanadapter);
            }
        });

    }
    void openHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Empty Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id_barang.add(cursor.getString(0));
                nama_barang.add(cursor.getString(1));
                harga_barang.add(cursor.getString(2));
                stok_barang.add(cursor.getString(3));
            }
        }
    }

    void searchdata(String text){
        Cursor cursor = myDB.search(text);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Empty Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id_barang.add(cursor.getString(0));
                nama_barang.add(cursor.getString(1));
                harga_barang.add(cursor.getString(2));
                stok_barang.add(cursor.getString(3));
            }
        }
    }
}