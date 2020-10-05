package com.example.cooli.accelerometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    //instance variables
    private SensorManager sensorManager;
    Sensor accelerometer;
    private static final String TAG = "MainActivity";
    TextView xValue,yValue,zValue,numValue;

    SensorManager smm;
    List<Sensor> sensor;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        xValue = (TextView) findViewById(R.id.x_View);
        yValue = (TextView) findViewById(R.id.y_View);
        zValue = (TextView) findViewById(R.id.z_View);
        numValue = (TextView) findViewById(R.id.num_View);
        smm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lv = (ListView) findViewById (R.id.listView1);
        sensor = smm.getSensorList(Sensor.TYPE_ALL);
        lv.setAdapter(new ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1,  sensor));
        numValue.setText(sensor.size() + " sensors");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        xValue.setText("" + event.values[0]);
        yValue.setText("" + event.values[1]);
        zValue.setText("" + event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
