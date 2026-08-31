package v4;

import java.security.GeneralSecurityException;
import o4.b;
/* loaded from: classes.dex */
public final class i implements u4.g {

    /* renamed from: b  reason: collision with root package name */
    private static final b.EnumC0135b f12373b = b.EnumC0135b.f9849f;

    /* renamed from: a  reason: collision with root package name */
    private final u4.i f12374a;

    public i(u4.i iVar) {
        if (!f12373b.e()) {
            throw new GeneralSecurityException("Can not use HMAC in FIPS-mode, as BoringCrypto module is not available.");
        }
        this.f12374a = iVar;
    }
}
