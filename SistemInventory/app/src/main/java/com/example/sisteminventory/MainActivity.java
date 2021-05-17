package com.example.sisteminventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private Button kelola_barang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kelola_barang =(Button) findViewById(R.id.kelola_barang);
        kelola_barang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKelolaBarang();
            }
        });
    }
    public void openKelolaBarang(){
        Intent intent = new Intent(this, KelolaBarang.class);
        startActivity(intent);
    }
}