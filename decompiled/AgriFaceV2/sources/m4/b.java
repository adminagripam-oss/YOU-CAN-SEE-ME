package m4;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import k4.v;
import l4.q;
import z4.i;
/* loaded from: classes.dex */
public abstract class b implements j4.a {

    /* renamed from: a  reason: collision with root package name */
    private static final ThreadLocal f9136a = new a();

    /* loaded from: classes.dex */
    class a extends ThreadLocal {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public Cipher initialValue() {
            try {
                Cipher cipher = (Cipher) i.f14427b.a("AES/GCM-SIV/NoPadding");
                if (q.f(cipher)) {
                    return cipher;
                }
                return null;
            } catch (GeneralSecurityException e8) {
                throw new IllegalStateException(e8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Cipher d() {
        try {
            Cipher cipher = (Cipher) f9136a.get();
            if (cipher != null) {
                return cipher;
            }
            throw new GeneralSecurityException("AES GCM SIV cipher is invalid.");
        } catch (IllegalStateException e8) {
            throw new GeneralSecurityException("AES GCM SIV cipher is not available or is invalid.", e8);
        }
    }

    public static j4.a e(v vVar) {
        return q.c(vVar, new q.a() { // from class: m4.a
            @Override // l4.q.a
            public final Object get() {
                Cipher d8;
                d8 = b.d();
                return d8;
            }
        });
    }
}
