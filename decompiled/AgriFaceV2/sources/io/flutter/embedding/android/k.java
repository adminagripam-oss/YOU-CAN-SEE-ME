package io.flutter.embedding.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.hardware.HardwareBuffer;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.view.Surface;
import android.view.View;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
import java.nio.ByteBuffer;
import java.util.Locale;
/* loaded from: classes.dex */
public class k extends View implements io.flutter.embedding.engine.renderer.m {

    /* renamed from: a  reason: collision with root package name */
    private ImageReader f7620a;

    /* renamed from: b  reason: collision with root package name */
    private Image f7621b;

    /* renamed from: c  reason: collision with root package name */
    private Bitmap f7622c;

    /* renamed from: d  reason: collision with root package name */
    private FlutterRenderer f7623d;

    /* renamed from: e  reason: collision with root package name */
    private b f7624e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f7625f;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f7626a;

        static {
            int[] iArr = new int[b.values().length];
            f7626a = iArr;
            try {
                iArr[b.background.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7626a[b.overlay.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum b {
        background,
        overlay
    }

    public k(Context context, int i8, int i9, b bVar) {
        this(context, h(i8, i9), bVar);
    }

    private void f() {
        Image image = this.f7621b;
        if (image != null) {
            image.close();
            this.f7621b = null;
        }
    }

    private static ImageReader h(int i8, int i9) {
        int i10;
        int i11;
        ImageReader newInstance;
        if (i8 <= 0) {
            j("ImageReader width must be greater than 0, but given width=%d, set width=1", Integer.valueOf(i8));
            i10 = 1;
        } else {
            i10 = i8;
        }
        if (i9 <= 0) {
            j("ImageReader height must be greater than 0, but given height=%d, set height=1", Integer.valueOf(i9));
            i11 = 1;
        } else {
            i11 = i9;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            newInstance = ImageReader.newInstance(i10, i11, 1, 3, 768L);
            return newInstance;
        }
        return ImageReader.newInstance(i10, i11, 1, 3);
    }

    private void i() {
        setAlpha(0.0f);
    }

    private static void j(String str, Object... objArr) {
        z5.b.g("FlutterImageView", String.format(Locale.US, str, objArr));
    }

    private void l() {
        HardwareBuffer hardwareBuffer;
        Bitmap wrapHardwareBuffer;
        if (Build.VERSION.SDK_INT >= 29) {
            hardwareBuffer = this.f7621b.getHardwareBuffer();
            wrapHardwareBuffer = Bitmap.wrapHardwareBuffer(hardwareBuffer, ColorSpace.get(ColorSpace.Named.SRGB));
            this.f7622c = wrapHardwareBuffer;
            hardwareBuffer.close();
            return;
        }
        Image.Plane[] planes = this.f7621b.getPlanes();
        if (planes.length != 1) {
            return;
        }
        Image.Plane plane = planes[0];
        int rowStride = plane.getRowStride() / plane.getPixelStride();
        int height = this.f7621b.getHeight();
        Bitmap bitmap = this.f7622c;
        if (bitmap == null || bitmap.getWidth() != rowStride || this.f7622c.getHeight() != height) {
            this.f7622c = Bitmap.createBitmap(rowStride, height, Bitmap.Config.ARGB_8888);
        }
        ByteBuffer buffer = plane.getBuffer();
        buffer.rewind();
        this.f7622c.copyPixelsFromBuffer(buffer);
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public void a() {
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public void b() {
        if (this.f7625f) {
            setAlpha(0.0f);
            e();
            this.f7622c = null;
            f();
            invalidate();
            this.f7625f = false;
        }
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public void c() {
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public void d(FlutterRenderer flutterRenderer) {
        if (a.f7626a[this.f7624e.ordinal()] == 1) {
            flutterRenderer.C(this.f7620a.getSurface());
        }
        setAlpha(1.0f);
        this.f7623d = flutterRenderer;
        this.f7625f = true;
    }

    public boolean e() {
        if (this.f7625f) {
            Image acquireLatestImage = this.f7620a.acquireLatestImage();
            if (acquireLatestImage != null) {
                f();
                this.f7621b = acquireLatestImage;
                invalidate();
            }
            return acquireLatestImage != null;
        }
        return false;
    }

    public void g() {
        this.f7620a.close();
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public FlutterRenderer getAttachedRenderer() {
        return this.f7623d;
    }

    public ImageReader getImageReader() {
        return this.f7620a;
    }

    public Surface getSurface() {
        return this.f7620a.getSurface();
    }

    public void k(int i8, int i9) {
        if (this.f7623d == null) {
            return;
        }
        if (i8 == this.f7620a.getWidth() && i9 == this.f7620a.getHeight()) {
            return;
        }
        f();
        g();
        this.f7620a = h(i8, i9);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f7621b != null) {
            l();
        }
        Bitmap bitmap = this.f7622c;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i8, int i9, int i10, int i11) {
        if (!(i8 == this.f7620a.getWidth() && i9 == this.f7620a.getHeight()) && this.f7624e == b.background && this.f7625f) {
            k(i8, i9);
            this.f7623d.C(this.f7620a.getSurface());
        }
    }

    k(Context context, ImageReader imageReader, b bVar) {
        super(context, null);
        this.f7625f = false;
        this.f7620a = imageReader;
        this.f7624e = bVar;
        i();
    }
}
