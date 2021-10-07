package com.example.powerpal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void showAppliance(View view) {
        Intent intent = new Intent(this, ApplianceSearchActivity.class);
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
