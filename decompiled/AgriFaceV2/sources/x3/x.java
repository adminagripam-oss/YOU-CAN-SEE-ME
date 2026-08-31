package x3;

import android.location.Location;
import com.google.android.gms.common.api.Status;
/* loaded from: classes.dex */
final class x extends h1 {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ f4.k f12711e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public x(f4.k kVar) {
        this.f12711e = kVar;
    }

    @Override // x3.i1
    public final void x(Status status, Location location) {
        j3.k.b(status, location, this.f12711e);
    }
}
