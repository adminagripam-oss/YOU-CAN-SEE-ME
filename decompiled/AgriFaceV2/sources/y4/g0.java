package y4;

import com.google.crypto.tink.shaded.protobuf.p0;
import com.google.crypto.tink.shaded.protobuf.q0;
import com.google.crypto.tink.shaded.protobuf.x;
import com.google.crypto.tink.shaded.protobuf.x0;
/* loaded from: classes.dex */
public final class g0 extends com.google.crypto.tink.shaded.protobuf.x implements q0 {
    private static final g0 DEFAULT_INSTANCE;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile x0 PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private int bitField0_;
    private h0 params_;
    private int version_;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f13289a;

        static {
            int[] iArr = new int[x.d.values().length];
            f13289a = iArr;
            try {
                iArr[x.d.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13289a[x.d.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13289a[x.d.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f13289a[x.d.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f13289a[x.d.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f13289a[x.d.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f13289a[x.d.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends x.a implements q0 {
        private b() {
            super(g0.DEFAULT_INSTANCE);
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

        public b q(h0 h0Var) {
            k();
            ((g0) this.f6278f).b0(h0Var);
            return this;
        }

        /* synthetic */ b(a aVar) {
            this();
        }
    }

    static {
        g0 g0Var = new g0();
        DEFAULT_INSTANCE = g0Var;
        com.google.crypto.tink.shaded.protobuf.x.Q(g0.class, g0Var);
    }

    private g0() {
    }

    public static b Y() {
        return (b) DEFAULT_INSTANCE.q();
    }

    public static g0 Z(com.google.crypto.tink.shaded.protobuf.h hVar, com.google.crypto.tink.shaded.protobuf.p pVar) {
        return (g0) com.google.crypto.tink.shaded.protobuf.x.K(DEFAULT_INSTANCE, hVar, pVar);
    }

    public static x0 a0() {
        return DEFAULT_INSTANCE.y();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b0(h0 h0Var) {
        h0Var.getClass();
        this.params_ = h0Var;
        this.bitField0_ |= 1;
    }

    public h0 W() {
        h0 h0Var = this.params_;
        return h0Var == null ? h0.X() : h0Var;
    }

    public int X() {
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
        switch (a.f13289a[dVar.ordinal()]) {
            case 1:
                return new g0();
            case 2:
                return new b(null);
            case 3:
                return com.google.crypto.tink.shaded.protobuf.x.I(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000", new Object[]{"bitField0_", "version_", "params_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                x0 x0Var = PARSER;
                if (x0Var == null) {
                    synchronized (g0.class) {
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
