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
    String cID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("WSSA");
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent homeIntent = new Intent(Welcome.this,MainActivity.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    public void history(View view){
        Intent i = new Intent(this, History.class);
        cID = getIntent().getStringExtra("cID");
        i.putExtra("cID",cID);
        startActivity(i);
    }
    public void billing(View view){
        Intent i = new Intent(this, Payment.class);
        cID = getIntent().getStringExtra("cID");
        i.putExtra("cID",cID);
        startActivity(i);
    }
    public void report(View view){
        Intent i = new Intent(this, Report.class);
        cID = getIntent().getStringExtra("cID");
        i.putExtra("cID",cID);
        startActivity(i);
    }
}
