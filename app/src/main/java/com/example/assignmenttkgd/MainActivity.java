package com.example.assignmenttkgd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.assignmenttkgd.fragment.ChiFragment;
import com.example.assignmenttkgd.fragment.GioiThieuFragment;
import com.example.assignmenttkgd.fragment.ThuFragment;
import com.example.assignmenttkgd.fragment.ThongKeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ThuFragment fragment_thu;
    private ChiFragment fragment_chi;
    private ThongKeFragment fragment_thongKe;
    private GioiThieuFragment fragment_gioithieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment_thongKe = new ThongKeFragment();
        fragment_thu = new ThuFragment();
        fragment_chi = new ChiFragment();
        fragment_gioithieu = new GioiThieuFragment();
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        hienThiManHinhThu();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_khoan_thu:
                        toolbar.setTitle("Quản Lý Khoản Thu");
                        hienThiManHinhThu();
                        break;
                    case R.id.nav_khoan_chi:
                        toolbar.setTitle("Quản Lý Khoản Chi");
                        hienThiManHinhChi();
                        break;
                    case R.id.nav_thong_ke:
                        toolbar.setTitle("Thống Kê");
                        hienThiManHinhThongKe();
                        break;
                    case R.id.nav_intro:
                        toolbar.setTitle("Giới Thiệu");
                        hienThiManHinhGioiThieu();
                        break;
                    case R.id.nav_out:
                        finish();
                        break;
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void hienThiManHinhThongKe() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment_thu.isAdded()) {
            ft.hide(fragment_thu);
        }
        if (fragment_chi.isAdded()) {
            ft.hide(fragment_chi);
        }
        if (fragment_gioithieu.isAdded()) {
            ft.hide(fragment_gioithieu);
        }
        if (fragment_thongKe.isAdded()) {
            ft.show(fragment_thongKe);
        } else {
            ft.add(R.id.fragment_container, fragment_thongKe);
        }
        ft.commit();
    }

    public void hienThiManHinhThu() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment_chi.isAdded()) {
            ft.hide(fragment_chi);
        }
        if (fragment_thongKe.isAdded()) {
            ft.hide(fragment_thongKe);
        }
        if (fragment_gioithieu.isAdded()) {
            ft.hide(fragment_gioithieu);
        }
        if (fragment_thu.isAdded()) {
            ft.show(fragment_thu);
        } else {
            ft.add(R.id.fragment_container, fragment_thu);
        }
        ft.commit();
    }

    public void hienThiManHinhChi() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment_thongKe.isAdded()) {
            ft.hide(fragment_thongKe);
        }
        if (fragment_thu.isAdded()) {
            ft.hide(fragment_thu);
        }
        if (fragment_gioithieu.isAdded()) {
            ft.hide(fragment_gioithieu);
        }
        if (fragment_chi.isAdded()) {
            ft.show(fragment_chi);
        } else {
            ft.add(R.id.fragment_container, fragment_chi);
        }
        ft.commit();
    }

    public void hienThiManHinhGioiThieu(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment_thongKe.isAdded()) {
            ft.hide(fragment_thongKe);
        }
        if (fragment_thu.isAdded()) {
            ft.hide(fragment_thu);
        }
        if (fragment_chi.isAdded()) {
            ft.hide(fragment_chi);
        }
        if (fragment_gioithieu.isAdded()) {
            ft.show(fragment_gioithieu);
        } else {
            ft.add(R.id.fragment_container, fragment_gioithieu);
        }
        ft.commit();
    }
}
