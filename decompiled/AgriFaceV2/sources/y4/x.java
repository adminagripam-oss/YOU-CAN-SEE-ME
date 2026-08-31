package y4;

import com.google.crypto.tink.shaded.protobuf.p0;
import com.google.crypto.tink.shaded.protobuf.q0;
import com.google.crypto.tink.shaded.protobuf.x;
import com.google.crypto.tink.shaded.protobuf.x0;
/* loaded from: classes.dex */
public final class x extends com.google.crypto.tink.shaded.protobuf.x implements q0 {
    private static final x DEFAULT_INSTANCE;
    public static final int HASH_FIELD_NUMBER = 1;
    private static volatile x0 PARSER = null;
    public static final int TAG_SIZE_FIELD_NUMBER = 2;
    private int hash_;
    private int tagSize_;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f13332a;

        static {
            int[] iArr = new int[x.d.values().length];
            f13332a = iArr;
            try {
                iArr[x.d.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13332a[x.d.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13332a[x.d.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f13332a[x.d.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f13332a[x.d.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f13332a[x.d.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f13332a[x.d.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends x.a implements q0 {
        private b() {
            super(x.DEFAULT_INSTANCE);
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

        public b q(u uVar) {
            k();
            ((x) this.f6278f).b0(uVar);
            return this;
        }

        public b r(int i8) {
            k();
            ((x) this.f6278f).c0(i8);
            return this;
        }

        /* synthetic */ b(a aVar) {
            this();
        }
    }

    static {
        x xVar = new x();
        DEFAULT_INSTANCE = xVar;
        com.google.crypto.tink.shaded.protobuf.x.Q(x.class, xVar);
    }

    private x() {
    }

    public static x X() {
        return DEFAULT_INSTANCE;
    }

    public static b a0() {
        return (b) DEFAULT_INSTANCE.q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b0(u uVar) {
        this.hash_ = uVar.f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c0(int i8) {
        this.tagSize_ = i8;
    }

    public u Y() {
        u e8 = u.e(this.hash_);
        return e8 == null ? u.UNRECOGNIZED : e8;
    }

    public int Z() {
        return this.tagSize_;
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
        switch (a.f13332a[dVar.ordinal()]) {
            case 1:
                return new x();
            case 2:
                return new b(null);
            case 3:
                return com.google.crypto.tink.shaded.protobuf.x.I(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000b", new Object[]{"hash_", "tagSize_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                x0 x0Var = PARSER;
                if (x0Var == null) {
                    synchronized (x.class) {
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
