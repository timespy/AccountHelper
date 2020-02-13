package com.example.accounthelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddNewRecord extends AppCompatActivity {
    private DBWorker worker;

    private Spinner type;
    private EditText descrip;
    private EditText price;

    private String selected_type = new String();
    private Bill bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_record);

        worker = new DBWorker(AddNewRecord.this, "bill.db");
        bill = new Bill();

        type = (Spinner)findViewById(R.id.select_type);
        descrip = (EditText)findViewById(R.id.descrip);
        price = (EditText)findViewById(R.id.price);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_type = type.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        Button add = (Button)findViewById(R.id.button_add_new_bill);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bill.setType(selected_type);
                bill.setDescrip(descrip.getText().toString());
                bill.setPrice(Double.parseDouble(price.getText().toString()));
                worker.add_new_bill(bill);
            }
        });
    }
}
