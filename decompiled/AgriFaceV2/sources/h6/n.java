package h6;

import i6.j;
import java.util.HashMap;
/* loaded from: classes.dex */
public class n {

    /* renamed from: a  reason: collision with root package name */
    public final i6.j f7308a;

    /* renamed from: b  reason: collision with root package name */
    private final j.c f7309b;

    /* loaded from: classes.dex */
    class a implements j.c {
        a() {
        }

        @Override // i6.j.c
        public void onMethodCall(i6.i iVar, j.d dVar) {
            dVar.success(null);
        }
    }

    public n(a6.a aVar) {
        a aVar2 = new a();
        this.f7309b = aVar2;
        i6.j jVar = new i6.j(aVar, "flutter/navigation", i6.f.f7534a);
        this.f7308a = jVar;
        jVar.e(aVar2);
    }

    public void a() {
        z5.b.f("NavigationChannel", "Sending message to pop route.");
        this.f7308a.c("popRoute", null);
    }

    public void b(String str) {
        z5.b.f("NavigationChannel", "Sending message to push route information '" + str + "'");
        HashMap hashMap = new HashMap();
        hashMap.put("location", str);
        this.f7308a.c("pushRouteInformation", hashMap);
    }

    public void c(String str) {
        z5.b.f("NavigationChannel", "Sending message to set initial route to '" + str + "'");
        this.f7308a.c("setInitialRoute", str);
    }
}
