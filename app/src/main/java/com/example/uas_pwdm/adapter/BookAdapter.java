package com.example.uas_pwdm.adapter;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uas_pwdm.R;
import com.example.uas_pwdm.model.BookData;

import java.util.List;

public class BookAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<BookData> lists;

    public BookAdapter(Activity activity, List<BookData> lists){
        this.activity = activity;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null && inflater != null){
            convertView = inflater.inflate(R.layout.list_books, null);
        }
        if (convertView != null){
            TextView judul = convertView.findViewById(R.id.txt_judul);
            TextView penulis = convertView.findViewById(R.id.txt_penulis);
            TextView tahun = convertView.findViewById(R.id.txt_tahun);
            BookData data = lists.get(position);
            judul.setText(data.getJudul());
            penulis.setText(data.getPenulis());
            tahun.setText(data.getTahun());
        }
        return convertView;
    }
}
