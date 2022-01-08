package com.example.myloginwithsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static String DATABASE_NAME ="userdetails.db";
    private static String TABLE_NAME ="user_details";
    private static String ID ="Id";
    private static String NAME ="Name";
    private static String EMAIL ="Email";
    private static String USERNAME ="username";
    private static String PASSWORD ="Password";
    private static final int VERSION_NUMBER =2;
    private static final String CREATE_TABLE ="CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255) NOT NULL,"+EMAIL+" TEXT NOT NULL,"+USERNAME+" TEXT NOT NULL,"+PASSWORD+" TEXT NOT NULL);";
    private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;

    
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Toast.makeText(context, "onCreate is Called", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_LONG).show();

        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            Toast.makeText(context, "onUpgrade is Called", Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        }catch (Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_LONG).show();

        }

    }
    public long insertData(UserDetaills userDetaills){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,userDetaills.getName());
        contentValues.put(EMAIL,userDetaills.getEmail());
        contentValues.put(USERNAME,userDetaills.getUsername());
        contentValues.put(PASSWORD,userDetaills.getPassword());
        long rowId = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;

    }
    public Boolean findPassword(String uname,String pass){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        Boolean result = false;
        if (cursor.getCount()==0){
            Toast.makeText(context, "No data is found", Toast.LENGTH_LONG).show();
        }else {
            while (cursor.moveToNext()){
                String username = cursor.getString(3);
                String password = cursor.getString(4);
                if (username.equals(uname)&& password.equals(pass)){
                    result =true;
                    break;
                }
            }

        }
        return result;


    }

}
