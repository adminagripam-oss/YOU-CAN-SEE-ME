package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
/* loaded from: classes.dex */
public abstract class uk {

    /* renamed from: a  reason: collision with root package name */
    static final Charset f5673a;

    /* renamed from: b  reason: collision with root package name */
    public static final byte[] f5674b;

    static {
        Charset.forName("US-ASCII");
        f5673a = Charset.forName("UTF-8");
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        f5674b = bArr;
        ByteBuffer.wrap(bArr);
        int i8 = qj.f5558a;
        try {
            new oj(bArr, 0, 0, false, null).c(0);
        } catch (wk e8) {
            throw new IllegalArgumentException(e8);
        }
    }

    public static int a(boolean z7) {
        return z7 ? 1231 : 1237;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(int i8, byte[] bArr, int i9, int i10) {
        for (int i11 = 0; i11 < i10; i11++) {
            i8 = (i8 * 31) + bArr[i11];
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object c(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("messageType");
    }
}
