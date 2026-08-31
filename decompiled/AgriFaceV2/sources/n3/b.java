package n3;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class b extends l3.a {
    public static final Parcelable.Creator<b> CREATOR = new h();

    /* renamed from: a  reason: collision with root package name */
    private final boolean f9753a;

    /* renamed from: b  reason: collision with root package name */
    private final int f9754b;

    public b(boolean z7, int i8) {
        this.f9753a = z7;
        this.f9754b = i8;
    }

    public boolean a() {
        return this.f9753a;
    }

    public int b() {
        return this.f9754b;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.c(parcel, 1, a());
        l3.c.g(parcel, 2, b());
        l3.c.b(parcel, a8);
    }
}
