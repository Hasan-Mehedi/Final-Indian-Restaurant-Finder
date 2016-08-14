package com.example.shaon.finalindianrestaurant;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by shaon on 8/14/2016.
 */
public class FavoriteResultAdapter extends ArrayAdapter {

    ArrayList<ResultsFavorite> results;
    Context context;

    public FavoriteResultAdapter(Context context, ArrayList<ResultsFavorite> results) {
        super(context, R.layout.single_favorite, results);
        this.results = results;
        this.context = context;


    }

    class ResultsViewHolder{
        TextView myName;
        TextView myAddress;
        ImageButton mapButton,shareButton;


        ResultsViewHolder(View v){
            myName = (TextView) v.findViewById(R.id.nameFavorite);
            myAddress = (TextView) v.findViewById(R.id.addressFavorite);
            mapButton= (ImageButton) v.findViewById(R.id.mapFavorite);
            shareButton= (ImageButton) v.findViewById(R.id.shareFavorite);
        }
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View row = convertView;
        ResultsViewHolder holder = null;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_favorite, parent, false);
            holder = new ResultsViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ResultsViewHolder) row.getTag();
        }
        final ResultsFavorite result = results.get(position);
        holder.myName.setText(result.name);
        holder.myAddress.setText(result.address);

        holder.mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(parent.getContext(), MapViewRestaurant.class);
                intent.putExtra("latt", result.latitude);
                intent.putExtra("lngg", result.longitude);
                intent.putExtra("name",result.name);
                intent.putExtra("city",result.address);
                parent.getContext().startActivity(intent);


            }
        });

        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return row;
    }
}
