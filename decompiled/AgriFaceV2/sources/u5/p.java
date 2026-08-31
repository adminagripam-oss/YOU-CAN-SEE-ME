package u5;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.biometrics.BiometricPrompt$AuthenticationCallback;
import android.os.Build;
import android.os.CancellationSignal;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import android.util.Log;
import com.it_nomads.fluttersecurestorage.crypto.a;
import com.it_nomads.fluttersecurestorage.crypto.b;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
/* loaded from: classes.dex */
public class p {

    /* renamed from: f  reason: collision with root package name */
    private static final Charset f12168f = StandardCharsets.UTF_8;

    /* renamed from: a  reason: collision with root package name */
    private s f12169a;

    /* renamed from: b  reason: collision with root package name */
    private final Context f12170b;

    /* renamed from: c  reason: collision with root package name */
    private SharedPreferences f12171c;

    /* renamed from: d  reason: collision with root package name */
    private v5.m f12172d;

    /* renamed from: e  reason: collision with root package name */
    private v5.p f12173e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements z {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Cipher f12174a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ SharedPreferences f12175b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ SharedPreferences f12176c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ y f12177d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ z f12178e;

        a(Cipher cipher, SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2, y yVar, z zVar) {
            this.f12174a = cipher;
            this.f12175b = sharedPreferences;
            this.f12176c = sharedPreferences2;
            this.f12177d = yVar;
            this.f12178e = zVar;
        }

        @Override // u5.z
        public /* bridge */ /* synthetic */ void a(Object obj) {
            c(o.a(obj));
        }

        @Override // u5.z
        public void b(Exception exc) {
            Log.e("FlutterSecureStorage", "Biometric authentication failed for migration", exc);
            this.f12178e.b(new Exception("Migration cancelled: Biometric authentication failed", exc));
        }

        public void c(BiometricPrompt.AuthenticationResult authenticationResult) {
            try {
                Log.d("FlutterSecureStorage", "Step 2/7: Decrypting all data from _BACKUP with saved biometric cipher...");
                v5.m f8 = p.this.f12173e.f(p.this.f12170b, this.f12174a);
                Map u7 = p.this.u(this.f12175b, null, f8);
                Log.d("FlutterSecureStorage", "Step 3/7: Initializing current non-biometric cipher...");
                v5.m d8 = p.this.f12173e.d(p.this.f12170b, null);
                Log.d("FlutterSecureStorage", "Step 4/7: Encrypting all data with current cipher...");
                p.this.y(u7, this.f12175b, d8);
                Log.d("FlutterSecureStorage", "Step 5/7: Deleting backup after successful re-encryption...");
                x.d(this.f12175b, this.f12176c, this.f12177d, p.this.f12169a, p.this.f12169a.l());
                Log.d("FlutterSecureStorage", "Step 6/7: Updating algorithm markers to current...");
                p.this.e0(this.f12177d);
                Log.d("FlutterSecureStorage", "Step 7/7: Deleting old biometric AES key from Android KeyStore...");
                if (p.this.f12173e.a()) {
                    try {
                        p.this.f12173e.e(p.this.f12170b).d();
                        f8.c(p.this.f12170b);
                        Log.d("FlutterSecureStorage", "Old key deleted from KeyStore");
                    } catch (Exception e8) {
                        Log.w("FlutterSecureStorage", "Failed to delete old key from KeyStore (may not exist)", e8);
                    }
                }
                p.this.f12172d = d8;
                Log.i("FlutterSecureStorage", "Biometric→Non-biometric migration WITH BACKUP completed! Data no longer requires biometric authentication.");
                this.f12178e.a(null);
            } catch (Exception e9) {
                Log.e("FlutterSecureStorage", "Failed to complete migration after authentication", e9);
                this.f12178e.b(e9);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements z {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Cipher f12180a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Map f12181b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ SharedPreferences f12182c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ SharedPreferences f12183d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ y f12184e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ v5.m f12185f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ z f12186g;

        b(Cipher cipher, Map map, SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2, y yVar, v5.m mVar, z zVar) {
            this.f12180a = cipher;
            this.f12181b = map;
            this.f12182c = sharedPreferences;
            this.f12183d = sharedPreferences2;
            this.f12184e = yVar;
            this.f12185f = mVar;
            this.f12186g = zVar;
        }

        @Override // u5.z
        public /* bridge */ /* synthetic */ void a(Object obj) {
            c(o.a(obj));
        }

        @Override // u5.z
        public void b(Exception exc) {
            Log.e("FlutterSecureStorage", "Biometric authentication failed for migration", exc);
            this.f12186g.b(new Exception("Migration cancelled: Biometric authentication failed", exc));
        }

        public void c(BiometricPrompt.AuthenticationResult authenticationResult) {
            try {
                Log.d("FlutterSecureStorage", "Step 3/7: Initializing current biometric cipher...");
                v5.m d8 = p.this.f12173e.d(p.this.f12170b, this.f12180a);
                Log.d("FlutterSecureStorage", "Step 4/7: Encrypting all data with current biometric cipher...");
                p.this.y(this.f12181b, this.f12182c, d8);
                Log.d("FlutterSecureStorage", "Step 5/7: Deleting backup after successful re-encryption...");
                x.d(this.f12182c, this.f12183d, this.f12184e, p.this.f12169a, p.this.f12169a.l());
                Log.d("FlutterSecureStorage", "Step 6/7: Updating algorithm markers to current...");
                p.this.e0(this.f12184e);
                Log.d("FlutterSecureStorage", "Step 7/7: Deleting old RSA key from Android KeyStore...");
                if (p.this.f12173e.a()) {
                    try {
                        p.this.f12173e.e(p.this.f12170b).d();
                        this.f12185f.c(p.this.f12170b);
                        Log.d("FlutterSecureStorage", "Old key deleted from KeyStore");
                    } catch (Exception e8) {
                        Log.w("FlutterSecureStorage", "Failed to delete old key from KeyStore (may not exist)", e8);
                    }
                }
                p.this.f12172d = d8;
                Log.i("FlutterSecureStorage", "Non-biometric→Biometric migration WITH BACKUP completed! Data now requires biometric authentication.");
                this.f12186g.a(null);
            } catch (Exception e9) {
                Log.e("FlutterSecureStorage", "Failed to complete migration after authentication", e9);
                this.f12186g.b(e9);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements z {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Cipher f12188a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ SharedPreferences f12189b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ SharedPreferences f12190c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ y f12191d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ z f12192e;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a implements z {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ Cipher f12194a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ Map f12195b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ v5.m f12196c;

            a(Cipher cipher, Map map, v5.m mVar) {
                this.f12194a = cipher;
                this.f12195b = map;
                this.f12196c = mVar;
            }

            @Override // u5.z
            public /* bridge */ /* synthetic */ void a(Object obj) {
                c(o.a(obj));
            }

            @Override // u5.z
            public void b(Exception exc) {
                Log.e("FlutterSecureStorage", "Second biometric authentication failed for migration", exc);
                c.this.f12192e.b(new Exception("Migration cancelled: Second biometric authentication failed", exc));
            }

            public void c(BiometricPrompt.AuthenticationResult authenticationResult) {
                try {
                    Log.d("FlutterSecureStorage", "Step 4/8: Initializing current biometric cipher...");
                    v5.m d8 = p.this.f12173e.d(p.this.f12170b, this.f12194a);
                    if (this.f12195b.isEmpty()) {
                        Log.i("FlutterSecureStorage", "Step 5/8: No data to encrypt, skipping...");
                    } else {
                        Log.d("FlutterSecureStorage", "Step 5/8: Encrypting all data with current biometric cipher...");
                        c cVar = c.this;
                        p.this.y(this.f12195b, cVar.f12189b, d8);
                    }
                    Log.d("FlutterSecureStorage", "Step 6/8: Deleting backup after successful re-encryption...");
                    c cVar2 = c.this;
                    x.d(cVar2.f12189b, cVar2.f12190c, cVar2.f12191d, p.this.f12169a, p.this.f12169a.l());
                    Log.d("FlutterSecureStorage", "Step 7/8: Updating algorithm markers to current...");
                    c cVar3 = c.this;
                    p.this.e0(cVar3.f12191d);
                    Log.d("FlutterSecureStorage", "Step 8/8: Deleting old biometric AES key from Android KeyStore...");
                    if (p.this.f12173e.a()) {
                        try {
                            p.this.f12173e.e(p.this.f12170b).d();
                            this.f12196c.c(p.this.f12170b);
                            Log.d("FlutterSecureStorage", "Old key deleted from KeyStore");
                        } catch (Exception e8) {
                            Log.w("FlutterSecureStorage", "Failed to delete old key from KeyStore (may not exist)", e8);
                        }
                    }
                    p.this.f12172d = d8;
                    Log.i("FlutterSecureStorage", "Biometric→Biometric migration WITH BACKUP completed! Data now uses new biometric cipher.");
                    Log.i("FlutterSecureStorage", "Migrated " + this.f12195b.size() + " data items with new biometric algorithm.");
                    c.this.f12192e.a(null);
                } catch (Exception e9) {
                    Log.e("FlutterSecureStorage", "Failed to complete migration after second authentication", e9);
                    c.this.f12192e.b(e9);
                }
            }
        }

        c(Cipher cipher, SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2, y yVar, z zVar) {
            this.f12188a = cipher;
            this.f12189b = sharedPreferences;
            this.f12190c = sharedPreferences2;
            this.f12191d = yVar;
            this.f12192e = zVar;
        }

        @Override // u5.z
        public /* bridge */ /* synthetic */ void a(Object obj) {
            c(o.a(obj));
        }

        @Override // u5.z
        public void b(Exception exc) {
            Log.e("FlutterSecureStorage", "First biometric authentication failed for migration", exc);
            this.f12192e.b(new Exception("Migration cancelled: First biometric authentication failed", exc));
        }

        public void c(BiometricPrompt.AuthenticationResult authenticationResult) {
            String str;
            try {
                Log.d("FlutterSecureStorage", "Step 2/8: Decrypting all data from _BACKUP with saved biometric cipher...");
                v5.m f8 = p.this.f12173e.f(p.this.f12170b, this.f12188a);
                Map u7 = p.this.u(this.f12189b, null, f8);
                if (u7.isEmpty()) {
                    str = "No data found in _BACKUP keys to migrate";
                } else {
                    str = "Found " + u7.size() + " items to migrate from _BACKUP keys";
                }
                Log.i("FlutterSecureStorage", str);
                Log.d("FlutterSecureStorage", "Step 3/8: Getting current biometric cipher...");
                Cipher c8 = p.this.f12173e.c(p.this.f12170b).c(p.this.f12170b);
                if (c8 == null) {
                    throw new Exception("Failed to get current biometric cipher");
                }
                Log.i("FlutterSecureStorage", "Authenticating with NEW biometric cipher to encrypt data...");
                p.this.q(c8, new a(c8, u7, f8));
            } catch (Exception e8) {
                Log.e("FlutterSecureStorage", "Failed after first authentication", e8);
                this.f12192e.b(e8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements z {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ SharedPreferences f12198a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ SharedPreferences f12199b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ y f12200c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ z f12201d;

        d(SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2, y yVar, z zVar) {
            this.f12198a = sharedPreferences;
            this.f12199b = sharedPreferences2;
            this.f12200c = yVar;
            this.f12201d = zVar;
        }

        @Override // u5.z
        public void b(Exception exc) {
            Log.e("FlutterSecureStorage", "Cipher initialization failed during migration. Using EncryptedSharedPreferences.", exc);
            p.this.f12171c = this.f12198a;
            this.f12201d.a(null);
        }

        @Override // u5.z
        /* renamed from: c */
        public void a(Void r52) {
            try {
                p.this.T(this.f12198a, this.f12199b);
                p.this.f12171c = this.f12199b;
                Log.i("FlutterSecureStorage", "Migration completed successfully. Now using custom cipher storage.");
                p.this.d0(this.f12200c);
                this.f12201d.a(null);
            } catch (Exception e8) {
                Log.e("FlutterSecureStorage", "Migration failed. Falling back to EncryptedSharedPreferences.", e8);
                p.this.f12171c = this.f12198a;
                this.f12201d.a(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements z {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ SharedPreferences f12203a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ z f12204b;

        e(SharedPreferences sharedPreferences, z zVar) {
            this.f12203a = sharedPreferences;
            this.f12204b = zVar;
        }

        @Override // u5.z
        public void b(Exception exc) {
            this.f12204b.b(exc);
        }

        @Override // u5.z
        /* renamed from: c */
        public void a(Void r22) {
            p.this.f12171c = this.f12203a;
            this.f12204b.a(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f implements z {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ z f12206a;

        f(z zVar) {
            this.f12206a = zVar;
        }

        @Override // u5.z
        public /* bridge */ /* synthetic */ void a(Object obj) {
            c(o.a(obj));
        }

        @Override // u5.z
        public void b(Exception exc) {
            this.f12206a.b(exc);
        }

        public void c(BiometricPrompt.AuthenticationResult authenticationResult) {
            BiometricPrompt.CryptoObject cryptoObject;
            Cipher cipher;
            try {
                p pVar = p.this;
                v5.p pVar2 = pVar.f12173e;
                Context context = p.this.f12170b;
                cryptoObject = authenticationResult.getCryptoObject();
                cipher = cryptoObject.getCipher();
                pVar.f12172d = pVar2.d(context, cipher);
                Log.d("FlutterSecureStorage", "Biometric authentication succeeded");
            } catch (Exception e8) {
                Log.e("FlutterSecureStorage", "Failed to initialize storage cipher after authentication", e8);
                this.f12206a.b(e8);
            }
            this.f12206a.a(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g implements z {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Cipher f12208a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ SharedPreferences f12209b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ y f12210c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ z f12211d;

        g(Cipher cipher, SharedPreferences sharedPreferences, y yVar, z zVar) {
            this.f12208a = cipher;
            this.f12209b = sharedPreferences;
            this.f12210c = yVar;
            this.f12211d = zVar;
        }

        @Override // u5.z
        public /* bridge */ /* synthetic */ void a(Object obj) {
            c(o.a(obj));
        }

        @Override // u5.z
        public void b(Exception exc) {
            Log.e("FlutterSecureStorage", "Biometric authentication failed for migration", exc);
            this.f12211d.b(new Exception("Migration cancelled: Biometric authentication failed", exc));
        }

        public void c(BiometricPrompt.AuthenticationResult authenticationResult) {
            try {
                Log.d("FlutterSecureStorage", "Step 2/6: Decrypting all data with saved biometric cipher...");
                v5.m f8 = p.this.f12173e.f(p.this.f12170b, this.f12208a);
                Map t8 = p.this.t(this.f12209b, f8);
                Log.d("FlutterSecureStorage", "Step 3/6: Deleting old biometric AES key from Android KeyStore...");
                if (p.this.f12173e.a()) {
                    try {
                        p.this.f12173e.e(p.this.f12170b).d();
                        f8.c(p.this.f12170b);
                        Log.d("FlutterSecureStorage", "Old key deleted from KeyStore");
                    } catch (Exception e8) {
                        Log.w("FlutterSecureStorage", "Failed to delete old key from KeyStore (may not exist)", e8);
                    }
                }
                Log.d("FlutterSecureStorage", "Step 4/6: Updating algorithm markers to current...");
                p.this.e0(this.f12210c);
                Log.d("FlutterSecureStorage", "Step 5/6: Initializing current non-biometric cipher...");
                v5.m d8 = p.this.f12173e.d(p.this.f12170b, null);
                Log.d("FlutterSecureStorage", "Step 6/6: Encrypting all data with current cipher...");
                p.this.y(t8, this.f12209b, d8);
                p.this.f12172d = d8;
                Log.i("FlutterSecureStorage", "Biometric→Non-biometric migration completed! Data no longer requires biometric authentication.");
                this.f12211d.a(null);
            } catch (Exception e9) {
                Log.e("FlutterSecureStorage", "Failed to complete migration after authentication", e9);
                this.f12211d.b(e9);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class h implements z {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Cipher f12213a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Map f12214b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ SharedPreferences f12215c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ z f12216d;

        h(Cipher cipher, Map map, SharedPreferences sharedPreferences, z zVar) {
            this.f12213a = cipher;
            this.f12214b = map;
            this.f12215c = sharedPreferences;
            this.f12216d = zVar;
        }

        @Override // u5.z
        public /* bridge */ /* synthetic */ void a(Object obj) {
            c(o.a(obj));
        }

        @Override // u5.z
        public void b(Exception exc) {
            Log.e("FlutterSecureStorage", "Biometric authentication failed for migration", exc);
            this.f12216d.b(new Exception("Migration cancelled: Biometric authentication failed", exc));
        }

        public void c(BiometricPrompt.AuthenticationResult authenticationResult) {
            try {
                Log.d("FlutterSecureStorage", "Step 5/6: Initializing current biometric cipher...");
                v5.m d8 = p.this.f12173e.d(p.this.f12170b, this.f12213a);
                Log.d("FlutterSecureStorage", "Step 6/6: Encrypting all data with current biometric cipher...");
                p.this.y(this.f12214b, this.f12215c, d8);
                p.this.f12172d = d8;
                Log.i("FlutterSecureStorage", "Non-biometric→Biometric migration completed! Data now requires biometric authentication.");
                this.f12216d.a(null);
            } catch (Exception e8) {
                Log.e("FlutterSecureStorage", "Failed to complete migration after authentication", e8);
                this.f12216d.b(e8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class i implements z {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Cipher f12218a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ SharedPreferences f12219b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ y f12220c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ z f12221d;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a implements z {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ Cipher f12223a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ Map f12224b;

            a(Cipher cipher, Map map) {
                this.f12223a = cipher;
                this.f12224b = map;
            }

            @Override // u5.z
            public /* bridge */ /* synthetic */ void a(Object obj) {
                c(o.a(obj));
            }

            @Override // u5.z
            public void b(Exception exc) {
                Log.e("FlutterSecureStorage", "Second biometric authentication failed for migration", exc);
                i.this.f12221d.b(new Exception("Migration cancelled: Second biometric authentication failed", exc));
            }

            public void c(BiometricPrompt.AuthenticationResult authenticationResult) {
                try {
                    Log.d("FlutterSecureStorage", "Step 6/7: Initializing current biometric cipher...");
                    v5.m d8 = p.this.f12173e.d(p.this.f12170b, this.f12223a);
                    Log.d("FlutterSecureStorage", "Step 7/7: Encrypting all data with current biometric cipher...");
                    i iVar = i.this;
                    p.this.y(this.f12224b, iVar.f12219b, d8);
                    p.this.f12172d = d8;
                    Log.i("FlutterSecureStorage", "Biometric→Biometric migration completed! Data now uses new biometric cipher.");
                    i.this.f12221d.a(null);
                } catch (Exception e8) {
                    Log.e("FlutterSecureStorage", "Failed to complete migration after second authentication", e8);
                    i.this.f12221d.b(e8);
                }
            }
        }

        i(Cipher cipher, SharedPreferences sharedPreferences, y yVar, z zVar) {
            this.f12218a = cipher;
            this.f12219b = sharedPreferences;
            this.f12220c = yVar;
            this.f12221d = zVar;
        }

        @Override // u5.z
        public /* bridge */ /* synthetic */ void a(Object obj) {
            c(o.a(obj));
        }

        @Override // u5.z
        public void b(Exception exc) {
            Log.e("FlutterSecureStorage", "First biometric authentication failed for migration", exc);
            this.f12221d.b(new Exception("Migration cancelled: First biometric authentication failed", exc));
        }

        public void c(BiometricPrompt.AuthenticationResult authenticationResult) {
            try {
                Log.d("FlutterSecureStorage", "Step 2/7: Decrypting all data with saved biometric cipher...");
                v5.m f8 = p.this.f12173e.f(p.this.f12170b, this.f12218a);
                Map t8 = p.this.t(this.f12219b, f8);
                Log.d("FlutterSecureStorage", "Step 3/7: Deleting old biometric AES key from Android KeyStore...");
                if (p.this.f12173e.a()) {
                    try {
                        p.this.f12173e.e(p.this.f12170b).d();
                        f8.c(p.this.f12170b);
                        Log.d("FlutterSecureStorage", "Old key deleted from KeyStore");
                    } catch (Exception e8) {
                        Log.w("FlutterSecureStorage", "Failed to delete old key from KeyStore (may not exist)", e8);
                    }
                }
                Log.d("FlutterSecureStorage", "Step 4/7: Updating algorithm markers to current...");
                p.this.e0(this.f12220c);
                Log.d("FlutterSecureStorage", "Step 5/7: Getting current biometric cipher...");
                Cipher c8 = p.this.f12173e.c(p.this.f12170b).c(p.this.f12170b);
                if (c8 == null) {
                    throw new Exception("Failed to get current biometric cipher");
                }
                Log.i("FlutterSecureStorage", "Authenticating with NEW biometric cipher to encrypt data...");
                p.this.q(c8, new a(c8, t8));
            } catch (Exception e9) {
                Log.e("FlutterSecureStorage", "Failed after first authentication", e9);
                this.f12221d.b(e9);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class j implements z {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ y f12226a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ z f12227b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f12228c;

        j(y yVar, z zVar, String str) {
            this.f12226a = yVar;
            this.f12227b = zVar;
            this.f12228c = str;
        }

        @Override // u5.z
        public void b(Exception exc) {
            Log.e("FlutterSecureStorage", "Data migration failed: " + exc.getMessage(), exc);
            if (p.this.f12169a.t()) {
                Log.w("FlutterSecureStorage", "resetOnError is enabled. Deleting all data as fallback...");
                p.this.x(this.f12226a, this.f12227b);
                p.this.d0(this.f12226a);
                return;
            }
            Log.e("FlutterSecureStorage", "Set resetOnError=true to automatically delete data after migration failure.");
            this.f12227b.b(new Exception(String.format("Migration failed after algorithm change (%s). Enable resetOnError=true or call deleteAll().", this.f12228c), exc));
        }

        @Override // u5.z
        /* renamed from: c */
        public void a(Void r22) {
            Log.i("FlutterSecureStorage", "Data migration completed successfully!");
            p.this.d0(this.f12226a);
            this.f12227b.a(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class k extends BiometricPrompt$AuthenticationCallback {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ z f12230a;

        k(z zVar) {
            this.f12230a = zVar;
        }

        public void onAuthenticationError(int i8, CharSequence charSequence) {
            super.onAuthenticationError(i8, charSequence);
            Log.e("FlutterSecureStorage", "Biometric authentication error [" + i8 + "]: " + ((Object) charSequence));
            z zVar = this.f12230a;
            zVar.b(new Exception("Biometric authentication error [" + i8 + "]: " + ((Object) charSequence)));
        }

        public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
            super.onAuthenticationSucceeded(authenticationResult);
            this.f12230a.a(authenticationResult);
        }
    }

    public p(Context context) {
        this.f12170b = context.getApplicationContext();
    }

    private void A(boolean z7) {
        int canAuthenticate;
        int i8 = Build.VERSION.SDK_INT;
        if (i8 < 28) {
            if (z7) {
                throw new Exception("BIOMETRIC_UNAVAILABLE: Biometric authentication requires Android 9 (API 28) or higher");
            }
        } else if (!K()) {
            if (z7) {
                throw new Exception("BIOMETRIC_UNAVAILABLE: Device has no PIN, pattern, password, or biometric enrolled. Please secure your device in Settings.");
            }
            Log.w("FlutterSecureStorage", "Device has no security. Biometric authentication will be skipped (enforceBiometrics=false).");
        } else if (i8 >= 30) {
            BiometricManager a8 = u5.b.a(this.f12170b.getSystemService(l.a()));
            if (a8 == null) {
                if (z7) {
                    throw new Exception("BIOMETRIC_UNAVAILABLE: BiometricManager not available on this device");
                }
                return;
            }
            canAuthenticate = a8.canAuthenticate(this.f12169a.r() ? 15 : 32783);
            if (canAuthenticate != 0) {
                if (canAuthenticate != 1) {
                    if (canAuthenticate != 11) {
                        if (canAuthenticate != 12) {
                            if (canAuthenticate != 15) {
                                if (z7) {
                                    throw new Exception("BIOMETRIC_UNAVAILABLE: Unknown biometric status (code: " + canAuthenticate + ")");
                                }
                            } else if (z7) {
                                throw new Exception("BIOMETRIC_UNAVAILABLE: Security update required for biometric authentication");
                            }
                        } else if (z7) {
                            throw new Exception("BIOMETRIC_UNAVAILABLE: No biometric hardware detected on this device");
                        }
                    } else if (z7) {
                        throw new Exception("BIOMETRIC_UNAVAILABLE: No fingerprint or face enrolled. Please enroll in Settings.");
                    }
                } else if (z7) {
                    throw new Exception("BIOMETRIC_UNAVAILABLE: Biometric hardware temporarily unavailable");
                }
                Log.w("FlutterSecureStorage", "Biometric check failed with code " + canAuthenticate + ", but continuing (enforceBiometrics=false)");
            }
        }
    }

    private Boolean B(y yVar) {
        return Boolean.valueOf(yVar.d("ENCRYPTED_PREFERENCES_MIGRATED", false));
    }

    private void C(y yVar, z zVar, Exception exc, String str) {
        Log.e("FlutterSecureStorage", "Key mismatch detected during cipher initialization: " + str, exc);
        Log.e("FlutterSecureStorage", "This typically occurs after an algorithm change.");
        Log.e("FlutterSecureStorage", "Stored key cannot be decrypted with current algorithm.");
        if (this.f12169a.u()) {
            Log.i("FlutterSecureStorage", "migrateOnAlgorithmChange is enabled. Attempting data migration...");
            Q(yVar, this.f12170b.getSharedPreferences(this.f12169a.d(), 0), new j(yVar, zVar, str));
            return;
        }
        Log.w("FlutterSecureStorage", "migrateOnAlgorithmChange is disabled. Skipping data migration.");
        if (this.f12169a.t()) {
            Log.w("FlutterSecureStorage", "resetOnError is enabled. Deleting all data and keys to recover.");
            x(yVar, zVar);
            return;
        }
        Log.e("FlutterSecureStorage", "Set resetOnError=true to automatically delete data and recover.");
        Log.e("FlutterSecureStorage", "Or set migrateOnAlgorithmChange=true to preserve data during algorithm changes.");
        zVar.b(new Exception(String.format("Key mismatch after algorithm change (%s). Enable migrateOnAlgorithmChange=true to preserve data, or resetOnError=true to delete.", str), exc));
    }

    private boolean D(String str, String str2, Exception exc) {
        String str3;
        boolean t8 = this.f12169a.t();
        if (str2 != null) {
            str3 = "key '" + str2 + "'";
        } else {
            str3 = "all data";
        }
        Object[] objArr = new Object[3];
        objArr[0] = str;
        objArr[1] = str3;
        objArr[2] = t8 ? "Attempting to delete corrupted data and retry..." : "Set resetOnError=true to automatically delete corrupted data.";
        Log.e("FlutterSecureStorage", String.format("Storage operation '%s' failed for %s. %s", objArr), exc);
        if (t8) {
            try {
                if (str2 != null) {
                    v(str2);
                } else {
                    w();
                }
                Object[] objArr2 = new Object[1];
                objArr2[0] = str2 != null ? "Data for key has been deleted" : "All data has been deleted";
                Log.w("FlutterSecureStorage", String.format("%s completed. Retrying operation...", objArr2));
                return true;
            } catch (Exception e8) {
                Object[] objArr3 = new Object[1];
                objArr3[0] = str2 != null ? "delete data for key" : "delete all data";
                Log.e("FlutterSecureStorage", String.format("Failed to %s during error handling.", objArr3), e8);
                return false;
            }
        }
        return false;
    }

    private boolean E(SharedPreferences sharedPreferences) {
        for (String str : sharedPreferences.getAll().keySet()) {
            if (str.contains(this.f12169a.l())) {
                return true;
            }
        }
        return false;
    }

    private SharedPreferences G(Context context) {
        return com.it_nomads.fluttersecurestorage.crypto.a.e(context, this.f12169a.d(), new b.a(context).b(new KeyGenParameterSpec.Builder("_androidx_security_master_key_", 3).setEncryptionPaddings("NoPadding").setBlockModes("GCM").setKeySize(256).build()).a(), a.d.AES256_SIV, a.e.AES256_GCM);
    }

    private void H(y yVar, z zVar) {
        String str;
        try {
            v5.p pVar = new v5.p(yVar, this.f12169a.j(), this.f12169a.k(), this.f12169a);
            this.f12173e = pVar;
            if (pVar.h()) {
                Log.w("FlutterSecureStorage", "Algorithm changed detected.");
                C(yVar, zVar, null, "Algorithm changed detected");
                return;
            }
            Cipher c8 = this.f12173e.c(this.f12170b).c(this.f12170b);
            boolean f8 = this.f12169a.f();
            boolean K = K();
            if (c8 != null && Build.VERSION.SDK_INT >= 28 && (f8 || K)) {
                q(c8, new f(zVar));
                return;
            }
            this.f12172d = this.f12173e.d(this.f12170b, c8);
            zVar.a(null);
        } catch (InvalidKeyException e8) {
            e = e8;
            str = "Invalid key, key type incompatible with cipher";
            C(yVar, zVar, e, str);
        } catch (NoSuchAlgorithmException e9) {
            Log.e("FlutterSecureStorage", "Cryptographic algorithm not available on this device", e9);
            zVar.b(new Exception("Required cryptographic algorithm not supported by device.", e9));
        } catch (BadPaddingException e10) {
            e = e10;
            str = "Bad padding, wrong key for cipher algorithm";
            C(yVar, zVar, e, str);
        } catch (IllegalBlockSizeException e11) {
            e = e11;
            str = "Illegal block size, wrong cipher configuration";
            C(yVar, zVar, e, str);
        } catch (Exception e12) {
            Log.e("FlutterSecureStorage", "Failed to initialize storage cipher", e12);
            zVar.b(e12);
        }
    }

    private boolean I(String str) {
        return str != null && str.contains("BIOMETRIC");
    }

    private void N(y yVar, SharedPreferences sharedPreferences, boolean z7, boolean z8, z zVar) {
        Log.i("FlutterSecureStorage", "Starting biometric migration (authentication required)...");
        Log.i("FlutterSecureStorage", "Migration direction: FROM biometric=" + z7 + ", TO biometric=" + z8);
        try {
            boolean v7 = this.f12169a.v();
            if (v7) {
                Log.i("FlutterSecureStorage", "Using biometric migration WITH BACKUP protection");
            }
            if (z7 && !z8) {
                Log.i("FlutterSecureStorage", "You will be prompted to authenticate with your OLD biometric settings to decrypt existing data.");
                if (v7) {
                    S(yVar, sharedPreferences, zVar);
                } else {
                    R(yVar, sharedPreferences, zVar);
                }
            } else if (z7 || !z8) {
                Log.i("FlutterSecureStorage", "You will be prompted to authenticate twice (once for decrypt, once for encrypt).");
                if (v7) {
                    P(yVar, sharedPreferences, zVar);
                } else {
                    O(yVar, sharedPreferences, zVar);
                }
            } else {
                Log.i("FlutterSecureStorage", "You will be prompted to authenticate with your NEW biometric settings to encrypt data.");
                if (v7) {
                    W(yVar, sharedPreferences, zVar);
                } else {
                    V(yVar, sharedPreferences, zVar);
                }
            }
        } catch (Exception e8) {
            Log.e("FlutterSecureStorage", "Biometric migration failed", e8);
            zVar.b(new Exception("Biometric migration failed", e8));
        }
    }

    private void O(y yVar, SharedPreferences sharedPreferences, z zVar) {
        try {
            Log.d("FlutterSecureStorage", "Step 1/7: Getting saved biometric cipher...");
            Cipher c8 = this.f12173e.e(this.f12170b).c(this.f12170b);
            if (c8 == null) {
                throw new Exception("Failed to get saved biometric cipher");
            }
            Log.i("FlutterSecureStorage", "Authenticating with OLD biometric cipher to decrypt data...");
            q(c8, new i(c8, sharedPreferences, yVar, zVar));
        } catch (Exception e8) {
            Log.e("FlutterSecureStorage", "Failed to initialize biometric-to-biometric migration", e8);
            zVar.b(e8);
        }
    }

    private void P(y yVar, SharedPreferences sharedPreferences, z zVar) {
        try {
            SharedPreferences sharedPreferences2 = this.f12170b.getSharedPreferences("FlutterSecureKeyStorage", 0);
            if (!"complete".equals(x.h(yVar, this.f12169a))) {
                Log.i("FlutterSecureStorage", "Creating backup before biometric→biometric migration...");
                s sVar = this.f12169a;
                x.b(sharedPreferences, sharedPreferences2, yVar, sVar, sVar.l());
                Log.i("FlutterSecureStorage", "Backup created successfully");
            }
            Log.d("FlutterSecureStorage", "Step 1/8: Getting saved biometric cipher...");
            Cipher c8 = this.f12173e.e(this.f12170b).c(this.f12170b);
            if (c8 == null) {
                throw new Exception("Failed to get saved biometric cipher");
            }
            Log.i("FlutterSecureStorage", "Authenticating with OLD biometric cipher to decrypt data...");
            q(c8, new c(c8, sharedPreferences, sharedPreferences2, yVar, zVar));
        } catch (Exception e8) {
            Log.e("FlutterSecureStorage", "Failed to initialize biometric-to-biometric migration with backup", e8);
            zVar.b(e8);
        }
    }

    private void Q(y yVar, SharedPreferences sharedPreferences, z zVar) {
        Log.i("FlutterSecureStorage", "Starting data migration from saved to current cipher algorithms...");
        try {
            String obj = this.f12173e.e(this.f12170b).toString();
            String k8 = this.f12169a.k();
            boolean I = I(obj);
            boolean I2 = I(k8);
            if (I || I2) {
                Log.i("FlutterSecureStorage", "Detected biometric migration: FROM=" + obj + ", TO=" + k8);
                N(yVar, sharedPreferences, I, I2, zVar);
            } else {
                Log.i("FlutterSecureStorage", "Detected non-biometric migration: FROM=" + obj + ", TO=" + k8);
                if (this.f12169a.v()) {
                    Log.i("FlutterSecureStorage", "Using migration WITH BACKUP protection");
                    Y(yVar, sharedPreferences, zVar);
                } else {
                    X(yVar, sharedPreferences, zVar);
                }
            }
        } catch (Exception e8) {
            Log.e("FlutterSecureStorage", "Failed to start migration", e8);
            zVar.b(new Exception("Migration initialization failed", e8));
        }
    }

    private void R(y yVar, SharedPreferences sharedPreferences, z zVar) {
        try {
            Log.d("FlutterSecureStorage", "Step 1/6: Getting saved biometric cipher...");
            Cipher c8 = this.f12173e.e(this.f12170b).c(this.f12170b);
            if (c8 == null) {
                throw new Exception("Failed to get saved biometric cipher");
            }
            Log.i("FlutterSecureStorage", "Authenticating with OLD biometric cipher to decrypt data...");
            q(c8, new g(c8, sharedPreferences, yVar, zVar));
        } catch (Exception e8) {
            Log.e("FlutterSecureStorage", "Failed to initialize biometric migration", e8);
            zVar.b(e8);
        }
    }

    private void S(y yVar, SharedPreferences sharedPreferences, z zVar) {
        try {
            SharedPreferences sharedPreferences2 = this.f12170b.getSharedPreferences("FlutterSecureKeyStorage", 0);
            if (!"complete".equals(x.h(yVar, this.f12169a))) {
                Log.i("FlutterSecureStorage", "Creating backup before biometric→non-biometric migration...");
                s sVar = this.f12169a;
                x.b(sharedPreferences, sharedPreferences2, yVar, sVar, sVar.l());
                Log.i("FlutterSecureStorage", "Backup created successfully");
            }
            Log.d("FlutterSecureStorage", "Step 1/7: Getting saved biometric cipher...");
            Cipher c8 = this.f12173e.e(this.f12170b).c(this.f12170b);
            if (c8 == null) {
                throw new Exception("Failed to get saved biometric cipher");
            }
            Log.i("FlutterSecureStorage", "Authenticating with OLD biometric cipher to decrypt data...");
            q(c8, new a(c8, sharedPreferences, sharedPreferences2, yVar, zVar));
        } catch (Exception e8) {
            Log.e("FlutterSecureStorage", "Failed to initialize biometric migration with backup", e8);
            zVar.b(e8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T(SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2) {
        U(sharedPreferences, sharedPreferences2, this.f12172d);
    }

    private void U(SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2, v5.m mVar) {
        int i8 = 0;
        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            if (value instanceof String) {
                String str = (String) value;
                if (key.contains(this.f12169a.l())) {
                    sharedPreferences2.edit().putString(key, Base64.encodeToString(mVar.a(str.getBytes(f12168f)), 0)).apply();
                    sharedPreferences.edit().remove(key).apply();
                    i8++;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Migrated key: ");
                    sb.append(key.replaceFirst(this.f12169a.l() + '_', ""));
                    Log.d("FlutterSecureStorage", sb.toString());
                }
            }
        }
        Log.i("FlutterSecureStorage", "Migration complete: " + i8 + " items migrated from EncryptedSharedPreferences to custom cipher storage");
    }

    private void V(y yVar, SharedPreferences sharedPreferences, z zVar) {
        try {
            Log.d("FlutterSecureStorage", "Step 1/6: Decrypting all data with saved non-biometric cipher...");
            v5.m f8 = this.f12173e.f(this.f12170b, null);
            Map t8 = t(sharedPreferences, f8);
            Log.d("FlutterSecureStorage", "Step 2/6: Deleting old RSA key from Android KeyStore...");
            if (this.f12173e.a()) {
                try {
                    this.f12173e.e(this.f12170b).d();
                    f8.c(this.f12170b);
                    Log.d("FlutterSecureStorage", "Old key deleted from KeyStore");
                } catch (Exception e8) {
                    Log.w("FlutterSecureStorage", "Failed to delete old key from KeyStore (may not exist)", e8);
                }
            }
            Log.d("FlutterSecureStorage", "Step 3/6: Updating algorithm markers to current...");
            e0(yVar);
            Log.d("FlutterSecureStorage", "Step 4/6: Getting current biometric cipher...");
            Cipher c8 = this.f12173e.c(this.f12170b).c(this.f12170b);
            if (c8 == null) {
                throw new Exception("Failed to get current biometric cipher");
            }
            Log.i("FlutterSecureStorage", "Authenticating with NEW biometric cipher to encrypt data...");
            q(c8, new h(c8, t8, sharedPreferences, zVar));
        } catch (Exception e9) {
            Log.e("FlutterSecureStorage", "Failed to initialize biometric migration", e9);
            zVar.b(e9);
        }
    }

    private void W(y yVar, SharedPreferences sharedPreferences, z zVar) {
        try {
            SharedPreferences sharedPreferences2 = this.f12170b.getSharedPreferences("FlutterSecureKeyStorage", 0);
            if (!"complete".equals(x.h(yVar, this.f12169a))) {
                Log.i("FlutterSecureStorage", "Creating backup before non-biometric→biometric migration...");
                s sVar = this.f12169a;
                x.b(sharedPreferences, sharedPreferences2, yVar, sVar, sVar.l());
                Log.i("FlutterSecureStorage", "Backup created successfully");
            }
            Log.d("FlutterSecureStorage", "Step 1/7: Decrypting all data from _BACKUP with saved non-biometric cipher...");
            v5.m f8 = this.f12173e.f(this.f12170b, null);
            Map u7 = u(sharedPreferences, null, f8);
            Log.d("FlutterSecureStorage", "Step 2/7: Getting current biometric cipher...");
            Cipher c8 = this.f12173e.c(this.f12170b).c(this.f12170b);
            if (c8 == null) {
                throw new Exception("Failed to get current biometric cipher");
            }
            Log.i("FlutterSecureStorage", "Authenticating with NEW biometric cipher to encrypt data...");
            q(c8, new b(c8, u7, sharedPreferences, sharedPreferences2, yVar, f8, zVar));
        } catch (Exception e8) {
            Log.e("FlutterSecureStorage", "Failed to initialize biometric migration with backup", e8);
            zVar.b(e8);
        }
    }

    private void X(y yVar, SharedPreferences sharedPreferences, z zVar) {
        Log.i("FlutterSecureStorage", "Starting non-biometric migration (no authentication required)...");
        try {
            Log.d("FlutterSecureStorage", "Step 1/6: Initializing saved cipher...");
            v5.m f8 = this.f12173e.f(this.f12170b, null);
            Log.d("FlutterSecureStorage", "Step 2/6: Decrypting all data with saved cipher...");
            Map t8 = t(sharedPreferences, f8);
            Log.d("FlutterSecureStorage", "Step 3/6: Deleting old RSA key from Android KeyStore...");
            if (this.f12173e.a()) {
                try {
                    this.f12173e.e(this.f12170b).d();
                    f8.c(this.f12170b);
                    Log.d("FlutterSecureStorage", "Old key deleted from KeyStore");
                } catch (Exception e8) {
                    Log.w("FlutterSecureStorage", "Failed to delete old key from KeyStore (may not exist)", e8);
                }
            }
            Log.d("FlutterSecureStorage", "Step 4/6: Updating algorithm markers to current...");
            e0(yVar);
            Log.d("FlutterSecureStorage", "Step 5/6: Initializing current cipher with fresh AES key...");
            v5.m d8 = this.f12173e.d(this.f12170b, null);
            if (t8.isEmpty()) {
                Log.i("FlutterSecureStorage", "Step 6/6: No data to migrate, continuing...");
            } else {
                Log.d("FlutterSecureStorage", "Step 6/6: Encrypting all data with current cipher...");
                y(t8, sharedPreferences, d8);
            }
            this.f12172d = d8;
            Log.i("FlutterSecureStorage", "Non-biometric migration completed successfully! Migrated " + t8.size() + " items.");
            zVar.a(null);
        } catch (Exception e9) {
            Log.e("FlutterSecureStorage", "Non-biometric migration failed", e9);
            zVar.b(new Exception("Non-biometric migration failed", e9));
        }
    }

    private void Y(y yVar, SharedPreferences sharedPreferences, z zVar) {
        String str;
        v5.m mVar;
        Log.i("FlutterSecureStorage", "Starting non-biometric migration WITH BACKUP (rename operation)...");
        try {
            SharedPreferences sharedPreferences2 = this.f12170b.getSharedPreferences("FlutterSecureKeyStorage", 0);
            Log.d("FlutterSecureStorage", "Step 1/8: Creating backup (copy originals to _BACKUP, keep originals)...");
            if (this.f12173e.a()) {
                s sVar = this.f12169a;
                x.b(sharedPreferences, sharedPreferences2, yVar, sVar, sVar.l());
                str = "Backup step complete - originals preserved alongside _BACKUP copies";
            } else {
                str = "No algorithm change detected, skipping backup";
            }
            Log.i("FlutterSecureStorage", str);
            Log.d("FlutterSecureStorage", "Step 2/8: Restoring wrapped keys from _BACKUP and initializing saved cipher...");
            boolean i8 = x.i(yVar, this.f12169a.l());
            HashMap hashMap = new HashMap();
            if (i8) {
                for (Map.Entry<String, ?> entry : sharedPreferences2.getAll().entrySet()) {
                    String key = entry.getKey();
                    if (!key.endsWith("_BACKUP") && (entry.getValue() instanceof String)) {
                        hashMap.put(key, (String) entry.getValue());
                    }
                }
                Log.d("FlutterSecureStorage", "Step 2/8: _MIGRATED markers found — saved " + hashMap.size() + " new key entries; temporarily restoring _BACKUP blobs for savedCipher init");
            }
            SharedPreferences.Editor edit = sharedPreferences2.edit();
            for (Map.Entry<String, ?> entry2 : sharedPreferences2.getAll().entrySet()) {
                String key2 = entry2.getKey();
                if (key2.endsWith("_BACKUP") && (entry2.getValue() instanceof String)) {
                    edit.putString(key2.substring(0, key2.length() - 7), (String) entry2.getValue());
                }
            }
            edit.commit();
            v5.m f8 = this.f12173e.f(this.f12170b, null);
            if (i8 && !hashMap.isEmpty()) {
                SharedPreferences.Editor edit2 = sharedPreferences2.edit();
                for (Map.Entry entry3 : hashMap.entrySet()) {
                    edit2.putString((String) entry3.getKey(), (String) entry3.getValue());
                }
                edit2.commit();
                Log.d("FlutterSecureStorage", "Step 2/8: New wrapped key restored to keyStorage after savedCipher init");
            }
            Log.d("FlutterSecureStorage", "Step 3/8: Decrypting all data from _BACKUP keys...");
            Map u7 = u(sharedPreferences, null, f8);
            Log.d("FlutterSecureStorage", "Successfully decrypted " + u7.size() + " items from _BACKUP keys");
            Log.d("FlutterSecureStorage", "Step 4/8: Deleting original encrypted entries (preserving already-migrated)...");
            x.g(sharedPreferences, sharedPreferences2, yVar, this.f12169a.l());
            Log.i("FlutterSecureStorage", u7.isEmpty() ? "No data found to migrate" : "Found " + u7.size() + " items to migrate");
            Log.d("FlutterSecureStorage", "Step 5/8: Initializing current cipher with new algorithm...");
            v5.m d8 = this.f12173e.d(this.f12170b, null);
            if (u7.isEmpty()) {
                Log.i("FlutterSecureStorage", "Step 6/8: No data to encrypt, skipping...");
                mVar = d8;
            } else {
                Log.d("FlutterSecureStorage", "Step 6/8: Encrypting all data with current cipher (per-key tracking)...");
                mVar = d8;
                z(u7, sharedPreferences, yVar, d8, this.f12169a.l());
            }
            Log.d("FlutterSecureStorage", "Step 7/8: Checking for ESP data to migrate...");
            if (!B(yVar).booleanValue()) {
                try {
                    SharedPreferences G = G(this.f12170b);
                    if (E(G)) {
                        Log.i("FlutterSecureStorage", "Found ESP data - migrating to custom cipher storage...");
                        U(G, sharedPreferences, mVar);
                        d0(yVar);
                        Log.i("FlutterSecureStorage", "ESP migration completed successfully");
                    } else {
                        Log.d("FlutterSecureStorage", "No ESP data found");
                    }
                } catch (Exception e8) {
                    Log.w("FlutterSecureStorage", "ESP migration failed or ESP not available: " + e8.getMessage());
                }
            }
            Log.d("FlutterSecureStorage", "Step 8/8: Cleaning up - deleting _BACKUP, _MIGRATED markers, updating markers, deleting old keys...");
            s sVar2 = this.f12169a;
            x.d(sharedPreferences, sharedPreferences2, yVar, sVar2, sVar2.l());
            x.f(yVar, this.f12169a.l());
            e0(yVar);
            if (this.f12173e.a()) {
                try {
                    this.f12173e.e(this.f12170b).d();
                    f8.c(this.f12170b);
                    Log.d("FlutterSecureStorage", "Old RSA keys deleted from KeyStore");
                } catch (Exception e9) {
                    Log.w("FlutterSecureStorage", "Failed to delete old key from KeyStore (may not exist)", e9);
                }
            }
            this.f12172d = mVar;
            Log.i("FlutterSecureStorage", "Non-biometric migration WITH BACKUP completed successfully!");
            Log.i("FlutterSecureStorage", "Migrated " + u7.size() + " data items with new algorithm.");
            zVar.a(null);
        } catch (Exception e10) {
            Log.e("FlutterSecureStorage", "Non-biometric migration with backup failed", e10);
            zVar.b(new Exception("Non-biometric migration with backup failed", e10));
        }
    }

    private Map b0() {
        Map<String, ?> all = this.f12171c.getAll();
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            if (entry.getKey().contains(this.f12169a.l())) {
                hashMap.put(entry.getKey().replaceFirst(this.f12169a.l() + '_', ""), (!this.f12169a.s() || this.f12169a.u()) ? s((String) entry.getValue()) : (String) entry.getValue());
            }
        }
        return hashMap;
    }

    private String c0(String str) {
        String string = this.f12171c.getString(str, null);
        return (!this.f12169a.s() || this.f12169a.u()) ? s(string) : string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d0(y yVar) {
        SharedPreferences.Editor b8 = yVar.b();
        b8.putBoolean("ENCRYPTED_PREFERENCES_MIGRATED", true);
        b8.commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e0(y yVar) {
        SharedPreferences.Editor b8 = yVar.b();
        this.f12173e.i(b8);
        b8.commit();
        Log.d("FlutterSecureStorage", "Algorithm markers updated to current");
    }

    private void g0(String str, String str2) {
        SharedPreferences.Editor edit = this.f12171c.edit();
        if (!this.f12169a.s() || this.f12169a.u()) {
            str2 = Base64.encodeToString(this.f12172d.a(str2.getBytes(f12168f)), 0);
        }
        edit.putString(str, str2);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(Cipher cipher, z zVar) {
        BiometricPrompt.Builder title;
        BiometricPrompt.Builder subtitle;
        BiometricPrompt build;
        boolean f8 = this.f12169a.f();
        A(f8);
        int i8 = Build.VERSION.SDK_INT;
        if (i8 < 28) {
            if (f8) {
                throw new Exception("BIOMETRIC_UNAVAILABLE: Biometric authentication requires Android 9 (API 28) or higher");
            }
            return;
        }
        BiometricPrompt.CryptoObject a8 = u5.a.a(cipher);
        final CancellationSignal cancellationSignal = new CancellationSignal();
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        u5.e.a();
        title = u5.d.a(this.f12170b).setTitle(this.f12169a.b());
        subtitle = title.setSubtitle(this.f12169a.i());
        if (i8 >= 30) {
            subtitle.setAllowedAuthenticators(this.f12169a.r() ? 15 : 32783);
            if (this.f12169a.r()) {
                subtitle.setNegativeButton(this.f12169a.a(), newSingleThreadExecutor, new DialogInterface.OnClickListener() { // from class: u5.m
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        cancellationSignal.cancel();
                    }
                });
            }
        } else {
            subtitle.setNegativeButton(this.f12169a.a(), newSingleThreadExecutor, new DialogInterface.OnClickListener() { // from class: u5.n
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    cancellationSignal.cancel();
                }
            });
        }
        build = subtitle.build();
        build.authenticate(a8, cancellationSignal, newSingleThreadExecutor, new k(zVar));
    }

    private String s(String str) {
        if (str == null) {
            return null;
        }
        return new String(this.f12172d.b(Base64.decode(str, 0)), f12168f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map t(SharedPreferences sharedPreferences, v5.m mVar) {
        HashMap hashMap = new HashMap();
        int i8 = 0;
        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if ((value instanceof String) && key.contains(this.f12169a.l())) {
                try {
                    hashMap.put(key, new String(mVar.b(Base64.decode((String) value, 0)), f12168f));
                    i8++;
                } catch (Exception e8) {
                    Log.e("FlutterSecureStorage", "Failed to decrypt key: " + key, e8);
                    throw new Exception("Failed to decrypt existing data with saved cipher for key: " + key, e8);
                }
            }
        }
        Log.d("FlutterSecureStorage", "Successfully decrypted " + i8 + " items with saved cipher");
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.Map u(android.content.SharedPreferences r11, android.content.SharedPreferences r12, v5.m r13) {
        /*
            Method dump skipped, instructions count: 273
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: u5.p.u(android.content.SharedPreferences, android.content.SharedPreferences, v5.m):java.util.Map");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(y yVar, z zVar) {
        try {
            try {
                this.f12173e.c(this.f12170b).d();
                Log.i("FlutterSecureStorage", "Deleted key from AndroidKeyStore");
            } catch (Exception e8) {
                Log.w("FlutterSecureStorage", "Failed to delete key from AndroidKeyStore (may not exist)", e8);
            }
            this.f12170b.getSharedPreferences(this.f12169a.d(), 0).edit().clear().apply();
            Log.d("FlutterSecureStorage", "Deleted all encrypted data");
            this.f12170b.getSharedPreferences(this.f12169a.e(), 0).edit().clear().apply();
            Log.d("FlutterSecureStorage", "Deleted wrapped keys from SharedPreferences");
            SharedPreferences.Editor b8 = yVar.b();
            this.f12173e.i(b8);
            b8.apply();
            Log.d("FlutterSecureStorage", "Updated algorithm markers to current");
            Log.w("FlutterSecureStorage", "All data deleted. Reinitializing with new algorithm...");
            H(yVar, zVar);
        } catch (Exception e9) {
            Log.e("FlutterSecureStorage", "Failed to clean up after key mismatch", e9);
            zVar.b(e9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(Map map, SharedPreferences sharedPreferences, v5.m mVar) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        int i8 = 0;
        for (Map.Entry entry : map.entrySet()) {
            try {
                edit.putString((String) entry.getKey(), Base64.encodeToString(mVar.a(((String) entry.getValue()).getBytes(f12168f)), 0));
                i8++;
            } catch (Exception e8) {
                Log.e("FlutterSecureStorage", "Failed to encrypt key: " + ((String) entry.getKey()), e8);
                throw new Exception("Failed to encrypt data with current cipher for key: " + ((String) entry.getKey()), e8);
            }
        }
        if (!edit.commit()) {
            throw new Exception("Failed to commit encrypted data to disk - storage may be full or unavailable");
        }
        Log.d("FlutterSecureStorage", "Successfully encrypted and committed " + i8 + " items with current cipher to disk");
    }

    private void z(Map map, SharedPreferences sharedPreferences, y yVar, v5.m mVar, String str) {
        int i8 = 0;
        int i9 = 0;
        for (Map.Entry entry : map.entrySet()) {
            String str2 = (String) entry.getKey();
            String str3 = str2 + "_MIGRATED";
            if (yVar.a(str3)) {
                i9++;
                Log.d("FlutterSecureStorage", "Skipping already-migrated key: " + str2);
            } else {
                try {
                    String encodeToString = Base64.encodeToString(mVar.a(((String) entry.getValue()).getBytes(f12168f)), 0);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(str2, encodeToString);
                    if (!edit.commit()) {
                        throw new Exception("Failed to commit encrypted data for key: " + str2);
                    }
                    yVar.b().putBoolean(str3, true).commit();
                    i8++;
                } catch (Exception e8) {
                    Log.e("FlutterSecureStorage", "Failed to encrypt key: " + str2, e8);
                    throw new Exception("Failed to encrypt data with current cipher for key: " + str2, e8);
                }
            }
        }
        Log.d("FlutterSecureStorage", "Encrypted " + i8 + " items (skipped " + i9 + " already-migrated) with current cipher");
    }

    public void F(s sVar, z zVar) {
        if (this.f12171c != null) {
            zVar.a(null);
            return;
        }
        this.f12169a = sVar;
        SharedPreferences sharedPreferences = this.f12170b.getSharedPreferences(sVar.d(), 0);
        y yVar = new y(this.f12170b, sVar.d());
        Boolean B = B(yVar);
        if (!B.booleanValue() && !sVar.v()) {
            try {
                SharedPreferences G = G(this.f12170b);
                if (E(G)) {
                    Log.w("FlutterSecureStorage", "Found data in EncryptedSharedPreferences (deprecated)");
                    Log.w("FlutterSecureStorage", "EncryptedSharedPreferences is DEPRECATED and will be removed in a later version");
                    Log.w("FlutterSecureStorage", "The Jetpack Security library has been deprecated by Google.");
                    if (sVar.u()) {
                        Log.i("FlutterSecureStorage", "Migrating data from EncryptedSharedPreferences to custom cipher storage...");
                        if (sVar.s()) {
                            Log.w("FlutterSecureStorage", "Your data will be automatically migrated. You can safely remove encryptedSharedPreferences from your config after migration.");
                        }
                        Log.i("FlutterSecureStorage", "Migrating data from EncryptedSharedPreferences to selected custom cipher storage...");
                        H(yVar, new d(G, sharedPreferences, yVar, zVar));
                        return;
                    }
                    Log.w("FlutterSecureStorage", "Data found in EncryptedSharedPreferences, but migrateOnAlgorithmChange is set to false.");
                    Log.w("FlutterSecureStorage", "Set migrateOnAlgorithmChange=true to migrate to custom cipher storage.");
                    if (sVar.s()) {
                        Log.i("FlutterSecureStorage", "Using EncryptedSharedPreferences (migration disabled).");
                        this.f12171c = G;
                        zVar.a(null);
                        return;
                    }
                    Log.e("FlutterSecureStorage", "Data exists in EncryptedSharedPreferences but encryptedSharedPreferences=false and migrateOnAlgorithmChange=false.");
                    Log.e("FlutterSecureStorage", "Either set encryptedSharedPreferences=true to use the old data, or set migrateOnAlgorithmChange=true to migrate it.");
                    zVar.b(new Exception("EncryptedSharedPreferences data found but migration is disabled. Set migrateOnAlgorithmChange=true to migrate."));
                    return;
                }
                Log.d("FlutterSecureStorage", "No data found in EncryptedSharedPreferences.");
                if (sVar.s() && !sVar.u()) {
                    Log.w("FlutterSecureStorage", "Using EncryptedSharedPreferences (deprecated). Consider migrating to custom ciphers.");
                    this.f12171c = G;
                    zVar.a(null);
                    return;
                }
            } catch (Exception e8) {
                Log.e("FlutterSecureStorage", "EncryptedSharedPreferences initialization failed. Falling back to custom ciphers.", e8);
            }
        }
        if (this.f12171c == null) {
            if (sVar.s() && B.booleanValue()) {
                Log.i("FlutterSecureStorage", "Data already migrated, encryptedSharedPreferences ignored and can be safely removed.");
            }
            H(yVar, new e(sharedPreferences, zVar));
        }
    }

    public boolean J() {
        int canAuthenticate;
        if (Build.VERSION.SDK_INT >= 30) {
            BiometricManager a8 = u5.b.a(this.f12170b.getSystemService(l.a()));
            if (a8 == null) {
                return false;
            }
            canAuthenticate = a8.canAuthenticate(this.f12169a.r() ? 15 : 32783);
            return canAuthenticate == 0 && K();
        }
        return K();
    }

    public boolean K() {
        KeyguardManager keyguardManager = (KeyguardManager) this.f12170b.getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.isDeviceSecure();
    }

    public String Z(String str) {
        try {
            return c0(str);
        } catch (Exception e8) {
            if (D("read", str, e8)) {
                return c0(str);
            }
            throw e8;
        }
    }

    public Map a0() {
        try {
            return b0();
        } catch (Exception e8) {
            if (D("readAll", null, e8)) {
                return b0();
            }
            throw e8;
        }
    }

    public void f0(String str, String str2) {
        try {
            g0(str, str2);
        } catch (Exception e8) {
            if (!D("write", str, e8)) {
                throw e8;
            }
            g0(str, str2);
        }
    }

    public String p(String str) {
        return this.f12169a.l() + "_" + str;
    }

    public boolean r(String str) {
        return this.f12171c.contains(str);
    }

    public void v(String str) {
        SharedPreferences.Editor edit = this.f12171c.edit();
        edit.remove(str);
        edit.apply();
    }

    public void w() {
        SharedPreferences.Editor edit = this.f12171c.edit();
        edit.clear();
        edit.apply();
    }
}
