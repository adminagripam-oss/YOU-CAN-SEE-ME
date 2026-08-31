package com.pichillilorenzo.flutter_inappwebview_android.types;
/* loaded from: classes.dex */
public enum NavigationActionPolicy {
    CANCEL(0),
    ALLOW(1);
    
    private final int value;

    NavigationActionPolicy(int i8) {
        this.value = i8;
    }

    public static NavigationActionPolicy fromValue(int i8) {
        NavigationActionPolicy[] values;
        for (NavigationActionPolicy navigationActionPolicy : values()) {
            if (i8 == navigationActionPolicy.value) {
                return navigationActionPolicy;
            }
        }
        throw new IllegalArgumentException("No enum constant: " + i8);
    }

    public boolean equalsValue(int i8) {
        return this.value == i8;
    }

    public int rawValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.value);
    }
}
