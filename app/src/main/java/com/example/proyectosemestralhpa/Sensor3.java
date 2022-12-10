package com.example.proyectosemestralhpa;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Sensor3 extends Activity implements SensorEventListener{


    // Gravity for accelerometer data
    private float[] gravity = new float[3];
    // smoothed values
    private float[] smoothed = new float[3];
    // sensor manager
    private SensorManager sensorManager;
    // sensor gravity
    private Sensor sensorGravity;
    private double bearing = 0;

    private TextView acc;
    private TextView stepView;
    private TextView thresholdView;
    private SeekBar seek;
    private ToggleButton countToggle;

    private int stepCount;
    private boolean toggle;
    private double prevY;
    private double threshold;
    private boolean ignore;
    private int countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor3);
        acc = (TextView) findViewById((R.id.accelerometer));
        thresholdView = (TextView) findViewById((R.id.thresholdView));
        stepView = (TextView) findViewById((R.id.stepView));
        countToggle = (ToggleButton) findViewById(R.id.countToggle);
        seek = (SeekBar) findViewById(R.id.seek);

        seek.setProgress(0);
        seek.incrementProgressBy(1);
        seek.setMax(40);
        // keep screen light on (wake lock light)

        implementListeners();
    }

    protected float[] lowPassFilter( float[] input, float[] output ) {
        if ( output == null ) return input;
        for ( int i=0; i<input.length; i++ ) {
            output[i] = output[i] + 1.0f * (input[i] - output[i]);
        }
        return output;
    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorGravity = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // listen to these sensors
        sensorManager.registerListener(this, sensorGravity,
                SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    protected void onStop() {
        super.onStop();
        // remove listeners
        sensorManager.unregisterListener(this, sensorGravity);
    }







    @Override
    public void onSensorChanged(SensorEvent event) {

        // get accelerometer data
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // we need to use a low pass filter to make data smoothed
            smoothed = lowPassFilter(event.values, gravity);
            gravity[0] = smoothed[0];
            gravity[1] = smoothed[1];
            gravity[2] = smoothed[2];
            //acc.setText("x: "+gravity[0] + " y: " + gravity[1] + " z: " + gravity[2]+ "ignore: "+ ignore + "countdown: "+ countdown);
            if(ignore) {
                countdown--;
                ignore = (countdown < 0)? false : ignore;
            }
            else
                countdown = 22;
            if(toggle && (Math.abs(prevY - gravity[1]) > threshold) && !ignore){
                stepCount++;
                stepView.setText("" + stepCount);
                ignore = true;
            }
            prevY = gravity[1];
        }
    }

    public void implementListeners(){
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                threshold = ((double)seek.getProgress()) * 0.02;
                thresholdView.setText(""+ threshold);
            }
        });

        countToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle = !toggle ;
                if(toggle){
                    stepCount = 0;
                    countdown = 5;
                    ignore = true;
                    stepView.setText("" + stepCount);
                }
            }
        });

    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}