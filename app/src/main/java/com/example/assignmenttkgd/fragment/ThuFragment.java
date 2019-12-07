package com.example.assignmenttkgd.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.assignmenttkgd.R;
import com.example.assignmenttkgd.adapter.HomePagerKhoanThu;
import com.google.android.material.tabs.TabLayout;

public class ThuFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private HomePagerKhoanThu homePagerKhoanThu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.thu_fragment, container, false);
        viewPager = view.findViewById(R.id.viewPaperThu);
        tabLayout = view.findViewById(R.id.tableLayoutThu);
        homePagerKhoanThu = new HomePagerKhoanThu(getFragmentManager());
        viewPager.setAdapter(homePagerKhoanThu);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
