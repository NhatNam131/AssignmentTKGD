package com.example.assignmenttkgd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assignmenttkgd.R;
import com.example.assignmenttkgd.model.LoaiThu;

import java.util.List;

public class SpinnerTenLoaiThu extends BaseAdapter {
    public Context context;
    public List<LoaiThu> loaiThus;

    public SpinnerTenLoaiThu(Context context, List<LoaiThu> loaiThus) {
        this.context = context;
        this.loaiThus = loaiThus;
    }

    @Override
    public int getCount() {
        return loaiThus.size();
    }

    @Override
    public Object getItem(int position) {
        return loaiThus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.spinner_loai_thu, parent,false);
        TextView tvName;
        tvName  =  convertView.findViewById(R.id.tvName);
        tvName.setText(loaiThus.get(position).getName());
        return convertView;
    }
}
