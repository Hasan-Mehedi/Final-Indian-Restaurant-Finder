package com.example.shaon.finalindianrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class SQLDatabaseAdapter {

    SQLHelper helper;

    public SQLDatabaseAdapter(Context context) {
        helper = new SQLHelper(context);
    }

    public long insertData(Results result) {

        if (getResultName(result.name)) return 0;


        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLHelper.NAME, result.name);
        contentValues.put(SQLHelper.ADDRESS, result.address);
        contentValues.put(SQLHelper.LATTITUDE, result.latitude);
        contentValues.put(SQLHelper.LONGITUDE, result.longitude);
        contentValues.put(SQLHelper.USERRATING, result.aggregate_rating);
        contentValues.put(SQLHelper.MENU, result.menu_url);
        contentValues.put(SQLHelper.RESTAURANTURL, result.restauranturl);

        long id = db.insert(SQLHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public Boolean getResultName(String resultName) {

        SQLiteDatabase db = helper.getReadableDatabase();

        String[] columns = {SQLHelper.NAME};

        Cursor cursor = db.query(SQLHelper.TABLE_NAME, columns, SQLHelper.NAME + " = '" + resultName + "'", null, null, null, null);
        if (!cursor.moveToFirst() || cursor.getCount() == 0) return false;
        return true;
    }

    public Cursor fetchAllResults() {
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] columns = {SQLHelper.NAME, SQLHelper.ADDRESS, SQLHelper.LATTITUDE, SQLHelper.LONGITUDE, SQLHelper.USERRATING, SQLHelper.MENU, SQLHelper.RESTAURANTURL};

        Cursor cursor = db.query(SQLHelper.TABLE_NAME, columns, null, null, null, null, null);


//        ArrayList<Results> result = new ArrayList<>();
//
//	        /*
//	         *
//	         * Silver Diner | 123 Main St | 123456
//	         * 2 | Sam | 123 Someother st
//	         *
//	         */
//
//        while(cursor.moveToNext()) {
//            int indexTitle = cursor.getColumnIndex(SQLHelper.TITLE);
//            int indexAddress = cursor.getColumnIndex(SQLHelper.ADDRESS);
//            int indexPhone = cursor.getColumnIndex(SQLHelper.PHONE);
//
//            String title = cursor.getString(indexTitle);
//            String address = cursor.getString(indexAddress);
//            String phone = cursor.getString(indexPhone);
//
//            Log.d("Results", title +"-"+address);
//
//
//
//        }
//        return  null;
        return cursor;

    }


    static class SQLHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "IndianRestaurantDatabase";
        private static final String TABLE_NAME = "RESULTS";
        private static final int DATABASE_VERSION = 1;
        private static final String NAME = "rName";
        private static final String ADDRESS = "rAddress";
        private static final String LATTITUDE = "rLattitude";
        private static final String LONGITUDE = "rLongitude";
        private static final String USERRATING = "rUserrating";
        private static final String MENU = "rMenu";
        private static final String RESTAURANTURL = "rUrl";


        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + NAME + " VARCHAR(255), " + ADDRESS + " VARCHAR(255), " + LATTITUDE + " VARCHAR(255), " + LONGITUDE + " VARCHAR(255), " + USERRATING + " VARCHAR(255), " + MENU + " VARCHAR(255), " + RESTAURANTURL + " VARCHAR(255));";
        //private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
        //private static final String ALTER_TABLE = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + PHONE + " int DEFAULT 0";

        Context context;

        public SQLHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            db.execSQL(ALTER_TABLE);
            Toast.makeText(context, "onUpgrade Called", Toast.LENGTH_LONG).show();

        }
    }
}
