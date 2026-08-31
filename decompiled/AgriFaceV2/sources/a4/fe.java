package a4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes.dex */
public final class fe extends l3.a {
    public static final Parcelable.Creator<fe> CREATOR = new ge();

    /* renamed from: a  reason: collision with root package name */
    private final int f573a;

    /* renamed from: b  reason: collision with root package name */
    private final List f574b;

    public fe(int i8, List list) {
        this.f573a = i8;
        this.f574b = list;
    }

    public final int a() {
        return this.f573a;
    }

    public final List b() {
        return this.f574b;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, this.f573a);
        l3.c.n(parcel, 2, this.f574b, false);
        l3.c.b(parcel, a8);
    }
}
