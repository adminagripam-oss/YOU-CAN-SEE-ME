package l7;

import android.os.Handler;
import android.os.Looper;
import d7.k;
import java.util.concurrent.CancellationException;
import k7.i1;
import k7.n0;
import t6.g;
/* loaded from: classes.dex */
public final class c extends d {
    private volatile c _immediate;

    /* renamed from: g  reason: collision with root package name */
    private final Handler f9069g;

    /* renamed from: h  reason: collision with root package name */
    private final String f9070h;

    /* renamed from: i  reason: collision with root package name */
    private final boolean f9071i;

    /* renamed from: j  reason: collision with root package name */
    private final c f9072j;

    public c(Handler handler, String str) {
        this(handler, str, false);
    }

    private final void U(g gVar, Runnable runnable) {
        i1.c(gVar, new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed"));
        n0.b().P(gVar, runnable);
    }

    @Override // k7.a0
    public void P(g gVar, Runnable runnable) {
        if (this.f9069g.post(runnable)) {
            return;
        }
        U(gVar, runnable);
    }

    @Override // k7.a0
    public boolean Q(g gVar) {
        return (this.f9071i && k.a(Looper.myLooper(), this.f9069g.getLooper())) ? false : true;
    }

    @Override // k7.o1
    /* renamed from: V */
    public c S() {
        return this.f9072j;
    }

    public boolean equals(Object obj) {
        return (obj instanceof c) && ((c) obj).f9069g == this.f9069g;
    }

    public int hashCode() {
        return System.identityHashCode(this.f9069g);
    }

    @Override // k7.a0
    public String toString() {
        String T = T();
        if (T == null) {
            String str = this.f9070h;
            if (str == null) {
                str = this.f9069g.toString();
            }
            if (this.f9071i) {
                return str + ".immediate";
            }
            return str;
        }
        return T;
    }

    public /* synthetic */ c(Handler handler, String str, int i8, d7.g gVar) {
        this(handler, (i8 & 2) != 0 ? null : str);
    }

    private c(Handler handler, String str, boolean z7) {
        super(null);
        this.f9069g = handler;
        this.f9070h = str;
        this.f9071i = z7;
        this._immediate = z7 ? this : null;
        c cVar = this._immediate;
        if (cVar == null) {
            cVar = new c(handler, str, true);
            this._immediate = cVar;
        }
        this.f9072j = cVar;
    }
}
