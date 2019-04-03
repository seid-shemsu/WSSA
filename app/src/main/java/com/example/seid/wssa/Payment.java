package com.example.seid.wssa;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("PAYMENT");
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent homeIntent = new Intent(Payment.this,MainActivity.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                return true;
            }
        });


        ///////////////////////////////////////////////// initializing items

        sp = (Spinner) findViewById(R.id.month);
        pay = (Button) findViewById(R.id.pay);
        amount = (TextView) findViewById(R.id.amount);
        bill = (Button) findViewById(R.id.viewbill);
        decline = (Button) findViewById(R.id.decline);
        /////////////////////////////////////////////////////////// set customer name on top

        cusName = (TextView) findViewById(R.id.cus_name);
        final String cID = getIntent().getStringExtra("cID");
        cusName.setText(cID);
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
                        PaymentConnection db = new PaymentConnection(Payment.this);
                        db.execute(cID, month, amount.getText().toString(),hour,day,year);
                        ////////////////////////////////////////////////////////// saving to local database
                        try{
                            SQLiteDatabase database = lDatabase.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("cID",cID);
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
