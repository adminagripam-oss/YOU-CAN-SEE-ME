package r5;

import a4.ca;
import a4.g6;
import a4.i8;
import a4.ka;
import a4.lb;
import a4.qd;
import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.dynamite.DynamiteModule;
import java.util.List;
/* loaded from: classes.dex */
final class n implements c {

    /* renamed from: a  reason: collision with root package name */
    private boolean f11354a;

    /* renamed from: b  reason: collision with root package name */
    private final Context f11355b;

    /* renamed from: c  reason: collision with root package name */
    private final q5.e f11356c;

    /* renamed from: d  reason: collision with root package name */
    private final int f11357d;

    /* renamed from: e  reason: collision with root package name */
    private final qd f11358e;

    /* renamed from: f  reason: collision with root package name */
    private i8 f11359f;

    /* renamed from: g  reason: collision with root package name */
    private i8 f11360g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(Context context, q5.e eVar, qd qdVar) {
        this.f11355b = context;
        this.f11356c = eVar;
        this.f11357d = h3.e.f().a(context);
        this.f11358e = qdVar;
    }

    static int c(int i8) {
        if (i8 != 1) {
            if (i8 == 2) {
                return 1;
            }
            throw new IllegalArgumentException("Invalid classification type: " + i8);
        }
        return 0;
    }

    static int d(int i8) {
        if (i8 != 1) {
            if (i8 == 2) {
                return 1;
            }
            throw new IllegalArgumentException("Invalid landmark type: " + i8);
        }
        return 0;
    }

