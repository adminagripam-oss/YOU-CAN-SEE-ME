package e4;

import android.os.Parcel;
import android.os.Parcelable;
import k3.l0;
/* loaded from: classes.dex */
public final class l extends l3.a {
    public static final Parcelable.Creator<l> CREATOR = new m();

    /* renamed from: a  reason: collision with root package name */
    final int f6610a;

    /* renamed from: b  reason: collision with root package name */
    private final h3.a f6611b;

    /* renamed from: c  reason: collision with root package name */
    private final l0 f6612c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(int i8, h3.a aVar, l0 l0Var) {
        this.f6610a = i8;
        this.f6611b = aVar;
        this.f6612c = l0Var;
    }

    public final h3.a a() {
        return this.f6611b;
    }

    public final l0 b() {
        return this.f6612c;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, this.f6610a);
        l3.c.j(parcel, 2, this.f6611b, i8, false);
        l3.c.j(parcel, 3, this.f6612c, i8, false);
        l3.c.b(parcel, a8);
    }
}
