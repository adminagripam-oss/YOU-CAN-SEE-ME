package com.google.android.gms.common.api.internal;

import android.os.Looper;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f4998a;

    /* renamed from: b  reason: collision with root package name */
    private volatile Object f4999b;

    /* renamed from: c  reason: collision with root package name */
    private volatile a f5000c;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final Object f5001a;

        /* renamed from: b  reason: collision with root package name */
        private final String f5002b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(Object obj, String str) {
            this.f5001a = obj;
            this.f5002b = str;
        }

        public String a() {
            int identityHashCode = System.identityHashCode(this.f5001a);
            return this.f5002b + "@" + identityHashCode;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof a) {
                a aVar = (a) obj;
                return this.f5001a == aVar.f5001a && this.f5002b.equals(aVar.f5002b);
            }
            return false;
        }

        public int hashCode() {
            return (System.identityHashCode(this.f5001a) * 31) + this.f5002b.hashCode();
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(Object obj);

        void b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Looper looper, Object obj, String str) {
        this.f4998a = new r3.a(looper);
        this.f4999b = k3.q.h(obj, "Listener must not be null");
        this.f5000c = new a(obj, k3.q.e(str));
    }

    public void a() {
        this.f4999b = null;
        this.f5000c = null;
    }

    public a b() {
        return this.f5000c;
    }

    public void c(final b bVar) {
        k3.q.h(bVar, "Notifier must not be null");
        this.f4998a.execute(new Runnable() { // from class: com.google.android.gms.common.api.internal.u
            @Override // java.lang.Runnable
            public final void run() {
                c.this.d(bVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(b bVar) {
        Object obj = this.f4999b;
        if (obj == null) {
            bVar.b();
            return;
        }
        try {
            bVar.a(obj);
        } catch (RuntimeException e8) {
            bVar.b();
            throw e8;
        }
    }
}
