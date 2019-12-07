package com.example.assignmenttkgd.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmenttkgd.R;
import com.example.assignmenttkgd.dao.LoaiThuDAO;
import com.example.assignmenttkgd.model.KhoanThu;
import com.example.assignmenttkgd.model.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class LoaiThuAdapter extends BaseAdapter implements Filterable {
    List<LoaiThu> arrLoaiThu;
    List<LoaiThu> arrSortLoaiThu;
    private Filter LoaiThuFilter;
    public Activity context;
    public LayoutInflater inflater;
    LoaiThuDAO loaiThuDAO;

    public LoaiThuAdapter(Activity context, List<LoaiThu> arrLoaiThu) {
        super();
        this.context = context;
        this.arrLoaiThu = arrLoaiThu;
        this.arrSortLoaiThu = arrLoaiThu;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loaiThuDAO = new LoaiThuDAO(context);
    }

    @Override
    public int getCount() {
        return arrLoaiThu.size();
    }

    @Override
    public Object getItem(int position) {
        return arrLoaiThu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        TextView tvTenLoaiThu;
        ImageView imgUpdate, imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_loai_thu, null);
            holder.tvTenLoaiThu = (TextView) convertView.findViewById(R.id.tvName);
            holder.imgUpdate = (ImageView) convertView.findViewById(R.id.imageUpdate);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imageDelete);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        LoaiThu _entry = (LoaiThu) arrLoaiThu.get(position);
        holder.tvTenLoaiThu.setText(_entry.getName());
        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn xóa thông tin loại thu");
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loaiThuDAO.deleteLoaiThuByID(arrLoaiThu.get(position).getId());
                        arrLoaiThu.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDatasetLoaiThu(List<LoaiThu> items) {
        this.arrLoaiThu = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrLoaiThu = arrSortLoaiThu;
    }

    @Override
    public Filter getFilter() {
        if (LoaiThuFilter == null)
            LoaiThuFilter = new CustomFilter();
        return LoaiThuFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortLoaiThu;
                results.count = arrSortLoaiThu.size();
            } else {
                List<LoaiThu> lsLoaiThu = new ArrayList<LoaiThu>();
                for (LoaiThu p : arrLoaiThu) {
                    if (p.getName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsLoaiThu.add(p);
                }
                results.values = lsLoaiThu;
                results.count = lsLoaiThu.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrLoaiThu = (List<LoaiThu>) results.values;
                notifyDataSetChanged();
            }
        }

    }

//    List<LoaiThu> list;
//    Context context;
//    int resource;
//
//    public LoaiThuAdapter(@NonNull Context context, int resource, @NonNull List<LoaiThu> lsloaiThu) {
//        this.context = context;
//        this.resource = resource;
//        this.list = lsloaiThu;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View itemView = inflater.inflate(R.layout.item_loai_thu, parent, false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        LoaiThu loaiThu = list.get(position);
//        holder.tvName.setText(loaiThu.getName());
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView tvName;
//        public ImageView imgUpdate;
//        public ImageView imgDelete;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvName = itemView.findViewById(R.id.tvName);
//        }
//    }
}
