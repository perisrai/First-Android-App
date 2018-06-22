package com.example.peris.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {
    public String _userSession;
    private SqliteDBHelper editDbHelper;
    private EditText Nameedt,Emailedt,Contactedt,Locationedt;
    private EditText Passwordedt,ConfimPasswordedt;
    private String eName,eEmail, eContact;

    private Button update_btn;
    private boolean isExists=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Nameedt=findViewById(R.id.user_name_edit);
        Emailedt=findViewById(R.id.email_edit);

        Contactedt=findViewById(R.id.contact_edit);

        editDbHelper=new SqliteDBHelper(this);
        SharedPreferences SharedPref= getSharedPreferences("FirstApp", Context.MODE_PRIVATE);
        _userSession=SharedPref.getString("userName",null);
        retriveDataEdit(_userSession);
        Nameedt.setText(eName);
        Emailedt.setText(eEmail);

        Contactedt.setText(eContact);

        update_btn=findViewById(R.id.update_btn_eddit);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                udpateEntry(_userSession);
            }
        });

    }

    private void udpateEntry(String updatename) {
        String nam=Nameedt.getText().toString();
        String em=Emailedt.getText().toString();

        String cont=Contactedt.getText().toString();
        Passwordedt=findViewById(R.id.password_edit);
        ConfimPasswordedt=findViewById(R.id.confirm_password_edit);
        String pass=Passwordedt.getText().toString();
        String cpass=ConfimPasswordedt.getText().toString();
        if(nam.isEmpty()){
            Nameedt.setError("username can't be empty");
            return;
        }
        if(pass.isEmpty()){
            Passwordedt.setError("Password can't be empty");
            return;
        }
        if(cpass.isEmpty()){
            ConfimPasswordedt.setError("Please re-enter password");
            return;
        }
        if(!(pass.equals(cpass))){
            ConfimPasswordedt.setError("Passwords do not match");
            return;
        }

        checkDuplication(nam);
        if (isExists==true){
            Nameedt.setError("Username Already Exists");
            isExists=false;
            return;
        }
        SQLiteDatabase database=editDbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(SqliteDBHelper.NAME,nam);
        contentValues.put(SqliteDBHelper.EMAIL,em);
        contentValues.put(SqliteDBHelper.CONTACT,cont);
        contentValues.put(SqliteDBHelper.PASSWORD,pass);
        String[] whereArgs={updatename};
        int count=database.update(SqliteDBHelper.TABLE_NAME,contentValues,SqliteDBHelper.NAME + " = ? ",whereArgs);
        Toast.makeText(EditProfileActivity.this,"Update",Toast.LENGTH_SHORT).show();

    }

    private void checkDuplication(String nam) {
        SQLiteDatabase database = editDbHelper.getWritableDatabase();
        String field[] = {SqliteDBHelper.NAME};
        Cursor cursor = database.query(SqliteDBHelper.TABLE_NAME, field, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String _name = cursor.getString(cursor.getColumnIndex(SqliteDBHelper.NAME));
            if(_name.equals(nam)){
                isExists=true;
                return;
            }
        }
    }

    private void retriveDataEdit(String namsession) {
        SQLiteDatabase database=editDbHelper.getWritableDatabase();
        String field[] = {SqliteDBHelper.NAME,SqliteDBHelper.EMAIL,SqliteDBHelper.CONTACT};
        Cursor cursor = database.query(SqliteDBHelper.TABLE_NAME, field, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String _name = cursor.getString(cursor.getColumnIndex(SqliteDBHelper.NAME));
if(_name.equals(namsession)){
    eName=_name;
    eEmail=cursor.getString(cursor.getColumnIndex(SqliteDBHelper.EMAIL));

    eContact= cursor.getString(cursor.getColumnIndex(SqliteDBHelper.CONTACT));
}
        }
    }
}
