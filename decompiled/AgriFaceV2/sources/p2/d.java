package p2;

import java.util.Map;
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private final String f10031a;

    /* renamed from: b  reason: collision with root package name */
    private final String f10032b;

    /* renamed from: c  reason: collision with root package name */
    private final String f10033c;

    /* renamed from: d  reason: collision with root package name */
    private final a f10034d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f10035e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f10036f;

    /* renamed from: g  reason: collision with root package name */
    private final boolean f10037g;

    /* renamed from: h  reason: collision with root package name */
    private final Integer f10038h;

    private d(String str, String str2, String str3, a aVar, boolean z7, boolean z8, boolean z9, Integer num) {
        this.f10031a = str;
        this.f10032b = str2;
        this.f10033c = str3;
        this.f10034d = aVar;
        this.f10035e = z7;
        this.f10036f = z8;
        this.f10037g = z9;
        this.f10038h = num;
    }

    public static d i(Map map) {
        if (map == null) {
            return null;
        }
        a c8 = a.c((Map) map.get("notificationIcon"));
        String str = (String) map.get("notificationTitle");
        String str2 = (String) map.get("notificationChannelName");
        String str3 = (String) map.get("notificationText");
        Boolean bool = (Boolean) map.get("enableWifiLock");
        Boolean bool2 = (Boolean) map.get("enableWakeLock");
        Boolean bool3 = (Boolean) map.get("setOngoing");
        Object obj = map.get("color");
        return new d(str, str3, str2, c8, bool.booleanValue(), bool2.booleanValue(), bool3.booleanValue(), obj != null ? Integer.valueOf(((Number) obj).intValue()) : null);
    }

    public Integer a() {
        return this.f10038h;
    }

    public String b() {
        return this.f10033c;
    }

    public a c() {
        return this.f10034d;
    }

    public String d() {
        return this.f10032b;
    }

    public String e() {
        return this.f10031a;
    }

    public boolean f() {
        return this.f10036f;
    }

    public boolean g() {
        return this.f10035e;
    }

    public boolean h() {
        return this.f10037g;
    }
}
