package m6;

import m6.t0;
/* loaded from: classes.dex */
public class o0 implements t0.b0 {

    /* renamed from: a  reason: collision with root package name */
    private final v4 f9391a;

    /* renamed from: b  reason: collision with root package name */
    private final b f9392b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9393a;

        static {
            int[] iArr = new int[t0.b2.values().length];
            f9393a = iArr;
            try {
                iArr[t0.b2.HIGHER_QUALITY_OR_LOWER_THAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9393a[t0.b2.HIGHER_QUALITY_THAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9393a[t0.b2.LOWER_QUALITY_OR_HIGHER_THAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9393a[t0.b2.LOWER_QUALITY_THAN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        public r0.p a(t0.x1 x1Var, t0.b2 b2Var) {
            r0.v i8 = a6.i(x1Var);
            int i9 = a.f9393a[b2Var.ordinal()];
            if (i9 != 1) {
                if (i9 != 2) {
                    if (i9 != 3) {
                        if (i9 == 4) {
                            return r0.p.d(i8);
                        }
                        throw new IllegalArgumentException("Specified fallback rule " + b2Var + " unrecognized.");
                    }
                    return r0.p.c(i8);
                }
                return r0.p.b(i8);
            }
            return r0.p.a(i8);
        }
    }

    public o0(v4 v4Var) {
        this(v4Var, new b());
    }

    @Override // m6.t0.b0
    public void b(Long l8, t0.x1 x1Var, t0.b2 b2Var) {
        this.f9391a.a(this.f9392b.a(x1Var, b2Var), l8.longValue());
    }

    o0(v4 v4Var, b bVar) {
        this.f9391a = v4Var;
        this.f9392b = bVar;
    }
}
