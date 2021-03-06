package com.example.admin.adminoperations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Button add = (Button) findViewById(R.id.add);
        Button del = (Button) findViewById(R.id.del);
        Button upd = (Button) findViewById(R.id.upd);

        add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), AdminAdd.class);
                startActivity(i);
            }
        });

        del.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Delete.class);
                startActivity(i);
            }
        });

        upd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Update.class);
                startActivity(i);
            }
        });


    }
}
