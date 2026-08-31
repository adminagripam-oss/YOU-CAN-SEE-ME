package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class e0 {

    /* renamed from: l  reason: collision with root package name */
    private static final RectF f2286l = new RectF();

    /* renamed from: m  reason: collision with root package name */
    private static ConcurrentHashMap f2287m = new ConcurrentHashMap();

    /* renamed from: n  reason: collision with root package name */
    private static ConcurrentHashMap f2288n = new ConcurrentHashMap();

    /* renamed from: a  reason: collision with root package name */
    private int f2289a = 0;

    /* renamed from: b  reason: collision with root package name */
    private boolean f2290b = false;

    /* renamed from: c  reason: collision with root package name */
    private float f2291c = -1.0f;

    /* renamed from: d  reason: collision with root package name */
    private float f2292d = -1.0f;

    /* renamed from: e  reason: collision with root package name */
    private float f2293e = -1.0f;

    /* renamed from: f  reason: collision with root package name */
    private int[] f2294f = new int[0];

    /* renamed from: g  reason: collision with root package name */
    private boolean f2295g = false;

    /* renamed from: h  reason: collision with root package name */
    private TextPaint f2296h;

    /* renamed from: i  reason: collision with root package name */
    private final TextView f2297i;

    /* renamed from: j  reason: collision with root package name */
    private final Context f2298j;

    /* renamed from: k  reason: collision with root package name */
    private final f f2299k;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class a {
        static StaticLayout a(CharSequence charSequence, Layout.Alignment alignment, int i8, TextView textView, TextPaint textPaint) {
            return new StaticLayout(charSequence, textPaint, i8, alignment, textView.getLineSpacingMultiplier(), textView.getLineSpacingExtra(), textView.getIncludeFontPadding());
        }

        static int b(TextView textView) {
            return textView.getMaxLines();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class b {
        static boolean a(View view) {
            return view.isInLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class c {
        static StaticLayout a(CharSequence charSequence, Layout.Alignment alignment, int i8, int i9, TextView textView, TextPaint textPaint, f fVar) {
            StaticLayout.Builder obtain = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), textPaint, i8);
            StaticLayout.Builder hyphenationFrequency = obtain.setAlignment(alignment).setLineSpacing(textView.getLineSpacingExtra(), textView.getLineSpacingMultiplier()).setIncludePad(textView.getIncludeFontPadding()).setBreakStrategy(textView.getBreakStrategy()).setHyphenationFrequency(textView.getHyphenationFrequency());
            if (i9 == -1) {
                i9 = Integer.MAX_VALUE;
            }
            hyphenationFrequency.setMaxLines(i9);
            try {
                fVar.a(obtain, textView);
            } catch (ClassCastException unused) {
                Log.w("ACTVAutoSizeHelper", "Failed to obtain TextDirectionHeuristic, auto size may be incorrect");
            }
            return obtain.build();
        }
    }

    /* loaded from: classes.dex */
    private static class d extends f {
        d() {
        }

        @Override // androidx.appcompat.widget.e0.f
        void a(StaticLayout.Builder builder, TextView textView) {
            builder.setTextDirection((TextDirectionHeuristic) e0.m(textView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR));
        }
    }

    /* loaded from: classes.dex */
    private static class e extends d {
        e() {
        }

        @Override // androidx.appcompat.widget.e0.d, androidx.appcompat.widget.e0.f
        void a(StaticLayout.Builder builder, TextView textView) {
            TextDirectionHeuristic textDirectionHeuristic;
            textDirectionHeuristic = textView.getTextDirectionHeuristic();
            builder.setTextDirection(textDirectionHeuristic);
        }

        @Override // androidx.appcompat.widget.e0.f
        boolean b(TextView textView) {
            boolean isHorizontallyScrollable;
            isHorizontallyScrollable = textView.isHorizontallyScrollable();
            return isHorizontallyScrollable;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class f {
        f() {
        }

        abstract void a(StaticLayout.Builder builder, TextView textView);

        boolean b(TextView textView) {
            return ((Boolean) e0.m(textView, "getHorizontallyScrolling", Boolean.FALSE)).booleanValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e0(TextView textView) {
        this.f2297i = textView;
        this.f2298j = textView.getContext();
        this.f2299k = Build.VERSION.SDK_INT >= 29 ? new e() : new d();
    }

    private int[] b(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i8 : iArr) {
            if (i8 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i8)) < 0) {
                arrayList.add(Integer.valueOf(i8));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i9 = 0; i9 < size; i9++) {
            iArr2[i9] = ((Integer) arrayList.get(i9)).intValue();
        }
        return iArr2;
    }

    private void c() {
        this.f2289a = 0;
        this.f2292d = -1.0f;
        this.f2293e = -1.0f;
        this.f2291c = -1.0f;
        this.f2294f = new int[0];
        this.f2290b = false;
    }

    private int e(RectF rectF) {
        int length = this.f2294f.length;
        if (length != 0) {
            int i8 = 1;
            int i9 = length - 1;
            int i10 = 0;
            while (i8 <= i9) {
                int i11 = (i8 + i9) / 2;
                if (x(this.f2294f[i11], rectF)) {
                    int i12 = i11 + 1;
                    i10 = i8;
                    i8 = i12;
                } else {
                    i10 = i11 - 1;
                    i9 = i10;
                }
            }
            return this.f2294f[i10];
        }
        throw new IllegalStateException("No available text sizes to choose from.");
    }

    private static Method k(String str) {
        try {
            Method method = (Method) f2287m.get(str);
            if (method == null && (method = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                method.setAccessible(true);
                f2287m.put(str, method);
            }
            return method;
        } catch (Exception e8) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e8);
            return null;
        }
    }

    static Object m(Object obj, String str, Object obj2) {
        try {
            return k(str).invoke(obj, new Object[0]);
        } catch (Exception e8) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e8);
            return obj2;
        }
    }

    private void s(float f8) {
        if (f8 != this.f2297i.getPaint().getTextSize()) {
            this.f2297i.getPaint().setTextSize(f8);
            boolean a8 = b.a(this.f2297i);
            if (this.f2297i.getLayout() != null) {
                this.f2290b = false;
                try {
                    Method k8 = k("nullLayouts");
                    if (k8 != null) {
                        k8.invoke(this.f2297i, new Object[0]);
                    }
                } catch (Exception e8) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e8);
                }
                if (a8) {
                    this.f2297i.forceLayout();
                } else {
                    this.f2297i.requestLayout();
                }
                this.f2297i.invalidate();
            }
        }
    }

    private boolean u() {
        if (y() && this.f2289a == 1) {
            if (!this.f2295g || this.f2294f.length == 0) {
                int floor = ((int) Math.floor((this.f2293e - this.f2292d) / this.f2291c)) + 1;
                int[] iArr = new int[floor];
                for (int i8 = 0; i8 < floor; i8++) {
                    iArr[i8] = Math.round(this.f2292d + (i8 * this.f2291c));
                }
                this.f2294f = b(iArr);
            }
            this.f2290b = true;
        } else {
            this.f2290b = false;
        }
        return this.f2290b;
    }

    private void v(TypedArray typedArray) {
        int length = typedArray.length();
        int[] iArr = new int[length];
        if (length > 0) {
            for (int i8 = 0; i8 < length; i8++) {
                iArr[i8] = typedArray.getDimensionPixelSize(i8, -1);
            }
            this.f2294f = b(iArr);
            w();
        }
    }

    private boolean w() {
        int[] iArr = this.f2294f;
        int length = iArr.length;
        boolean z7 = length > 0;
        this.f2295g = z7;
        if (z7) {
            this.f2289a = 1;
            this.f2292d = iArr[0];
            this.f2293e = iArr[length - 1];
            this.f2291c = -1.0f;
        }
        return z7;
    }

    private boolean x(int i8, RectF rectF) {
        CharSequence transformation;
        CharSequence text = this.f2297i.getText();
        TransformationMethod transformationMethod = this.f2297i.getTransformationMethod();
        if (transformationMethod != null && (transformation = transformationMethod.getTransformation(text, this.f2297i)) != null) {
            text = transformation;
        }
        int b8 = a.b(this.f2297i);
        l(i8);
        StaticLayout d8 = d(text, (Layout.Alignment) m(this.f2297i, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL), Math.round(rectF.right), b8);
        return (b8 == -1 || (d8.getLineCount() <= b8 && d8.getLineEnd(d8.getLineCount() - 1) == text.length())) && ((float) d8.getHeight()) <= rectF.bottom;
    }

    private boolean y() {
        return !(this.f2297i instanceof l);
    }

    private void z(float f8, float f9, float f10) {
        if (f8 <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f8 + "px) is less or equal to (0px)");
        } else if (f9 <= f8) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f9 + "px) is less or equal to minimum auto-size text size (" + f8 + "px)");
        } else if (f10 <= 0.0f) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f10 + "px) is less or equal to (0px)");
        } else {
            this.f2289a = 1;
            this.f2292d = f8;
            this.f2293e = f9;
            this.f2291c = f10;
            this.f2295g = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        if (n()) {
            if (this.f2290b) {
                if (this.f2297i.getMeasuredHeight() <= 0 || this.f2297i.getMeasuredWidth() <= 0) {
                    return;
                }
                int measuredWidth = this.f2299k.b(this.f2297i) ? 1048576 : (this.f2297i.getMeasuredWidth() - this.f2297i.getTotalPaddingLeft()) - this.f2297i.getTotalPaddingRight();
                int height = (this.f2297i.getHeight() - this.f2297i.getCompoundPaddingBottom()) - this.f2297i.getCompoundPaddingTop();
                if (measuredWidth <= 0 || height <= 0) {
                    return;
                }
                RectF rectF = f2286l;
                synchronized (rectF) {
                    rectF.setEmpty();
                    rectF.right = measuredWidth;
                    rectF.bottom = height;
                    float e8 = e(rectF);
                    if (e8 != this.f2297i.getTextSize()) {
                        t(0, e8);
                    }
                }
            }
            this.f2290b = true;
        }
    }

    StaticLayout d(CharSequence charSequence, Layout.Alignment alignment, int i8, int i9) {
        return c.a(charSequence, alignment, i8, i9, this.f2297i, this.f2296h, this.f2299k);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f() {
        return Math.round(this.f2293e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g() {
        return Math.round(this.f2292d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int h() {
        return Math.round(this.f2291c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] i() {
        return this.f2294f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int j() {
        return this.f2289a;
    }

    void l(int i8) {
        TextPaint textPaint = this.f2296h;
        if (textPaint == null) {
            this.f2296h = new TextPaint();
        } else {
            textPaint.reset();
        }
        this.f2296h.set(this.f2297i.getPaint());
        this.f2296h.setTextSize(i8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean n() {
        return y() && this.f2289a != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(AttributeSet attributeSet, int i8) {
        int resourceId;
        TypedArray obtainStyledAttributes = this.f2298j.obtainStyledAttributes(attributeSet, g.j.f6954g0, i8, 0);
        TextView textView = this.f2297i;
        androidx.core.view.p0.L(textView, textView.getContext(), g.j.f6954g0, attributeSet, obtainStyledAttributes, i8, 0);
        if (obtainStyledAttributes.hasValue(g.j.f6979l0)) {
            this.f2289a = obtainStyledAttributes.getInt(g.j.f6979l0, 0);
        }
        float dimension = obtainStyledAttributes.hasValue(g.j.f6974k0) ? obtainStyledAttributes.getDimension(g.j.f6974k0, -1.0f) : -1.0f;
        float dimension2 = obtainStyledAttributes.hasValue(g.j.f6964i0) ? obtainStyledAttributes.getDimension(g.j.f6964i0, -1.0f) : -1.0f;
        float dimension3 = obtainStyledAttributes.hasValue(g.j.f6959h0) ? obtainStyledAttributes.getDimension(g.j.f6959h0, -1.0f) : -1.0f;
        if (obtainStyledAttributes.hasValue(g.j.f6969j0) && (resourceId = obtainStyledAttributes.getResourceId(g.j.f6969j0, 0)) > 0) {
            TypedArray obtainTypedArray = obtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            v(obtainTypedArray);
            obtainTypedArray.recycle();
        }
        obtainStyledAttributes.recycle();
        if (!y()) {
            this.f2289a = 0;
        } else if (this.f2289a == 1) {
            if (!this.f2295g) {
                DisplayMetrics displayMetrics = this.f2298j.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                z(dimension2, dimension3, dimension);
            }
            u();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(int i8, int i9, int i10, int i11) {
        if (y()) {
            DisplayMetrics displayMetrics = this.f2298j.getResources().getDisplayMetrics();
            z(TypedValue.applyDimension(i11, i8, displayMetrics), TypedValue.applyDimension(i11, i9, displayMetrics), TypedValue.applyDimension(i11, i10, displayMetrics));
            if (u()) {
                a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(int[] iArr, int i8) {
        if (y()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArr2 = new int[length];
                if (i8 == 0) {
                    iArr2 = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = this.f2298j.getResources().getDisplayMetrics();
                    for (int i9 = 0; i9 < length; i9++) {
                        iArr2[i9] = Math.round(TypedValue.applyDimension(i8, iArr[i9], displayMetrics));
                    }
                }
                this.f2294f = b(iArr2);
                if (!w()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                this.f2295g = false;
            }
            if (u()) {
                a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(int i8) {
        if (y()) {
            if (i8 == 0) {
                c();
            } else if (i8 != 1) {
                throw new IllegalArgumentException("Unknown auto-size text type: " + i8);
            } else {
                DisplayMetrics displayMetrics = this.f2298j.getResources().getDisplayMetrics();
                z(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
                if (u()) {
                    a();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t(int i8, float f8) {
        Context context = this.f2298j;
        s(TypedValue.applyDimension(i8, f8, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics()));
    }
}
