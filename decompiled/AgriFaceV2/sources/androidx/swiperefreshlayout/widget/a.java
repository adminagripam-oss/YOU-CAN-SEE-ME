package androidx.swiperefreshlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.animation.Animation;
import android.widget.ImageView;
import androidx.core.view.p0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a extends ImageView {

    /* renamed from: a  reason: collision with root package name */
    private Animation.AnimationListener f3933a;

    /* renamed from: b  reason: collision with root package name */
    private int f3934b;

    /* renamed from: c  reason: collision with root package name */
    private int f3935c;

    /* renamed from: androidx.swiperefreshlayout.widget.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0049a extends OvalShape {

        /* renamed from: e  reason: collision with root package name */
        private Paint f3936e = new Paint();

        /* renamed from: f  reason: collision with root package name */
        private int f3937f;

        /* renamed from: g  reason: collision with root package name */
        private a f3938g;

        C0049a(a aVar, int i8) {
            this.f3938g = aVar;
            this.f3937f = i8;
            a((int) rect().width());
        }

        private void a(int i8) {
            float f8 = i8 / 2;
            this.f3936e.setShader(new RadialGradient(f8, f8, this.f3937f, new int[]{1023410176, 0}, (float[]) null, Shader.TileMode.CLAMP));
        }

        @Override // android.graphics.drawable.shapes.OvalShape, android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        public void draw(Canvas canvas, Paint paint) {
            int width;
            float width2 = this.f3938g.getWidth() / 2;
            float height = this.f3938g.getHeight() / 2;
            canvas.drawCircle(width2, height, width2, this.f3936e);
            canvas.drawCircle(width2, height, width - this.f3937f, paint);
        }

        @Override // android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        protected void onResize(float f8, float f9) {
            super.onResize(f8, f9);
            a((int) f8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context) {
        super(context);
        ShapeDrawable shapeDrawable;
        float f8 = getContext().getResources().getDisplayMetrics().density;
        int i8 = (int) (1.75f * f8);
        int i9 = (int) (0.0f * f8);
        this.f3934b = (int) (3.5f * f8);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(y1.a.f13211f);
        this.f3935c = obtainStyledAttributes.getColor(y1.a.f13212g, -328966);
        obtainStyledAttributes.recycle();
        if (a()) {
            shapeDrawable = new ShapeDrawable(new OvalShape());
            p0.T(this, f8 * 4.0f);
        } else {
            ShapeDrawable shapeDrawable2 = new ShapeDrawable(new C0049a(this, this.f3934b));
            setLayerType(1, shapeDrawable2.getPaint());
            shapeDrawable2.getPaint().setShadowLayer(this.f3934b, i9, i8, 503316480);
            int i10 = this.f3934b;
            setPadding(i10, i10, i10, i10);
            shapeDrawable = shapeDrawable2;
        }
        shapeDrawable.getPaint().setColor(this.f3935c);
        p0.Q(this, shapeDrawable);
    }

    private boolean a() {
        return true;
    }

    public void b(Animation.AnimationListener animationListener) {
        this.f3933a = animationListener;
    }

    @Override // android.view.View
    public void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animationListener = this.f3933a;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    @Override // android.view.View
    public void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.f3933a;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i8, int i9) {
        super.onMeasure(i8, i9);
        if (a()) {
            return;
        }
        setMeasuredDimension(getMeasuredWidth() + (this.f3934b * 2), getMeasuredHeight() + (this.f3934b * 2));
    }

    @Override // android.view.View
    public void setBackgroundColor(int i8) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(i8);
            this.f3935c = i8;
        }
    }
}
