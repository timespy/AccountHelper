package com.example.accounthelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class Summary extends AppCompatActivity {
    private TextView summary_food;
    private TextView summary_cloth;
    private TextView summary_daily_necessities;
    private TextView summary_emotion;
    private TextView summary_luxury;
    private TextView summary_sum;

    private EditText summary_year;
    private EditText summary_month;
    private EditText summary_day;

    private DBWorker worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        worker = new DBWorker(Summary.this, "bill.db");

        summary_food = (TextView)findViewById(R.id.summary_food);
        summary_cloth = (TextView)findViewById(R.id.summary_cloth);
        summary_daily_necessities = (TextView)findViewById(R.id.summary_daily_necessities);
        summary_emotion = (TextView)findViewById(R.id.summary_emotion);
        summary_luxury = (TextView)findViewById(R.id.summary_luxury);
        summary_sum = (TextView)findViewById(R.id.summary_sum);

        summary_year = (EditText)findViewById(R.id.summary_year);
        summary_month = (EditText)findViewById(R.id.summary_month);
        summary_day = (EditText)findViewById(R.id.summary_day);

        Button summary = (Button)findViewById(R.id.summary);
        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year;
                int month;
                int day;
                if (!summary_year.getText().toString().equals(""))
                    year = Integer.parseInt(summary_year.getText().toString());
                else
                    year = -1;
                if (!summary_month.getText().toString().equals(""))
                    month = Integer.parseInt(summary_month.getText().toString());
                else
                    month = -1;
                if (!summary_day.getText().toString().equals(""))
                    day = Integer.parseInt(summary_day.getText().toString());
                else
                    day = -1;

                List<Bill> list_bill = worker.select_bills(year, month, day, "全部");

                double sum_food = 0;
                double sum_cloth = 0;
                double sum_daily_necessities = 0;
                double sum_emotion = 0;
                double sum_luxury = 0;
                double sum = 0;

                for(int i = 0; i < list_bill.size(); i ++){
                    Bill bill = list_bill.get(i);
                    if (bill.getType().equals("饮食"))
                        sum_food += bill.getPrice();
                    if (bill.getType().equals("服装"))
                        sum_cloth += bill.getPrice();
                    if (bill.getType().equals("日用"))
                        sum_daily_necessities += bill.getPrice();
                    if (bill.getType().equals("人情"))
                        sum_emotion += bill.getPrice();
                    if (bill.getType().equals("大件"))
                        sum_luxury += bill.getPrice();
                    sum += bill.getPrice();
                }

                summary_food.setText(String.valueOf(sum_food));
                summary_cloth.setText(String.valueOf(sum_cloth));
                summary_daily_necessities.setText(String.valueOf(sum_daily_necessities));
                summary_emotion.setText(String.valueOf(sum_emotion));
                summary_luxury.setText(String.valueOf(sum_luxury));
                summary_sum.setText(String.valueOf(sum));
            }
        });

    }
}
