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
        ArrayList<Session> sessionsList = intent.getParcelableArrayListExtra(MainActivity.SESSIONS);

        getSupportActionBar().setTitle(titleStr);

        StringBuilder sb = new StringBuilder();
        String lastDay = "";
        for (int i =0; i < sessionsList.size(); i ++ )
        {
//            Log.d("Detail", String.valueOf(sessionsList.get(i).getRoom().getName()));
            String currentDay = changeData(sessionsList.get(i).getDay());


            if (!currentDay.equals(lastDay)) {
                sb.append("\n"+ "Dia " + currentDay + "\n\n");
            }
                //append to string
                String hour = sessionsList.get(i).getHour();
                sb.append("\t\t " + hour.substring(0, 5));

            if(sessionsList.get(i).is3d())
                sb.append(" 3D ");

            if(sessionsList.get(i).isDubbed())
                sb.append(" Dub ");

            sb.append(" " + sessionsList.get(i).getRoom().getName() + "\n");


            lastDay = currentDay;
        }


        TextView sessionsTV = (TextView) findViewById(R.id.sessionTV);
        sessionsTV.setText(sb.toString());

        TextView titleTV = (TextView) findViewById(R.id.title);
        titleTV.setText(titleStr);

        TextView ratingTV = (TextView) findViewById(R.id.rating);
        ratingTV.setText(String.format("Rating: %.1f",rating));

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

    private String changeData(String data)
    {
        String ano = data.substring(0,4);
        String mes = data.substring(5, 7);
        String dia = data.substring(8);



        return (dia + "/" + mes + "/" + ano );
    }
}
