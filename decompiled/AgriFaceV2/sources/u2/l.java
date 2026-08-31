package u2;

import u2.b;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class l {

    /* loaded from: classes.dex */
    public static abstract class a {
        public abstract l a();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract a b(s2.b bVar);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract a c(s2.c cVar);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract a d(s2.e eVar);

        public abstract a e(m mVar);

        public abstract a f(String str);
    }

    public static a a() {
        return new b.C0157b();
    }

    public abstract s2.b b();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract s2.c c();

    public byte[] d() {
        return (byte[]) e().apply(c().b());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract s2.e e();

    public abstract m f();

    public abstract String g();
}
