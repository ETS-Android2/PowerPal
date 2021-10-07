package com.example.powerpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class ApplianceSearchActivity extends AppCompatActivity {

    DbManager db =  new DbManager(this);
    private ArrayList<ApplianceItem> applianceList;

    private RecyclerView applianceView;
    private ApplianceSearchAdapter applianceAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance_search);
        createExampleList();
        buildRecyclerView();

        EditText editText = findViewById(R.id.applianceSearchField);

        //https://stackoverflow.com/questions/7397391/event-for-handling-the-focus-of-the-edittext
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    if(editText.getText().toString().equals("Enter Appliance Name"))
                        editText.setText("");
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    private void filter(String text) {
        ArrayList<ApplianceItem> filteredList = new ArrayList<>();

        for (ApplianceItem item : applianceList) {
            if (item.getApplianceName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        applianceAdapter.filterList(filteredList);
    }

    private void createExampleList() {
        boolean test = db.getAppliance();
        applianceList = new ArrayList<>();
        applianceList.add(new ApplianceItem("One", "Line 2"));
        applianceList.add(new ApplianceItem("Two", "Line 2"));
        applianceList.add(new ApplianceItem("Three", "Line 2"));
        applianceList.add(new ApplianceItem("Four", "Line 2"));
        applianceList.add(new ApplianceItem("Five", "Line 2"));
        applianceList.add(new ApplianceItem("Six", "Line 2"));
        applianceList.add(new ApplianceItem("Seven", "Line 2"));
        applianceList.add(new ApplianceItem("Eight", "Line 2"));
        applianceList.add(new ApplianceItem("Nine", "Line 2"));
    }

    private void buildRecyclerView() {
        applianceView = findViewById(R.id.applianceRecycler);
        applianceView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        applianceAdapter = new ApplianceSearchAdapter(applianceList);

        applianceView.setLayoutManager(layoutManager);
        applianceView.setAdapter(applianceAdapter);
    }

}