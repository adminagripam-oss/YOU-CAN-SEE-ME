package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.core.content.res.f;
/* loaded from: classes.dex */
public class b1 {

    /* renamed from: a  reason: collision with root package name */
    private final Context f2216a;

    /* renamed from: b  reason: collision with root package name */
    private final TypedArray f2217b;

    /* renamed from: c  reason: collision with root package name */
    private TypedValue f2218c;

    private b1(Context context, TypedArray typedArray) {
        this.f2216a = context;
        this.f2217b = typedArray;
    }

    public static b1 s(Context context, int i8, int[] iArr) {
        return new b1(context, context.obtainStyledAttributes(i8, iArr));
    }

    public static b1 t(Context context, AttributeSet attributeSet, int[] iArr) {
        return new b1(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static b1 u(Context context, AttributeSet attributeSet, int[] iArr, int i8, int i9) {
        return new b1(context, context.obtainStyledAttributes(attributeSet, iArr, i8, i9));
    }

    public boolean a(int i8, boolean z7) {
        return this.f2217b.getBoolean(i8, z7);
    }

    public int b(int i8, int i9) {
        return this.f2217b.getColor(i8, i9);
    }

    public ColorStateList c(int i8) {
        int resourceId;
        ColorStateList a8;
        return (!this.f2217b.hasValue(i8) || (resourceId = this.f2217b.getResourceId(i8, 0)) == 0 || (a8 = h.a.a(this.f2216a, resourceId)) == null) ? this.f2217b.getColorStateList(i8) : a8;
    }

    public int d(int i8, int i9) {
        return this.f2217b.getDimensionPixelOffset(i8, i9);
    }

    public int e(int i8, int i9) {
        return this.f2217b.getDimensionPixelSize(i8, i9);
    }

    public Drawable f(int i8) {
        int resourceId;
        return (!this.f2217b.hasValue(i8) || (resourceId = this.f2217b.getResourceId(i8, 0)) == 0) ? this.f2217b.getDrawable(i8) : h.a.b(this.f2216a, resourceId);
    }

    public Drawable g(int i8) {
        int resourceId;
        if (!this.f2217b.hasValue(i8) || (resourceId = this.f2217b.getResourceId(i8, 0)) == 0) {
            return null;
        }
        return k.b().d(this.f2216a, resourceId, true);
    }

    public float h(int i8, float f8) {
        return this.f2217b.getFloat(i8, f8);
    }

    public Typeface i(int i8, int i9, f.e eVar) {
        int resourceId = this.f2217b.getResourceId(i8, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f2218c == null) {
            this.f2218c = new TypedValue();
        }
        return androidx.core.content.res.f.f(this.f2216a, resourceId, this.f2218c, i9, eVar);
    }

    public int j(int i8, int i9) {
        return this.f2217b.getInt(i8, i9);
    }

    public int k(int i8, int i9) {
        return this.f2217b.getInteger(i8, i9);
    }

    public int l(int i8, int i9) {
        return this.f2217b.getLayoutDimension(i8, i9);
    }

    public int m(int i8, int i9) {
        return this.f2217b.getResourceId(i8, i9);
    }

    public String n(int i8) {
        return this.f2217b.getString(i8);
    }

    public CharSequence o(int i8) {
        return this.f2217b.getText(i8);
    }

    public CharSequence[] p(int i8) {
        return this.f2217b.getTextArray(i8);
    }

    public TypedArray q() {
        return this.f2217b;
    }

    public boolean r(int i8) {
        return this.f2217b.hasValue(i8);
    }

    public void v() {
        this.f2217b.recycle();
    }
}
