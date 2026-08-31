package v4;

import java.security.GeneralSecurityException;
import java.security.Provider;
import o4.b;
/* loaded from: classes.dex */
public final class h implements u4.g {

    /* renamed from: b  reason: collision with root package name */
    private static final b.EnumC0135b f12371b = b.EnumC0135b.f9848e;

    /* renamed from: a  reason: collision with root package name */
    private final u4.a f12372a;

    public h(u4.a aVar) {
        this.f12372a = aVar;
    }

    public static u4.g a(u4.a aVar) {
        if (f12371b.e()) {
            Provider a8 = t4.b.a();
            if (a8 != null) {
                try {
                    return g.a(aVar, a8);
                } catch (GeneralSecurityException unused) {
                }
            }
            return new h(aVar);
        }
        throw new GeneralSecurityException("Cannot use AES-CMAC in FIPS-mode.");
    }
}
