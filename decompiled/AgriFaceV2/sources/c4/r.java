package c4;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class r extends l3.a {
    public static final Parcelable.Creator<r> CREATOR = new s();

    /* renamed from: a  reason: collision with root package name */
    public final int f4812a;

    /* renamed from: b  reason: collision with root package name */
    public final int f4813b;

    /* renamed from: c  reason: collision with root package name */
    public final long f4814c;

    /* renamed from: d  reason: collision with root package name */
    public final long f4815d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(int i8, int i9, long j8, long j9) {
        this.f4812a = i8;
        this.f4813b = i9;
        this.f4814c = j8;
        this.f4815d = j9;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof r) {
            r rVar = (r) obj;
            if (this.f4812a == rVar.f4812a && this.f4813b == rVar.f4813b && this.f4814c == rVar.f4814c && this.f4815d == rVar.f4815d) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return k3.p.b(Integer.valueOf(this.f4813b), Integer.valueOf(this.f4812a), Long.valueOf(this.f4815d), Long.valueOf(this.f4814c));
    }

    public final String toString() {
        int i8 = this.f4812a;
        int length = String.valueOf(i8).length();
        int i9 = this.f4813b;
        int length2 = String.valueOf(i9).length();
        long j8 = this.f4815d;
        int length3 = String.valueOf(j8).length();
        long j9 = this.f4814c;
        StringBuilder sb = new StringBuilder(length + 50 + length2 + 18 + length3 + 17 + String.valueOf(j9).length());
        sb.append("NetworkLocationStatus: Wifi status: ");
        sb.append(i8);
        sb.append(" Cell status: ");
        sb.append(i9);
        sb.append(" elapsed time NS: ");
        sb.append(j8);
        sb.append(" system time ms: ");
        sb.append(j9);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        int i9 = this.f4812a;
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, i9);
        l3.c.g(parcel, 2, this.f4813b);
        l3.c.i(parcel, 3, this.f4814c);
        l3.c.i(parcel, 4, this.f4815d);
        l3.c.b(parcel, a8);
    }
}
