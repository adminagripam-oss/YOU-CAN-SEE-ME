package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
public final class cc {

    /* renamed from: a  reason: collision with root package name */
    private final zb f5177a;

    /* renamed from: b  reason: collision with root package name */
    private final xb f5178b;

    /* renamed from: c  reason: collision with root package name */
    private final ac f5179c;

    /* renamed from: d  reason: collision with root package name */
    private final yb f5180d;

    /* renamed from: e  reason: collision with root package name */
    private final Boolean f5181e;

    /* renamed from: f  reason: collision with root package name */
    private final Float f5182f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ cc(wb wbVar, bc bcVar) {
        zb zbVar;
        xb xbVar;
        ac acVar;
        yb ybVar;
        Boolean bool;
        Float f8;
        zbVar = wbVar.f5881a;
        this.f5177a = zbVar;
        xbVar = wbVar.f5882b;
        this.f5178b = xbVar;
        acVar = wbVar.f5883c;
        this.f5179c = acVar;
        ybVar = wbVar.f5884d;
        this.f5180d = ybVar;
        bool = wbVar.f5885e;
        this.f5181e = bool;
        f8 = wbVar.f5886f;
        this.f5182f = f8;
    }

    public final xb a() {
        return this.f5178b;
    }

    public final yb b() {
        return this.f5180d;
    }

    public final zb c() {
        return this.f5177a;
    }

    public final ac d() {
        return this.f5179c;
    }

    public final Boolean e() {
        return this.f5181e;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof cc) {
            cc ccVar = (cc) obj;
            return k3.p.a(this.f5177a, ccVar.f5177a) && k3.p.a(this.f5178b, ccVar.f5178b) && k3.p.a(this.f5179c, ccVar.f5179c) && k3.p.a(this.f5180d, ccVar.f5180d) && k3.p.a(this.f5181e, ccVar.f5181e) && k3.p.a(this.f5182f, ccVar.f5182f);
        }
        return false;
    }

    public final Float f() {
        return this.f5182f;
    }

    public final int hashCode() {
        return k3.p.b(this.f5177a, this.f5178b, this.f5179c, this.f5180d, this.f5181e, this.f5182f);
    }
}
