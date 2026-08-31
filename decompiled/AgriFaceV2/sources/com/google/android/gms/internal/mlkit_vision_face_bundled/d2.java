package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public abstract class d2 {
    public static ByteBuffer a(Bitmap bitmap, boolean z7) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i8 = width * height;
        int[] iArr = new int[i8];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int ceil = (int) Math.ceil(height / 2.0d);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(((ceil + ceil) * ((int) Math.ceil(width / 2.0d))) + i8);
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
                allocateDirect.put(i9, (byte) Math.min(255, (((((i18 * 66) + (i19 * 129)) + (i16 * 25)) + 128) >> 8) + 16));
                if (i11 % 2 == 0 && i10 % 2 == 0) {
                    int i20 = i18 * 112;
                    int i21 = i8 + 1;
                    allocateDirect.put(i8, (byte) Math.min(255, ((((i20 - (i19 * 94)) - (i16 * 18)) + 128) >> 8) + 128));
                    i8 = i21 + 1;
                    allocateDirect.put(i21, (byte) Math.min(255, (((((i18 * (-38)) - (i19 * 74)) + (i16 * 112)) + 128) >> 8) + 128));
                }
                i10++;
                i12++;
                i9 = i17;
            }
        }
        return allocateDirect;
    }
}
