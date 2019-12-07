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

import com.example.assignmenttkgd.R;
import com.example.assignmenttkgd.adapter.LoaiChiAdapter;
import com.example.assignmenttkgd.adapter.LoaiThuAdapter;
import com.example.assignmenttkgd.dao.LoaiChiDAO;
import com.example.assignmenttkgd.dao.LoaiThuDAO;
import com.example.assignmenttkgd.model.LoaiChi;
import com.example.assignmenttkgd.model.LoaiThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LoaiChiFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private LoaiChiDAO loaiChiDAO;
    private EditText edtName, edtDescription;
    private LoaiChiAdapter adapter = null;
    ListView listLoaiChi;
    public static List<LoaiChi> lsLoaiChi = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.loai_chi_fragment, container, false);
        floatingActionButton = view.findViewById(R.id.fabtheloai);
        listLoaiChi = view.findViewById(R.id.list_loai_chi);
        loaiChiDAO = new LoaiChiDAO(getContext());
        lsLoaiChi = loaiChiDAO.getAllLoaiChi();
        adapter = new LoaiChiAdapter(getActivity(), lsLoaiChi);
        listLoaiChi.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Loại Chi");
                final View viewDialog = inflater.inflate(R.layout.dialog_them_loai_chi, null);
                builder.setView(viewDialog);

                edtName = viewDialog.findViewById(R.id.edtNameLoaiChi);
                edtDescription = viewDialog.findViewById(R.id.edtDescriptionLoaiChi);

                builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = edtName.getText().toString().trim();
                        String description = edtDescription.getText().toString().trim();

                        loaiChiDAO = new LoaiChiDAO(getContext());

                        LoaiChi loaiChi = new LoaiChi();
                        loaiChi.setName(edtName.getText().toString());
                        loaiChi.setDescription(edtDescription.getText().toString());

                        try {
                            if (name.isEmpty() || description.isEmpty()) {
                                if (name.isEmpty())
                                    edtName.setError(getString(R.string.notify_empty_name_type));
                                if (description.isEmpty())
                                    edtDescription.setError(getString(R.string.notify_empty_description));
                            } else if (loaiChiDAO.insertLoaiChi(loaiChi) > 0) {
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                lsLoaiChi.clear();
                                lsLoaiChi = loaiChiDAO.getAllLoaiChi();
                                adapter.changeDatasetLoaiThu(loaiChiDAO.getAllLoaiChi());
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
