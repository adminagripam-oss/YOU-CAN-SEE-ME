package androidx.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class n {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f1432a;

    /* renamed from: b  reason: collision with root package name */
    private final c7.a f1433b;

    /* renamed from: c  reason: collision with root package name */
    private final Object f1434c;

    /* renamed from: d  reason: collision with root package name */
    private int f1435d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f1436e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f1437f;

    /* renamed from: g  reason: collision with root package name */
    private final List f1438g;

    /* renamed from: h  reason: collision with root package name */
    private final Runnable f1439h;

    public n(Executor executor, c7.a aVar) {
        d7.k.e(executor, "executor");
        d7.k.e(aVar, "reportFullyDrawn");
        this.f1432a = executor;
        this.f1433b = aVar;
        this.f1434c = new Object();
        this.f1438g = new ArrayList();
        this.f1439h = new Runnable() { // from class: androidx.activity.m
            @Override // java.lang.Runnable
            public final void run() {
                n.d(n.this);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(n nVar) {
        d7.k.e(nVar, "this$0");
        synchronized (nVar.f1434c) {
            nVar.f1436e = false;
            if (nVar.f1435d == 0 && !nVar.f1437f) {
                nVar.f1433b.a();
                nVar.b();
            }
            r6.r rVar = r6.r.f11372a;
        }
    }

    public final void b() {
        synchronized (this.f1434c) {
            this.f1437f = true;
            for (c7.a aVar : this.f1438g) {
                aVar.a();
            }
            this.f1438g.clear();
            r6.r rVar = r6.r.f11372a;
        }
    }

    public final boolean c() {
        boolean z7;
        synchronized (this.f1434c) {
            z7 = this.f1437f;
        }
        return z7;
    }
}
