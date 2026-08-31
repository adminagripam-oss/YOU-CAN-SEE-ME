package androidx.fragment.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
/* loaded from: classes.dex */
public class y {

    /* renamed from: a  reason: collision with root package name */
    private final a0 f3696a;

    private y(a0 a0Var) {
        this.f3696a = a0Var;
    }

    public static y b(a0 a0Var) {
        return new y((a0) g1.e.k(a0Var, "callbacks == null"));
    }

    public void a(p pVar) {
        i0 g8 = this.f3696a.g();
        a0 a0Var = this.f3696a;
        g8.l(a0Var, a0Var, pVar);
    }

    public void c() {
        this.f3696a.g().x();
    }

    public boolean d(MenuItem menuItem) {
        return this.f3696a.g().A(menuItem);
    }

    public void e() {
        this.f3696a.g().B();
    }

    public void f() {
        this.f3696a.g().D();
    }

    public void g() {
        this.f3696a.g().M();
    }

    public void h() {
        this.f3696a.g().Q();
    }

    public void i() {
        this.f3696a.g().R();
    }

    public void j() {
        this.f3696a.g().T();
    }

    public boolean k() {
        return this.f3696a.g().a0(true);
    }

    public i0 l() {
        return this.f3696a.g();
    }

    public void m() {
        this.f3696a.g().Y0();
    }

    public View n(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f3696a.g().w0().onCreateView(view, str, context, attributeSet);
    }
}
