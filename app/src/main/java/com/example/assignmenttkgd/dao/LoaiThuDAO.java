package com.example.assignmenttkgd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assignmenttkgd.Contant;
import com.example.assignmenttkgd.database.DatabaseManager;
import com.example.assignmenttkgd.model.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class LoaiThuDAO implements Contant {

    private SQLiteDatabase db;
    private DatabaseManager databasemanager;


    public LoaiThuDAO(Context context) {
        databasemanager = new DatabaseManager(context);
        db = databasemanager.getWritableDatabase();
    }

    public int insertLoaiThu(LoaiThu loaiThu){
        ContentValues values = new ContentValues();
//        values.put("Id", loaiThu.getId());
        values.put("Name", loaiThu.getName());
        values.put("Description", loaiThu.getDescription());
        try {
            if (db.insert(TABLE_LOAI_THU, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG_LOAI_THU, ex.toString());
        }
        return 1;
    }

    public int updateLoaiThu(String Id, String Name, String edDescription) {
        ContentValues values = new ContentValues();
        values.put("Id", Id);
        values.put("Name", Name);
        int result = db.update(TABLE_LOAI_THU, values, "Id=?", new String[]{Id});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public List<LoaiThu> getAllLoaiThu() {
        List<LoaiThu> dsLoaiThu = new ArrayList<>();
        Cursor c = db.query(TABLE_LOAI_THU, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            LoaiThu ee = new LoaiThu();
            ee.setId(c.getString(0));
            ee.setName(c.getString(1));
            ee.setDescription(c.getString(2));
            dsLoaiThu.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsLoaiThu;
    }

    public int deleteLoaiThuByID(String maLoaiThu) {
        int result = db.delete(TABLE_LOAI_THU, "Id=?", new String[]{maLoaiThu});
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
