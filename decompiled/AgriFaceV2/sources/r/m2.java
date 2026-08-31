package r;

import b0.h0;
import java.util.Objects;
import y.r;
/* loaded from: classes.dex */
class m2 {

    /* renamed from: a  reason: collision with root package name */
    private final b0.o0 f10659a;

    /* renamed from: b  reason: collision with root package name */
    private final androidx.lifecycle.s f10660b;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f10661a;

        static {
            int[] iArr = new int[h0.a.values().length];
            f10661a = iArr;
            try {
                iArr[h0.a.PENDING_OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10661a[h0.a.OPENING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10661a[h0.a.OPEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10661a[h0.a.CONFIGURED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10661a[h0.a.CLOSING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10661a[h0.a.RELEASING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10661a[h0.a.CLOSED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f10661a[h0.a.RELEASED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public m2(b0.o0 o0Var) {
        this.f10659a = o0Var;
        androidx.lifecycle.s sVar = new androidx.lifecycle.s();
        this.f10660b = sVar;
        sVar.l(y.r.a(r.b.CLOSED));
    }

    private y.r b() {
        return y.r.a(this.f10659a.c() ? r.b.OPENING : r.b.PENDING_OPEN);
    }

    public androidx.lifecycle.p a() {
        return this.f10660b;
    }

    public void c(h0.a aVar, r.a aVar2) {
        y.r b8;
        r.b bVar;
        switch (a.f10661a[aVar.ordinal()]) {
            case 1:
                b8 = b();
                break;
            case 2:
                bVar = r.b.OPENING;
                b8 = y.r.b(bVar, aVar2);
                break;
            case 3:
            case 4:
                bVar = r.b.OPEN;
                b8 = y.r.b(bVar, aVar2);
                break;
            case 5:
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                bVar = r.b.CLOSING;
                b8 = y.r.b(bVar, aVar2);
                break;
            case 7:
            case 8:
                bVar = r.b.CLOSED;
                b8 = y.r.b(bVar, aVar2);
                break;
            default:
                throw new IllegalStateException("Unknown internal camera state: " + aVar);
        }
        y.f1.a("CameraStateMachine", "New public camera state " + b8 + " from " + aVar + " and " + aVar2);
        if (Objects.equals((y.r) this.f10660b.e(), b8)) {
            return;
        }
        y.f1.a("CameraStateMachine", "Publishing new public camera state " + b8);
        this.f10660b.l(b8);
    }
}
