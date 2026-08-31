package v4;

import j4.o;
import j4.v;
import java.security.GeneralSecurityException;
import t4.c0;
import t4.y;
/* loaded from: classes.dex */
public abstract class q {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final v f12389a;

        /* renamed from: b  reason: collision with root package name */
        public final int f12390b;

        public b(v vVar, int i8) {
            this.f12389a = vVar;
            this.f12390b = i8;
        }
    }

    /* loaded from: classes.dex */
    private static class c implements v {

        /* renamed from: a  reason: collision with root package name */
        private final b f12391a;

        /* renamed from: b  reason: collision with root package name */
        private final y f12392b;

        /* renamed from: c  reason: collision with root package name */
        private final t4.l f12393c;

        /* renamed from: d  reason: collision with root package name */
        private final t4.l f12394d;

        private c(b bVar, y yVar, t4.l lVar, t4.l lVar2) {
            this.f12391a = bVar;
            this.f12392b = yVar;
            this.f12393c = lVar;
            this.f12394d = lVar2;
        }

        @Override // j4.v
        public void a(byte[] bArr, byte[] bArr2) {
            for (b bVar : this.f12392b.a(bArr)) {
                try {
                    bVar.f12389a.a(bArr, bArr2);
                    this.f12394d.b(bVar.f12390b, bArr2.length);
                    return;
                } catch (GeneralSecurityException unused) {
                }
            }
            this.f12394d.a();
            throw new GeneralSecurityException("invalid MAC");
        }

        @Override // j4.v
        public byte[] b(byte[] bArr) {
            try {
                byte[] b8 = this.f12391a.f12389a.b(bArr);
                this.f12393c.b(this.f12391a.f12390b, bArr.length);
                return b8;
            } catch (GeneralSecurityException e8) {
                this.f12393c.a();
                throw e8;
            }
        }
    }

    public static v a(j4.o oVar, c0.a aVar) {
        y.b bVar = new y.b();
        for (int i8 = 0; i8 < oVar.size(); i8++) {
            o.a b8 = oVar.b(i8);
            if (b8.b().equals(j4.j.f8247b)) {
                bVar.b(b(b8.getKey()), new b((v) aVar.a(b8), b8.a()));
            }
        }
        oVar.a(t4.k.class);
        t4.l lVar = t4.m.f11871a;
        return new c(new b((v) aVar.a(oVar.c()), oVar.c().a()), bVar.a(), lVar, lVar);
    }

    private static a5.a b(j4.h hVar) {
        if (hVar instanceof u4.p) {
            return ((u4.p) hVar).c();
        }
        if (hVar instanceof t4.i) {
            return ((t4.i) hVar).d();
        }
        throw new GeneralSecurityException("Cannot get output prefix for key of class " + hVar.getClass().getName() + " with parameters " + hVar.b());
    }
}
