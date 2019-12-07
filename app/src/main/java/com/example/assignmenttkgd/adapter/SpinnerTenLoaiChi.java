package com.example.assignmenttkgd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assignmenttkgd.R;
import com.example.assignmenttkgd.model.LoaiChi;
import com.example.assignmenttkgd.model.LoaiThu;

import java.util.List;

public class SpinnerTenLoaiChi extends BaseAdapter {
    public Context context;
    public List<LoaiChi> loaiChis;

    public SpinnerTenLoaiChi(Context context, List<LoaiChi> loaiChis) {
        this.context = context;
        this.loaiChis = loaiChis;
    }

    @Override
    public int getCount() {
        return loaiChis.size();
    }

    @Override
    public Object getItem(int position) {
        return loaiChis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.spinner_loai_chi, parent,false);
        TextView tvName;
        tvName  =  convertView.findViewById(R.id.tvName);
        tvName.setText(loaiChis.get(position).getName());
        return convertView;
    }
}
