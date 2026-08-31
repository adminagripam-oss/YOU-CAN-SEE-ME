package r6;

import java.io.Serializable;
/* loaded from: classes.dex */
public abstract class k implements Serializable {

    /* renamed from: e  reason: collision with root package name */
    public static final a f11363e = new a(null);

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public static final class b implements Serializable {

        /* renamed from: e  reason: collision with root package name */
        public final Throwable f11364e;

        public b(Throwable th) {
            d7.k.e(th, "exception");
            this.f11364e = th;
        }

        public boolean equals(Object obj) {
            return (obj instanceof b) && d7.k.a(this.f11364e, ((b) obj).f11364e);
        }

        public int hashCode() {
            return this.f11364e.hashCode();
        }

        public String toString() {
            return "Failure(" + this.f11364e + ')';
        }
    }

    public static Object a(Object obj) {
        return obj;
    }

    public static final Throwable b(Object obj) {
        if (obj instanceof b) {
            return ((b) obj).f11364e;
        }
        return null;
    }

    public static final boolean c(Object obj) {
        return obj instanceof b;
    }

    public static final boolean d(Object obj) {
        return !(obj instanceof b);
    }
}
