package androidx.camera.core;

import android.graphics.Bitmap;
import android.media.Image;
import android.media.ImageWriter;
import android.util.Log;
import android.view.Surface;
import androidx.camera.core.ImageProcessingUtil;
import androidx.camera.core.e;
import b0.q1;
import java.nio.ByteBuffer;
import java.util.Locale;
import y.f1;
/* loaded from: classes.dex */
public abstract class ImageProcessingUtil {

    /* renamed from: a  reason: collision with root package name */
    private static int f2660a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum a {
        UNKNOWN,
        SUCCESS,
        ERROR_CONVERSION
    }

    static {
        System.loadLibrary("image_processing_util_jni");
    }

    public static boolean c(n nVar) {
        String str;
        if (!l(nVar)) {
            str = "Unsupported format for YUV to RGB";
        } else if (d(nVar) != a.ERROR_CONVERSION) {
            return true;
        } else {
            str = "One pixel shift for YUV failure";
        }
        f1.c("ImageProcessingUtil", str);
        return false;
    }

    private static a d(n nVar) {
        int width = nVar.getWidth();
        int height = nVar.getHeight();
        int a8 = nVar.i()[0].a();
        int a9 = nVar.i()[1].a();
        int a10 = nVar.i()[2].a();
        int b8 = nVar.i()[0].b();
        int b9 = nVar.i()[1].b();
        return nativeShiftPixel(nVar.i()[0].c(), a8, nVar.i()[1].c(), a9, nVar.i()[2].c(), a10, b8, b9, width, height, b8, b9, b9) != 0 ? a.ERROR_CONVERSION : a.SUCCESS;
    }

    public static n e(q1 q1Var, byte[] bArr) {
        g1.e.a(q1Var.a() == 256);
        g1.e.j(bArr);
        Surface surface = q1Var.getSurface();
        g1.e.j(surface);
        if (nativeWriteJpegToSurface(bArr, surface) != 0) {
            f1.c("ImageProcessingUtil", "Failed to enqueue JPEG image.");
            return null;
        }
        n acquireLatestImage = q1Var.acquireLatestImage();
        if (acquireLatestImage == null) {
            f1.c("ImageProcessingUtil", "Failed to get acquire JPEG image.");
        }
        return acquireLatestImage;
    }

    public static Bitmap f(n nVar) {
        if (nVar.e() == 35) {
            int width = nVar.getWidth();
            int height = nVar.getHeight();
            int a8 = nVar.i()[0].a();
            int a9 = nVar.i()[1].a();
            int a10 = nVar.i()[2].a();
            int b8 = nVar.i()[0].b();
            int b9 = nVar.i()[1].b();
            Bitmap createBitmap = Bitmap.createBitmap(nVar.getWidth(), nVar.getHeight(), Bitmap.Config.ARGB_8888);
            if (nativeConvertAndroid420ToBitmap(nVar.i()[0].c(), a8, nVar.i()[1].c(), a9, nVar.i()[2].c(), a10, b8, b9, createBitmap, createBitmap.getRowBytes(), width, height) == 0) {
                return createBitmap;
            }
            throw new UnsupportedOperationException("YUV to RGB conversion failed");
        }
        throw new IllegalArgumentException("Input image format must be YUV_420_888");
    }

    public static n g(final n nVar, q1 q1Var, ByteBuffer byteBuffer, int i8, boolean z7) {
        String str;
        if (l(nVar)) {
            long currentTimeMillis = System.currentTimeMillis();
            if (!k(i8)) {
                str = "Unsupported rotation degrees for rotate RGB";
            } else if (h(nVar, q1Var.getSurface(), byteBuffer, i8, z7) == a.ERROR_CONVERSION) {
                str = "YUV to RGB conversion failure";
            } else {
                if (Log.isLoggable("MH", 3)) {
                    f1.a("ImageProcessingUtil", String.format(Locale.US, "Image processing performance profiling, duration: [%d], image count: %d", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Integer.valueOf(f2660a)));
                    f2660a++;
                }
                final n acquireLatestImage = q1Var.acquireLatestImage();
                if (acquireLatestImage != null) {
                    s sVar = new s(acquireLatestImage);
                    sVar.b(new e.a() { // from class: y.x0
                        @Override // androidx.camera.core.e.a
                        public final void b(androidx.camera.core.n nVar2) {
                            ImageProcessingUtil.m(androidx.camera.core.n.this, nVar, nVar2);
                        }
                    });
                    return sVar;
                }
                str = "YUV to RGB acquireLatestImage failure";
            }
        } else {
            str = "Unsupported format for YUV to RGB";
        }
        f1.c("ImageProcessingUtil", str);
        return null;
    }

    private static a h(n nVar, Surface surface, ByteBuffer byteBuffer, int i8, boolean z7) {
        int width = nVar.getWidth();
        int height = nVar.getHeight();
        int a8 = nVar.i()[0].a();
        int a9 = nVar.i()[1].a();
        int a10 = nVar.i()[2].a();
        int b8 = nVar.i()[0].b();
        int b9 = nVar.i()[1].b();
        return nativeConvertAndroid420ToABGR(nVar.i()[0].c(), a8, nVar.i()[1].c(), a9, nVar.i()[2].c(), a10, b8, b9, surface, byteBuffer, width, height, z7 ? b8 : 0, z7 ? b9 : 0, z7 ? b9 : 0, i8) != 0 ? a.ERROR_CONVERSION : a.SUCCESS;
    }

