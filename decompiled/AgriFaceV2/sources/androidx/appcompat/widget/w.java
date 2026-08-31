package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
/* loaded from: classes.dex */
public class w extends RatingBar {

    /* renamed from: a  reason: collision with root package name */
    private final u f2502a;

    public w(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, g.a.D);
    }

    @Override // android.widget.RatingBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onMeasure(int i8, int i9) {
        super.onMeasure(i8, i9);
        Bitmap b8 = this.f2502a.b();
        if (b8 != null) {
            setMeasuredDimension(View.resolveSizeAndState(b8.getWidth() * getNumStars(), i8, 0), getMeasuredHeight());
        }
    }

    public w(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        x0.a(this, getContext());
        u uVar = new u(this);
        this.f2502a = uVar;
        uVar.c(attributeSet, i8);
    }
}
