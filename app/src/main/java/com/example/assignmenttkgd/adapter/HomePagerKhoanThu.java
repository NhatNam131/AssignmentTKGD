package com.example.assignmenttkgd.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.assignmenttkgd.fragment.KhoanThuFragment;
import com.example.assignmenttkgd.fragment.LoaiThuFragment;

public class HomePagerKhoanThu extends FragmentPagerAdapter {
    public HomePagerKhoanThu(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new KhoanThuFragment();
            default:
                return new LoaiThuFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Khoản Thu" ;
            default:
                return "Loại Thu";
        }

    }
}
