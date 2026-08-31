package a5;

import j4.y;
import z4.p;
/* loaded from: classes.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private final a f1353a;

    private b(a aVar) {
        this.f1353a = aVar;
    }

    public static b a(byte[] bArr, y yVar) {
        if (yVar != null) {
            return new b(a.a(bArr));
        }
        throw new NullPointerException("SecretKeyAccess required");
    }

    public static b b(int i8) {
        return new b(a.a(p.a(i8)));
    }

    public int c() {
        return this.f1353a.c();
    }

    public byte[] d(y yVar) {
        if (yVar != null) {
            return this.f1353a.d();
        }
        throw new NullPointerException("SecretKeyAccess required");
    }
}
