package com.example.powerpal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }
    public void login(View view) {
        // if incorrect email, or blank password, display error message
        // else, authenticate
            // if successful, open new activity
            // else, display error message

        new AlertDialog.Builder(this)
                .setTitle("Not implemented")
                .setMessage("We haven't implemented this yet..")
                .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setIcon((android.R.drawable.ic_dialog_alert))
                .show();

    }
    public void showAbout(View view) {
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