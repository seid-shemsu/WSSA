package com.example.seid.wssa;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button submit;
    TextInputLayout inputUser,inputPass;
    EditText username, password;
    public String Username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Username = username.getText().toString();
                    if(check()){
                        Toast.makeText(MainActivity.this,"logged in successfully",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this,Welcome.class);
                        i.putExtra("username",Username);
                        startActivity(i);
                     }
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        check();
    }

    public EditText getUsername() {
        return username;
    }

    public void initialize(){
        submit = (Button) findViewById(R.id.submit);
        username = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.pass);
        inputPass = (TextInputLayout) findViewById(R.id.InputPass);
        inputUser = (TextInputLayout) findViewById(R.id.InputUser);
    }

    public boolean check(){
        boolean a = true;
        if(username.getText().toString().isEmpty()){
            inputUser.setError("user name is required.");
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
}
