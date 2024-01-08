package com.example.uas_pwdm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas_pwdm.helper.Helper;

public class EditorMemberActivity extends AppCompatActivity {
    private TextView headlineMember;
    private EditText editNama, editNo_telp, editEmail, editAlamat;
    private Button btnSave;
    private Helper db = new Helper(this);
    private String id, nama, no_telp, email, alamat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_member);

        headlineMember = findViewById(R.id.txt_headline_memberEditor);

        editNama = findViewById(R.id.input_nama);
        editNo_telp = findViewById(R.id.input_no_telp);
        editEmail = findViewById(R.id.input_email);
        editAlamat = findViewById(R.id.input_alamat);
        btnSave = findViewById(R.id.button_save);

        id = getIntent().getStringExtra("id");
        nama = getIntent().getStringExtra("nama");
        no_telp = getIntent().getStringExtra("no_telp");
        email = getIntent().getStringExtra("email");
        alamat = getIntent().getStringExtra("alamat");

        if(id == null || id.equals("")) {
            headlineMember.setText("Tambah Member");
        } else {
            headlineMember.setText("Edit Member");
            editNama.setText(nama);
            editNo_telp.setText(no_telp);
            editEmail.setText(email);
            editAlamat.setText(alamat);
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
        if(String.valueOf(editNama.getText()).equals("") || String.valueOf(editNo_telp.getText()).equals("") || String.valueOf(editEmail.getText()).equals("") || String.valueOf(editAlamat.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data: ", Toast.LENGTH_SHORT).show();
        } else {
            db.insertMembers(editNama.getText().toString(), editNo_telp.getText().toString(), editEmail.getText().toString(), editAlamat.getText().toString());
            finish();
        }
    }

    private  void edit() {
        if (String.valueOf(editNama.getText()).equals("") || String.valueOf(editNo_telp.getText()).equals("") || String.valueOf(editEmail.getText()).equals("") || String.valueOf(editAlamat.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
        } else {
            db.updateMembers(Integer.parseInt(id), editNama.getText().toString(), editNo_telp.getText().toString(), editEmail.getText().toString(), editAlamat.getText().toString());
            finish();
        }
    }
}