package p5;

import android.graphics.Bitmap;
import android.media.Image;
import java.nio.ByteBuffer;
import k3.q;
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final c f10119a = new c();

    private c() {
    }

    public static ByteBuffer a(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return byteBuffer;
        }
        byteBuffer.rewind();
        byte[] bArr = new byte[byteBuffer.limit()];
        byteBuffer.get(bArr);
        return ByteBuffer.wrap(bArr);
    }

    public static c c() {
        return f10119a;
    }

    public static ByteBuffer e(ByteBuffer byteBuffer, boolean z7) {
        int i8;
        byteBuffer.rewind();
        int limit = byteBuffer.limit();
        int i9 = limit / 6;
        ByteBuffer allocate = z7 ? ByteBuffer.allocate(limit) : ByteBuffer.allocateDirect(limit);
        int i10 = 0;
        while (true) {
            i8 = i9 * 4;
            if (i10 >= i8) {
                break;
            }
            allocate.put(i10, byteBuffer.get(i10));
            i10++;
        }
        for (int i11 = 0; i11 < i9 + i9; i11++) {
            allocate.put(i8 + i11, byteBuffer.get(((i11 % 2) * i9) + i8 + (i11 / 2)));
        }
        return allocate;
    }

    private static final void f(Image.Plane plane, int i8, int i9, byte[] bArr, int i10, int i11) {
        ByteBuffer buffer = plane.getBuffer();
        buffer.rewind();
        int limit = ((buffer.limit() + plane.getRowStride()) - 1) / plane.getRowStride();
        if (limit == 0) {
            return;
        }
        int i12 = i8 / (i9 / limit);
        int i13 = 0;
        for (int i14 = 0; i14 < limit; i14++) {
            int i15 = i13;
            for (int i16 = 0; i16 < i12; i16++) {
                bArr[i10] = buffer.get(i15);
                i10 += i11;
                i15 += plane.getPixelStride();
            }
            i13 += plane.getRowStride();
        }
    }

    public ByteBuffer b(o5.a aVar, boolean z7) {
        int h8 = aVar.h();
        if (h8 != -1) {
            if (h8 == 17) {
                return z7 ? a((ByteBuffer) q.g(aVar.f())) : (ByteBuffer) q.g(aVar.f());
            } else if (h8 != 35) {
                if (h8 == 842094169) {
                    return e((ByteBuffer) q.g(aVar.f()), z7);
                }
                throw new i5.a("Unsupported image format", 13);
            } else {
                return d((Image.Plane[]) q.g(aVar.k()), aVar.m(), aVar.i());
            }
        }
        Bitmap bitmap = (Bitmap) q.g(aVar.e());
        if (bitmap.getConfig() == Bitmap.Config.HARDWARE) {
            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, bitmap.isMutable());
        }
        Bitmap bitmap2 = bitmap;
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        int i8 = width * height;
        int[] iArr = new int[i8];
        bitmap2.getPixels(iArr, 0, width, 0, 0, width, height);
        int ceil = (int) Math.ceil(height / 2.0d);
        int ceil2 = ((ceil + ceil) * ((int) Math.ceil(width / 2.0d))) + i8;
        ByteBuffer allocate = z7 ? ByteBuffer.allocate(ceil2) : ByteBuffer.allocateDirect(ceil2);
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < height; i11++) {
            int i12 = 0;
            while (i12 < width) {
                int i13 = iArr[i10];
                int i14 = i13 >> 16;
                int i15 = i13 >> 8;
                int i16 = i13 & 255;
                int i17 = i9 + 1;
                int i18 = i14 & 255;
                int i19 = i15 & 255;
                allocate.put(i9, (byte) Math.min(255, (((((i18 * 66) + (i19 * 129)) + (i16 * 25)) + 128) >> 8) + 16));
                if (i11 % 2 == 0 && i10 % 2 == 0) {
                    int i20 = i8 + 1;
                    allocate.put(i8, (byte) Math.min(255, (((((i18 * 112) - (i19 * 94)) - (i16 * 18)) + 128) >> 8) + 128));
                    i8 = i20 + 1;
                    allocate.put(i20, (byte) Math.min(255, (((((i18 * (-38)) - (i19 * 74)) + (i16 * 112)) + 128) >> 8) + 128));
                }
                i10++;
                i12++;
                i9 = i17;
            }
        }
        return allocate;
    }

    public ByteBuffer d(Image.Plane[] planeArr, int i8, int i9) {
        int i10 = i8 * i9;
        int i11 = i10 / 4;
        byte[] bArr = new byte[i11 + i11 + i10];
        ByteBuffer buffer = planeArr[1].getBuffer();
        ByteBuffer buffer2 = planeArr[2].getBuffer();
        int position = buffer2.position();
        int limit = buffer.limit();
        buffer2.position(position + 1);
        buffer.limit(limit - 1);
        int i12 = (i10 + i10) / 4;
        boolean z7 = buffer2.remaining() == i12 + (-2) && buffer2.compareTo(buffer) == 0;
        buffer2.position(position);
        buffer.limit(limit);
        if (z7) {
            planeArr[0].getBuffer().get(bArr, 0, i10);
            ByteBuffer buffer3 = planeArr[1].getBuffer();
            planeArr[2].getBuffer().get(bArr, i10, 1);
            buffer3.get(bArr, i10 + 1, i12 - 1);
        } else {
            f(planeArr[0], i8, i9, bArr, 0, 1);
            f(planeArr[1], i8, i9, bArr, i10 + 1, 2);
            f(planeArr[2], i8, i9, bArr, i10, 2);
        }
        return ByteBuffer.wrap(bArr);
    }
}
