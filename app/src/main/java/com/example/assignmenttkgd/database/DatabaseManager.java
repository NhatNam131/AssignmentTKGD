package com.example.assignmenttkgd.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignmenttkgd.Contant;
import com.example.assignmenttkgd.dao.KhoanThuDAO;
import com.example.assignmenttkgd.dao.LoaiThuDAO;

public class DatabaseManager extends SQLiteOpenHelper implements Contant {

    public static final String DB_NAME = "QuanLyThuChi";
    public static final int DB_VERSION = 1;

    public DatabaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LoaiThuDAO.CREATE_TABLE_LOAI_THU);
        db.execSQL(LoaiThuDAO.CREATE_TABLE_LOAI_CHI);
        db.execSQL(KhoanThuDAO.CREATE_TABLE_KHOAN_THU);
        db.execSQL(KhoanThuDAO.CREATE_TABLE_KHOAN_CHI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + LoaiThuDAO.TABLE_LOAI_THU);
        db.execSQL(" DROP TABLE IF EXISTS " + LoaiThuDAO.TABLE_LOAI_CHI);
        db.execSQL(" DROP TABLE IF EXISTS " + KhoanThuDAO.TABLE_KHOAN_THU);
        db.execSQL(" DROP TABLE IF EXISTS " + KhoanThuDAO.TABLE_KHOAN_CHI);
        onCreate(db);
    }
}
