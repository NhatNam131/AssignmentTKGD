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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignmenttkgd.R;
import com.example.assignmenttkgd.adapter.KhoanThuAdapter;
import com.example.assignmenttkgd.adapter.SpinnerTenLoaiThu;
import com.example.assignmenttkgd.dao.KhoanThuDAO;

import com.example.assignmenttkgd.dao.LoaiThuDAO;
import com.example.assignmenttkgd.database.DatabaseManager;
import com.example.assignmenttkgd.model.KhoanThu;

import com.example.assignmenttkgd.model.LoaiThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class KhoanThuFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private KhoanThuDAO khoanThuDAO;
    private EditText edtName, edtMoney, edtDate, edtDescription;
    private Spinner spType;
    private KhoanThuAdapter adapter = null;
    ListView listKhoanThu;
    public static List<KhoanThu> lsKhoanThu = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.khoan_thu_fragment, container, false);
        floatingActionButton = view.findViewById(R.id.fabAdd);
        listKhoanThu = view.findViewById(R.id.list_khoan_thu);
        khoanThuDAO = new KhoanThuDAO(getContext());
        lsKhoanThu = khoanThuDAO.getAllKhoanThu();
        adapter = new KhoanThuAdapter(getActivity(), lsKhoanThu);
        listKhoanThu.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Khoản Thu");
                final View viewDialog = inflater.inflate(R.layout.dialog_them_khoan_thu, null);
                builder.setView(viewDialog);

                edtName = viewDialog.findViewById(R.id.edtName);
                spType = viewDialog.findViewById(R.id.spType);
                edtMoney = viewDialog.findViewById(R.id.edtPrice);
                edtDate = viewDialog.findViewById(R.id.edtDay);
                edtDescription = viewDialog.findViewById(R.id.edtDescription);

                List<LoaiThu> loaiThus = new LoaiThuDAO(getContext()).getAllLoaiThu();
                spType.setAdapter(new SpinnerTenLoaiThu(getContext(), loaiThus));

                builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = edtName.getText().toString().trim();
                        String description = edtDescription.getText().toString().trim();

                        khoanThuDAO = new KhoanThuDAO(getContext());

                        KhoanThu khoanThu = new KhoanThu();
                        khoanThu.setName(edtName.getText().toString());
                        LoaiThu selectedLoaiThu = (LoaiThu) spType.getSelectedItem();
                        String loaiThu = String.valueOf(selectedLoaiThu.getId());
                        khoanThu.setType(loaiThu);
                        khoanThu.setMoney(edtMoney.getText().toString());
                        khoanThu.setDate(edtDate.getText().toString());
                        khoanThu.setDescription(edtDescription.getText().toString());

                        try {
                            if (name.isEmpty() || description.isEmpty()) {
                                if (name.isEmpty())
                                    edtName.setError(getString(R.string.notify_empty_name_type));
                                if (description.isEmpty())
                                    edtDescription.setError(getString(R.string.notify_empty_description));
                            } else if (khoanThuDAO.insertKhoanThu(khoanThu) > 0) {
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                lsKhoanThu.clear();
                                lsKhoanThu = khoanThuDAO.getAllKhoanThu();
                                adapter.changeDatasetKhoanThu(khoanThuDAO.getAllKhoanThu());
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
