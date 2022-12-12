package com.example.proyectosemestralhpa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class Sensor2 extends AppCompatActivity {

    private GestureDetectorCompat mGestureDetector;
    private ImageView mImage1, mImage2;
    private TextView textpress;
    private Button buttonreturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor2);

        mImage1 = findViewById(R.id.image1);
        mImage2 = findViewById(R.id.image2);
        textpress = findViewById(R.id.textpress);
        buttonreturn = findViewById(R.id.button6);

        mGestureDetector = new GestureDetectorCompat(this, new GestureListener());


        buttonreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Sensor2.this, MainActivity.class));
            }
        });

    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public void onLongPress(MotionEvent e) {
            Toast.makeText(Sensor2.this, "Gesto de toque largo detectado", Toast.LENGTH_SHORT).show();
            buttonreturn.setVisibility(View.VISIBLE);
            textpress.setVisibility(View.INVISIBLE);
            mImage1.setVisibility(View.INVISIBLE);
            mImage2.setVisibility(View.INVISIBLE);
            super.onLongPress(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Toast.makeText(Sensor2.this, "Gesto de arrojar detectado", Toast.LENGTH_SHORT).show();
            buttonreturn.setVisibility(View.INVISIBLE);
            textpress.setVisibility(View.VISIBLE);
            mImage1.setVisibility(View.INVISIBLE);
            mImage2.setVisibility(View.INVISIBLE);
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Toast.makeText(Sensor2.this, "Gesto de doble tap detectado", Toast.LENGTH_SHORT).show();
            buttonreturn.setVisibility(View.INVISIBLE);
            textpress.setVisibility(View.INVISIBLE);
            mImage1.setVisibility(View.VISIBLE);
            mImage2.setVisibility(View.VISIBLE);
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Toast.makeText(Sensor2.this, "Gesto de un solo tap detectado", Toast.LENGTH_SHORT).show();
            buttonreturn.setVisibility(View.INVISIBLE);
            textpress.setVisibility(View.INVISIBLE);
            mImage1.setVisibility(View.VISIBLE);
            mImage2.setVisibility(View.INVISIBLE);
            return super.onSingleTapConfirmed(e);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
