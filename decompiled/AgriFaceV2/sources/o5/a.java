package o5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.SystemClock;
import java.nio.ByteBuffer;
import k3.q;
import m5.h;
import p5.d;
import z3.fc;
import z3.hc;
/* loaded from: classes.dex */
public class a implements h {

    /* renamed from: a  reason: collision with root package name */
    private volatile Bitmap f9851a;

    /* renamed from: b  reason: collision with root package name */
    private volatile ByteBuffer f9852b;

    /* renamed from: c  reason: collision with root package name */
    private final int f9853c;

    /* renamed from: d  reason: collision with root package name */
    private final int f9854d;

    /* renamed from: e  reason: collision with root package name */
    private final int f9855e;

    /* renamed from: f  reason: collision with root package name */
    private final int f9856f;

    /* renamed from: g  reason: collision with root package name */
    private final Matrix f9857g;

    private a(Bitmap bitmap, int i8) {
        this.f9851a = (Bitmap) q.g(bitmap);
        this.f9853c = bitmap.getWidth();
        this.f9854d = bitmap.getHeight();
        n(i8);
        this.f9855e = i8;
        this.f9856f = -1;
        this.f9857g = null;
    }

    public static a a(Bitmap bitmap, int i8) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        a aVar = new a(bitmap, i8);
        o(-1, 1, elapsedRealtime, bitmap.getHeight(), bitmap.getWidth(), bitmap.getAllocationByteCount(), i8);
        return aVar;
    }

    public static a b(byte[] bArr, int i8, int i9, int i10, int i11) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        a aVar = new a(ByteBuffer.wrap((byte[]) q.g(bArr)), i8, i9, i10, i11);
        o(i11, 2, elapsedRealtime, i9, i8, bArr.length, i10);
        return aVar;
    }

    public static a c(ByteBuffer byteBuffer, int i8, int i9, int i10, int i11) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        a aVar = new a(byteBuffer, i8, i9, i10, i11);
        o(i11, 3, elapsedRealtime, i9, i8, byteBuffer.limit(), i10);
        return aVar;
    }

    public static a d(Context context, Uri uri) {
        q.h(context, "Please provide a valid Context");
        q.h(uri, "Please provide a valid imageUri");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Bitmap e8 = d.b().e(context.getContentResolver(), uri);
        a aVar = new a(e8, 0);
        o(-1, 4, elapsedRealtime, e8.getHeight(), e8.getWidth(), e8.getAllocationByteCount(), 0);
        return aVar;
    }

    private static int n(int i8) {
        boolean z7 = true;
        if (i8 != 0 && i8 != 90 && i8 != 180) {
            if (i8 == 270) {
                i8 = 270;
            } else {
                z7 = false;
            }
        }
        q.b(z7, "Invalid rotation. Only 0, 90, 180, 270 are supported currently.");
        return i8;
    }

    private static void o(int i8, int i9, long j8, int i10, int i11, int i12, int i13) {
        hc.a(fc.b("vision-common"), i8, i9, j8, i10, i11, i12, i13);
    }

    public Bitmap e() {
        return this.f9851a;
    }

    public ByteBuffer f() {
        return this.f9852b;
    }

    public Matrix g() {
        return this.f9857g;
    }

    public int h() {
        return this.f9856f;
    }

    public int i() {
        return this.f9854d;
    }

    public Image j() {
        return null;
    }

    public Image.Plane[] k() {
        return null;
    }

    public int l() {
        return this.f9855e;
    }

    public int m() {
        return this.f9853c;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private a(java.nio.ByteBuffer r5, int r6, int r7, int r8, int r9) {
        /*
            r4 = this;
            r4.<init>()
            r0 = 842094169(0x32315659, float:1.0322389E-8)
            r1 = 0
            r2 = 1
            if (r9 == r0) goto L12
            r0 = 17
            if (r9 != r0) goto L10
            r9 = r0
            goto L12
        L10:
            r0 = r1
            goto L13
        L12:
            r0 = r2
        L13:
            k3.q.a(r0)
            java.lang.Object r0 = k3.q.g(r5)
            java.nio.ByteBuffer r0 = (java.nio.ByteBuffer) r0
            r4.f9852b = r0
            int r0 = r5.limit()
            int r3 = r6 * r7
            if (r0 <= r3) goto L27
            r1 = r2
        L27:
            java.lang.String r0 = "Image dimension, ByteBuffer size and format don't match. Please check if the ByteBuffer is in the decalred format."
            k3.q.b(r1, r0)
            r5.rewind()
            r4.f9853c = r6
            r4.f9854d = r7
            n(r8)
            r4.f9855e = r8
            r4.f9856f = r9
            r5 = 0
            r4.f9857g = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: o5.a.<init>(java.nio.ByteBuffer, int, int, int, int):void");
    }
}
