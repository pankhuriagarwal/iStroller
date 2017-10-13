package com.example.android.istroller;

/**
 * Created by pankhuriagarwal on 11/18/16.
 */

public class NearbyPlacesData {
    private Double mLat;
    private Double mLong;
    private String mName;
    private int mDistance;
    private Double mRating;







    /**
     * Constructs a new TextView with initial values for text and text color.
     */
    public NearbyPlacesData (Double lat, Double lon,String Name, int distance,  Double rating) {
//        mCheckInDate= CheckInDate;
        mLat = lat;
        mLong=lon;
        mName=Name;
        mDistance=distance;
        mRating=rating;



    }


    /**
     * Sets the string value in the TextView.
     *
     *
     */
//    public void setCheckInDate(String CheckInDate) {
//        mCheckInDate = CheckInDate;
//    }
    public void setLat(Double lat) {
        mLat = lat;
    }
    public void setName(String Name) {
        mName = Name;
    }
    public void setLon(Double lon) {
        mLong = lon;
    }
    public void setDistance(int distance) {
        mDistance = distance;
    }
    public void setRating(Double rating) {
        mRating = rating;
    }


    /**
     * Gets the string value in the TextView.
     *
     * @return current text in the TextView.
     */
//    public String getCheckInDate() {
//        return mCheckInDate;
//    }
    public Double getLat() {
        return mLat;
    }
    public String getName() {
        return mName;
    }
    public Double getLon() {
        return mLong;
    }
    public Double getRating() {
        return mRating;
    }
    public int getDistance() {
        return mDistance;
    }
}
