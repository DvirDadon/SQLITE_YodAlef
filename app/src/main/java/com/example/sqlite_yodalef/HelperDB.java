package com.example.sqlite_yodalef;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.sqlite_yodalef.Grades.GRADE;
import static com.example.sqlite_yodalef.Grades.QUARTER;
import static com.example.sqlite_yodalef.Grades.SUBJECT;
import static com.example.sqlite_yodalef.Grades.TABLE_GRADES;
import static com.example.sqlite_yodalef.Student_Info.ADDRESS;
import static com.example.sqlite_yodalef.Student_Info.HOME_PHONE;
import static com.example.sqlite_yodalef.Student_Info.KEY_ID;
import static com.example.sqlite_yodalef.Student_Info.PARENT_NAME;
import static com.example.sqlite_yodalef.Student_Info.PARENT_PHONE;
import static com.example.sqlite_yodalef.Student_Info.STUDENT_NAME;
import static com.example.sqlite_yodalef.Student_Info.STUDENT_PHONE;
import static com.example.sqlite_yodalef.Student_Info.STUDENT_TABLE;

public class HelperDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbexam.db";
    private static final int DATABASE_VERSION = 1;
    String strCreate, strDelete;

    public HelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        strCreate="CREATE TABLE "+STUDENT_TABLE;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+STUDENT_NAME+" TEXT,";
        strCreate+=" "+ADDRESS+" TEXT,";
        strCreate+=" "+STUDENT_PHONE+" INTEGER,";
        strCreate+=" "+HOME_PHONE+" INTEGER,";
        strCreate+=" "+PARENT_NAME+" TEXT,";
        strCreate+=" "+PARENT_PHONE+" TEXT";
        strCreate+=");";
        db.execSQL(strCreate);
        strCreate="CREATE TABLE "+TABLE_GRADES;
        strCreate+=" ("+ KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+SUBJECT+" TEXT,";
        strCreate+=" "+QUARTER+" INTEGER,";
        strCreate+=" "+GRADE+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        strDelete="DROP TABLE IF EXISTS "+ STUDENT_TABLE;
        db.execSQL(strDelete);
        strDelete="DROP TABLE IF EXISTS "+ TABLE_GRADES;
        db.execSQL(strDelete);
        onCreate(db);
    }
}
