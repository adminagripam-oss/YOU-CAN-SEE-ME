package com.google.crypto.tink.shaded.protobuf;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes.dex */
public abstract class z {

    /* renamed from: a  reason: collision with root package name */
    static final Charset f6292a = Charset.forName("US-ASCII");

    /* renamed from: b  reason: collision with root package name */
    static final Charset f6293b = Charset.forName("UTF-8");

    /* renamed from: c  reason: collision with root package name */
    static final Charset f6294c = Charset.forName("ISO-8859-1");

    /* renamed from: d  reason: collision with root package name */
    public static final byte[] f6295d;

    /* renamed from: e  reason: collision with root package name */
    public static final ByteBuffer f6296e;

    /* renamed from: f  reason: collision with root package name */
    public static final i f6297f;

    /* loaded from: classes.dex */
    public interface a {
    }

    /* loaded from: classes.dex */
    public interface b {
    }

    /* loaded from: classes.dex */
    public interface c {
        boolean a(int i8);
    }

    /* loaded from: classes.dex */
    public interface d extends List, RandomAccess {
        void d();

        d e(int i8);

        boolean i();
    }

    static {
        byte[] bArr = new byte[0];
        f6295d = bArr;
        f6296e = ByteBuffer.wrap(bArr);
        f6297f = i.j(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object a(Object obj) {
        obj.getClass();
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object b(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(str);
    }

    public static int c(boolean z7) {
        return z7 ? 1231 : 1237;
    }

    public static int d(byte[] bArr) {
        return e(bArr, 0, bArr.length);
    }

    static int e(byte[] bArr, int i8, int i9) {
        int g8 = g(i9, bArr, i8, i9);
        if (g8 == 0) {
            return 1;
        }
        return g8;
    }

    public static int f(long j8) {
        return (int) (j8 ^ (j8 >>> 32));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int g(int i8, byte[] bArr, int i9, int i10) {
        for (int i11 = i9; i11 < i9 + i10; i11++) {
            i8 = (i8 * 31) + bArr[i11];
        }
        return i8;
    }
}
