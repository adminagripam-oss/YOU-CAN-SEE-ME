package l4;

import j4.o;
import java.security.GeneralSecurityException;
import t4.c0;
import t4.y;
/* loaded from: classes.dex */
public abstract class m0 {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public final j4.a f9009a;

        /* renamed from: b  reason: collision with root package name */
        public final int f9010b;

        public b(j4.a aVar, int i8) {
            this.f9009a = aVar;
            this.f9010b = i8;
        }
    }

    /* loaded from: classes.dex */
    private static class c implements j4.a {

        /* renamed from: a  reason: collision with root package name */
        private final b f9011a;

        /* renamed from: b  reason: collision with root package name */
        private final t4.y f9012b;

        /* renamed from: c  reason: collision with root package name */
        private final t4.l f9013c;

        /* renamed from: d  reason: collision with root package name */
        private final t4.l f9014d;

        private c(b bVar, t4.y yVar, t4.l lVar, t4.l lVar2) {
            this.f9011a = bVar;
            this.f9012b = yVar;
            this.f9013c = lVar;
            this.f9014d = lVar2;
        }

        @Override // j4.a
        public byte[] a(byte[] bArr, byte[] bArr2) {
            try {
                byte[] a8 = this.f9011a.f9009a.a(bArr, bArr2);
                this.f9013c.b(this.f9011a.f9010b, bArr.length);
                return a8;
            } catch (GeneralSecurityException e8) {
                this.f9013c.a();
                throw e8;
            }
        }

        @Override // j4.a
        public byte[] b(byte[] bArr, byte[] bArr2) {
            for (b bVar : this.f9012b.a(bArr)) {
                try {
                    byte[] b8 = bVar.f9009a.b(bArr, bArr2);
                    this.f9014d.b(bVar.f9010b, bArr.length);
                    return b8;
                } catch (GeneralSecurityException unused) {
                }
            }
            this.f9014d.a();
            throw new GeneralSecurityException("decryption failed");
        }
    }

    public static j4.a a(j4.o oVar, c0.a aVar) {
        y.b bVar = new y.b();
        for (int i8 = 0; i8 < oVar.size(); i8++) {
            o.a b8 = oVar.b(i8);
            if (b8.b().equals(j4.j.f8247b)) {
                bVar.b(b(b8.getKey()), new b((j4.a) aVar.a(b8), b8.a()));
            }
        }
        oVar.a(t4.k.class);
        t4.l lVar = t4.m.f11871a;
        return new c(new b((j4.a) aVar.a(oVar.c()), oVar.c().a()), bVar.a(), lVar, lVar);
    }

    private static a5.a b(j4.h hVar) {
        if (hVar instanceof k4.b) {
            return ((k4.b) hVar).c();
        }
        if (hVar instanceof t4.i) {
            return ((t4.i) hVar).d();
        }
        throw new GeneralSecurityException("Cannot get output prefix for key of class " + hVar.getClass().getName() + " with parameters " + hVar.b());
    }
}
