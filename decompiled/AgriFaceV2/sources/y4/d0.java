package y4;

import com.google.crypto.tink.shaded.protobuf.p0;
import com.google.crypto.tink.shaded.protobuf.q0;
import com.google.crypto.tink.shaded.protobuf.x;
import com.google.crypto.tink.shaded.protobuf.x0;
import com.google.crypto.tink.shaded.protobuf.z;
/* loaded from: classes.dex */
public final class d0 extends com.google.crypto.tink.shaded.protobuf.x implements q0 {
    private static final d0 DEFAULT_INSTANCE;
    public static final int KEY_INFO_FIELD_NUMBER = 2;
    private static volatile x0 PARSER = null;
    public static final int PRIMARY_KEY_ID_FIELD_NUMBER = 1;
    private z.d keyInfo_ = com.google.crypto.tink.shaded.protobuf.x.t();
    private int primaryKeyId_;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f13283a;

        static {
            int[] iArr = new int[x.d.values().length];
            f13283a = iArr;
            try {
                iArr[x.d.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13283a[x.d.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13283a[x.d.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f13283a[x.d.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f13283a[x.d.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f13283a[x.d.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f13283a[x.d.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends x.a implements q0 {
        private b() {
            super(d0.DEFAULT_INSTANCE);
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
            ((d0) this.f6278f).X(cVar);
            return this;
        }

        public b r(int i8) {
            k();
            ((d0) this.f6278f).a0(i8);
            return this;
        }

        /* synthetic */ b(a aVar) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public static final class c extends com.google.crypto.tink.shaded.protobuf.x implements q0 {
        private static final c DEFAULT_INSTANCE;
        public static final int KEY_ID_FIELD_NUMBER = 3;
        public static final int OUTPUT_PREFIX_TYPE_FIELD_NUMBER = 4;
        private static volatile x0 PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 2;
        public static final int TYPE_URL_FIELD_NUMBER = 1;
        private int keyId_;
        private int outputPrefixType_;
        private int status_;
        private String typeUrl_ = "";

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

            public a q(int i8) {
                k();
                ((c) this.f6278f).a0(i8);
                return this;
            }

            public a r(i0 i0Var) {
                k();
                ((c) this.f6278f).b0(i0Var);
                return this;
            }

            public a s(z zVar) {
                k();
                ((c) this.f6278f).c0(zVar);
                return this;
            }

            public a t(String str) {
                k();
                ((c) this.f6278f).d0(str);
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

        public static a Z() {
            return (a) DEFAULT_INSTANCE.q();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a0(int i8) {
            this.keyId_ = i8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b0(i0 i0Var) {
            this.outputPrefixType_ = i0Var.f();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c0(z zVar) {
            this.status_ = zVar.f();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d0(String str) {
            str.getClass();
            this.typeUrl_ = str;
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
            switch (a.f13283a[dVar.ordinal()]) {
                case 1:
                    return new c();
                case 2:
                    return new a(null);
                case 3:
                    return com.google.crypto.tink.shaded.protobuf.x.I(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", new Object[]{"typeUrl_", "status_", "keyId_", "outputPrefixType_"});
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
        d0 d0Var = new d0();
        DEFAULT_INSTANCE = d0Var;
        com.google.crypto.tink.shaded.protobuf.x.Q(d0.class, d0Var);
    }

    private d0() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X(c cVar) {
        cVar.getClass();
        Y();
        this.keyInfo_.add(cVar);
    }

    private void Y() {
        z.d dVar = this.keyInfo_;
        if (dVar.i()) {
            return;
        }
        this.keyInfo_ = com.google.crypto.tink.shaded.protobuf.x.G(dVar);
    }

    public static b Z() {
        return (b) DEFAULT_INSTANCE.q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a0(int i8) {
        this.primaryKeyId_ = i8;
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
        switch (a.f13283a[dVar.ordinal()]) {
            case 1:
                return new d0();
            case 2:
                return new b(null);
            case 3:
                return com.google.crypto.tink.shaded.protobuf.x.I(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"primaryKeyId_", "keyInfo_", c.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                x0 x0Var = PARSER;
                if (x0Var == null) {
                    synchronized (d0.class) {
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
