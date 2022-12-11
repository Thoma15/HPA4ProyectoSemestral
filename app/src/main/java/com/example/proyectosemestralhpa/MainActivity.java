package com.example.proyectosemestralhpa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner spin;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        spin = (Spinner) findViewById(R.id.spinner);
        String[] opciones = {"Sensor 1 Proximidad", "Sensor 2 Gestos","Sensor 3 Detector", "Sensor 4 Orientación"};
        ArrayAdapter<String> aa = new
                ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opciones);
        spin.setAdapter(aa);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //int pos = 0;
               // changeActivity(pos);
                //private void changeActivity(int pos) {
                String selec = spin.getSelectedItem().toString();
                     if(selec.equals("Sensor 1 Proximidad")){
                        Intent intent = new Intent(MainActivity.this, Sensor1.class);
                        startActivity(intent);
                    }
                    else if(selec.equals("Sensor 2 Reconocimiento")){
                        Intent intent = new Intent(MainActivity.this, Sensor2.class);
                        startActivity(intent);
                    }
                    else if(selec.equals("Sensor 3 Detector")){
                        Intent intent = new Intent(MainActivity.this, Sensor3.class);
                        startActivity(intent);
                    }
                    else if(selec.equals("Sensor 4 Orientación")){
                        Intent intent = new Intent(MainActivity.this, Sensor4.class);
                        startActivity(intent);
                    }}
            });}}
