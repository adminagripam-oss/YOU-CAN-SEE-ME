package h3;

import android.os.Parcel;
import android.os.Parcelable;
import k3.p;
/* loaded from: classes.dex */
public class c extends l3.a {
    public static final Parcelable.Creator<c> CREATOR = new m();

    /* renamed from: a  reason: collision with root package name */
    private final String f7228a;

    /* renamed from: b  reason: collision with root package name */
    private final int f7229b;

    /* renamed from: c  reason: collision with root package name */
    private final long f7230c;

    public c(String str, int i8, long j8) {
        this.f7228a = str;
        this.f7229b = i8;
        this.f7230c = j8;
    }

    public String a() {
        return this.f7228a;
    }

    public long b() {
        long j8 = this.f7230c;
        return j8 == -1 ? this.f7229b : j8;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof c) {
            c cVar = (c) obj;
            if (((a() != null && a().equals(cVar.a())) || (a() == null && cVar.a() == null)) && b() == cVar.b()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return k3.p.b(a(), Long.valueOf(b()));
    }

    public final String toString() {
        p.a c8 = k3.p.c(this);
        c8.a("name", a());
        c8.a("version", Long.valueOf(b()));
        return c8.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.k(parcel, 1, a(), false);
        l3.c.g(parcel, 2, this.f7229b);
        l3.c.i(parcel, 3, b());
        l3.c.b(parcel, a8);
    }

    public c(String str, long j8) {
        this.f7228a = str;
        this.f7230c = j8;
        this.f7229b = -1;
    }
}
