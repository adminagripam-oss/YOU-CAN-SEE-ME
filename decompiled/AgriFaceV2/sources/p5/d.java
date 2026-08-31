package p5;

import android.graphics.Bitmap;
import android.media.Image;
import java.nio.ByteBuffer;
import k3.j;
import k3.q;
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static final j f10120a = new j("MLKitImageUtils", "");

    /* renamed from: b  reason: collision with root package name */
    private static final d f10121b = new d();

    private d() {
    }

    public static d b() {
        return f10121b;
    }

    public t3.a a(o5.a aVar) {
        Object obj;
        int h8 = aVar.h();
        if (h8 != -1) {
            if (h8 != 17) {
                if (h8 == 35) {
                    obj = aVar.j();
                } else if (h8 != 842094169) {
                    int h9 = aVar.h();
                    throw new i5.a("Unsupported image format: " + h9, 3);
                }
            }
            obj = (ByteBuffer) q.g(aVar.f());
        } else {
            obj = (Bitmap) q.g(aVar.e());
        }
        return t3.b.d0(obj);
    }

    public int c(o5.a aVar) {
        return aVar.h();
    }

    public int d(o5.a aVar) {
        if (aVar.h() == -1) {
            return ((Bitmap) q.g(aVar.e())).getAllocationByteCount();
        }
        if (aVar.h() == 17 || aVar.h() == 842094169) {
            return ((ByteBuffer) q.g(aVar.f())).limit();
        }
        if (aVar.h() != 35) {
            return 0;
        }
        return (((Image.Plane[]) q.g(aVar.k()))[0].getBuffer().limit() * 3) / 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0067 A[Catch: FileNotFoundException -> 0x00c4, TryCatch #1 {FileNotFoundException -> 0x00c4, blocks: (B:3:0x0004, B:5:0x000a, B:7:0x0018, B:33:0x006d, B:34:0x0082, B:46:0x00ad, B:48:0x00b7, B:36:0x0087, B:37:0x008b, B:38:0x008e, B:39:0x0092, B:40:0x0096, B:41:0x009a, B:42:0x009e, B:44:0x00a5, B:32:0x0067, B:29:0x0055, B:50:0x00bc, B:51:0x00c3), top: B:56:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0087 A[Catch: FileNotFoundException -> 0x00c4, TryCatch #1 {FileNotFoundException -> 0x00c4, blocks: (B:3:0x0004, B:5:0x000a, B:7:0x0018, B:33:0x006d, B:34:0x0082, B:46:0x00ad, B:48:0x00b7, B:36:0x0087, B:37:0x008b, B:38:0x008e, B:39:0x0092, B:40:0x0096, B:41:0x009a, B:42:0x009e, B:44:0x00a5, B:32:0x0067, B:29:0x0055, B:50:0x00bc, B:51:0x00c3), top: B:56:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008b A[Catch: FileNotFoundException -> 0x00c4, TryCatch #1 {FileNotFoundException -> 0x00c4, blocks: (B:3:0x0004, B:5:0x000a, B:7:0x0018, B:33:0x006d, B:34:0x0082, B:46:0x00ad, B:48:0x00b7, B:36:0x0087, B:37:0x008b, B:38:0x008e, B:39:0x0092, B:40:0x0096, B:41:0x009a, B:42:0x009e, B:44:0x00a5, B:32:0x0067, B:29:0x0055, B:50:0x00bc, B:51:0x00c3), top: B:56:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0092 A[Catch: FileNotFoundException -> 0x00c4, TryCatch #1 {FileNotFoundException -> 0x00c4, blocks: (B:3:0x0004, B:5:0x000a, B:7:0x0018, B:33:0x006d, B:34:0x0082, B:46:0x00ad, B:48:0x00b7, B:36:0x0087, B:37:0x008b, B:38:0x008e, B:39:0x0092, B:40:0x0096, B:41:0x009a, B:42:0x009e, B:44:0x00a5, B:32:0x0067, B:29:0x0055, B:50:0x00bc, B:51:0x00c3), top: B:56:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0096 A[Catch: FileNotFoundException -> 0x00c4, TryCatch #1 {FileNotFoundException -> 0x00c4, blocks: (B:3:0x0004, B:5:0x000a, B:7:0x0018, B:33:0x006d, B:34:0x0082, B:46:0x00ad, B:48:0x00b7, B:36:0x0087, B:37:0x008b, B:38:0x008e, B:39:0x0092, B:40:0x0096, B:41:0x009a, B:42:0x009e, B:44:0x00a5, B:32:0x0067, B:29:0x0055, B:50:0x00bc, B:51:0x00c3), top: B:56:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009a A[Catch: FileNotFoundException -> 0x00c4, TryCatch #1 {FileNotFoundException -> 0x00c4, blocks: (B:3:0x0004, B:5:0x000a, B:7:0x0018, B:33:0x006d, B:34:0x0082, B:46:0x00ad, B:48:0x00b7, B:36:0x0087, B:37:0x008b, B:38:0x008e, B:39:0x0092, B:40:0x0096, B:41:0x009a, B:42:0x009e, B:44:0x00a5, B:32:0x0067, B:29:0x0055, B:50:0x00bc, B:51:0x00c3), top: B:56:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x009e A[Catch: FileNotFoundException -> 0x00c4, TryCatch #1 {FileNotFoundException -> 0x00c4, blocks: (B:3:0x0004, B:5:0x000a, B:7:0x0018, B:33:0x006d, B:34:0x0082, B:46:0x00ad, B:48:0x00b7, B:36:0x0087, B:37:0x008b, B:38:0x008e, B:39:0x0092, B:40:0x0096, B:41:0x009a, B:42:0x009e, B:44:0x00a5, B:32:0x0067, B:29:0x0055, B:50:0x00bc, B:51:0x00c3), top: B:56:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a5 A[Catch: FileNotFoundException -> 0x00c4, TryCatch #1 {FileNotFoundException -> 0x00c4, blocks: (B:3:0x0004, B:5:0x000a, B:7:0x0018, B:33:0x006d, B:34:0x0082, B:46:0x00ad, B:48:0x00b7, B:36:0x0087, B:37:0x008b, B:38:0x008e, B:39:0x0092, B:40:0x0096, B:41:0x009a, B:42:0x009e, B:44:0x00a5, B:32:0x0067, B:29:0x0055, B:50:0x00bc, B:51:0x00c3), top: B:56:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.Bitmap e(android.content.ContentResolver r11, android.net.Uri r12) {
        /*
            r10 = this;
            java.lang.Class<java.lang.Throwable> r0 = java.lang.Throwable.class
            java.lang.String r1 = "MLKitImageUtils"
            android.graphics.Bitmap r9 = android.provider.MediaStore.Images.Media.getBitmap(r11, r12)     // Catch: java.io.FileNotFoundException -> Lc4
            if (r9 == 0) goto Lbc
            java.lang.String r2 = "content"
            java.lang.String r3 = r12.getScheme()     // Catch: java.io.FileNotFoundException -> Lc4
            boolean r2 = r2.equals(r3)     // Catch: java.io.FileNotFoundException -> Lc4
            r3 = 0
            r4 = 0
            if (r2 != 0) goto L25
            java.lang.String r2 = "file"
            java.lang.String r5 = r12.getScheme()     // Catch: java.io.FileNotFoundException -> Lc4
            boolean r2 = r2.equals(r5)     // Catch: java.io.FileNotFoundException -> Lc4
            if (r2 != 0) goto L25
            goto L6d
        L25:
            r2 = 1
            java.io.InputStream r11 = r11.openInputStream(r12)     // Catch: java.io.IOException -> L53
            if (r11 == 0) goto L4a
            q1.a r5 = new q1.a     // Catch: java.lang.Throwable -> L32
            r5.<init>(r11)     // Catch: java.lang.Throwable -> L32
            goto L4b
        L32:
            r5 = move-exception
            r11.close()     // Catch: java.lang.Throwable -> L37
            goto L49
        L37:
            r11 = move-exception
            java.lang.String r6 = "addSuppressed"
            java.lang.Class[] r7 = new java.lang.Class[r2]     // Catch: java.lang.Exception -> L49
            r7[r3] = r0     // Catch: java.lang.Exception -> L49
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r6, r7)     // Catch: java.lang.Exception -> L49
            java.lang.Object[] r11 = new java.lang.Object[]{r11}     // Catch: java.lang.Exception -> L49
            r0.invoke(r5, r11)     // Catch: java.lang.Exception -> L49
        L49:
            throw r5     // Catch: java.io.IOException -> L53
        L4a:
            r5 = r4
        L4b:
            if (r11 == 0) goto L64
            r11.close()     // Catch: java.io.IOException -> L51
            goto L64
        L51:
            r11 = move-exception
            goto L55
        L53:
            r11 = move-exception
            r5 = r4
        L55:
            k3.j r0 = p5.d.f10120a     // Catch: java.io.FileNotFoundException -> Lc4
            java.lang.String r6 = java.lang.String.valueOf(r12)     // Catch: java.io.FileNotFoundException -> Lc4
            java.lang.String r7 = "failed to open file to read rotation meta data: "
            java.lang.String r6 = r7.concat(r6)     // Catch: java.io.FileNotFoundException -> Lc4
            r0.d(r1, r6, r11)     // Catch: java.io.FileNotFoundException -> Lc4
        L64:
            if (r5 != 0) goto L67
            goto L6d
        L67:
            java.lang.String r11 = "Orientation"
            int r3 = r5.o(r11, r2)     // Catch: java.io.FileNotFoundException -> Lc4
        L6d:
            android.graphics.Matrix r11 = new android.graphics.Matrix     // Catch: java.io.FileNotFoundException -> Lc4
            r11.<init>()     // Catch: java.io.FileNotFoundException -> Lc4
            int r5 = r9.getWidth()     // Catch: java.io.FileNotFoundException -> Lc4
            int r6 = r9.getHeight()     // Catch: java.io.FileNotFoundException -> Lc4
            r0 = -1028390912(0xffffffffc2b40000, float:-90.0)
            r2 = 1119092736(0x42b40000, float:90.0)
            r7 = -1082130432(0xffffffffbf800000, float:-1.0)
            r8 = 1065353216(0x3f800000, float:1.0)
            switch(r3) {
                case 2: goto La5;
                case 3: goto L9e;
                case 4: goto L9a;
                case 5: goto L96;
                case 6: goto L92;
                case 7: goto L8b;
                case 8: goto L87;
                default: goto L85;
            }     // Catch: java.io.FileNotFoundException -> Lc4
        L85:
            r7 = r4
            goto Lab
        L87:
            r11.postRotate(r0)     // Catch: java.io.FileNotFoundException -> Lc4
            goto La3
        L8b:
            r11.postRotate(r0)     // Catch: java.io.FileNotFoundException -> Lc4
        L8e:
            r11.postScale(r7, r8)     // Catch: java.io.FileNotFoundException -> Lc4
            goto La3
        L92:
            r11.postRotate(r2)     // Catch: java.io.FileNotFoundException -> Lc4
            goto La3
        L96:
            r11.postRotate(r2)     // Catch: java.io.FileNotFoundException -> Lc4
            goto L8e
        L9a:
            r11.postScale(r8, r7)     // Catch: java.io.FileNotFoundException -> Lc4
            goto La3
        L9e:
            r0 = 1127481344(0x43340000, float:180.0)
            r11.postRotate(r0)     // Catch: java.io.FileNotFoundException -> Lc4
        La3:
            r7 = r11
            goto Lab
        La5:
            android.graphics.Matrix r11 = new android.graphics.Matrix     // Catch: java.io.FileNotFoundException -> Lc4
            r11.<init>()     // Catch: java.io.FileNotFoundException -> Lc4
            goto L8e
        Lab:
            if (r7 == 0) goto Lbb
            r3 = 0
            r4 = 0
            r8 = 1
            r2 = r9
            android.graphics.Bitmap r11 = android.graphics.Bitmap.createBitmap(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.io.FileNotFoundException -> Lc4
            if (r9 == r11) goto Lbb
            r9.recycle()     // Catch: java.io.FileNotFoundException -> Lc4
            r9 = r11
        Lbb:
            return r9
        Lbc:
            java.io.IOException r11 = new java.io.IOException     // Catch: java.io.FileNotFoundException -> Lc4
            java.lang.String r0 = "The image Uri could not be resolved."
            r11.<init>(r0)     // Catch: java.io.FileNotFoundException -> Lc4
            throw r11     // Catch: java.io.FileNotFoundException -> Lc4
        Lc4:
            r11 = move-exception
            k3.j r0 = p5.d.f10120a
            java.lang.String r12 = java.lang.String.valueOf(r12)
            java.lang.String r2 = "Could not open file: "
            java.lang.String r12 = r2.concat(r12)
            r0.d(r1, r12, r11)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: p5.d.e(android.content.ContentResolver, android.net.Uri):android.graphics.Bitmap");
    }
}
