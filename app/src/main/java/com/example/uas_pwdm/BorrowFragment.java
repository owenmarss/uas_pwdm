package com.example.uas_pwdm;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.uas_pwdm.adapter.BookAdapter;
import com.example.uas_pwdm.adapter.BorrowAdapter;
import com.example.uas_pwdm.helper.Helper;
import com.example.uas_pwdm.model.BookData;
import com.example.uas_pwdm.model.BorrowData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BorrowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class BorrowFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BorrowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BorrowFragment newInstance(String param1, String param2) {
        BorrowFragment fragment = new BorrowFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ListView listView;
    AlertDialog.Builder dialog;
    List<BorrowData> lists = new ArrayList<>();
    BorrowAdapter adapter;
    Helper db;
    Button btnAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_borrow, container, false);

        db  = new Helper(requireContext());
        btnAdd = view.findViewById(R.id.btn_addBorrow);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), EditorBorrowActivity.class);
                startActivity(intent);
            }
        });
        listView = view.findViewById(R.id.list_item);
        adapter = new BorrowAdapter(getActivity(), lists);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String id = lists.get(i).getId();
                final String judul = lists.get(i).getJudul();
                final String nama = lists.get(i).getNama();
                final String telp = lists.get(i).getTelp();
                final String email = lists.get(i).getEmail();
                final CharSequence[] dialogueItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(requireContext());
                dialog.setItems(dialogueItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(requireContext(), EditorBorrowActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("judul", judul);
                                intent.putExtra("nama", nama);
                                intent.putExtra("telp", telp);
                                intent.putExtra("email", email);
                                startActivity(intent);
                                break;
                            case 1:
                                db.deleteBorrow(Integer.parseInt(id));
                                lists.clear();
                                // Panggil Data Ulang
                                getData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getData();
        return view;
    }

    private void getData() {
        ArrayList<HashMap<String, String>> rows = db.getAllBorrows();
        for(int i = 0; i < rows.size(); i++) {
            String id = rows.get(i).get("id");
            String judul = rows.get(i).get("judul");
            String nama = rows.get(i).get("nama");
            String telp = rows.get(i).get("telp");
            String email = rows.get(i).get("email");

            BorrowData data = new BorrowData();
            data.setId(id);
            data.setJudul(judul);
            data.setNama(nama);
            data.setTelp(telp);
            data.setEmail(email);
            lists.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        lists.clear();
        getData();
    }
}