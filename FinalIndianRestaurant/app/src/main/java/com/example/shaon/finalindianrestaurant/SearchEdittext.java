package com.example.shaon.finalindianrestaurant;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class SearchEdittext extends AppCompatActivity {

    EditText editText;
    ImageButton imageButton;
    double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_edittext);
        editText = (EditText) findViewById(R.id.editText);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Geocoder geocoder = new Geocoder(SearchEdittext.this);
                final String zip = editText.getText().toString();
                try {
                    List<Address> addresses = geocoder.getFromLocationName(zip, 1);
                    if (addresses != null && !addresses.isEmpty()) {
                        Address address = addresses.get(0);
                        lat = address.getLatitude();
                        lng = address.getLongitude();

                    } else {
                        Toast.makeText(SearchEdittext.this, "Unable to search zipcode", Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {

                }

                Intent intent = new Intent(SearchEdittext.this, SearchResult.class);
                Bundle b = new Bundle();
                b.putDouble("lattitude", lat);
                b.putDouble("longitude", lng);
                intent.putExtras(b);
                startActivity(intent);
                finish();

            }
        });

    }
}
