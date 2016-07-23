package com.example.user.db;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class FragmentsClientB extends Fragment {
    final int port = 8080;
    TextView msg;
    Socket socket = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.client_b,container,false);
        msg = (TextView)view.findViewById(R.id.textView5);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new Thread(new Runnable(){

            @Override
            public void run() {
                    SerevrAsyncTask serevrAsyncTask = new SerevrAsyncTask();
                    serevrAsyncTask.execute();
            }
        }).start();

    }
    class SerevrAsyncTask extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... params) {
            String res="p";

            InputStream is = null;
            //PrintWriter out = null;
            //BufferedReader br = null;
            try {
                socket = new Socket("192.168.0.100", port);
                Log.d("CREATION", "1");
                Log.d("CREATION", "2");
                is= socket.getInputStream();
                Log.d("CREATION", "3");
                Scanner scan= new Scanner(is);
               // out = new PrintWriter(socket.getOutputStream(),true);
               // br= new BufferedReader(new InputStreamReader(is));
                Log.d("CREATION", "4");
                res = scan.next();
                Log.d("CREATION", res);
                //out.write(params[2]);

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    assert is != null;
                    is.close();
                    socket.close();
                    //out.close();
                    //is.close();
                    //br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("CREATION", s);
            msg.setText(s);
            Log.d("CREATION", "7");
        }
    }
}