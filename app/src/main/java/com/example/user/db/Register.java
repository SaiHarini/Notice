package com.example.user.db;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Register extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog.Builder alertDialog;
    public Register(Context context) {
        this.context = context;
    }
    String useruname;
    String surname;
    String age;
    String username;
    String password;

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://10.50.1.49:81/register.php";
        if(type.equals("login")){

            try {
                useruname=params[1];
                surname=params[2];
                age = params[3];
                username = params[4];
                password = params[5];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                Log.d("CREATION", "2vvvvv");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                Log.d("CREATION", "2dfdf");
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postData = URLEncoder.encode("useruname", "UTF-8")+"="+ URLEncoder.encode(useruname, "UTF-8")+"&"+
                        URLEncoder.encode("surname", "UTF-8")+"="+ URLEncoder.encode(surname, "UTF-8")+"&"+
                        URLEncoder.encode("age", "UTF-8")+"="+ URLEncoder.encode(age, "UTF-8")+"&"+
                        URLEncoder.encode("username", "UTF-8")+"="+ URLEncoder.encode(username, "UTF-8")+"&"+
                        URLEncoder.encode("password", "UTF-8")+"="+ URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
                while((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Success")){
            Log.d("CREATION", "here");
            Intent intent = new Intent(context,TabbedActivity.class);
            intent.putExtra("useruname",useruname);
            intent.putExtra("surname",surname);
            intent.putExtra("age",age);
            intent.putExtra("username",username);
            context.startActivity(intent);
            Log.d("CREATION", "here");
        }
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}