package e4;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class b extends l3.a implements i3.j {
    public static final Parcelable.Creator<b> CREATOR = new c();

    /* renamed from: a  reason: collision with root package name */
    final int f6603a;

    /* renamed from: b  reason: collision with root package name */
    private int f6604b;

    /* renamed from: c  reason: collision with root package name */
    private Intent f6605c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(int i8, int i9, Intent intent) {
        this.f6603a = i8;
        this.f6604b = i9;
        this.f6605c = intent;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int i9 = this.f6603a;
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, i9);
        l3.c.g(parcel, 2, this.f6604b);
        l3.c.j(parcel, 3, this.f6605c, i8, false);
        l3.c.b(parcel, a8);
    }
}
