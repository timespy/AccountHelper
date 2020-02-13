package com.example.accounthelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class DBWorker {

    private BillDatabaseHelper dbHelper;
    private Context context;

    DBWorker(Context _context, String dbname){
        context = _context;
        dbHelper = new BillDatabaseHelper(context, dbname, null, 1);
        dbHelper.getWritableDatabase();
    }

    void add_new_bill(Bill bill){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("type", bill.getType());
        values.put("descrip", bill.getDescrip());
        values.put("price", bill.getPrice());

        SimpleDateFormat df_year = new SimpleDateFormat("yyyy");
        SimpleDateFormat df_month = new SimpleDateFormat("MM");
        SimpleDateFormat df_day = new SimpleDateFormat("dd");
        values.put("year", df_year.format(new Date()));
        values.put("month", df_month.format(new Date()));
        values.put("day", df_day.format(new Date()));

        db.insert("BILLDB", null, values);
        Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
    }

    List<Bill> select_bills(int year, int month, int day, String type){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor;
        String selection = "";
        String[] selectionArgs;
        List<Bill> billList = new ArrayList<>();

        int x = 0;
        if (year != -1)
            x += 1;
        if (month != -1)
            x += 2;
        if (day != -1)
            x += 4;
        if (!type.equals("全部"))
            x += 8;

        switch(x){
            case 1:
                selection += "year = ?";
                selectionArgs = new String[]{String.valueOf(year)};
                break;
            case 2:
                selection += "month = ?";
                selectionArgs = new String[]{String.valueOf(month)};
                break;
            case 3:
                selection += "year = ? and month = ?";
                selectionArgs = new String[]{String.valueOf(year), String.valueOf(month)};
                break;
            case 4:
                selection += "day = ?";
                selectionArgs = new String[]{String.valueOf(day)};
                break;
            case 5:
                selection += "year = ? and day = ?";
                selectionArgs = new String[]{String.valueOf(year), String.valueOf(day)};
                break;
            case 6:
                selection += "month = ? and day = ?";
                selectionArgs = new String[]{String.valueOf(month), String.valueOf(day)};
                break;
            case 7:
                selection += "year = ? and month = ? and day = ?";
                selectionArgs = new String[]{String.valueOf(year), String.valueOf(month), String.valueOf(day)};
                break;
            case 8:
                selection += "type = ?";
                selectionArgs = new String[]{type};
                break;
            case 9:
                selection += "year = ? and type = ?";
                selectionArgs = new String[]{String.valueOf(year), type};
                break;
            case 10:
                selection += "month = ? and type = ?";
                selectionArgs = new String[]{String.valueOf(month), type};
                break;
            case 11:
                selection += "year = ? and month = ? and type = ?";
                selectionArgs = new String[]{String.valueOf(year), String.valueOf(month), type};
                break;
            case 12:
                selection += "day = ? and type = ?";
                selectionArgs = new String[]{String.valueOf(day), type};
                break;
            case 13:
                selection += "year = ? and day = ? and type = ?";
                selectionArgs = new String[]{String.valueOf(year), String.valueOf(day), type};
                break;
            case 14:
                selection += "month = ? and day = ? and type = ?";
                selectionArgs = new String[]{String.valueOf(month), String.valueOf(day), type};
                break;
            case 15:
                selection += "year = ? and month = ? and day = ? and type = ?";
                selectionArgs = new String[]{String.valueOf(year), String.valueOf(month), String.valueOf(day), type};
                break;
            default:
                selection = null;
                selectionArgs = null;
                break;
        }

        if (selection != null && selectionArgs != null){
            Log.d("DBWorker", "selection = " + selection);
            Log.d("DBWorker", "selectionArgs = " + selectionArgs[0]);
        }

        cursor = db.query("BILLDB", null, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()){
            do{
                Bill bill = new Bill(cursor.getString(cursor.getColumnIndex("type")),
                                     cursor.getString(cursor.getColumnIndex("descrip")),
                                     cursor.getDouble(cursor.getColumnIndex("price")),
                                     cursor.getInt(cursor.getColumnIndex("year")),
                                     cursor.getInt(cursor.getColumnIndex("month")),
                                     cursor.getInt(cursor.getColumnIndex("day")));
                bill.setId(cursor.getInt(cursor.getColumnIndex("id")));
                billList.add(bill);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return billList;
    }

    void delete_certain_bill(Bill bill){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        db.delete("BILLDB", "type = ?, descrip = ?, price = ?, year = ?, month = ?, day = ?",
//                new String[]{bill.getType(), bill.getDescrip(), String.valueOf(bill.getPrice()),
//                String.valueOf(bill.getYear()), String.valueOf(bill.getMonth()), String.valueOf(bill.getDay())});
        db.delete("BILLDB", "id = ?", new String[]{String.valueOf(bill.getId())});
//        Toast.makeText(context, bill.getDescrip(), Toast.LENGTH_SHORT).show();
    }

}
