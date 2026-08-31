package j3;

import i3.a;
/* loaded from: classes.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private final int f8211a;

    /* renamed from: b  reason: collision with root package name */
    private final i3.a f8212b;

    /* renamed from: c  reason: collision with root package name */
    private final a.d f8213c;

    /* renamed from: d  reason: collision with root package name */
    private final String f8214d;

    private b(i3.a aVar, a.d dVar, String str) {
        this.f8212b = aVar;
        this.f8213c = dVar;
        this.f8214d = str;
        this.f8211a = k3.p.b(aVar, dVar, str);
    }

    public static b a(i3.a aVar, a.d dVar, String str) {
        return new b(aVar, dVar, str);
    }

    public final String b() {
        return this.f8212b.b();
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            return k3.p.a(this.f8212b, bVar.f8212b) && k3.p.a(this.f8213c, bVar.f8213c) && k3.p.a(this.f8214d, bVar.f8214d);
        }
        return false;
    }

    public final int hashCode() {
        return this.f8211a;
    }
}
