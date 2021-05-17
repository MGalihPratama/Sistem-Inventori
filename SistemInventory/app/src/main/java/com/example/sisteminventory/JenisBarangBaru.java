package com.example.sisteminventory;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JenisBarangBaru extends AppCompatActivity {
    Button tambah;
    EditText nama_barang, stok, harga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_barang_baru);

        nama_barang = (EditText) findViewById(R.id.nama_barang);
        stok = (EditText) findViewById(R.id.stok);
        harga = (EditText) findViewById(R.id.harga);
        tambah = (Button) findViewById(R.id.tambah);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(JenisBarangBaru.this);
                myDB.addBook(nama_barang.getText().toString().trim(),
                        harga.getText().toString().trim(),
                        Integer.valueOf(stok.getText().toString().trim()));
            }
        });
    }
}