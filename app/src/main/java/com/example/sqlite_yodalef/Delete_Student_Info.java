package com.example.sqlite_yodalef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.sqlite_yodalef.Grades.KEY_ID_G;
import static com.example.sqlite_yodalef.Student_Info.KEY_ID;
import static com.example.sqlite_yodalef.Student_Info.STUDENTS_TABLE;

public class Delete_Student_Info extends AppCompatActivity implements AdapterView.OnItemClickListener{
    AlertDialog.Builder adb;
    SQLiteDatabase db;
    HelperDB hlp;
    Cursor cr;
    ListView lv,lvGrades;
    ArrayList<String> tb1 = new ArrayList<>();
    ArrayList<String> tb2 = new ArrayList<>();
    ArrayAdapter Adp,Adp2;
    int co11,co12,co13,co14,co15,co16,co17,co20,co21,co22,co23;

    /**
     * @author Dvir Dadon
     * @Version 3.6.1
     * @since Unknown
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__student__info);
        lvGrades = findViewById(R.id.lvGrades);
        lvGrades.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvGrades.setOnItemClickListener(this);

        lv = findViewById(R.id.lv);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(this);

        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();
        tb2 = new ArrayList<>();
        cr = db.query(Grades.TABLE_GRADES,null,null,null,null,null,null);
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
            tb2.add(tmp);
            cr.moveToNext();

        }

        Adp2 = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,tb2);
        Adp2.notifyDataSetChanged();
        lvGrades.setAdapter(Adp2);

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
        Adp.notifyDataSetChanged();
        lv.setAdapter(Adp);
    }

    /**
     * @since 28/2/2020
     * The Method check if the user did a long click if he do AlertDialog pop up and ask him if he want to delete the information he clicked(for each SQlite).
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        adb = new AlertDialog.Builder(this);
        adb.setTitle("Warning!");
        adb.setMessage("Are you sure you want to delete the information?");
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {
                adb.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db = hlp.getWritableDatabase();
                        db.delete(STUDENTS_TABLE, KEY_ID + "=?", new String[]{Integer.toString(pos+1)});
                        tb1.remove(pos);
                        Adp.notifyDataSetChanged();
                        db.close();
                    }
                });
                adb.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog ad = adb.create();
                ad.show();
                return true;
            }
        });
        lvGrades.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {
                adb.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db = hlp.getWritableDatabase();
                        db.delete(Grades.TABLE_GRADES, KEY_ID_G + "=?", new String[]{Integer.toString(pos+1)});
                        tb2.remove(pos);
                        Adp2.notifyDataSetChanged();
                        db.close();
                    }
                });
                adb.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog ad = adb.create();
                ad.show();
                return true;
            }
        });

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
            case (R.id.menuSort): {
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
