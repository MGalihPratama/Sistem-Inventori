package com.example.sisteminventory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class KelolaBarang extends AppCompatActivity {
    RecyclerView recyclerView;
    Button jenis_barang_baru;
    MyDatabaseHelper myDB;
    ArrayList<String> id_barang, nama_barang, harga_barang, stok_barang;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_barang);
        jenis_barang_baru = (Button) findViewById(R.id.jenis_barang_baru);
        jenis_barang_baru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJenisBarangBaru();
            }
        });

        myDB = new MyDatabaseHelper(KelolaBarang.this);
        id_barang = new ArrayList<>();
        nama_barang = new ArrayList<>();
        harga_barang = new ArrayList<>();
        stok_barang = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(KelolaBarang.this,this, id_barang, nama_barang, harga_barang,
                stok_barang);
//        recyclerView.setAdapter(customAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(KelolaBarang.this));
    }
    public void openJenisBarangBaru(){
        Intent intent = new Intent(this, JenisBarangBaru.class );
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id_barang.add(cursor.getString(0));
                nama_barang.add(cursor.getString(1));
                harga_barang.add(cursor.getString(2));
                stok_barang.add(cursor.getString(3));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

//    void confirmDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Delete All?");
//        builder.setMessage("Are you sure you want to delete all Data?");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
//                myDB.deleteAllData();
//                //Refresh Activity
//                Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//        builder.create().show();
//    }
}