/*
Jamie Fish
Secret Keeper
1/01/2018

Main Activity
 */

package edu.matc.secretkeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button addSecret, viewSecret;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //buttons
        viewSecret = (Button) findViewById(R.id.viewSecretBtn);
        addSecret = (Button) findViewById(R.id.addSecretBtn);

        //add button click
        addSecret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this,
                        AddSecret.class);
                startActivity(myIntent);
            }
        });

        //view button click
        viewSecret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this,
                        ListData.class);
                startActivity(myIntent);
            }
        });







    }
}
