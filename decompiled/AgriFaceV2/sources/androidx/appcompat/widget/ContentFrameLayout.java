package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
/* loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private TypedValue f2077a;

    /* renamed from: b  reason: collision with root package name */
    private TypedValue f2078b;

    /* renamed from: c  reason: collision with root package name */
    private TypedValue f2079c;

    /* renamed from: d  reason: collision with root package name */
    private TypedValue f2080d;

    /* renamed from: e  reason: collision with root package name */
    private TypedValue f2081e;

    /* renamed from: f  reason: collision with root package name */
    private TypedValue f2082f;

    /* renamed from: g  reason: collision with root package name */
    private final Rect f2083g;

    /* renamed from: h  reason: collision with root package name */
    private a f2084h;

    /* loaded from: classes.dex */
    public interface a {
        void a();

        void onDetachedFromWindow();
    }

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    public void a(int i8, int i9, int i10, int i11) {
        this.f2083g.set(i8, i9, i10, i11);
        if (androidx.core.view.p0.z(this)) {
            requestLayout();
        }
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f2081e == null) {
            this.f2081e = new TypedValue();
        }
        return this.f2081e;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f2082f == null) {
            this.f2082f = new TypedValue();
        }
        return this.f2082f;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f2079c == null) {
            this.f2079c = new TypedValue();
        }
        return this.f2079c;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f2080d == null) {
            this.f2080d = new TypedValue();
        }
        return this.f2080d;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f2077a == null) {
            this.f2077a = new TypedValue();
        }
        return this.f2077a;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f2078b == null) {
            this.f2078b = new TypedValue();
        }
        return this.f2078b;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a aVar = this.f2084h;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = this.f2084h;
        if (aVar != null) {
            aVar.onDetachedFromWindow();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r14, int r15) {
        /*
            Method dump skipped, instructions count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ContentFrameLayout.onMeasure(int, int):void");
    }

    public void setAttachListener(a aVar) {
        this.f2084h = aVar;
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2083g = new Rect();
    }
}
