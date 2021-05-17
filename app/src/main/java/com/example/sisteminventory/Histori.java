package com.example.sisteminventory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Histori extends AppCompatActivity {
    private ImageButton histori_penjualan;
    private ImageButton histori_pembelian;
    private TextView pendapatankotor;
    ImageButton tombol_home;
    Integer total;
    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histori);
        histori_penjualan = (ImageButton) findViewById(R.id.histori_penjualan);
        histori_pembelian = (ImageButton) findViewById(R.id.histori_pembelian);
        pendapatankotor =  findViewById(R.id.pendapatan_kotor);
        tombol_home = (ImageButton) findViewById(R.id.tombol_home);
        myDB = new MyDatabaseHelper(this);
        int total = myDB.totalkotor();

        pendapatankotor.setText(""+total);
        tombol_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });

        histori_penjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistoriPenjualan();
            }
        });
        histori_pembelian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistoriPembelian();
            }
        });
    }
    void openHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openHistoriPenjualan(){
        Intent intent = new Intent (this, HistoriPenjualan.class);
        startActivity(intent);
    }
    public void openHistoriPembelian(){
        Intent intent = new Intent (this, HistoriPembelian.class);
        startActivity(intent);
    }
}
