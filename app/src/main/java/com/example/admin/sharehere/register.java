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

public class register extends AppCompatActivity {

    EditText  firstname;
    EditText lastname;
    EditText username;
    EditText dofb;
    EditText email;
    EditText password;

    Button register;
    String fname,lname,uname,eid,pass,dob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        firstname=findViewById(R.id.editText3);
        lastname=findViewById(R.id.editText4);
        username=findViewById(R.id.editText5);
        dofb=findViewById(R.id.editText6);
        email=findViewById(R.id.editText7);
        password=findViewById(R.id.editText8);
        register=findViewById(R.id.button);
        register=findViewById(R.id.button);
    }

    public void registered(View v) {
        fname=username.getText().toString();
        lname=username.getText().toString();
        uname=username.getText().toString();
        dob=username.getText().toString();
        eid=username.getText().toString();
        pass= password.getText().toString();
        new MyTask1().execute();
        Intent myint=new Intent(this,MainActivity.class);
        myint.putExtra("firstname",fname);
        myint.putExtra("lastname",lname);
        myint.putExtra("username",uname);
        myint.putExtra("dofb",dob);
        myint.putExtra("email",eid);
        myint.putExtra("password",pass);
        startActivity(myint);
    }

    private class MyTask1 extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {


            URL url = null;

            try {

                url = new URL("http://192.168.1.8:8888/finalone/project/main/registration&"+ "'" + fname + "'" + "&" + lname + "'" + "&" + uname + "'" + "&" + dob + "'" + "&" + eid + "'" + "&" + pass);

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

                //#########print result##############33333
                System.out.println("The response is " + response.toString());
                JSONObject ob = new JSONObject(response.toString());
                fname = ob.getString("firstname");
                lname = ob.getString("lastname");
                uname = ob.getString("username");
                dob = ob.getString("dofb");
                eid = ob.getString("email");
                pass = ob.getString("pass");

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
