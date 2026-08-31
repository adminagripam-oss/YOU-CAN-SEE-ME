package h6;

import i6.j;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class u {

    /* renamed from: a  reason: collision with root package name */
    public final i6.j f7432a;

    /* renamed from: b  reason: collision with root package name */
    private b f7433b;

    /* renamed from: c  reason: collision with root package name */
    public final j.c f7434c;

    /* loaded from: classes.dex */
    class a implements j.c {
        a() {
        }

        @Override // i6.j.c
        public void onMethodCall(i6.i iVar, j.d dVar) {
            if (u.this.f7433b == null) {
                z5.b.f("SpellCheckChannel", "No SpellCheckeMethodHandler registered, call not forwarded to spell check API.");
                return;
            }
            String str = iVar.f7535a;
            Object obj = iVar.f7536b;
            z5.b.f("SpellCheckChannel", "Received '" + str + "' message.");
            str.hashCode();
            if (!str.equals("SpellCheck.initiateSpellCheck")) {
                dVar.notImplemented();
                return;
            }
            try {
                ArrayList arrayList = (ArrayList) obj;
                u.this.f7433b.a((String) arrayList.get(0), (String) arrayList.get(1), dVar);
            } catch (IllegalStateException e8) {
                dVar.error("error", e8.getMessage(), null);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(String str, String str2, j.d dVar);
    }

    public u(a6.a aVar) {
        a aVar2 = new a();
        this.f7434c = aVar2;
        i6.j jVar = new i6.j(aVar, "flutter/spellcheck", i6.o.f7550b);
        this.f7432a = jVar;
        jVar.e(aVar2);
    }

    public void b(b bVar) {
        this.f7433b = bVar;
    }
}
