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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();

            }
        });
    }

    public void initialize(){
        submit = (Button) findViewById(R.id.submit);
        username = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.pass);
        inputPass = (TextInputLayout) findViewById(R.id.InputPass);
        inputUser = (TextInputLayout) findViewById(R.id.InputUser);
    }

    public void check(){
        int a = 0;
        if(username.getText().toString().isEmpty()){
            inputUser.setError("user name is required.");
            a=1;
        }
        else{
            inputUser.setErrorEnabled(false);
        }
        if(password.getText().toString().length()==0){
            inputPass.setError("password is required.");
            a=1;
        }
        else if(password.getText().length()<8){
            inputPass.setError("minimum 8 character");
            a=1;
        }
        else
            inputPass.setErrorEnabled(false);
        if(a==0){
            Intent i = new Intent(this, Welcome.class);
            startActivity(i);
        }
    }
}
