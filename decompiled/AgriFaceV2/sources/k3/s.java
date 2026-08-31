package k3;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class s extends l3.a {
    public static final Parcelable.Creator<s> CREATOR = new t0();

    /* renamed from: a  reason: collision with root package name */
    private final int f8473a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f8474b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f8475c;

    /* renamed from: d  reason: collision with root package name */
    private final int f8476d;

    /* renamed from: e  reason: collision with root package name */
    private final int f8477e;

    public s(int i8, boolean z7, boolean z8, int i9, int i10) {
        this.f8473a = i8;
        this.f8474b = z7;
        this.f8475c = z8;
        this.f8476d = i9;
        this.f8477e = i10;
    }

    public int a() {
        return this.f8476d;
    }

    public int b() {
        return this.f8477e;
    }

    public boolean c() {
        return this.f8474b;
    }

    public boolean d() {
        return this.f8475c;
    }

    public int e() {
        return this.f8473a;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, e());
        l3.c.c(parcel, 2, c());
        l3.c.c(parcel, 3, d());
        l3.c.g(parcel, 4, a());
        l3.c.g(parcel, 5, b());
        l3.c.b(parcel, a8);
    }
}
