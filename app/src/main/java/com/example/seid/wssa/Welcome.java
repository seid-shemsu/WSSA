package com.example.seid.wssa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by SEID on 3/7/2019.
 */

public class Welcome extends AppCompatActivity {
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("WSSA");
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i = new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    public void history(View view){
        Intent i = new Intent(this, History.class);
        name = getIntent().getStringExtra("username");
        i.putExtra("userName",name);
        startActivity(i);
    }
    public void billing(View view){
        Intent i = new Intent(this, Payment.class);
        name = getIntent().getStringExtra("username");
        i.putExtra("userName",name);
        startActivity(i);
    }
    public void report(View view){
        Intent i = new Intent(this, Report.class);
        startActivity(i);
    }
}
