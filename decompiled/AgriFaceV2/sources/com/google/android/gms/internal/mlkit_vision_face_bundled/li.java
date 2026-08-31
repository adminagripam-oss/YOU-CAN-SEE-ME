package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class li extends l3.a {
    public static final Parcelable.Creator<li> CREATOR = new mi();

    /* renamed from: a  reason: collision with root package name */
    private final int f5420a;

    /* renamed from: b  reason: collision with root package name */
    private final int f5421b;

    /* renamed from: c  reason: collision with root package name */
    private final int f5422c;

    /* renamed from: d  reason: collision with root package name */
    private final int f5423d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f5424e;

    /* renamed from: f  reason: collision with root package name */
    private final float f5425f;

    public li(int i8, int i9, int i10, int i11, boolean z7, float f8) {
        this.f5420a = i8;
        this.f5421b = i9;
        this.f5422c = i10;
        this.f5423d = i11;
        this.f5424e = z7;
        this.f5425f = f8;
    }

    public final float a() {
        return this.f5425f;
    }

    public final int b() {
        return this.f5422c;
    }

    public final int c() {
        return this.f5423d;
    }

    public final int d() {
        return this.f5421b;
    }

    public final int e() {
        return this.f5420a;
    }

    public final boolean f() {
        return this.f5424e;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, this.f5420a);
        l3.c.g(parcel, 2, this.f5421b);
        l3.c.g(parcel, 3, this.f5422c);
        l3.c.g(parcel, 4, this.f5423d);
        l3.c.c(parcel, 5, this.f5424e);
        l3.c.e(parcel, 6, this.f5425f);
        l3.c.b(parcel, a8);
    }
}
