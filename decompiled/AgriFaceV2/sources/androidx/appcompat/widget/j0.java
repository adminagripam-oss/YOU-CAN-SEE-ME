package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.m;
/* loaded from: classes.dex */
public interface j0 {
    void a(Menu menu, m.a aVar);

    boolean b();

    void c();

    void collapseActionView();

    void d(Drawable drawable);

    boolean e();

    boolean f();

    boolean g();

    Context getContext();

    CharSequence getTitle();

    boolean h();

    void i();

    void j(m.a aVar, g.a aVar2);

    void k(int i8);

    void l(v0 v0Var);

    ViewGroup m();

    void n(boolean z7);

    int o();

    boolean p();

    void q(int i8);

    int r();

    Menu s();

    void setIcon(int i8);

    void setIcon(Drawable drawable);

    void setTitle(CharSequence charSequence);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    void t(int i8);

    int u();

    androidx.core.view.x0 v(int i8, long j8);

    void w();

    void x();

    void y(boolean z7);
}
