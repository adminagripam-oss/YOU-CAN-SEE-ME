package k3;

import android.content.Intent;
/* loaded from: classes.dex */
final class d0 extends e0 {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Intent f8364a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ j3.f f8365b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d0(Intent intent, j3.f fVar, int i8) {
        this.f8364a = intent;
        this.f8365b = fVar;
    }

    @Override // k3.e0
    public final void a() {
        Intent intent = this.f8364a;
        if (intent != null) {
            this.f8365b.startActivityForResult(intent, 2);
        }
    }
}
