package com.it_nomads.fluttersecurestorage.crypto;

import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
/* loaded from: classes.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private final String f6337a;

    /* renamed from: b  reason: collision with root package name */
    private final KeyGenParameterSpec f6338b;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        final String f6339a;

        /* renamed from: b  reason: collision with root package name */
        KeyGenParameterSpec f6340b;

        /* renamed from: c  reason: collision with root package name */
        EnumC0074b f6341c;

        /* renamed from: d  reason: collision with root package name */
        boolean f6342d;

        /* renamed from: e  reason: collision with root package name */
        int f6343e;

        /* renamed from: f  reason: collision with root package name */
        boolean f6344f;

        /* renamed from: g  reason: collision with root package name */
        final Context f6345g;

        /* renamed from: com.it_nomads.fluttersecurestorage.crypto.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        static class C0071a {

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.it_nomads.fluttersecurestorage.crypto.b$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            public static class C0072a {
                static void a(KeyGenParameterSpec.Builder builder) {
                    builder.setIsStrongBoxBacked(true);
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.it_nomads.fluttersecurestorage.crypto.b$a$a$b  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            public static class C0073b {
                static void a(KeyGenParameterSpec.Builder builder, int i8, int i9) {
                    builder.setUserAuthenticationParameters(i8, i9);
                }
            }

            static b a(a aVar) {
                EnumC0074b enumC0074b = aVar.f6341c;
                if (enumC0074b == null && aVar.f6340b == null) {
                    throw new IllegalArgumentException("build() called before setKeyGenParameterSpec or setKeyScheme.");
                }
                if (enumC0074b == EnumC0074b.AES256_GCM) {
                    KeyGenParameterSpec.Builder keySize = new KeyGenParameterSpec.Builder(aVar.f6339a, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(256);
                    if (aVar.f6342d) {
                        keySize.setUserAuthenticationRequired(true);
                        if (Build.VERSION.SDK_INT >= 30) {
                            C0073b.a(keySize, aVar.f6343e, 3);
                        } else {
                            keySize.setUserAuthenticationValidityDurationSeconds(aVar.f6343e);
                        }
                    }
                    if (Build.VERSION.SDK_INT >= 28 && aVar.f6344f && aVar.f6345g.getPackageManager().hasSystemFeature("android.hardware.strongbox_keystore")) {
                        C0072a.a(keySize);
                    }
                    aVar.f6340b = keySize.build();
                }
                KeyGenParameterSpec keyGenParameterSpec = aVar.f6340b;
                if (keyGenParameterSpec != null) {
                    return new b(c.b(keyGenParameterSpec), aVar.f6340b);
                }
                throw new NullPointerException("KeyGenParameterSpec was null after build() check");
            }

            static String b(KeyGenParameterSpec keyGenParameterSpec) {
                return keyGenParameterSpec.getKeystoreAlias();
            }
        }

        public a(Context context) {
            this(context, "_androidx_security_master_key_");
        }

        public b a() {
            return C0071a.a(this);
        }

        public a b(KeyGenParameterSpec keyGenParameterSpec) {
            if (this.f6341c == null) {
                if (this.f6339a.equals(C0071a.b(keyGenParameterSpec))) {
                    this.f6340b = keyGenParameterSpec;
                    return this;
                }
                throw new IllegalArgumentException("KeyGenParamSpec's key alias does not match provided alias (" + this.f6339a + " vs " + C0071a.b(keyGenParameterSpec));
            }
            throw new IllegalArgumentException("KeyGenParamSpec set after setting a KeyScheme");
        }

        public a(Context context, String str) {
            this.f6345g = context.getApplicationContext();
            this.f6339a = str;
        }
    }

    /* renamed from: com.it_nomads.fluttersecurestorage.crypto.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public enum EnumC0074b {
        AES256_GCM
    }

    b(String str, Object obj) {
        this.f6337a = str;
        this.f6338b = (KeyGenParameterSpec) obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a() {
        return this.f6337a;
    }

    public boolean b() {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore.containsAlias(this.f6337a);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException unused) {
            return false;
        }
    }

    public String toString() {
        return "MasterKey{keyAlias=" + this.f6337a + ", isKeyStoreBacked=" + b() + "}";
    }
}
