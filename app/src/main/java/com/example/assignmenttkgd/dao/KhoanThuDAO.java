package com.example.assignmenttkgd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assignmenttkgd.Contant;
import com.example.assignmenttkgd.database.DatabaseManager;
import com.example.assignmenttkgd.model.KhoanThu;
import com.example.assignmenttkgd.model.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class KhoanThuDAO implements Contant {

    private SQLiteDatabase db;
    private DatabaseManager databasemanager;


    public KhoanThuDAO(Context context) {
        databasemanager = new DatabaseManager(context);
        db = databasemanager.getWritableDatabase();
    }

    public int insertKhoanThu(KhoanThu khoanThu){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_KHOAN_THU, khoanThu.getName());
        values.put(COLUMN_TYPE_KHOAN_THU, khoanThu.getType());
        values.put(COLUMN_MONEY_KHOAN_THU, khoanThu.getMoney());
        values.put(COLUMN_DATE_KHOAN_THU, khoanThu.getDate());
        values.put(COLUMN_DESCRIPTION_KHOAN_THU, khoanThu.getDescription());
        try {
            if (db.insert(TABLE_KHOAN_THU, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TABLE_KHOAN_THU, ex.toString());
        }
        return 1;
    }

    public int updateKhoanThu(String Id, String Name, String Type, String Money, String Date, String Description) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_KHOAN_THU, Id);
        values.put(COLUMN_NAME_KHOAN_THU, Name);
        values.put(COLUMN_TYPE_KHOAN_THU, Type);
        values.put(COLUMN_MONEY_KHOAN_THU, Money);
        values.put(COLUMN_DATE_KHOAN_THU, Date);
        values.put(COLUMN_DESCRIPTION_KHOAN_THU, Description);
        int result = db.update(TABLE_KHOAN_THU, values, COLUMN_ID_KHOAN_THU + "=?", new String[]{Id});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public List<KhoanThu> getAllKhoanThu() {
        List<KhoanThu> dsKhoanThu = new ArrayList<>();
        Cursor c = db.query(TABLE_KHOAN_THU, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            KhoanThu ee = new KhoanThu();
            ee.setId(c.getString(0));
            ee.setName(c.getString(1));
            ee.setType(c.getString(2));
            ee.setMoney(c.getString(3));
            ee.setDate(c.getString(4));
            ee.setDescription(c.getString(5));
            dsKhoanThu.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsKhoanThu;
    }

    public int deleteKhoanThuByID(String maKhoanThu) {
        int result = db.delete(TABLE_KHOAN_THU, COLUMN_ID_KHOAN_THU + "=?", new String[]{maKhoanThu});
        if (result == 0)
            return -1;
        return 1;
    }

//    DatabaseManager databaseManager;
//    Context context;
//
//    public KhoanThuDAO(Context context) {
//        this.context = context;
//        databaseManager = new DatabaseManager(context);
//    }
//
//    public void Insert(KhoanThu khoanThu) {
//        SQLiteDatabase sqLiteDatabase = databaseManager.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_ID_KHOAN_THU, khoanThu.getId());
//        contentValues.put(COLUMN_NAME_KHOAN_THU, khoanThu.getName());
//        contentValues.put(COLUMN_TYPE_KHOAN_THU, khoanThu.getType());
//        contentValues.put(COLUMN_MONEY_KHOAN_THU, khoanThu.getMoney());
//        contentValues.put(COLUMN_DATE_KHOAN_THU, khoanThu.getDate());
//        contentValues.put(COLUMN_DESCRIPTION_KHOAN_THU, khoanThu.getDescription());
//        sqLiteDatabase.insert(TABLE_KHOAN_THU, null, contentValues);
//        sqLiteDatabase.close();
//    }
//
//    public long Update(KhoanThu khoanThu) {
//        SQLiteDatabase sqLiteDatabase = databaseManager.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_ID_KHOAN_THU, khoanThu.getId());
//        contentValues.put(COLUMN_NAME_KHOAN_THU, khoanThu.getName());
//        contentValues.put(COLUMN_TYPE_KHOAN_THU, khoanThu.getType());
//        contentValues.put(COLUMN_MONEY_KHOAN_THU, khoanThu.getMoney());
//        contentValues.put(COLUMN_DATE_KHOAN_THU, khoanThu.getDate());
//        contentValues.put(COLUMN_DESCRIPTION_KHOAN_THU, khoanThu.getDescription());
//        return sqLiteDatabase.update(TABLE_KHOAN_THU, contentValues, COLUMN_NAME_KHOAN_THU + "=?",
//                new String[]{String.valueOf(khoanThu.getName())});
//    }
//
//    public boolean delete(KhoanThu khoanThu) {
//        SQLiteDatabase sqLiteDatabase = databaseManager.getWritableDatabase();
//        int check = sqLiteDatabase.delete(TABLE_KHOAN_THU, COLUMN_NAME_KHOAN_THU + " = " + khoanThu.getName(), null);
//        if (check != 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public List<KhoanThu> getAllKhoanThu() {
//        List<KhoanThu> listKhoanThu = new ArrayList<KhoanThu>();
//        SQLiteDatabase sqLiteDatabase = databaseManager.getWritableDatabase();
//
//        String SELECT_ALL_LIST = "SELECT * FROM " + databaseManager.TABLE_KHOAN_THU;
//        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_LIST, null);
//        cursor.moveToFirst();
//
//        while (!cursor.isAfterLast()) {
//            KhoanThu khoanThu = new KhoanThu();
//
//            khoanThu.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID_KHOAN_THU)));
//            khoanThu.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_KHOAN_THU)));
//            khoanThu.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_KHOAN_THU)));
//            khoanThu.setMoney(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_MONEY_KHOAN_THU))));
//            khoanThu.setDate(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_KHOAN_THU))));
//            khoanThu.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION_KHOAN_THU)));
//
//            listKhoanThu.add(khoanThu);
//            cursor.moveToNext();
//        }
//        return listKhoanThu;
//    }
}
