package v2;

import java.util.Arrays;
import v2.f;
/* loaded from: classes.dex */
final class a extends f {

    /* renamed from: a  reason: collision with root package name */
    private final Iterable f12324a;

    /* renamed from: b  reason: collision with root package name */
    private final byte[] f12325b;

    /* loaded from: classes.dex */
    static final class b extends f.a {

        /* renamed from: a  reason: collision with root package name */
        private Iterable f12326a;

        /* renamed from: b  reason: collision with root package name */
        private byte[] f12327b;

        @Override // v2.f.a
        public f a() {
            String str = "";
            if (this.f12326a == null) {
                str = " events";
            }
            if (str.isEmpty()) {
                return new a(this.f12326a, this.f12327b);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // v2.f.a
        public f.a b(Iterable iterable) {
            if (iterable != null) {
                this.f12326a = iterable;
                return this;
            }
            throw new NullPointerException("Null events");
        }

        @Override // v2.f.a
        public f.a c(byte[] bArr) {
            this.f12327b = bArr;
            return this;
        }
    }

    private a(Iterable iterable, byte[] bArr) {
        this.f12324a = iterable;
        this.f12325b = bArr;
    }

    @Override // v2.f
    public Iterable b() {
        return this.f12324a;
    }

    @Override // v2.f
    public byte[] c() {
        return this.f12325b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            if (this.f12324a.equals(fVar.b())) {
                if (Arrays.equals(this.f12325b, fVar instanceof a ? ((a) fVar).f12325b : fVar.c())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return ((this.f12324a.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.f12325b);
    }

    public String toString() {
        return "BackendRequest{events=" + this.f12324a + ", extras=" + Arrays.toString(this.f12325b) + "}";
    }
}
