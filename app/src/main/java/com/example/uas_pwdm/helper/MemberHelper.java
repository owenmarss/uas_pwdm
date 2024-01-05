package com.example.uas_pwdm.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class MemberHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    static  final String DATABASE_NAME = "uaspwdm";
    public MemberHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "CREATE TABLE members (id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, no_telp INTEGER, email TEXT, alamat TEXT);";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS members");
        onCreate(db);
    }

//    public ArrayList <HashMap<String,String>> getAllMembers() {
//        ArrayList<HashMap<String,String>> list = new ArrayList<>();
//        String QUERY = "SELECT * FROM members";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(QUERY, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                HashMap <String, String> map = new HashMap<>();
//                map.put("id", cursor getString(0));
//                map.put("nama", cursor getString(1));
//                // Give me no_telp
//                map.put("email", cursor getString(3));
//                map.put("alamat", cursor getString(4));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return books;
//    }
}
