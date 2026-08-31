package y4;

import com.google.crypto.tink.shaded.protobuf.p0;
import com.google.crypto.tink.shaded.protobuf.q0;
import com.google.crypto.tink.shaded.protobuf.x;
import com.google.crypto.tink.shaded.protobuf.x0;
import com.google.crypto.tink.shaded.protobuf.z;
import java.io.InputStream;
import java.util.List;
import y4.y;
/* loaded from: classes.dex */
public final class c0 extends com.google.crypto.tink.shaded.protobuf.x implements q0 {
    private static final c0 DEFAULT_INSTANCE;
    public static final int KEY_FIELD_NUMBER = 2;
    private static volatile x0 PARSER = null;
    public static final int PRIMARY_KEY_ID_FIELD_NUMBER = 1;
    private z.d key_ = com.google.crypto.tink.shaded.protobuf.x.t();
    private int primaryKeyId_;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f13281a;

        static {
            int[] iArr = new int[x.d.values().length];
            f13281a = iArr;
            try {
                iArr[x.d.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13281a[x.d.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13281a[x.d.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f13281a[x.d.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f13281a[x.d.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f13281a[x.d.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f13281a[x.d.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends x.a implements q0 {
        private b() {
            super(c0.DEFAULT_INSTANCE);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.q0
        public /* bridge */ /* synthetic */ p0 c() {
            return super.m();
        }

        public /* bridge */ /* synthetic */ Object clone() {
            return super.j();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.p0.a
        public /* bridge */ /* synthetic */ p0 f() {
            return super.i();
        }

        public b q(c cVar) {
            k();
            ((c0) this.f6278f).X(cVar);
            return this;
        }

        public b r(int i8) {
            k();
            ((c0) this.f6278f).g0(i8);
            return this;
        }

        /* synthetic */ b(a aVar) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public static final class c extends com.google.crypto.tink.shaded.protobuf.x implements q0 {
        private static final c DEFAULT_INSTANCE;
        public static final int KEY_DATA_FIELD_NUMBER = 1;
        public static final int KEY_ID_FIELD_NUMBER = 3;
        public static final int OUTPUT_PREFIX_TYPE_FIELD_NUMBER = 4;
        private static volatile x0 PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 2;
        private int bitField0_;
        private y keyData_;
        private int keyId_;
        private int outputPrefixType_;
        private int status_;

        /* loaded from: classes.dex */
        public static final class a extends x.a implements q0 {
            private a() {
                super(c.DEFAULT_INSTANCE);
            }

            @Override // com.google.crypto.tink.shaded.protobuf.q0
            public /* bridge */ /* synthetic */ p0 c() {
                return super.m();
            }

            public /* bridge */ /* synthetic */ Object clone() {
                return super.j();
            }

            @Override // com.google.crypto.tink.shaded.protobuf.p0.a
            public /* bridge */ /* synthetic */ p0 f() {
                return super.i();
            }

            public a q(y.b bVar) {
                k();
                ((c) this.f6278f).f0((y) bVar.h());
                return this;
            }

            public a r(int i8) {
                k();
                ((c) this.f6278f).g0(i8);
                return this;
            }

            public a s(i0 i0Var) {
                k();
                ((c) this.f6278f).h0(i0Var);
                return this;
            }

            public a t(z zVar) {
                k();
                ((c) this.f6278f).i0(zVar);
                return this;
            }

            /* synthetic */ a(a aVar) {
                this();
            }
        }

        static {
            c cVar = new c();
            DEFAULT_INSTANCE = cVar;
            com.google.crypto.tink.shaded.protobuf.x.Q(c.class, cVar);
        }

        private c() {
        }

        public static a e0() {
            return (a) DEFAULT_INSTANCE.q();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void f0(y yVar) {
            yVar.getClass();
            this.keyData_ = yVar;
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g0(int i8) {
            this.keyId_ = i8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void h0(i0 i0Var) {
            this.outputPrefixType_ = i0Var.f();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void i0(z zVar) {
            this.status_ = zVar.f();
        }

        public y Z() {
            y yVar = this.keyData_;
            return yVar == null ? y.Y() : yVar;
        }

        public int a0() {
            return this.keyId_;
        }

        public i0 b0() {
            i0 e8 = i0.e(this.outputPrefixType_);
            return e8 == null ? i0.UNRECOGNIZED : e8;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.q0
        public /* bridge */ /* synthetic */ p0 c() {
            return super.v();
        }

        public z c0() {
            z e8 = z.e(this.status_);
            return e8 == null ? z.UNRECOGNIZED : e8;
        }

        public boolean d0() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.p0
        public /* bridge */ /* synthetic */ p0.a e() {
            return super.H();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.x
        protected final Object s(x.d dVar, Object obj, Object obj2) {
            switch (a.f13281a[dVar.ordinal()]) {
                case 1:
                    return new c();
                case 2:
                    return new a(null);
                case 3:
                    return com.google.crypto.tink.shaded.protobuf.x.I(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002\f\u0003\u000b\u0004\f", new Object[]{"bitField0_", "keyData_", "status_", "keyId_", "outputPrefixType_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    x0 x0Var = PARSER;
                    if (x0Var == null) {
                        synchronized (c.class) {
                            x0Var = PARSER;
                            if (x0Var == null) {
                                x0Var = new x.b(DEFAULT_INSTANCE);
                                PARSER = x0Var;
                            }
                        }
                    }
                    return x0Var;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    return (byte) 1;
                default:
                    throw null;
            }
        }
    }

    static {
        c0 c0Var = new c0();
        DEFAULT_INSTANCE = c0Var;
        com.google.crypto.tink.shaded.protobuf.x.Q(c0.class, c0Var);
    }

    private c0() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X(c cVar) {
        cVar.getClass();
        Y();
        this.key_.add(cVar);
    }

    private void Y() {
        z.d dVar = this.key_;
        if (dVar.i()) {
            return;
        }
        this.key_ = com.google.crypto.tink.shaded.protobuf.x.G(dVar);
    }

    public static b d0() {
        return (b) DEFAULT_INSTANCE.q();
    }

    public static c0 e0(InputStream inputStream, com.google.crypto.tink.shaded.protobuf.p pVar) {
        return (c0) com.google.crypto.tink.shaded.protobuf.x.L(DEFAULT_INSTANCE, inputStream, pVar);
    }

    public static c0 f0(byte[] bArr, com.google.crypto.tink.shaded.protobuf.p pVar) {
        return (c0) com.google.crypto.tink.shaded.protobuf.x.M(DEFAULT_INSTANCE, bArr, pVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g0(int i8) {
        this.primaryKeyId_ = i8;
    }

    public c Z(int i8) {
        return (c) this.key_.get(i8);
    }

    public int a0() {
        return this.key_.size();
    }

    public List b0() {
        return this.key_;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.q0
    public /* bridge */ /* synthetic */ p0 c() {
        return super.v();
    }

    public int c0() {
        return this.primaryKeyId_;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p0
    public /* bridge */ /* synthetic */ p0.a e() {
        return super.H();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.x
    protected final Object s(x.d dVar, Object obj, Object obj2) {
        switch (a.f13281a[dVar.ordinal()]) {
            case 1:
                return new c0();
            case 2:
                return new b(null);
            case 3:
                return com.google.crypto.tink.shaded.protobuf.x.I(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"primaryKeyId_", "key_", c.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                x0 x0Var = PARSER;
                if (x0Var == null) {
                    synchronized (c0.class) {
                        x0Var = PARSER;
                        if (x0Var == null) {
                            x0Var = new x.b(DEFAULT_INSTANCE);
                            PARSER = x0Var;
                        }
                    }
                }
                return x0Var;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                return (byte) 1;
            default:
                throw null;
        }
    }
}
