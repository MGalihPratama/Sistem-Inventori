package com.example.sisteminventory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Jual extends AppCompatActivity {
    TextView nama_barang, harga_barang, stok_barang;
    Button jual;
    EditText stok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jual);

        Bundle extras = this.getIntent().getExtras();


        nama_barang = findViewById(R.id.curbarang);
        harga_barang = findViewById(R.id.curharga);
        stok_barang = findViewById(R.id.curstok);
        stok = findViewById(R.id.edtstok);
        jual = findViewById(R.id.jual);


        if (extras != null){
            nama_barang.setText("Nama Barang: " + extras.get("nama_barang").toString());
            harga_barang.setText("Harga Barang: " +extras.get("harga_barang").toString());
            stok_barang.setText("Stok Barang: " +extras.get("stok_barang").toString()+" - ");
        };
        jual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.valueOf(stok.getText().toString().trim()) <= Integer.valueOf(extras.get("stok_barang").toString())&&Integer.valueOf(stok.getText().toString().trim())>0){
                    MyDatabaseHelper myDB = new MyDatabaseHelper(Jual.this);
                    myDB.jualbarang(
                            extras.get("id_barang").toString(),
                            Integer.valueOf(stok.getText().toString().trim()),
                            Integer.valueOf(extras.get("stok_barang").toString()));
                    myDB.addhistory(
                            extras.get("nama_barang").toString(),
                            Integer.valueOf(extras.get("harga_barang").toString()),
                            Integer.valueOf(stok.getText().toString().trim()));
                    penjualan();
                }else{
                    Toast.makeText(Jual.this, "Stok Barang Kurang atau kurang dari sama dengan 0", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void penjualan(){
        Intent intent = new Intent(this, Penjualan.class );
        startActivity(intent);
    }
}