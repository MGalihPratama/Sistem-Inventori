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

public class HistoriPembelian extends AppCompatActivity {

    ImageButton back;
    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    HisbeliAdapter HisbeliAdapter;
    ArrayList<String> id_beli, nama_barang2, harga_barang2, beli_barang, tanggal_beli;
    Button belibuttoncari;
    EditText belibcari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histori_pembelian);
        back = findViewById(R.id.tombol_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
        belibuttoncari = findViewById(R.id.belibuttoncari);
        belibcari = findViewById(R.id.belibcari);

        recyclerView = findViewById(R.id.recyclerView);

        myDB = new MyDatabaseHelper(this);
        id_beli = new ArrayList<>();
        nama_barang2 = new ArrayList<>();
        harga_barang2 = new ArrayList<>();
        beli_barang = new ArrayList<>();
        tanggal_beli = new ArrayList<>();

        storeDataInArrays();

        HisbeliAdapter = new HisbeliAdapter(this,this, id_beli, nama_barang2, harga_barang2,
                beli_barang, tanggal_beli);
        recyclerView.setAdapter(HisbeliAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        belibuttoncari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HisbeliAdapter.clear();
                id_beli.clear();
                nama_barang2.clear();
                harga_barang2.clear();
                beli_barang.clear();
                tanggal_beli.clear();
                searchdata(belibcari.getText().toString());
                HisbeliAdapter = new HisbeliAdapter(HistoriPembelian.this,HistoriPembelian.this, id_beli, nama_barang2, harga_barang2,
                        beli_barang, tanggal_beli);
                recyclerView.setAdapter(HisbeliAdapter);
            }
        });

    }

    void storeDataInArrays(){
        Cursor cursor = myDB.history();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Empty Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id_beli.add(cursor.getString(0));
                nama_barang2.add(cursor.getString(1));
                harga_barang2.add(cursor.getString(2));
                beli_barang.add(cursor.getString(3));
                tanggal_beli.add(cursor.getString(4));
            }
        }
    }

    void searchdata(String text){
        Cursor cursor = myDB.searchhistory(text);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Empty Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id_beli.add(cursor.getString(0));
                nama_barang2.add(cursor.getString(1));
                harga_barang2.add(cursor.getString(2));
                beli_barang.add(cursor.getString(3));
                tanggal_beli.add(cursor.getString(4));
            }
        }
    }
    public void back (){
        Intent intent = new Intent (this, Histori.class);
        startActivity(intent);
    }
}
