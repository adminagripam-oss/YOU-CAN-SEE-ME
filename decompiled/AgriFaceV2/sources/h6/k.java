package h6;

import java.util.Locale;
/* loaded from: classes.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private b f7289a;

    /* renamed from: b  reason: collision with root package name */
    private b f7290b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f7291c;

    /* renamed from: d  reason: collision with root package name */
    private final i6.a f7292d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f7293a;

        static {
            int[] iArr = new int[b.values().length];
            f7293a = iArr;
            try {
                iArr[b.RESUMED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7293a[b.INACTIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7293a[b.HIDDEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7293a[b.PAUSED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7293a[b.DETACHED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum b {
        DETACHED,
        RESUMED,
        INACTIVE,
        HIDDEN,
        PAUSED
    }

    public k(a6.a aVar) {
        this(new i6.a(aVar, "flutter/lifecycle", i6.p.f7553b));
    }

    private void g(b bVar, boolean z7) {
        b bVar2 = this.f7289a;
        if (bVar2 == bVar && z7 == this.f7291c) {
            return;
        }
        if (bVar == null && bVar2 == null) {
            this.f7291c = z7;
            return;
        }
        int i8 = a.f7293a[bVar.ordinal()];
        b bVar3 = i8 != 1 ? (i8 == 2 || i8 == 3 || i8 == 4 || i8 == 5) ? bVar : null : z7 ? b.RESUMED : b.INACTIVE;
        this.f7289a = bVar;
        this.f7291c = z7;
        if (bVar3 == this.f7290b) {
            return;
        }
        String str = "AppLifecycleState." + bVar3.name().toLowerCase(Locale.ROOT);
        z5.b.f("LifecycleChannel", "Sending " + str + " message.");
        this.f7292d.c(str);
        this.f7290b = bVar3;
    }

    public void a() {
        g(this.f7289a, true);
    }

    public void b() {
        g(b.DETACHED, this.f7291c);
    }

    public void c() {
        g(b.INACTIVE, this.f7291c);
    }

    public void d() {
        g(b.PAUSED, this.f7291c);
    }

    public void e() {
        g(b.RESUMED, this.f7291c);
    }

    public void f() {
        g(this.f7289a, false);
    }

    public k(i6.a aVar) {
        this.f7289a = null;
        this.f7290b = null;
        this.f7291c = true;
        this.f7292d = aVar;
    }
}
