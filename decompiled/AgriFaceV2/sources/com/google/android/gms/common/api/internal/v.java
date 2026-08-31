package com.google.android.gms.common.api.internal;

import android.os.SystemClock;
import com.google.android.gms.common.api.Status;
import com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.ChromeCustomTabsActivity;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class v implements f4.e {

    /* renamed from: a  reason: collision with root package name */
    private final b f5077a;

    /* renamed from: b  reason: collision with root package name */
    private final int f5078b;

    /* renamed from: c  reason: collision with root package name */
    private final j3.b f5079c;

    /* renamed from: d  reason: collision with root package name */
    private final long f5080d;

    /* renamed from: e  reason: collision with root package name */
    private final long f5081e;

    v(b bVar, int i8, j3.b bVar2, long j8, long j9, String str, String str2) {
        this.f5077a = bVar;
        this.f5078b = i8;
        this.f5079c = bVar2;
        this.f5080d = j8;
        this.f5081e = j9;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static v b(b bVar, int i8, j3.b bVar2) {
        boolean z7;
        if (bVar.d()) {
            k3.s a8 = k3.r.b().a();
            if (a8 == null) {
                z7 = true;
            } else if (!a8.c()) {
                return null;
            } else {
                z7 = a8.d();
                q s8 = bVar.s(bVar2);
                if (s8 != null) {
                    if (!(s8.v() instanceof k3.c)) {
                        return null;
                    }
                    k3.c cVar = (k3.c) s8.v();
                    if (cVar.I() && !cVar.h()) {
                        k3.f c8 = c(s8, cVar, i8);
                        if (c8 == null) {
                            return null;
                        }
                        s8.G();
                        z7 = c8.e();
                    }
                }
            }
            return new v(bVar, i8, bVar2, z7 ? System.currentTimeMillis() : 0L, z7 ? SystemClock.elapsedRealtime() : 0L, null, null);
        }
        return null;
    }

    private static k3.f c(q qVar, k3.c cVar, int i8) {
        int[] b8;
        int[] c8;
        k3.f G = cVar.G();
        if (G == null || !G.d() || ((b8 = G.b()) != null ? !q3.a.a(b8, i8) : !((c8 = G.c()) == null || !q3.a.a(c8, i8))) || qVar.t() >= G.a()) {
            return null;
        }
        return G;
    }

    @Override // f4.e
    public final void a(f4.j jVar) {
        q s8;
        int i8;
        int i9;
        int i10;
        int a8;
        long j8;
        long j9;
        int i11;
        if (this.f5077a.d()) {
            k3.s a9 = k3.r.b().a();
            if ((a9 == null || a9.c()) && (s8 = this.f5077a.s(this.f5079c)) != null && (s8.v() instanceof k3.c)) {
                k3.c cVar = (k3.c) s8.v();
                boolean z7 = true;
                int i12 = 0;
                boolean z8 = this.f5080d > 0;
                int y7 = cVar.y();
                int i13 = 100;
                if (a9 != null) {
                    z8 &= a9.d();
                    int a10 = a9.a();
                    int b8 = a9.b();
                    i8 = a9.e();
                    if (cVar.I() && !cVar.h()) {
                        k3.f c8 = c(s8, cVar, this.f5078b);
                        if (c8 == null) {
                            return;
                        }
                        if (!c8.e() || this.f5080d <= 0) {
                            z7 = false;
                        }
                        b8 = c8.a();
                        z8 = z7;
                    }
                    i10 = a10;
                    i9 = b8;
                } else {
                    i8 = 0;
                    i9 = 100;
                    i10 = 5000;
                }
                b bVar = this.f5077a;
                if (jVar.j()) {
                    a8 = 0;
                } else {
                    if (!jVar.h()) {
                        Exception f8 = jVar.f();
                        if (f8 instanceof i3.b) {
                            Status a11 = ((i3.b) f8).a();
                            i13 = a11.b();
                            h3.a a12 = a11.a();
                            if (a12 != null) {
                                a8 = a12.a();
                                i12 = i13;
                            }
                        } else {
                            i12 = ChromeCustomTabsActivity.NO_HISTORY_CHROME_CUSTOM_TAB_REQUEST_CODE;
                            a8 = -1;
                        }
                    }
                    i12 = i13;
                    a8 = -1;
                }
                if (z8) {
                    long j10 = this.f5080d;
                    long j11 = this.f5081e;
                    long currentTimeMillis = System.currentTimeMillis();
                    i11 = (int) (SystemClock.elapsedRealtime() - j11);
                    j9 = currentTimeMillis;
                    j8 = j10;
                } else {
                    j8 = 0;
                    j9 = 0;
                    i11 = -1;
                }
                bVar.C(new k3.o(this.f5078b, i12, a8, j8, j9, null, null, y7, i11), i8, i10, i9);
            }
        }
    }
}
