package com.example.cooli.day6spinners;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem spinnerItem = menu.findItem(R.id.color_spinner);

        Spinner spinner = (Spinner) spinnerItem.getActionView();

        spinner.setOnItemSelectedListener(this); //for now, ignore the error you get

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colors_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


            if (i == 0) {
                for(int j = 0; j < 3; j++) {

                    Button button = (Button) findViewById(getResources().getIdentifier("button" + j, "id",
                            this.getPackageName()));
                    button.setBackgroundColor(Color.BLACK);
                }

            } if (i == 1) {

                    for(int k = 0; k < 3; k++) {

                        Button button2 = (Button) findViewById(getResources().getIdentifier("button" + k, "id",
                                this.getPackageName()));
                        button2.setBackgroundColor(Color.MAGENTA);
                    }
            } if (i == 2){

                for (int o = 0; o < 3; o++) {

                    Button button3 = (Button) findViewById(getResources().getIdentifier("button" + o, "id",
                            this.getPackageName()));
                    button3.setBackgroundColor(Color.CYAN);
                }
         }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
