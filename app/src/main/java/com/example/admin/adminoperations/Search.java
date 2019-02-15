package com.example.admin.adminoperations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Search extends AppCompatActivity {


    Button restaurent,travel,Goods ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button restaurent=(Button) findViewById(R.id.rest);
        Button travel=(Button) findViewById(R.id.tra);
        Button Goods=(Button) findViewById(R.id.ser);

        restaurent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Restaurant.class);
                startActivity(i);


            }
        });

    }
}
