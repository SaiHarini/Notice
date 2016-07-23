package com.example.user.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    EditText name;
    EditText surname;
    EditText age;
    EditText username;
    EditText pass;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        name = (EditText) findViewById(R.id.useruname);
        surname = (EditText) findViewById(R.id.surname);
        age = (EditText) findViewById(R.id.age);
        username = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        done = (Button) findViewById(R.id.button3);

    }

    public void onClickDone(View view) {
        String type = "login";
        Register register = new Register(this);
        String a = name.getText().toString();
        String b = surname.getText().toString();
        String c = age.getText().toString();
        String d = username.getText().toString();
        String e = pass.getText().toString();
        register.execute(type, a, b, c, d, e);
        name.setText("");
        surname.setText("");
        age.setText("");
        username.setText("");
        pass.setText("");
    }
}