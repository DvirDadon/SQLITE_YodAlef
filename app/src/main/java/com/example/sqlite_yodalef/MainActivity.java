package com.example.sqlite_yodalef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;
    EditText S_Name,Address,S_Phone,HomePhone,M_Name,M_Phone,D_N,D_P,Subject,Quarter,Grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        S_Name = (EditText)findViewById(R.id.S_Name);
        Address = (EditText)findViewById(R.id.Address);
        S_Phone = (EditText)findViewById(R.id.S_Phone);
        HomePhone = (EditText)findViewById(R.id.HomePhone);
        M_Name = (EditText)findViewById(R.id.P_Name);
        M_Phone = (EditText)findViewById(R.id.P_Phone);
        Subject = (EditText)findViewById(R.id.Subject);
        Quarter = (EditText)findViewById(R.id.Quarter);
        Grade = (EditText)findViewById(R.id.Grade);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
    }

    public void Data_In(View view) {
        String Students = S_Name.getText().toString();
        String address = Address.getText().toString();
        String Phone = S_Phone.getText().toString();
        String HP = HomePhone.getText().toString();
        String Mom_Name = M_Name.getText().toString();
        String Mom_Phone = M_Phone.getText().toString();
        int p,hp,m_phone;

         p=Integer.parseInt(Phone);
         hp=Integer.parseInt(HP);
         m_phone = Integer.parseInt(Mom_Phone);

         ContentValues cv = new ContentValues();
         cv.put(Student_Info.STUDENTS,Students);
         cv.put(Student_Info.ADDRESS,address);
         cv.put(Student_Info.STUDENT_PHONE,p);
         cv.put(Student_Info.HOME_PHONE,hp);
         cv.put(Student_Info.MOM_NAME,Mom_Name);
         cv.put(Student_Info.MOM_PHONE,m_phone);

         db = hlp.getWritableDatabase();
         db.insert(Student_Info.STUDENTS_TABLE, null, cv);
         db.close();

    }


    public void Grades_In(View view) {
        String sub = Subject.getText().toString();
        String Quart = Quarter.getText().toString();
        String grade = Grade.getText().toString();

        int Q =  Integer.parseInt(Quart);
        int G = Integer.parseInt(grade);

        ContentValues cv = new ContentValues();
        cv.put(Grades.SUBJECT,sub);
        cv.put(Grades.QUARTER,Q);
        cv.put(Grades.GRADE,G);

        db = hlp.getWritableDatabase();
        db.insert(Grades.TABLE_GRADES,null,cv);
        db.close();
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
            case (R.id.menuUpdate): {
                Intent s = new Intent(this, Update_Student_Info.class);
                startActivity(s);
                break;
            }
            case (R.id.menuSort): {
                Intent s = new Intent(this, Sort_Student_Info.class);
                startActivity(s);
                break;
            }
            default:
                break;
        }
        return true;
    }
}
