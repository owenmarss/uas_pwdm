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

    private HashMap<Integer, Integer> positionBookIdMap = new HashMap<>();
    private HashMap<Integer, Integer> positionBorrowerIdMap = new HashMap<>();

    ArrayList<HashMap<String, String>> borrowData;
    ArrayList<HashMap<String, String>> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_borrow);

        headlineBorrow = findViewById(R.id.txt_headline_borrowEditor);
        spinnerBook =  findViewById(R.id.spin_buku);
        spinnerMember = findViewById(R.id.spin_member);
        btnSave = findViewById(R.id.button_save);

        populateSpinners();

        if(id == null || id.equals("")) {
            headlineBorrow.setText("Tambah Peminjaman");
        } else {
            headlineBorrow.setText("Edit Peminjaman");

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
        if (String.valueOf(spinnerBook.getSelectedItem().toString()).equals("") || String.valueOf(spinnerMember.getSelectedItem().toString()).equals("")) {
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data: ", Toast.LENGTH_SHORT).show();
        } else {
            // Get the selected book and member from spinners
            String selectedBookTitle = (String) spinnerBook.getSelectedItem();
            String selectedMemberName = (String) spinnerMember.getSelectedItem();

            // Find the corresponding IDs for the selected book and member
            int selectedBookId = positionBookIdMap.get(spinnerBook.getSelectedItemPosition());
            int selectedMemberId = positionBorrowerIdMap.get(spinnerMember.getSelectedItemPosition());

            // Call the database method to insert the borrow record
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
        ArrayList<String> bookTitles = new ArrayList<>();
        positionBookIdMap = new HashMap<>();
        ArrayList<String> borrowerNames = new ArrayList<>();
        positionBorrowerIdMap = new HashMap<>();
        ArrayList<HashMap<String, String>> members = new ArrayList<>();

        int position = 0;
        for (HashMap<String, String> book : db.getAllBooks()) {
            String title = book.get("judul");
            int bookid = Integer.parseInt(book.get("id"));
            if (title != null) {
                bookTitles.add(title);
                positionBookIdMap.put(position, bookid);
            }
            position++;
        }

        position=0;
        for (HashMap<String, String> member : db.getAllMembers()) {
            String name = member.get("nama");
            int memberid = Integer.parseInt(member.get("id"));
            if (name != null) {
                borrowerNames.add(name);
                positionBorrowerIdMap.put(position, memberid);
            }
            position++;
        }

        // Set Adapter
        ArrayAdapter<String> bookAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bookTitles);
        bookAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBook.setAdapter(bookAdapter);

        ArrayAdapter<String> memberAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, borrowerNames);
        memberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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