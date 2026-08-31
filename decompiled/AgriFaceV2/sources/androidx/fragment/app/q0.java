package androidx.fragment.app;

import android.view.ViewGroup;
import androidx.lifecycle.i;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
/* loaded from: classes.dex */
public abstract class q0 {

    /* renamed from: a  reason: collision with root package name */
    private final z f3616a;

    /* renamed from: b  reason: collision with root package name */
    private final ClassLoader f3617b;

    /* renamed from: d  reason: collision with root package name */
    int f3619d;

    /* renamed from: e  reason: collision with root package name */
    int f3620e;

    /* renamed from: f  reason: collision with root package name */
    int f3621f;

    /* renamed from: g  reason: collision with root package name */
    int f3622g;

    /* renamed from: h  reason: collision with root package name */
    int f3623h;

    /* renamed from: i  reason: collision with root package name */
    boolean f3624i;

    /* renamed from: k  reason: collision with root package name */
    String f3626k;

    /* renamed from: l  reason: collision with root package name */
    int f3627l;

    /* renamed from: m  reason: collision with root package name */
    CharSequence f3628m;

    /* renamed from: n  reason: collision with root package name */
    int f3629n;

    /* renamed from: o  reason: collision with root package name */
    CharSequence f3630o;

    /* renamed from: p  reason: collision with root package name */
    ArrayList f3631p;

    /* renamed from: q  reason: collision with root package name */
    ArrayList f3632q;

    /* renamed from: s  reason: collision with root package name */
    ArrayList f3634s;

    /* renamed from: c  reason: collision with root package name */
    ArrayList f3618c = new ArrayList();

    /* renamed from: j  reason: collision with root package name */
    boolean f3625j = true;

    /* renamed from: r  reason: collision with root package name */
    boolean f3633r = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        int f3635a;

        /* renamed from: b  reason: collision with root package name */
        p f3636b;

        /* renamed from: c  reason: collision with root package name */
        boolean f3637c;

        /* renamed from: d  reason: collision with root package name */
        int f3638d;

        /* renamed from: e  reason: collision with root package name */
        int f3639e;

        /* renamed from: f  reason: collision with root package name */
        int f3640f;

        /* renamed from: g  reason: collision with root package name */
        int f3641g;

        /* renamed from: h  reason: collision with root package name */
        i.b f3642h;

        /* renamed from: i  reason: collision with root package name */
        i.b f3643i;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(int i8, p pVar) {
            this.f3635a = i8;
            this.f3636b = pVar;
            this.f3637c = false;
            i.b bVar = i.b.RESUMED;
            this.f3642h = bVar;
            this.f3643i = bVar;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(int i8, p pVar, boolean z7) {
            this.f3635a = i8;
            this.f3636b = pVar;
            this.f3637c = z7;
            i.b bVar = i.b.RESUMED;
            this.f3642h = bVar;
            this.f3643i = bVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public q0(z zVar, ClassLoader classLoader) {
        this.f3616a = zVar;
        this.f3617b = classLoader;
    }

    public q0 b(int i8, p pVar, String str) {
        k(i8, pVar, str, 1);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public q0 c(ViewGroup viewGroup, p pVar, String str) {
        pVar.I = viewGroup;
        return b(viewGroup.getId(), pVar, str);
    }

    public q0 d(p pVar, String str) {
        k(0, pVar, str, 1);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(a aVar) {
        this.f3618c.add(aVar);
        aVar.f3638d = this.f3619d;
        aVar.f3639e = this.f3620e;
        aVar.f3640f = this.f3621f;
        aVar.f3641g = this.f3622g;
    }

    public abstract int f();

    public abstract int g();

    public abstract void h();

    public abstract void i();

    public q0 j() {
        if (this.f3624i) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.f3625j = false;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(int i8, p pVar, String str, int i9) {
        String str2 = pVar.S;
        if (str2 != null) {
            s1.c.f(pVar, str2);
        }
        Class<?> cls = pVar.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str3 = pVar.A;
            if (str3 != null && !str.equals(str3)) {
                throw new IllegalStateException("Can't change tag of fragment " + pVar + ": was " + pVar.A + " now " + str);
            }
            pVar.A = str;
        }
        if (i8 != 0) {
            if (i8 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + pVar + " with tag " + str + " to container view with no id");
            }
            int i10 = pVar.f3582y;
            if (i10 != 0 && i10 != i8) {
                throw new IllegalStateException("Can't change container ID of fragment " + pVar + ": was " + pVar.f3582y + " now " + i8);
            }
            pVar.f3582y = i8;
            pVar.f3583z = i8;
        }
        e(new a(i9, pVar));
    }

    public q0 l(p pVar) {
        e(new a(3, pVar));
        return this;
    }

    public q0 m(boolean z7) {
        this.f3633r = z7;
        return this;
    }
}
