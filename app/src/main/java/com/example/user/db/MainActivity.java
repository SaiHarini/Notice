package com.example.user.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText uname,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname = (EditText)findViewById(R.id.editText);
        pass = (EditText)findViewById(R.id.editText2);
    }
    public void onClickLogin(View view){
        String username = uname.getText().toString();
        String password = pass.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
        uname.setText("");
        pass.setText("");
    }
    public void onSignup(View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);

    }
}
