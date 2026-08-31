package io.flutter.plugin.platform;

import android.content.Context;
import android.view.MotionEvent;
import io.flutter.embedding.android.k;
/* loaded from: classes.dex */
public class c extends io.flutter.embedding.android.k {

    /* renamed from: g  reason: collision with root package name */
    private a f7940g;

    public c(Context context, int i8, int i9, a aVar) {
        super(context, i8, i9, k.b.overlay);
        this.f7940g = aVar;
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        a aVar = this.f7940g;
        if (aVar == null || !aVar.a(motionEvent, true)) {
            return super.onHoverEvent(motionEvent);
        }
        return true;
    }
}
