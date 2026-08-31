package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.a;
import com.google.crypto.tink.shaded.protobuf.e;
import com.google.crypto.tink.shaded.protobuf.z;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public abstract class x extends com.google.crypto.tink.shaded.protobuf.a {
    private static final int MEMOIZED_SERIALIZED_SIZE_MASK = Integer.MAX_VALUE;
    private static final int MUTABLE_FLAG_MASK = Integer.MIN_VALUE;
    static final int UNINITIALIZED_HASH_CODE = 0;
    static final int UNINITIALIZED_SERIALIZED_SIZE = Integer.MAX_VALUE;
    private static Map<Class<?>, x> defaultInstanceMap = new ConcurrentHashMap();
    private int memoizedSerializedSize = -1;
    protected k1 unknownFields = k1.c();

    /* loaded from: classes.dex */
    public static abstract class a extends a.AbstractC0066a {

        /* renamed from: e  reason: collision with root package name */
        private final x f6277e;

        /* renamed from: f  reason: collision with root package name */
        protected x f6278f;

        /* JADX INFO: Access modifiers changed from: protected */
        public a(x xVar) {
            this.f6277e = xVar;
            if (xVar.D()) {
                throw new IllegalArgumentException("Default instance must be immutable.");
            }
            this.f6278f = p();
        }

        private static void o(Object obj, Object obj2) {
            z0.a().d(obj).a(obj, obj2);
        }

        private x p() {
            return this.f6277e.J();
        }

        public final x h() {
            x i8 = i();
            if (i8.B()) {
                return i8;
            }
            throw a.AbstractC0066a.g(i8);
        }

        public x i() {
            if (this.f6278f.D()) {
                this.f6278f.E();
                return this.f6278f;
            }
            return this.f6278f;
        }

        public a j() {
            a H = m().H();
            H.f6278f = i();
            return H;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final void k() {
            if (this.f6278f.D()) {
                return;
            }
            l();
        }

        protected void l() {
            x p8 = p();
            o(p8, this.f6278f);
            this.f6278f = p8;
        }

        public x m() {
            return this.f6277e;
        }

        public a n(x xVar) {
            if (m().equals(xVar)) {
                return this;
            }
            k();
            o(this.f6278f, xVar);
            return this;
        }
    }

    /* loaded from: classes.dex */
    protected static class b extends com.google.crypto.tink.shaded.protobuf.b {

        /* renamed from: b  reason: collision with root package name */
        private final x f6279b;

        public b(x xVar) {
            this.f6279b = xVar;
        }
    }

    /* loaded from: classes.dex */
    public static class c extends n {
    }

    /* loaded from: classes.dex */
    public enum d {
        GET_MEMOIZED_IS_INITIALIZED,
        SET_MEMOIZED_IS_INITIALIZED,
        BUILD_MESSAGE_INFO,
        NEW_MUTABLE_INSTANCE,
        NEW_BUILDER,
        GET_DEFAULT_INSTANCE,
        GET_PARSER
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object A(Method method, Object obj, Object... objArr) {
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

    private static final boolean C(x xVar, boolean z7) {
        byte byteValue = ((Byte) xVar.s(d.GET_MEMOIZED_IS_INITIALIZED, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean f8 = z0.a().d(xVar).f(xVar);
        if (z7) {
            xVar.s(d.SET_MEMOIZED_IS_INITIALIZED, f8 ? xVar : null, null);
        }
        return f8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static z.d G(z.d dVar) {
        return dVar.e(dVar.size() * 2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object I(p0 p0Var, String str, Object[] objArr) {
        return new b1(p0Var, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static x K(x xVar, h hVar, p pVar) {
        return l(N(xVar, hVar, pVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static x L(x xVar, InputStream inputStream, p pVar) {
        return l(O(xVar, i.h(inputStream), pVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static x M(x xVar, byte[] bArr, p pVar) {
        return l(P(xVar, bArr, 0, bArr.length, pVar));
    }

    private static x N(x xVar, h hVar, p pVar) {
        i s8 = hVar.s();
        x O = O(xVar, s8, pVar);
        try {
            s8.a(0);
            return O;
        } catch (a0 e8) {
            throw e8.k(O);
        }
    }

    static x O(x xVar, i iVar, p pVar) {
        x J = xVar.J();
        try {
            d1 d8 = z0.a().d(J);
            d8.d(J, j.O(iVar), pVar);
            d8.e(J);
            return J;
        } catch (a0 e8) {
            e = e8;
            if (e.a()) {
                e = new a0(e);
            }
            throw e.k(J);
        } catch (i1 e9) {
            throw e9.a().k(J);
        } catch (IOException e10) {
            if (e10.getCause() instanceof a0) {
                throw ((a0) e10.getCause());
            }
            throw new a0(e10).k(J);
        } catch (RuntimeException e11) {
            if (e11.getCause() instanceof a0) {
                throw ((a0) e11.getCause());
            }
            throw e11;
        }
    }

    private static x P(x xVar, byte[] bArr, int i8, int i9, p pVar) {
        if (i9 == 0) {
            return xVar;
        }
        x J = xVar.J();
        try {
            d1 d8 = z0.a().d(J);
            d8.b(J, bArr, i8, i8 + i9, new e.a(pVar));
            d8.e(J);
            return J;
        } catch (a0 e8) {
            e = e8;
            if (e.a()) {
                e = new a0(e);
            }
            throw e.k(J);
        } catch (i1 e9) {
            throw e9.a().k(J);
        } catch (IOException e10) {
            if (e10.getCause() instanceof a0) {
                throw ((a0) e10.getCause());
            }
            throw new a0(e10).k(J);
        } catch (IndexOutOfBoundsException unused) {
            throw a0.m().k(J);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void Q(Class cls, x xVar) {
        xVar.F();
        defaultInstanceMap.put(cls, xVar);
    }

    private static x l(x xVar) {
        if (xVar == null || xVar.B()) {
            return xVar;
        }
        throw xVar.h().a().k(xVar);
    }

    private int p(d1 d1Var) {
        return d1Var == null ? z0.a().d(this).h(this) : d1Var.h(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static z.d t() {
        return a1.j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static x u(Class cls) {
        x xVar = defaultInstanceMap.get(cls);
        if (xVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                xVar = defaultInstanceMap.get(cls);
            } catch (ClassNotFoundException e8) {
                throw new IllegalStateException("Class initialization cannot fail.", e8);
            }
        }
        if (xVar == null) {
            xVar = ((x) m1.k(cls)).v();
            if (xVar == null) {
                throw new IllegalStateException();
            }
            defaultInstanceMap.put(cls, xVar);
        }
        return xVar;
    }

    public final boolean B() {
        return C(this, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean D() {
        return (this.memoizedSerializedSize & MUTABLE_FLAG_MASK) != 0;
    }

    protected void E() {
        z0.a().d(this).e(this);
        F();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void F() {
        this.memoizedSerializedSize &= Integer.MAX_VALUE;
    }

    public final a H() {
        return (a) s(d.NEW_BUILDER, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public x J() {
        return (x) s(d.NEW_MUTABLE_INSTANCE, null, null);
    }

    void R(int i8) {
        this.memoizedHashCode = i8;
    }

    void S(int i8) {
        if (i8 >= 0) {
            this.memoizedSerializedSize = (i8 & Integer.MAX_VALUE) | (this.memoizedSerializedSize & MUTABLE_FLAG_MASK);
            return;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + i8);
    }

    public final a T() {
        return ((a) s(d.NEW_BUILDER, null, null)).n(this);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p0
    public int a() {
        return b(null);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.a
    int b(d1 d1Var) {
        if (!D()) {
            if (x() != Integer.MAX_VALUE) {
                return x();
            }
            int p8 = p(d1Var);
            S(p8);
            return p8;
        }
        int p9 = p(d1Var);
        if (p9 >= 0) {
            return p9;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + p9);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p0
    public void d(k kVar) {
        z0.a().d(this).c(this, l.P(kVar));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return z0.a().d(this).g(this, (x) obj);
        }
        return false;
    }

    public int hashCode() {
        if (D()) {
            return o();
        }
        if (z()) {
            R(o());
        }
        return w();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object k() {
        return s(d.BUILD_MESSAGE_INFO, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m() {
        this.memoizedHashCode = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n() {
        S(Integer.MAX_VALUE);
    }

    int o() {
        return z0.a().d(this).j(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final a q() {
        return (a) s(d.NEW_BUILDER, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final a r(x xVar) {
        return q().n(xVar);
    }

    protected abstract Object s(d dVar, Object obj, Object obj2);

    public String toString() {
        return r0.f(this, super.toString());
    }

    public final x v() {
        return (x) s(d.GET_DEFAULT_INSTANCE, null, null);
    }

    int w() {
        return this.memoizedHashCode;
    }

    int x() {
        return this.memoizedSerializedSize & Integer.MAX_VALUE;
    }

    public final x0 y() {
        return (x0) s(d.GET_PARSER, null, null);
    }

    boolean z() {
        return w() == 0;
    }
}
