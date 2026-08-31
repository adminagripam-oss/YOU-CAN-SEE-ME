package c4;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class j extends l3.a {
    public static final Parcelable.Creator<j> CREATOR = new p();

    /* renamed from: a  reason: collision with root package name */
    private final boolean f4802a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f4803b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f4804c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f4805d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f4806e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f4807f;

    public j(boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12) {
        this.f4802a = z7;
        this.f4803b = z8;
        this.f4804c = z9;
        this.f4805d = z10;
        this.f4806e = z11;
        this.f4807f = z12;
    }

    public boolean a() {
        return this.f4807f;
    }

    public boolean b() {
        return this.f4804c;
    }

    public boolean c() {
        return this.f4805d;
    }

    public boolean d() {
        return this.f4802a;
    }

    public boolean e() {
        return this.f4806e;
    }

    public boolean f() {
        return this.f4803b;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.c(parcel, 1, d());
        l3.c.c(parcel, 2, f());
        l3.c.c(parcel, 3, b());
        l3.c.c(parcel, 4, c());
        l3.c.c(parcel, 5, e());
        l3.c.c(parcel, 6, a());
        l3.c.b(parcel, a8);
    }
}
