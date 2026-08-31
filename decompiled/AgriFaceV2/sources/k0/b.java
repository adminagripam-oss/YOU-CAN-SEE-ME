package k0;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.Rational;
import android.util.Size;
import androidx.camera.core.ImageProcessingUtil;
import androidx.camera.core.n;
import d0.i;
import d0.j;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import y.f1;
/* loaded from: classes.dex */
public abstract class b {

    /* loaded from: classes.dex */
    public static final class a extends Exception {

        /* renamed from: e  reason: collision with root package name */
        private final EnumC0112a f8298e;

        /* renamed from: k0.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public enum EnumC0112a {
            ENCODE_FAILED,
            DECODE_FAILED,
            UNKNOWN
        }

        a(String str, EnumC0112a enumC0112a) {
            super(str);
            this.f8298e = enumC0112a;
        }
    }

    public static Rect a(Size size, Rational rational) {
        int i8;
        if (!h(rational)) {
            f1.l("ImageUtil", "Invalid view ratio.");
            return null;
        }
        int width = size.getWidth();
        int height = size.getHeight();
        float f8 = width;
        float f9 = height;
        int numerator = rational.getNumerator();
        int denominator = rational.getDenominator();
        int i9 = 0;
        if (rational.floatValue() > f8 / f9) {
            int round = Math.round((f8 / numerator) * denominator);
            i8 = (height - round) / 2;
            height = round;
        } else {
            int round2 = Math.round((f9 / denominator) * numerator);
            int i10 = (width - round2) / 2;
            width = round2;
            i8 = 0;
            i9 = i10;
        }
        return new Rect(i9, i8, width + i9, height + i8);
    }

    public static Bitmap b(n nVar) {
        int e8 = nVar.e();
        if (e8 != 1) {
            if (e8 != 35) {
                if (e8 == 256 || e8 == 4101) {
                    return c(nVar);
                }
                throw new IllegalArgumentException("Incorrect image format of the input image proxy: " + nVar.e() + ", only ImageFormat.YUV_420_888 and PixelFormat.RGBA_8888 are supported");
            }
            return ImageProcessingUtil.f(nVar);
        }
        return d(nVar);
    }

    private static Bitmap c(n nVar) {
        byte[] j8 = j(nVar);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(j8, 0, j8.length, null);
        if (decodeByteArray != null) {
            return decodeByteArray;
        }
        throw new UnsupportedOperationException("Decode jpeg byte array failed");
    }

    private static Bitmap d(n nVar) {
        Bitmap createBitmap = Bitmap.createBitmap(nVar.getWidth(), nVar.getHeight(), Bitmap.Config.ARGB_8888);
        nVar.i()[0].c().rewind();
        ImageProcessingUtil.j(createBitmap, nVar.i()[0].c(), nVar.i()[0].a());
        return createBitmap;
    }

    public static ByteBuffer e(Bitmap bitmap) {
        g1.e.b(bitmap.getConfig() == Bitmap.Config.ARGB_8888, "Only accept Bitmap with ARGB_8888 format for now.");
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(bitmap.getAllocationByteCount());
        ImageProcessingUtil.i(bitmap, allocateDirect, bitmap.getRowBytes());
        allocateDirect.rewind();
        return allocateDirect;
    }

    public static Rational f(int i8, Rational rational) {
        return (i8 == 90 || i8 == 270) ? g(rational) : new Rational(rational.getNumerator(), rational.getDenominator());
    }

    private static Rational g(Rational rational) {
        return rational == null ? rational : new Rational(rational.getDenominator(), rational.getNumerator());
    }

    public static boolean h(Rational rational) {
        return (rational == null || rational.floatValue() <= 0.0f || rational.isNaN()) ? false : true;
    }

    public static boolean i(int i8) {
        return i8 == 256 || i8 == 4101;
    }

    public static byte[] j(n nVar) {
        if (!i(nVar.e())) {
            throw new IllegalArgumentException("Incorrect image format of the input image proxy: " + nVar.e());
        }
        ByteBuffer c8 = nVar.i()[0].c();
        byte[] bArr = new byte[c8.capacity()];
        c8.rewind();
        c8.get(bArr);
        return bArr;
    }

    public static Bitmap k(Bitmap bitmap, int i8) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i8);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static byte[] l(n nVar, Rect rect, int i8, int i9) {
        if (nVar.e() != 35) {
            throw new IllegalArgumentException("Incorrect image format of the input image proxy: " + nVar.e());
        }
        YuvImage yuvImage = new YuvImage(m(nVar), 17, nVar.getWidth(), nVar.getHeight(), null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        j jVar = new j(byteArrayOutputStream, i.b(nVar, i9));
        if (rect == null) {
            rect = new Rect(0, 0, nVar.getWidth(), nVar.getHeight());
        }
        if (yuvImage.compressToJpeg(rect, i8, jVar)) {
            return byteArrayOutputStream.toByteArray();
        }
        throw new a("YuvImage failed to encode jpeg.", a.EnumC0112a.ENCODE_FAILED);
    }

    public static byte[] m(n nVar) {
        n.a aVar = nVar.i()[0];
        n.a aVar2 = nVar.i()[1];
        n.a aVar3 = nVar.i()[2];
        ByteBuffer c8 = aVar.c();
        ByteBuffer c9 = aVar2.c();
        ByteBuffer c10 = aVar3.c();
        c8.rewind();
        c9.rewind();
        c10.rewind();
        int remaining = c8.remaining();
        byte[] bArr = new byte[((nVar.getWidth() * nVar.getHeight()) / 2) + remaining];
        int i8 = 0;
        for (int i9 = 0; i9 < nVar.getHeight(); i9++) {
            c8.get(bArr, i8, nVar.getWidth());
            i8 += nVar.getWidth();
            c8.position(Math.min(remaining, (c8.position() - nVar.getWidth()) + aVar.a()));
        }
        int height = nVar.getHeight() / 2;
        int width = nVar.getWidth() / 2;
        int a8 = aVar3.a();
        int a9 = aVar2.a();
        int b8 = aVar3.b();
        int b9 = aVar2.b();
        byte[] bArr2 = new byte[a8];
        byte[] bArr3 = new byte[a9];
        for (int i10 = 0; i10 < height; i10++) {
            c10.get(bArr2, 0, Math.min(a8, c10.remaining()));
            c9.get(bArr3, 0, Math.min(a9, c9.remaining()));
            int i11 = 0;
            int i12 = 0;
            for (int i13 = 0; i13 < width; i13++) {
                int i14 = i8 + 1;
                bArr[i8] = bArr2[i11];
                i8 = i14 + 1;
                bArr[i14] = bArr3[i12];
                i11 += b8;
                i12 += b9;
            }
        }
        return bArr;
    }
}
