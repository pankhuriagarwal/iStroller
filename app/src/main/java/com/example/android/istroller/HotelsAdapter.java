package com.example.android.istroller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pankhuriagarwal on 11/20/16.
 */

public class HotelsAdapter extends ArrayAdapter<HotelsData> {
private int mColorResourceId;
        Context mContext;


        ListView listView;


private static final String LOG_TAG = com.example.android.istroller.NearbyPlacesAdapter.class.getSimpleName();

public HotelsAdapter(Activity context, ArrayList<HotelsData> NearbyPlaces) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, NearbyPlaces);
        mContext = context;


        }

/**
 * Provides a view for an AdapterView (ListView, GridView, etc.)
 *
 * @param position    The position in the list of data that should be displayed in the
 *                    list item view.
 * @param convertView The recycled view to populate.
 * @param parent      The parent ViewGroup that is used for inflation.
 * @return The View for the position in the AdapterView.
 */
@Override
public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
        listItemView = LayoutInflater.from(getContext()).inflate(
        R.layout.listviewpropertieshotels, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        HotelsData currentPlace = getItem(position);


        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentPlace.getName());


//        ImageView imageView =(ImageView) listItemView.findViewById(R.id.image);
//        int image = currentWord.getImage();
//        if(image==0){imageView.setVisibility(View.GONE);}
//
//        imageView.setImageResource(image);


        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView distanceTextView = (TextView) listItemView.findViewById(R.id.distance);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView

        String dist = currentPlace.getDistance();

        String distance1 = "Address:  "+dist ;
        distanceTextView.setText(distance1);

        TextView ratingTextView = (TextView) listItemView.findViewById(R.id.rating);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        ratingTextView.setText(currentPlace.getRating().toString());

//        // Set the theme color for the list item
//        View textContainer = listItemView.findViewById(R.id.text_container);
//        // Find the color that the resource ID maps to
//        int color = ContextCompat.getColor(getContext(), mColorResourceId);
//        // Set the background color of the text container Vie
//        textContainer.setBackgroundColor(color);


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
        }

        }