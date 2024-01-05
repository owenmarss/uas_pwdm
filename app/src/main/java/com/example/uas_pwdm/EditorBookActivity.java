package com.example.uas_pwdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uas_pwdm.helper.BookHelper;

public class EditorBookActivity extends AppCompatActivity {
    private EditText editJudulBuku, editPenulis, editTahunTerbit;
    private Button btnSave;
    private BookHelper db = new BookHelper(this);
    private String id, judulBuku, penulis, tahunTerbit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_book);

        editJudulBuku = findViewById(R.id.input_judulBuku);
        editPenulis = findViewById(R.id.input_penulis);
        editTahunTerbit = findViewById(R.id.input_tahunTerbit);

        id = getIntent().getStringExtra("id");
        judulBuku = getIntent().getStringExtra("judulBuku");
        penulis = getIntent().getStringExtra("penulis");
        tahunTerbit = getIntent().getStringExtra("tahunTerbit");

        if(id == null || id.equals("")) {
            setTitle("Tambah Buku");
        } else {
            setTitle("Edit Buku");
            editJudulBuku.setText(judulBuku);
            editPenulis.setText(penulis);
            editTahunTerbit.setText(tahunTerbit);
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(id == null || id.equals("")) {

                    } else {

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
            db.insert(editJudulBuku.getText().toString(), editPenulis.getText().toString(), editTahunTerbit.getText().toString());
            finish();
        }
    }

    private  void edit() {
        if (String.valueOf(editJudulBuku.getText()).equals("") || String.valueOf(editPenulis.getText()).equals("") || String.valueOf(editTahunTerbit.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
        } else {
            db.update(Integer.parseInt(id), editJudulBuku.getText().toString(), editPenulis.getText().toString(), editTahunTerbit.getText().toString());
            finish();
        }
    }
}