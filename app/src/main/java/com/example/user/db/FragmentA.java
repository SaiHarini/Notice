package com.example.user.db;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentA extends Fragment {
    TextView useruname,surname,age,username;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args1) {
        View view = inflater.inflate(R.layout.fragment_a,container,false);
        Bundle args = getArguments();
        String a = args.getString("useruname");
        String b = args.getString("surname");
        String c = args.getString("age");
        String d = args.getString("username");
        useruname = (TextView) view.findViewById(R.id.textView);
        surname = (TextView) view.findViewById(R.id.textView2);
        age = (TextView) view.findViewById(R.id.textView3);
        username = (TextView) view.findViewById(R.id.textView4);
        useruname.setText(a);
        surname.setText(b);
        age.setText(c);
        username.setText(d);
        return view;
    }
}
