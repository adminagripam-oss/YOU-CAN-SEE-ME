package k3;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import k3.k;
/* loaded from: classes.dex */
public class g extends l3.a {
    public static final Parcelable.Creator<g> CREATOR = new d1();

    /* renamed from: o  reason: collision with root package name */
    static final Scope[] f8394o = new Scope[0];

    /* renamed from: p  reason: collision with root package name */
    static final h3.c[] f8395p = new h3.c[0];

    /* renamed from: a  reason: collision with root package name */
    final int f8396a;

    /* renamed from: b  reason: collision with root package name */
    final int f8397b;

    /* renamed from: c  reason: collision with root package name */
    final int f8398c;

    /* renamed from: d  reason: collision with root package name */
    String f8399d;

    /* renamed from: e  reason: collision with root package name */
    IBinder f8400e;

    /* renamed from: f  reason: collision with root package name */
    Scope[] f8401f;

    /* renamed from: g  reason: collision with root package name */
    Bundle f8402g;

    /* renamed from: h  reason: collision with root package name */
    Account f8403h;

    /* renamed from: i  reason: collision with root package name */
    h3.c[] f8404i;

    /* renamed from: j  reason: collision with root package name */
    h3.c[] f8405j;

    /* renamed from: k  reason: collision with root package name */
    final boolean f8406k;

    /* renamed from: l  reason: collision with root package name */
    final int f8407l;

    /* renamed from: m  reason: collision with root package name */
    boolean f8408m;

    /* renamed from: n  reason: collision with root package name */
    private final String f8409n;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(int i8, int i9, int i10, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account, h3.c[] cVarArr, h3.c[] cVarArr2, boolean z7, int i11, boolean z8, String str2) {
        scopeArr = scopeArr == null ? f8394o : scopeArr;
        bundle = bundle == null ? new Bundle() : bundle;
        cVarArr = cVarArr == null ? f8395p : cVarArr;
        cVarArr2 = cVarArr2 == null ? f8395p : cVarArr2;
        this.f8396a = i8;
        this.f8397b = i9;
        this.f8398c = i10;
        if ("com.google.android.gms".equals(str)) {
            this.f8399d = "com.google.android.gms";
        } else {
            this.f8399d = str;
        }
        if (i8 < 2) {
            this.f8403h = iBinder != null ? a.f(k.a.e(iBinder)) : null;
        } else {
            this.f8400e = iBinder;
            this.f8403h = account;
        }
        this.f8401f = scopeArr;
        this.f8402g = bundle;
        this.f8404i = cVarArr;
        this.f8405j = cVarArr2;
        this.f8406k = z7;
        this.f8407l = i11;
        this.f8408m = z8;
        this.f8409n = str2;
    }

    public final String a() {
        return this.f8409n;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        d1.a(this, parcel, i8);
    }
}
