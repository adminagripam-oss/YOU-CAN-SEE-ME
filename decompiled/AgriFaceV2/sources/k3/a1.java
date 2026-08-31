package k3;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class a1 extends l3.a {
    public static final Parcelable.Creator<a1> CREATOR = new b1();

    /* renamed from: a  reason: collision with root package name */
    Bundle f8326a;

    /* renamed from: b  reason: collision with root package name */
    h3.c[] f8327b;

    /* renamed from: c  reason: collision with root package name */
    int f8328c;

    /* renamed from: d  reason: collision with root package name */
    f f8329d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a1(Bundle bundle, h3.c[] cVarArr, int i8, f fVar) {
        this.f8326a = bundle;
        this.f8327b = cVarArr;
        this.f8328c = i8;
        this.f8329d = fVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.d(parcel, 1, this.f8326a, false);
        l3.c.m(parcel, 2, this.f8327b, i8, false);
        l3.c.g(parcel, 3, this.f8328c);
        l3.c.j(parcel, 4, this.f8329d, i8, false);
        l3.c.b(parcel, a8);
    }
}
