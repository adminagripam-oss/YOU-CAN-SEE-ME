package androidx.core.view;

import android.os.Build;
import android.view.View;
/* loaded from: classes.dex */
public final class l0 {

    /* renamed from: a  reason: collision with root package name */
    private final c f3139a;

    /* loaded from: classes.dex */
    private static class a extends c {

        /* renamed from: a  reason: collision with root package name */
        private final View f3140a;

        a(View view) {
            this.f3140a = view;
        }
    }

    /* loaded from: classes.dex */
    private static class b extends a {

        /* renamed from: b  reason: collision with root package name */
        private View f3141b;

        b(View view) {
            super(view);
            this.f3141b = view;
        }
    }

    /* loaded from: classes.dex */
    private static class c {
        c() {
        }
    }

    public l0(View view) {
        this.f3139a = Build.VERSION.SDK_INT >= 30 ? new b(view) : new a(view);
    }
}
