package x3;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class g0 extends l3.a {
    public static final Parcelable.Creator<g0> CREATOR = new h0();

    /* renamed from: a  reason: collision with root package name */
    private final int f12664a;

    /* renamed from: b  reason: collision with root package name */
    private final IBinder f12665b;

    /* renamed from: c  reason: collision with root package name */
    private final IBinder f12666c;

    /* renamed from: d  reason: collision with root package name */
    private final PendingIntent f12667d;

    /* renamed from: e  reason: collision with root package name */
    private final String f12668e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g0(int i8, IBinder iBinder, IBinder iBinder2, PendingIntent pendingIntent, String str) {
        this.f12664a = i8;
        this.f12665b = iBinder;
        this.f12666c = iBinder2;
        this.f12667d = pendingIntent;
        this.f12668e = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.os.IBinder] */
    /* JADX WARN: Type inference failed for: r8v0, types: [c4.z, android.os.IBinder] */
    public static g0 a(IInterface iInterface, c4.z zVar, String str) {
        if (iInterface == null) {
            iInterface = null;
        }
        return new g0(2, iInterface, zVar, null, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static g0 b(i1 i1Var) {
        return new g0(4, null, i1Var, null, null);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int i9 = this.f12664a;
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, i9);
        l3.c.f(parcel, 2, this.f12665b, false);
        l3.c.f(parcel, 3, this.f12666c, false);
        l3.c.j(parcel, 4, this.f12667d, i8, false);
        l3.c.k(parcel, 6, this.f12668e, false);
        l3.c.b(parcel, a8);
    }
}
