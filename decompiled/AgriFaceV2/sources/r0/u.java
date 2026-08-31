package r0;

import android.content.Context;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class u {

    /* renamed from: a  reason: collision with root package name */
    private final Context f11256a;

    /* renamed from: b  reason: collision with root package name */
    private final q0 f11257b;

    /* renamed from: c  reason: collision with root package name */
    private final s f11258c;

    /* renamed from: d  reason: collision with root package name */
    private g1.a f11259d;

    /* renamed from: e  reason: collision with root package name */
    private Executor f11260e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f11261f = false;

    /* renamed from: g  reason: collision with root package name */
    private boolean f11262g = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public u(Context context, q0 q0Var, s sVar) {
        this.f11256a = d0.f.a(context);
        this.f11257b = q0Var;
        this.f11258c = sVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Context a() {
        return this.f11256a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g1.a b() {
        return this.f11259d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Executor c() {
        return this.f11260e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public s d() {
        return this.f11258c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public q0 e() {
        return this.f11257b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f() {
        return this.f11261f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean g() {
        return this.f11262g;
    }

    public a1 h(Executor executor, g1.a aVar) {
        g1.e.k(executor, "Listener Executor can't be null.");
        g1.e.k(aVar, "Event listener can't be null");
        this.f11260e = executor;
        this.f11259d = aVar;
        return this.f11257b.M0(this);
    }

    public u i() {
        if (androidx.core.content.e.b(this.f11256a, "android.permission.RECORD_AUDIO") != -1) {
            g1.e.m(this.f11257b.Q(), "The Recorder this recording is associated to doesn't support audio.");
            this.f11261f = true;
            return this;
        }
        throw new SecurityException("Attempted to enable audio for recording but application does not have RECORD_AUDIO permission granted.");
    }
}
