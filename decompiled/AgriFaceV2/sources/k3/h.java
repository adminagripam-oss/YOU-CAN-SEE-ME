package k3;

import android.accounts.Account;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Scope;
import i3.a;
import i3.f;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class h extends c implements a.f {
    private final e F;
    private final Set G;
    private final Account H;

    /* JADX INFO: Access modifiers changed from: protected */
    public h(Context context, Looper looper, int i8, e eVar, f.a aVar, f.b bVar) {
        this(context, looper, i8, eVar, (j3.c) aVar, (j3.h) bVar);
    }

    private final Set i0(Set set) {
        Set<Scope> h02 = h0(set);
        for (Scope scope : h02) {
            if (!set.contains(scope)) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return h02;
    }

    @Override // k3.c
    protected final Set B() {
        return this.G;
    }

    @Override // i3.a.f
    public Set c() {
        return o() ? this.G : Collections.emptySet();
    }

    protected Set h0(Set set) {
        return set;
    }

    @Override // k3.c
    public final Account t() {
        return this.H;
    }

    @Override // k3.c
    protected Executor v() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public h(Context context, Looper looper, int i8, e eVar, j3.c cVar, j3.h hVar) {
        this(context, looper, i.a(context), h3.d.m(), i8, eVar, (j3.c) q.g(cVar), (j3.h) q.g(hVar));
    }

    protected h(Context context, Looper looper, i iVar, h3.d dVar, int i8, e eVar, j3.c cVar, j3.h hVar) {
        super(context, looper, iVar, dVar, i8, cVar == null ? null : new f0(cVar), hVar == null ? null : new g0(hVar), eVar.h());
        this.F = eVar;
        this.H = eVar.a();
        this.G = i0(eVar.c());
    }
}
