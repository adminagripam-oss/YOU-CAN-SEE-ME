package com.pichillilorenzo.flutter_inappwebview_android.types;
/* loaded from: classes.dex */
public enum UserScriptInjectionTime {
    AT_DOCUMENT_START(0),
    AT_DOCUMENT_END(1);
    
    private final int value;

    UserScriptInjectionTime(int i8) {
        this.value = i8;
    }

    public static UserScriptInjectionTime fromValue(int i8) {
        UserScriptInjectionTime[] values;
        for (UserScriptInjectionTime userScriptInjectionTime : values()) {
            if (i8 == userScriptInjectionTime.toValue()) {
                return userScriptInjectionTime;
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
