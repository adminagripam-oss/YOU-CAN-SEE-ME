package com.it_nomads.fluttersecurestorage.crypto;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;
import j4.f;
import j4.k;
import j4.l;
import j4.m;
import j4.x;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import s4.a;
/* loaded from: classes.dex */
public final class a implements SharedPreferences {

    /* renamed from: g  reason: collision with root package name */
    private static final Integer f6308g = 4;

    /* renamed from: h  reason: collision with root package name */
    private static final Integer f6309h = 8;

    /* renamed from: i  reason: collision with root package name */
    private static final Integer f6310i = 4;

    /* renamed from: j  reason: collision with root package name */
    private static final Integer f6311j = 1;

    /* renamed from: a  reason: collision with root package name */
    final SharedPreferences f6312a;

    /* renamed from: b  reason: collision with root package name */
    final CopyOnWriteArrayList f6313b = new CopyOnWriteArrayList();

    /* renamed from: c  reason: collision with root package name */
    final String f6314c;

    /* renamed from: d  reason: collision with root package name */
    final String f6315d;

    /* renamed from: e  reason: collision with root package name */
    final j4.a f6316e;

    /* renamed from: f  reason: collision with root package name */
    final f f6317f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.it_nomads.fluttersecurestorage.crypto.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C0070a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f6318a;

