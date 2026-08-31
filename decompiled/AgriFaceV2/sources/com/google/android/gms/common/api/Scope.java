package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import k3.q;
import l3.c;
/* loaded from: classes.dex */
public final class Scope extends l3.a implements ReflectedParcelable {
    public static final Parcelable.Creator<Scope> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    final int f4957a;

    /* renamed from: b  reason: collision with root package name */
    private final String f4958b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Scope(int i8, String str) {
        q.f(str, "scopeUri must not be null or empty");
        this.f4957a = i8;
        this.f4958b = str;
    }

    public String a() {
        return this.f4958b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Scope) {
            return this.f4958b.equals(((Scope) obj).f4958b);
        }
        return false;
    }

    public int hashCode() {
        return this.f4958b.hashCode();
    }

    public String toString() {
        return this.f4958b;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        int i9 = this.f4957a;
        int a8 = c.a(parcel);
        c.g(parcel, 1, i9);
        c.k(parcel, 2, a(), false);
        c.b(parcel, a8);
    }

    public Scope(String str) {
        this(1, str);
    }
}
