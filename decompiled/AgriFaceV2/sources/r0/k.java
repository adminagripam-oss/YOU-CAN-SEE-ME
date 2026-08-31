package r0;

import java.util.concurrent.Executor;
import r0.q0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class k extends q0.j {

    /* renamed from: l  reason: collision with root package name */
    private final s f11070l;

    /* renamed from: m  reason: collision with root package name */
    private final Executor f11071m;

    /* renamed from: n  reason: collision with root package name */
    private final g1.a f11072n;

    /* renamed from: o  reason: collision with root package name */
    private final boolean f11073o;

    /* renamed from: p  reason: collision with root package name */
    private final boolean f11074p;

    /* renamed from: q  reason: collision with root package name */
    private final long f11075q;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(s sVar, Executor executor, g1.a aVar, boolean z7, boolean z8, long j8) {
        if (sVar == null) {
            throw new NullPointerException("Null getOutputOptions");
        }
        this.f11070l = sVar;
        this.f11071m = executor;
        this.f11072n = aVar;
        this.f11073o = z7;
        this.f11074p = z8;
        this.f11075q = j8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r0.q0.j
    public boolean D() {
        return this.f11074p;
    }

    public boolean equals(Object obj) {
        Executor executor;
        g1.a aVar;
        if (obj == this) {
            return true;
        }
        if (obj instanceof q0.j) {
            q0.j jVar = (q0.j) obj;
            return this.f11070l.equals(jVar.t()) && ((executor = this.f11071m) != null ? executor.equals(jVar.n()) : jVar.n() == null) && ((aVar = this.f11072n) != null ? aVar.equals(jVar.q()) : jVar.q() == null) && this.f11073o == jVar.x() && this.f11074p == jVar.D() && this.f11075q == jVar.v();
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.f11070l.hashCode() ^ 1000003) * 1000003;
        Executor executor = this.f11071m;
        int hashCode2 = (hashCode ^ (executor == null ? 0 : executor.hashCode())) * 1000003;
        g1.a aVar = this.f11072n;
        int hashCode3 = (((hashCode2 ^ (aVar != null ? aVar.hashCode() : 0)) * 1000003) ^ (this.f11073o ? 1231 : 1237)) * 1000003;
        int i8 = this.f11074p ? 1231 : 1237;
        long j8 = this.f11075q;
        return ((hashCode3 ^ i8) * 1000003) ^ ((int) (j8 ^ (j8 >>> 32)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r0.q0.j
    public Executor n() {
        return this.f11071m;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r0.q0.j
    public g1.a q() {
        return this.f11072n;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r0.q0.j
    public s t() {
        return this.f11070l;
    }

    public String toString() {
        return "RecordingRecord{getOutputOptions=" + this.f11070l + ", getCallbackExecutor=" + this.f11071m + ", getEventListener=" + this.f11072n + ", hasAudioEnabled=" + this.f11073o + ", isPersistent=" + this.f11074p + ", getRecordingId=" + this.f11075q + "}";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r0.q0.j
    public long v() {
        return this.f11075q;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r0.q0.j
    public boolean x() {
        return this.f11073o;
    }
}
