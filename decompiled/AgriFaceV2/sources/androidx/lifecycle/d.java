package androidx.lifecycle;

import androidx.lifecycle.i;
/* loaded from: classes.dex */
public final class d implements k {

    /* renamed from: e  reason: collision with root package name */
    private final DefaultLifecycleObserver f3749e;

    /* renamed from: f  reason: collision with root package name */
    private final k f3750f;

    /* loaded from: classes.dex */
    public /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f3751a;

        static {
            int[] iArr = new int[i.a.values().length];
            try {
                iArr[i.a.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[i.a.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[i.a.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[i.a.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[i.a.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[i.a.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[i.a.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            f3751a = iArr;
        }
    }

    public d(DefaultLifecycleObserver defaultLifecycleObserver, k kVar) {
        d7.k.e(defaultLifecycleObserver, "defaultLifecycleObserver");
        this.f3749e = defaultLifecycleObserver;
        this.f3750f = kVar;
    }

    @Override // androidx.lifecycle.k
    public void f(m mVar, i.a aVar) {
        d7.k.e(mVar, "source");
        d7.k.e(aVar, "event");
        switch (a.f3751a[aVar.ordinal()]) {
            case 1:
                this.f3749e.d(mVar);
                break;
            case 2:
                this.f3749e.onStart(mVar);
                break;
            case 3:
                this.f3749e.onResume(mVar);
                break;
            case 4:
                this.f3749e.onPause(mVar);
                break;
            case 5:
                this.f3749e.onStop(mVar);
                break;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                this.f3749e.onDestroy(mVar);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        k kVar = this.f3750f;
        if (kVar != null) {
            kVar.f(mVar, aVar);
        }
    }
}
