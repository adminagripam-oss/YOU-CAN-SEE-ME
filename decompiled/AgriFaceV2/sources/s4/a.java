package s4;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;
import j4.g;
import j4.k;
import j4.m;
import j4.p;
import j4.r;
import j4.u;
import j4.z;
import java.io.CharConversionException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.ProviderException;
import y4.a0;
/* loaded from: classes.dex */
public final class a {

    /* renamed from: d  reason: collision with root package name */
    private static final Object f11562d = new Object();

    /* renamed from: e  reason: collision with root package name */
    private static final String f11563e = "a";

    /* renamed from: a  reason: collision with root package name */
    private final r f11564a;

    /* renamed from: b  reason: collision with root package name */
    private final j4.a f11565b;

    /* renamed from: c  reason: collision with root package name */
    private p f11566c;

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private Context f11567a = null;

        /* renamed from: b  reason: collision with root package name */
        private String f11568b = null;

        /* renamed from: c  reason: collision with root package name */
        private String f11569c = null;

        /* renamed from: d  reason: collision with root package name */
        private String f11570d = null;

        /* renamed from: e  reason: collision with root package name */
        private j4.a f11571e = null;

        /* renamed from: f  reason: collision with root package name */
        private boolean f11572f = true;

        /* renamed from: g  reason: collision with root package name */
        private k f11573g = null;

        /* renamed from: h  reason: collision with root package name */
        private a0 f11574h = null;

        /* renamed from: i  reason: collision with root package name */
        private p f11575i;

        private p g() {
            k kVar = this.f11573g;
            if (kVar != null) {
                m q8 = m.q(kVar);
                a.g(q8, new e(this.f11567a, this.f11568b, this.f11569c), this.f11571e);
                return p.b(q8);
            }
            throw new GeneralSecurityException("cannot read or generate keyset");
        }

        private static byte[] h(Context context, String str, String str2) {
            if (str != null) {
                Context applicationContext = context.getApplicationContext();
                try {
                    String string = (str2 == null ? PreferenceManager.getDefaultSharedPreferences(applicationContext) : applicationContext.getSharedPreferences(str2, 0)).getString(str, null);
                    if (string == null) {
                        return null;
                    }
                    return z4.k.a(string);
                } catch (ClassCastException | IllegalArgumentException unused) {
                    throw new CharConversionException(String.format("can't read keyset; the pref value %s is not a valid hex string", str));
                }
            }
            throw new IllegalArgumentException("keysetName cannot be null");
        }

        private p i(byte[] bArr) {
            return p.b(u.b(j4.c.c(bArr), g.a()));
        }

        private p j(byte[] bArr) {
            try {
                this.f11571e = new d().a(this.f11570d);
                try {
                    return p.b(u.a(j4.c.c(bArr), this.f11571e, new byte[0]));
                } catch (IOException | GeneralSecurityException e8) {
                    try {
                        return i(bArr);
                    } catch (IOException unused) {
                        throw e8;
                    }
                }
            } catch (GeneralSecurityException | ProviderException e9) {
                try {
                    p i8 = i(bArr);
                    Log.w(a.f11563e, "cannot use Android Keystore, it'll be disabled", e9);
                    return i8;
                } catch (IOException unused2) {
                    throw e9;
                }
            }
        }

        private j4.a k() {
            if (!a.b()) {
                Log.w(a.f11563e, "Android Keystore requires at least Android M");
                return null;
            }
            d dVar = new d();
            try {
                boolean d8 = d.d(this.f11570d);
                try {
                    return dVar.a(this.f11570d);
                } catch (GeneralSecurityException | ProviderException e8) {
                    if (d8) {
                        Log.w(a.f11563e, "cannot use Android Keystore, it'll be disabled", e8);
                        return null;
                    }
                    throw new KeyStoreException(String.format("the master key %s exists but is unusable", this.f11570d), e8);
                }
            } catch (GeneralSecurityException | ProviderException e9) {
                Log.w(a.f11563e, "cannot use Android Keystore, it'll be disabled", e9);
                return null;
            }
        }

        public synchronized a f() {
            p i8;
            a aVar;
            if (this.f11568b == null) {
                throw new IllegalArgumentException("keysetName cannot be null");
            }
            a0 a0Var = this.f11574h;
            if (a0Var != null && this.f11573g == null) {
                this.f11573g = k.a(z.a(a0Var.i()));
            }
            synchronized (a.f11562d) {
                byte[] h8 = h(this.f11567a, this.f11568b, this.f11569c);
                if (h8 == null) {
                    if (this.f11570d != null) {
                        this.f11571e = k();
                    }
                    i8 = g();
                } else {
                    if (this.f11570d != null && a.b()) {
                        i8 = j(h8);
                    }
                    i8 = i(h8);
                }
                this.f11575i = i8;
                aVar = new a(this);
            }
            return aVar;
        }

        public b l(k kVar) {
            this.f11573g = kVar;
            return this;
        }

        public b m(String str) {
            if (str.startsWith("android-keystore://")) {
                if (this.f11572f) {
                    this.f11570d = str;
                    return this;
                }
                throw new IllegalArgumentException("cannot call withMasterKeyUri() after calling doNotUseKeystore()");
            }
            throw new IllegalArgumentException("key URI must start with android-keystore://");
        }

        public b n(Context context, String str, String str2) {
            if (context != null) {
                if (str != null) {
                    this.f11567a = context;
                    this.f11568b = str;
                    this.f11569c = str2;
                    return this;
                }
                throw new IllegalArgumentException("need a keyset name");
            }
            throw new IllegalArgumentException("need an Android context");
        }
    }

    private a(b bVar) {
        this.f11564a = new e(bVar.f11567a, bVar.f11568b, bVar.f11569c);
        this.f11565b = bVar.f11571e;
        this.f11566c = bVar.f11575i;
    }

    static /* synthetic */ boolean b() {
        return f();
    }

    private static boolean f() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g(m mVar, r rVar, j4.a aVar) {
        try {
            if (aVar != null) {
                u.c(mVar, rVar, aVar, new byte[0]);
            } else {
                u.d(mVar, rVar, g.a());
            }
        } catch (IOException e8) {
            throw new GeneralSecurityException(e8);
        }
    }

    public synchronized m e() {
        return this.f11566c.a();
    }
}