    private static int e(int i8) {
        if (i8 != 1) {
            if (i8 == 2) {
                return 1;
            }
            throw new IllegalArgumentException("Invalid mode type: " + i8);
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00a5 A[LOOP:0: B:12:0x00a3->B:13:0x00a5, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List f(a4.i8 r15, o5.a r16) {
        /*
            r14 = this;
            a4.ne r11 = new a4.ne     // Catch: android.os.RemoteException -> Lb9
            int r2 = r16.m()     // Catch: android.os.RemoteException -> Lb9
            int r3 = r16.i()     // Catch: android.os.RemoteException -> Lb9
            r4 = 0
            long r5 = android.os.SystemClock.elapsedRealtime()     // Catch: android.os.RemoteException -> Lb9
            int r0 = r16.l()     // Catch: android.os.RemoteException -> Lb9
            int r7 = p5.b.a(r0)     // Catch: android.os.RemoteException -> Lb9
            r1 = r11
            r1.<init>(r2, r3, r4, r5, r7)     // Catch: android.os.RemoteException -> Lb9
            int r0 = r16.h()     // Catch: android.os.RemoteException -> Lb9
            r1 = 35
            r12 = 0
            if (r0 != r1) goto L89
            r13 = r14
            int r0 = r13.f11357d     // Catch: android.os.RemoteException -> Lb7
            r1 = 201500000(0xc02a560, float:1.0064601E-31)
            if (r0 < r1) goto L8a
            android.media.Image$Plane[] r0 = r16.k()     // Catch: android.os.RemoteException -> Lb7
            java.lang.Object r0 = k3.q.g(r0)     // Catch: android.os.RemoteException -> Lb7
            android.media.Image$Plane[] r0 = (android.media.Image.Plane[]) r0     // Catch: android.os.RemoteException -> Lb7
            r1 = r0[r12]     // Catch: android.os.RemoteException -> Lb7
            java.nio.ByteBuffer r1 = r1.getBuffer()     // Catch: android.os.RemoteException -> Lb7
            t3.a r2 = t3.b.d0(r1)     // Catch: android.os.RemoteException -> Lb7
            r1 = 1
            r3 = r0[r1]     // Catch: android.os.RemoteException -> Lb7
            java.nio.ByteBuffer r3 = r3.getBuffer()     // Catch: android.os.RemoteException -> Lb7
            t3.a r3 = t3.b.d0(r3)     // Catch: android.os.RemoteException -> Lb7
            r4 = 2
            r5 = r0[r4]     // Catch: android.os.RemoteException -> Lb7
            java.nio.ByteBuffer r5 = r5.getBuffer()     // Catch: android.os.RemoteException -> Lb7
            t3.a r5 = t3.b.d0(r5)     // Catch: android.os.RemoteException -> Lb7
            r6 = r0[r12]     // Catch: android.os.RemoteException -> Lb7
            int r6 = r6.getPixelStride()     // Catch: android.os.RemoteException -> Lb7
            r7 = r0[r1]     // Catch: android.os.RemoteException -> Lb7
            int r7 = r7.getPixelStride()     // Catch: android.os.RemoteException -> Lb7
            r8 = r0[r4]     // Catch: android.os.RemoteException -> Lb7
            int r8 = r8.getPixelStride()     // Catch: android.os.RemoteException -> Lb7
            r9 = r0[r12]     // Catch: android.os.RemoteException -> Lb7
            int r9 = r9.getRowStride()     // Catch: android.os.RemoteException -> Lb7
            r1 = r0[r1]     // Catch: android.os.RemoteException -> Lb7
            int r10 = r1.getRowStride()     // Catch: android.os.RemoteException -> Lb7
            r0 = r0[r4]     // Catch: android.os.RemoteException -> Lb7
            int r0 = r0.getRowStride()     // Catch: android.os.RemoteException -> Lb7
            r1 = r15
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r0
            a4.e4[] r0 = r1.e0(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch: android.os.RemoteException -> Lb7
            r1 = r16
            goto L9d
        L89:
            r13 = r14
        L8a:
            p5.c r0 = p5.c.c()     // Catch: android.os.RemoteException -> Lb7
            r1 = r16
            java.nio.ByteBuffer r0 = r0.b(r1, r12)     // Catch: android.os.RemoteException -> Lb7
            t3.a r0 = t3.b.d0(r0)     // Catch: android.os.RemoteException -> Lb7
            r2 = r15
            a4.e4[] r0 = r15.d0(r0, r11)     // Catch: android.os.RemoteException -> Lb7
        L9d:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            int r3 = r0.length
        La3:
            if (r12 >= r3) goto Lb6
            r4 = r0[r12]
            q5.a r5 = new q5.a
            android.graphics.Matrix r6 = r16.g()
            r5.<init>(r4, r6)
            r2.add(r5)
            int r12 = r12 + 1
            goto La3
        Lb6:
            return r2
        Lb7:
            r0 = move-exception
            goto Lbb
        Lb9:
            r0 = move-exception
            r13 = r14
        Lbb:
            i5.a r1 = new i5.a
            java.lang.String r2 = "Failed to detect with legacy face detector"
            r3 = 13
            r1.<init>(r2, r3, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: r5.n.f(a4.i8, o5.a):java.util.List");
    }

    @Override // r5.c
    public final Pair a(o5.a aVar) {
        List list;
        if (this.f11359f == null && this.f11360g == null) {
            b();
        }
        i8 i8Var = this.f11359f;
        if (i8Var == null && this.f11360g == null) {
            throw new i5.a("Waiting for the face detection module to be downloaded. Please wait.", 14);
        }
        List list2 = null;
        if (i8Var != null) {
            list = f(i8Var, aVar);
            if (!this.f11356c.g()) {
                i.m(list);
            }
        } else {
            list = null;
        }
        i8 i8Var2 = this.f11360g;
        if (i8Var2 != null) {
            list2 = f(i8Var2, aVar);
            i.m(list2);
        }
        return new Pair(list, list2);
    }

    @Override // r5.c
    public final boolean b() {
        i8 p8;
        if (this.f11359f == null && this.f11360g == null) {
            try {
                lb d8 = ka.d(DynamiteModule.d(this.f11355b, DynamiteModule.f5089b, "com.google.android.gms.vision.dynamite").c("com.google.android.gms.vision.face.ChimeraNativeFaceDetectorCreator"));
                t3.a d02 = t3.b.d0(this.f11355b);
                if (this.f11356c.c() != 2) {
                    if (this.f11359f == null) {
                        p8 = d8.p(d02, new g6(e(this.f11356c.e()), d(this.f11356c.d()), c(this.f11356c.b()), false, this.f11356c.g(), this.f11356c.a()));
                        this.f11359f = p8;
                    }
                    if (this.f11359f == null) {
                        Log.d("LegacyFaceDelegate", "Request face optional module download.");
                        m5.l.a(this.f11355b, "barcode");
                        this.f11354a = true;
                    }
                    k.c(this.f11358e, false, ca.NO_ERROR);
                    return false;
                }
                if (this.f11360g == null) {
                    this.f11360g = d8.p(d02, new g6(2, 2, 0, true, false, this.f11356c.a()));
                }
                if ((this.f11356c.d() == 2 || this.f11356c.b() == 2 || this.f11356c.e() == 2) && this.f11359f == null) {
                    p8 = d8.p(d02, new g6(e(this.f11356c.e()), d(this.f11356c.d()), c(this.f11356c.b()), false, this.f11356c.g(), this.f11356c.a()));
                    this.f11359f = p8;
                }
                if (this.f11359f == null && this.f11360g == null && !this.f11354a) {
                    Log.d("LegacyFaceDelegate", "Request face optional module download.");
                    m5.l.a(this.f11355b, "barcode");
                    this.f11354a = true;
                }
                k.c(this.f11358e, false, ca.NO_ERROR);
                return false;
            } catch (RemoteException e8) {
                throw new i5.a("Failed to create legacy face detector.", 13, e8);
            } catch (DynamiteModule.a e9) {
                throw new i5.a("Failed to load deprecated vision dynamite module.", 13, e9);
            }
        }
        return false;
    }

    @Override // r5.c
    public final void zzb() {
        i8 i8Var = this.f11359f;
        if (i8Var != null) {
            try {
                i8Var.b();
            } catch (RemoteException e8) {
                Log.e("LegacyFaceDelegate", "Failed to release legacy face detector.", e8);
            }
            this.f11359f = null;
        }
        i8 i8Var2 = this.f11360g;
        if (i8Var2 != null) {
            try {
                i8Var2.b();
            } catch (RemoteException e9) {
                Log.e("LegacyFaceDelegate", "Failed to release legacy face detector.", e9);
            }
            this.f11360g = null;
        }
    }
}
