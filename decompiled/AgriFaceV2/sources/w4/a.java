package w4;

import java.security.GeneralSecurityException;
/* loaded from: classes.dex */
public final class a extends d {

    /* renamed from: a  reason: collision with root package name */
    private final b f12474a;

    /* renamed from: b  reason: collision with root package name */
    private final a5.b f12475b;

    private a(b bVar, a5.b bVar2) {
        this.f12474a = bVar;
        this.f12475b = bVar2;
    }

    public static a c(b bVar, a5.b bVar2) {
        if (bVar.c() == bVar2.c()) {
            return new a(bVar, bVar2);
        }
        throw new GeneralSecurityException("Key size mismatch");
    }

    @Override // j4.h
    public Integer a() {
        return null;
    }

    public a5.b d() {
        return this.f12475b;
    }

    @Override // j4.h
    /* renamed from: e */
    public b b() {
        return this.f12474a;
    }
}
