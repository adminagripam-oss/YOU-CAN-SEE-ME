package com.pichillilorenzo.flutter_inappwebview_android.content_blocker;
/* loaded from: classes.dex */
public enum ContentBlockerTriggerResourceType {
    DOCUMENT("document"),
    IMAGE("image"),
    STYLE_SHEET("style-sheet"),
    SCRIPT("script"),
    FONT("font"),
    SVG_DOCUMENT("svg-document"),
    MEDIA("media"),
    POPUP("popup"),
    RAW("raw");
    
    private final String value;

    ContentBlockerTriggerResourceType(String str) {
        this.value = str;
    }

    public static ContentBlockerTriggerResourceType fromValue(String str) {
        ContentBlockerTriggerResourceType[] values;
        for (ContentBlockerTriggerResourceType contentBlockerTriggerResourceType : values()) {
            if (str.equals(contentBlockerTriggerResourceType.value)) {
                return contentBlockerTriggerResourceType;
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
