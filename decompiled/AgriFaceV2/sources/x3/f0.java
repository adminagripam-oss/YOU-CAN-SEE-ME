package x3;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public final class f0 extends l3.a {

    /* renamed from: a  reason: collision with root package name */
    private final int f12658a;

    /* renamed from: b  reason: collision with root package name */
    private final String f12659b;

    /* renamed from: c  reason: collision with root package name */
    private final String f12660c;

    /* renamed from: d  reason: collision with root package name */
    private final String f12661d;

    /* renamed from: e  reason: collision with root package name */
    private final List f12662e;

    /* renamed from: f  reason: collision with root package name */
    private final f0 f12663f;

    /* renamed from: g  reason: collision with root package name */
    public static final r f12657g = new r(null);
    public static final Parcelable.Creator<f0> CREATOR = new y0();

    static {
        Process.myUid();
        Process.myPid();
    }

    public f0(int i8, String str, String str2, String str3, List list, f0 f0Var) {
        d7.k.e(str, "packageName");
        if (f0Var != null && f0Var.a()) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        this.f12658a = i8;
        this.f12659b = str;
        this.f12660c = str2;
        this.f12661d = str3 == null ? f0Var != null ? f0Var.f12661d : null : str3;
        if (list == null) {
            list = f0Var != null ? f0Var.f12662e : null;
            if (list == null) {
                list = v0.o();
                d7.k.d(list, "of(...)");
            }
        }
        d7.k.e(list, "<this>");
        v0 p8 = v0.p(list);
        d7.k.d(p8, "copyOf(...)");
        this.f12662e = p8;
        this.f12663f = f0Var;
    }

    public final boolean a() {
        return this.f12663f != null;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof f0) {
            f0 f0Var = (f0) obj;
            if (this.f12658a == f0Var.f12658a && d7.k.a(this.f12659b, f0Var.f12659b) && d7.k.a(this.f12660c, f0Var.f12660c) && d7.k.a(this.f12661d, f0Var.f12661d) && d7.k.a(this.f12663f, f0Var.f12663f) && d7.k.a(this.f12662e, f0Var.f12662e)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f12658a), this.f12659b, this.f12660c, this.f12661d, this.f12663f});
    }

    public final String toString() {
        boolean i8;
        int length = this.f12659b.length() + 18;
        String str = this.f12660c;
        StringBuilder sb = new StringBuilder(length + (str != null ? str.length() : 0));
        sb.append(this.f12658a);
        sb.append("/");
        sb.append(this.f12659b);
        String str2 = this.f12660c;
        if (str2 != null) {
            sb.append("[");
            i8 = j7.l.i(str2, this.f12659b, false, 2, null);
            if (i8) {
                sb.append((CharSequence) str2, this.f12659b.length(), str2.length());
            } else {
                sb.append(str2);
            }
            sb.append("]");
        }
        if (this.f12661d != null) {
            sb.append("/");
            String str3 = this.f12661d;
            sb.append(Integer.toHexString(str3 != null ? str3.hashCode() : 0));
        }
        String sb2 = sb.toString();
        d7.k.d(sb2, "toString(...)");
        return sb2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i8) {
        d7.k.e(parcel, "dest");
        int i9 = this.f12658a;
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, i9);
        l3.c.k(parcel, 3, this.f12659b, false);
        l3.c.k(parcel, 4, this.f12660c, false);
        l3.c.k(parcel, 6, this.f12661d, false);
        l3.c.j(parcel, 7, this.f12663f, i8, false);
        l3.c.n(parcel, 8, this.f12662e, false);
        l3.c.b(parcel, a8);
    }
}
