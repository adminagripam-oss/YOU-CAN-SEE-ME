package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.c;
import i3.a;
/* loaded from: classes.dex */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    private final c f5007a;

    /* renamed from: b  reason: collision with root package name */
    private final h3.c[] f5008b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f5009c;

    /* renamed from: d  reason: collision with root package name */
    private final int f5010d;

    /* JADX INFO: Access modifiers changed from: protected */
    public e(c cVar, h3.c[] cVarArr, boolean z7, int i8) {
        this.f5007a = cVar;
        this.f5008b = cVarArr;
        this.f5009c = z7;
        this.f5010d = i8;
    }

    public void a() {
        this.f5007a.a();
    }

    public c.a b() {
        return this.f5007a.b();
    }

    public h3.c[] c() {
        return this.f5008b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void d(a.b bVar, f4.k kVar);

    public final int e() {
        return this.f5010d;
    }

    public final boolean f() {
        return this.f5009c;
    }
}
