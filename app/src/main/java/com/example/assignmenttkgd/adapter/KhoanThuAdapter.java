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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmenttkgd.R;
import com.example.assignmenttkgd.dao.KhoanThuDAO;
import com.example.assignmenttkgd.dao.LoaiThuDAO;
import com.example.assignmenttkgd.model.KhoanThu;
import com.example.assignmenttkgd.model.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class KhoanThuAdapter extends BaseAdapter implements Filterable {

    List<KhoanThu> arrKhoanThu;
    List<KhoanThu> arrSortKhoanThu;
    private Filter KhoanThuFilter;
    public Activity context;
    public LayoutInflater inflater;
    KhoanThuDAO khoanThuDAO;

    public KhoanThuAdapter(Activity context, List<KhoanThu> arrKhoanThu) {
        super();
        this.context = context;
        this.arrKhoanThu = arrKhoanThu;
        this.arrSortKhoanThu = arrKhoanThu;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        khoanThuDAO = new KhoanThuDAO(context);
    }

    @Override
    public int getCount() {
        return arrKhoanThu.size();
    }

    @Override
    public Object getItem(int position) {
        return arrKhoanThu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        TextView tvTenKhoanThu;
        ImageView imgUpdate, imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        KhoanThuAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_khoan_thu, null);
            holder.tvTenKhoanThu = (TextView) convertView.findViewById(R.id.tvName);
            holder.imgUpdate = (ImageView) convertView.findViewById(R.id.imageUpdate);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imageDelete);
            convertView.setTag(holder);
        } else
            holder = (KhoanThuAdapter.ViewHolder) convertView.getTag();
        KhoanThu _entry = (KhoanThu) arrKhoanThu.get(position);
        holder.tvTenKhoanThu.setText(_entry.getName());
        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn xóa thông tin khoản thu");
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        khoanThuDAO.deleteKhoanThuByID(arrKhoanThu.get(position).getId());
                        arrKhoanThu.remove(position);
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

    public void changeDatasetKhoanThu(List<KhoanThu> items) {
        this.arrKhoanThu = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrKhoanThu = arrSortKhoanThu;
    }

    @Override
    public Filter getFilter() {
        if (KhoanThuFilter == null)
            KhoanThuFilter = new CustomFilter();
        return KhoanThuFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortKhoanThu;
                results.count = arrSortKhoanThu.size();
            } else {
                List<KhoanThu> lsKhoanThu = new ArrayList<KhoanThu>();
                for (KhoanThu p : arrKhoanThu) {
                    if (p.getName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsKhoanThu.add(p);
                }
                results.values = lsKhoanThu;
                results.count = lsKhoanThu.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrKhoanThu = (List<KhoanThu>) results.values;
                notifyDataSetChanged();
            }
        }

    }

//    List<KhoanThu> list;
//    Context context;
//    int resource;
//
//    public KhoanThuAdapter(@NonNull Context context, int resource, @NonNull List<KhoanThu> lskhoanThu){
//        this.context = context;
//        this.resource = resource;
//        this.list = lskhoanThu;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View itemView = inflater.inflate(R.layout.item_khoan_thu, parent, false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }
}
