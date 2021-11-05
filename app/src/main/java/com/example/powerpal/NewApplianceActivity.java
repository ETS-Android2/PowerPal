package com.example.powerpal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;


public class NewApplianceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_appliance);
    }

    public void addNew(View view){
        addNewApp(String.valueOf(R.id.newAppliance),
                String.valueOf(R.id.newMax),
                String.valueOf(R.id.newMin),
                String.valueOf(R.id.newIdle));
     }

}