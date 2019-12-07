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
import com.example.assignmenttkgd.adapter.KhoanChiAdapter;
import com.example.assignmenttkgd.adapter.SpinnerTenLoaiChi;
import com.example.assignmenttkgd.adapter.SpinnerTenLoaiThu;
import com.example.assignmenttkgd.dao.KhoanChiDAO;
import com.example.assignmenttkgd.dao.LoaiChiDAO;
import com.example.assignmenttkgd.dao.LoaiThuDAO;
import com.example.assignmenttkgd.model.KhoanChi;
import com.example.assignmenttkgd.model.LoaiChi;
import com.example.assignmenttkgd.model.LoaiThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class KhoanChiFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private KhoanChiDAO khoanChiDAO;
    private EditText edtName, edtMoney, edtDate, edtDescription;
    private Spinner spType;
    private KhoanChiAdapter adapter = null;
    ListView listKhoanChi;
    public static List<KhoanChi> lsKhoanChi = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.khoan_chi_fragment, container, false);
        floatingActionButton = view.findViewById(R.id.fabAdd);
        listKhoanChi = view.findViewById(R.id.list_khoan_chi);
        khoanChiDAO = new KhoanChiDAO(getContext());
        lsKhoanChi = khoanChiDAO.getAllKhoanChi();
        adapter = new KhoanChiAdapter(getActivity(), lsKhoanChi);
        listKhoanChi.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Khoản Chi");
                final View viewDialog = inflater.inflate(R.layout.dialog_them_khoan_chi, null);
                builder.setView(viewDialog);

                edtName = viewDialog.findViewById(R.id.edtName);
                spType = viewDialog.findViewById(R.id.spType);
                edtMoney = viewDialog.findViewById(R.id.edtPrice);
                edtDate = viewDialog.findViewById(R.id.edtDay);
                edtDescription = viewDialog.findViewById(R.id.edtDescription);

                List<LoaiChi> loaiChis = new LoaiChiDAO(getContext()).getAllLoaiChi();
                spType.setAdapter(new SpinnerTenLoaiChi(getContext(), loaiChis));

                builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = edtName.getText().toString().trim();
                        String description = edtDescription.getText().toString().trim();

                        khoanChiDAO = new KhoanChiDAO(getContext());

                        KhoanChi khoanChi = new KhoanChi();
                        khoanChi.setName(edtName.getText().toString());
                        LoaiChi selectedLoaiChi = (LoaiChi) spType.getSelectedItem();
                        String loaiChi = String.valueOf(selectedLoaiChi.getId());
                        khoanChi.setType(loaiChi);
                        khoanChi.setMoney(edtMoney.getText().toString());
                        khoanChi.setDate(edtDate.getText().toString());
                        khoanChi.setDescription(edtDescription.getText().toString());

                        try {
                            if (name.isEmpty() || description.isEmpty()) {
                                if (name.isEmpty())
                                    edtName.setError(getString(R.string.notify_empty_name_type));
                                if (description.isEmpty())
                                    edtDescription.setError(getString(R.string.notify_empty_description));
                            } else if (khoanChiDAO.insertKhoanChi(khoanChi) > 0) {
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                lsKhoanChi.clear();
                                lsKhoanChi = khoanChiDAO.getAllKhoanChi();
                                adapter.changeDatasetKhoanThu(khoanChiDAO.getAllKhoanChi());
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
