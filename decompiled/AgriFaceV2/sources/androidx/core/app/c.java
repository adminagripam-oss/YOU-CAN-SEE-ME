package androidx.core.app;

import android.app.ActivityOptions;
import android.content.Context;
import android.os.Bundle;
/* loaded from: classes.dex */
public abstract class c {

    /* loaded from: classes.dex */
    private static class a extends c {

        /* renamed from: a  reason: collision with root package name */
        private final ActivityOptions f2819a;

        a(ActivityOptions activityOptions) {
            this.f2819a = activityOptions;
        }

        @Override // androidx.core.app.c
        public Bundle b() {
            return this.f2819a.toBundle();
        }
    }

    protected c() {
    }

    public static c a(Context context, int i8, int i9) {
        return new a(ActivityOptions.makeCustomAnimation(context, i8, i9));
    }

    public abstract Bundle b();
}
