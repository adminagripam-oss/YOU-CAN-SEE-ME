package y4;

import com.google.crypto.tink.shaded.protobuf.p0;
import com.google.crypto.tink.shaded.protobuf.q0;
import com.google.crypto.tink.shaded.protobuf.x;
import com.google.crypto.tink.shaded.protobuf.x0;
/* loaded from: classes.dex */
public final class d extends com.google.crypto.tink.shaded.protobuf.x implements q0 {
    public static final int AES_CTR_KEY_FIELD_NUMBER = 2;
    private static final d DEFAULT_INSTANCE;
    public static final int HMAC_KEY_FIELD_NUMBER = 3;
    private static volatile x0 PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private f aesCtrKey_;
    private int bitField0_;
    private v hmacKey_;
    private int version_;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f13282a;

        static {
            int[] iArr = new int[x.d.values().length];
            f13282a = iArr;
            try {
                iArr[x.d.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13282a[x.d.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13282a[x.d.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f13282a[x.d.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f13282a[x.d.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f13282a[x.d.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f13282a[x.d.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends x.a implements q0 {
        private b() {
            super(d.DEFAULT_INSTANCE);
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

        public b q(f fVar) {
            k();
            ((d) this.f6278f).d0(fVar);
            return this;
        }

        public b r(v vVar) {
            k();
            ((d) this.f6278f).e0(vVar);
            return this;
        }

        /* synthetic */ b(a aVar) {
            this();
        }
    }

    static {
        d dVar = new d();
        DEFAULT_INSTANCE = dVar;
        com.google.crypto.tink.shaded.protobuf.x.Q(d.class, dVar);
    }

    private d() {
    }

    public static b a0() {
        return (b) DEFAULT_INSTANCE.q();
    }

    public static d b0(com.google.crypto.tink.shaded.protobuf.h hVar, com.google.crypto.tink.shaded.protobuf.p pVar) {
        return (d) com.google.crypto.tink.shaded.protobuf.x.K(DEFAULT_INSTANCE, hVar, pVar);
    }

    public static x0 c0() {
        return DEFAULT_INSTANCE.y();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d0(f fVar) {
        fVar.getClass();
        this.aesCtrKey_ = fVar;
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e0(v vVar) {
        vVar.getClass();
        this.hmacKey_ = vVar;
        this.bitField0_ |= 2;
    }

    public f X() {
        f fVar = this.aesCtrKey_;
        return fVar == null ? f.X() : fVar;
    }

    public v Y() {
        v vVar = this.hmacKey_;
        return vVar == null ? v.X() : vVar;
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
        switch (a.f13282a[dVar.ordinal()]) {
            case 1:
                return new d();
            case 2:
                return new b(null);
            case 3:
                return com.google.crypto.tink.shaded.protobuf.x.I(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003ဉ\u0001", new Object[]{"bitField0_", "version_", "aesCtrKey_", "hmacKey_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                x0 x0Var = PARSER;
                if (x0Var == null) {
                    synchronized (d.class) {
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
