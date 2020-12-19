package com.example.parksandrecs;

public class TableColumns {


    public static class UsersTableColumns {

        public static final String USER_ID_COLUMN = "uid";
        public static final String USERNAME_COLUMN = "username";
        public static final String PASSWORD_COLUMN = "password";

    }

    public static class ParkingSpotColumns {
        public static final String PARKING_SPOT_ID_COLUMN = "spot_id";
        public static final String PARKING_SPOT_LOT_ID_COLUMN = "lot_id";
        public static final String PARKING_SPOT_CITY_ID_COLUMN = "city_id";
    }

    public static class ReservationTableColumns {
        public static final String RESERVATION_ID_COLUMN = "reservation_id";
        public static final String RESERVATION_USER_ID_COLUMN = "uid";
        public static final String RESERVATION_CITY_ID_COLUMN = "city_id";
        public static final String RESERVATION_LOT_ID_COLUMN = "lot_id";
        public static final String RESRVATION_SPOT_ID_COLUMN = "spot_id";
        public static final String RESERVATION_DATE_FROM_COLUMN = "date_from";
        public static final String RESERVATION_DURATION_COLUMN = "duration";
    }

    public static class ParkingLotColumns {
        public static final String PARKING_LOT_ID_COLUMN = "lot_id";
        public static final String PARKING_LOT_CITY_ID_COLUMN = "city_id";
        public static final String PARKING_LOT_NAME_COLUMN = "lot_name";
        public static final String PARKING_LOT_LATITUDE_COLUMN = "latitude";
        public static final String PARKING_LOT_LONGITUDE_COLUMN = "longitude";
    }

    public static class CitiesTableColumns {
        public static final String CITY_ID_COLUMN = "city_id";
        public static final String CITY_NAME_COLUMN = "city_name";
        public static final String CITY_ACRONYM_COLUMN = "city_achronim";
    }
}
