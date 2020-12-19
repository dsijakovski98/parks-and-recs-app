package com.example.parksandrecs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class MyDatabase extends SQLiteOpenHelper {
    // Table name constants
    private final static String DATABASE_NAME = "ParksAndRecsDb.db";
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
//        SQLiteDatabase db = this.getReadableDatabase();
//        SQLiteDatabase db = this.getWritableDatabase();
        // custom code here
    }

    public int getCurrentUserId(String username) {
        try {
            createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format("SELECT %s FROM %s WHERE %s='%s'",
                TableColumns.UsersTableColumns.USER_ID_COLUMN, USERS_TABLE,
                TableColumns.UsersTableColumns.USERNAME_COLUMN, username);

        Cursor cursor = db.rawQuery(query, null);

        int currentUserId = -1;

        if(cursor.moveToFirst()) {
            currentUserId = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return currentUserId;
    }
    
    public boolean checkUsernameTaken(String username) {
        try {
            createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format("SELECT * FROM %s WHERE %s='%s'",
                USERS_TABLE, TableColumns.UsersTableColumns.USERNAME_COLUMN, username);

        Cursor cursor = db.rawQuery(query, null);
        boolean usernameTaken = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return usernameTaken;
    }

    public boolean checkCorrectPassword(String username, String password) {
        try {
            createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format("SELECT %s FROM %s WHERE %s='%s'",
                TableColumns.UsersTableColumns.PASSWORD_COLUMN,
                USERS_TABLE, TableColumns.UsersTableColumns.USERNAME_COLUMN,
                username);

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            String dbPassword = cursor.getString(0);
            return (password.equals(dbPassword));
        }

        cursor.close();
        db.close();

        return false;
    }

    public List<City> getAllCities() {
        try {
            createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = this.getReadableDatabase();

        List<City> returnList = new ArrayList<>();

        String query = String.format("SELECT * FROM %s", CITIES_TABLE);

        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()) {
            int cityId = cursor.getInt(0);
            String cityName = cursor.getString(1);
            String cityAcronym = cursor.getString(2);

            City city = new City(cityId, cityName, cityAcronym, 0);
            returnList.add(city);
        }

        cursor.close();
        db.close();

        return returnList;
    }

    public int getNumberOfParkingLots(int cityId) {
        try {
            createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = this.getReadableDatabase();

        String city_id = String.valueOf(cityId);
        String query = String.format("SELECT * FROM %s WHERE %s='%s'",
                PARKING_LOT_TABLE, TableColumns.ParkingLotColumns.PARKING_LOT_CITY_ID_COLUMN, city_id);

        Cursor cursor = db.rawQuery(query, null);
        int numberParkingLots = cursor.getCount();

        cursor.close();
        db.close();

        return numberParkingLots;
    }

    public void insertUser(String username, String password) {
        try {
            createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = this.getWritableDatabase();

        String insertCommand = String.format("INSERT INTO %s (%s, %s) VALUES('%s', '%s')",
                USERS_TABLE,
                TableColumns.UsersTableColumns.USERNAME_COLUMN, TableColumns.UsersTableColumns.PASSWORD_COLUMN,
                username, password);

        db.execSQL(insertCommand);

        db.close();
    }

    public boolean reservationCapExceeded(int currentUserId) {
        try {
            createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = this.getReadableDatabase();

        String userId = String.valueOf(currentUserId);
        String query = String.format("SELECT * FROM %s WHERE %s='%s'",
                RESERVATION_TABLE,
                TableColumns.ReservationTableColumns.RESERVATION_USER_ID_COLUMN, userId);

        Cursor cursor = db.rawQuery(query, null);

        boolean capExceeded = (cursor.getCount() >= 3);

        cursor.close();
        db.close();

        return capExceeded;
    }

    public List<ParkingLot> getAllParkingLots(int selectedCityId) {
        try {
            createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = this.getReadableDatabase();
        List<ParkingLot> returnList = new ArrayList<>();

        String query = String.format("SELECT * FROM %s WHERE %s='%s'",
                PARKING_LOT_TABLE, TableColumns.ParkingLotColumns.PARKING_LOT_CITY_ID_COLUMN, selectedCityId);

        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()) {
            int parkingLotId = cursor.getInt(0);

            String parkingLotName = cursor.getString(2);

            float latitude = cursor.getFloat(3);
            String parkingLotLatitude = String.valueOf(latitude);

            float longitude = cursor.getFloat(4);
            String parkingLoatLongitude = String.valueOf(longitude);

            int parkingLotCapacity = getParkingLotCapacity(parkingLotId, selectedCityId);

            ParkingLot parkingLot =
                    new ParkingLot(parkingLotId, parkingLotName, parkingLotCapacity, parkingLoatLongitude, parkingLotLatitude);

            returnList.add(parkingLot);
        }

        cursor.close();
        db.close();

        return returnList;
    }

    private int getParkingLotCapacity(int parkingLotId, int city_Id) {
        try {
            createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = this.getReadableDatabase();

        String lotId = String.valueOf(parkingLotId);
        String cityId = String.valueOf(city_Id);

        String query = String.format("SELECT * FROM %s WHERE %s='%s' AND %s='%s'",
                PARKING_SPOT_TABLE,
                TableColumns.ParkingSpotColumns.PARKING_SPOT_LOT_ID_COLUMN, lotId,
                TableColumns.ParkingSpotColumns.PARKING_SPOT_CITY_ID_COLUMN, cityId);

        Cursor cursor = db.rawQuery(query, null);

        int capacity = cursor.getCount();

        cursor.close();
        db.close();

        return capacity;
    }

    public int getCityId(String cityName) {
        try {
            createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format("SELECT %s FROM %s WHERE %s='%s'",
                TableColumns.CitiesTableColumns.CITY_ID_COLUMN, CITIES_TABLE,
                TableColumns.CitiesTableColumns.CITY_NAME_COLUMN, cityName);

        Cursor cursor = db.rawQuery(query, null);

        int cityId = -1;

        if(cursor.moveToFirst()) {
            cityId = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return  cityId;
    }

    public int getNumberOfTakenSpots(int cityId, int parkingId, String date, String time) {
        try {
            createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format("SELECT * FROM %s WHERE " +
                "%s='%s' AND %s='%s' AND %s='%s' AND %s='%s'",
                RESERVATION_TABLE,
                TableColumns.ReservationTableColumns.RESERVATION_CITY_ID_COLUMN, cityId,
                TableColumns.ReservationTableColumns.RESERVATION_LOT_ID_COLUMN, parkingId,
                TableColumns.ReservationTableColumns.RESERVATION_DATE_COLUMN, date,
                TableColumns.ReservationTableColumns.RESERVATION_TIME_COLUMN, time);

        Cursor cursor = db.rawQuery(query, null);

        int takenSpots = cursor.getCount();

        cursor.close();
        db.close();

        return takenSpots;
    }

    public void insertReservation
            (int currentUserId, int confirmCityId, int parkingLotId, String confirmDate, String confirmTime) {
        try {
            createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = this.getWritableDatabase();
        String userId = String.valueOf(currentUserId);
        String cityId = String.valueOf(confirmCityId);
        String lotId = String.valueOf(parkingLotId);

        String query = String.format("INSERT INTO %s " +
                "(%s, %s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s', '%s')",
                RESERVATION_TABLE,
                TableColumns.ReservationTableColumns.RESERVATION_USER_ID_COLUMN,
                TableColumns.ReservationTableColumns.RESERVATION_CITY_ID_COLUMN,
                TableColumns.ReservationTableColumns.RESERVATION_LOT_ID_COLUMN,
                TableColumns.ReservationTableColumns.RESERVATION_DATE_COLUMN,
                TableColumns.ReservationTableColumns.RESERVATION_TIME_COLUMN,
                userId, cityId, lotId, confirmDate, confirmTime);

        db.execSQL(query);
    }
}
