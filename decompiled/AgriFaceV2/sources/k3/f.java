package k3;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class f extends l3.a {
    public static final Parcelable.Creator<f> CREATOR = new c1();

    /* renamed from: a  reason: collision with root package name */
    private final s f8387a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f8388b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f8389c;

    /* renamed from: d  reason: collision with root package name */
    private final int[] f8390d;

    /* renamed from: e  reason: collision with root package name */
    private final int f8391e;

    /* renamed from: f  reason: collision with root package name */
    private final int[] f8392f;

    public f(s sVar, boolean z7, boolean z8, int[] iArr, int i8, int[] iArr2) {
        this.f8387a = sVar;
        this.f8388b = z7;
        this.f8389c = z8;
        this.f8390d = iArr;
        this.f8391e = i8;
        this.f8392f = iArr2;
    }

    public int a() {
        return this.f8391e;
    }

    public int[] b() {
        return this.f8390d;
    }

    public int[] c() {
        return this.f8392f;
    }

    public boolean d() {
        return this.f8388b;
    }

    public boolean e() {
        return this.f8389c;
    }

    public final s f() {
        return this.f8387a;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.j(parcel, 1, this.f8387a, i8, false);
        l3.c.c(parcel, 2, d());
        l3.c.c(parcel, 3, e());
        l3.c.h(parcel, 4, b(), false);
        l3.c.g(parcel, 5, a());
        l3.c.h(parcel, 6, c(), false);
        l3.c.b(parcel, a8);
    }
}
