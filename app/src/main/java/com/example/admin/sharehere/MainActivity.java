package com.example.admin.sharehere;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText username,password;

    Button login;
    String uname,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.editText1);
        password=findViewById(R.id.editText4);
        login=findViewById(R.id.btn_login);


    }
    public void login(View v) {
        uname=username.getText().toString();
        pass=password.getText().toString();
        new MyTask().execute();
        Intent myint=new Intent(this,register.class);
        myint.putExtra("username",uname);
        myint.putExtra("password", Integer.parseInt(String.valueOf(pass)));
        startActivity(myint);


    }
    private class MyTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {


            URL url = null;

            try {

                url = new URL("http://192.168.1.8:8888/finalone/project/main/userlogin&"+ "'" + uname + "'" + "&" +pass);

                HttpURLConnection client = null;

                client = (HttpURLConnection) url.openConnection();

                client.setRequestMethod("GET");

                int responseCode = client.getResponseCode();

                System.out.println("\n Sending 'GET' request to URL : " + url);

                System.out.println("Response Code : " + responseCode);

                InputStreamReader myInput = new InputStreamReader(client.getInputStream());

                BufferedReader in = new BufferedReader(myInput);
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print result
                System.out.println("The response is " + response.toString());
                JSONObject ob = new JSONObject(response.toString());
                uname = ob.getString("username");
                pass = ob.getString("password");


            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
        }
    }
}
