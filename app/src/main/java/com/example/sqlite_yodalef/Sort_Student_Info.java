package com.example.sqlite_yodalef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.sqlite_yodalef.Grades.GRADE;
import static com.example.sqlite_yodalef.Grades.KEY_ID_G;
import static com.example.sqlite_yodalef.Student_Info.STUDENTS_TABLE;

public class Sort_Student_Info extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;
    Cursor cr;
    ListView lvGrades,lv;
    ListView lvsorted;
    ArrayList<String> tb1 = new ArrayList<>();
    ArrayAdapter Adp;
    int co11,co12,co13,co14,co15,co16,co17,co20,co21,co22,co23;

    String selectionArgs[] = new String[]{""};
    String [] columns = {Grades.SUBJECT};
    String selection = Grades.SUBJECT+"=?";
    String orderBy = null;

    EditText Subject;

    /**
     * @author Dvir Dadon
     * @Version 3.6.1
     * @since Unknown
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__student__info);
        lvGrades = findViewById(R.id.lvGrades);
        lv = findViewById(R.id.lv);
        Subject = findViewById(R.id.Subject);
        lvsorted = findViewById(R.id.lvsorted);
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();
        tb1 = new ArrayList<>();
        cr = db.query(Grades.TABLE_GRADES,null,null,null,null,null,null);
        co20 = cr.getColumnIndex(KEY_ID_G);
        co21 = cr.getColumnIndex(Grades.SUBJECT);
        co22 = cr.getColumnIndex(Grades.QUARTER);
        co23 = cr.getColumnIndex(GRADE);
        cr.moveToFirst();
        while (!cr.isAfterLast()){
            int key = cr.getInt(co20);
            String Subject = cr.getString(co21);
            int Quarter = cr.getInt(co22);
            int Grades = cr.getInt(co23);
            String tmp = ""+key+", "+Subject+", "+Quarter+", "+Grades;
            tb1.add(tmp);
            cr.moveToNext();

        }

        Adp = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,tb1);
        Adp.notifyDataSetChanged();
        lvGrades.setAdapter(Adp);

        tb1 = new ArrayList<>();
        cr = db.query(STUDENTS_TABLE,null,null,null,null,null,null);
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

    /**
     * The method sort the SQlite by grades(from low to high).
     * @param view
     */
    public void SortBy_Grades(View view) {
        orderBy = Grades.GRADE;
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();
        tb1 = new ArrayList<>();
        cr = db.query(Grades.TABLE_GRADES,null,null,null,null,null,orderBy);
        co20 = cr.getColumnIndex(KEY_ID_G);
        co21 = cr.getColumnIndex(Grades.SUBJECT);
        co22 = cr.getColumnIndex(Grades.QUARTER);
        co23 = cr.getColumnIndex(Grades.GRADE);
        cr.moveToFirst();
        while (!cr.isAfterLast()){
            int key = cr.getInt(co20);
            String Subject = cr.getString(co21);
            int Quarter = cr.getInt(co22);
            int Grades = cr.getInt(co23);
            String tmp = ""+key+", "+Subject+", "+Quarter+", "+Grades;
            tb1.add(tmp);
            cr.moveToNext();
        }
        Adp = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,tb1);
        lvsorted.setAdapter(Adp);
    }

    /**
     * The method Filter the SQlite by the user input.
     * @param view
     */
    public void Filter(View view) {
        String s = Subject.getText().toString();
        selectionArgs = new String[]{s};

        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();
        tb1 = new ArrayList<>();
        cr = db.query(Grades.TABLE_GRADES,null,selection,selectionArgs,null,null,null);
        co20 = cr.getColumnIndex(KEY_ID_G);
        co21 = cr.getColumnIndex(Grades.SUBJECT);
        co22 = cr.getColumnIndex(Grades.QUARTER);
        co23 = cr.getColumnIndex(Grades.GRADE);
        cr.moveToFirst();
        while (!cr.isAfterLast()){
            int key = cr.getInt(co20);
            String Subject = cr.getString(co21);
            int Quarter = cr.getInt(co22);
            int Grades = cr.getInt(co23);
            String tmp = ""+key+", "+Subject+", "+Quarter+", "+Grades;
            tb1.add(tmp);
            cr.moveToNext();
        }
        Adp = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,tb1);
        lvsorted.setAdapter(Adp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case (R.id.menuDataIn): {
                Intent s = new Intent(this, MainActivity.class);
                startActivity(s);
                break;
            }
            case (R.id.menuDelete): {
                Intent s = new Intent(this, Sort_Student_Info.class);
                startActivity(s);
                break;
            }
            case (R.id.Credits): {
                Intent s = new Intent(this, Credits.class);
                startActivity(s);
                break;
            }
            default:
                break;
        }
        return true;
    }
}
