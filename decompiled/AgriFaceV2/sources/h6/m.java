package h6;

import i6.j;
/* loaded from: classes.dex */
public class m {

    /* renamed from: a  reason: collision with root package name */
    public final i6.j f7304a;

    /* renamed from: b  reason: collision with root package name */
    private b f7305b;

    /* renamed from: c  reason: collision with root package name */
    private final j.c f7306c;

    /* loaded from: classes.dex */
    class a implements j.c {
        a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0040  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0041 A[Catch: Exception -> 0x0076, TRY_LEAVE, TryCatch #1 {Exception -> 0x0076, blocks: (B:6:0x0029, B:15:0x0041, B:17:0x0056, B:19:0x005d, B:9:0x0033, B:16:0x004d), top: B:26:0x0029, inners: #0 }] */
        @Override // i6.j.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onMethodCall(i6.i r6, i6.j.d r7) {
            /*
                r5 = this;
                java.lang.String r0 = "error"
                h6.m r1 = h6.m.this
                h6.m$b r1 = h6.m.a(r1)
                if (r1 != 0) goto Lb
                return
            Lb:
                java.lang.String r1 = r6.f7535a
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "Received '"
                r2.append(r3)
                r2.append(r1)
                java.lang.String r3 = "' message."
                r2.append(r3)
                java.lang.String r2 = r2.toString()
                java.lang.String r3 = "MouseCursorChannel"
                z5.b.f(r3, r2)
                r2 = 0
                int r3 = r1.hashCode()     // Catch: java.lang.Exception -> L76
                r4 = -1307105544(0xffffffffb21726f8, float:-8.798217E-9)
                if (r3 == r4) goto L33
                goto L3d
            L33:
                java.lang.String r3 = "activateSystemCursor"
                boolean r1 = r1.equals(r3)     // Catch: java.lang.Exception -> L76
                if (r1 == 0) goto L3d
                r1 = 0
                goto L3e
            L3d:
                r1 = -1
            L3e:
                if (r1 == 0) goto L41
                goto L8f
            L41:
                java.lang.Object r6 = r6.f7536b     // Catch: java.lang.Exception -> L76
                java.util.HashMap r6 = (java.util.HashMap) r6     // Catch: java.lang.Exception -> L76
                java.lang.String r1 = "kind"
                java.lang.Object r6 = r6.get(r1)     // Catch: java.lang.Exception -> L76
                java.lang.String r6 = (java.lang.String) r6     // Catch: java.lang.Exception -> L76
                h6.m r1 = h6.m.this     // Catch: java.lang.Exception -> L5c
                h6.m$b r1 = h6.m.a(r1)     // Catch: java.lang.Exception -> L5c
                r1.a(r6)     // Catch: java.lang.Exception -> L5c
                java.lang.Boolean r6 = java.lang.Boolean.TRUE     // Catch: java.lang.Exception -> L76
                r7.success(r6)     // Catch: java.lang.Exception -> L76
                goto L8f
            L5c:
                r6 = move-exception
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L76
                r1.<init>()     // Catch: java.lang.Exception -> L76
                java.lang.String r3 = "Error when setting cursors: "
                r1.append(r3)     // Catch: java.lang.Exception -> L76
                java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Exception -> L76
                r1.append(r6)     // Catch: java.lang.Exception -> L76
                java.lang.String r6 = r1.toString()     // Catch: java.lang.Exception -> L76
                r7.error(r0, r6, r2)     // Catch: java.lang.Exception -> L76
                goto L8f
            L76:
                r6 = move-exception
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r3 = "Unhandled error: "
                r1.append(r3)
                java.lang.String r6 = r6.getMessage()
                r1.append(r6)
                java.lang.String r6 = r1.toString()
                r7.error(r0, r6, r2)
            L8f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: h6.m.a.onMethodCall(i6.i, i6.j$d):void");
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(String str);
    }

    public m(a6.a aVar) {
        a aVar2 = new a();
        this.f7306c = aVar2;
        i6.j jVar = new i6.j(aVar, "flutter/mousecursor", i6.o.f7550b);
        this.f7304a = jVar;
        jVar.e(aVar2);
    }

    public void b(b bVar) {
        this.f7305b = bVar;
    }
}
