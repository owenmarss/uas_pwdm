package com.example.uas_pwdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.uas_pwdm.helper.Helper;

import java.util.ArrayList;
import java.util.HashMap;

public class EditorBorrowActivity extends AppCompatActivity {

    Helper db;
    Spinner spinnerBook, spinnerMember;

    ArrayList<HashMap<String, String>> borrowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_borrow);

        spinnerBook =  findViewById(R.id.spin_buku);
        spinnerMember = findViewById(R.id.spin_member);

        db = new Helper(this);
        borrowData = db.getAllBorrows();

        populateSpinners();

    }

    private void populateSpinners() {
        // Extract the data you need to display in the Spinner
        ArrayList<String> bookTitles = new ArrayList<>();
        ArrayList<String> memberNames = new ArrayList<>();

        for (HashMap<String, String> borrow : borrowData) {
            // Assuming 'judul' represents book titles and 'nama' represents member names
            bookTitles.add(borrow.get("judul"));
            memberNames.add(borrow.get("nama"));
        }

        // Create ArrayAdapter for the Spinners
        ArrayAdapter<String> bookAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bookTitles);
        ArrayAdapter<String> memberAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, memberNames);

        // Set the dropdown layout resource for both adapters
        bookAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        memberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapters to the Spinners
        spinnerBook.setAdapter(bookAdapter);
        spinnerMember.setAdapter(memberAdapter);
    }
}