package com.example.powerpal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    DbManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DbManager(this);
    }

    public void createAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        EditText enterEmail = (EditText) findViewById(R.id.editTextEmail);
        EditText password = (EditText) findViewById(R.id.editTextPassword);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        if (TextUtils.isEmpty(enterEmail.getText()) || !Patterns.EMAIL_ADDRESS.matcher(enterEmail.getText()).matches()) {
            enterEmail.setError("Please enter a valid email address");
            return;
        }
        if (TextUtils.isEmpty(password.getText())) {
            password.setError("Please enter a password");
            return;
        }
        // authentication
        Snackbar.make(view, "Account is not valid!", Snackbar.LENGTH_LONG).show();
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