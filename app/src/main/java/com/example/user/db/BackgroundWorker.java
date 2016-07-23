package com.example.user.db;

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

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    public BackgroundWorker(Context context) {
        this.context = context;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://10.50.2.252:81/login.php";
        if(type.equals("login")){
            Log.d("CREATION", "1");
            try {
                Log.d("CREATION", "2mmm");
                String username = params[1];
                String password = params[2];
                Log.d("CREATION", "2nnnnn");
                URL url = new URL(login_url);
                Log.d("CREATION", "2uuuu");
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                Log.d("CREATION", "2vvvvv");
                httpURLConnection.setRequestMethod("POST");
                Log.d("CREATION", "2sssss");
                httpURLConnection.setDoOutput(true);
                Log.d("CREATION", "2aaaaaa");
                httpURLConnection.setDoInput(true);
                Log.d("CREATION", "2iiiiii");
                OutputStream outputStream = null;
                try {
                    outputStream= httpURLConnection.getOutputStream();
                    Log.d("CREATION", "2qqqq");
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    Log.d("CREATION", "2fffff");
                    String postData = URLEncoder.encode("username", "UTF-8")+"="+ URLEncoder.encode(username, "UTF-8")+"&"+
                            URLEncoder.encode("password", "UTF-8")+"="+ URLEncoder.encode(password, "UTF-8");
                    Log.d("CREATION", "2.1.1");
                    bufferedWriter.write(postData);
                    Log.d("CREATION", "2.1.1.1..1");
                    bufferedWriter.flush();
                    bufferedWriter.close();

                }catch (Exception e){
                   Log.d("CREATION",e.getMessage());
                }



                outputStream.close();
                Log.d("CREATION", "2");
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
                Log.d("CREATION", "3");
                while((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                Log.d("CREATION", "4");
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
        Log.d("CREATION", "5");
        Log.d("CREATION", result);
        if(result.contains("Success")){
            Log.d("CREATION", "5");
            String[] ans = result.split(" ");
            Intent intent = new Intent(context,TabbedActivity.class);
            intent.putExtra("useruname",ans[1]);
            intent.putExtra("surname",ans[2]);
            intent.putExtra("age",ans[3]);
            intent.putExtra("username",ans[4]);
            context.startActivity(intent);
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
