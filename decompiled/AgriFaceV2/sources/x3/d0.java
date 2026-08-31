package x3;

import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d0 extends c4.y {

    /* renamed from: e  reason: collision with root package name */
    private final z f12656e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d0(z zVar) {
        this.f12656e = zVar;
    }

    @Override // c4.z
    public final void K(LocationAvailability locationAvailability) {
        this.f12656e.zza().c(new b0(this, locationAvailability));
    }

    @Override // c4.z
    public final void P() {
        this.f12656e.zza().c(new c0(this));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d0() {
        this.f12656e.zza().a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ z e0() {
        return this.f12656e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final d0 f(com.google.android.gms.common.api.internal.c cVar) {
        this.f12656e.b(cVar);
        return this;
    }

    @Override // c4.z
    public final void r(LocationResult locationResult) {
        this.f12656e.zza().c(new a0(this, locationResult));
    }
}
