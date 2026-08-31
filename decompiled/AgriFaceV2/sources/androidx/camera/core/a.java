package androidx.camera.core;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.Image;
import androidx.camera.core.n;
import b0.y2;
import java.nio.ByteBuffer;
import y.c1;
import y.w0;
/* loaded from: classes.dex */
final class a implements n {

    /* renamed from: e  reason: collision with root package name */
    private final Image f2665e;

    /* renamed from: f  reason: collision with root package name */
    private final C0015a[] f2666f;

    /* renamed from: g  reason: collision with root package name */
    private final w0 f2667g;

    /* renamed from: androidx.camera.core.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static final class C0015a implements n.a {

        /* renamed from: a  reason: collision with root package name */
        private final Image.Plane f2668a;

        C0015a(Image.Plane plane) {
            this.f2668a = plane;
        }

        @Override // androidx.camera.core.n.a
        public int a() {
            return this.f2668a.getRowStride();
        }

        @Override // androidx.camera.core.n.a
        public int b() {
            return this.f2668a.getPixelStride();
        }

        @Override // androidx.camera.core.n.a
        public ByteBuffer c() {
            return this.f2668a.getBuffer();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Image image) {
        this.f2665e = image;
        Image.Plane[] planes = image.getPlanes();
        if (planes != null) {
            this.f2666f = new C0015a[planes.length];
            for (int i8 = 0; i8 < planes.length; i8++) {
                this.f2666f[i8] = new C0015a(planes[i8]);
            }
        } else {
            this.f2666f = new C0015a[0];
        }
        this.f2667g = c1.d(y2.b(), image.getTimestamp(), 0, new Matrix());
    }

    @Override // androidx.camera.core.n, java.lang.AutoCloseable
    public void close() {
        this.f2665e.close();
    }

    @Override // androidx.camera.core.n
    public int e() {
        return this.f2665e.getFormat();
    }

    @Override // androidx.camera.core.n
    public int getHeight() {
        return this.f2665e.getHeight();
    }

    @Override // androidx.camera.core.n
    public int getWidth() {
        return this.f2665e.getWidth();
    }

    @Override // androidx.camera.core.n
    public n.a[] i() {
        return this.f2666f;
    }

    @Override // androidx.camera.core.n
    public void l(Rect rect) {
        this.f2665e.setCropRect(rect);
    }

    @Override // androidx.camera.core.n
    public w0 p() {
        return this.f2667g;
    }

    @Override // androidx.camera.core.n
    public Image z() {
        return this.f2665e;
    }
}
