package k3;

import android.app.Activity;
import android.content.Intent;
/* loaded from: classes.dex */
final class c0 extends e0 {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Intent f8359a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Activity f8360b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ int f8361c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c0(Intent intent, Activity activity, int i8) {
        this.f8359a = intent;
        this.f8360b = activity;
        this.f8361c = i8;
    }

    @Override // k3.e0
    public final void a() {
        Intent intent = this.f8359a;
        if (intent != null) {
            this.f8360b.startActivityForResult(intent, this.f8361c);
        }
    }
}
