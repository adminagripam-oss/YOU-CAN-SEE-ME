package k3;

import android.os.Bundle;
import i3.a;
/* loaded from: classes.dex */
public class w implements a.d {

    /* renamed from: c  reason: collision with root package name */
    public static final w f8484c = a().a();

    /* renamed from: b  reason: collision with root package name */
    private final String f8485b;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private String f8486a;

        /* synthetic */ a(z zVar) {
        }

        public w a() {
            return new w(this.f8486a, null);
        }

        public a b(String str) {
            this.f8486a = str;
            return this;
        }
    }

    /* synthetic */ w(String str, a0 a0Var) {
        this.f8485b = str;
    }

    public static a a() {
        return new a(null);
    }

    public final Bundle b() {
        Bundle bundle = new Bundle();
        String str = this.f8485b;
        if (str != null) {
            bundle.putString("api", str);
        }
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof w) {
            return p.a(this.f8485b, ((w) obj).f8485b);
        }
        return false;
    }

    public final int hashCode() {
        return p.b(this.f8485b);
    }
}
