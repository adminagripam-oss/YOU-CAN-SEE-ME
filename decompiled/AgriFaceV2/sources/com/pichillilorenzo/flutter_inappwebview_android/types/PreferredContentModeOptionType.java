package com.pichillilorenzo.flutter_inappwebview_android.types;
/* loaded from: classes.dex */
public enum PreferredContentModeOptionType {
    RECOMMENDED(0),
    MOBILE(1),
    DESKTOP(2);
    
    private final int value;

    PreferredContentModeOptionType(int i8) {
        this.value = i8;
    }

    public static PreferredContentModeOptionType fromValue(int i8) {
        PreferredContentModeOptionType[] values;
        for (PreferredContentModeOptionType preferredContentModeOptionType : values()) {
            if (i8 == preferredContentModeOptionType.toValue()) {
                return preferredContentModeOptionType;
            }
        }
        throw new IllegalArgumentException("No enum constant: " + i8);
    }

    public boolean equalsValue(int i8) {
        return this.value == i8;
    }

    public int toValue() {
        return this.value;
    }
}
