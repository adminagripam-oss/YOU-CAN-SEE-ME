package androidx.camera.core;

import android.graphics.Rect;
import android.util.Size;
import y.w0;
/* loaded from: classes.dex */
public final class r extends e {

    /* renamed from: h  reason: collision with root package name */
    private final Object f2767h;

    /* renamed from: i  reason: collision with root package name */
    private final w0 f2768i;

    /* renamed from: j  reason: collision with root package name */
    private Rect f2769j;

    /* renamed from: k  reason: collision with root package name */
    private final int f2770k;

    /* renamed from: l  reason: collision with root package name */
    private final int f2771l;

    public r(n nVar, Size size, w0 w0Var) {
        super(nVar);
        int height;
        this.f2767h = new Object();
        if (size == null) {
            this.f2770k = super.getWidth();
            height = super.getHeight();
        } else {
            this.f2770k = size.getWidth();
            height = size.getHeight();
        }
        this.f2771l = height;
        this.f2768i = w0Var;
    }

    @Override // androidx.camera.core.e, androidx.camera.core.n
    public int getHeight() {
        return this.f2771l;
    }

    @Override // androidx.camera.core.e, androidx.camera.core.n
    public int getWidth() {
        return this.f2770k;
    }

    @Override // androidx.camera.core.e, androidx.camera.core.n
    public void l(Rect rect) {
        if (rect != null) {
            Rect rect2 = new Rect(rect);
            if (!rect2.intersect(0, 0, getWidth(), getHeight())) {
                rect2.setEmpty();
            }
            rect = rect2;
        }
        synchronized (this.f2767h) {
            this.f2769j = rect;
        }
    }

    @Override // androidx.camera.core.e, androidx.camera.core.n
    public w0 p() {
        return this.f2768i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(n nVar, w0 w0Var) {
        this(nVar, null, w0Var);
    }
}
