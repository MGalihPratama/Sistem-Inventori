package com.example.sisteminventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "db_pti.db";
    private static final int DATABASE_VERSION = 1;

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + "barang" +
                        " (" + "id_barang" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nama_barang" + " TEXT, " +
                "harga_barang" + " INTEGER, " +
                "stok_barang" + " INTEGER);";
        db.execSQL(query);
        String query1 = "CREATE TABLE " + "jual" +
                " (" + "id_jual" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nama_barang1" + " TEXT, " +
                "harga_barang1" + " INTEGER, " +
                "jual_barang" + " INTEGER, " +
                "tanggal_jual" + " TEXT);";
        db.execSQL(query1);
        String query2 = "CREATE TABLE " + "beli" +
                " (" + "id_beli" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nama_barang2" + " TEXT, " +
                "harga_barang2" + " INTEGER, " +
                "beli_barang" + " INTEGER, " +
                "tanggal_beli" + " TEXT);";
        db.execSQL(query2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "barang");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + "jual");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + "beli");
        onCreate(db);
    }

    void additems(String nb, int hb, int sb){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("nama_barang", nb);
        cv.put("harga_barang", hb);
        cv.put("stok_barang", sb);
        long result = db.insert("barang",null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void addhistory(String nb, int hb, int sb){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        cv.put("nama_barang1", nb);
        cv.put("harga_barang1", hb);
        cv.put("jual_barang", sb);
        cv.put("tanggal_jual", dateFormat.format(date));
        long result = db.insert("jual",null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    void sellhistory(String nb, int hb, int sb){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        cv.put("nama_barang2", nb);
        cv.put("harga_barang2", hb);
        cv.put("beli_barang", sb);
        cv.put("tanggal_beli", dateFormat.format(date));
        long result = db.insert("beli",null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + "barang";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor historydata(){
        String query = "SELECT * FROM jual ORDER BY id_jual DESC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor history(){
        String query = "SELECT * FROM beli ORDER BY id_beli DESC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public int totalkotor() {
        int result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(harga_barang1*jual_barang) FROM jual", null);
        if (cursor.moveToFirst()) result = cursor.getInt(0);
        cursor.close();
        db.close();
        return result;
    }

    Cursor search(String cari){
        String query = "SELECT * FROM barang WHERE nama_barang LIKE '%"+cari+"%'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor searchhistory(String cari){
        String query = "SELECT * FROM jual WHERE nama_barang1 LIKE '%"+cari+"%'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateBarang(String row_id, String nb, Integer hb, Integer sb){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nama_barang", nb);
        cv.put("harga_barang", hb);
        cv.put("stok_barang", sb);

        long result = db.update("barang", cv, "id_barang=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void jualbarang(String row_id, Integer sb, Integer lastSb){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("stok_barang", lastSb - sb);

        long result = db.update("barang", cv, "id_barang=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
//            Toast.makeText(context, "Stok Barang: -"+sb.toString(), Toast.LENGTH_SHORT).show();
        }

    }
    void belibarang(String row_id, Integer sb, Integer lastSb){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("stok_barang", lastSb + sb);

        long result = db.update("barang", cv, "id_barang=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
//            Toast.makeText(context, "Stok Barang: -"+sb.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    void deletebarang(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("barang", "id_barang=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + "barang");
    }

}
