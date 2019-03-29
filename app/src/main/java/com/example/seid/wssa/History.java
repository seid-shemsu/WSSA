package com.example.seid.wssa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by SEID on 3/7/2019.
 */

public class History extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
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
        view();
    }

    public void view() {
        listView = (ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        try {
            LocalHistory dbHelper = new LocalHistory(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String name[] = {"name", "month", "day", "amount"};
            String customer = getIntent().getStringExtra("userName");
            Cursor c = db.query("history", name, "name=?", new String[]{customer}, null, null, null);

            if (c.moveToFirst()) {
                do {
                    String s = "";
                   s += "" + c.getString(0);
                   s += "          " + c.getString(1);
                   s += "              " + c.getString(2);
                   s += "                   " + c.getString(3)+" birr";
                    arrayList.add(s);
                    arrayAdapter.notifyDataSetChanged();
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG);
        }

    }

}
