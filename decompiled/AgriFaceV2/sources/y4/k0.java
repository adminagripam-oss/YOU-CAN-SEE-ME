package y4;

import com.google.crypto.tink.shaded.protobuf.p0;
import com.google.crypto.tink.shaded.protobuf.q0;
import com.google.crypto.tink.shaded.protobuf.x;
import com.google.crypto.tink.shaded.protobuf.x0;
/* loaded from: classes.dex */
public final class k0 extends com.google.crypto.tink.shaded.protobuf.x implements q0 {
    private static final k0 DEFAULT_INSTANCE;
    public static final int KEY_VALUE_FIELD_NUMBER = 3;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile x0 PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private int bitField0_;
    private com.google.crypto.tink.shaded.protobuf.h keyValue_ = com.google.crypto.tink.shaded.protobuf.h.f6072f;
    private m0 params_;
    private int version_;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f13306a;

        static {
            int[] iArr = new int[x.d.values().length];
            f13306a = iArr;
            try {
                iArr[x.d.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13306a[x.d.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13306a[x.d.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f13306a[x.d.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f13306a[x.d.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f13306a[x.d.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f13306a[x.d.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends x.a implements q0 {
        private b() {
            super(k0.DEFAULT_INSTANCE);
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

        public b q(com.google.crypto.tink.shaded.protobuf.h hVar) {
            k();
            ((k0) this.f6278f).c0(hVar);
            return this;
        }

        public b r(m0 m0Var) {
            k();
            ((k0) this.f6278f).d0(m0Var);
            return this;
        }

        /* synthetic */ b(a aVar) {
            this();
        }
    }

    static {
        k0 k0Var = new k0();
        DEFAULT_INSTANCE = k0Var;
        com.google.crypto.tink.shaded.protobuf.x.Q(k0.class, k0Var);
    }

    private k0() {
    }

    public static b a0() {
        return (b) DEFAULT_INSTANCE.q();
    }

    public static k0 b0(com.google.crypto.tink.shaded.protobuf.h hVar, com.google.crypto.tink.shaded.protobuf.p pVar) {
        return (k0) com.google.crypto.tink.shaded.protobuf.x.K(DEFAULT_INSTANCE, hVar, pVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c0(com.google.crypto.tink.shaded.protobuf.h hVar) {
        hVar.getClass();
        this.keyValue_ = hVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d0(m0 m0Var) {
        m0Var.getClass();
        this.params_ = m0Var;
        this.bitField0_ |= 1;
    }

    public com.google.crypto.tink.shaded.protobuf.h X() {
        return this.keyValue_;
    }

    public m0 Y() {
        m0 m0Var = this.params_;
        return m0Var == null ? m0.W() : m0Var;
    }

    public int Z() {
        return this.version_;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.q0
    public /* bridge */ /* synthetic */ p0 c() {
        return super.v();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.p0
    public /* bridge */ /* synthetic */ p0.a e() {
        return super.H();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.x
    protected final Object s(x.d dVar, Object obj, Object obj2) {
        switch (a.f13306a[dVar.ordinal()]) {
            case 1:
                return new k0();
            case 2:
                return new b(null);
            case 3:
                return com.google.crypto.tink.shaded.protobuf.x.I(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"bitField0_", "version_", "params_", "keyValue_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                x0 x0Var = PARSER;
                if (x0Var == null) {
                    synchronized (k0.class) {
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
