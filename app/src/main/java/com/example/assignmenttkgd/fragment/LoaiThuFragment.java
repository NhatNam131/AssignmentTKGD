package com.example.assignmenttkgd.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmenttkgd.R;
import com.example.assignmenttkgd.adapter.LoaiThuAdapter;
import com.example.assignmenttkgd.dao.LoaiThuDAO;
import com.example.assignmenttkgd.model.LoaiChi;
import com.example.assignmenttkgd.model.LoaiThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LoaiThuFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private LoaiThuDAO loaiThuDAO;
    private EditText edtName, edtDescription;
    private LoaiThuAdapter adapter = null;
    ListView listLoaiThu;
    public static List<LoaiThu> lsLoaiThu = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.loai_thu_fragment, container, false);
        floatingActionButton = view.findViewById(R.id.fabtheloai);
        listLoaiThu = view.findViewById(R.id.list_loai_thu);
        loaiThuDAO = new LoaiThuDAO(getContext());
        lsLoaiThu = loaiThuDAO.getAllLoaiThu();
        adapter = new LoaiThuAdapter(getActivity(), lsLoaiThu);
        listLoaiThu.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Loại Thu");
                final View viewDialog = inflater.inflate(R.layout.dialog_them_loai_thu, null);
                builder.setView(viewDialog);

                edtName = viewDialog.findViewById(R.id.edtNameLoaiThu);
                edtDescription = viewDialog.findViewById(R.id.edtDescriptionLoaiThu);

                builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = edtName.getText().toString().trim();
                        String description = edtDescription.getText().toString().trim();

                        loaiThuDAO = new LoaiThuDAO(getContext());

                        LoaiThu loaiThu = new LoaiThu();
                        loaiThu.setName(edtName.getText().toString());
                        loaiThu.setDescription(edtDescription.getText().toString());

                        try {
                            if (name.isEmpty() || description.isEmpty()) {
                                if (name.isEmpty())
                                    edtName.setError(getString(R.string.notify_empty_name_type));
                                if (description.isEmpty())
                                    edtDescription.setError(getString(R.string.notify_empty_description));
                            } else if (loaiThuDAO.insertLoaiThu(loaiThu) > 0) {
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                lsLoaiThu.clear();
                                lsLoaiThu = loaiThuDAO.getAllLoaiThu();
                                adapter.changeDatasetLoaiThu(loaiThuDAO.getAllLoaiThu());
                                dialogInterface.dismiss();
                            } else {
                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.e("Error", e.toString());
                        }
                    }
                });

                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });
        return view;
    }
}
