package com.example.sqlite_yodalef;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Update_Student_Info extends AppCompatActivity {

    SQLiteDatabase db;
    HelperDB hlp;
    Cursor cr;
    ListView lv;
    ArrayList<String> tb1 = new ArrayList<>();
    ArrayAdapter Adp;
    int co11,co12,co13,co14,co15,co16,co17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__student__info);
        lv = findViewById(R.id.lv);
        hlp = new HelperDB(this);
        db=hlp.getWritableDatabase();
        tb1 = new ArrayList<>();
        db.query(Student_Info.STUDENT_TABLE,null,null,null,null,null,null);
        co11=cr.getColumnIndex(Student_Info.KEY_ID);
        co12=cr.getColumnIndex(Student_Info.STUDENT_NAME);
        co13=cr.getColumnIndex(Student_Info.ADDRESS);
        co14=cr.getColumnIndex(Student_Info.STUDENT_PHONE);
        co15=cr.getColumnIndex(Student_Info.HOME_PHONE);
        co16=cr.getColumnIndex(Student_Info.PARENT_NAME);
        co17=cr.getColumnIndex(Student_Info.PARENT_PHONE);
        cr.moveToFirst();
        while (!cr.isAfterLast()){
            int key = cr.getInt(co11);
            String name =cr.getString(co12);
            String Address=cr.getString(co13);
            int Phone_num=cr.getInt(co14);
            int home_phone=cr.getInt(co15);
            String P_name=cr.getString(co16);
            String P_Phone=cr.getString(co17);
            String tmp = ""+key+", "+name+", "+Address+", "+Phone_num+", "+home_phone+", "+P_name+", "+P_Phone;
            tb1.add(tmp);
            cr.moveToNext();
        }
        cr.close();
        db.close();
        Adp = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,tb1);
        lv.setAdapter(Adp);
    }
}
