package edu.example.ncc.introtojson;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_LAT = "lat";
    private static final String TAG_LON = "lon";
    private static final String TAG_CITY = "name";
    public static final String TAG_DAY = "day";
    public static final String TAG_MIN = "min";
    public static final String TAG_MAX = "max";
    public static final String TAG_DESCRIPTION = "desc";

    ArrayList<HashMap<String, String>> locationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        locationList = new ArrayList<>();

        //calls internal class method
        //multithreading - multiple processes running at the same time in parallel
        //async task is a multithreaded abilty/implementation that runs another process in the background, easier to use as long as it is appropriate
        //async task should be used if the backgorund process can be done fairly simply
        new GetLocations().execute();
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

    private class GetLocations extends AsyncTask<Void, Void, Void> {
        String result = "";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpURLConnection urlConnection;
            BufferedReader reader;

            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?id=5118226&APPID=cff54cf4393ce98abf4134eca01c4b27&units=imperial&cnt=5");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String s;
                while ((s = reader.readLine()) != null) {
                    result += s;
                }
            } catch (Exception e) {
                Log.i("HttpAsyncTask", "EXCEPTION: " + e.getMessage());
            }

            Log.i("PARSING", "Returned string: " + result);
            return null;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(Void r) {
            super.onPostExecute(r);

            if (result.length() != 0) {
                Log.i("Parsing", "about to start" + result);
                try {
                        JSONObject jsonObj = new JSONObject(result);
                        JSONObject city = jsonObj.getJSONObject("city");
                        JSONObject coord = city.getJSONObject("coord");
                        JSONArray list = jsonObj.getJSONArray("list");
                         for(int i = 0 ; i < 5; i++) {

                        JSONObject dailyInfo = list.getJSONObject(i);
                        JSONObject tempInfo = dailyInfo.getJSONObject("temp");

                        JSONArray weather = dailyInfo.getJSONArray("weather");
                        JSONObject descr = weather.getJSONObject(0);

                        String day = tempInfo.getString("day");
                        String min = tempInfo.getString("min");
                        String max = tempInfo.getString("max");
                        String lat = coord.getString("lat");
                        String lon = coord.getString("lon");
                        String cityName = city.getString("name");

                        String des = descr.getString("description");



                        HashMap<String, String> location = new HashMap<>();

                        location.put(TAG_LAT, lat);
                        location.put(TAG_LON, lon);
                        location.put(TAG_CITY, cityName);
                        location.put(TAG_DAY, day);
                        location.put(TAG_MAX, max);
                        location.put(TAG_MIN, min);
                        location.put(TAG_DESCRIPTION,des);


                        locationList.add(location);


                        ListAdapter adapter = new SimpleAdapter(
                                MainActivity.this, locationList,

                                R.layout.list_item, new String[]{TAG_CITY, TAG_LAT, TAG_LON, TAG_DAY,
                                TAG_MIN, TAG_MAX, TAG_DESCRIPTION},
                                new int[]{R.id.name, R.id.latitude, R.id.longitude, R.id.temperature,
                                        R.id.min, R.id.max, R.id.description});

                        ListView myList = (ListView) findViewById(R.id.list);
                        myList.setAdapter(adapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        }
    }
}

/**
 location.put(TAG_LAT, lat);
 location.put(TAG_LON, lon);
 location.put(TAG_CITY, cityName);
 location.put(TAG_DAY, day);
 location.put(TAG_MAX, max);
 location.put(TAG_MIN, min);
 location.put(TAG_DESCRIPTION,des);


 locationList.add(location);


 ListAdapter adapter = new SimpleAdapter(
 MainActivity.this, locationList,

 R.layout.list_item, new String[]{TAG_CITY, TAG_LAT, TAG_LON, TAG_DAY,
 TAG_MIN, TAG_MAX, TAG_DESCRIPTION},
 new int[]{R.id.name, R.id.latitude, R.id.longitude, R.id.temperature,
 R.id.min, R.id.max, R.id.description});

 ListView myList = (ListView) findViewById(R.id.list);
 myList.setAdapter(adapter);
 }
 */
