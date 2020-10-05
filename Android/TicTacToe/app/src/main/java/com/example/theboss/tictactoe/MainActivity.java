package com.example.theboss.tictactoe;

//import statements
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

/**
 * Title: TicTacToe
 * Filename: MainActivity.java
 * Date Written: January 30, 2018
 * Due Date: February 3, 2018
 * Description: Defines the methods that will be used to create a basic tic tac toe
 * user interface. There will be 10 buttons in total. Nine of the buttns will represent
 * the tic tac toe board and the other button will represent a reset button. Once
 * the first button is clicked an x will be placed there and further clicks leads
 * to an alternating input between 'X' and 'O'. If the reset button is clicked the game
 * will restart.
 *
 *@author Derick Hansraj
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //instance variable to keep count of the amount of valid button clicks
    int count = 0;

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
                Snackbar.make(view, "Derick Hansraj\nn00827531@students.ncc.edu", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    /**
     * onClick method -- Once this method has been assigned to a button the button
     * responds to the onClick events defined. This method keeps track of the current
     * button that has been clicked. If that button is the reset button it will reset
     * the game. If the button is one of the other 9 buttons it will change the state
     * according to the alternating pattern. If a button is already clicked it will not be changed.
     * @param view the current event that is drawn and being handled.
     */
    public void onClick(View view) {
        //variables
        Button btn = (Button) findViewById(view.getId());
        Button resetBtn = (Button) findViewById(R.id.button_Reset);
        String buttonText = btn.getText().toString();

        //if statement to determine whether or not the reset button was clicked.
        //if it was it should reset the game and display a message using toast.
        if (btn == resetBtn) {
            Context context = getApplicationContext();
            CharSequence text = "Game has been reset!!!";
            int duration = Toast.LENGTH_SHORT;
            Toast resetStr = Toast.makeText(context, text, duration);
            resetStr.setGravity(Gravity.CENTER, 50, 50);
            resetStr.show();

            Button buttonOne = (Button) findViewById(R.id.button_One);
            buttonOne.setText(R.string.empty_Str);
            Button buttonTwo = (Button) findViewById(R.id.button_Two);
            buttonTwo.setText(R.string.empty_Str);
            Button buttonThree = (Button) findViewById(R.id.button_Three);
            buttonThree.setText(R.string.empty_Str);
            Button buttonFour = (Button) findViewById(R.id.button_Four);
            buttonFour.setText(R.string.empty_Str);
            Button buttonFive = (Button) findViewById(R.id.button_Five);
            buttonFive.setText(R.string.empty_Str);
            Button buttonSix = (Button) findViewById(R.id.button_Six);
            buttonSix.setText(R.string.empty_Str);
            Button buttonSeven = (Button) findViewById(R.id.button_Seven);
            buttonSeven.setText(R.string.empty_Str);
            Button buttonEight = (Button) findViewById(R.id.button_Eight);
            buttonEight.setText(R.string.empty_Str);
            Button buttonNine = (Button) findViewById(R.id.button_Nine);
            buttonNine.setText(R.string.empty_Str);
            count = 0;
        }
        //if statement to determine whether an x or o goes into a button once it is clicked
        if (count < 9) {
            if (buttonText.equals("")) {
                if (count % 2 == 0) {
                    btn.setText(R.string.strX);
                    count++;
                } else {
                    btn.setText(R.string.strO);
                    count++;
                }
            }
        }
    }
}
