package v5;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes.dex */
public class t implements m {

    /* renamed from: a  reason: collision with root package name */
    private final String f12427a;

    /* renamed from: b  reason: collision with root package name */
    private final Cipher f12428b;

    /* renamed from: c  reason: collision with root package name */
    private final SecureRandom f12429c;

    /* renamed from: d  reason: collision with root package name */
    private final Key f12430d;

    public t(Context context, a aVar, Cipher cipher, u5.s sVar) {
        String e8 = sVar.e();
        this.f12427a = e8;
        SecureRandom secureRandom = new SecureRandom();
        this.f12429c = secureRandom;
        SharedPreferences sharedPreferences = context.getSharedPreferences(e8, 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String string = sharedPreferences.getString("AESVGhpcyBpcyB0aGUga2V5IGZvciBhIHNlY3VyZSBzdG9yYWdlIEFFUyBLZXkK", null);
        this.f12428b = d();
        if (string != null) {
            this.f12430d = aVar.b(Base64.decode(string, 0), "AES");
            return;
        }
        byte[] bArr = new byte[16];
        secureRandom.nextBytes(bArr);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        this.f12430d = secretKeySpec;
        edit.putString("AESVGhpcyBpcyB0aGUga2V5IGZvciBhIHNlY3VyZSBzdG9yYWdlIEFFUyBLZXkK", Base64.encodeToString(aVar.a(secretKeySpec), 0));
        edit.apply();
    }

    @Override // v5.m
    public byte[] a(byte[] bArr) {
        int e8 = e();
        byte[] bArr2 = new byte[e8];
        this.f12429c.nextBytes(bArr2);
        this.f12428b.init(1, this.f12430d, f(bArr2));
        byte[] doFinal = this.f12428b.doFinal(bArr);
        byte[] bArr3 = new byte[doFinal.length + e8];
        System.arraycopy(bArr2, 0, bArr3, 0, e8);
        System.arraycopy(doFinal, 0, bArr3, e8, doFinal.length);
        return bArr3;
    }

    @Override // v5.m
    public byte[] b(byte[] bArr) {
        int e8 = e();
        byte[] bArr2 = new byte[e8];
        System.arraycopy(bArr, 0, bArr2, 0, e8);
        AlgorithmParameterSpec f8 = f(bArr2);
        int length = bArr.length - e();
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, e8, bArr3, 0, length);
        this.f12428b.init(2, this.f12430d, f8);
        return this.f12428b.doFinal(bArr3);
    }

    @Override // v5.m
    public void c(Context context) {
        context.getSharedPreferences(this.f12427a, 0).edit().remove("AESVGhpcyBpcyB0aGUga2V5IGZvciBhIHNlY3VyZSBzdG9yYWdlIEFFUyBLZXkK").apply();
    }

    protected Cipher d() {
        return Cipher.getInstance("AES/GCM/NoPadding");
    }

    protected int e() {
        return 12;
    }

    protected AlgorithmParameterSpec f(byte[] bArr) {
        return new GCMParameterSpec(128, bArr);
    }
}
