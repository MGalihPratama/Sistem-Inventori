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

public class KelolaBarang extends AppCompatActivity {
    private ImageButton jenis_barang_baru, tombol_home;
    private EditText cari;
    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ArrayList<String> id_barang, nama_barang, harga_barang, stok_barang;
    CustomAdapter customAdapter;
    Button bcari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_barang);
        cari = (EditText) findViewById(R.id.et_cari);
        jenis_barang_baru = (ImageButton) findViewById(R.id.jenis_barang_baru);
        tombol_home = (ImageButton) findViewById(R.id.tombol_home);
        recyclerView = findViewById(R.id.recyclerView);
        bcari = findViewById(R.id.bcari);

        tombol_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });

        jenis_barang_baru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJenisBarangBaru();
            }
        });

        bcari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customAdapter.clear();
                id_barang.clear();
                nama_barang.clear();
                harga_barang.clear();
                stok_barang.clear();
                searchdata(cari.getText().toString());
                customAdapter = new CustomAdapter(KelolaBarang.this,KelolaBarang.this, id_barang, nama_barang, harga_barang,
                        stok_barang);
                recyclerView.setAdapter(customAdapter);
            }
        });

        myDB = new MyDatabaseHelper(this);
        id_barang = new ArrayList<>();
        nama_barang = new ArrayList<>();
        harga_barang = new ArrayList<>();
        stok_barang = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(this,this, id_barang, nama_barang, harga_barang,
                stok_barang);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
//    private void filter (String text){
//        ArrayList<String> filteredList = new ArrayList<>();
//
//        for (String item : nama_barang){
//            if (item.contains(text.toLowerCase())){
//                filteredList.add(item);
//            }
//        }
//        customAdapter.filterList(filteredList);
//    }

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

    public void openJenisBarangBaru(){
        Intent intent = new Intent(this, JenisBarangBaru.class );
        startActivity(intent);
    }
}