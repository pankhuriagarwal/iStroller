package com.example.android.istroller;

/**
 * Created by pankhuriagarwal on 11/20/16.
 */

public class HotelsData {

    private String mName;
    private String mDistance;
    private Double mRating;







    /**
     * Constructs a new TextView with initial values for text and text color.
     */
    public HotelsData (String Name, String distance,  Double rating) {

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

    public void setName(String Name) {
        mName = Name;
    }

    public void setDistance(String distance) {
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

    public String getName() {
        return mName;
    }

    public Double getRating() {
        return mRating;
    }
    public String getDistance() {
        return mDistance;
    }
}
