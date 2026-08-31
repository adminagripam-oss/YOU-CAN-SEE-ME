package v5;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes.dex */
public class s implements m {

    /* renamed from: a  reason: collision with root package name */
    private final String f12423a;

    /* renamed from: d  reason: collision with root package name */
    private final Key f12426d;

    /* renamed from: c  reason: collision with root package name */
    private final SecureRandom f12425c = new SecureRandom();

    /* renamed from: b  reason: collision with root package name */
    private final Cipher f12424b = e();

    public s(Context context, a aVar, Cipher cipher, u5.s sVar) {
        this.f12423a = sVar.e();
        this.f12426d = g(context, cipher);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f(SharedPreferences sharedPreferences) {
        return sharedPreferences.contains("BVGhpcyBpcyB0aGUga2V5IGZvciBhIHNlY3VyZSBzdG9yYWdlIEFFUyBLZXkK");
    }

    private SecretKey g(Context context, Cipher cipher) {
        if (cipher == null) {
            cipher = e();
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.f12423a, 0);
        String string = sharedPreferences.getString("BVGhpcyBpcyB0aGUga2V5IGZvciBhIHNlY3VyZSBzdG9yYWdlIEFFUyBLZXkK", null);
        if (string != null) {
            return new SecretKeySpec(cipher.doFinal(Base64.decode(string, 0)), "AES");
        }
        byte[] d8 = d(32);
        SecretKeySpec secretKeySpec = new SecretKeySpec(d8, "AES");
        byte[] doFinal = cipher.doFinal(d8);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("BVGhpcyBpcyB0aGUga2V5IGZvciBhIHNlY3VyZSBzdG9yYWdlIEFFUyBLZXkK", Base64.encodeToString(doFinal, 0));
        edit.apply();
        return secretKeySpec;
    }

    @Override // v5.m
    public byte[] a(byte[] bArr) {
        byte[] d8 = d(12);
        this.f12424b.init(1, this.f12426d, new GCMParameterSpec(128, d8));
        byte[] doFinal = this.f12424b.doFinal(bArr);
        byte[] bArr2 = new byte[d8.length + doFinal.length];
        System.arraycopy(d8, 0, bArr2, 0, d8.length);
        System.arraycopy(doFinal, 0, bArr2, d8.length, doFinal.length);
        return bArr2;
    }

    @Override // v5.m
    public byte[] b(byte[] bArr) {
        byte[] bArr2 = new byte[12];
        System.arraycopy(bArr, 0, bArr2, 0, 12);
        int length = bArr.length - 12;
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, 12, bArr3, 0, length);
        this.f12424b.init(2, this.f12426d, new GCMParameterSpec(128, bArr2));
        return this.f12424b.doFinal(bArr3);
    }

    @Override // v5.m
    public void c(Context context) {
        context.getSharedPreferences(this.f12423a, 0).edit().remove("BVGhpcyBpcyB0aGUga2V5IGZvciBhIHNlY3VyZSBzdG9yYWdlIEFFUyBLZXkK").apply();
    }

    public byte[] d(int i8) {
        byte[] bArr = new byte[i8];
        this.f12425c.nextBytes(bArr);
        return bArr;
    }

    protected Cipher e() {
        return Cipher.getInstance("AES/GCM/NoPadding");
    }
}
