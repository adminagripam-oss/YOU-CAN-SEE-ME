package m6;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import java.io.File;
import java.io.IOException;
import m6.b0;
import m6.t0;
/* loaded from: classes.dex */
public class k6 implements t0.t1 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9346a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9347b;

    /* renamed from: c  reason: collision with root package name */
    private Context f9348c;

    /* renamed from: d  reason: collision with root package name */
    public g0 f9349d = new g0();

    /* renamed from: e  reason: collision with root package name */
    public i6 f9350e;

    /* renamed from: f  reason: collision with root package name */
    private Activity f9351f;

    /* renamed from: g  reason: collision with root package name */
    private b0.b f9352g;

    public k6(i6.b bVar, v4 v4Var, Context context) {
        this.f9346a = bVar;
        this.f9347b = v4Var;
        this.f9348c = context;
        this.f9350e = new i6(bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void m(t0.r1 r1Var, String str, String str2) {
        r1Var.success(str == null ? null : new t0.m.a().c(str).b(str2).a());
    }

    @Override // m6.t0.t1
    public void c(Boolean bool, final t0.r1 r1Var) {
        if (this.f9351f == null) {
            throw new IllegalStateException("Activity must be set to request camera permissions.");
        }
        this.f9349d.a().e(this.f9351f, this.f9352g, bool.booleanValue(), new b0.c() { // from class: m6.j6
            @Override // m6.b0.c
            public final void a(String str, String str2) {
                k6.m(t0.r1.this, str, str2);
            }
        });
    }

    @Override // m6.t0.t1
    public String f(String str, String str2) {
        Context context = this.f9348c;
        if (context != null) {
            try {
                return File.createTempFile(str, str2, context.getCacheDir()).toString();
            } catch (IOException | SecurityException e8) {
                throw new t0.c0("getTempFilePath_failure", "SystemServicesHostApiImpl.getTempFilePath encountered an exception: " + e8.toString(), null);
            }
        }
        throw new IllegalStateException("Context must be set to create a temporary file.");
    }

    @Override // m6.t0.t1
    public Boolean j() {
        return Boolean.valueOf(Build.VERSION.SDK_INT < 29);
    }

    public void n(Activity activity) {
        this.f9351f = activity;
    }

    public void o(Context context) {
        this.f9348c = context;
    }

    public void p(b0.b bVar) {
        this.f9352g = bVar;
    }
}
