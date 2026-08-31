package h6;

import i6.j;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class s {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f7405a;

    /* renamed from: b  reason: collision with root package name */
    private byte[] f7406b;

    /* renamed from: c  reason: collision with root package name */
    private i6.j f7407c;

    /* renamed from: d  reason: collision with root package name */
    private j.d f7408d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f7409e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f7410f;

    /* renamed from: g  reason: collision with root package name */
    private final j.c f7411g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements j.d {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ byte[] f7412a;

        a(byte[] bArr) {
            this.f7412a = bArr;
        }

        @Override // i6.j.d
        public void error(String str, String str2, Object obj) {
            z5.b.b("RestorationChannel", "Error " + str + " while sending restoration data to framework: " + str2);
        }

        @Override // i6.j.d
        public void notImplemented() {
        }

        @Override // i6.j.d
        public void success(Object obj) {
            s.this.f7406b = this.f7412a;
        }
    }

    /* loaded from: classes.dex */
    class b implements j.c {
        b() {
        }

        @Override // i6.j.c
        public void onMethodCall(i6.i iVar, j.d dVar) {
            Map i8;
            String str = iVar.f7535a;
            Object obj = iVar.f7536b;
            str.hashCode();
            if (str.equals("get")) {
                s.this.f7410f = true;
                if (!s.this.f7409e) {
                    s sVar = s.this;
                    if (sVar.f7405a) {
                        sVar.f7408d = dVar;
                        return;
                    }
                }
                s sVar2 = s.this;
                i8 = sVar2.i(sVar2.f7406b);
            } else if (!str.equals("put")) {
                dVar.notImplemented();
                return;
            } else {
                s.this.f7406b = (byte[]) obj;
                i8 = null;
            }
            dVar.success(i8);
        }
    }

    public s(a6.a aVar, boolean z7) {
        this(new i6.j(aVar, "flutter/restoration", i6.o.f7550b), z7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map i(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put("enabled", Boolean.TRUE);
        hashMap.put("data", bArr);
        return hashMap;
    }

    public void g() {
        this.f7406b = null;
    }

    public byte[] h() {
        return this.f7406b;
    }

    public void j(byte[] bArr) {
        this.f7409e = true;
        j.d dVar = this.f7408d;
        if (dVar != null) {
            dVar.success(i(bArr));
            this.f7408d = null;
        } else if (this.f7410f) {
            this.f7407c.d("push", i(bArr), new a(bArr));
            return;
        }
        this.f7406b = bArr;
    }

    s(i6.j jVar, boolean z7) {
        this.f7409e = false;
        this.f7410f = false;
        b bVar = new b();
        this.f7411g = bVar;
        this.f7407c = jVar;
        this.f7405a = z7;
        jVar.e(bVar);
    }
}
