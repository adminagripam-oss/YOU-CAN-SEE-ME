package j2;

import androidx.window.extensions.layout.WindowLayoutComponent;
import d7.k;
/* loaded from: classes.dex */
public abstract class a implements i2.a {

    /* renamed from: a  reason: collision with root package name */
    public static final C0109a f8186a = new C0109a(null);

    /* renamed from: j2.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0109a {
        private C0109a() {
        }

        public /* synthetic */ C0109a(d7.g gVar) {
            this();
        }

        public final i2.a a(WindowLayoutComponent windowLayoutComponent, e2.d dVar) {
            k.e(windowLayoutComponent, "component");
            k.e(dVar, "adapter");
            int a8 = e2.e.f6572a.a();
            return a8 >= 2 ? new e(windowLayoutComponent) : a8 == 1 ? new d(windowLayoutComponent, dVar) : new c();
        }
    }
}
