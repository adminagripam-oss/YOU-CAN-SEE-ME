package androidx.browser.customtabs;

import android.os.Bundle;
/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final Integer f2549a;

    /* renamed from: b  reason: collision with root package name */
    public final Integer f2550b;

    /* renamed from: c  reason: collision with root package name */
    public final Integer f2551c;

    /* renamed from: d  reason: collision with root package name */
    public final Integer f2552d;

    /* renamed from: androidx.browser.customtabs.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0011a {

        /* renamed from: a  reason: collision with root package name */
        private Integer f2553a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f2554b;

        /* renamed from: c  reason: collision with root package name */
        private Integer f2555c;

        /* renamed from: d  reason: collision with root package name */
        private Integer f2556d;

        public a a() {
            return new a(this.f2553a, this.f2554b, this.f2555c, this.f2556d);
        }

        public C0011a b(int i8) {
            this.f2555c = Integer.valueOf(i8 | (-16777216));
            return this;
        }

        public C0011a c(int i8) {
            this.f2556d = Integer.valueOf(i8);
            return this;
        }

        public C0011a d(int i8) {
            this.f2554b = Integer.valueOf(i8);
            return this;
        }

        public C0011a e(int i8) {
            this.f2553a = Integer.valueOf(i8 | (-16777216));
            return this;
        }
    }

    a(Integer num, Integer num2, Integer num3, Integer num4) {
        this.f2549a = num;
        this.f2550b = num2;
        this.f2551c = num3;
        this.f2552d = num4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bundle a() {
        Bundle bundle = new Bundle();
        Integer num = this.f2549a;
        if (num != null) {
            bundle.putInt("android.support.customtabs.extra.TOOLBAR_COLOR", num.intValue());
        }
        Integer num2 = this.f2550b;
        if (num2 != null) {
            bundle.putInt("android.support.customtabs.extra.SECONDARY_TOOLBAR_COLOR", num2.intValue());
        }
        Integer num3 = this.f2551c;
        if (num3 != null) {
            bundle.putInt("androidx.browser.customtabs.extra.NAVIGATION_BAR_COLOR", num3.intValue());
        }
        Integer num4 = this.f2552d;
        if (num4 != null) {
            bundle.putInt("androidx.browser.customtabs.extra.NAVIGATION_BAR_DIVIDER_COLOR", num4.intValue());
        }
        return bundle;
    }
}
