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


public class ResultsAdapter extends ArrayAdapter {

    ArrayList<Results> results;
    ArrayList<ResultsFavorite> resultsfavorite;
    int flag;
    Context context;

    public ResultsAdapter(Context context, ArrayList<Results> results) {
        super(context, R.layout.single_row, results);
        this.results = results;
        this.context = context;
    }

    class ResultsViewHolder {
        TextView myName;
        TextView myAddress;
        TextView myaveragecost;
        TextView myRating;
        ImageButton menubutton;
        ImageButton favoriteButton;


        ResultsViewHolder(View v) {
            myName = (TextView) v.findViewById(R.id.nameField);
            myAddress = (TextView) v.findViewById(R.id.addressField);
            myaveragecost = (TextView) v.findViewById(R.id.averagecostField);
            myRating = (TextView) v.findViewById(R.id.ratingField);
            menubutton = (ImageButton) v.findViewById(R.id.menuButton);
            favoriteButton = (ImageButton) v.findViewById(R.id.favoriteButton);

        }
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View row = convertView;
        ResultsViewHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_row, parent, false);
            holder = new ResultsViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ResultsViewHolder) row.getTag();
        }

            final Results result = results.get(position);
            holder.myName.setText(result.name);
            holder.myAddress.setText(result.address);
            holder.myaveragecost.setText("Average cost for a couple: " + result.average_cost_for_two);
            holder.myRating.setText("Rating: " + result.aggregate_rating);

            holder.menubutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(parent.getContext(), MapViewRestaurant.class);
                    intent.putExtra("latt", String.valueOf(result.latitude));
                    intent.putExtra("lngg", String.valueOf(result.longitude));
                    intent.putExtra("name",result.name);
                    intent.putExtra("city",result.city);
                    parent.getContext().startActivity(intent);
                }
            });

            holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLDatabaseAdapter databaseAdapter = new SQLDatabaseAdapter(getContext());
                    Log.d("INSERTING", result.name);
                    Toast.makeText(getContext(), result.name + " added to favourites", Toast.LENGTH_LONG).show();
                    databaseAdapter.insertData(result);
                }
            });



        return row;
    }
}
