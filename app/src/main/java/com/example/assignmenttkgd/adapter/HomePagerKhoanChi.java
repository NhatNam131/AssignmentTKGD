package com.example.assignmenttkgd.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.assignmenttkgd.fragment.KhoanChiFragment;
import com.example.assignmenttkgd.fragment.LoaiChiFragment;

public class HomePagerKhoanChi extends FragmentPagerAdapter {


    public HomePagerKhoanChi(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new KhoanChiFragment();
            default:
                return new LoaiChiFragment();
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
                return "Khoản Chi";
            default:
                return "Loại Chi";
        }

    }
}
