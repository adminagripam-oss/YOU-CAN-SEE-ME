package androidx.webkit.internal;

import android.os.Build;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class a implements k0 {

    /* renamed from: c  reason: collision with root package name */
    private static final Set f4001c = new HashSet();

    /* renamed from: a  reason: collision with root package name */
    private final String f4002a;

    /* renamed from: b  reason: collision with root package name */
    private final String f4003b;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: androidx.webkit.internal.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0052a {

        /* renamed from: a  reason: collision with root package name */
        static final Set f4004a = new HashSet(Arrays.asList(h1.d().a()));
    }

    /* loaded from: classes.dex */
    public static class b extends a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public b(String str, String str2) {
            super(str, str2);
        }

        @Override // androidx.webkit.internal.a
        public final boolean c() {
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static class c extends a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public c(String str, String str2) {
            super(str, str2);
        }

        @Override // androidx.webkit.internal.a
        public final boolean c() {
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static class d extends a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public d(String str, String str2) {
            super(str, str2);
        }

        @Override // androidx.webkit.internal.a
        public final boolean c() {
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static class e extends a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public e(String str, String str2) {
            super(str, str2);
        }

        @Override // androidx.webkit.internal.a
        public final boolean c() {
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static class f extends a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public f(String str, String str2) {
            super(str, str2);
        }

        @Override // androidx.webkit.internal.a
        public final boolean c() {
            return Build.VERSION.SDK_INT >= 27;
        }
    }

    /* loaded from: classes.dex */
    public static class g extends a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public g(String str, String str2) {
            super(str, str2);
        }

        @Override // androidx.webkit.internal.a
        public final boolean c() {
            return Build.VERSION.SDK_INT >= 28;
        }
    }

    /* loaded from: classes.dex */
    public static class h extends a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public h(String str, String str2) {
            super(str, str2);
        }

        @Override // androidx.webkit.internal.a
        public final boolean c() {
            return Build.VERSION.SDK_INT >= 29;
        }
    }

    /* loaded from: classes.dex */
    public static class i extends a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public i(String str, String str2) {
            super(str, str2);
        }

        @Override // androidx.webkit.internal.a
        public final boolean c() {
            return Build.VERSION.SDK_INT >= 33;
        }
    }

    a(String str, String str2) {
        this.f4002a = str;
        this.f4003b = str2;
        f4001c.add(this);
    }

    public static Set e() {
        return Collections.unmodifiableSet(f4001c);
    }

    @Override // androidx.webkit.internal.k0
    public String a() {
        return this.f4002a;
    }

    @Override // androidx.webkit.internal.k0
    public boolean b() {
        return c() || d();
    }

    public abstract boolean c();

    public boolean d() {
        return t7.a.b(C0052a.f4004a, this.f4003b);
    }
}
