package com.example.seid.wssa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by SEID on 3/7/2019.
 */

public class Payment  extends AppCompatActivity {
    Spinner sp;
    Button bill,pay;
    TextView amount,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing);

        sp = (Spinner) findViewById(R.id.month);
        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(this,R.array.month_amharic,android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad);

        amount = (TextView) findViewById(R.id.amount);
        bill = (Button) findViewById(R.id.viewbill);
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a=(Math.random()*1000);
                int b = (int) a;
                amount.setText(Integer.toString(b));
            }
        });

        name = (TextView) findViewById(R.id.cus_name);
        pay = (Button) findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paid();
                amount.setText("");
            }
        });
    }
    public void paid(){
        Toast.makeText(this,"paid successfully",Toast.LENGTH_SHORT).show();
    }
}
