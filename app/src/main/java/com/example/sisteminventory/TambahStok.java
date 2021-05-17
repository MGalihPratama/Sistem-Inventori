package com.example.sisteminventory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TambahStok extends AppCompatActivity {
    TextView nama_barang, harga_barang, stok_barang;
    MyDatabaseHelper myDb;
    Button tmbhstok;
    EditText stok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_stok);

        Bundle extras = this.getIntent().getExtras();

        nama_barang = findViewById(R.id.tvnamabarang);
        harga_barang = findViewById(R.id.tvhargabarang);
        stok_barang = findViewById(R.id.tvstokbarang);
        stok = findViewById(R.id.etstokbarang);
        tmbhstok = findViewById(R.id.btstoktambah);

        if (extras != null){
            nama_barang.setText("Nama Barang: " + extras.get("nama_barang"));
            harga_barang.setText("Harga Barang: " +extras.get("harga_barang"));
            stok_barang.setText("Stok Barang: " +extras.get("stok_barang")+" + ");
        };

        tmbhstok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.valueOf(stok.getText().toString().trim()) > 0) {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(TambahStok.this);
                    myDB.belibarang(
                            extras.get("id_barang").toString(),
                            Integer.valueOf(stok.getText().toString().trim()),
                            Integer.valueOf(extras.get("stok_barang").toString()));
                    myDB.sellhistory(
                            extras.get("nama_barang").toString().trim(),
                            Integer.valueOf(extras.get("harga_barang").toString().trim()),
                            Integer.valueOf(stok.getText().toString().trim()));
                    dashboard();
                }else{
                    Toast.makeText(TambahStok.this, "Stok yang ditambah tidak boleh sama dengan 0", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void dashboard(){
        Intent intent = new Intent(this, KelolaBarang.class );
        startActivity(intent);
    }
}