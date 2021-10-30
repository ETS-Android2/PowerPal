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
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ApplianceSearchActivity extends AppCompatActivity implements ApplianceSearchAdapter.ListItemClickListener{

//    DbManager db =  new DbManager(this);
    public ArrayList<ApplianceItem> applianceList = new ArrayList<>();
    private RecyclerView applianceView;
    private ApplianceSearchAdapter applianceAdapter = new ApplianceSearchAdapter(createApplianceList(), this);
    private RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_appliances);
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
    
    @Override
    public void onListItemClick(int position, String recentlyAdded, float max, float min, float idle) {
        TextView added = findViewById(R.id.recentlyAdded);
        TextView newMax = findViewById(R.id.minWattVal);
        TextView newMin = findViewById(R.id.maxWattVal);
        TextView newIdle = findViewById(R.id.idleWattVal);
        added.setText("     " + recentlyAdded + " added!");
        newMax.setText(String.valueOf(Float.parseFloat(newMax.getText().toString()) + max));
        newMin.setText(String.valueOf(Float.parseFloat(newMin.getText().toString()) + min));
        newIdle.setText(String.valueOf(Float.parseFloat(newIdle.getText().toString()) + idle));

    }

    private ArrayList createApplianceList() {
//        boolean test = db.getAppliance();
        applianceList.add(new ApplianceItem("100W light bulb (Incandescent)","Min: 100 Max: 100 Standby: 0"));
        applianceList.add(new ApplianceItem("22 Inch LED TV","Min: 17 Max: 17 Standby: 0.5"));
        applianceList.add(new ApplianceItem("25 Inch colour TV","Min: 150 Max: 150 Standby: N/A"));
        applianceList.add(new ApplianceItem("3 Inch belt sander","Min: 1000 Max: 1000 Standby: N/A"));
        applianceList.add(new ApplianceItem("32 Inch LED TV","Min: 20 Max: 60 Standby: 1"));
        applianceList.add(new ApplianceItem("42 Inch LED TV","Min: 58 Max: 60 Standby: 0.3"));
        applianceList.add(new ApplianceItem("46 Inch LED TV","Min: 60 Max: 70 Standby: 1"));
        applianceList.add(new ApplianceItem("49 Inch LED TV","Min: 85 Max: 85 Standby: 1"));
        applianceList.add(new ApplianceItem("55 Inch LED TV","Min: 116 Max: 116 Standby: 0.5"));
        applianceList.add(new ApplianceItem("60W light bulb (Incandescent)","Min: 60 Max: 60 Standby: 0"));
        applianceList.add(new ApplianceItem("65 Inch LED TV","Min: 120 Max: 130 Standby: 1"));
        applianceList.add(new ApplianceItem("82 Inch LED TV","Min: 228 Max: 295 Standby: 0.5"));
        applianceList.add(new ApplianceItem("9 Inch disc sander","Min: 1200 Max: 1200 Standby: N/A"));
        applianceList.add(new ApplianceItem("Air Cooler","Min: 65 Max: 80 Standby: N/A"));
        applianceList.add(new ApplianceItem("Air Fryer","Min: 1500 Max: 1500 Standby: N/A"));
        applianceList.add(new ApplianceItem("Air Purifier","Min: 25 Max: 30 Standby: N/A"));
        applianceList.add(new ApplianceItem("Amazon Echo","Min: 3 Max: 3 Standby: 2"));
        applianceList.add(new ApplianceItem("Amazon Echo Dot","Min: 2 Max: 3 Standby: N/A"));
        applianceList.add(new ApplianceItem("Amazon Echo Show","Min: 2 Max: 4 Standby: 0.1"));
        applianceList.add(new ApplianceItem("American-Style Fridge Freezer","Min: 40 Max: 80 Standby: N/A"));
        applianceList.add(new ApplianceItem("Apple TV","Min: 3 Max: 6 Standby: 0.3"));
        applianceList.add(new ApplianceItem("Aquarium Pump","Min: 20 Max: 50 Standby: N/A"));
        applianceList.add(new ApplianceItem("AV Receiver","Min: 450 Max: 450 Standby: N/A"));
        applianceList.add(new ApplianceItem("Bathroom Towel Heater","Min: 60 Max: 150 Standby: N/A"));
        applianceList.add(new ApplianceItem("Ceiling Fan","Min: 60 Max: 70 Standby: 0"));
        applianceList.add(new ApplianceItem("Chromebook","Min: 45 Max: 45 Standby: N/A"));
        applianceList.add(new ApplianceItem("Chromecast","Min: 2 Max: 2 Standby: N/A"));
        applianceList.add(new ApplianceItem("Clock radio","Min: 1 Max: 2 Standby: N/A"));
        applianceList.add(new ApplianceItem("Clothes Dryer","Min: 1000 Max: 4000 Standby: N/A"));
        applianceList.add(new ApplianceItem("Coffee Maker","Min: 800 Max: 1400 Standby: N/A"));
        applianceList.add(new ApplianceItem("Computer Monitor","Min: 25 Max: 30 Standby: N/A"));
        applianceList.add(new ApplianceItem("Cooker Hood","Min: 20 Max: 30 Standby: 0"));
        applianceList.add(new ApplianceItem("Corded Drill","Min: 600 Max: 850 Standby: N/A"));
        applianceList.add(new ApplianceItem("Corded Electric Handheld Leaf Blower","Min: 2500 Max: 2500 Standby: N/A"));
        applianceList.add(new ApplianceItem("Cordless Drill Charger","Min: 70 Max: 150 Standby: N/A"));
        applianceList.add(new ApplianceItem("Curling Iron","Min: 25 Max: 35 Standby: 0"));
        applianceList.add(new ApplianceItem("DAB Mains Radio","Min: 5 Max: 9 Standby: N/A"));
        applianceList.add(new ApplianceItem("Deep Freezer","Min: 19 Max: 19 Standby: N/A"));
        applianceList.add(new ApplianceItem("Dehumidifier","Min: 240 Max: 240 Standby: N/A"));
        applianceList.add(new ApplianceItem("Desktop Computer","Min: 100 Max: 450 Standby: N/A"));
        applianceList.add(new ApplianceItem("Dishwasher","Min: 1200 Max: 1500 Standby: N/A"));
        applianceList.add(new ApplianceItem("Domestic Water Pump","Min: 200 Max: 300 Standby: 0"));
        applianceList.add(new ApplianceItem("DVD Player","Min: 26 Max: 60 Standby: N/A"));
        applianceList.add(new ApplianceItem("Electric Blanket","Min: 200 Max: 200 Standby: N/A"));
        applianceList.add(new ApplianceItem("Electric Boiler","Min: 4000 Max: 14000 Standby: N/A"));
        applianceList.add(new ApplianceItem("Electric Doorbell Transformer","Min: 2 Max: 2 Standby: N/A"));
        applianceList.add(new ApplianceItem("Electric Heater Fan","Min: 2000 Max: 3000 Standby: N/A"));
        applianceList.add(new ApplianceItem("Electric Kettle","Min: 1200 Max: 3000 Standby: 0"));
        applianceList.add(new ApplianceItem("Electric Mower","Min: 1500 Max: 1500 Standby: N/A"));
        applianceList.add(new ApplianceItem("Electric Pressure Cooker","Min: 1000 Max: 1000 Standby: N/A"));
        applianceList.add(new ApplianceItem("Electric Shaver","Min: 15 Max: 20 Standby: N/A"));
        applianceList.add(new ApplianceItem("Electric stove","Min: 2000 Max: 2000 Standby: N/A"));
        applianceList.add(new ApplianceItem("Electric Tankless Water Heater","Min: 6600 Max: 8800 Standby: N/A"));
        applianceList.add(new ApplianceItem("Electric Thermal Radiator","Min: 500 Max: 500 Standby: N/A"));
        applianceList.add(new ApplianceItem("Espresso Coffee Machine","Min: 1300 Max: 1500 Standby: N/A"));
        applianceList.add(new ApplianceItem("EV Car Charger","Min: 2000 Max: 7000 Standby: N/A"));
        applianceList.add(new ApplianceItem("EV Home Charger","Min: 1600 Max: 3400 Standby: N/A"));
        applianceList.add(new ApplianceItem("Evaporative Air Conditioner","Min: 2600 Max: 2600 Standby: N/A"));
        applianceList.add(new ApplianceItem("External Hard Drive","Min: 1 Max: 3 Standby: N/A"));
        applianceList.add(new ApplianceItem("Extractor Fan","Min: 12 Max: 12 Standby: N/A"));
        applianceList.add(new ApplianceItem("Fluorescent Lamp","Min: 28 Max: 45 Standby: N/A"));
        applianceList.add(new ApplianceItem("Food Blender","Min: 300 Max: 400 Standby: N/A"));
        applianceList.add(new ApplianceItem("Food Dehydrator","Min: 800 Max: 800 Standby: N/A"));
        applianceList.add(new ApplianceItem("Freezer","Min: 30 Max: 50 Standby: N/A"));
        applianceList.add(new ApplianceItem("Fridge","Min: 100 Max: 220 Standby: N/A"));
        applianceList.add(new ApplianceItem("Fridge / Freezer","Min: 150 Max: 400 Standby: N/A"));
        applianceList.add(new ApplianceItem("Fryer","Min: 1000 Max: 1000 Standby: N/A"));
        applianceList.add(new ApplianceItem("Game Console","Min: 120 Max: 200 Standby: N/A"));
        applianceList.add(new ApplianceItem("Gaming PC","Min: 300 Max: 600 Standby: 1"));
        applianceList.add(new ApplianceItem("Garage Door Opener","Min: 300 Max: 400 Standby: N/A"));
        applianceList.add(new ApplianceItem("Google Home Mini","Min: 15 Max: 15 Standby: 2"));
        applianceList.add(new ApplianceItem("Guitar Amplifier","Min: 20 Max: 30 Standby: N/A"));
        applianceList.add(new ApplianceItem("Hair Blow Dryer","Min: 1800 Max: 2500 Standby: N/A"));
        applianceList.add(new ApplianceItem("Hand Wash Oversink Water Heater","Min: 3000 Max: 3000 Standby: N/A"));
        applianceList.add(new ApplianceItem("Heated Bathroom Mirror","Min: 50 Max: 100 Standby: N/A"));
        applianceList.add(new ApplianceItem("Heated Hair Rollers","Min: 400 Max: 400 Standby: N/A"));
        applianceList.add(new ApplianceItem("Home Air Conditioner","Min: 1000 Max: 4000 Standby: N/A"));
        applianceList.add(new ApplianceItem("Home Internet Router","Min: 5 Max: 15 Standby: N/A"));
        applianceList.add(new ApplianceItem("Home Phone","Min: 3 Max: 5 Standby: 2"));
        applianceList.add(new ApplianceItem("Home Sound System","Min: 95 Max: 95 Standby: 1"));
        applianceList.add(new ApplianceItem("Hot Water Dispenser","Min: 1200 Max: 1300 Standby: N/A"));
        applianceList.add(new ApplianceItem("Hot Water Immersion Heater","Min: 3000 Max: 3000 Standby: N/A"));
        applianceList.add(new ApplianceItem("Humidifier","Min: 35 Max: 40 Standby: N/A"));
        applianceList.add(new ApplianceItem("iMac","Min: 60 Max: 240 Standby: 1"));
        applianceList.add(new ApplianceItem("Induction Hob (per hob)","Min: 1400 Max: 1800 Standby: N/A"));
        applianceList.add(new ApplianceItem("Inkjet Printer","Min: 20 Max: 30 Standby: N/A"));
        applianceList.add(new ApplianceItem("Inverter Air conditioner","Min: 1300 Max: 1800 Standby: N/A"));
        applianceList.add(new ApplianceItem("Iron","Min: 1000 Max: 1000 Standby: N/A"));
        applianceList.add(new ApplianceItem("Jacuzzi","Min: 3000 Max: 7500 Standby: 1500"));
        applianceList.add(new ApplianceItem("Kitchen Extractor Fan","Min: 200 Max: 200 Standby: N/A"));
        applianceList.add(new ApplianceItem("Laptop Computer","Min: 50 Max: 100 Standby: N/A"));
        applianceList.add(new ApplianceItem("Laser Printer","Min: 600 Max: 800 Standby: N/A"));
        applianceList.add(new ApplianceItem("Lawnmower","Min: 1000 Max: 1400 Standby: N/A"));
        applianceList.add(new ApplianceItem("LED Christmas Lights","Min: 5 Max: 5 Standby: N/A"));
        applianceList.add(new ApplianceItem("LED Light Bulb","Min: 7 Max: 10 Standby: 0"));
        applianceList.add(new ApplianceItem("Mi Box","Min: 5 Max: 7 Standby: 3"));
        applianceList.add(new ApplianceItem("Microwave","Min: 600 Max: 1700 Standby: 3"));
        applianceList.add(new ApplianceItem("Night Light","Min: 1 Max: 1 Standby: 0"));
        applianceList.add(new ApplianceItem("Nintendo Switch AC Adapter","Min: 7 Max: 40 Standby: N/A"));
        applianceList.add(new ApplianceItem("Outdoor Hot Tub","Min: 60 Max: 500 Standby: N/A"));
        applianceList.add(new ApplianceItem("Oven","Min: 2150 Max: 2150 Standby: N/A"));
        applianceList.add(new ApplianceItem("Paper Shredder","Min: 200 Max: 220 Standby: N/A"));
        applianceList.add(new ApplianceItem("Pedestal Fan","Min: 50 Max: 60 Standby: N/A"));
        applianceList.add(new ApplianceItem("Percolator","Min: 800 Max: 1100 Standby: N/A"));
        applianceList.add(new ApplianceItem("Philips Hue Smart Bulb","Min: 8 Max: 9 Standby: 0.4"));
        applianceList.add(new ApplianceItem("Phone Charger","Min: 4 Max: 7 Standby: N/A"));
        applianceList.add(new ApplianceItem("Playstation 4","Min: 85 Max: 90 Standby: N/A"));
        applianceList.add(new ApplianceItem("Playstation 5","Min: 160 Max: 200 Standby: N/A"));
        applianceList.add(new ApplianceItem("Portable Air Conditioner","Min: 1000 Max: 1200 Standby: N/A"));
        applianceList.add(new ApplianceItem("Power Shower","Min: 7500 Max: 10500 Standby: 0"));
        applianceList.add(new ApplianceItem("Pressure Cooker","Min: 700 Max: 700 Standby: N/A"));
        applianceList.add(new ApplianceItem("Pressure Washer","Min: 1500 Max: 1500 Standby: N/A"));
        applianceList.add(new ApplianceItem("Projector","Min: 220 Max: 270 Standby: 1"));
        applianceList.add(new ApplianceItem("Refrigerator","Min: 100 Max: 200 Standby: N/A"));
        applianceList.add(new ApplianceItem("Rice Cooker","Min: 200 Max: 800 Standby: N/A"));
        applianceList.add(new ApplianceItem("Sandwich Maker","Min: 700 Max: 1000 Standby: N/A"));
        applianceList.add(new ApplianceItem("Scanner","Min: 10 Max: 18 Standby: N/A"));
        applianceList.add(new ApplianceItem("Set Top Box","Min: 27 Max: 30 Standby: N/A"));
        applianceList.add(new ApplianceItem("Sewing Machine","Min: 70 Max: 80 Standby: N/A"));
        applianceList.add(new ApplianceItem("Singer Sewing Machine","Min: 100 Max: 100 Standby: N/A"));
        applianceList.add(new ApplianceItem("Sky Q 2TB Box","Min: 40 Max: 40 Standby: N/A"));
        applianceList.add(new ApplianceItem("Slow Cooker","Min: 160 Max: 180 Standby: N/A"));
        applianceList.add(new ApplianceItem("Smoke Detector","Min: 0 Max: 1 Standby: N/A"));
        applianceList.add(new ApplianceItem("Space Heater","Min: 2000 Max: 5000 Standby: N/A"));
        applianceList.add(new ApplianceItem("Steam Iron","Min: 2200 Max: 2500 Standby: N/A"));
        applianceList.add(new ApplianceItem("Steriliser","Min: 650 Max: 650 Standby: N/A"));
        applianceList.add(new ApplianceItem("Straightening Iron","Min: 75 Max: 300 Standby: N/A"));
        applianceList.add(new ApplianceItem("Strimmer","Min: 300 Max: 500 Standby: N/A"));
        applianceList.add(new ApplianceItem("Submersible Water Pump","Min: 200 Max: 400 Standby: N/A"));
        applianceList.add(new ApplianceItem("Table Fan","Min: 10 Max: 25 Standby: N/A"));
        applianceList.add(new ApplianceItem("Table Top Fridge","Min: 10 Max: 15 Standby: N/A"));
        applianceList.add(new ApplianceItem("Tablet Charger","Min: 10 Max: 15 Standby: N/A"));
        applianceList.add(new ApplianceItem("Tablet Computer","Min: 5 Max: 10 Standby: N/A"));
        applianceList.add(new ApplianceItem("Toaster","Min: 800 Max: 1800 Standby: 0"));
        applianceList.add(new ApplianceItem("Tower Fan","Min: 60 Max: 60 Standby: N/A"));
        applianceList.add(new ApplianceItem("Treadmill","Min: 280 Max: 900 Standby: N/A"));
        applianceList.add(new ApplianceItem("Tube Light (1500mm)","Min: 22 Max: 22 Standby: N/A"));
        applianceList.add(new ApplianceItem("TV (19 Inch colour)","Min: 40 Max: 100 Standby: 1"));
        applianceList.add(new ApplianceItem("Vacuum Cleaner","Min: 450 Max: 900 Standby: 0"));
        applianceList.add(new ApplianceItem("Wall Fan","Min: 45 Max: 60 Standby: 0"));
        applianceList.add(new ApplianceItem("Washing Machine","Min: 500 Max: 500 Standby: 1"));
        applianceList.add(new ApplianceItem("Water Dispenser","Min: 100 Max: 100 Standby: N/A"));
        applianceList.add(new ApplianceItem("Water Feature","Min: 35 Max: 35 Standby: N/A"));
        applianceList.add(new ApplianceItem("Water Filter and Cooler","Min: 70 Max: 100 Standby: N/A"));
        applianceList.add(new ApplianceItem("WiFi Booster","Min: 1 Max: 2 Standby: N/A"));
        applianceList.add(new ApplianceItem("WiFi Router","Min: 4 Max: 10 Standby: 4"));
        applianceList.add(new ApplianceItem("Window Air Conditioner","Min: 500 Max: 1500 Standby: N/A"));
        applianceList.add(new ApplianceItem("Wine cooler (18 bottles)","Min: 83 Max: 83 Standby: 0"));
        applianceList.add(new ApplianceItem("Xbox One","Min: 50 Max: 110 Standby: 14"));
        return applianceList;

    }

    private void buildRecyclerView() {
        applianceView = findViewById(R.id.applianceRecycler);
        applianceView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);


        applianceView.setLayoutManager(layoutManager);
        applianceView.setAdapter(applianceAdapter);
    }

}