package com.example.android.istroller;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by pankhuriagarwal on 11/20/16.
 */

public class HotelsLoader extends AsyncTaskLoader<ArrayList<HotelsData>> {
        NearbyStores coordinates = new NearbyStores();
        Double lat = 40.7128;
//            lat = coordinates.getLatitude();
        Double lon = 74.0059;
//    lon = coordinates.getLongitude();


private String NearbyPlacesURL="https://api.sandbox.amadeus.com/v1.2/hotels/search-airport?apikey=kbAJ6HAk7KTxtTQ5KH6Ge92S35keHwGh&location=BOS&check_in=2016-11-23&check_out=2016-11-27&amenity=BABY_SITTING&amenity=NON_PORNOGRAPHIC_TV";
private String jsonResponse;
        ArrayList<HotelsData> hotels;
/**
 * Constant value for the earthquake loader ID. We can choose any integer.
 * This really only comes into play if you're using multiple loaders.
 */

/**
 * Tag for log messages
 */
private final String LOG_TAG = NearbyPlacesLoader.class.getName();

/**
 * Query URL
 */
private String mUrl;


        HotelsLoader(Context context, String url) {
        super(context);
        mUrl = url;
        }

private URL createUrl(String stringUrl) {
        URL url;
        try {
        url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
        Log.e(LOG_TAG, "Error with creating URL", exception);
        return null;
        }
        return url;
        }

@Override
protected void onStartLoading() {
        forceLoad();
        }

@Override
public ArrayList<HotelsData> loadInBackground() {
        if (mUrl == null) {
        return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.

        // Create URL object
        URL url = createUrl(NearbyPlacesURL);

        // Perform HTTP request to the URL and receive a JSON response back
        jsonResponse = "";
        try {
        jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
        // TODO Handle the IOException
        }

//        // Extract relevant fields from the JSON response and create an {@link Event} object
        try {
        hotels = onResponseGetAirline(jsonResponse);
        } catch (JSONException e) {
        //put a catch
        }


        // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
        return hotels;
        }

/**
 * Make an HTTP request to the given URL and return a String as the response.
 */
private String makeHttpRequest(URL url) throws IOException {
        jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);
        urlConnection.connect();
        if (urlConnection.getResponseCode() == 200) {
        inputStream = urlConnection.getInputStream();
        jsonResponse = readFromStream(inputStream);
        } else {
        Log.e(LOG_TAG, "error response code: " + urlConnection.getResponseCode());
        }
        } catch (IOException e) {
        // TODO: Handle the exception
        } finally {

        if (urlConnection != null) {
        urlConnection.disconnect();
        }
        if (inputStream != null) {
        // function must handle java.io.IOException here
        inputStream.close();
        }
        }
        return jsonResponse;
        }


private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line = reader.readLine();
        while (line != null) {
        output.append(line);
        line = reader.readLine();
        }
        }
        return output.toString();
        }

private ArrayList<HotelsData> onResponseGetAirline(String jsonResponse)
        throws JSONException {

        ArrayList<HotelsData> hotels = new ArrayList<HotelsData>();

        JSONObject response = new JSONObject(jsonResponse);
        JSONArray results = response.getJSONArray("results");
    for(int i=0;i<results.length();i++){
        JSONObject firstResult = results.getJSONObject(i);
        String name = firstResult.getString("property_name");
        JSONObject cost = firstResult.getJSONObject("total_price");
        Double price = cost.getDouble("amount");
        JSONObject addressObject = firstResult.getJSONObject("address");
        String street = addressObject.getString("line1");
        String city = addressObject.getString("city");
        String address = street+", "+city;


//        JSONObject firstItem = items.getJSONObject(i);
//        String name = firstItem.getString("title");
//        int distance = firstItem.getInt("distance");
//        JSONArray position = firstItem.getJSONArray("position");
//        Double lat = position.getDouble(0);
//        Double lon = position.getDouble(1);
//        Double rating = firstItem.getDouble("averageRating");
        hotels.add(new HotelsData(name, address, price));}


        return hotels;


        }

public ArrayList<HotelsData> getNearbyPlaces(){
        return hotels;
        }

        }
