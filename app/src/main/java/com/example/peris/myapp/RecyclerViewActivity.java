package com.example.peris.myapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SqliteDBHelper sqliteDBHelper;
    private StudentAdapter studentAdapter;
    private Button logout;
    private List<Student> stds =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView=(RecyclerView) findViewById(R.id.activity_recycler_view);
        //recyclerView=(RecyclerView) findViewById(R.id.progress_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        sqliteDBHelper=new SqliteDBHelper(this);



        SQLiteDatabase database = sqliteDBHelper.getWritableDatabase();
        String field[] = {SqliteDBHelper.NAME,SqliteDBHelper.EMAIL,SqliteDBHelper.CONTACT};
        Cursor cursor = database.query(SqliteDBHelper.TABLE_NAME, field, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(SqliteDBHelper.NAME));
            String email = cursor.getString(cursor.getColumnIndex(SqliteDBHelper.EMAIL));
            String contact = (cursor.getString(cursor.getColumnIndex(SqliteDBHelper.CONTACT)));

            Student obj= new Student(name, email, contact);
            stds.add(obj);
            studentAdapter = new StudentAdapter(this, stds);
            recyclerView.setAdapter(studentAdapter);

        }
      /*  Users obj=new Users("ab","a","as", (long) 123);
        users.add(obj);*/

        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences SharedPref = getSharedPreferences("FirstApp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = SharedPref.edit();
                editor.remove("isLoggedin");
                editor.apply();
                Intent in = new Intent(RecyclerViewActivity.this, LoginActivity.class);
                startActivity(in);
                finish();
            }
        });

    }


}
