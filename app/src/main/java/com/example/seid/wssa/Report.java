package com.example.seid.wssa;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by SEID on 3/7/2019.
 */

public class Report  extends AppCompatActivity {
    Spinner sp ;
    String problem;
    Button request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Report");
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent homeIntent = new Intent(Report.this,MainActivity.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                return true;
            }
        });

        request = (Button) findViewById(R.id.request);
        sp = (Spinner) findViewById(R.id.problem);
        final String cID = getIntent().getStringExtra("userName");
        /////////////////////////////////////////////////////////// set spinner value or choose problem
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(Report.this,R.array.report,
                android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(arrayAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                problem = sp.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        ////////////////////////////////////////////////////////////////////////////////
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Req db = new Req(Report.this);
                    db.execute(cID,problem);
                    finish();
                }
                catch (Exception e){

                }
            }
        });
    }


}
