package v5;

import android.content.Context;
import javax.crypto.Cipher;
/* loaded from: classes.dex */
public enum o {
    AES_CBC_PKCS7Padding(new q() { // from class: v5.n
        @Override // v5.q
        public final m a(Context context, a aVar, Cipher cipher, u5.s sVar) {
            return new r(context, aVar, cipher, sVar);
        }
    }, 1),
    AES_GCM_NoPadding(null, 23);
    

    /* renamed from: e  reason: collision with root package name */
    final q f12410e;

    /* renamed from: f  reason: collision with root package name */
    final int f12411f;

    o(q qVar, int i8) {
        this.f12410e = qVar;
        this.f12411f = i8;
    }

    public static o e(String str) {
        return "AES_GCM_NoPadding_BIOMETRIC".equals(str) ? AES_GCM_NoPadding : valueOf(str);
    }
}
