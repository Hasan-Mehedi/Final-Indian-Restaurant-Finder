package com.example.shaon.finalindianrestaurant;

import android.app.SearchManager;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchResult extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayList<Results> results;
    String zip;
    double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        listView = (ListView) findViewById(R.id.searchListview);
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            zip = intent.getStringExtra(SearchManager.QUERY);
        }

        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> addresses = geocoder.getFromLocationName(zip, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                lat = address.getLatitude();
                lng = address.getLongitude();

            } else {
                Toast.makeText(this, "Unable to search zipcode", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {

        }

//        Bundle b = getIntent().getExtras();
//        double lat = b.getDouble("lattitude");
//        double lng = b.getDouble("longitude");
        String latt = String.valueOf(lat);
        String lngg = String.valueOf(lng);
        ZomatoApiDownloader downloader = new ZomatoApiDownloader(this, 2);
        downloader.execute(latt, lngg);
    }

    public void drawListView(ArrayList<Results> resultsArray) {
        results = new ArrayList<Results>();
        results = resultsArray;
        ResultsAdapter adapter = new ResultsAdapter(this, resultsArray);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Results result = results.get(position);
        Intent intent = new Intent(this, WebviewDetails.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }
}
