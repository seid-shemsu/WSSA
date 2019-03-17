package com.example.seid.wssa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by SEID on 3/7/2019.
 */

public class Welcome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
    }

    public void history(View view){
        Intent i = new Intent(this, History.class);
        startActivity(i);
    }
    public void billing(View view){
        Intent i = new Intent(this, Payment.class);
        startActivity(i);
    }
    public void report(View view){
        Intent i = new Intent(this, Report.class);
        startActivity(i);
    }
    public void help(View view){
        Intent i = new Intent(this, Help.class);
        startActivity(i);
    }

}
