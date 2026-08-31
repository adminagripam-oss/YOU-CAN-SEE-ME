package androidx.appcompat.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
/* loaded from: classes.dex */
class b extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    final ActionBarContainer f2213a;

    /* loaded from: classes.dex */
    private static class a {
        public static void a(Drawable drawable, Outline outline) {
            drawable.getOutline(outline);
        }
    }

    public b(ActionBarContainer actionBarContainer) {
        this.f2213a = actionBarContainer;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        ActionBarContainer actionBarContainer = this.f2213a;
        if (actionBarContainer.f2009h) {
            Drawable drawable = actionBarContainer.f2008g;
            if (drawable != null) {
                drawable.draw(canvas);
                return;
            }
            return;
        }
        Drawable drawable2 = actionBarContainer.f2006e;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        ActionBarContainer actionBarContainer2 = this.f2213a;
        Drawable drawable3 = actionBarContainer2.f2007f;
        if (drawable3 == null || !actionBarContainer2.f2010i) {
            return;
        }
        drawable3.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        Drawable drawable;
        ActionBarContainer actionBarContainer = this.f2213a;
        if (!actionBarContainer.f2009h) {
            drawable = actionBarContainer.f2006e;
            if (drawable == null) {
                return;
            }
        } else if (actionBarContainer.f2008g == null) {
            return;
        } else {
            drawable = actionBarContainer.f2006e;
        }
        a.a(drawable, outline);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i8) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
