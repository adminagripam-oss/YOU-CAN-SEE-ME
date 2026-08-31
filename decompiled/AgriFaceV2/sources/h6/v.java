package h6;

import java.util.HashMap;
/* loaded from: classes.dex */
public class v {

    /* renamed from: a  reason: collision with root package name */
    public final i6.a f7436a;

    public v(a6.a aVar) {
        this.f7436a = new i6.a(aVar, "flutter/system", i6.e.f7533a);
    }

    public void a() {
        z5.b.f("SystemChannel", "Sending memory pressure warning to Flutter.");
        HashMap hashMap = new HashMap(1);
        hashMap.put("type", "memoryPressure");
        this.f7436a.c(hashMap);
    }
}
