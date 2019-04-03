package com.example.seid.wssa;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.Manifest.permission.INTERNET;

public class MainActivity extends AppCompatActivity {
    Button submit;
    TextInputLayout inputUser,inputPass;
    EditText cID, password;
    public String Username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(MainActivity.this, Welcome.class);
                //startActivity(i);
                try{
                    Username = cID.getText().toString();
                    if(check()){
                        if(isConnected()){
                            Login backend = new Login(MainActivity.this);
                            backend.execute(cID.getText().toString(    ),password.getText().toString());

                        }
                        else{
                            Toast.makeText(getBaseContext(),"check your internet please",Toast.LENGTH_SHORT).show();
                        }
                     }
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }
    public void initialize(){
        submit = (Button) findViewById(R.id.submit);
        cID = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.pass);
        inputPass = (TextInputLayout) findViewById(R.id.InputPass);
        inputUser = (TextInputLayout) findViewById(R.id.InputUser);
    }

    public boolean check(){//check the user input is valid.
        boolean a = true;
        if(cID.getText().toString().isEmpty()){
            inputUser.setError("id is required.");
            a=false;
        }
        else{
            inputUser.setErrorEnabled(false);
        }
        if(password.getText().toString().length()==0){
            inputPass.setError("password is required.");
            a=false;
        }
        else if(password.getText().length()<4){
            inputPass.setError("minimum 4 character");
            a=false;
        }
        else
            inputPass.setErrorEnabled(false);
        return a;
    }

    public boolean isConnected(){//check the wifi connection.
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mWifi.isConnected()) {
            return true;
        }
        return false;
    }
}
