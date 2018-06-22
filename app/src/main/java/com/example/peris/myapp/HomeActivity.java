package com.example.peris.myapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView username_txt;
    private Button logout_btn;
    private TextView editprofile_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username_txt= findViewById(R.id.user_home);
        SharedPreferences SharedPref= getSharedPreferences("FirstApp", Context.MODE_PRIVATE);
        String _userSession=SharedPref.getString("userName",null);
        username_txt.setText("Welcome,  "+_userSession);

        logout_btn=findViewById(R.id.logout_home);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences SharedPref= getSharedPreferences("FirstApp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=SharedPref.edit();
                editor.remove("isLoggedin");
                editor.apply();
                Intent in=new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(in);
            }
        });

        editprofile_txt=findViewById(R.id.editprofile_home);
        editprofile_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(HomeActivity.this, EditProfileActivity.class);
                startActivity(in);
            }
        });
    }
}
