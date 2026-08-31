package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
/* loaded from: classes.dex */
public abstract class x0 {

    /* renamed from: a  reason: collision with root package name */
    private static final ThreadLocal f2522a = new ThreadLocal();

    /* renamed from: b  reason: collision with root package name */
    static final int[] f2523b = {-16842910};

    /* renamed from: c  reason: collision with root package name */
    static final int[] f2524c = {16842908};

    /* renamed from: d  reason: collision with root package name */
    static final int[] f2525d = {16843518};

    /* renamed from: e  reason: collision with root package name */
    static final int[] f2526e = {16842919};

    /* renamed from: f  reason: collision with root package name */
    static final int[] f2527f = {16842912};

    /* renamed from: g  reason: collision with root package name */
    static final int[] f2528g = {16842913};

    /* renamed from: h  reason: collision with root package name */
    static final int[] f2529h = {-16842919, -16842908};

    /* renamed from: i  reason: collision with root package name */
    static final int[] f2530i = new int[0];

    /* renamed from: j  reason: collision with root package name */
    private static final int[] f2531j = new int[1];

    public static void a(View view, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(g.j.f7044y0);
        try {
            if (!obtainStyledAttributes.hasValue(g.j.D0)) {
                Log.e("ThemeUtils", "View " + view.getClass() + " is an AppCompat widget that can only be used with a Theme.AppCompat theme (or descendant).");
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static int b(Context context, int i8) {
        ColorStateList e8 = e(context, i8);
        if (e8 == null || !e8.isStateful()) {
            TypedValue f8 = f();
            context.getTheme().resolveAttribute(16842803, f8, true);
            return d(context, i8, f8.getFloat());
        }
        return e8.getColorForState(f2523b, e8.getDefaultColor());
    }

    public static int c(Context context, int i8) {
        int[] iArr = f2531j;
        iArr[0] = i8;
        b1 t8 = b1.t(context, null, iArr);
        try {
            return t8.b(0, 0);
        } finally {
            t8.v();
        }
    }

    static int d(Context context, int i8, float f8) {
        int c8 = c(context, i8);
        return androidx.core.graphics.a.f(c8, Math.round(Color.alpha(c8) * f8));
    }

    public static ColorStateList e(Context context, int i8) {
        int[] iArr = f2531j;
        iArr[0] = i8;
        b1 t8 = b1.t(context, null, iArr);
        try {
            return t8.c(0);
        } finally {
            t8.v();
        }
    }

    private static TypedValue f() {
        ThreadLocal threadLocal = f2522a;
        TypedValue typedValue = (TypedValue) threadLocal.get();
        if (typedValue == null) {
            TypedValue typedValue2 = new TypedValue();
            threadLocal.set(typedValue2);
            return typedValue2;
        }
        return typedValue;
    }
}
