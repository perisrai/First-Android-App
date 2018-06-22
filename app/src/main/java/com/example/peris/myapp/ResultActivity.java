package com.example.peris.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    private EditText username;
    public static final String USERNAME = "user_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        username = (EditText) findViewById(R.id.userName);
        Button setResultOk = (Button) findViewById(R.id.setResultOk);
        setResultOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = username.getText().toString();
                if (string.isEmpty()) {
                    Toast.makeText(ResultActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra(USERNAME, string);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });

    }
}
