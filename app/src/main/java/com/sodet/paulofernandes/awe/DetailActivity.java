package com.sodet.paulofernandes.awe;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;


public class DetailActivity extends ActionBarActivity {

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);





        Intent intent = getIntent();
        String titleStr = intent.getStringExtra(MainActivity.TITLE);
        String backdropUrl = intent.getStringExtra(MainActivity.BACKDROP_URL);
        Double rating = intent.getDoubleExtra(MainActivity.RATING, 0.0);
        String sinopse = intent.getStringExtra(MainActivity.SINOPSE);
        String genre = intent.getStringExtra(MainActivity.GENRE);




        TextView titleTV = (TextView) findViewById(R.id.title);
        titleTV.setText(titleStr);

        TextView ratingTV = (TextView) findViewById(R.id.rating);
        ratingTV.setText(rating.toString());
        TextView sinopseTV = (TextView) findViewById(R.id.sinopse);
        sinopseTV.setText(sinopse);


        NetworkImageView backdrop = (NetworkImageView) findViewById(R.id.backdrop);
        backdrop.setImageUrl(backdropUrl, imageLoader);

        TextView genreTV = (TextView) findViewById(R.id.genre);
        genreTV.setText(genre);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
