package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.io.FileDescriptor;
import java.io.PrintWriter;
/* loaded from: classes.dex */
public class LifecycleCallback {

    /* renamed from: a  reason: collision with root package name */
    protected final j3.f f4971a;

    /* JADX INFO: Access modifiers changed from: protected */
    public LifecycleCallback(j3.f fVar) {
        this.f4971a = fVar;
    }

    public static j3.f c(Activity activity) {
        return d(new j3.e(activity));
    }

    protected static j3.f d(j3.e eVar) {
        if (eVar.d()) {
            return j3.f0.B1(eVar.b());
        }
        if (eVar.c()) {
            return j3.d0.f(eVar.a());
        }
        throw new IllegalArgumentException("Can't get fragment for unexpected activity.");
    }

    private static j3.f getChimeraLifecycleFragmentImpl(j3.e eVar) {
        throw new IllegalStateException("Method not available in SDK.");
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public Activity b() {
        Activity c8 = this.f4971a.c();
        k3.q.g(c8);
        return c8;
    }

    public void e(int i8, int i9, Intent intent) {
    }

    public void f(Bundle bundle) {
    }

    public void g() {
    }

    public void h() {
    }

    public void i(Bundle bundle) {
    }

    public void j() {
    }

    public void k() {
    }
}
