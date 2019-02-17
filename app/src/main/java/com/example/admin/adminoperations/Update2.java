package com.example.admin.adminoperations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

public class Update2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update2);
        Bundle b = getIntent().getExtras();
        final Item i = (Item)b.getSerializable("data");

        final EditText nm = (EditText)findViewById(R.id.name);
        nm.setText(i.content);

        final EditText d = (EditText)findViewById(R.id.des);
        d.setText(i.desc);

        Button bt=(Button)findViewById(R.id.update);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = nm.getText().toString();
                String de = d.getText().toString();
                new DownloadImageTask2(
                        "http://192.168.2.26:8080/internship/mobile/main/update&"+i.id+"&"+n+"&"+de).execute();
            }
        });

        ImageView im =(ImageView)findViewById(R.id.i);
        new DownloadImageTask1(im)
                .execute("http://192.168.2.26:8080/internship/mobile/files/"+i.image);
    }



    class DownloadImageTask2 extends AsyncTask<String, Void, String> {

        String url;
        public DownloadImageTask2(String url) {
            this.url = url;
        }

        protected String doInBackground(String... urls) {
            new JSONParser().getJSONFromUrl(url);
            return "";
        }

        protected void onPostExecute(Bitmap result) {

        }
    }

    class DownloadImageTask1 extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask1(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            if(result != null){
                int nh = (int) ( result.getHeight() * (512.0 / result.getWidth()) );
                Bitmap scaled = Bitmap.createScaledBitmap(result, 512, nh, true);

                bmImage.setImageBitmap(scaled);
            }
        }
    }

}
