package com.pichillilorenzo.flutter_inappwebview_android.content_blocker;
/* loaded from: classes.dex */
public enum ContentBlockerActionType {
    BLOCK("block"),
    CSS_DISPLAY_NONE("css-display-none"),
    MAKE_HTTPS("make-https");
    
    private final String value;

    ContentBlockerActionType(String str) {
        this.value = str;
    }

    public static ContentBlockerActionType fromValue(String str) {
        ContentBlockerActionType[] values;
        for (ContentBlockerActionType contentBlockerActionType : values()) {
            if (str.equals(contentBlockerActionType.value)) {
                return contentBlockerActionType;
            }
        }
        throw new IllegalArgumentException("No enum constant: " + str);
    }

    public boolean equalsValue(String str) {
        return this.value.equals(str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
