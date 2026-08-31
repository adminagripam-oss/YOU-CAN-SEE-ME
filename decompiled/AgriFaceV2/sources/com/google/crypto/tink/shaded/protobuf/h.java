package com.google.crypto.tink.shaded.protobuf;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
/* loaded from: classes.dex */
public abstract class h implements Iterable, Serializable {

    /* renamed from: f  reason: collision with root package name */
    public static final h f6072f = new j(z.f6295d);

    /* renamed from: g  reason: collision with root package name */
    private static final f f6073g;

    /* renamed from: h  reason: collision with root package name */
    private static final Comparator f6074h;

    /* renamed from: e  reason: collision with root package name */
    private int f6075e = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends c {

        /* renamed from: e  reason: collision with root package name */
        private int f6076e = 0;

        /* renamed from: f  reason: collision with root package name */
        private final int f6077f;

        a() {
            this.f6077f = h.this.size();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h.g
        public byte a() {
            int i8 = this.f6076e;
            if (i8 < this.f6077f) {
                this.f6076e = i8 + 1;
                return h.this.p(i8);
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f6076e < this.f6077f;
        }
    }

    /* loaded from: classes.dex */
    class b implements Comparator {
        b() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(h hVar, h hVar2) {
            g q8 = hVar.q();
            g q9 = hVar2.q();
            while (q8.hasNext() && q9.hasNext()) {
                int compare = Integer.compare(h.x(q8.a()), h.x(q9.a()));
                if (compare != 0) {
                    return compare;
                }
            }
            return Integer.compare(hVar.size(), hVar2.size());
        }
    }

    /* loaded from: classes.dex */
    static abstract class c implements g {
        c() {
        }

        @Override // java.util.Iterator
        /* renamed from: b */
        public final Byte next() {
            return Byte.valueOf(a());
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes.dex */
    private static final class d implements f {
        private d() {
        }

        /* synthetic */ d(a aVar) {
            this();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h.f
        public byte[] a(byte[] bArr, int i8, int i9) {
            return Arrays.copyOfRange(bArr, i8, i9 + i8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class e extends j {

        /* renamed from: j  reason: collision with root package name */
        private final int f6079j;

        /* renamed from: k  reason: collision with root package name */
        private final int f6080k;

        e(byte[] bArr, int i8, int i9) {
            super(bArr);
            h.k(i8, i8 + i9, bArr.length);
            this.f6079j = i8;
            this.f6080k = i9;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h.j
        protected int D() {
            return this.f6079j;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h.j, com.google.crypto.tink.shaded.protobuf.h
        public byte f(int i8) {
            h.j(i8, size());
            return this.f6083i[this.f6079j + i8];
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h.j, com.google.crypto.tink.shaded.protobuf.h
        protected void o(byte[] bArr, int i8, int i9, int i10) {
            System.arraycopy(this.f6083i, D() + i8, bArr, i9, i10);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h.j, com.google.crypto.tink.shaded.protobuf.h
        byte p(int i8) {
            return this.f6083i[this.f6079j + i8];
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h.j, com.google.crypto.tink.shaded.protobuf.h
        public int size() {
            return this.f6080k;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface f {
        byte[] a(byte[] bArr, int i8, int i9);
    }

    /* loaded from: classes.dex */
    public interface g extends Iterator {
        byte a();
    }

    /* renamed from: com.google.crypto.tink.shaded.protobuf.h$h  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static final class C0067h {

        /* renamed from: a  reason: collision with root package name */
        private final com.google.crypto.tink.shaded.protobuf.k f6081a;

        /* renamed from: b  reason: collision with root package name */
        private final byte[] f6082b;

        private C0067h(int i8) {
            byte[] bArr = new byte[i8];
            this.f6082b = bArr;
            this.f6081a = com.google.crypto.tink.shaded.protobuf.k.S(bArr);
        }

        public h a() {
            this.f6081a.c();
            return new j(this.f6082b);
        }

        public com.google.crypto.tink.shaded.protobuf.k b() {
            return this.f6081a;
        }

        /* synthetic */ C0067h(int i8, a aVar) {
            this(i8);
        }
    }

    /* loaded from: classes.dex */
    static abstract class i extends h {
        private i() {
        }

        /* synthetic */ i(a aVar) {
            this();
        }

        @Override // java.lang.Iterable
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return super.q();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class j extends i {

        /* renamed from: i  reason: collision with root package name */
        protected final byte[] f6083i;

        j(byte[] bArr) {
            super(null);
            bArr.getClass();
            this.f6083i = bArr;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h
        final void B(com.google.crypto.tink.shaded.protobuf.g gVar) {
            gVar.a(this.f6083i, D(), size());
        }

        final boolean C(h hVar, int i8, int i9) {
            if (i9 > hVar.size()) {
                throw new IllegalArgumentException("Length too large: " + i9 + size());
            }
            int i10 = i8 + i9;
            if (i10 > hVar.size()) {
                throw new IllegalArgumentException("Ran off end of other: " + i8 + ", " + i9 + ", " + hVar.size());
            } else if (hVar instanceof j) {
                j jVar = (j) hVar;
                byte[] bArr = this.f6083i;
                byte[] bArr2 = jVar.f6083i;
                int D = D() + i9;
                int D2 = D();
                int D3 = jVar.D() + i8;
                while (D2 < D) {
                    if (bArr[D2] != bArr2[D3]) {
                        return false;
                    }
                    D2++;
                    D3++;
                }
                return true;
            } else {
                return hVar.v(i8, i10).equals(v(0, i9));
            }
        }

        protected int D() {
            return 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if ((obj instanceof h) && size() == ((h) obj).size()) {
                if (size() == 0) {
                    return true;
                }
                if (obj instanceof j) {
                    j jVar = (j) obj;
                    int u7 = u();
                    int u8 = jVar.u();
                    if (u7 == 0 || u8 == 0 || u7 == u8) {
                        return C(jVar, 0, size());
                    }
                    return false;
                }
                return obj.equals(this);
            }
            return false;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h
        public byte f(int i8) {
            return this.f6083i[i8];
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h
        protected void o(byte[] bArr, int i8, int i9, int i10) {
            System.arraycopy(this.f6083i, i8, bArr, i9, i10);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h
        byte p(int i8) {
            return this.f6083i[i8];
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h
        public final com.google.crypto.tink.shaded.protobuf.i s() {
            return com.google.crypto.tink.shaded.protobuf.i.l(this.f6083i, D(), size(), true);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h
        public int size() {
            return this.f6083i.length;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h
        protected final int t(int i8, int i9, int i10) {
            return z.g(i8, this.f6083i, D() + i9, i10);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h
        public final h v(int i8, int i9) {
            int k8 = h.k(i8, i9, size());
            return k8 == 0 ? h.f6072f : new e(this.f6083i, D() + i8, k8);
        }
    }

    /* loaded from: classes.dex */
    private static final class k implements f {
        private k() {
        }

        /* synthetic */ k(a aVar) {
            this();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h.f
        public byte[] a(byte[] bArr, int i8, int i9) {
            byte[] bArr2 = new byte[i9];
            System.arraycopy(bArr, i8, bArr2, 0, i9);
            return bArr2;
        }
    }

    static {
        f6073g = com.google.crypto.tink.shaded.protobuf.d.c() ? new k(null) : new d(null);
        f6074h = new b();
    }

    h() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static h A(byte[] bArr, int i8, int i9) {
        return new e(bArr, i8, i9);
    }

    static void j(int i8, int i9) {
        if (((i9 - (i8 + 1)) | i8) < 0) {
            if (i8 < 0) {
                throw new ArrayIndexOutOfBoundsException("Index < 0: " + i8);
            }
            throw new ArrayIndexOutOfBoundsException("Index > length: " + i8 + ", " + i9);
        }
    }

    static int k(int i8, int i9, int i10) {
        int i11 = i9 - i8;
        if ((i8 | i9 | i11 | (i10 - i9)) < 0) {
            if (i8 < 0) {
                throw new IndexOutOfBoundsException("Beginning index: " + i8 + " < 0");
            } else if (i9 < i8) {
                throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i8 + ", " + i9);
            } else {
                throw new IndexOutOfBoundsException("End index: " + i9 + " >= " + i10);
            }
        }
        return i11;
    }

    public static h l(byte[] bArr) {
        return m(bArr, 0, bArr.length);
    }

    public static h m(byte[] bArr, int i8, int i9) {
        k(i8, i8 + i9, bArr.length);
        return new j(f6073g.a(bArr, i8, i9));
    }

    public static h n(String str) {
        return new j(str.getBytes(z.f6293b));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static C0067h r(int i8) {
        return new C0067h(i8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int x(byte b8) {
        return b8 & 255;
    }

    private String y() {
        if (size() <= 50) {
            return h1.a(this);
        }
        return h1.a(v(0, 47)) + "...";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static h z(byte[] bArr) {
        return new j(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void B(com.google.crypto.tink.shaded.protobuf.g gVar);

    public abstract boolean equals(Object obj);

    public abstract byte f(int i8);

    public final int hashCode() {
        int i8 = this.f6075e;
        if (i8 == 0) {
            int size = size();
            i8 = t(size, 0, size);
            if (i8 == 0) {
                i8 = 1;
            }
            this.f6075e = i8;
        }
        return i8;
    }

    protected abstract void o(byte[] bArr, int i8, int i9, int i10);

    abstract byte p(int i8);

    public g q() {
        return new a();
    }

    public abstract com.google.crypto.tink.shaded.protobuf.i s();

    public abstract int size();

    protected abstract int t(int i8, int i9, int i10);

    public final String toString() {
        return String.format(Locale.ROOT, "<ByteString@%s size=%d contents=\"%s\">", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()), y());
    }

    protected final int u() {
        return this.f6075e;
    }

    public abstract h v(int i8, int i9);

    public final byte[] w() {
        int size = size();
        if (size == 0) {
            return z.f6295d;
        }
        byte[] bArr = new byte[size];
        o(bArr, 0, 0, size);
        return bArr;
    }
}
