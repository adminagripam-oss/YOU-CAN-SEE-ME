package o;

import android.os.Bundle;
/* loaded from: classes.dex */
public interface a {

    /* renamed from: o.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0134a implements a {
        @Override // o.a
        public Bundle a() {
            Bundle bundle = new Bundle();
            bundle.putInt("androidx.browser.trusted.displaymode.KEY_ID", 0);
            return bundle;
        }
    }

    /* loaded from: classes.dex */
    public static class b implements a {

        /* renamed from: a  reason: collision with root package name */
        private final boolean f9795a;

        /* renamed from: b  reason: collision with root package name */
        private final int f9796b;

        public b(boolean z7, int i8) {
            this.f9795a = z7;
            this.f9796b = i8;
        }

        @Override // o.a
        public Bundle a() {
            Bundle bundle = new Bundle();
            bundle.putInt("androidx.browser.trusted.displaymode.KEY_ID", 1);
            bundle.putBoolean("androidx.browser.trusted.displaymode.KEY_STICKY", this.f9795a);
            bundle.putInt("androidx.browser.trusted.displaymode.KEY_CUTOUT_MODE", this.f9796b);
            return bundle;
        }
    }

    Bundle a();
}
