package com.example.uma.final_project;
import android.content.Intent;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageButton b1, b2;
    String button1;
    String imageString;
    int id1;
    String Description1;
    String Offers1;
    String Expiration_date1, image;
    Intent myint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.imageButton);
        myint = new Intent(this, SecondActivity.class);

    }

    public void fetchdata(View V) {
        new MyTask().execute();

    }

    private class MyTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            StringBuffer response = new StringBuffer();
            URL url = null;

            try {

                String description = "hello";
                String offer = "30offer";
                String Expiration_date = "10april";


                url = new URL("http://10.0.2.2:27897/WebApplication1/Myapp/main/displayoffers&" + "'" + description + "'");

                /*url = new URL("http://10.0.2.2:27897/WebApplication1/Myapp/main/Addoffers_travels&10"+ "'" +  imageString+ "'"+
                "&"+"'"+description+"'"+"&"+"'"+offer+"'"+"&"+"'"+Expiration_date+"'");*/


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

                //print result
                // System.out.println("The response is " + response.toString());


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

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject ob = new JSONObject(result.toString());
                id1 = ob.getInt("id");
                Description1 = ob.getString("description");
                Offers1 = ob.getString("offers");
                Expiration_date1 = ob.getString("Expiration_date");
                image=ob.getString("image");
                System.out.println("printing inside method????????????" + id1 + " " + Description1 + " " + Offers1 + " " + Expiration_date1);
                System.out.println("From first activity" + id1 + " " + Description1 + " " + Offers1 + " " + Expiration_date1);
                myint.putExtra("id", String.valueOf(id1));
                myint.putExtra("description", Description1);
                myint.putExtra("offers", Offers1);
                myint.putExtra("expdate", Expiration_date1);
                myint.putExtra("image",image);
                startActivity(myint);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
