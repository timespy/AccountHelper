package com.example.accounthelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class BillDatabaseHelper extends SQLiteOpenHelper {
    private Context mContext;

    public static final String CREATE_TABLE = "create table BILLDB("
            + "id integer primary key autoincrement, "
            + "type String, "
            + "descrip String, "
            + "price real,"
            + "year integer,"
            + "month integer,"
            + "day integer)";


    public BillDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
        Toast.makeText(mContext, "db has been created succeed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}
