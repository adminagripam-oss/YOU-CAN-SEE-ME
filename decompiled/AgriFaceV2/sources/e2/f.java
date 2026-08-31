package e2;

import java.util.List;
/* loaded from: classes.dex */
final class f extends h {

    /* renamed from: b  reason: collision with root package name */
    private final Object f6574b;

    /* renamed from: c  reason: collision with root package name */
    private final String f6575c;

    /* renamed from: d  reason: collision with root package name */
    private final String f6576d;

    /* renamed from: e  reason: collision with root package name */
    private final g f6577e;

    /* renamed from: f  reason: collision with root package name */
    private final j f6578f;

    /* renamed from: g  reason: collision with root package name */
    private final l f6579g;

    /* loaded from: classes.dex */
    public /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f6580a;

        static {
            int[] iArr = new int[j.values().length];
            try {
                iArr[j.STRICT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[j.LOG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[j.QUIET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            f6580a = iArr;
        }
    }

    public f(Object obj, String str, String str2, g gVar, j jVar) {
        List g8;
        d7.k.e(obj, "value");
        d7.k.e(str, "tag");
        d7.k.e(str2, "message");
        d7.k.e(gVar, "logger");
        d7.k.e(jVar, "verificationMode");
        this.f6574b = obj;
        this.f6575c = str;
        this.f6576d = str2;
        this.f6577e = gVar;
        this.f6578f = jVar;
        l lVar = new l(b(obj, str2));
        StackTraceElement[] stackTrace = lVar.getStackTrace();
        d7.k.d(stackTrace, "stackTrace");
        g8 = s6.j.g(stackTrace, 2);
        lVar.setStackTrace((StackTraceElement[]) g8.toArray(new StackTraceElement[0]));
        this.f6579g = lVar;
    }

    @Override // e2.h
    public Object a() {
        int i8 = a.f6580a[this.f6578f.ordinal()];
        if (i8 != 1) {
            if (i8 == 2) {
                this.f6577e.a(this.f6575c, b(this.f6574b, this.f6576d));
                return null;
            } else if (i8 == 3) {
                return null;
            } else {
                throw new r6.i();
            }
        }
        throw this.f6579g;
    }

    @Override // e2.h
    public h c(String str, c7.l lVar) {
        d7.k.e(str, "message");
        d7.k.e(lVar, "condition");
        return this;
    }
}