    public static void i(Bitmap bitmap, ByteBuffer byteBuffer, int i8) {
        nativeCopyBetweenByteBufferAndBitmap(bitmap, byteBuffer, bitmap.getRowBytes(), i8, bitmap.getWidth(), bitmap.getHeight(), false);
    }

    public static void j(Bitmap bitmap, ByteBuffer byteBuffer, int i8) {
        nativeCopyBetweenByteBufferAndBitmap(bitmap, byteBuffer, i8, bitmap.getRowBytes(), bitmap.getWidth(), bitmap.getHeight(), true);
    }

    private static boolean k(int i8) {
        return i8 == 0 || i8 == 90 || i8 == 180 || i8 == 270;
    }

    private static boolean l(n nVar) {
        return nVar.e() == 35 && nVar.i().length == 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void m(n nVar, n nVar2, n nVar3) {
        if (nVar == null || nVar2 == null) {
            return;
        }
        nVar2.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void n(n nVar, n nVar2, n nVar3) {
        if (nVar == null || nVar2 == null) {
            return;
        }
        nVar2.close();
    }

    private static native int nativeConvertAndroid420ToABGR(ByteBuffer byteBuffer, int i8, ByteBuffer byteBuffer2, int i9, ByteBuffer byteBuffer3, int i10, int i11, int i12, Surface surface, ByteBuffer byteBuffer4, int i13, int i14, int i15, int i16, int i17, int i18);

    private static native int nativeConvertAndroid420ToBitmap(ByteBuffer byteBuffer, int i8, ByteBuffer byteBuffer2, int i9, ByteBuffer byteBuffer3, int i10, int i11, int i12, Bitmap bitmap, int i13, int i14, int i15);

    private static native int nativeCopyBetweenByteBufferAndBitmap(Bitmap bitmap, ByteBuffer byteBuffer, int i8, int i9, int i10, int i11, boolean z7);

    private static native int nativeRotateYUV(ByteBuffer byteBuffer, int i8, ByteBuffer byteBuffer2, int i9, ByteBuffer byteBuffer3, int i10, int i11, ByteBuffer byteBuffer4, int i12, int i13, ByteBuffer byteBuffer5, int i14, int i15, ByteBuffer byteBuffer6, int i16, int i17, ByteBuffer byteBuffer7, ByteBuffer byteBuffer8, ByteBuffer byteBuffer9, int i18, int i19, int i20);

    private static native int nativeShiftPixel(ByteBuffer byteBuffer, int i8, ByteBuffer byteBuffer2, int i9, ByteBuffer byteBuffer3, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17);

    private static native int nativeWriteJpegToSurface(byte[] bArr, Surface surface);

    public static n o(final n nVar, q1 q1Var, ImageWriter imageWriter, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i8) {
        String str;
        if (!l(nVar)) {
            str = "Unsupported format for rotate YUV";
        } else if (k(i8)) {
            a aVar = a.ERROR_CONVERSION;
            if ((i8 > 0 ? p(nVar, imageWriter, byteBuffer, byteBuffer2, byteBuffer3, i8) : aVar) == aVar) {
                str = "rotate YUV failure";
            } else {
                final n acquireLatestImage = q1Var.acquireLatestImage();
                if (acquireLatestImage != null) {
                    s sVar = new s(acquireLatestImage);
                    sVar.b(new e.a() { // from class: y.y0
                        @Override // androidx.camera.core.e.a
                        public final void b(androidx.camera.core.n nVar2) {
                            ImageProcessingUtil.n(androidx.camera.core.n.this, nVar, nVar2);
                        }
                    });
                    return sVar;
                }
                str = "YUV rotation acquireLatestImage failure";
            }
        } else {
            str = "Unsupported rotation degrees for rotate YUV";
        }
        f1.c("ImageProcessingUtil", str);
        return null;
    }

    private static a p(n nVar, ImageWriter imageWriter, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i8) {
        int width = nVar.getWidth();
        int height = nVar.getHeight();
        int a8 = nVar.i()[0].a();
        int a9 = nVar.i()[1].a();
        int a10 = nVar.i()[2].a();
        int b8 = nVar.i()[1].b();
        Image b9 = h0.a.b(imageWriter);
        if (b9 != null && nativeRotateYUV(nVar.i()[0].c(), a8, nVar.i()[1].c(), a9, nVar.i()[2].c(), a10, b8, b9.getPlanes()[0].getBuffer(), b9.getPlanes()[0].getRowStride(), b9.getPlanes()[0].getPixelStride(), b9.getPlanes()[1].getBuffer(), b9.getPlanes()[1].getRowStride(), b9.getPlanes()[1].getPixelStride(), b9.getPlanes()[2].getBuffer(), b9.getPlanes()[2].getRowStride(), b9.getPlanes()[2].getPixelStride(), byteBuffer, byteBuffer2, byteBuffer3, width, height, i8) == 0) {
            h0.a.d(imageWriter, b9);
            return a.SUCCESS;
        }
        return a.ERROR_CONVERSION;
    }

    public static boolean q(Surface surface, byte[] bArr) {
        g1.e.j(bArr);
        g1.e.j(surface);
        if (nativeWriteJpegToSurface(bArr, surface) != 0) {
            f1.c("ImageProcessingUtil", "Failed to enqueue JPEG image.");
            return false;
        }
        return true;
    }
}
