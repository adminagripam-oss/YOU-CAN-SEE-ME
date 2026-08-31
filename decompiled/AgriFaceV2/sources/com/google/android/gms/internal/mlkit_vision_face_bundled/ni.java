package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
/* loaded from: classes.dex */
public final class ni extends l3.a {
    public static final Parcelable.Creator<ni> CREATOR = new oi();

    /* renamed from: a  reason: collision with root package name */
    private final int f5479a;

    /* renamed from: b  reason: collision with root package name */
    private final Rect f5480b;

    /* renamed from: c  reason: collision with root package name */
    private final float f5481c;

    /* renamed from: d  reason: collision with root package name */
    private final float f5482d;

    /* renamed from: e  reason: collision with root package name */
    private final float f5483e;

    /* renamed from: f  reason: collision with root package name */
    private final float f5484f;

    /* renamed from: g  reason: collision with root package name */
    private final float f5485g;

    /* renamed from: h  reason: collision with root package name */
    private final float f5486h;

    /* renamed from: i  reason: collision with root package name */
    private final float f5487i;

    /* renamed from: j  reason: collision with root package name */
    private final List f5488j;

    /* renamed from: k  reason: collision with root package name */
    private final List f5489k;

    public ni(int i8, Rect rect, float f8, float f9, float f10, float f11, float f12, float f13, float f14, List list, List list2) {
        this.f5479a = i8;
        this.f5480b = rect;
        this.f5481c = f8;
        this.f5482d = f9;
        this.f5483e = f10;
        this.f5484f = f11;
        this.f5485g = f12;
        this.f5486h = f13;
        this.f5487i = f14;
        this.f5488j = list;
        this.f5489k = list2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int i9 = this.f5479a;
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, i9);
        l3.c.j(parcel, 2, this.f5480b, i8, false);
        l3.c.e(parcel, 3, this.f5481c);
        l3.c.e(parcel, 4, this.f5482d);
        l3.c.e(parcel, 5, this.f5483e);
        l3.c.e(parcel, 6, this.f5484f);
        l3.c.e(parcel, 7, this.f5485g);
        l3.c.e(parcel, 8, this.f5486h);
        l3.c.e(parcel, 9, this.f5487i);
        l3.c.n(parcel, 10, this.f5488j, false);
        l3.c.n(parcel, 11, this.f5489k, false);
        l3.c.b(parcel, a8);
    }
}
