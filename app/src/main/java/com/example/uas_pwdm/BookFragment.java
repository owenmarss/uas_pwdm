package com.example.uas_pwdm;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.uas_pwdm.adapter.BookAdapter;
import com.example.uas_pwdm.helper.BookHelper;
import com.example.uas_pwdm.model.BookData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class BookFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookFragment newInstance(String param1, String param2) {
        BookFragment fragment = new BookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    ListView listView;
    AlertDialog.Builder dialog;
    List<BookData> lists = new ArrayList<>();
    BookAdapter adapter;
    BookHelper db;
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
        View view = inflater.inflate(R.layout.fragment_book, container, false);

        db  = new BookHelper(requireContext());
        btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EditorBookFragment editorBookFragment = new EditorBookFragment();
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                transaction.replace(R.id.container, editorBookFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();

                Intent intent = new Intent(requireContext(), EditorBookActivity.class);
                startActivity(intent);
            }
        });
        listView = view.findViewById(R.id.list_item);
        adapter = new BookAdapter(getActivity(), lists);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String id = lists.get(i).getId();
                final String judulBuku = lists.get(i).getJudul();
                final String penulis = lists.get(i).getPenulis();
                final String tahunTerbit = lists.get(i).getTahun();
                final CharSequence[] dialogueItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(requireContext());
                dialog.setItems(dialogueItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(requireContext(), EditorBookActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("judulBuku", judulBuku);
                                intent.putExtra("penulis", penulis);
                                intent.putExtra("tahunTerbit", tahunTerbit);
                                startActivity(intent);
                                break;
                            case 1:
                                db.delete(Integer.parseInt(id));
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
        ArrayList<HashMap<String, String>> rows = db.getAllBooks();
        for(int i = 0; i < rows.size(); i++) {
            String id = rows.get(i).get("id");
            String judulBuku = rows.get(i).get("judul");
            String penulis = rows.get(i).get("penulis");
            String tahunTerbit = rows.get(i).get("tahun");

            BookData data = new BookData();
            data.setId(id);
            data.setJudul(judulBuku);
            data.setPenulis(penulis);
            data.setTahun(tahunTerbit);
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