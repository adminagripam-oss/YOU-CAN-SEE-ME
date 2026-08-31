package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class vi extends l3.a {
    public static final Parcelable.Creator<vi> CREATOR = new wi();

    /* renamed from: a  reason: collision with root package name */
    private final int f5844a;

    /* renamed from: b  reason: collision with root package name */
    private final PointF f5845b;

    public vi(int i8, PointF pointF) {
        this.f5844a = i8;
        this.f5845b = pointF;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int i9 = this.f5844a;
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, i9);
        l3.c.j(parcel, 2, this.f5845b, i8, false);
        l3.c.b(parcel, a8);
    }
}
