package u2;

import android.util.Base64;
import u2.c;
/* loaded from: classes.dex */
public abstract class m {

    /* loaded from: classes.dex */
    public static abstract class a {
        public abstract m a();

        public abstract a b(String str);

        public abstract a c(byte[] bArr);

        public abstract a d(s2.d dVar);
    }

    public static a a() {
        return new c.b().d(s2.d.DEFAULT);
    }

    public abstract String b();

    public abstract byte[] c();

    public abstract s2.d d();

    public m e(s2.d dVar) {
        return a().b(b()).d(dVar).c(c()).a();
    }

    public final String toString() {
        Object[] objArr = new Object[3];
        objArr[0] = b();
        objArr[1] = d();
        objArr[2] = c() == null ? "" : Base64.encodeToString(c(), 2);
        return String.format("TransportContext(%s, %s, %s)", objArr);
    }
}
