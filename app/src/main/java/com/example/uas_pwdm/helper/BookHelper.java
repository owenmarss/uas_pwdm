package com.example.uas_pwdm.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;


public class BookHelper extends SQLiteOpenHelper {
    private static final int  DATABASE_VERSION = 1;
    static  final String DATABASE_NAME = "uaspwdm";
    public BookHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "CREATE TABLE books (id INTEGER PRIMARY KEY AUTOINCREMENT, judul TEXT, penulis TEXT, tahun TEXT);";
        db.execSQL(SQL_CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS books");
        onCreate(db);
    }

    //QUERY untuk SELECT ALL
    public ArrayList<HashMap<String,String>> getAllBooks(){
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM books";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("judul", cursor.getString(1));
                map.put("penulis", cursor.getString(2));
                map.put("tahun", cursor.getString(3));
                list.add(map);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    // QUERY untuk INSERT
    public void insert(String judul, String penulis, String tahun){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "INSERT INTO books (judul, penulis, tahun) VALUES ('"+judul+"', '"+penulis+"', "+tahun+");";
        db.execSQL(QUERY);
    }

    // QUERY untuk UPDATE
    public void update(int id, String judul, String penulis, String tahun){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "UPDATE books SET judul = '"+judul+"', penulis = '"+penulis+"', tahun = "+tahun+" WHERE id = "+id+";";
        db.execSQL(QUERY);
    }

    //QUERY untuk DELETE
    public void delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "DELETE FROM books WHERE id = "+id;
        db.execSQL(QUERY);
    }

}
