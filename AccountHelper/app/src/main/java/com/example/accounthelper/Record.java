package com.example.accounthelper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Record extends AppCompatActivity {
    private DBWorker worker;

    private List<Bill> billList = new ArrayList<>();

    private EditText check_year;
    private EditText check_month;
    private EditText check_day;
    private Spinner check_type;
    private ListView show_list;

    private String checked_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        worker = new DBWorker(Record.this, "bill.db");

        check_day = (EditText)findViewById(R.id.check_day);
        check_month = (EditText)findViewById(R.id.check_month);
        check_year = (EditText)findViewById(R.id.check_year);
        check_type = (Spinner)findViewById(R.id.check_type);
        show_list = (ListView)findViewById(R.id.show_bill);

//        SimpleDateFormat df_year = new SimpleDateFormat("yyyy");
//        SimpleDateFormat df_month = new SimpleDateFormat("MM");
//        SimpleDateFormat df_day = new SimpleDateFormat("dd");
//        check_day.setText(df_day.format(new Date()));
//        check_month.setText(df_month.format(new Date()));
//        check_year.setText(df_year.format(new Date()));

        check_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                checked_type = check_type.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button check = (Button)findViewById(R.id.check_bill);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year;
                int month;
                int day;
                if (!check_year.getText().toString().equals(""))
                    year = Integer.parseInt(check_year.getText().toString());
                else
                    year = -1;
                if (!check_month.getText().toString().equals(""))
                    month = Integer.parseInt(check_month.getText().toString());
                else
                    month = -1;
                if (!check_day.getText().toString().equals(""))
                    day = Integer.parseInt(check_day.getText().toString());
                else
                    day = -1;
                billList.clear();
                billList.addAll(worker.select_bills(year, month, day, check_type.getSelectedItem().toString()));
                BillAdapter adapter = new BillAdapter(Record.this, R.layout.bill_item,
                        billList);
                ListView check_bills = (ListView)findViewById(R.id.show_bill);
                check_bills.setAdapter(adapter);
            }
        });

        show_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
//                Bill bill = billList.get(i);
//                Toast.makeText(Record.this, bill.getDescrip(), Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(Record.this)
                        .setTitle("确认")
                        .setMessage("是否确认删除？")
                        .setNegativeButton("不删", null)
                        .setPositiveButton("是的", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                worker.delete_certain_bill(billList.get(i));
                                Toast.makeText(Record.this, "删除成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }
}
