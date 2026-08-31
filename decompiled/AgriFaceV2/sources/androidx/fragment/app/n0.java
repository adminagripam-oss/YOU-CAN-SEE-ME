package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.lifecycle.i;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class n0 implements Parcelable {
    public static final Parcelable.Creator<n0> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    final String f3531a;

    /* renamed from: b  reason: collision with root package name */
    final String f3532b;

    /* renamed from: c  reason: collision with root package name */
    final boolean f3533c;

    /* renamed from: d  reason: collision with root package name */
    final int f3534d;

    /* renamed from: e  reason: collision with root package name */
    final int f3535e;

    /* renamed from: f  reason: collision with root package name */
    final String f3536f;

    /* renamed from: g  reason: collision with root package name */
    final boolean f3537g;

    /* renamed from: h  reason: collision with root package name */
    final boolean f3538h;

    /* renamed from: i  reason: collision with root package name */
    final boolean f3539i;

    /* renamed from: j  reason: collision with root package name */
    final boolean f3540j;

    /* renamed from: k  reason: collision with root package name */
    final int f3541k;

    /* renamed from: l  reason: collision with root package name */
    final String f3542l;

    /* renamed from: m  reason: collision with root package name */
    final int f3543m;

    /* renamed from: n  reason: collision with root package name */
    final boolean f3544n;

    /* loaded from: classes.dex */
    class a implements Parcelable.Creator {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public n0 createFromParcel(Parcel parcel) {
            return new n0(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public n0[] newArray(int i8) {
            return new n0[i8];
        }
    }

    n0(Parcel parcel) {
        this.f3531a = parcel.readString();
        this.f3532b = parcel.readString();
        this.f3533c = parcel.readInt() != 0;
        this.f3534d = parcel.readInt();
        this.f3535e = parcel.readInt();
        this.f3536f = parcel.readString();
        this.f3537g = parcel.readInt() != 0;
        this.f3538h = parcel.readInt() != 0;
        this.f3539i = parcel.readInt() != 0;
        this.f3540j = parcel.readInt() != 0;
        this.f3541k = parcel.readInt();
        this.f3542l = parcel.readString();
        this.f3543m = parcel.readInt();
        this.f3544n = parcel.readInt() != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p a(z zVar, ClassLoader classLoader) {
        p a8 = zVar.a(classLoader, this.f3531a);
        a8.f3563f = this.f3532b;
        a8.f3573p = this.f3533c;
        a8.f3575r = true;
        a8.f3582y = this.f3534d;
        a8.f3583z = this.f3535e;
        a8.A = this.f3536f;
        a8.D = this.f3537g;
        a8.f3570m = this.f3538h;
        a8.C = this.f3539i;
        a8.B = this.f3540j;
        a8.T = i.b.values()[this.f3541k];
        a8.f3566i = this.f3542l;
        a8.f3567j = this.f3543m;
        a8.L = this.f3544n;
        return a8;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f3531a);
        sb.append(" (");
        sb.append(this.f3532b);
        sb.append(")}:");
        if (this.f3533c) {
            sb.append(" fromLayout");
        }
        if (this.f3535e != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f3535e));
        }
        String str = this.f3536f;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.f3536f);
        }
        if (this.f3537g) {
            sb.append(" retainInstance");
        }
        if (this.f3538h) {
            sb.append(" removing");
        }
        if (this.f3539i) {
            sb.append(" detached");
        }
        if (this.f3540j) {
            sb.append(" hidden");
        }
        if (this.f3542l != null) {
            sb.append(" targetWho=");
            sb.append(this.f3542l);
            sb.append(" targetRequestCode=");
            sb.append(this.f3543m);
        }
        if (this.f3544n) {
            sb.append(" userVisibleHint");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeString(this.f3531a);
        parcel.writeString(this.f3532b);
        parcel.writeInt(this.f3533c ? 1 : 0);
        parcel.writeInt(this.f3534d);
        parcel.writeInt(this.f3535e);
        parcel.writeString(this.f3536f);
        parcel.writeInt(this.f3537g ? 1 : 0);
        parcel.writeInt(this.f3538h ? 1 : 0);
        parcel.writeInt(this.f3539i ? 1 : 0);
        parcel.writeInt(this.f3540j ? 1 : 0);
        parcel.writeInt(this.f3541k);
        parcel.writeString(this.f3542l);
        parcel.writeInt(this.f3543m);
        parcel.writeInt(this.f3544n ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public n0(p pVar) {
        this.f3531a = pVar.getClass().getName();
        this.f3532b = pVar.f3563f;
        this.f3533c = pVar.f3573p;
        this.f3534d = pVar.f3582y;
        this.f3535e = pVar.f3583z;
        this.f3536f = pVar.A;
        this.f3537g = pVar.D;
        this.f3538h = pVar.f3570m;
        this.f3539i = pVar.C;
        this.f3540j = pVar.B;
        this.f3541k = pVar.T.ordinal();
        this.f3542l = pVar.f3566i;
        this.f3543m = pVar.f3567j;
        this.f3544n = pVar.L;
    }
}
