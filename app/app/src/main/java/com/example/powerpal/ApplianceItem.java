package com.example.powerpal;

public class ApplianceItem {
    private String applianceName;
    private String applianceSpecs;

    public ApplianceItem(String _applianceName, String _applianceSpecs) {
        applianceName = _applianceName;
        applianceSpecs = _applianceSpecs;
    }

    public String getApplianceName() {
        return applianceName;
    }

    public String getApplianceSpecs() {
        return applianceSpecs;
    }
}