package h6;

import i6.j;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    public final i6.j f7284a;

    /* renamed from: b  reason: collision with root package name */
    private b f7285b;

    /* renamed from: c  reason: collision with root package name */
    public final j.c f7286c;

    /* loaded from: classes.dex */
    class a implements j.c {

        /* renamed from: a  reason: collision with root package name */
        Map f7287a = new HashMap();

        a() {
        }

        @Override // i6.j.c
        public void onMethodCall(i6.i iVar, j.d dVar) {
            if (j.this.f7285b != null) {
                String str = iVar.f7535a;
                str.hashCode();
                if (!str.equals("getKeyboardState")) {
                    dVar.notImplemented();
                    return;
                }
                try {
                    this.f7287a = j.this.f7285b.a();
                } catch (IllegalStateException e8) {
                    dVar.error("error", e8.getMessage(), null);
                }
            }
            dVar.success(this.f7287a);
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        Map a();
    }

    public j(i6.b bVar) {
        a aVar = new a();
        this.f7286c = aVar;
        i6.j jVar = new i6.j(bVar, "flutter/keyboard", i6.o.f7550b);
        this.f7284a = jVar;
        jVar.e(aVar);
    }

    public void b(b bVar) {
        this.f7285b = bVar;
    }
}
