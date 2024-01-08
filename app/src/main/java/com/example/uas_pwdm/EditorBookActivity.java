package com.example.uas_pwdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas_pwdm.helper.Helper;

public class EditorBookActivity extends AppCompatActivity {
    private TextView headlineBuku;
    private EditText editJudulBuku, editPenulis, editTahunTerbit;
    private Button btnSave;
    private Helper db = new Helper(this);
    private String id, judulBuku, penulis, tahunTerbit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_book);

        headlineBuku = findViewById(R.id.txt_headline_bookEditor);

        editJudulBuku = findViewById(R.id.input_judulBuku);
        editPenulis = findViewById(R.id.input_penulis);
        editTahunTerbit = findViewById(R.id.input_tahunTerbit);
        btnSave = findViewById(R.id.button_save);

        id = getIntent().getStringExtra("id");
        judulBuku = getIntent().getStringExtra("judulBuku");
        penulis = getIntent().getStringExtra("penulis");
        tahunTerbit = getIntent().getStringExtra("tahunTerbit");

        if(id == null || id.equals("")) {
            headlineBuku.setText("Tambah Buku");
        } else {
            headlineBuku.setText("Edit Buku");
            editJudulBuku.setText(judulBuku);
            editPenulis.setText(penulis);
            editTahunTerbit.setText(tahunTerbit);
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(id == null || id.equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e) {
                    Log.e("Saving", e.getMessage());
                }
            }
        });
    }

    private void save() {
        if(String.valueOf(editJudulBuku.getText()).equals("") || String.valueOf(editPenulis.getText()).equals("") || String.valueOf(editTahunTerbit.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data: ", Toast.LENGTH_SHORT).show();
        } else {
            db.insertBook(editJudulBuku.getText().toString(), editPenulis.getText().toString(), editTahunTerbit.getText().toString());
            finish();
        }
    }

    private  void edit() {
        if (String.valueOf(editJudulBuku.getText()).equals("") || String.valueOf(editPenulis.getText()).equals("") || String.valueOf(editTahunTerbit.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
        } else {
            db.updateBook(Integer.parseInt(id), editJudulBuku.getText().toString(), editPenulis.getText().toString(), editTahunTerbit.getText().toString());
            finish();
        }
    }
}