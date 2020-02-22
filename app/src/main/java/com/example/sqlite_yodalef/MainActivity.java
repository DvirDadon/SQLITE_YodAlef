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
    EditText S_Name,Address,S_Phone,HomePhone,P_Name,P_Phone,Subject,Quarter,Grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        S_Name=(EditText)findViewById(R.id.S_Name);
        Address=(EditText)findViewById(R.id.Address);
        S_Phone=(EditText)findViewById(R.id.S_Phone);
        HomePhone=(EditText)findViewById(R.id.HomePhone);
        P_Name=(EditText)findViewById(R.id.P_Name);
        P_Phone=(EditText)findViewById(R.id.P_Phone);
        Subject=(EditText)findViewById(R.id.Subject);
        Quarter=(EditText)findViewById(R.id.Quarter);
        Grade=(EditText)findViewById(R.id.Grade);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
    }

    public void Data_In(View view) {
        String Students=S_Name.getText().toString();
        String address = Address.getText().toString();
        String Phone=S_Phone.getText().toString();
        String HP=HomePhone.getText().toString();
        String Parent_Name=P_Name.getText().toString();
        String Parent_Phone=P_Phone.getText().toString();
        String G=Grade.getText().toString();
        String S=Subject.getText().toString();
        String Q=Quarter.getText().toString();
        int p,hp,grade,p_phone,quarter;

         p=Integer.parseInt(Phone);
         hp=Integer.parseInt(HP);
         grade= Integer.parseInt(G);
         p_phone=Integer.parseInt(Parent_Phone);
         quarter=Integer.parseInt(Q);

         ContentValues cv = new ContentValues();
         cv.put(Student_Info.STUDENTS,Students);
         cv.put(Student_Info.ADDRESS,address);
         cv.put(Student_Info.STUDENT_PHONE,p);
         cv.put(Student_Info.HOME_PHONE,hp);
         cv.put(Student_Info.PARENT_NAME,Parent_Name);
         cv.put(Student_Info.PARENT_PHONE,p_phone);
        db=hlp.getWritableDatabase();
        db.insert(Student_Info.STUDENTS_TABLE, null, cv);
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
