package com.sodet.paulofernandes.awe;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {


    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String url = "http://sonae.sodet.biz/api/v1/movies/all/shopping-1";
    public static final String TITLE = "title";
    public static final String BACKDROP_URL = "backdropUrl";
    public static final String RATING = "rating";
    public static final String SINOPSE = "sinopse";
    public static final String GENRE = "genre";
    public static final String SESSIONS = "sessions";
    private ProgressDialog  pDialog;
    private List<Movie> movieList = new ArrayList<Movie>();
    private ListView listView;
    private CustomListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, movieList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentDetail = new Intent(getApplicationContext(), DetailActivity.class);
                intentDetail.putExtra(TITLE, movieList.get(position).getTitle());
                intentDetail.putExtra(BACKDROP_URL, movieList.get(position).getBackdropUrl());
                intentDetail.putExtra(RATING, movieList.get(position).getRating());
                intentDetail.putExtra(SINOPSE, movieList.get(position).getSinopse());
                intentDetail.putExtra(GENRE, movieList.get(position).getGenre());
                intentDetail.putParcelableArrayListExtra(SESSIONS, movieList.get(position).getSessions());
                startActivity(intentDetail);
            }
        });

        JsonObjectRequest movieReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d(TAG, jsonObject.toString());
                pDialog.hide();

                try {
                    JSONArray movieArray = jsonObject.getJSONArray("movie");
                    for (int i =0; i < movieArray.length() ; i++)
                    {
                        ArrayList<Session> sessionsList = new ArrayList<Session>();

                        JSONObject movieObj = movieArray.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setTitle(movieObj.getString("name"));
                        movie.setThumbnailUrl(movieObj.getString("poster"));
                        movie.setRating(((Number) movieObj.get("imdb_rating")).doubleValue());
                        movie.setBackdropUrl((movieObj.getString("backdrop")));
                        movie.setSinopse(movieObj.getString("sinopse"));

                        movie.setGenre(movieObj.getString("genre"));

                        JSONArray sessionsArray = movieObj.getJSONArray("sessions");
                        for (int j = 0; j < sessionsArray.length(); j++)
                        {

                            JSONObject sessionObj = sessionsArray.getJSONObject(j);
                            Session session = new Session();
                            session.setIs3d(sessionObj.getBoolean("3D"));
                            session.setDay(sessionObj.getString("day"));
                            session.setDubbed(sessionObj.getBoolean("dubbed"));
                            session.setHour(sessionObj.getString("hour"));

                            JSONObject roomObj = sessionObj.getJSONObject("room");
                            session.setRoom(new Room(roomObj.getInt("id"), roomObj.getString("name")));
                            sessionsList.add(session);
                        }

                        movie.setSessions(sessionsList);

                        movieList.add(movie);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.notifyDataSetChanged();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d(TAG, "Error");
                pDialog.hide();
            }
        });

        AppController.getInstance().addToRequestQueue(movieReq);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if(pDialog != null)
        {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
