package com.example.assignmenttkgd;

public interface Contant {

    String TAG_KHOAN_THU = "KHOAN_THU_DAO";

    //Table Khoan Thu
    String TABLE_KHOAN_THU = "KhoanThu";

    String COLUMN_ID_KHOAN_THU = "Id";

    String COLUMN_NAME_KHOAN_THU = "Name";

    String COLUMN_TYPE_KHOAN_THU = "Type";

    String COLUMN_MONEY_KHOAN_THU = "Money";

    String COLUMN_DATE_KHOAN_THU = "Date";

    String COLUMN_DESCRIPTION_KHOAN_THU = "Description";

    String CREATE_TABLE_KHOAN_THU = "CREATE TABLE " + TABLE_KHOAN_THU + " (" +
            "" + COLUMN_ID_KHOAN_THU + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            "" + COLUMN_NAME_KHOAN_THU + " NVARCHAR(10)," +
            "" + COLUMN_TYPE_KHOAN_THU + " VARCHAR," +
            "" + COLUMN_MONEY_KHOAN_THU + " INTEGER," +
            "" + COLUMN_DATE_KHOAN_THU + " INTEGER," +
            "" + COLUMN_DESCRIPTION_KHOAN_THU + " NVARCHAR(50)" +
            ")";


    String TAG_KHOAN_Chi = "KHOAN_CHI_DAO";

    //Table Khoan Chi
    String TABLE_KHOAN_CHI = "KhoanChi";

    String COLUMN_ID_KHOAN_CHI = "Id";

    String COLUMN_NAME_KHOAN_CHI = "Name";

    String COLUMN_TYPE_KHOAN_CHI = "Type";

    String COLUMN_MONEY_KHOAN_CHI = "Money";

    String COLUMN_DATE_KHOAN_CHI = "Date";

    String COLUMN_DESCRIPTION_KHOAN_CHI = "Description";

    String CREATE_TABLE_KHOAN_CHI = "CREATE TABLE " + TABLE_KHOAN_CHI + " (" +
            "" + COLUMN_ID_KHOAN_CHI + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            "" + COLUMN_NAME_KHOAN_CHI + " NVARCHAR(10)," +
            "" + COLUMN_TYPE_KHOAN_CHI + " VARCHAR," +
            "" + COLUMN_MONEY_KHOAN_CHI + " INTEGER," +
            "" + COLUMN_DATE_KHOAN_CHI + " INTEGER," +
            "" + COLUMN_DESCRIPTION_KHOAN_CHI + " NVARCHAR(50)" +
            ")";


    String TAG_LOAI_THU = "LOAI_THU_DAO";

    //Table Loai Thu
    String TABLE_LOAI_THU = "LoaiThu";

    String COLUMN_ID_LOAI_THU = "Id";

    String COLUMN_NAME_LOAI_THU = "Name";

    String COLUMN_DESCRIPTION_LOAI_THU = "Description";

    String CREATE_TABLE_LOAI_THU = "CREATE TABLE " + TABLE_LOAI_THU + " (" +
            "" + COLUMN_ID_LOAI_THU + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            "" + COLUMN_NAME_LOAI_THU + " NVARCHAR(10)," +
            "" + COLUMN_DESCRIPTION_LOAI_THU + " NVARCHAR(50)" +
            ")";



    String TAG_LOAI_CHI = "LOAI_CHI_DAO";

    //Table Loai Chi
    String TABLE_LOAI_CHI = "LoaiChi";

    String COLUMN_ID_LOAI_CHI = "Id";

    String COLUMN_NAME_LOAI_CHI = "Name";

    String COLUMN_DESCRIPTION_LOAI_CHI = "Description";

    String CREATE_TABLE_LOAI_CHI = "CREATE TABLE " + TABLE_LOAI_CHI + " (" +
            "" + COLUMN_ID_LOAI_CHI + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            "" + COLUMN_NAME_LOAI_CHI + " NVARCHAR(10)," +
            "" + COLUMN_DESCRIPTION_LOAI_CHI + " NVARCHAR(50)" +
            ")";
}
