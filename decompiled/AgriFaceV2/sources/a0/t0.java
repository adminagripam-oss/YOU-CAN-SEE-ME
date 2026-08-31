package a0;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.Image;
import androidx.camera.core.n;
import b0.y2;
import d0.i;
import java.nio.ByteBuffer;
import java.util.Objects;
/* loaded from: classes.dex */
public final class t0 implements androidx.camera.core.n {

    /* renamed from: e  reason: collision with root package name */
    private final Object f106e;

    /* renamed from: f  reason: collision with root package name */
    private final int f107f;

    /* renamed from: g  reason: collision with root package name */
    private final int f108g;

    /* renamed from: h  reason: collision with root package name */
    private final Rect f109h;

    /* renamed from: i  reason: collision with root package name */
    n.a[] f110i;

    /* renamed from: j  reason: collision with root package name */
    private final y.w0 f111j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements n.a {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ int f112a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ int f113b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ ByteBuffer f114c;

        a(int i8, int i9, ByteBuffer byteBuffer) {
            this.f112a = i8;
            this.f113b = i9;
            this.f114c = byteBuffer;
        }

        @Override // androidx.camera.core.n.a
        public int a() {
            return this.f112a;
        }

        @Override // androidx.camera.core.n.a
        public int b() {
            return this.f113b;
        }

        @Override // androidx.camera.core.n.a
        public ByteBuffer c() {
            return this.f114c;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements y.w0 {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ long f115a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ int f116b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Matrix f117c;

        b(long j8, int i8, Matrix matrix) {
            this.f115a = j8;
            this.f116b = i8;
            this.f117c = matrix;
        }

        @Override // y.w0
        public void a(i.b bVar) {
            throw new UnsupportedOperationException("Custom ImageProxy does not contain Exif data.");
        }

        @Override // y.w0
        public y2 b() {
            throw new UnsupportedOperationException("Custom ImageProxy does not contain TagBundle");
        }

        @Override // y.w0
        public long c() {
            return this.f115a;
        }
    }

    public t0(Bitmap bitmap, Rect rect, int i8, Matrix matrix, long j8) {
        this(k0.b.e(bitmap), 4, bitmap.getWidth(), bitmap.getHeight(), rect, i8, matrix, j8);
    }

    private void b() {
        synchronized (this.f106e) {
            g1.e.m(this.f110i != null, "The image is closed.");
        }
    }

    private static y.w0 c(long j8, int i8, Matrix matrix) {
        return new b(j8, i8, matrix);
    }

    private static n.a d(ByteBuffer byteBuffer, int i8, int i9) {
        return new a(i8, i9, byteBuffer);
    }

    @Override // androidx.camera.core.n, java.lang.AutoCloseable
    public void close() {
        synchronized (this.f106e) {
            b();
            this.f110i = null;
        }
    }

    @Override // androidx.camera.core.n
    public int e() {
        synchronized (this.f106e) {
            b();
        }
        return 1;
    }

    @Override // androidx.camera.core.n
    public int getHeight() {
        int i8;
        synchronized (this.f106e) {
            b();
            i8 = this.f108g;
        }
        return i8;
    }

    @Override // androidx.camera.core.n
    public int getWidth() {
        int i8;
        synchronized (this.f106e) {
            b();
            i8 = this.f107f;
        }
        return i8;
    }

    @Override // androidx.camera.core.n
    public n.a[] i() {
        n.a[] aVarArr;
        synchronized (this.f106e) {
            b();
            n.a[] aVarArr2 = this.f110i;
            Objects.requireNonNull(aVarArr2);
            aVarArr = aVarArr2;
        }
        return aVarArr;
    }

    @Override // androidx.camera.core.n
    public void l(Rect rect) {
        synchronized (this.f106e) {
            b();
            if (rect != null) {
                this.f109h.set(rect);
            }
        }
    }

    @Override // androidx.camera.core.n
    public y.w0 p() {
        y.w0 w0Var;
        synchronized (this.f106e) {
            b();
            w0Var = this.f111j;
        }
        return w0Var;
    }

    @Override // androidx.camera.core.n
    public Image z() {
        synchronized (this.f106e) {
            b();
        }
        return null;
    }

    public t0(ByteBuffer byteBuffer, int i8, int i9, int i10, Rect rect, int i11, Matrix matrix, long j8) {
        this.f106e = new Object();
        this.f107f = i9;
        this.f108g = i10;
        this.f109h = rect;
        this.f111j = c(j8, i11, matrix);
        byteBuffer.rewind();
        this.f110i = new n.a[]{d(byteBuffer, i9 * i8, i8)};
    }

    public t0(l0.z zVar) {
        this((Bitmap) zVar.c(), zVar.b(), zVar.f(), zVar.g(), zVar.a().c());
    }
}
