package com.example.parksandrecs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MyDatabase extends SQLiteOpenHelper {
    // Table name constants
    private final static String DATABASE_NAME = "testDB.db";
    private final static String USERS_TABLE = "users";
    private final static String CITIES_TABLE = "cities";
    private final static String PARKING_LOT_TABLE = "parking_lots";
    private final static String PARKING_SPOT_TABLE = "parking_spots";
    private final static String RESERVATION_TABLE = "reservations";


    private final String dbPath;
    SQLiteDatabase myDatabase;
    private final Context context;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;

        dbPath = "data/user/0/" + context.getPackageName() + "/databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    // Check if database exists
    private boolean checkDatabase() {
        try {
            final String path = dbPath + DATABASE_NAME;
            final File file = new File(path);

            return file.exists();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Copy the database into local storage
    private void copyDatabase() {
        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            String outFileName = dbPath + DATABASE_NAME;

            OutputStream outputStream = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];
            int length;

            while( (length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create the database
    public void createDatabase() {
        boolean dbExists = checkDatabase();

        if(!dbExists) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                this.close();
            }
        }
    }

    @Override
    public synchronized void close() {
        if(myDatabase != null) myDatabase.close();

        SQLiteDatabase.releaseMemory();
        super.close();
    }

    public void customFunction() {
        try {
            createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // custom code here
    }

}
