package h6;

import android.content.pm.PackageManager;
import i6.j;
import java.util.ArrayList;
import java.util.Map;
/* loaded from: classes.dex */
public class r {

    /* renamed from: a  reason: collision with root package name */
    public final i6.j f7400a;

    /* renamed from: b  reason: collision with root package name */
    public final PackageManager f7401b;

    /* renamed from: c  reason: collision with root package name */
    private b f7402c;

    /* renamed from: d  reason: collision with root package name */
    public final j.c f7403d;

    /* loaded from: classes.dex */
    class a implements j.c {
        a() {
        }

        @Override // i6.j.c
        public void onMethodCall(i6.i iVar, j.d dVar) {
            if (r.this.f7402c == null) {
                return;
            }
            String str = iVar.f7535a;
            Object obj = iVar.f7536b;
            str.hashCode();
            try {
                if (str.equals("ProcessText.processTextAction")) {
                    ArrayList arrayList = (ArrayList) obj;
                    boolean booleanValue = ((Boolean) arrayList.get(2)).booleanValue();
                    r.this.f7402c.a((String) arrayList.get(0), (String) arrayList.get(1), booleanValue, dVar);
                } else if (!str.equals("ProcessText.queryTextActions")) {
                    dVar.notImplemented();
                } else {
                    dVar.success(r.this.f7402c.b());
                }
            } catch (IllegalStateException e8) {
                dVar.error("error", e8.getMessage(), null);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(String str, String str2, boolean z7, j.d dVar);

        Map b();
    }

    public r(a6.a aVar, PackageManager packageManager) {
        a aVar2 = new a();
        this.f7403d = aVar2;
        this.f7401b = packageManager;
        i6.j jVar = new i6.j(aVar, "flutter/processtext", i6.o.f7550b);
        this.f7400a = jVar;
        jVar.e(aVar2);
    }

    public void b(b bVar) {
        this.f7402c = bVar;
    }
}
