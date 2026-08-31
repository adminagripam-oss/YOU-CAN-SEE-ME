package c4;

import android.os.Parcel;
import android.os.Parcelable;
import x3.f0;
import x3.m0;
/* loaded from: classes.dex */
public final class d extends l3.a {
    public static final Parcelable.Creator<d> CREATOR = new m();

    /* renamed from: a  reason: collision with root package name */
    private final long f4782a;

    /* renamed from: b  reason: collision with root package name */
    private final int f4783b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f4784c;

    /* renamed from: d  reason: collision with root package name */
    private final f0 f4785d;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private long f4786a = Long.MAX_VALUE;

        /* renamed from: b  reason: collision with root package name */
        private int f4787b = 0;

        /* renamed from: c  reason: collision with root package name */
        private final boolean f4788c = false;

        /* renamed from: d  reason: collision with root package name */
        private final f0 f4789d = null;

        public d a() {
            return new d(this.f4786a, this.f4787b, this.f4788c, this.f4789d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(long j8, int i8, boolean z7, f0 f0Var) {
        this.f4782a = j8;
        this.f4783b = i8;
        this.f4784c = z7;
        this.f4785d = f0Var;
    }

    public int a() {
        return this.f4783b;
    }

    public long b() {
        return this.f4782a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof d) {
            d dVar = (d) obj;
            return this.f4782a == dVar.f4782a && this.f4783b == dVar.f4783b && this.f4784c == dVar.f4784c && k3.p.a(this.f4785d, dVar.f4785d);
        }
        return false;
    }

    public int hashCode() {
        return k3.p.b(Long.valueOf(this.f4782a), Integer.valueOf(this.f4783b), Boolean.valueOf(this.f4784c));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LastLocationRequest[");
        if (this.f4782a != Long.MAX_VALUE) {
            sb.append("maxAge=");
            m0.c(this.f4782a, sb);
        }
        if (this.f4783b != 0) {
            sb.append(", ");
            sb.append(w.b(this.f4783b));
        }
        if (this.f4784c) {
            sb.append(", bypass");
        }
        if (this.f4785d != null) {
            sb.append(", impersonation=");
            sb.append(this.f4785d);
        }
        sb.append(']');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.i(parcel, 1, b());
        l3.c.g(parcel, 2, a());
        l3.c.c(parcel, 3, this.f4784c);
        l3.c.j(parcel, 5, this.f4785d, i8, false);
        l3.c.b(parcel, a8);
    }
}
