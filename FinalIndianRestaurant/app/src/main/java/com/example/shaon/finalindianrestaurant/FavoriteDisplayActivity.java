package com.example.shaon.finalindianrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavoriteDisplayActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<ResultsFavorite> databaseresults;
    ResultsFavorite favorite;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_display);
        databaseresults = this.getIntent().getExtras().getParcelableArrayList("databaseresult");
        listView = (ListView) findViewById(R.id.favoritelistview);
        FavoriteResultAdapter adapter = new FavoriteResultAdapter(this, databaseresults);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ResultsFavorite result = databaseresults.get(position);
        Intent intent = new Intent(this, FavoriteWebView.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }
}
