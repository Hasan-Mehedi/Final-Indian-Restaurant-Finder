package com.example.shaon.finalindianrestaurant;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shaon on 8/13/2016.
 */
public class ResultsFavorite implements Parcelable {

    String name;
    String address;
    String latitude;
    String longitude;
    String aggregate_rating;
    String menu_url;
    String restauranturl;


    public ResultsFavorite(String name, String address, String latitude, String longitude, String aggregate_rating, String menu_url, String restauranturl) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.aggregate_rating = aggregate_rating;
        this.menu_url = menu_url;
        this.restauranturl = restauranturl;
    }

    protected ResultsFavorite(Parcel in) {
        name = in.readString();
        address = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        aggregate_rating = in.readString();
        menu_url = in.readString();
        restauranturl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(aggregate_rating);
        dest.writeString(menu_url);
        dest.writeString(restauranturl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ResultsFavorite> CREATOR = new Parcelable.Creator<ResultsFavorite>() {
        @Override
        public ResultsFavorite createFromParcel(Parcel in) {
            return new ResultsFavorite(in);
        }

        @Override
        public ResultsFavorite[] newArray(int size) {
            return new ResultsFavorite[size];
        }
    };
}


