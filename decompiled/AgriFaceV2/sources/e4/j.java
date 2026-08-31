package e4;

import android.os.Parcel;
import android.os.Parcelable;
import k3.j0;
/* loaded from: classes.dex */
public final class j extends l3.a {
    public static final Parcelable.Creator<j> CREATOR = new k();

    /* renamed from: a  reason: collision with root package name */
    final int f6608a;

    /* renamed from: b  reason: collision with root package name */
    final j0 f6609b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(int i8, j0 j0Var) {
        this.f6608a = i8;
        this.f6609b = j0Var;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, this.f6608a);
        l3.c.j(parcel, 2, this.f6609b, i8, false);
        l3.c.b(parcel, a8);
    }
}
