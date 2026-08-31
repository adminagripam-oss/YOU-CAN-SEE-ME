package androidx.appcompat.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.face.ModuleDescriptor;
/* loaded from: classes.dex */
class u {

    /* renamed from: c  reason: collision with root package name */
    private static final int[] f2487c = {16843067, 16843068};

    /* renamed from: a  reason: collision with root package name */
    private final ProgressBar f2488a;

    /* renamed from: b  reason: collision with root package name */
    private Bitmap f2489b;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {
        public static void a(LayerDrawable layerDrawable, LayerDrawable layerDrawable2, int i8) {
            layerDrawable2.setLayerGravity(i8, layerDrawable.getLayerGravity(i8));
            layerDrawable2.setLayerWidth(i8, layerDrawable.getLayerWidth(i8));
            layerDrawable2.setLayerHeight(i8, layerDrawable.getLayerHeight(i8));
            layerDrawable2.setLayerInsetLeft(i8, layerDrawable.getLayerInsetLeft(i8));
            layerDrawable2.setLayerInsetRight(i8, layerDrawable.getLayerInsetRight(i8));
            layerDrawable2.setLayerInsetTop(i8, layerDrawable.getLayerInsetTop(i8));
            layerDrawable2.setLayerInsetBottom(i8, layerDrawable.getLayerInsetBottom(i8));
            layerDrawable2.setLayerInsetStart(i8, layerDrawable.getLayerInsetStart(i8));
            layerDrawable2.setLayerInsetEnd(i8, layerDrawable.getLayerInsetEnd(i8));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public u(ProgressBar progressBar) {
        this.f2488a = progressBar;
    }

    private Shape a() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    private Drawable e(Drawable drawable) {
        if (drawable instanceof AnimationDrawable) {
            AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            AnimationDrawable animationDrawable2 = new AnimationDrawable();
            animationDrawable2.setOneShot(animationDrawable.isOneShot());
            for (int i8 = 0; i8 < numberOfFrames; i8++) {
                Drawable d8 = d(animationDrawable.getFrame(i8), true);
                d8.setLevel(ModuleDescriptor.MODULE_VERSION);
                animationDrawable2.addFrame(d8, animationDrawable.getDuration(i8));
            }
            animationDrawable2.setLevel(ModuleDescriptor.MODULE_VERSION);
            return animationDrawable2;
        }
        return drawable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bitmap b() {
        return this.f2489b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(AttributeSet attributeSet, int i8) {
        b1 u7 = b1.u(this.f2488a.getContext(), attributeSet, f2487c, i8, 0);
        Drawable g8 = u7.g(0);
        if (g8 != null) {
            this.f2488a.setIndeterminateDrawable(e(g8));
        }
        Drawable g9 = u7.g(1);
        if (g9 != null) {
            this.f2488a.setProgressDrawable(d(g9, false));
        }
        u7.v();
    }

    Drawable d(Drawable drawable, boolean z7) {
        if (drawable instanceof androidx.core.graphics.drawable.b) {
            androidx.core.graphics.drawable.b bVar = (androidx.core.graphics.drawable.b) drawable;
            Drawable b8 = bVar.b();
            if (b8 != null) {
                bVar.a(d(b8, z7));
            }
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            Drawable[] drawableArr = new Drawable[numberOfLayers];
            for (int i8 = 0; i8 < numberOfLayers; i8++) {
                int id = layerDrawable.getId(i8);
                drawableArr[i8] = d(layerDrawable.getDrawable(i8), id == 16908301 || id == 16908303);
            }
            LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
            for (int i9 = 0; i9 < numberOfLayers; i9++) {
                layerDrawable2.setId(i9, layerDrawable.getId(i9));
                a.a(layerDrawable, layerDrawable2, i9);
            }
            return layerDrawable2;
        } else if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (this.f2489b == null) {
                this.f2489b = bitmap;
            }
            ShapeDrawable shapeDrawable = new ShapeDrawable(a());
            shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
            shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
            return z7 ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
        }
        return drawable;
    }
}
