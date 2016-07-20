package com.example.user.androidswitch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    NumberPicker np;
    EditText text1, text2;
    String saved = "ageData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        np = (NumberPicker) findViewById(R.id.numberPicker);
        text1 = (EditText) findViewById(R.id.editText);
        text2 = (EditText) findViewById(R.id.editText2);

        np.setMinValue(0);
        np.setMaxValue(100);
        np.setWrapSelectorWheel(false);

       np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
           @Override
           public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

           }
       });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){

            case R.id.new_age:

                np.setValue(0);
                np.scrollBy(0, 0);
                np.setWrapSelectorWheel(true);

                Toast toast1 = Toast.makeText(getApplicationContext(), "New Age Loaded", Toast.LENGTH_LONG);
                toast1.show();
                break;

            case R.id.load_age:

                int age_num = np.getValue();
                np.setValue(age_num);
                np.scrollBy(age_num, age_num);
                np.setWrapSelectorWheel(true);

                SharedPreferences loadAge = getSharedPreferences(saved, 0);
                age_num = loadAge.getInt("CurrentValue", 10);

                Toast toast2 = Toast.makeText(getApplicationContext(), "Age Loaded Successfully: " + age_num, Toast.LENGTH_LONG);
                toast2.show();
                break;

            case R.id.save_age:

                int saveNum = np.getValue();
                SharedPreferences saveAge = getSharedPreferences(saved, 0);
                SharedPreferences.Editor editor = saveAge.edit();
                editor.putInt("CurrentValue", saveNum);
                editor.apply();

                Toast toast3 = Toast.makeText(getApplicationContext(), "Age Saved Successfully: " + saveNum, Toast.LENGTH_LONG);
                toast3.show();

                break;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_main) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void nextView(View view) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("userAge", np.getValue())
                .putExtra("userName", text1.getText().toString())
                .putExtra("userMail", text2.getText().toString());
        startActivity(intent);
    }
}
