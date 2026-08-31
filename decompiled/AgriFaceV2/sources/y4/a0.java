package y4;

import com.google.crypto.tink.shaded.protobuf.p0;
import com.google.crypto.tink.shaded.protobuf.q0;
import com.google.crypto.tink.shaded.protobuf.x;
import com.google.crypto.tink.shaded.protobuf.x0;
/* loaded from: classes.dex */
public final class a0 extends com.google.crypto.tink.shaded.protobuf.x implements q0 {
    private static final a0 DEFAULT_INSTANCE;
    public static final int OUTPUT_PREFIX_TYPE_FIELD_NUMBER = 3;
    private static volatile x0 PARSER = null;
    public static final int TYPE_URL_FIELD_NUMBER = 1;
    public static final int VALUE_FIELD_NUMBER = 2;
    private int outputPrefixType_;
    private String typeUrl_ = "";
    private com.google.crypto.tink.shaded.protobuf.h value_ = com.google.crypto.tink.shaded.protobuf.h.f6072f;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f13277a;

        static {
            int[] iArr = new int[x.d.values().length];
            f13277a = iArr;
            try {
                iArr[x.d.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13277a[x.d.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13277a[x.d.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f13277a[x.d.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f13277a[x.d.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f13277a[x.d.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f13277a[x.d.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends x.a implements q0 {
        private b() {
            super(a0.DEFAULT_INSTANCE);
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

        public b q(i0 i0Var) {
            k();
            ((a0) this.f6278f).f0(i0Var);
            return this;
        }

        public b r(String str) {
            k();
            ((a0) this.f6278f).g0(str);
            return this;
        }

        public b s(com.google.crypto.tink.shaded.protobuf.h hVar) {
            k();
            ((a0) this.f6278f).h0(hVar);
            return this;
        }

        /* synthetic */ b(a aVar) {
            this();
        }
    }

    static {
        a0 a0Var = new a0();
        DEFAULT_INSTANCE = a0Var;
        com.google.crypto.tink.shaded.protobuf.x.Q(a0.class, a0Var);
    }

    private a0() {
    }

    public static a0 Y() {
        return DEFAULT_INSTANCE;
    }

    public static b c0() {
        return (b) DEFAULT_INSTANCE.q();
    }

    public static b d0(a0 a0Var) {
        return (b) DEFAULT_INSTANCE.r(a0Var);
    }

    public static a0 e0(byte[] bArr, com.google.crypto.tink.shaded.protobuf.p pVar) {
        return (a0) com.google.crypto.tink.shaded.protobuf.x.M(DEFAULT_INSTANCE, bArr, pVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f0(i0 i0Var) {
        this.outputPrefixType_ = i0Var.f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g0(String str) {
        str.getClass();
        this.typeUrl_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h0(com.google.crypto.tink.shaded.protobuf.h hVar) {
        hVar.getClass();
        this.value_ = hVar;
    }

    public i0 Z() {
        i0 e8 = i0.e(this.outputPrefixType_);
        return e8 == null ? i0.UNRECOGNIZED : e8;
    }

    public String a0() {
        return this.typeUrl_;
    }

    public com.google.crypto.tink.shaded.protobuf.h b0() {
        return this.value_;
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
        switch (a.f13277a[dVar.ordinal()]) {
            case 1:
                return new a0();
            case 2:
                return new b(null);
            case 3:
                return com.google.crypto.tink.shaded.protobuf.x.I(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"typeUrl_", "value_", "outputPrefixType_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                x0 x0Var = PARSER;
                if (x0Var == null) {
                    synchronized (a0.class) {
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
