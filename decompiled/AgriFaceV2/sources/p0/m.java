package p0;

import b0.s;
import b0.t;
import b0.u;
import b0.v;
import b0.w;
import b0.x;
import b0.y2;
/* loaded from: classes.dex */
public class m implements x {

    /* renamed from: a  reason: collision with root package name */
    private final x f9971a;

    /* renamed from: b  reason: collision with root package name */
    private final y2 f9972b;

    /* renamed from: c  reason: collision with root package name */
    private final long f9973c;

    private m(x xVar, y2 y2Var, long j8) {
        this.f9971a = xVar;
        this.f9972b = y2Var;
        this.f9973c = j8;
    }

    @Override // b0.x
    public y2 b() {
        return this.f9972b;
    }

    @Override // b0.x
    public long c() {
        x xVar = this.f9971a;
        if (xVar != null) {
            return xVar.c();
        }
        long j8 = this.f9973c;
        if (j8 != -1) {
            return j8;
        }
        throw new IllegalStateException("No timestamp is available.");
    }

    @Override // b0.x
    public s d() {
        x xVar = this.f9971a;
        return xVar != null ? xVar.d() : s.UNKNOWN;
    }

    @Override // b0.x
    public b0.r e() {
        x xVar = this.f9971a;
        return xVar != null ? xVar.e() : b0.r.UNKNOWN;
    }

    @Override // b0.x
    public v f() {
        x xVar = this.f9971a;
        return xVar != null ? xVar.f() : v.UNKNOWN;
    }

    @Override // b0.x
    public w g() {
        x xVar = this.f9971a;
        return xVar != null ? xVar.g() : w.UNKNOWN;
    }

    @Override // b0.x
    public u i() {
        x xVar = this.f9971a;
        return xVar != null ? xVar.i() : u.UNKNOWN;
    }

    @Override // b0.x
    public b0.q j() {
        x xVar = this.f9971a;
        return xVar != null ? xVar.j() : b0.q.UNKNOWN;
    }

    @Override // b0.x
    public t k() {
        x xVar = this.f9971a;
        return xVar != null ? xVar.k() : t.UNKNOWN;
    }

    public m(y2 y2Var, long j8) {
        this(null, y2Var, j8);
    }

    public m(y2 y2Var, x xVar) {
        this(xVar, y2Var, -1L);
    }
}
