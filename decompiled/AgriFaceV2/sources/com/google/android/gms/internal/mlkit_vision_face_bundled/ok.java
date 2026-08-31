package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public abstract class ok extends yi {
    private static final Map zzb = new ConcurrentHashMap();
    private int zzd = -1;
    protected km zzc = km.c();

    /* JADX INFO: Access modifiers changed from: protected */
    public static tk i() {
        return ul.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object j(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e8) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e8);
        } catch (InvocationTargetException e9) {
            Throwable cause = e9.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object k(ll llVar, String str, Object[] objArr) {
        return new vl(llVar, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void n(Class cls, ok okVar) {
        okVar.m();
        zzb.put(cls, okVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final boolean p(ok okVar, boolean z7) {
        byte byteValue = ((Byte) okVar.t(1, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean h8 = tl.a().b(okVar.getClass()).h(okVar);
        if (z7) {
            okVar.t(2, true != h8 ? null : okVar, null);
        }
        return h8;
    }

    private final int r(wl wlVar) {
        return tl.a().b(getClass()).b(this);
    }

    private static ok s(ok okVar, byte[] bArr, int i8, int i9, ak akVar) {
        if (i9 == 0) {
            return okVar;
        }
        ok y7 = okVar.y();
        try {
            wl b8 = tl.a().b(y7.getClass());
            b8.f(y7, bArr, 0, i9, new bj(akVar));
            b8.a(y7);
            return y7;
        } catch (im e8) {
            throw e8.a();
        } catch (wk e9) {
            throw e9;
        } catch (IOException e10) {
            if (e10.getCause() instanceof wk) {
                throw ((wk) e10.getCause());
            }
            throw new wk(e10);
        } catch (IndexOutOfBoundsException unused) {
            throw new wk("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    public static nk w(ll llVar, ll llVar2, qk qkVar, int i8, vm vmVar, boolean z7, Class cls) {
        return new nk(llVar, Collections.emptyList(), llVar2, new mk(null, 202056002, vmVar, true, false), cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ok x(Class cls) {
        Map map = zzb;
        ok okVar = (ok) map.get(cls);
        if (okVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                okVar = (ok) map.get(cls);
            } catch (ClassNotFoundException e8) {
                throw new IllegalStateException("Class initialization cannot fail.", e8);
            }
        }
        if (okVar == null) {
            okVar = (ok) ((ok) rm.j(cls)).t(6, null, null);
            if (okVar == null) {
                throw new IllegalStateException();
            }
            map.put(cls, okVar);
        }
        return okVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static ok z(ok okVar, byte[] bArr, ak akVar) {
        ok s8 = s(okVar, bArr, 0, bArr.length, akVar);
        if (s8 == null || p(s8, true)) {
            return s8;
        }
        throw new im(s8).a();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ll
    public final int a() {
        int i8;
        if (q()) {
            i8 = r(null);
            if (i8 < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + i8);
            }
        } else {
            i8 = this.zzd & Integer.MAX_VALUE;
            if (i8 == Integer.MAX_VALUE) {
                i8 = r(null);
                if (i8 < 0) {
                    throw new IllegalStateException("serialized size must be non-negative, was " + i8);
                }
                this.zzd = (this.zzd & Integer.MIN_VALUE) | i8;
            }
        }
        return i8;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ml
    public final /* synthetic */ ll c() {
        return (ok) t(6, null, null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ll
    public final /* synthetic */ kl d() {
        return (jk) t(5, null, null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ll
    public final void e(vj vjVar) {
        tl.a().b(getClass()).d(this, wj.L(vjVar));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return tl.a().b(getClass()).g(this, (ok) obj);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.yi
    final int f(wl wlVar) {
        if (q()) {
            int b8 = wlVar.b(this);
            if (b8 >= 0) {
                return b8;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + b8);
        }
        int i8 = this.zzd & Integer.MAX_VALUE;
        if (i8 == Integer.MAX_VALUE) {
            int b9 = wlVar.b(this);
            if (b9 >= 0) {
                this.zzd = (this.zzd & Integer.MIN_VALUE) | b9;
                return b9;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + b9);
        }
        return i8;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ml
    public final boolean g() {
        return p(this, true);
    }

    public final int hashCode() {
        if (q()) {
            return u();
        }
        int i8 = this.zza;
        if (i8 == 0) {
            int u7 = u();
            this.zza = u7;
            return u7;
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void l() {
        tl.a().b(getClass()).a(this);
        m();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void m() {
        this.zzd &= Integer.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void o(int i8) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean q() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object t(int i8, Object obj, Object obj2);

    public final String toString() {
        return nl.a(this, super.toString());
    }

    final int u() {
        return tl.a().b(getClass()).i(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final jk v() {
        return (jk) t(5, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ok y() {
        return (ok) t(4, null, null);
    }
}
