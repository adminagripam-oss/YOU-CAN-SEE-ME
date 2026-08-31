package n3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import k3.q;
/* loaded from: classes.dex */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    private final List f9756a;

    /* renamed from: b  reason: collision with root package name */
    private final Executor f9757b;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final List f9758a = new ArrayList();

        /* renamed from: b  reason: collision with root package name */
        private Executor f9759b;

        public a a(i3.g gVar) {
            this.f9758a.add(gVar);
            return this;
        }

        public f b() {
            return new f(this.f9758a, null, this.f9759b, true, null);
        }
    }

    /* synthetic */ f(List list, n3.a aVar, Executor executor, boolean z7, j jVar) {
        q.h(list, "APIs must not be null.");
        q.b(!list.isEmpty(), "APIs must not be empty.");
        if (executor != null) {
            q.h(aVar, "Listener must not be null when listener executor is set.");
        }
        this.f9756a = list;
        this.f9757b = executor;
    }

    public static a d() {
        return new a();
    }

    public List a() {
        return this.f9756a;
    }

    public n3.a b() {
        return null;
    }

    public Executor c() {
        return this.f9757b;
    }
}
