package com.example.powerpal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.powerpal.DbManager;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public boolean checkPassword(String password) {
        boolean hasChar = false, hasInt = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i))) {
                hasChar = true;
            }
            else if (Character.isDigit(password.charAt(i))) {
                hasInt = true;
            }
        }
        return hasChar && hasInt;
    }

    public boolean validateFields() {
        EditText enterEmail = (EditText) findViewById(R.id.editTextCreateEmail);
        EditText confirmEmail = (EditText) findViewById(R.id.editTextConfirmEmail);
        EditText password = (EditText) findViewById(R.id.editTextCreatePassword);

        if (TextUtils.isEmpty(enterEmail.getText()) || !Patterns.EMAIL_ADDRESS.matcher(enterEmail.getText()).matches()) {
            enterEmail.setError("Please enter a valid email address");
            return false;
        }
        if (!confirmEmail.getText().toString().equalsIgnoreCase(enterEmail.getText().toString())) {
            confirmEmail.setError("Email addresses do not match");
            return false;
        }
        if (password.getText().length() < 8) {
            password.setError("Password must be at least 8 characters.");
            return false;
        }
        else if (!checkPassword(password.getText().toString())) {
            password.setError("Password must contain letters and numbers.");
            return false;
        }
        return true;
    }

    public void login(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        if (validateFields()) {
            EditText enterEmail = (EditText) findViewById(R.id.editTextCreateEmail);
            EditText password = (EditText) findViewById(R.id.editTextCreatePassword);
            if (!com.example.powerpal.DbManager.createNewAccount(enterEmail.getText().toString(), password.getText().toString())) {
                enterEmail.setError("An account already exists for this email");
            }

        }
    }
}