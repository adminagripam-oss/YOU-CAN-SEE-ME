package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.SeekBar;
/* loaded from: classes.dex */
public class y extends SeekBar {

    /* renamed from: a  reason: collision with root package name */
    private final z f2532a;

    public y(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, g.a.F);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.f2532a.h();
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.f2532a.i();
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f2532a.g(canvas);
    }

    public y(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        x0.a(this, getContext());
        z zVar = new z(this);
        this.f2532a = zVar;
        zVar.c(attributeSet, i8);
    }
}
