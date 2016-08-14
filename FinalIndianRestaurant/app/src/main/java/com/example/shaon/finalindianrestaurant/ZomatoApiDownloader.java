package com.example.shaon.finalindianrestaurant;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class ZomatoApiDownloader extends AsyncTask<String, Integer, ArrayList> {

    MainActivity activity;
    SearchResult activity2;
    int flag;

    public ZomatoApiDownloader(MainActivity activity,int flag) {
        this.activity = activity;
        this.flag=flag;
    }

    public ZomatoApiDownloader(SearchResult activity2,int flag) {
        this.activity2 = activity2;
        this.flag=flag;
    }

    @Override
    protected ArrayList doInBackground(String... params) {
        String zomatoURL = "https://developers.zomato.com/api/v2.1/search?count=10&lat=" + params[0] + "&lon=" + params[1] + "&cuisines=148&sort=real_distance&order=asc" + "&apikey=a08d1a0f0f5548cfd078adb5e8c7945d";
        ArrayList<Results> resultsArrayList = new ArrayList<Results>();

        try {
            URL theUrl = new URL(zomatoURL);
            BufferedReader reader = new BufferedReader(new InputStreamReader(theUrl.openConnection().getInputStream(), "UTF-8"));
            String json = reader.readLine();

            JSONObject jsonObject = new JSONObject(json);
            JSONArray resultsArray = jsonObject.getJSONArray("restaurants");

            for (int i = 0; i < resultsArray.length(); i++) {
                publishProgress((i + 1) * 10);
                JSONObject singleObject = resultsArray.getJSONObject(i);
                JSONObject restaurantObject = singleObject.getJSONObject("restaurant");

                String name = restaurantObject.getString("name");
                String restauranturl = restaurantObject.getString("url");

                JSONObject addressObject = restaurantObject.getJSONObject("location");
                String address = addressObject.getString("address");
                String locality = addressObject.getString("locality");
                String city = addressObject.getString("city");
                String zipcode = addressObject.getString("zipcode");
                double latitude = Double.parseDouble(addressObject.getString("latitude"));
                double longitude = Double.parseDouble(addressObject.getString("longitude"));

                String cuisines = restaurantObject.getString("cuisines");
                String average_cost_for_two = restaurantObject.getString("average_cost_for_two");
                String price_range = restaurantObject.getString("price_range");
                String offers = restaurantObject.getString("offers");
                String thumb = restaurantObject.getString("thumb");

                JSONObject userratingObject = restaurantObject.getJSONObject("user_rating");
                String aggregate_rating = userratingObject.getString("aggregate_rating");
                String votes = userratingObject.getString("votes");

                String photos_url = restaurantObject.getString("photos_url");
                String menu_url = restaurantObject.getString("menu_url");
                String featured_image_url = restaurantObject.getString("featured_image");
                String has_online_delivery = restaurantObject.getString("has_online_delivery");
                String is_delivering_now = restaurantObject.getString("is_delivering_now");
                String events_url = restaurantObject.getString("events_url");
                Results result = new Results(name, restauranturl, address, locality, city, zipcode, latitude, longitude, cuisines, average_cost_for_two, price_range, offers, thumb, aggregate_rating, votes, photos_url,
                        menu_url, featured_image_url, has_online_delivery, is_delivering_now, events_url);
                resultsArrayList.add(result);
                Log.d("INSERTING", result.name);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultsArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
        if(flag==1) {
            activity.drawListView(arrayList);
        }
        else
            activity2.drawListView(arrayList);
    }
}
