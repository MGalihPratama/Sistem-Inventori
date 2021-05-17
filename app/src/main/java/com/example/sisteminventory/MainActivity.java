package com.example.sisteminventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton kelola_barang;
    private ImageButton penjualan;
    private ImageButton histori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kelola_barang =(ImageButton) findViewById(R.id.kelola_barang);
        penjualan =(ImageButton) findViewById(R.id.penjualan);
        histori =(ImageButton) findViewById(R.id.histori);

        kelola_barang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKelolaBarang();
            }
        });
        penjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPenjualan();
            }
        });
        histori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistori();
            }
        });
    }
    public void openKelolaBarang(){
        Intent intent = new Intent(this, KelolaBarang.class);
        startActivity(intent);
    }
    public void openPenjualan(){
        Intent intent = new Intent(this, Penjualan.class);
        startActivity(intent);
    }
    public void openHistori(){
        Intent intent = new Intent(this, Histori.class);
        startActivity(intent);
    }
}