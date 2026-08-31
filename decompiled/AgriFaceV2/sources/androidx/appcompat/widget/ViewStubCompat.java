package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public final class ViewStubCompat extends View {

    /* renamed from: a  reason: collision with root package name */
    private int f2173a;

    /* renamed from: b  reason: collision with root package name */
    private int f2174b;

    /* renamed from: c  reason: collision with root package name */
    private WeakReference f2175c;

    /* renamed from: d  reason: collision with root package name */
    private LayoutInflater f2176d;

    /* loaded from: classes.dex */
    public interface a {
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public View a() {
        ViewParent parent = getParent();
        if (parent instanceof ViewGroup) {
            if (this.f2173a != 0) {
                ViewGroup viewGroup = (ViewGroup) parent;
                LayoutInflater layoutInflater = this.f2176d;
                if (layoutInflater == null) {
                    layoutInflater = LayoutInflater.from(getContext());
                }
                View inflate = layoutInflater.inflate(this.f2173a, viewGroup, false);
                int i8 = this.f2174b;
                if (i8 != -1) {
                    inflate.setId(i8);
                }
                int indexOfChild = viewGroup.indexOfChild(this);
                viewGroup.removeViewInLayout(this);
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                if (layoutParams != null) {
                    viewGroup.addView(inflate, indexOfChild, layoutParams);
                } else {
                    viewGroup.addView(inflate, indexOfChild);
                }
                this.f2175c = new WeakReference(inflate);
                return inflate;
            }
            throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
        }
        throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
    }

    @Override // android.view.View
    protected void dispatchDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
    }

    public int getInflatedId() {
        return this.f2174b;
    }

    public LayoutInflater getLayoutInflater() {
        return this.f2176d;
    }

    public int getLayoutResource() {
        return this.f2173a;
    }

    @Override // android.view.View
    protected void onMeasure(int i8, int i9) {
        setMeasuredDimension(0, 0);
    }

    public void setInflatedId(int i8) {
        this.f2174b = i8;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.f2176d = layoutInflater;
    }

    public void setLayoutResource(int i8) {
        this.f2173a = i8;
    }

    public void setOnInflateListener(a aVar) {
    }

    @Override // android.view.View
    public void setVisibility(int i8) {
        WeakReference weakReference = this.f2175c;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            if (view == null) {
                throw new IllegalStateException("setVisibility called on un-referenced view");
            }
            view.setVisibility(i8);
            return;
        }
        super.setVisibility(i8);
        if (i8 == 0 || i8 == 4) {
            a();
        }
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2173a = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g.j.f7052z3, i8, 0);
        this.f2174b = obtainStyledAttributes.getResourceId(g.j.C3, -1);
        this.f2173a = obtainStyledAttributes.getResourceId(g.j.B3, 0);
        setId(obtainStyledAttributes.getResourceId(g.j.A3, -1));
        obtainStyledAttributes.recycle();
        setVisibility(8);
        setWillNotDraw(true);
    }
}
