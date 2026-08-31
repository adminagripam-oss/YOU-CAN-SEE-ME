package e4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes.dex */
public final class h extends l3.a implements i3.j {
    public static final Parcelable.Creator<h> CREATOR = new i();

    /* renamed from: a  reason: collision with root package name */
    private final List f6606a;

    /* renamed from: b  reason: collision with root package name */
    private final String f6607b;

    public h(List list, String str) {
        this.f6606a = list;
        this.f6607b = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        List list = this.f6606a;
        int a8 = l3.c.a(parcel);
        l3.c.l(parcel, 1, list, false);
        l3.c.k(parcel, 2, this.f6607b, false);
        l3.c.b(parcel, a8);
    }
}
