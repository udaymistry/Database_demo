package com.iamawsome.database_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabaseAdapter
{

    dbhelper Dbhelper;

    public MyDatabaseAdapter(Context context)
    {
        Dbhelper = new dbhelper(context);
    }
    public long insertdata(String Name ,String Password)
    {
        SQLiteDatabase db = Dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.Name,Name);
        contentValues.put(dbhelper.Password,Password);
        return db.insert(dbhelper.TABLE_NAME,null,contentValues);

    }
    public  String getdata()
    {
        SQLiteDatabase db = Dbhelper.getWritableDatabase();

        String columns[] = {dbhelper.UID,dbhelper.Name,dbhelper.Password};
       Cursor cursor = db.query(dbhelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuilder stringBuffer = new StringBuilder();


        while (cursor.moveToNext())
        {
            int index = cursor.getColumnIndex(dbhelper.UID);
            int index1 = cursor.getColumnIndex(dbhelper.Name);
            int index2  = cursor.getColumnIndex(dbhelper.Password);
            int cid = cursor.getInt(index);
            String name = cursor.getString(index1);
            String password = cursor.getString(index2);
            stringBuffer.append(cid).append(" ").append(name).append(" ").append(password).append("\n");
        }


        return stringBuffer.toString();
    }

     static class dbhelper extends SQLiteOpenHelper
    {

        private static final String DATABASE_NAME="MY_DATA_BASE";
        private static final String TABLE_NAME="MY_TABLE";
        private static final int Version = 8;
        private static final String UID = "_id";
        private static final String Name = "NAME";
        private static final String Password = "PASSWORD";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Name+" VARCHAR(255),"+Password+" VARCHAR(255));";
        private Context c;
        private static final String DELETE_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
        public dbhelper(Context context)
        {
            super(context, DATABASE_NAME, null, Version);
            this.c=context;
            Message.message(c,"MyDatabaseAdapter Constructor Called ");

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
                Message.message(c,"onCreate Method Called");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                Message.message(c, "" + e);
            }

        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try
            {
                db.execSQL(DELETE_TABLE);
                onCreate(db);
                Message.message(c,"onUpgrade Method Called");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                Message.message(c,":"+e);
            }
        }

    }
}
