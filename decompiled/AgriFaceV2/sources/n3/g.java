package n3;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class g extends l3.a {
    public static final Parcelable.Creator<g> CREATOR = new k();

    /* renamed from: a  reason: collision with root package name */
    private final int f9760a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f9761b;

    public g(int i8) {
        this(i8, false);
    }

    public int a() {
        return this.f9760a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, a());
        l3.c.c(parcel, 2, this.f9761b);
        l3.c.b(parcel, a8);
    }

    public g(int i8, boolean z7) {
        this.f9760a = i8;
        this.f9761b = z7;
    }
}