        static {
            int[] iArr = new int[c.values().length];
            f6318a = iArr;
            try {
                iArr[c.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6318a[c.INT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6318a[c.LONG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f6318a[c.FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f6318a[c.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f6318a[c.STRING_SET.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes.dex */
    private static final class b implements SharedPreferences.Editor {

        /* renamed from: a  reason: collision with root package name */
        private final a f6319a;

        /* renamed from: b  reason: collision with root package name */
        private final SharedPreferences.Editor f6320b;

        /* renamed from: d  reason: collision with root package name */
        private final AtomicBoolean f6322d = new AtomicBoolean(false);

        /* renamed from: c  reason: collision with root package name */
        private final List f6321c = new CopyOnWriteArrayList();

        b(a aVar, SharedPreferences.Editor editor) {
            this.f6319a = aVar;
            this.f6320b = editor;
        }

        private void a() {
            if (this.f6322d.getAndSet(false)) {
                for (String str : this.f6319a.getAll().keySet()) {
                    if (!this.f6321c.contains(str) && !this.f6319a.k(str)) {
                        this.f6320b.remove(this.f6319a.h(str));
                    }
                }
            }
        }

        private void b() {
            Iterator it = this.f6319a.f6313b.iterator();
            while (it.hasNext()) {
                SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = (SharedPreferences.OnSharedPreferenceChangeListener) it.next();
                for (String str : this.f6321c) {
                    onSharedPreferenceChangeListener.onSharedPreferenceChanged(this.f6319a, str);
                }
            }
        }

        private void c(String str, byte[] bArr) {
            if (this.f6319a.k(str)) {
                throw new SecurityException(str + " is a reserved key for the encryption keyset.");
            }
            this.f6321c.add(str);
            if (str == null) {
                str = "__NULL__";
            }
            try {
                Pair i8 = this.f6319a.i(str, bArr);
                this.f6320b.putString((String) i8.first, (String) i8.second);
            } catch (GeneralSecurityException e8) {
                throw new SecurityException("Could not encrypt data: " + e8.getMessage(), e8);
            }
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            a();
            this.f6320b.apply();
            b();
            this.f6321c.clear();
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            this.f6322d.set(true);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            a();
            try {
                return this.f6320b.commit();
            } finally {
                b();
                this.f6321c.clear();
            }
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z7) {
            ByteBuffer allocate = ByteBuffer.allocate(a.f6308g.intValue() + a.f6311j.intValue());
            allocate.putInt(c.BOOLEAN.f());
            allocate.put(z7 ? (byte) 1 : (byte) 0);
            c(str, allocate.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f8) {
            ByteBuffer allocate = ByteBuffer.allocate(a.f6308g.intValue() + a.f6310i.intValue());
            allocate.putInt(c.FLOAT.f());
            allocate.putFloat(f8);
            c(str, allocate.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i8) {
            ByteBuffer allocate = ByteBuffer.allocate(a.f6308g.intValue() + a.f6308g.intValue());
            allocate.putInt(c.INT.f());
            allocate.putInt(i8);
            c(str, allocate.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j8) {
            ByteBuffer allocate = ByteBuffer.allocate(a.f6308g.intValue() + a.f6309h.intValue());
            allocate.putInt(c.LONG.f());
            allocate.putLong(j8);
            c(str, allocate.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, String str2) {
            if (str2 == null) {
                str2 = "__NULL__";
            }
            byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            ByteBuffer allocate = ByteBuffer.allocate(a.f6308g.intValue() + a.f6308g.intValue() + length);
            allocate.putInt(c.STRING.f());
            allocate.putInt(length);
            allocate.put(bytes);
            c(str, allocate.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, Set set) {
            if (set == null) {
                set = new b1.b();
                set.add("__NULL__");
            }
            ArrayList<byte[]> arrayList = new ArrayList(set.size());
            int size = set.size() * a.f6308g.intValue();
            for (String str2 : set) {
                byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
                arrayList.add(bytes);
                size += bytes.length;
            }
            ByteBuffer allocate = ByteBuffer.allocate(size + a.f6308g.intValue());
            allocate.putInt(c.STRING_SET.f());
            for (byte[] bArr : arrayList) {
                allocate.putInt(bArr.length);
                allocate.put(bArr);
            }
            c(str, allocate.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String str) {
            if (!this.f6319a.k(str)) {
                this.f6320b.remove(this.f6319a.h(str));
                this.f6321c.add(str);
                return this;
            }
            throw new SecurityException(str + " is a reserved key for the encryption keyset.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum c {
        STRING(0),
        STRING_SET(1),
        INT(2),
        LONG(3),
        FLOAT(4),
        BOOLEAN(5);
        

        /* renamed from: e  reason: collision with root package name */
        private final int f6330e;

        c(int i8) {
            this.f6330e = i8;
        }

        public static c e(int i8) {
            if (i8 != 0) {
                if (i8 != 1) {
                    if (i8 != 2) {
                        if (i8 != 3) {
                            if (i8 != 4) {
                                if (i8 != 5) {
                                    return null;
                                }
                                return BOOLEAN;
                            }
                            return FLOAT;
                        }
                        return LONG;
                    }
                    return INT;
                }
                return STRING_SET;
            }
            return STRING;
        }

        public int f() {
            return this.f6330e;
        }
    }

    /* loaded from: classes.dex */
    public enum d {
        AES256_SIV("AES256_SIV");
        

        /* renamed from: e  reason: collision with root package name */
        private final String f6333e;

        d(String str) {
            this.f6333e = str;
        }

        k e() {
            return l.a(this.f6333e);
        }
    }

    /* loaded from: classes.dex */
    public enum e {
        AES256_GCM("AES256_GCM");
        

        /* renamed from: e  reason: collision with root package name */
        private final String f6336e;

        e(String str) {
            this.f6336e = str;
        }

        k e() {
            return l.a(this.f6336e);
        }
    }

    a(String str, String str2, SharedPreferences sharedPreferences, j4.a aVar, f fVar) {
        this.f6314c = str;
        this.f6312a = sharedPreferences;
        this.f6315d = str2;
        this.f6316e = aVar;
        this.f6317f = fVar;
    }

    public static SharedPreferences e(Context context, String str, com.it_nomads.fluttersecurestorage.crypto.b bVar, d dVar, e eVar) {
        return f(str, bVar.a(), context, dVar, eVar);
    }

    private static SharedPreferences f(String str, String str2, Context context, d dVar, e eVar) {
        p4.f.b();
        k4.a.c();
        Context applicationContext = context.getApplicationContext();
        a.b n8 = new a.b().l(dVar.e()).n(applicationContext, "__androidx_security_crypto_encrypted_prefs_key_keyset__", str);
        m e8 = n8.m("android-keystore://" + str2).f().e();
        a.b n9 = new a.b().l(eVar.e()).n(applicationContext, "__androidx_security_crypto_encrypted_prefs_value_keyset__", str);
        m e9 = n9.m("android-keystore://" + str2).f().e();
        f fVar = (f) e8.x(x.a(), f.class);
        return new a(str, str2, applicationContext.getSharedPreferences(str, 0), (j4.a) e9.x(x.a(), j4.a.class), fVar);
    }

    private Object j(String str) {
        if (k(str)) {
            throw new SecurityException(str + " is a reserved key for the encryption keyset.");
        }
        if (str == null) {
            str = "__NULL__";
        }
        try {
            String h8 = h(str);
            String string = this.f6312a.getString(h8, null);
            if (string == null) {
                return null;
            }
            ByteBuffer wrap = ByteBuffer.wrap(this.f6316e.b(z4.e.a(string, 0), h8.getBytes(StandardCharsets.UTF_8)));
            wrap.position(0);
            int i8 = wrap.getInt();
            c e8 = c.e(i8);
            if (e8 == null) {
                throw new SecurityException("Unknown type ID for encrypted pref value: " + i8);
            }
            switch (C0070a.f6318a[e8.ordinal()]) {
                case 1:
                    int i9 = wrap.getInt();
                    ByteBuffer slice = wrap.slice();
                    wrap.limit(i9);
                    String charBuffer = StandardCharsets.UTF_8.decode(slice).toString();
                    if (charBuffer.equals("__NULL__")) {
                        return null;
                    }
                    return charBuffer;
                case 2:
                    return Integer.valueOf(wrap.getInt());
                case 3:
                    return Long.valueOf(wrap.getLong());
                case 4:
                    return Float.valueOf(wrap.getFloat());
                case 5:
                    return Boolean.valueOf(wrap.get() != 0);
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    b1.b bVar = new b1.b();
                    while (wrap.hasRemaining()) {
                        int i10 = wrap.getInt();
                        ByteBuffer slice2 = wrap.slice();
                        slice2.limit(i10);
                        wrap.position(wrap.position() + i10);
                        bVar.add(StandardCharsets.UTF_8.decode(slice2).toString());
                    }
                    if (bVar.size() == 1 && "__NULL__".equals(bVar.o(0))) {
                        return null;
                    }
                    return bVar;
                default:
                    throw new SecurityException("Unhandled type for encrypted pref value: " + e8);
            }
        } catch (GeneralSecurityException e9) {
            throw new SecurityException("Could not decrypt value. " + e9.getMessage(), e9);
        }
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        if (!k(str)) {
            return this.f6312a.contains(h(str));
        }
        throw new SecurityException(str + " is a reserved key for the encryption keyset.");
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return new b(this, this.f6312a.edit());
    }

    String g(String str) {
        try {
            String str2 = new String(this.f6317f.b(z4.e.a(str, 0), this.f6314c.getBytes()), StandardCharsets.UTF_8);
            if (str2.equals("__NULL__")) {
                return null;
            }
            return str2;
        } catch (GeneralSecurityException e8) {
            throw new SecurityException("Could not decrypt key. " + e8.getMessage(), e8);
        }
    }

    @Override // android.content.SharedPreferences
    public Map getAll() {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, ?> entry : this.f6312a.getAll().entrySet()) {
            if (!k(entry.getKey())) {
                String g8 = g(entry.getKey());
                hashMap.put(g8, j(g8));
            }
        }
        return hashMap;
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z7) {
        Object j8 = j(str);
        return j8 instanceof Boolean ? ((Boolean) j8).booleanValue() : z7;
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f8) {
        Object j8 = j(str);
        return j8 instanceof Float ? ((Float) j8).floatValue() : f8;
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i8) {
        Object j8 = j(str);
        return j8 instanceof Integer ? ((Integer) j8).intValue() : i8;
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j8) {
        Object j9 = j(str);
        return j9 instanceof Long ? ((Long) j9).longValue() : j8;
    }

    @Override // android.content.SharedPreferences
    public String getString(String str, String str2) {
        Object j8 = j(str);
        return j8 instanceof String ? (String) j8 : str2;
    }

    @Override // android.content.SharedPreferences
    public Set getStringSet(String str, Set set) {
        Object j8 = j(str);
        Set bVar = j8 instanceof Set ? (Set) j8 : new b1.b();
        return !bVar.isEmpty() ? bVar : set;
    }

    String h(String str) {
        if (str == null) {
            str = "__NULL__";
        }
        try {
            return z4.e.d(this.f6317f.a(str.getBytes(StandardCharsets.UTF_8), this.f6314c.getBytes()));
        } catch (GeneralSecurityException e8) {
            throw new SecurityException("Could not encrypt key. " + e8.getMessage(), e8);
        }
    }

    Pair i(String str, byte[] bArr) {
        String h8 = h(str);
        return new Pair(h8, z4.e.d(this.f6316e.a(bArr, h8.getBytes(StandardCharsets.UTF_8))));
    }

    boolean k(String str) {
        return "__androidx_security_crypto_encrypted_prefs_key_keyset__".equals(str) || "__androidx_security_crypto_encrypted_prefs_value_keyset__".equals(str);
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.f6313b.add(onSharedPreferenceChangeListener);
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.f6313b.remove(onSharedPreferenceChangeListener);
    }
}
