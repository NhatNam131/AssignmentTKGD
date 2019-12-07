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

import com.example.assignmenttkgd.R;
import com.example.assignmenttkgd.dao.LoaiChiDAO;
import com.example.assignmenttkgd.dao.LoaiThuDAO;
import com.example.assignmenttkgd.model.LoaiChi;
import com.example.assignmenttkgd.model.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class LoaiChiAdapter extends BaseAdapter implements Filterable {
    List<LoaiChi> arrLoaiChi;
    List<LoaiChi> arrSortLoaiChi;
    private Filter LoaiChiFilter;
    public Activity context;
    public LayoutInflater inflater;
    LoaiChiDAO loaiChiDAO;


    public LoaiChiAdapter(Activity context, List<LoaiChi> arrLoaiThu) {
        super();
        this.context = context;
        this.arrLoaiChi = arrLoaiThu;
        this.arrSortLoaiChi = arrLoaiThu;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loaiChiDAO = new LoaiChiDAO(context);
    }

    @Override
    public int getCount() {
        return arrLoaiChi.size();
    }

    @Override
    public Object getItem(int position) {
        return arrLoaiChi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        TextView tvTenLoaiChi;
        ImageView imgUpdate, imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_loai_chi, null);
            holder.tvTenLoaiChi = (TextView) convertView.findViewById(R.id.tvName);
            holder.imgUpdate = (ImageView) convertView.findViewById(R.id.imageUpdate);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imageDelete);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        LoaiChi _entry = (LoaiChi) arrLoaiChi.get(position);
        holder.tvTenLoaiChi.setText(_entry.getName());
        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn xóa thông tin loại chi");
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loaiChiDAO.deleteLoaiChiByID(arrLoaiChi.get(position).getId());
                        arrLoaiChi.remove(position);
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

    public void changeDatasetLoaiThu(List<LoaiChi> items) {
        this.arrLoaiChi = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrLoaiChi = arrSortLoaiChi;
    }

    @Override
    public Filter getFilter() {
        if (LoaiChiFilter == null)
            LoaiChiFilter = new CustomFilter();
        return LoaiChiFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortLoaiChi;
                results.count = arrSortLoaiChi.size();
            } else {
                List<LoaiChi> lsLoaiChi = new ArrayList<LoaiChi>();
                for (LoaiChi p : arrLoaiChi) {
                    if (p.getName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsLoaiChi.add(p);
                }
                results.values = lsLoaiChi;
                results.count = lsLoaiChi.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrLoaiChi = (List<LoaiChi>) results.values;
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
