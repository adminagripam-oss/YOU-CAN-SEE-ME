package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
class a1 extends t0 {

    /* renamed from: b  reason: collision with root package name */
    private final WeakReference f2212b;

    public a1(Context context, Resources resources) {
        super(resources);
        this.f2212b = new WeakReference(context);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i8) {
        Drawable a8 = a(i8);
        Context context = (Context) this.f2212b.get();
        if (a8 != null && context != null) {
            s0.g().w(context, i8, a8);
        }
        return a8;
    }
}
