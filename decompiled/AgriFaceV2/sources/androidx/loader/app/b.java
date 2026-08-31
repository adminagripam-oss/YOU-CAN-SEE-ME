package androidx.loader.app;

import androidx.appcompat.app.w;
import androidx.lifecycle.k0;
import androidx.lifecycle.l0;
import androidx.lifecycle.m;
import androidx.lifecycle.n0;
import b1.h;
import java.io.FileDescriptor;
import java.io.PrintWriter;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b extends androidx.loader.app.a {

    /* renamed from: a  reason: collision with root package name */
    private final m f3859a;

    /* renamed from: b  reason: collision with root package name */
    private final a f3860b;

    /* loaded from: classes.dex */
    static class a extends k0 {

        /* renamed from: f  reason: collision with root package name */
        private static final l0.b f3861f = new C0047a();

        /* renamed from: d  reason: collision with root package name */
        private h f3862d = new h();

        /* renamed from: e  reason: collision with root package name */
        private boolean f3863e = false;

        /* renamed from: androidx.loader.app.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        static class C0047a implements l0.b {
            C0047a() {
            }

            @Override // androidx.lifecycle.l0.b
            public k0 a(Class cls) {
                return new a();
            }
        }

        a() {
        }

        static a g(n0 n0Var) {
            return (a) new l0(n0Var, f3861f).a(a.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.lifecycle.k0
        public void d() {
            super.d();
            if (this.f3862d.k() <= 0) {
                this.f3862d.c();
            } else {
                w.a(this.f3862d.l(0));
                throw null;
            }
        }

        public void f(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f3862d.k() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("    ");
                if (this.f3862d.k() <= 0) {
                    return;
                }
                w.a(this.f3862d.l(0));
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.f3862d.i(0));
                printWriter.print(": ");
                throw null;
            }
        }

        void h() {
            if (this.f3862d.k() <= 0) {
                return;
            }
            w.a(this.f3862d.l(0));
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(m mVar, n0 n0Var) {
        this.f3859a = mVar;
        this.f3860b = a.g(n0Var);
    }

    @Override // androidx.loader.app.a
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f3860b.f(str, fileDescriptor, printWriter, strArr);
    }

    @Override // androidx.loader.app.a
    public void c() {
        this.f3860b.h();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        g1.b.a(this.f3859a, sb);
        sb.append("}}");
        return sb.toString();
    }
}
