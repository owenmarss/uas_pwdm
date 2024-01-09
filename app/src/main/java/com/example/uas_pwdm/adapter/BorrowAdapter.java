package com.example.uas_pwdm.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uas_pwdm.R;
import com.example.uas_pwdm.model.BookData;
import com.example.uas_pwdm.model.BorrowData;

import java.util.List;

public class BorrowAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<BorrowData> lists;

    public BorrowAdapter(Activity activity, List<BorrowData> lists) {
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
            convertView = inflater.inflate(R.layout.list_borrows, null);
        }
        if (convertView != null){
            TextView judul = convertView.findViewById(R.id.txt_judul_borrow);
            TextView nama = convertView.findViewById(R.id.txt_nama_borrow);
            TextView telp = convertView.findViewById(R.id.txt_telp_borrow);
            TextView email = convertView.findViewById(R.id.txt_email_borrow);
            BorrowData data = lists.get(position);
            judul.setText(data.getJudul());
            nama.setText(data.getNama());
            telp.setText(data.getTelp());
            email.setText(data.getEmail());
        }
        return convertView;
    }
}
