package p2;

import java.util.Map;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final String f10010a;

    /* renamed from: b  reason: collision with root package name */
    private final String f10011b;

    private a(String str, String str2) {
        this.f10010a = str;
        this.f10011b = str2;
    }

    public static a c(Map map) {
        if (map == null) {
            return null;
        }
        return new a((String) map.get("name"), (String) map.get("defType"));
    }

    public String a() {
        return this.f10011b;
    }

    public String b() {
        return this.f10010a;
    }
}
