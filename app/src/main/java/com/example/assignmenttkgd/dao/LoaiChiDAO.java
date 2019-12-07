package com.example.assignmenttkgd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assignmenttkgd.Contant;
import com.example.assignmenttkgd.database.DatabaseManager;
import com.example.assignmenttkgd.model.LoaiChi;
import com.example.assignmenttkgd.model.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class LoaiChiDAO implements Contant {

    private SQLiteDatabase db;
    private DatabaseManager databasemanager;


    public LoaiChiDAO(Context context) {
        databasemanager = new DatabaseManager(context);
        db = databasemanager.getWritableDatabase();
    }

    public int insertLoaiChi(LoaiChi loaiChi){
        ContentValues values = new ContentValues();
        values.put("Name", loaiChi.getName());
        values.put("Description", loaiChi.getDescription());
        try {
            if (db.insert(TABLE_LOAI_CHI, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG_LOAI_CHI, ex.toString());
        }
        return 1;
    }

    public int updateLoaiChi(String Id, String Name, String Description) {
        ContentValues values = new ContentValues();
        values.put("Name", Name);
        values.put("Description", Description);
        int result = db.update(TABLE_LOAI_CHI, values, "Id=?", new String[]{Id});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public List<LoaiChi> getAllLoaiChi() {
        List<LoaiChi> dsLoaiChi = new ArrayList<>();
        Cursor c = db.query(TABLE_LOAI_CHI, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            LoaiChi ee = new LoaiChi();
            ee.setId(c.getString(0));
            ee.setName(c.getString(1));
            ee.setDescription(c.getString(2));
            dsLoaiChi.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsLoaiChi;
    }

    public int deleteLoaiChiByID(String maLoaiChi) {
        int result = db.delete(TABLE_LOAI_CHI, "Id=?", new String[]{maLoaiChi});
        if (result == 0)
            return -1;
        return 1;
    }

//    DatabaseManager databaseManager;
//    Context context;
//
//    public LoaiThuDAO(Context context) {
//        this.context = context;
//        databaseManager = new DatabaseManager(context);
//    }
//
//    public void Insert(LoaiThu loaiThu) {
//        SQLiteDatabase sqLiteDatabase = databaseManager.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_ID_LOAI_THU, loaiThu.getId());
//        contentValues.put(COLUMN_NAME_LOAI_THU, loaiThu.getName());
//        contentValues.put(COLUMN_DESCRIPTION_LOAI_THU, loaiThu.getDescription());
//        sqLiteDatabase.insert(TABLE_LOAI_THU, null, contentValues);
//        sqLiteDatabase.close();
//    }
//
//    public long Update(LoaiThu loaiThu) {
//        SQLiteDatabase sqLiteDatabase = databaseManager.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_ID_LOAI_THU, loaiThu.getId());
//        contentValues.put(COLUMN_NAME_LOAI_THU, loaiThu.getName());
//        contentValues.put(COLUMN_DESCRIPTION_LOAI_THU, loaiThu.getDescription());
//        return sqLiteDatabase.update(TABLE_LOAI_THU, contentValues, COLUMN_NAME_LOAI_THU + "=?",
//                new String[]{String.valueOf(loaiThu.getName())});
//    }
//
//    public boolean delete(LoaiThu loaiThu) {
//        SQLiteDatabase sqLiteDatabase = databaseManager.getWritableDatabase();
//        int check = sqLiteDatabase.delete(TABLE_LOAI_THU, COLUMN_NAME_LOAI_THU + " = " + loaiThu.getName(), null);
//        if (check != 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public List<LoaiThu> getAllLoaiThu() {
//        List<LoaiThu> listLoaiThu = new ArrayList<LoaiThu>();
//        SQLiteDatabase sqLiteDatabase = databaseManager.getWritableDatabase();
//
//        String SELECT_ALL_LIST = "SELECT * FROM " + databaseManager.TABLE_LOAI_THU;
//        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_LIST, null);
//        cursor.moveToFirst();
//
//        while (!cursor.isAfterLast()) {
//            LoaiThu loaiThu = new LoaiThu();
//
//            loaiThu.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID_LOAI_THU)));
//            loaiThu.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LOAI_THU)));
//            loaiThu.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION_LOAI_THU)));
//
//            listLoaiThu.add(loaiThu);
//            cursor.moveToNext();
//        }
//        return listLoaiThu;
//    }
}
