package com.example.uas_pwdm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.uas_pwdm.adapter.BookAdapter;
import com.example.uas_pwdm.helper.BookHelper;
import com.example.uas_pwdm.model.BookData;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<BookData> lists = new ArrayList<>();
    BookAdapter adapter;
    BookHelper db = new BookHelper(this);
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        db = new BookHelper(getApplicationContext());
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(BookActivity.this, )
            }
        });
        listView = findViewById(R.id.list_item);
    }
}