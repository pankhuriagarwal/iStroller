package com.example.android.istroller;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class Hotels extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<HotelsData>> {
    private static final int LOADER_ID = 1;
    private HotelsAdapter mAdapter;
    private String NearbyPlacesURL="https://api.sandbox.amadeus.com/v1.2/hotels/search-airport?apikey=kbAJ6HAk7KTxtTQ5KH6Ge92S35keHwGh&location=BOS&check_in=2016-11-23&check_out=2016-11-27&amenity=BABY_SITTING&amenity=NON_PORNOGRAPHIC_TV";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        LoaderManager loaderManager = getLoaderManager();


        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(LOADER_ID, null,this);
    }

    @Override
    public Loader<ArrayList<HotelsData>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given ;
        //    NearbyPlacesURL = "https://places.demo.api.here.com/places/v1/discover/explore?at="+latitude+"%-"+longitude+"&cat=hospital-health-care-facility&app_id=2nRXH88SKWqkEopJWDmH&app_code=k8jIgW_W2pnxIv91pNhJtA";
        return new HotelsLoader(this, NearbyPlacesURL);

    }

    @Override
    public void onLoadFinished(Loader<ArrayList<HotelsData>> loader, final ArrayList<HotelsData> nearbyPlaces) {
        if (nearbyPlaces == null) {
            Log.e(null, "onPostLoad: earthquakes = null");

        } else {

//            loader.setEarthQuake();
            // Find a reference to the {@link ListView} in the layout
            ListView nearbyPlacesListView = (ListView) findViewById(R.id.list);


            mAdapter = null;
            // Create a new {@link ArrayAdapter} of earthquakes
            if (nearbyPlaces != null)
                mAdapter = new HotelsAdapter(Hotels.this, ((HotelsLoader) loader).getNearbyPlaces());

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            if (mAdapter != null)
                nearbyPlacesListView.setAdapter(mAdapter);

            System.out.println("hello im here");


        }

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<HotelsData>> loader) {
//        Log.i(LOG_TAG, "test earthquake activity onLoaderReset called");
//        mAdapter.clear();
        // Loader reset, so we can clear out our existing data.

    }

}
