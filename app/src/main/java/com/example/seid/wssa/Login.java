package com.example.seid.wssa;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by SEID on 3/30/2019.
 */
////////////////////////////////////////////////////////////////////
//        this class is called by MainActivity class              //
////////////////////////////////////////////////////////////////////
public class Login extends AsyncTask<String, Void, String> {
    Context context;
    String cID;
    String password;
    public Login(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        String login_url = "http://192.168.43.149/WSSA/login.php";
        cID = strings[0];
        password = strings[1];
        try{
            URL url = new URL(login_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String postData = URLEncoder.encode("cID","UTF-8")+"="+URLEncoder.encode(cID,"UTF-8")+
                    "&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
            bufferedWriter.write(postData);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="",line="";
            while ((line=bufferedReader.readLine())!=null){
                result = line;
            }
            bufferedReader.close();
            inputStream.close();
            connection.disconnect();
            return result;
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e ){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        char[] res = result.toCharArray();
        if(res[0] == '<'){
            Toast.makeText(context,"invalid id or password",Toast.LENGTH_LONG).show();
        }
        else{
            Intent i = new Intent(context,Welcome.class);
            i.putExtra("cID",cID);
            context.startActivity(i);
        }

    }
}
