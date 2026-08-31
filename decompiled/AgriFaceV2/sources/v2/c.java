package v2;

import android.content.Context;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c extends h {

    /* renamed from: a  reason: collision with root package name */
    private final Context f12330a;

    /* renamed from: b  reason: collision with root package name */
    private final d3.a f12331b;

    /* renamed from: c  reason: collision with root package name */
    private final d3.a f12332c;

    /* renamed from: d  reason: collision with root package name */
    private final String f12333d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Context context, d3.a aVar, d3.a aVar2, String str) {
        if (context == null) {
            throw new NullPointerException("Null applicationContext");
        }
        this.f12330a = context;
        if (aVar == null) {
            throw new NullPointerException("Null wallClock");
        }
        this.f12331b = aVar;
        if (aVar2 == null) {
            throw new NullPointerException("Null monotonicClock");
        }
        this.f12332c = aVar2;
        if (str == null) {
            throw new NullPointerException("Null backendName");
        }
        this.f12333d = str;
    }

    @Override // v2.h
    public Context b() {
        return this.f12330a;
    }

    @Override // v2.h
    public String c() {
        return this.f12333d;
    }

    @Override // v2.h
    public d3.a d() {
        return this.f12332c;
    }

    @Override // v2.h
    public d3.a e() {
        return this.f12331b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof h) {
            h hVar = (h) obj;
            return this.f12330a.equals(hVar.b()) && this.f12331b.equals(hVar.e()) && this.f12332c.equals(hVar.d()) && this.f12333d.equals(hVar.c());
        }
        return false;
    }

    public int hashCode() {
        return ((((((this.f12330a.hashCode() ^ 1000003) * 1000003) ^ this.f12331b.hashCode()) * 1000003) ^ this.f12332c.hashCode()) * 1000003) ^ this.f12333d.hashCode();
    }

    public String toString() {
        return "CreationContext{applicationContext=" + this.f12330a + ", wallClock=" + this.f12331b + ", monotonicClock=" + this.f12332c + ", backendName=" + this.f12333d + "}";
    }
}
