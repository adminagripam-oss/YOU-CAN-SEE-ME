package androidx.fragment.app;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import androidx.lifecycle.i;
import androidx.lifecycle.l0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class u0 implements androidx.lifecycle.g, w1.f, androidx.lifecycle.o0 {

    /* renamed from: a  reason: collision with root package name */
    private final p f3673a;

    /* renamed from: b  reason: collision with root package name */
    private final androidx.lifecycle.n0 f3674b;

    /* renamed from: c  reason: collision with root package name */
    private final Runnable f3675c;

    /* renamed from: d  reason: collision with root package name */
    private androidx.lifecycle.n f3676d = null;

    /* renamed from: e  reason: collision with root package name */
    private w1.e f3677e = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public u0(p pVar, androidx.lifecycle.n0 n0Var, Runnable runnable) {
        this.f3673a = pVar;
        this.f3674b = n0Var;
        this.f3675c = runnable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(i.a aVar) {
        this.f3676d.h(aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        if (this.f3676d == null) {
            this.f3676d = new androidx.lifecycle.n(this);
            w1.e a8 = w1.e.a(this);
            this.f3677e = a8;
            a8.c();
            this.f3675c.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c() {
        return this.f3676d != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(Bundle bundle) {
        this.f3677e.d(bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(Bundle bundle) {
        this.f3677e.e(bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(i.b bVar) {
        this.f3676d.m(bVar);
    }

    @Override // androidx.lifecycle.g
    public v1.a getDefaultViewModelCreationExtras() {
        Application application;
        Context applicationContext = this.f3673a.k1().getApplicationContext();
        while (true) {
            if (!(applicationContext instanceof ContextWrapper)) {
                application = null;
                break;
            } else if (applicationContext instanceof Application) {
                application = (Application) applicationContext;
                break;
            } else {
                applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
            }
        }
        v1.b bVar = new v1.b();
        if (application != null) {
            bVar.c(l0.a.f3793h, application);
        }
        bVar.c(androidx.lifecycle.e0.f3755a, this.f3673a);
        bVar.c(androidx.lifecycle.e0.f3756b, this);
        if (this.f3673a.n() != null) {
            bVar.c(androidx.lifecycle.e0.f3757c, this.f3673a.n());
        }
        return bVar;
    }

    @Override // androidx.lifecycle.m
    public androidx.lifecycle.i getLifecycle() {
        b();
        return this.f3676d;
    }

    @Override // w1.f
    public w1.d getSavedStateRegistry() {
        b();
        return this.f3677e.b();
    }

    @Override // androidx.lifecycle.o0
    public androidx.lifecycle.n0 getViewModelStore() {
        b();
        return this.f3674b;
    }
}
