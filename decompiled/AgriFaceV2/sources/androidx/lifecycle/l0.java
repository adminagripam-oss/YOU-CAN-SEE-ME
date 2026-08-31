package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;
import v1.a;
/* loaded from: classes.dex */
public class l0 {

    /* renamed from: a  reason: collision with root package name */
    private final n0 f3788a;

    /* renamed from: b  reason: collision with root package name */
    private final b f3789b;

    /* renamed from: c  reason: collision with root package name */
    private final v1.a f3790c;

    /* loaded from: classes.dex */
    public static class a extends c {

        /* renamed from: g  reason: collision with root package name */
        private static a f3792g;

        /* renamed from: e  reason: collision with root package name */
        private final Application f3794e;

        /* renamed from: f  reason: collision with root package name */
        public static final C0044a f3791f = new C0044a(null);

        /* renamed from: h  reason: collision with root package name */
        public static final a.b f3793h = C0044a.C0045a.f3795a;

        /* renamed from: androidx.lifecycle.l0$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0044a {

            /* renamed from: androidx.lifecycle.l0$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            private static final class C0045a implements a.b {

                /* renamed from: a  reason: collision with root package name */
                public static final C0045a f3795a = new C0045a();

                private C0045a() {
                }
            }

            private C0044a() {
            }

            public /* synthetic */ C0044a(d7.g gVar) {
                this();
            }

            public final a a(Application application) {
                d7.k.e(application, "application");
                if (a.f3792g == null) {
                    a.f3792g = new a(application);
                }
                a aVar = a.f3792g;
                d7.k.b(aVar);
                return aVar;
            }
        }

        public a() {
            this(null, 0);
        }

        private final k0 g(Class cls, Application application) {
            if (androidx.lifecycle.a.class.isAssignableFrom(cls)) {
                try {
                    k0 k0Var = (k0) cls.getConstructor(Application.class).newInstance(application);
                    d7.k.d(k0Var, "{\n                try {\n…          }\n            }");
                    return k0Var;
                } catch (IllegalAccessException e8) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e8);
                } catch (InstantiationException e9) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e9);
                } catch (NoSuchMethodException e10) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e10);
                } catch (InvocationTargetException e11) {
                    throw new RuntimeException("Cannot create an instance of " + cls, e11);
                }
            }
            return super.a(cls);
        }

        @Override // androidx.lifecycle.l0.c, androidx.lifecycle.l0.b
        public k0 a(Class cls) {
            d7.k.e(cls, "modelClass");
            Application application = this.f3794e;
            if (application != null) {
                return g(cls, application);
            }
            throw new UnsupportedOperationException("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
        }

        @Override // androidx.lifecycle.l0.b
        public k0 b(Class cls, v1.a aVar) {
            d7.k.e(cls, "modelClass");
            d7.k.e(aVar, "extras");
            if (this.f3794e != null) {
                return a(cls);
            }
            Application application = (Application) aVar.a(f3793h);
            if (application != null) {
                return g(cls, application);
            }
            if (androidx.lifecycle.a.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException("CreationExtras must have an application by `APPLICATION_KEY`");
            }
            return super.a(cls);
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public a(Application application) {
            this(application, 0);
            d7.k.e(application, "application");
        }

        private a(Application application, int i8) {
            this.f3794e = application;
        }
    }

    /* loaded from: classes.dex */
    public interface b {

        /* renamed from: a  reason: collision with root package name */
        public static final a f3796a = a.f3797a;

        /* loaded from: classes.dex */
        public static final class a {

            /* renamed from: a  reason: collision with root package name */
            static final /* synthetic */ a f3797a = new a();

            private a() {
            }
        }

        default k0 a(Class cls) {
            d7.k.e(cls, "modelClass");
            throw new UnsupportedOperationException("Factory.create(String) is unsupported.  This Factory requires `CreationExtras` to be passed into `create` method.");
        }

        default k0 b(Class cls, v1.a aVar) {
            d7.k.e(cls, "modelClass");
            d7.k.e(aVar, "extras");
            return a(cls);
        }
    }

    /* loaded from: classes.dex */
    public static class c implements b {

        /* renamed from: c  reason: collision with root package name */
        private static c f3799c;

        /* renamed from: b  reason: collision with root package name */
        public static final a f3798b = new a(null);

        /* renamed from: d  reason: collision with root package name */
        public static final a.b f3800d = a.C0046a.f3801a;

        /* loaded from: classes.dex */
        public static final class a {

            /* renamed from: androidx.lifecycle.l0$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            private static final class C0046a implements a.b {

                /* renamed from: a  reason: collision with root package name */
                public static final C0046a f3801a = new C0046a();

                private C0046a() {
                }
            }

            private a() {
            }

            public /* synthetic */ a(d7.g gVar) {
                this();
            }

            public final c a() {
                if (c.f3799c == null) {
                    c.f3799c = new c();
                }
                c cVar = c.f3799c;
                d7.k.b(cVar);
                return cVar;
            }
        }

        @Override // androidx.lifecycle.l0.b
        public k0 a(Class cls) {
            d7.k.e(cls, "modelClass");
            try {
                Object newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                d7.k.d(newInstance, "{\n                modelC…wInstance()\n            }");
                return (k0) newInstance;
            } catch (IllegalAccessException e8) {
                throw new RuntimeException("Cannot create an instance of " + cls, e8);
            } catch (InstantiationException e9) {
                throw new RuntimeException("Cannot create an instance of " + cls, e9);
            } catch (NoSuchMethodException e10) {
                throw new RuntimeException("Cannot create an instance of " + cls, e10);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class d {
        public abstract void c(k0 k0Var);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public l0(n0 n0Var, b bVar) {
        this(n0Var, bVar, null, 4, null);
        d7.k.e(n0Var, "store");
        d7.k.e(bVar, "factory");
    }

    public k0 a(Class cls) {
        d7.k.e(cls, "modelClass");
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return b("androidx.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public k0 b(String str, Class cls) {
        k0 a8;
        d7.k.e(str, "key");
        d7.k.e(cls, "modelClass");
        k0 b8 = this.f3788a.b(str);
        if (!cls.isInstance(b8)) {
            v1.b bVar = new v1.b(this.f3790c);
            bVar.c(c.f3800d, str);
            try {
                a8 = this.f3789b.b(cls, bVar);
            } catch (AbstractMethodError unused) {
                a8 = this.f3789b.a(cls);
            }
            this.f3788a.d(str, a8);
            return a8;
        }
        b bVar2 = this.f3789b;
        d dVar = bVar2 instanceof d ? (d) bVar2 : null;
        if (dVar != null) {
            d7.k.b(b8);
            dVar.c(b8);
        }
        d7.k.c(b8, "null cannot be cast to non-null type T of androidx.lifecycle.ViewModelProvider.get");
        return b8;
    }

    public l0(n0 n0Var, b bVar, v1.a aVar) {
        d7.k.e(n0Var, "store");
        d7.k.e(bVar, "factory");
        d7.k.e(aVar, "defaultCreationExtras");
        this.f3788a = n0Var;
        this.f3789b = bVar;
        this.f3790c = aVar;
    }

    public /* synthetic */ l0(n0 n0Var, b bVar, v1.a aVar, int i8, d7.g gVar) {
        this(n0Var, bVar, (i8 & 4) != 0 ? a.C0159a.f12322b : aVar);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public l0(o0 o0Var, b bVar) {
        this(o0Var.getViewModelStore(), bVar, m0.a(o0Var));
        d7.k.e(o0Var, "owner");
        d7.k.e(bVar, "factory");
    }
}
