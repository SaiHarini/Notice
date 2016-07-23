package com.example.user.db;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FragmentB extends Fragment implements View.OnClickListener{
    final int port = 8080;
    EditText msg;
    Button butt;
    String s;
    Socket socket = null;
    int flag=1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b,container,false);
        msg = (EditText)view.findViewById(R.id.editText4);
        butt = (Button) view.findViewById(R.id.button5);
        butt.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)  {
        super.onActivityCreated(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    while (flag==1){
                    socket = serverSocket.accept();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    @Override
    public void onClick(View v) {
        Log.d("CREATION", "click");
        s=msg.getText().toString();
            SerevrAsyncTask serevrAsyncTask = new SerevrAsyncTask();
            serevrAsyncTask.execute(socket);
    }

    class SerevrAsyncTask extends AsyncTask<Socket,Void,String> {

        @Override
        protected String doInBackground(Socket... params) {
            String res="hghghh";
            Socket socket = params[0];
            Log.d("CREATION", "1");
           // InputStream is = null;
            PrintWriter out = null;
           // BufferedReader br = null;
            try {
                Log.d("CREATION", "2");
               // is = socket.getInputStream();
                out = new PrintWriter(socket.getOutputStream());
                Log.d("CREATION", "3");
                out.println(s);
                Log.d("CREATION", "4");
               // br = new BufferedReader(new InputStreamReader(is));
                //res = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    assert out != null;
                    out.close();
                    socket.close();
                    //
                    //is.close();
                   // br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("CREATION", s);
           // msg.setText(s);
            Log.d("CREATION", "7");
        }
    }
}