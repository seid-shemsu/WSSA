package com.example.seid.wssa;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by SEID on 3/26/2019.
 */

public class PaymentConnection extends AsyncTask<String, Void, String> {

    Context ctx;
    public PaymentConnection(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String url_reg = "http://192.168.43.149/WSSA/history.php";
        String cID = params[0], month = params[1], amount=params[2],hour = params[3], day=params[4], year=params[5];
        try {
            URL url = new URL(url_reg);
            HttpURLConnection http =(HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            OutputStream OS = http.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

            String data = URLEncoder.encode("cID","UTF-8") +"="+ URLEncoder.encode(cID,"UTF-8") + " & "+
                    URLEncoder.encode("month","UTF-8") +"="+ URLEncoder.encode(month,"UTF-8") +" & "+
                    URLEncoder.encode("amount","UTF-8") +"="+ URLEncoder.encode(amount,"UTF-8") +" & "+
                    URLEncoder.encode("hour","UTF-8") +"="+ URLEncoder.encode(hour,"UTF-8") +" & "+
                    URLEncoder.encode("day","UTF-8") +"="+ URLEncoder.encode(day,"UTF-8") +" & "+
                    URLEncoder.encode("year","UTF-8") +"="+ URLEncoder.encode(year,"UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream IS = http.getInputStream();
            IS.close();
            return "paid successfully";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();
    }
}
