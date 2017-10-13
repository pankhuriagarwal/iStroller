package com.example.android.istroller;

import android.Manifest;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NearbyStores extends AppCompatActivity implements LocationListener,LoaderManager.LoaderCallbacks<ArrayList<NearbyPlacesData>> {
    Double latitude=42.3685;
    Double longitude= -71.0752;
    private static final int LOADER_ID = 1;
    private NearbyPlacesAdapter mAdapter;
    private String NearbyPlacesURL="https://places.demo.api.here.com/places/v1/discover/explore?at=42.3685%2C-71.0752&cat=hospital-health-care-facility&app_id=DemoAppId01082013GAL&app_code=AJKnXv84fjrb0KIHawS0Tg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_stores);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
// Register the listener with the Location Manager to receive location updates
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            //handle permission denied
        }
        else{
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);}

        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(bestProvider);


        LoaderManager loaderManager = getLoaderManager();


        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(LOADER_ID, null,this);





    }
    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

        latitude = (Double) (location.getLatitude());
       longitude = (Double) (location.getLongitude());

        Log.i("Geo_Location", "Latitude: " + latitude + ", Longitude: " + longitude);
    }
    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }
    public Double getLatitude(){return latitude;}
    public Double getLongitude(){return longitude;}


    @Override
    public Loader<ArrayList<NearbyPlacesData>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given ;
    //    NearbyPlacesURL = "https://places.demo.api.here.com/places/v1/discover/explore?at="+latitude+"%-"+longitude+"&cat=hospital-health-care-facility&app_id=2nRXH88SKWqkEopJWDmH&app_code=k8jIgW_W2pnxIv91pNhJtA";
        return new NearbyPlacesLoader(this, NearbyPlacesURL);

    }

    @Override
    public void onLoadFinished(Loader<ArrayList<NearbyPlacesData>> loader, final ArrayList<NearbyPlacesData> nearbyPlaces) {
        if (nearbyPlaces == null) {
            Log.e(null, "onPostLoad: earthquakes = null");

        } else {

//            loader.setEarthQuake();
            // Find a reference to the {@link ListView} in the layout
            ListView nearbyPlacesListView = (ListView) findViewById(R.id.list);
            nearbyPlacesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    NearbyPlacesData place1 = nearbyPlaces.get(i);
                    Double latitude1 = place1.getLat();
                    Double longitude1 = place1.getLon();

                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?saddr="+latitude+",-"+longitude+"&daddr="+latitude1.toString()+","+longitude1.toString()));
                    startActivity(intent);


                }
            });

            mAdapter = null;
            // Create a new {@link ArrayAdapter} of earthquakes
            if (nearbyPlaces != null)
                mAdapter = new NearbyPlacesAdapter(NearbyStores.this, ((NearbyPlacesLoader) loader).getNearbyPlaces());

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            if (mAdapter != null)
                nearbyPlacesListView.setAdapter(mAdapter);

            System.out.println("hello im here");


        }

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<NearbyPlacesData>> loader) {
//        Log.i(LOG_TAG, "test earthquake activity onLoaderReset called");
//        mAdapter.clear();
        // Loader reset, so we can clear out our existing data.

    }


}
