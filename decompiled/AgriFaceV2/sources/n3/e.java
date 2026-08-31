package n3;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class e extends l3.a {
    public static final Parcelable.Creator<e> CREATOR = new i();

    /* renamed from: a  reason: collision with root package name */
    private final PendingIntent f9755a;

    public e(PendingIntent pendingIntent) {
        this.f9755a = pendingIntent;
    }

    public PendingIntent a() {
        return this.f9755a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.j(parcel, 1, a(), i8, false);
        l3.c.b(parcel, a8);
    }
}
