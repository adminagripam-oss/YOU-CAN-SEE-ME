package z4;

import java.security.GeneralSecurityException;
/* loaded from: classes.dex */
public abstract class m implements w4.c {

    /* loaded from: classes.dex */
    private static class b implements w4.c {

        /* renamed from: a  reason: collision with root package name */
        final w4.c f14438a;

        /* renamed from: b  reason: collision with root package name */
        final w4.c f14439b;

        private b(w4.c cVar, w4.c cVar2) {
            this.f14438a = cVar;
            this.f14439b = cVar2;
        }

        @Override // w4.c
        public byte[] a(byte[] bArr, int i8) {
            return (bArr.length <= 64 ? this.f14438a : this.f14439b).a(bArr, i8);
        }
    }

    public static w4.c b(w4.a aVar) {
        w4.c c8 = x4.a.c(aVar);
        try {
            return new b(c8, x4.b.b(aVar));
        } catch (GeneralSecurityException unused) {
            return c8;
        }
    }
}
