package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.Arrays;
import java.util.Map;
/* loaded from: classes.dex */
public class CustomTabsActionButton {
    private String description;
    private byte[] icon;
    private int id;
    private boolean shouldTint;

    public CustomTabsActionButton(int i8, byte[] bArr, String str, boolean z7) {
        this.id = i8;
        this.icon = bArr;
        this.description = str;
        this.shouldTint = z7;
    }

    public static CustomTabsActionButton fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return new CustomTabsActionButton(((Integer) map.get("id")).intValue(), (byte[]) map.get("icon"), (String) map.get("description"), ((Boolean) map.get("shouldTint")).booleanValue());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CustomTabsActionButton customTabsActionButton = (CustomTabsActionButton) obj;
        if (this.id == customTabsActionButton.id && this.shouldTint == customTabsActionButton.shouldTint && Arrays.equals(this.icon, customTabsActionButton.icon)) {
            return this.description.equals(customTabsActionButton.description);
        }
        return false;
    }

    public String getDescription() {
        return this.description;
    }

    public byte[] getIcon() {
        return this.icon;
    }

    public int getId() {
        return this.id;
    }

    public int hashCode() {
        return (((((this.id * 31) + Arrays.hashCode(this.icon)) * 31) + this.description.hashCode()) * 31) + (this.shouldTint ? 1 : 0);
    }

    public boolean isShouldTint() {
        return this.shouldTint;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setIcon(byte[] bArr) {
        this.icon = bArr;
    }

    public void setId(int i8) {
        this.id = i8;
    }

    public void setShouldTint(boolean z7) {
        this.shouldTint = z7;
    }

    public String toString() {
        return "CustomTabsActionButton{id=" + this.id + ", icon=" + Arrays.toString(this.icon) + ", description='" + this.description + "', shouldTint=" + this.shouldTint + '}';
    }
}
