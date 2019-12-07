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
import com.example.assignmenttkgd.dao.KhoanChiDAO;
import com.example.assignmenttkgd.dao.KhoanThuDAO;
import com.example.assignmenttkgd.model.KhoanChi;
import com.example.assignmenttkgd.model.KhoanThu;

import java.util.ArrayList;
import java.util.List;

public class KhoanChiAdapter extends BaseAdapter implements Filterable {

    List<KhoanChi> arrKhoanChi;
    List<KhoanChi> arrSortKhoanChi;
    private Filter KhoanChiFilter;
    public Activity context;
    public LayoutInflater inflater;
    KhoanChiDAO khoanChiDAO;

    public KhoanChiAdapter(Activity context, List<KhoanChi> arrKhoanThu) {
        super();
        this.context = context;
        this.arrKhoanChi = arrKhoanThu;
        this.arrSortKhoanChi = arrKhoanChi;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        khoanChiDAO = new KhoanChiDAO(context);
    }

    @Override
    public int getCount() {
        return arrKhoanChi.size();
    }

    @Override
    public Object getItem(int position) {
        return arrKhoanChi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        TextView tvTenKhoanChi;
        ImageView imgUpdate, imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        KhoanChiAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_khoan_thu, null);
            holder.tvTenKhoanChi = (TextView) convertView.findViewById(R.id.tvName);
            holder.imgUpdate = (ImageView) convertView.findViewById(R.id.imageUpdate);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imageDelete);
            convertView.setTag(holder);
        } else
            holder = (KhoanChiAdapter.ViewHolder) convertView.getTag();
        KhoanChi _entry = (KhoanChi) arrKhoanChi.get(position);
        holder.tvTenKhoanChi.setText(_entry.getName());
        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn xóa thông tin khoản chi");
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        khoanChiDAO.deleteKhoanThuByID(arrKhoanChi.get(position).getId());
                        arrKhoanChi.remove(position);
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

    public void changeDatasetKhoanThu(List<KhoanChi> items) {
        this.arrKhoanChi = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrKhoanChi = arrSortKhoanChi;
    }

    @Override
    public Filter getFilter() {
        if (KhoanChiFilter == null)
            KhoanChiFilter = new CustomFilter();
        return KhoanChiFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortKhoanChi;
                results.count = arrSortKhoanChi.size();
            } else {
                List<KhoanChi> lsKhoanChi = new ArrayList<KhoanChi>();
                for (KhoanChi p : arrKhoanChi) {
                    if (p.getName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsKhoanChi.add(p);
                }
                results.values = lsKhoanChi;
                results.count = lsKhoanChi.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrKhoanChi = (List<KhoanChi>) results.values;
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
