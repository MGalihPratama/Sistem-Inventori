package com.example.sisteminventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "db_pti.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME_1 = "barang";
    private static final String TB1_COLUMN1 = "id_barang";
    private static final String TB1_COLUMN2 = "nama_barang";
    private static final String TB1_COLUMN3 = "harga_barang";
    private static final String TB1_COLUMN4 = "stok_barang";

//    private static final String TABLE_NAME_2 = "pembelian";
//    private static final String TB2_COLUMN1 = "id_beli";
//    private static final String TB2_COLUMN2 = "id_barang";
//    private static final String TB2_COLUMN3 = "Nama_Barang";
//    private static final String TB2_COLUMN4 = "Jml_Beli";
//    private static final String TB2_COLUMN5 = "Harga_Beli";
//
//    private static final String TABLE_NAME_3 = "penjualan";
//    private static final String TB3_COLUMN1 = "id_jual";
//    private static final String TB3_COLUMN2 = "id_barang";
//    private static final String TB3_COLUMN3 = "Nama_Barang";
//    private static final String TB3_COLUMN4 = "Jml_jua;";
//    private static final String TB3_COLUMN5 = "Harga_jual";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE " + TABLE_NAME_1 +
                        " (" + TB1_COLUMN1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TB1_COLUMN2 + " TEXT, " +
                TB1_COLUMN3 + " TEXT, " +
                TB1_COLUMN4 + " INTEGER);";
        db.execSQL(query1);

//        String query2 = "CREATE TABLE " + TABLE_NAME_2 +
//                " (" + TB2_COLUMN1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                TB2_COLUMN2 + " INTEGER, " +
//                TB2_COLUMN3 + " TEXT, " +
//                TB2_COLUMN4 + " INTEGER, " +
//                TB2_COLUMN5 + " INTEGER);"+
//                "FOREIGN KEY ("+TB2_COLUMN2+") REFERENCES "+TABLE_NAME_1+"("+TB1_COLUMN1+"));";
//        db.execSQL(query2);
//
//        String query3 = "CREATE TABLE " + TABLE_NAME_3 +
//                " (" + TB3_COLUMN1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                TB3_COLUMN2 + " INTEGER, " +
//                TB3_COLUMN3 + " TEXT, " +
//                TB3_COLUMN4 + " INTEGER, " +
//                TB3_COLUMN5 + " INTEGER);";
//        db.execSQL(query3);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        onCreate(db);
    }

    void addBook(String nama_barang, String harga_barang, int stok_barang){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TB1_COLUMN2, nama_barang);
        cv.put(TB1_COLUMN3, harga_barang);
        cv.put(TB1_COLUMN4, stok_barang);
        long result = db.insert(TABLE_NAME_1,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME_1;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

//    void updateData(String row_id, String title, String author, String pages){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(COLUMN_TITLE, title);
//        cv.put(COLUMN_AUTHOR, author);
//        cv.put(COLUMN_PAGES, pages);
//
//        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
//        if(result == -1){
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
//        }
//
//    }

//    void deleteOneRow(String row_id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
//        if(result == -1){
//            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
//        }
//    }

//    void deleteAllData(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DELETE FROM " + TABLE_NAME);
//    }

}
