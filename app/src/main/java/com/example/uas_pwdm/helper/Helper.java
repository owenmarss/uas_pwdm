package com.example.uas_pwdm.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;


public class Helper extends SQLiteOpenHelper {
    private static final int  DATABASE_VERSION = 1;
    static  final String DATABASE_NAME = "uaspwdm";
    public Helper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // ini ko
        final String SQL_CREATE_TABLE_BOOK = "CREATE TABLE books (id INTEGER PRIMARY KEY AUTOINCREMENT, judul TEXT, penulis TEXT, tahun TEXT);";
        final String SQL_CREATE_TABLE_MEMBER = "CREATE TABLE members (id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, no_telp TEXT, email TEXT, alamat TEXT);";
        final String SQL_CREATE_TABLE_BORROW = "CREATE TABLE borrows (id INTEGER PRIMARY KEY AUTOINCREMENT, book_id INT, member_id INT, FOREIGN KEY (book_id) REFERENCES books(id), FOREIGN KEY (member_id) REFERENCES members(id));";

        db.execSQL(SQL_CREATE_TABLE_BOOK);
        db.execSQL(SQL_CREATE_TABLE_MEMBER);
        db.execSQL(SQL_CREATE_TABLE_BORROW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS books");
        db.execSQL("DROP TABLE IF EXISTS members");
        db.execSQL("DROP TABLE IF EXISTS borrows");
        onCreate(db);
    }

    //QUERY untuk BOOKS
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
            } while (cursor.moveToNext());
        }
        cursor.close();
        System.out.println("Masuk");
        System.out.println(list);
        return list;
    }

    // QUERY untuk INSERT BOOK
    public void insertBook(String judul, String penulis, String tahun){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "INSERT INTO books (judul, penulis, tahun) VALUES ('"+judul+"', '"+penulis+"', '"+tahun+"');";
        db.execSQL(QUERY);
    }

    // QUERY untuk UPDATE BOOK
    public void updateBook(int id, String judul, String penulis, String tahun){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "UPDATE books SET judul = '"+judul+"', penulis = '"+penulis+"', tahun = "+tahun+" WHERE id = "+id+";";
        db.execSQL(QUERY);
    }

    //QUERY untuk DELETE BOOK
    public void deleteBook(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "DELETE FROM books WHERE id = "+id;
        db.execSQL(QUERY);
    }


    // Query untuk MEMBERS
    public ArrayList<HashMap<String,String>> getAllMembers() {
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM members";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);
        if(cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("nama", cursor.getString(1));
                map.put("no_telp", cursor.getString(2));
                map.put("email", cursor.getString(3));
                map.put("alamat", cursor.getString(4));
                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    // QUERY untuk INSERT MEMBERS
    public void insertMembers(String nama, String no_telp, String email, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "INSERT INTO members (nama, no_telp, email, alamat) VALUES ('"+nama+"', '"+no_telp+"', '"+email+"', '"+alamat+"');";
        db.execSQL(QUERY);
    }

    // QUERY untuk UPDATE MEMBERS
    public void updateMembers(int id, String nama, String no_telp, String email, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "UPDATE members SET nama = '"+nama+"', no_telp = '"+no_telp+"', email = '"+email+"', alamat = '"+alamat+"' WHERE id = "+id+";";
        db.execSQL(QUERY);
    }

    // QUERY untuk DELETE MEMBERS
    public void deleteMembers(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "DELETE FROM members WHERE id = "+id;
        db.execSQL(QUERY);
    }


//    QUERY untuk BORROWS
    public ArrayList<HashMap<String,String>> getAllBorrows(){
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        String QUERY = "SELECT borrows.id, borrows.book_id, borrows.member_id, books.judul, members.nama, members.no_telp, members.email FROM borrows JOIN books ON borrows.book_id = books.id JOIN members ON borrows.member_id = members.id;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("book_id", cursor.getString(1));
                map.put("member_id", cursor.getString(2));
                map.put("judul", cursor.getString(3));
                map.put("nama", cursor.getString(4));
                map.put("telp", cursor.getString(5));
                map.put("email", cursor.getString(6));
                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    // QUERY untuk INSERT BORROW
    public void insertBorrow(int book, int member){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("book_id", book);
        values.put("member_id", member);
        db.insert("borrows", null, values);
    }


    // QUERY untuk UPDATE BORROW
    public void updateBorrow(int id, int book, int member){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "UPDATE borrows SET book_id = '"+book+"', member_id = '"+member+"' WHERE id = "+id+";";
        db.execSQL(QUERY);
    }

    //QUERY untuk DELETE BORROW
    public void deleteBorrow(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "DELETE FROM borrows WHERE id = "+id;
        db.execSQL(QUERY);
    }

}
