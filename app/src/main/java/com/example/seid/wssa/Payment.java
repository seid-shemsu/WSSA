package com.example.seid.wssa;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.MovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Date;

import static android.R.color.black;
import static android.R.color.white;


/**
 * Created by SEID on 3/7/2019.
 */

public class Payment extends AppCompatActivity {
    Spinner sp;
    Button bill, pay, decline;
    TextView amount, cusName;
    String month;
    LocalHistory lDatabase ;


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing);
        lDatabase = new LocalHistory(this);
        try {
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
        catch (Exception e){
            Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_LONG).show();
        }

        ///////////////////////////////////////////////// initializing items

        sp = (Spinner) findViewById(R.id.month);
        pay = (Button) findViewById(R.id.pay);
        amount = (TextView) findViewById(R.id.amount);
        bill = (Button) findViewById(R.id.viewbill);
        decline = (Button) findViewById(R.id.decline);
        /////////////////////////////////////////////////////////// set customer name on top

        cusName = (TextView) findViewById(R.id.cus_name);
        final String name = getIntent().getStringExtra("userName");
        cusName.setText(name);
        /////////////////////////////////////////////////////////// set spinner value or choose month


        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(Payment.this, R.array.month_english
                , android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                month = sp.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //////////////////////////////////////////////////////////  random bill generator
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = (Math.random() * 1000);
                int b = (int) a;
                amount.setText(Integer.toString(b));
                bill.setBackgroundResource(R.drawable.noterror);
                pay.setEnabled(true);
            }
        });
        /////////////////////////////////////////////////////////////////// payment part
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay.setEnabled(false);
                if (amount.getText().toString() != "") {
                    try {
                        String year="", day="", hour="" ;
                        Date date = new Date();
                        char d[] = date.toString().toCharArray();
                        for(int i=date.toString().length()-4;i<date.toString().length();i++)
                            year+=Character.toString(d[i]);
                        for(int i=8;i<=9;i++){
                            day+=Character.toString(d[i]);
                        }
                        for(int i=11;i<=15;i++){
                            hour+=Character.toString(d[i]);
                        }
                        Database db = new Database(Payment.this);
                        db.execute(name, month, amount.getText().toString(),hour,day,year);
                        ////////////////////////////////////////////////////////// saving to local database
                        try{
                            SQLiteDatabase database = lDatabase.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("name",name);
                            values.put("day",day);
                            values.put("month",month.toString());
                            values.put("amount",amount.getText().toString());
                            long row = database.insert("history",null,values);
                            Toast.makeText(getBaseContext(),Long.toString(row),Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e){
                            Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_LONG).show();
                        }
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(Payment.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    bill.setBackgroundResource(R.drawable.error);
                    bill.setTextColor(getResources().getColor(white));
                    Toast.makeText(Payment.this, "check your bill first", Toast.LENGTH_LONG).show();
                }


            }
        });
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }




}
