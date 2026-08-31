package c4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
/* loaded from: classes.dex */
public final class i extends l3.a implements i3.j {
    public static final Parcelable.Creator<i> CREATOR = new o();

    /* renamed from: a  reason: collision with root package name */
    private final Status f4800a;

    /* renamed from: b  reason: collision with root package name */
    private final j f4801b;

    public i(Status status, j jVar) {
        this.f4800a = status;
        this.f4801b = jVar;
    }

    public j a() {
        return this.f4801b;
    }

    public Status b() {
        return this.f4800a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.j(parcel, 1, b(), i8, false);
        l3.c.j(parcel, 2, a(), i8, false);
        l3.c.b(parcel, a8);
    }
}
