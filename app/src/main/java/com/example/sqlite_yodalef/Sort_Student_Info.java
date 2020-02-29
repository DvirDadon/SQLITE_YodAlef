package com.example.sqlite_yodalef;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Sort_Student_Info extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;
    Cursor cr;
    ListView lv,lvsort;
    ArrayList<String> tb1 = new ArrayList<>();
    ArrayAdapter Adp;
    int co11,co12,co13,co14,co15,co16,co17,co18,co19;

    String selectionArgs[] = new String[]{""};
    String [] columns = {Student_Info.ADDRESS};
    String selection = Student_Info.ADDRESS+"=?";
    String orderBy = null;

    EditText FAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort__student__info);
        lv =(ListView) findViewById(R.id.lv);
        lvsort =(ListView) findViewById(R.id.lvsort);
        FAddress =(EditText) findViewById(R.id.FAddress);
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();

        tb1 = new ArrayList<>();
        cr = db.query(Student_Info.STUDENTS_TABLE,null,null,null,null,null,null);
        co11 = cr.getColumnIndex(Student_Info.KEY_ID);
        co12 = cr.getColumnIndex(Student_Info.STUDENTS);
        co13 = cr.getColumnIndex(Student_Info.ADDRESS);
        co14 = cr.getColumnIndex(Student_Info.STUDENT_PHONE);
        co15 = cr.getColumnIndex(Student_Info.HOME_PHONE);
        co16 = cr.getColumnIndex(Student_Info.MOM_NAME);
        co17 = cr.getColumnIndex(Student_Info.MOM_PHONE);
        cr.moveToFirst();
        while (!cr.isAfterLast()){
            int key = cr.getInt(co11);
            String name = cr.getString(co12);
            String Address = cr.getString(co13);
            int Phone_num = cr.getInt(co14);
            int home_phone = cr.getInt(co15);
            String M_name = cr.getString(co16);
            int M_Phone = cr.getInt(co17);
            String tmp = ""+key+", "+name+", "+Address+", "+Phone_num+", "+home_phone+", "+M_name+", "+M_Phone;
            tb1.add(tmp);
            cr.moveToNext();
        }
        cr.close();
        db.close();
        Adp = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,tb1);
        lv.setAdapter(Adp);
    }

    public void SortBy_Grades(View view) {
    }

    public void SortBy_Name(View view) {
        orderBy = Student_Info.STUDENTS;
        db = hlp.getReadableDatabase();

        tb1 = new ArrayList<>();
        cr = db.query(Student_Info.STUDENTS_TABLE,null,null,null,null,null,orderBy);
        co11 = cr.getColumnIndex(Student_Info.KEY_ID);
        co12 = cr.getColumnIndex(Student_Info.STUDENTS);
        co13 = cr.getColumnIndex(Student_Info.ADDRESS);
        co14 = cr.getColumnIndex(Student_Info.STUDENT_PHONE);
        co15 = cr.getColumnIndex(Student_Info.HOME_PHONE);
        co16 = cr.getColumnIndex(Student_Info.MOM_NAME);
        co17 = cr.getColumnIndex(Student_Info.MOM_PHONE);
        cr.moveToFirst();
        while (!cr.isAfterLast()){
            int key = cr.getInt(co11);
            String name = cr.getString(co12);
            String Address = cr.getString(co13);
            int Phone_num = cr.getInt(co14);
            int home_phone = cr.getInt(co15);
            String M_name = cr.getString(co16);
            int M_Phone = cr.getInt(co17);
            String tmp = ""+key+", "+name+", "+Address+", "+Phone_num+", "+home_phone+", "+M_name+", "+M_Phone;
            tb1.add(tmp);
            cr.moveToNext();
        }
        cr.close();
        db.close();
        Adp = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,tb1);
        lvsort.setAdapter(Adp);
        orderBy = null;
    }

    public void Filter(View view) {
        String s = FAddress.getText().toString();
        selectionArgs = new String[]{s};
        db = hlp.getReadableDatabase();

        tb1 = new ArrayList<>();
        cr = db.query(Student_Info.STUDENTS_TABLE,null,selection,selectionArgs,null,null,null);
        co11 = cr.getColumnIndex(Student_Info.KEY_ID);
        co12 = cr.getColumnIndex(Student_Info.STUDENTS);
        co13 = cr.getColumnIndex(Student_Info.ADDRESS);
        co14 = cr.getColumnIndex(Student_Info.STUDENT_PHONE);
        co15 = cr.getColumnIndex(Student_Info.HOME_PHONE);
        co16 = cr.getColumnIndex(Student_Info.MOM_NAME);
        co17 = cr.getColumnIndex(Student_Info.MOM_PHONE);

        cr.moveToFirst();
        while (!cr.isAfterLast()){
            int key = cr.getInt(co11);
            String name = cr.getString(co12);
            String Address = cr.getString(co13);
            int Phone_num = cr.getInt(co14);
            int home_phone = cr.getInt(co15);
            String M_name = cr.getString(co16);
            int M_Phone = cr.getInt(co17);
            String tmp = ""+key+", "+name+", "+Address+", "+Phone_num+", "+home_phone+", "+M_name+", "+M_Phone;
            tb1.add(tmp);
            cr.moveToNext();
        }
        cr.close();
        db.close();
        Adp = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,tb1);
        lvsort.setAdapter(Adp);

    }
}
