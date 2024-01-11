package com.example.uas_pwdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas_pwdm.helper.Helper;

import java.util.ArrayList;
import java.util.HashMap;

public class EditorBorrowActivity extends AppCompatActivity {

    private TextView headlineBorrow;
    Spinner spinnerBook, spinnerMember;
    private Button btnSave;
    private Helper db = new Helper(this);
    String id, book_id, member_id, judul, nama, telp, email;
    ArrayList<HashMap<String, String>> borrowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_borrow);

        headlineBorrow = findViewById(R.id.txt_headline_borrowEditor);

        spinnerBook =  findViewById(R.id.spin_buku);
        spinnerMember = findViewById(R.id.spin_member);

        btnSave = findViewById(R.id.button_save);

        //percobaan yang belum bisa
        borrowData = db.getAllBorrows();
        populateSpinners();

        id = getIntent().getStringExtra("id");
        book_id = getIntent().getStringExtra("book_id");
        member_id = getIntent().getStringExtra("member_id");
//        judul = getIntent().getStringExtra("judul");
//        nama = getIntent().getStringExtra("nama");
//        telp = getIntent().getStringExtra("telp");
//        email = getIntent().getStringExtra("email");

        if(id == null || id.equals("")) {
            headlineBorrow.setText("Tambah Buku");
        } else {
            headlineBorrow.setText("Edit Buku");

            // Find the index of the item you want to set as selected in the Spinner
            int selectedBookPosition = findPositionOfBookInSpinner(Integer.parseInt(book_id));
            int selectedMemberPosition = findPositionOfMemberInSpinner(Integer.parseInt(member_id));

            // Set the selected item in the Spinner
            spinnerBook.setSelection(selectedBookPosition);
            spinnerMember.setSelection(selectedMemberPosition);
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
        if(String.valueOf(spinnerBook.getSelectedItem().toString()).equals("") || String.valueOf(spinnerMember.getSelectedItem().toString()).equals("")) {
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data: ", Toast.LENGTH_SHORT).show();
        } else {
            CustomItem selectedBook = (CustomItem) spinnerBook.getSelectedItem();
            CustomItem selectedMember = (CustomItem) spinnerMember.getSelectedItem();

            int selectedBookId = selectedBook.getId();
            int selectedMemberId = selectedMember.getId();

            db.insertBorrow(selectedBookId, selectedMemberId);
            finish();
        }
    }

    private  void edit() {
        if (String.valueOf(spinnerBook.getSelectedItem().toString()).equals("") || String.valueOf(spinnerMember.getSelectedItem().toString()).equals("")) {
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
        } else {
            CustomItem selectedBook = (CustomItem) spinnerBook.getSelectedItem();
            CustomItem selectedMember = (CustomItem) spinnerMember.getSelectedItem();

            int selectedBookId = selectedBook.getId();
            int selectedMemberId = selectedMember.getId();

            db.updateBorrow(Integer.parseInt(id), selectedBookId, selectedMemberId);
            finish();
        }
    }

    private void populateSpinners() {
        ArrayList<CustomItem> books = new ArrayList<>();
        ArrayList<CustomItem> members = new ArrayList<>();

        for (HashMap<String, String> borrow : borrowData) {
            int bookId = Integer.parseInt(borrow.get("book_id"));
            String bookTitle = borrow.get("judul");
            books.add(new CustomItem(bookId, bookTitle));

            int memberId = Integer.parseInt(borrow.get("member_id"));
            String memberName = borrow.get("nama");
            members.add(new CustomItem(memberId, memberName));
        }

        // Ini yang BERHASIL
        ArrayAdapter<CharSequence> bookAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.judul_buku, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> memberAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.nama_member, android.R.layout.simple_spinner_item);

        // Ini yang GAGAL
        // ArrayAdapter<CustomItem> bookAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, books);
        // ArrayAdapter<CustomItem> memberAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, members);

        bookAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        memberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerBook.setAdapter(bookAdapter);
        spinnerMember.setAdapter(memberAdapter);
    }

    private int findPositionOfBookInSpinner(int bookId) {
        for (int i = 0; i < spinnerBook.getCount(); i++) {
            CustomItem item = (CustomItem) spinnerBook.getItemAtPosition(i);
            if (item.getId() == bookId) {
                return i;
            }
        }
        return 0; // Default position if not found
    }

    private int findPositionOfMemberInSpinner(int memberId) {
        for (int i = 0; i < spinnerMember.getCount(); i++) {
            CustomItem item = (CustomItem) spinnerMember.getItemAtPosition(i);
            if (item.getId() == memberId) {
                return i;
            }
        }
        return 0; // Default position if not found
    }
}