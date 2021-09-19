package com.example.powerpal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }
    public void login(View view) {
        // validate emails
        // validate password
        // send to database
        // if unsuccessful, display error

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
}