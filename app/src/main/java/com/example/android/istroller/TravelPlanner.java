package com.example.android.istroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class TravelPlanner extends AppCompatActivity {
    String originAirport;
    String destinationAirport;
    String date;
    Boolean minValueOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_planner);
        Button submit =(Button)findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick (View view){

                TextView DestinationAirportCode = (TextView)findViewById(R.id.destinationAirportCode);
                destinationAirport=DestinationAirportCode.getText().toString();
                TextView dateIn = (TextView)findViewById(R.id.dates);
                date=dateIn.getText().toString();
                CheckBox minValue = (CheckBox) findViewById(R.id.minValue);
                minValueOption = minValue.isChecked();

                if(minValueOption==true) {

                }
                Intent openHotels = new Intent(TravelPlanner.this, Hotels.class);
                startActivity(openHotels);




            }

        });
    }
}
