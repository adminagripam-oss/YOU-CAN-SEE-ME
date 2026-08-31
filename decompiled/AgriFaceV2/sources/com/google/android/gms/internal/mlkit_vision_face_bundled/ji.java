package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes.dex */
public final class ji extends l3.a {
    public static final Parcelable.Creator<ji> CREATOR = new ki();

    /* renamed from: a  reason: collision with root package name */
    private final int f5388a;

    /* renamed from: b  reason: collision with root package name */
    private final List f5389b;

    public ji(int i8, List list) {
        this.f5388a = i8;
        this.f5389b = list;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int i9 = this.f5388a;
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, i9);
        l3.c.n(parcel, 2, this.f5389b, false);
        l3.c.b(parcel, a8);
    }
}
