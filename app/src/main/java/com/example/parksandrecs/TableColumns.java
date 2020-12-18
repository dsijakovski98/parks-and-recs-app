package com.example.parksandrecs;

public class TableColumns {


    public static class UsersTableColumns {

        public static final String USER_ID_COLUMN = "uid";
        public static final String USERNAME_COLUMN = "username";
        public static final String PASSWORD_COLUMN = "password";

    }

    public static class ParkingSpotColumns {
        public static final String PARKING_SPOT_ID_COLUMN = "spot_id";
        public static final String PARKING_SPOT_NAME_COLUMN = "spot_name";
    }

    public static class ReservationTableColumns {
        public static final String RESERVATION_ID_COLUMN = "reservation_id";
        public static final String DATE_FROM_COLUMN = "date_from";
        public static final String DURATION_COLUMN = "duration";
    }

    public static class ParkingLotColumns {
        public static final String PARKING_LOT_ID_COLUMN = "lot_id";
        public static final String PARKING_LOT_NAME_COLUMN = "lot_name";
        public static final String PARKING_LOT_LOCATION_COLUMN = "lot_location";
    }

    public static class CitiesTableColumns {
        public static final String CITY_ID_COLUMN = "city_id";
        public static final String CITY_NAME_COLUMN = "city_name";
        public static final String CITY_LOCATION_COLUMN = "city_location";
        public static final String CITY_ACHRONIM_COLUMN = "city_achronim";
    }
}
