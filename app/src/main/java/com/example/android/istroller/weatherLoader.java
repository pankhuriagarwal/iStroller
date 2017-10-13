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

/**
 * Created by pankhuriagarwal on 11/19/16.
 */

public class weatherLoader extends AsyncTaskLoader<weatherData> {
    NearbyStores coordinates = new NearbyStores();
    Double lat = 40.7128;
    //            lat = coordinates.getLatitude();
    Double lon = -74.0059;
//    lon = coordinates.getLongitude();


    // private String NearbyPlacesURL = "https://places.demo.api.here.com/places/v1/discover/explore?at="+lat+"%-"+lon+"&cat=hospital-health-care-facility&app_id=2nRXH88SKWqkEopJWDmH&app_code=k8jIgW_W2pnxIv91pNhJtA";
    private String weatherURL = "http://dataservice.accuweather.com/currentconditions/v1/2627461?apikey=vyZ01HrOrKj9GNEKd0BQDxeiOnB6OaoN";
    private String jsonResponse;
    weatherData weather;
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


    weatherLoader(Context context, String url) {
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
    public weatherData loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.

        // Create URL object
        URL url = createUrl(weatherURL);

        // Perform HTTP request to the URL and receive a JSON response back
        jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            // TODO Handle the IOException
        }

//        // Extract relevant fields from the JSON response and create an {@link Event} object
        try {
            weather = onResponseGetAirline(jsonResponse);
        } catch (JSONException e) {
            //put a catch
        }


        // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
        return weather;
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

    private weatherData onResponseGetAirline(String jsonResponse)
            throws JSONException {


        JSONArray response = new JSONArray(jsonResponse);
        JSONObject results = response.getJSONObject(0);
        String weatherText = results.getString("WeatherText");
        JSONObject temperature = results.getJSONObject("Temperature");
        JSONObject celcius = temperature.getJSONObject("Metric");
        JSONObject farenheit = temperature.getJSONObject("imperial");
        Double tempC = celcius.getDouble("Value");
        Double tempF = farenheit.getDouble("Value");

        weather = new weatherData(tempC, tempF, weatherText);


        return weather;


    }

    public weatherData getweather() {
        return weather;
    }

}

