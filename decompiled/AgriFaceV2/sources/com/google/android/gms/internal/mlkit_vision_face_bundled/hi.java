package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class hi extends l3.a {
    public static final Parcelable.Creator<hi> CREATOR = new ii();

    /* renamed from: a  reason: collision with root package name */
    private final int f5362a;

    /* renamed from: b  reason: collision with root package name */
    private final int f5363b;

    /* renamed from: c  reason: collision with root package name */
    private final int f5364c;

    /* renamed from: d  reason: collision with root package name */
    private final int f5365d;

    /* renamed from: e  reason: collision with root package name */
    private final long f5366e;

    public hi(int i8, int i9, int i10, int i11, long j8) {
        this.f5362a = i8;
        this.f5363b = i9;
        this.f5364c = i10;
        this.f5365d = i11;
        this.f5366e = j8;
    }

    public final int a() {
        return this.f5364c;
    }

    public final int b() {
        return this.f5362a;
    }

    public final int c() {
        return this.f5365d;
    }

    public final int d() {
        return this.f5363b;
    }

    public final long e() {
        return this.f5366e;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, this.f5362a);
        l3.c.g(parcel, 2, this.f5363b);
        l3.c.g(parcel, 3, this.f5364c);
        l3.c.g(parcel, 4, this.f5365d);
        l3.c.i(parcel, 5, this.f5366e);
        l3.c.b(parcel, a8);
    }
}
