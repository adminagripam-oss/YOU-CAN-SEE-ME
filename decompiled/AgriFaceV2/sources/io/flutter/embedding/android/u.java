package io.flutter.embedding.android;

import android.view.KeyEvent;
import h6.i;
import io.flutter.embedding.android.a0;
/* loaded from: classes.dex */
public class u implements a0.d {

    /* renamed from: a  reason: collision with root package name */
    private final h6.i f7680a;

    /* renamed from: b  reason: collision with root package name */
    private final a0.b f7681b = new a0.b();

    public u(h6.i iVar) {
        this.f7680a = iVar;
    }

    @Override // io.flutter.embedding.android.a0.d
    public void a(KeyEvent keyEvent, final a0.d.a aVar) {
        int action = keyEvent.getAction();
        if (action == 0 || action == 1) {
            this.f7680a.e(new i.b(keyEvent, this.f7681b.a(keyEvent.getUnicodeChar())), action != 0, new i.a() { // from class: io.flutter.embedding.android.t
                @Override // h6.i.a
                public final void a(boolean z7) {
                    a0.d.a.this.a(z7);
                }
            });
        } else {
            aVar.a(false);
        }
    }
}
