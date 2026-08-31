package v5;

import android.content.Context;
/* loaded from: classes.dex */
public enum e {
    RSA_ECB_PKCS1Padding(new f() { // from class: v5.b
        @Override // v5.f
        public final a a(Context context, u5.s sVar) {
            return new k(context, sVar);
        }
    }, 1),
    RSA_ECB_OAEPwithSHA_256andMGF1Padding(new f() { // from class: v5.c
        @Override // v5.f
        public final a a(Context context, u5.s sVar) {
            return new l(context, sVar);
        }
    }, 23),
    AES_GCM_NoPadding(new f() { // from class: v5.d
        @Override // v5.f
        public final a a(Context context, u5.s sVar) {
            return new j(context, sVar);
        }
    }, 23);
    

    /* renamed from: e  reason: collision with root package name */
    final f f12399e;

    /* renamed from: f  reason: collision with root package name */
    final int f12400f;

    e(f fVar, int i8) {
        this.f12399e = fVar;
        this.f12400f = i8;
    }

    public static e e(String str) {
        return "AES_GCM_NoPadding_BIOMETRIC".equals(str) ? AES_GCM_NoPadding : valueOf(str);
    }
}
