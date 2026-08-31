package x3;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
/* loaded from: classes.dex */
public final class a1 extends l3.a implements i3.j {

    /* renamed from: a  reason: collision with root package name */
    private final Status f12653a;

    /* renamed from: b  reason: collision with root package name */
    public static final a1 f12652b = new a1(Status.f4960f);
    public static final Parcelable.Creator<a1> CREATOR = new b1();

    public a1(Status status) {
        this.f12653a = status;
    }

    public final Status a() {
        return this.f12653a;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.j(parcel, 1, this.f12653a, i8, false);
        l3.c.b(parcel, a8);
    }
}
