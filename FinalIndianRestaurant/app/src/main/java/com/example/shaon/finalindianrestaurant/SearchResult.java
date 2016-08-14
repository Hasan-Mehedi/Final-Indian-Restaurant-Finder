package com.example.shaon.finalindianrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayList<Results> results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        listView= (ListView) findViewById(R.id.searchListview);
        Bundle b = getIntent().getExtras();
        double lat = b.getDouble("lattitude");
        double lng = b.getDouble("longitude");
        String latt = String.valueOf(lat);
        String lngg = String.valueOf(lng);
        ZomatoApiDownloader downloader = new ZomatoApiDownloader(this,2);
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
