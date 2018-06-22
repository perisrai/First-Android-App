package com.example.peris.myapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nameedt;
    private EditText usernameedt;
    private EditText passwordedt;
    private EditText confirmpassedt;
    private EditText emailedt;
    private EditText contactedt;
    private Button signup;
    private String user, pass, username, name, email, contact, password, confirmpass;
    private SqliteDBHelper sqliteDBHelper;
    private boolean isExist = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        sqliteDBHelper = new SqliteDBHelper(this);
        signup = (Button) findViewById(R.id.signupButton);
        signup.setOnClickListener(this);

    }


    public void onClick(View view) {

        // Integer contact = Integer.valueOf(edit_contact.getText().toString());
        if (view.getId() == R.id.signupButton) {
            nameedt = (EditText) findViewById(R.id.edit_name);
            usernameedt = (EditText) findViewById(R.id.edit_username);
            passwordedt = (EditText) findViewById(R.id.edit_password);
            confirmpassedt = (EditText) findViewById(R.id.edit_confirmPass);
            emailedt = (EditText) findViewById(R.id.edit_email);
            contactedt = (EditText) findViewById(R.id.edit_contact);

            int id = view.getId();
            String name = nameedt.getText().toString();
            String username = usernameedt.getText().toString();
            String password = passwordedt.getText().toString();
            String confirmpass = confirmpassedt.getText().toString();
            String email = emailedt.getText().toString();
            String contact = contactedt.getText().toString();


            if (!password.equals(confirmpass)) {
                Toast pass = Toast.makeText(SignupActivity.this, "Password don't match!!!!", Toast.LENGTH_LONG);
                pass.show();
            }
                switch (id) {
                    case R.id.signupButton:
                        insertData(name, username, email, contact, password, confirmpass);
                        return;
                }


                Intent in = new Intent(SignupActivity.this, LoginActivity.class);
                in.putExtra(username, user);
                in.putExtra(password, pass);
                setResult(Activity.RESULT_OK, in);

                startActivity(in);
                finish();

        }
    }

    private void insertData(String name, String username, String email, String contact, String password, String confirmpass) {

        checkDuplication(name);
        if (isExist == true) {
            nameedt.setError("Username Already Exists");
            isExist = false;
            return;
        }

        SQLiteDatabase database = sqliteDBHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(SqliteDBHelper.NAME, name);
        value.put(SqliteDBHelper.USERNAME, username);
        value.put(SqliteDBHelper.PASSWORD, password);
        value.put(SqliteDBHelper.EMAIL, email);
        value.put(SqliteDBHelper.CONTACT, contact);

        //value.put(String.valueOf(SqliteDBHelper.CONTACT), contact);
        long id = database.insert(SqliteDBHelper.TABLE_NAME, null, value);

        Log.d("Test", id + "");
        if (id >= 0) {
            Toast.makeText(SignupActivity.this, "Data Insert", Toast.LENGTH_SHORT).show();
            Intent in=new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(in);
            finish();
        } else {
            Toast.makeText(SignupActivity.this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkDuplication(String name) {
        SQLiteDatabase database = sqliteDBHelper.getWritableDatabase();
        String field[] = {sqliteDBHelper.NAME};
        Cursor cursor = database.query(sqliteDBHelper.TABLE_NAME, field, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            String _name = cursor.getString(cursor.getColumnIndex(sqliteDBHelper.NAME));
            if (_name.equals(name)) {
                isExist = true;
                return;
            }
        }

    }
}
