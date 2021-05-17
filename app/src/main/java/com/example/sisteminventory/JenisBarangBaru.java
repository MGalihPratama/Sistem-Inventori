package com.example.sisteminventory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JenisBarangBaru extends AppCompatActivity {
    EditText nama_barang, harga_barang, etstok;
    TextView stok_barang;
    Button tambah, edit, delete,tambahstok;
    TextView title;
    String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_barang_baru);
        Bundle extras = this.getIntent().getExtras();


        nama_barang = findViewById(R.id.nama_barang);
        harga_barang = findViewById(R.id.harga);
        stok_barang = findViewById(R.id.stok);
        edit = findViewById(R.id.edit);
        delete = findViewById(R.id.delete);
        title = findViewById(R.id.textView);
        etstok = findViewById(R.id.etstok);
        tambah = findViewById(R.id.tambah);
        tambahstok = findViewById(R.id.tambahstok);


        if (extras != null){
            id = extras.get("id_barang").toString();
            title.setText("Ubah Barang");
            nama_barang.setText(extras.get("nama_barang").toString());
            harga_barang.setText(extras.get("harga_barang").toString());
            stok_barang.setText(extras.get("stok_barang").toString());
            tambah.setVisibility(View.GONE);
            edit.setVisibility(View.VISIBLE);
            stok_barang.setVisibility(View.VISIBLE);
            etstok.setVisibility(View.GONE);
            tambahstok.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
        }

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(JenisBarangBaru.this);
                myDB.additems(nama_barang.getText().toString().trim(),
                        Integer.valueOf(harga_barang.getText().toString().trim()),
                        Integer.valueOf(etstok.getText().toString().trim()));
                Dashboard();
            }

        });

        tambahstok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TambahStok.class);
                intent.putExtra("id_barang", id);
                intent.putExtra("nama_barang", nama_barang.getText().toString());
                intent.putExtra("harga_barang", harga_barang.getText().toString());
                intent.putExtra("stok_barang", stok_barang.getText().toString());
                startActivity(intent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(JenisBarangBaru.this);
                myDB.updateBarang(
                        id,
                        nama_barang.getText().toString().trim(),
                        Integer.valueOf(harga_barang.getText().toString().trim()),
                        Integer.valueOf(stok_barang.getText().toString().trim()));
                Dashboard();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialog();
            }
        });
    }
    public void Dashboard(){
        Intent intent = new Intent(this, KelolaBarang.class );
        startActivity(intent);
    }
    public void opendialog(){
        dialog Dialog = new dialog();
        Dialog.id = id.toString();
        Dialog.show(getSupportFragmentManager(),"dialog");
    }
}