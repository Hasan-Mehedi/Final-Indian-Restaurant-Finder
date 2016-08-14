package com.example.shaon.finalindianrestaurant;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    double lat, lng;
    ListView listView;
    ArrayList<Results> results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        Get_Latitude_Longitude tracker = new Get_Latitude_Longitude(this);
        if (!tracker.canGetLocation()) {
            tracker.showSettingsAlert();
        } else {
            lat = tracker.getLatitude();
            lng = tracker.getLongitude();
        }
        String latt = String.valueOf(lat);
        String lngg = String.valueOf(lng);
        ZomatoApiDownloader downloader = new ZomatoApiDownloader(this, 1);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.search: {
                Intent intent = new Intent(MainActivity.this, SearchEdittext.class);
                startActivity(intent);
                break;
            }
            case R.id.favorite: {
                Cursor result = getContentResolver().query(MyContentProvider.CONTENT_URI, null, null, null, null);
                ArrayList<ResultsFavorite> databaseresults = new ArrayList<ResultsFavorite>();
                while (result.moveToNext()) {
                    int nameIndex = result.getColumnIndex("rName");
                    String name = result.getString(nameIndex);
                    int addressIndex = result.getColumnIndex("rAddress");
                    String address = result.getString(addressIndex);
                    int lattitudeIndex = result.getColumnIndex("rLattitude");
                    String lattitude = result.getString(lattitudeIndex);
                    int longitudeIndex = result.getColumnIndex("rLongitude");
                    String longitude = result.getString(longitudeIndex);
                    int userratingIndex = result.getColumnIndex("rUserrating");
                    String userrating = result.getString(userratingIndex);
                    int menuIndex = result.getColumnIndex("rMenu");
                    String menu = result.getString(menuIndex);
                    int urlIndex = result.getColumnIndex("rUrl");
                    String restauranturl = result.getString(urlIndex);
                    ResultsFavorite favorite = new ResultsFavorite(name, address, lattitude, longitude, userrating, menu, restauranturl);
                    databaseresults.add(favorite);
                }
                Intent intent = new Intent(MainActivity.this, FavoriteDisplayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("databaseresult", databaseresults);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }
            case R.id.shareapp: {
                Toast.makeText(this, "shareapp with your friend", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.about: {
                Toast.makeText(this, "display details about the app and author", Toast.LENGTH_LONG).show();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
