package com.example.accounthelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button to_record = (Button)findViewById(R.id.to_record);
        Button to_add = (Button)findViewById(R.id.to_add);
        Button to_summary = (Button)findViewById(R.id.to_summary);

        to_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Record.class);
                startActivity(intent);
            }
        });

        to_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewRecord.class);
                startActivity(intent);
            }
        });

        to_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Summary.class);
                startActivity(intent);
            }
        });

    }
}
