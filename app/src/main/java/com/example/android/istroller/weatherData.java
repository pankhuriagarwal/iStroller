package com.example.android.istroller;

/**
 * Created by pankhuriagarwal on 11/19/16.
 */

public class weatherData {
    private Double mTemp;
    private String  mRain;
    private Double mTempF;







    /**
     * Constructs a new TextView with initial values for text and text color.
     */
    public weatherData (Double temp, Double tempF, String  rain ) {

        mTempF = tempF;
        mTemp=temp;
        mRain=rain;



    }


    /**
     * Sets the string value in the TextView.
     *
     *
     */
//    public void setCheckInDate(String CheckInDate) {
//        mCheckInDate = CheckInDate;
//    }
    public void setTemp(Double temp) {
        mTemp = temp;
    }

    public void settempF(Double feekslike) {
        mTempF = feekslike;
    }
    public void setRain(String  rain) {
        mRain = rain;
    }



    /**
     * Gets the string value in the TextView.
     *
     * @return current text in the TextView.
     */
//    public String getCheckInDate() {
//        return mCheckInDate;
//    }
    public Double getTemp() {
        return mTemp;
    }

    public Double getTempF() {
        return mTempF;
    }
    public String  getRain() {
        return mRain;
    }

}
