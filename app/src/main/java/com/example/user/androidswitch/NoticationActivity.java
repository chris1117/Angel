package com.example.user.androidswitch;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by User on 7/9/2016.
 */
public class NoticationActivity extends Activity {

    int getAge;
    String showAge;
    EditText tView, eView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notification_main);

        Bundle extras = getIntent().getExtras();
        getAge = extras.getInt("sendAge");
        showAge = Integer.toString(extras.getInt("sendAge"));

        tView = (EditText) findViewById(R.id.textView15);
        eView = (EditText) findViewById(R.id.editText4);

        if (getAge >= 0 && getAge <=12){

            tView.setText("Toddler/Adolescence");
            eView.setText(showAge);
        }

        else if (getAge > 12 && getAge < 20){

            tView.setText("Teenager");
            eView.setText(showAge);
        }

        else if (getAge >= 20 && getAge <= 50) {

            tView.setText("Youth/Adult");
            eView.setText(showAge);
        }

        else{

            tView.setText("Senior");
            eView.setText(showAge);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    }
}
