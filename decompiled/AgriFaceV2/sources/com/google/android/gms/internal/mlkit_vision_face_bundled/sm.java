package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
abstract class sm {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void a(byte b8, byte b9, byte b10, byte b11, char[] cArr, int i8) {
        if (e(b9) || (((b8 << 28) + (b9 + 112)) >> 30) != 0 || e(b10) || e(b11)) {
            throw new wk("Protocol message had invalid UTF-8.");
        }
        int i9 = ((b8 & 7) << 18) | ((b9 & 63) << 12) | ((b10 & 63) << 6) | (b11 & 63);
        cArr[i8] = (char) ((i9 >>> 10) + 55232);
        cArr[i8 + 1] = (char) ((i9 & 1023) + 56320);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void b(byte b8, byte b9, byte b10, char[] cArr, int i8) {
        if (!e(b9)) {
            if (b8 == -32) {
                if (b9 >= -96) {
                    b8 = -32;
                }
            }
            if (b8 == -19) {
                if (b9 < -96) {
                    b8 = -19;
                }
            }
            if (!e(b10)) {
                cArr[i8] = (char) (((b8 & 15) << 12) | ((b9 & 63) << 6) | (b10 & 63));
                return;
            }
        }
        throw new wk("Protocol message had invalid UTF-8.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void c(byte b8, byte b9, char[] cArr, int i8) {
        if (b8 < -62 || e(b9)) {
            throw new wk("Protocol message had invalid UTF-8.");
        }
        cArr[i8] = (char) (((b8 & 31) << 6) | (b9 & 63));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean d(byte b8) {
        return b8 >= 0;
    }

    private static boolean e(byte b8) {
        return b8 > -65;
    }
}
