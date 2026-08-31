package x3;

import com.google.android.gms.common.api.Status;
import j3.d;
/* loaded from: classes.dex */
final class w extends d.a {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Object f12706e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ f4.k f12707f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public w(Object obj, f4.k kVar) {
        this.f12706e = obj;
        this.f12707f = kVar;
    }

    @Override // j3.d
    public final void H(Status status) {
        j3.k.b(status, this.f12706e, this.f12707f);
    }
}
