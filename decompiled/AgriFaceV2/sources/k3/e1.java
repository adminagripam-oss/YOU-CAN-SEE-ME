package k3;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
/* loaded from: classes.dex */
public final class e1 {

    /* renamed from: f  reason: collision with root package name */
    private static final Uri f8381f = new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").build();

    /* renamed from: a  reason: collision with root package name */
    private final String f8382a;

    /* renamed from: b  reason: collision with root package name */
    private final String f8383b;

    /* renamed from: c  reason: collision with root package name */
    private final ComponentName f8384c;

    /* renamed from: d  reason: collision with root package name */
    private final int f8385d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f8386e;

    public e1(String str, String str2, int i8, boolean z7) {
        q.e(str);
        this.f8382a = str;
        q.e(str2);
        this.f8383b = str2;
        this.f8384c = null;
        this.f8385d = 4225;
        this.f8386e = z7;
    }

    public final ComponentName a() {
        return this.f8384c;
    }

    public final Intent b(Context context) {
        Bundle bundle;
        if (this.f8382a != null) {
            if (this.f8386e) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("serviceActionBundleKey", this.f8382a);
                try {
                    bundle = context.getContentResolver().call(f8381f, "serviceIntentCall", (String) null, bundle2);
                } catch (IllegalArgumentException e8) {
                    Log.w("ConnectionStatusConfig", "Dynamic intent resolution failed: ".concat(e8.toString()));
                    bundle = null;
                }
                r2 = bundle != null ? (Intent) bundle.getParcelable("serviceResponseIntentKey") : null;
                if (r2 == null) {
                    Log.w("ConnectionStatusConfig", "Dynamic lookup for intent failed for action: ".concat(String.valueOf(this.f8382a)));
                }
            }
            return r2 == null ? new Intent(this.f8382a).setPackage(this.f8383b) : r2;
        }
        return new Intent().setComponent(this.f8384c);
    }

    public final String c() {
        return this.f8383b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof e1) {
            e1 e1Var = (e1) obj;
            return p.a(this.f8382a, e1Var.f8382a) && p.a(this.f8383b, e1Var.f8383b) && p.a(this.f8384c, e1Var.f8384c) && this.f8386e == e1Var.f8386e;
        }
        return false;
    }

    public final int hashCode() {
        return p.b(this.f8382a, this.f8383b, this.f8384c, 4225, Boolean.valueOf(this.f8386e));
    }

    public final String toString() {
        String str = this.f8382a;
        if (str == null) {
            q.g(this.f8384c);
            return this.f8384c.flattenToString();
        }
        return str;
    }
}
