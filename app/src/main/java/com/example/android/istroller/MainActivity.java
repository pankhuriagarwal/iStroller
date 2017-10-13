package com.example.android.istroller;

import android.Manifest;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<weatherData>  {
    private static final int WeatherLOADER_ID = 1;
    Double tempFarenheit;
    Double tempCelcius;
    String weatherCondition;
    private String weatherURL="http://dataservice.accuweather.com/currentconditions/v1/2627461?apikey=vyZ01HrOrKj9GNEKd0BQDxeiOnB6OaoN";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView storeLocations = (TextView) findViewById(R.id.nearbyStores);
        storeLocations.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent openNearbyStores = new Intent(MainActivity.this, NearbyStores.class);
                startActivity(openNearbyStores);

            }

        });
        LinearLayout hotels = (LinearLayout) findViewById(R.id.music);
        hotels.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent openTravelPlanner = new Intent(MainActivity.this, TravelPlanner.class);
                startActivity(openTravelPlanner);

            }

        });

        TextView emergencyCall = (TextView) findViewById(R.id.emergencyAlert);
        emergencyCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "2156879668"));
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                } else {
                    startActivity(intent);
                }
            }
        });
        LoaderManager loaderManager = getLoaderManager();


        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(WeatherLOADER_ID, null,this);


    }
    @Override
    public Loader<weatherData> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given ;
        return new weatherLoader(MainActivity.this, weatherURL);

    }

    @Override
    public void onLoadFinished(Loader<weatherData> loader, final weatherData nearbyPlaces) {
        if (nearbyPlaces == null) {
            Log.e(null, "onPostLoad: earthquakes = null");


        } else {

//            loader.setEarthQuake();
            // Find a reference to the {@link ListView} in the layout
            TextView weatherText = (TextView) findViewById(R.id.weatherText);
            TextView tempC = (TextView) findViewById(R.id.tempC);
            TextView tempF = (TextView) findViewById(R.id.tempF);
            weatherData weather;
            weatherLoader wLoader=new weatherLoader(MainActivity.this,weatherURL);
            weather=wLoader.getweather();
            if(weather.getRain().length()>0){weatherCondition=weather.getRain();}
            else{weatherCondition="NA";}
            if(weather.getTemp().toString().length()>0){tempCelcius=weather.getTemp();}
            else{tempCelcius=0.0;}
            if(weather.getTempF().toString().length()>0){tempFarenheit=weather.getTempF();}
            else{tempFarenheit=0.0;}

            weatherText.setText(weatherCondition);
            tempC.setText(tempC+" C");
            tempF.setText(tempFarenheit+" F");


            System.out.println("hello im here");


        }

    }

    @Override
    public void onLoaderReset(Loader<weatherData> loader) {
//        Log.i(LOG_TAG, "test earthquake activity onLoaderReset called");
//        mAdapter.clear();
        // Loader reset, so we can clear out our existing data.

    }
}
