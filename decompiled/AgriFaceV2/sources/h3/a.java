package h3;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import k3.p;
/* loaded from: classes.dex */
public final class a extends l3.a {

    /* renamed from: a  reason: collision with root package name */
    final int f7221a;

    /* renamed from: b  reason: collision with root package name */
    private final int f7222b;

    /* renamed from: c  reason: collision with root package name */
    private final PendingIntent f7223c;

    /* renamed from: d  reason: collision with root package name */
    private final String f7224d;

    /* renamed from: e  reason: collision with root package name */
    public static final a f7220e = new a(0);
    public static final Parcelable.Creator<a> CREATOR = new l();

    public a(int i8) {
        this(i8, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String f(int i8) {
        if (i8 != 99) {
            if (i8 != 1500) {
                switch (i8) {
                    case androidx.swiperefreshlayout.widget.c.DEFAULT_SLINGSHOT_DISTANCE /* -1 */:
                        return "UNKNOWN";
                    case 0:
                        return "SUCCESS";
                    case 1:
                        return "SERVICE_MISSING";
                    case 2:
                        return "SERVICE_VERSION_UPDATE_REQUIRED";
                    case 3:
                        return "SERVICE_DISABLED";
                    case 4:
                        return "SIGN_IN_REQUIRED";
                    case 5:
                        return "INVALID_ACCOUNT";
                    case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                        return "RESOLUTION_REQUIRED";
                    case 7:
                        return "NETWORK_ERROR";
                    case 8:
                        return "INTERNAL_ERROR";
                    case 9:
                        return "SERVICE_INVALID";
                    case 10:
                        return "DEVELOPER_ERROR";
                    case 11:
                        return "LICENSE_CHECK_FAILED";
                    default:
                        switch (i8) {
                            case 13:
                                return "CANCELED";
                            case 14:
                                return "TIMEOUT";
                            case 15:
                                return "INTERRUPTED";
                            case 16:
                                return "API_UNAVAILABLE";
                            case 17:
                                return "SIGN_IN_FAILED";
                            case 18:
                                return "SERVICE_UPDATING";
                            case 19:
                                return "SERVICE_MISSING_PERMISSION";
                            case 20:
                                return "RESTRICTED_PROFILE";
                            case 21:
                                return "API_VERSION_UPDATE_REQUIRED";
                            case 22:
                                return "RESOLUTION_ACTIVITY_NOT_FOUND";
                            case 23:
                                return "API_DISABLED";
                            case 24:
                                return "API_DISABLED_FOR_CONNECTION";
                            default:
                                return "UNKNOWN_ERROR_CODE(" + i8 + ")";
                        }
                }
            }
            return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
        }
        return "UNFINISHED";
    }

    public int a() {
        return this.f7222b;
    }

    public String b() {
        return this.f7224d;
    }

    public PendingIntent c() {
        return this.f7223c;
    }

    public boolean d() {
        return (this.f7222b == 0 || this.f7223c == null) ? false : true;
    }

    public boolean e() {
        return this.f7222b == 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof a) {
            a aVar = (a) obj;
            return this.f7222b == aVar.f7222b && k3.p.a(this.f7223c, aVar.f7223c) && k3.p.a(this.f7224d, aVar.f7224d);
        }
        return false;
    }

    public int hashCode() {
        return k3.p.b(Integer.valueOf(this.f7222b), this.f7223c, this.f7224d);
    }

    public String toString() {
        p.a c8 = k3.p.c(this);
        c8.a("statusCode", f(this.f7222b));
        c8.a("resolution", this.f7223c);
        c8.a("message", this.f7224d);
        return c8.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        int i9 = this.f7221a;
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, i9);
        l3.c.g(parcel, 2, a());
        l3.c.j(parcel, 3, c(), i8, false);
        l3.c.k(parcel, 4, b(), false);
        l3.c.b(parcel, a8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(int i8, int i9, PendingIntent pendingIntent, String str) {
        this.f7221a = i8;
        this.f7222b = i9;
        this.f7223c = pendingIntent;
        this.f7224d = str;
    }

    public a(int i8, PendingIntent pendingIntent) {
        this(i8, pendingIntent, null);
    }

    public a(int i8, PendingIntent pendingIntent, String str) {
        this(1, i8, pendingIntent, str);
    }
}
