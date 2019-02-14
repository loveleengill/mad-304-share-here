package com.example.uma.final_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class RegistrationActivity extends AppCompatActivity {
    EditText t,t1, t2, t3, t4, t5;
    String type, username, firstname, password, lastname, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        t=findViewById(R.id.txt);
        t1 = findViewById(R.id.txt1);
        t2 = findViewById(R.id.txt2);
        t3 = findViewById(R.id.txt3);
        t4 = findViewById(R.id.txt4);
        t5 = findViewById(R.id.txt5);


    }

    public void register(View view) {

        type=t.getText().toString();
        username = t1.getText().toString();
        password = t2.getText().toString();
        firstname = t3.getText().toString();
        lastname = t4.getText().toString();
        email = t5.getText().toString();
        System.out.println("Print something"+ username);
        new RegistrationActivity.MyTask().execute();
    }

    private class MyTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            StringBuffer response = new StringBuffer();
            URL url = null;


            try {

                url = new URL("http://10.0.2.2:27897/WebApplication1/Myapp/main/registration&"+ ""+type+""+"&"+"" + username + "" + "&" + "" + password + "" + "&" + "" + firstname + ""
                        + "&" + "" + lastname + "" + "&" + "" + email + "");

                // url = new URL("http://10.0.2.2:27897/WebApplication1/Myapp/main/login&"+ "'" + uname + "'"+ "&" +"'"+pass+"'");
              /* url = new URL("http://10.0.2.2:27897/WebApplication1/Myapp/main/registration&101"+"&" + "'"+type+"'"+"&"+"'" + username + "'" + "&" + "'" + password + "'" + "&" + "'" + firstname + "'"
                      + "&" + "'" + lastname + "'" + "&" + "'" + email + "'");
*/
                //url = new URL("http://10.0.2.2:27897/WebApplication1/Myapp/main/Addoffers_travels&1012"+ "'" +  file.getAbsolutePath()+ "'"+
                // "&"+"'"+description+"'"+"&"+"'"+offer+"'"+"&"+"'"+Expiration_date+"'");



               // url = new URL("http://10.0.2.2:27897/WebApplication1/Myapp/main/Registration&"+type+"&"+username+"&"+password+"&"+lastname+"&"+firstname+"&"+email+"");
              // url = new URL("http://10.0.2.2:27897/WebApplication1/Myapp/main/Registration&"+ "'" +type+ "'"+ "&" + "'" +username+ "'" + "&" + "'" +password+ "'" + "&"
                   //   + "'" +lastname+ "'" + "&" + "'" +firstname+ "'" + "& "+ "'"+email+"'");

                HttpURLConnection client = null;
                client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("GET");
                int responseCode = client.getResponseCode();
                System.out.println("\n Sending 'GET' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);
                InputStreamReader myInput = new InputStreamReader(client.getInputStream());
                BufferedReader in = new BufferedReader(myInput);
                String inputLine;


                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println("The response is " + response.toString());


            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
            }

            return response.toString();
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equalsIgnoreCase("wrong")) {
                Toast.makeText(RegistrationActivity.this, "error occured while registering, please try again", Toast.LENGTH_LONG).show();

            } else {
                Intent launchActivity1 = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(launchActivity1);

            }
        }

    }
}







