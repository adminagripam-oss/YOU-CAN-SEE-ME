package q4;

import j4.h;
import j4.j;
import j4.o;
import java.security.GeneralSecurityException;
import t4.c0;
import t4.i;
import t4.k;
import t4.l;
import t4.m;
import t4.y;
/* loaded from: classes.dex */
public abstract class g {

    /* loaded from: classes.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        public final j4.f f10347a;

        /* renamed from: b  reason: collision with root package name */
        public final int f10348b;

        public a(j4.f fVar, int i8) {
            this.f10347a = fVar;
            this.f10348b = i8;
        }
    }

    /* loaded from: classes.dex */
    private static class b implements j4.f {

        /* renamed from: a  reason: collision with root package name */
        private final a f10349a;

        /* renamed from: b  reason: collision with root package name */
        private final y f10350b;

        /* renamed from: c  reason: collision with root package name */
        private final l f10351c;

        /* renamed from: d  reason: collision with root package name */
        private final l f10352d;

        b(a aVar, y yVar, l lVar, l lVar2) {
            this.f10349a = aVar;
            this.f10350b = yVar;
            this.f10351c = lVar;
            this.f10352d = lVar2;
        }

        @Override // j4.f
        public byte[] a(byte[] bArr, byte[] bArr2) {
            try {
                byte[] a8 = this.f10349a.f10347a.a(bArr, bArr2);
                this.f10351c.b(this.f10349a.f10348b, bArr.length);
                return a8;
            } catch (GeneralSecurityException e8) {
                this.f10351c.a();
                throw e8;
            }
        }

        @Override // j4.f
        public byte[] b(byte[] bArr, byte[] bArr2) {
            for (a aVar : this.f10350b.a(bArr)) {
                try {
                    byte[] b8 = aVar.f10347a.b(bArr, bArr2);
                    this.f10352d.b(aVar.f10348b, bArr.length);
                    return b8;
                } catch (GeneralSecurityException unused) {
                }
            }
            this.f10352d.a();
            throw new GeneralSecurityException("decryption failed");
        }
    }

    public static j4.f a(o oVar, c0.a aVar) {
        y.b bVar = new y.b();
        for (int i8 = 0; i8 < oVar.size(); i8++) {
            o.a b8 = oVar.b(i8);
            if (b8.b().equals(j.f8247b)) {
                bVar.b(b(b8.getKey()), new a((j4.f) aVar.a(b8), b8.a()));
            }
        }
        oVar.a(k.class);
        l lVar = m.f11871a;
        return new b(new a((j4.f) aVar.a(oVar.c()), oVar.c().a()), bVar.a(), lVar, lVar);
    }

    private static a5.a b(h hVar) {
        if (hVar instanceof p4.g) {
            return ((p4.g) hVar).c();
        }
        if (hVar instanceof i) {
            return ((i) hVar).d();
        }
        throw new GeneralSecurityException("Cannot get output prefix for key of class " + hVar.getClass().getName() + " with parameters " + hVar.b());
    }
}
