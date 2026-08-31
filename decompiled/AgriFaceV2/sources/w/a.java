package w;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import s.o0;
import x.h;
import y.p;
import z.a;
/* loaded from: classes.dex */
public class a implements z.a {

    /* renamed from: a  reason: collision with root package name */
    private final o0 f12442a;

    /* renamed from: f  reason: collision with root package name */
    private int f12447f = 0;

    /* renamed from: c  reason: collision with root package name */
    private final Map f12444c = new HashMap();

    /* renamed from: e  reason: collision with root package name */
    private Set f12446e = new HashSet();

    /* renamed from: b  reason: collision with root package name */
    private final List f12443b = new ArrayList();

    /* renamed from: d  reason: collision with root package name */
    private List f12445d = new ArrayList();

    public a(o0 o0Var) {
        this.f12442a = o0Var;
        e();
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0074 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0017 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e() {
        /*
            r10 = this;
            java.lang.String r0 = "Camera2CameraCoordinator"
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            s.o0 r2 = r10.f12442a     // Catch: s.g -> Le
            java.util.Set r1 = r2.e()     // Catch: s.g -> Le
            goto L13
        Le:
            java.lang.String r2 = "Failed to get concurrent camera ids"
            y.f1.c(r0, r2)
        L13:
            java.util.Iterator r1 = r1.iterator()
        L17:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto Lce
            java.lang.Object r2 = r1.next()
            java.util.Set r2 = (java.util.Set) r2
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>(r2)
            int r2 = r3.size()
            r4 = 2
            if (r2 < r4) goto L17
            r2 = 0
            java.lang.Object r4 = r3.get(r2)
            java.lang.String r4 = (java.lang.String) r4
            r5 = 1
            java.lang.Object r6 = r3.get(r5)
            java.lang.String r6 = (java.lang.String) r6
            s.o0 r7 = r10.f12442a     // Catch: y.d1 -> L4f
            boolean r7 = r.k2.a(r7, r4)     // Catch: y.d1 -> L4f
            if (r7 == 0) goto L70
            s.o0 r7 = r10.f12442a     // Catch: y.d1 -> L4f
            boolean r7 = r.k2.a(r7, r6)     // Catch: y.d1 -> L4f
            if (r7 == 0) goto L70
            r7 = r5
            goto L71
        L4f:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Concurrent camera id pair: ("
            r7.append(r8)
            r7.append(r4)
            java.lang.String r8 = ", "
            r7.append(r8)
            r7.append(r6)
            java.lang.String r8 = ") is not backward compatible"
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            y.f1.a(r0, r7)
        L70:
            r7 = r2
        L71:
            if (r7 != 0) goto L74
            goto L17
        L74:
            java.util.Set r7 = r10.f12446e
            java.util.HashSet r8 = new java.util.HashSet
            java.lang.String[] r9 = new java.lang.String[]{r4, r6}
            java.util.List r9 = java.util.Arrays.asList(r9)
            r8.<init>(r9)
            r7.add(r8)
            java.util.Map r7 = r10.f12444c
            boolean r7 = r7.containsKey(r4)
            if (r7 != 0) goto L98
            java.util.Map r7 = r10.f12444c
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r7.put(r4, r8)
        L98:
            java.util.Map r7 = r10.f12444c
            boolean r7 = r7.containsKey(r6)
            if (r7 != 0) goto Laa
            java.util.Map r7 = r10.f12444c
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r7.put(r6, r8)
        Laa:
            java.util.Map r7 = r10.f12444c
            java.lang.Object r4 = r7.get(r4)
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r5 = r3.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            r4.add(r5)
            java.util.Map r4 = r10.f12444c
            java.lang.Object r4 = r4.get(r6)
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r2 = r3.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            r4.add(r2)
            goto L17
        Lce:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: w.a.e():void");
    }

    @Override // z.a
    public int a() {
        return this.f12447f;
    }

    @Override // z.a
    public String b(String str) {
        if (this.f12444c.containsKey(str)) {
            for (String str2 : (List) this.f12444c.get(str)) {
                for (p pVar : this.f12445d) {
                    if (str2.equals(h.a(pVar).c())) {
                        return str2;
                    }
                }
            }
            return null;
        }
        return null;
    }

    @Override // z.a
    public void c(int i8) {
        if (i8 != this.f12447f) {
            for (a.InterfaceC0170a interfaceC0170a : this.f12443b) {
                interfaceC0170a.a(this.f12447f, i8);
            }
        }
        if (this.f12447f == 2 && i8 != 2) {
            this.f12445d.clear();
        }
        this.f12447f = i8;
    }

    @Override // z.a
    public void d(a.InterfaceC0170a interfaceC0170a) {
        this.f12443b.add(interfaceC0170a);
    }
}
