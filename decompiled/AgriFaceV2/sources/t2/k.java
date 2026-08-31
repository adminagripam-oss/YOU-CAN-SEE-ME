package t2;

import t2.e;
/* loaded from: classes.dex */
public abstract class k {

    /* loaded from: classes.dex */
    public static abstract class a {
        public abstract k a();

        public abstract a b(t2.a aVar);

        public abstract a c(b bVar);
    }

    /* loaded from: classes.dex */
    public enum b {
        UNKNOWN(0),
        ANDROID_FIREBASE(23);
        

        /* renamed from: e  reason: collision with root package name */
        private final int f11757e;

        b(int i8) {
            this.f11757e = i8;
        }
    }

    public static a a() {
        return new e.b();
    }

    public abstract t2.a b();

    public abstract b c();
}
