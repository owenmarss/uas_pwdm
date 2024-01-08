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
import com.example.uas_pwdm.model.MemberData;

import java.util.List;

public class MemberAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<MemberData> lists;

    public MemberAdapter(Activity activity, List<MemberData> lists) {
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
            convertView = inflater.inflate(R.layout.list_members, null);
        }
        if (convertView != null){
            TextView nama = convertView.findViewById(R.id.txt_nama);
            TextView no_telp = convertView.findViewById(R.id.txt_no_telp);
            TextView email = convertView.findViewById(R.id.txt_email);
            TextView alamat = convertView.findViewById(R.id.txt_alamat);
            MemberData data = lists.get(position);
            nama.setText(data.getNama());
            no_telp.setText(data.getNo_telp());
            email.setText(data.getEmail());
            alamat.setText(data.getAlamat());
        }
        return convertView;
    }
}
