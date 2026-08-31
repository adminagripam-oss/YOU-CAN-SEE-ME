package k3;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class t extends l3.a {
    public static final Parcelable.Creator<t> CREATOR = new y();

    /* renamed from: a  reason: collision with root package name */
    private final int f8478a;

    /* renamed from: b  reason: collision with root package name */
    private List f8479b;

    public t(int i8, List list) {
        this.f8478a = i8;
        this.f8479b = list;
    }

    public final int a() {
        return this.f8478a;
    }

    public final List b() {
        return this.f8479b;
    }

    public final void c(o oVar) {
        if (this.f8479b == null) {
            this.f8479b = new ArrayList();
        }
        this.f8479b.add(oVar);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, this.f8478a);
        l3.c.n(parcel, 2, this.f8479b, false);
        l3.c.b(parcel, a8);
    }
}
