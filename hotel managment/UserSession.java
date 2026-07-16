package com.mycompany.project;

public class UserSession {

    private static String username;
    private static boolean isMaximized = false;
    private static String receiptPath;

    private static String roomType;
    private static String plan;
    private static String checkIn;
    private static String checkOut;
    private static int guests;
    private static int total;

    private static String email;

    public static void setEmail(String e) { email = e; }
    public static String getEmail() { return email; }
    
    public static void setUsername(String name) {
        username = name;
    }

    public static String getUsername() {
        return username;
    }

    public static void clear() {
        username = null;
    }

    public static void setMaximized(boolean value) {
        isMaximized = value;
    }

    public static boolean isMaximized() {
        return isMaximized;
    }

    public static void setReceiptPath(String path) {
        receiptPath = path;
    }

    public static String getReceiptPath() {
        return receiptPath;
    }


    public static void setBookingData(String r, String p, String in, String out, int g, int t) {
        roomType = r;
        plan = p;
        checkIn = in;
        checkOut = out;
        guests = g;
        total = t;
    }

    public static String getRoomType() { return roomType; }
    public static String getPlan() { return plan; }
    public static String getCheckIn() { return checkIn; }
    public static String getCheckOut() { return checkOut; }
    public static int getGuests() { return guests; }
    public static int getTotal() { return total; }
}

