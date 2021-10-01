package com.example.powerpal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        button1 = findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWeatherAPI();
            }
        });
    }

    /*
        public void createAccount(View view) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    */
    public void openWeatherAPI() {
        Intent intent = new Intent(this, WeatherAPI.class);
        startActivity(intent);
    }



    public void showAbout (View view){
        new AlertDialog.Builder(this)
                .setTitle("About PowerPal")
                .setMessage(R.string.about)
                .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setIcon((android.R.drawable.ic_dialog_info))
                .show();
    }
}