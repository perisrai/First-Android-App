package com.example.peris.myapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private TextView signUp;
    public static int REQUEST_CODE = 123;
    public static String USER_NAME_TAG = "username";
    private EditText username, password;
    private Button loginButton;
    private String user, pass;
    public int passId;

    private String usernamestr;// = "admin";
   private String passwordstr;//= "admin";
    SqliteDBHelper sqliteDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sqliteDBHelper= new SqliteDBHelper(this);
        SharedPreferences shared = getSharedPreferences("MYAPPLICATION", Context.MODE_PRIVATE);
        Boolean isLogin = shared.getBoolean("isLogin", false);
        if (isLogin) {
            Intent in = new Intent(LoginActivity.this, RecyclerViewActivity.class);
            startActivity(in);
            finish();
        }
        signUp = (TextView) findViewById(R.id.sign_up);
        username = (EditText) findViewById(R.id.edit_username);
        password = (EditText) findViewById(R.id.edit_password);
        loginButton = (Button) findViewById(R.id.loginButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();
                if(retriveUserPass()) {
                    SharedPreferences SharedPref= getSharedPreferences("FirstApp", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=SharedPref.edit();
                    editor.putBoolean("isLoggedin",true);
                    editor.putString("userName",user);
                    editor.putInt("sessionId",passId);
                    editor.commit();
                    Intent in = new Intent(LoginActivity.this, RecyclerViewActivity.class);
                    startActivity(in);
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                }


              if (user.equals(usernamestr) && pass.equals(passwordstr)) {

                    Intent in = new Intent(LoginActivity.this, SignupActivity.class);
                    startActivity(in);
                    finish();
                }
            }
        });


       signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 /*Intent in = new Intent(LoginActivity.this, TestActivity.class);
                in.putExtra(USER_NAME_TAG, "Rabindra");
                startActivity(in);*/
                Intent in = new Intent(LoginActivity.this, SignupActivity.class);
                startActivityForResult(in, REQUEST_CODE);
            }
        });
    }

    private boolean retriveUserPass() {
        int check=0;
        if(user.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Enter Username", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(pass.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        SQLiteDatabase database = sqliteDBHelper.getWritableDatabase();
        String field[] = {SqliteDBHelper.USERNAME, SqliteDBHelper.PASSWORD,SqliteDBHelper.ID};
        Cursor cursor = database.query(SqliteDBHelper.TABLE_NAME, field, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String _username = cursor.getString(cursor.getColumnIndex(SqliteDBHelper.USERNAME));
            String _pass = cursor.getString(cursor.getColumnIndex(SqliteDBHelper.PASSWORD));
            passId= Integer.parseInt(cursor.getString(cursor.getColumnIndex(SqliteDBHelper.ID)));
            if (_username.equals(user) && _pass.equals(pass)) {
                check=1;
                return true;
            }

        }
        if(check==1){return  true;}
        else {
            Toast.makeText(LoginActivity.this,"Invalid Username/password",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("Test", requestCode + " : " + resultCode + " : " + data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String user = data.getStringExtra(ResultActivity.USERNAME);
                username.setText(user);
                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
