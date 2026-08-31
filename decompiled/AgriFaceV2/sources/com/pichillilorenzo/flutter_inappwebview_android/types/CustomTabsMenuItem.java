package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.Map;
/* loaded from: classes.dex */
public class CustomTabsMenuItem {
    private int id;
    private String label;

    public CustomTabsMenuItem(int i8, String str) {
        this.id = i8;
        this.label = str;
    }

    public static CustomTabsMenuItem fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return new CustomTabsMenuItem(((Integer) map.get("id")).intValue(), (String) map.get("label"));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CustomTabsMenuItem customTabsMenuItem = (CustomTabsMenuItem) obj;
        if (this.id != customTabsMenuItem.id) {
            return false;
        }
        return this.label.equals(customTabsMenuItem.label);
    }

    public int getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }

    public int hashCode() {
        return (this.id * 31) + this.label.hashCode();
    }

    public void setId(int i8) {
        this.id = i8;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public String toString() {
        return "CustomTabsMenuItem{id=" + this.id + ", label='" + this.label + "'}";
    }
}
