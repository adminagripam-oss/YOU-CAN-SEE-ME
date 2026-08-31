package k3;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import k3.k;
/* loaded from: classes.dex */
public final class l0 extends l3.a {
    public static final Parcelable.Creator<l0> CREATOR = new m0();

    /* renamed from: a  reason: collision with root package name */
    final int f8440a;

    /* renamed from: b  reason: collision with root package name */
    final IBinder f8441b;

    /* renamed from: c  reason: collision with root package name */
    private final h3.a f8442c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f8443d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f8444e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l0(int i8, IBinder iBinder, h3.a aVar, boolean z7, boolean z8) {
        this.f8440a = i8;
        this.f8441b = iBinder;
        this.f8442c = aVar;
        this.f8443d = z7;
        this.f8444e = z8;
    }

    public final h3.a a() {
        return this.f8442c;
    }

    public final k b() {
        IBinder iBinder = this.f8441b;
        if (iBinder == null) {
            return null;
        }
        return k.a.e(iBinder);
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof l0) {
            l0 l0Var = (l0) obj;
            return this.f8442c.equals(l0Var.f8442c) && p.a(b(), l0Var.b());
        }
        return false;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, this.f8440a);
        l3.c.f(parcel, 2, this.f8441b, false);
        l3.c.j(parcel, 3, this.f8442c, i8, false);
        l3.c.c(parcel, 4, this.f8443d);
        l3.c.c(parcel, 5, this.f8444e);
        l3.c.b(parcel, a8);
    }
}
