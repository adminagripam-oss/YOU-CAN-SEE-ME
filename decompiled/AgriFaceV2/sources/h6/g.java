package h6;

import i6.j;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private final i6.j f7276a;

    /* renamed from: b  reason: collision with root package name */
    private Map f7277b;

    /* renamed from: c  reason: collision with root package name */
    final j.c f7278c;

    /* loaded from: classes.dex */
    class a implements j.c {
        a() {
        }

        @Override // i6.j.c
        public void onMethodCall(i6.i iVar, j.d dVar) {
            g.a(g.this);
        }
    }

    public g(a6.a aVar) {
        a aVar2 = new a();
        this.f7278c = aVar2;
        i6.j jVar = new i6.j(aVar, "flutter/deferredcomponent", i6.o.f7550b);
        this.f7276a = jVar;
        jVar.e(aVar2);
        z5.a.e().a();
        this.f7277b = new HashMap();
    }

    static /* synthetic */ b6.a a(g gVar) {
        gVar.getClass();
        return null;
    }
}
