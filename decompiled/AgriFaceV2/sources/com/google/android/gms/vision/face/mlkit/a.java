package com.google.android.gms.vision.face.mlkit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Rect;
import android.media.Image;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.internal.mlkit_vision_face_bundled.bn;
import com.google.android.gms.internal.mlkit_vision_face_bundled.d;
import com.google.android.gms.internal.mlkit_vision_face_bundled.d2;
import com.google.android.gms.internal.mlkit_vision_face_bundled.dn;
import com.google.android.gms.internal.mlkit_vision_face_bundled.e;
import com.google.android.gms.internal.mlkit_vision_face_bundled.g;
import com.google.android.gms.internal.mlkit_vision_face_bundled.g5;
import com.google.android.gms.internal.mlkit_vision_face_bundled.h;
import com.google.android.gms.internal.mlkit_vision_face_bundled.h6;
import com.google.android.gms.internal.mlkit_vision_face_bundled.hi;
import com.google.android.gms.internal.mlkit_vision_face_bundled.i;
import com.google.android.gms.internal.mlkit_vision_face_bundled.ji;
import com.google.android.gms.internal.mlkit_vision_face_bundled.k9;
import com.google.android.gms.internal.mlkit_vision_face_bundled.la;
import com.google.android.gms.internal.mlkit_vision_face_bundled.li;
import com.google.android.gms.internal.mlkit_vision_face_bundled.ni;
import com.google.android.gms.internal.mlkit_vision_face_bundled.nn;
import com.google.android.gms.internal.mlkit_vision_face_bundled.om;
import com.google.android.gms.internal.mlkit_vision_face_bundled.on;
import com.google.android.gms.internal.mlkit_vision_face_bundled.pi;
import com.google.android.gms.internal.mlkit_vision_face_bundled.pl;
import com.google.android.gms.internal.mlkit_vision_face_bundled.pn;
import com.google.android.gms.internal.mlkit_vision_face_bundled.qf;
import com.google.android.gms.internal.mlkit_vision_face_bundled.rk;
import com.google.android.gms.internal.mlkit_vision_face_bundled.si;
import com.google.android.gms.internal.mlkit_vision_face_bundled.tn;
import com.google.android.gms.internal.mlkit_vision_face_bundled.vi;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zh;
import com.google.android.gms.vision.face.FaceDetectorV2Jni;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import k3.j;
/* loaded from: classes.dex */
final class a extends pi {

    /* renamed from: l  reason: collision with root package name */
    private static final j f6023l = new j("FaceDetector", "");

    /* renamed from: e  reason: collision with root package name */
    private final Context f6024e;

    /* renamed from: f  reason: collision with root package name */
    private final li f6025f;

    /* renamed from: g  reason: collision with root package name */
    private final om f6026g;

    /* renamed from: h  reason: collision with root package name */
    private final FaceDetectorV2Jni f6027h;

    /* renamed from: i  reason: collision with root package name */
    private final b f6028i;

    /* renamed from: j  reason: collision with root package name */
    private final zh f6029j;

    /* renamed from: k  reason: collision with root package name */
    private long f6030k = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context, li liVar, FaceDetectorV2Jni faceDetectorV2Jni, b bVar) {
        this.f6024e = context;
        this.f6025f = liVar;
        int c8 = liVar.c();
        g A = h.A();
        A.n("models");
        h hVar = (h) A.i();
        pl A2 = om.A();
        pn A3 = tn.A();
        A3.o(hVar);
        A3.n(hVar);
        A3.p(hVar);
        A2.o(A3);
        k9 A4 = la.A();
        A4.m(hVar);
        A4.n(hVar);
        A2.m(A4);
        d A5 = e.A();
        A5.n(hVar);
        A5.o(hVar);
        A5.p(hVar);
        A5.m(hVar);
        A2.q(A5);
        boolean z7 = false;
        boolean z8 = c8 == 2;
        A2.s(z8);
        if (!z8 && liVar.f()) {
            z7 = true;
        }
        A2.n(z7);
        A2.r(liVar.a());
        A2.t(true);
        if (z8) {
            A2.w(4);
            A2.v(4);
        } else {
            int e8 = liVar.e();
            if (e8 == 1) {
                A2.w(2);
            } else if (e8 == 2) {
                A2.w(3);
            }
            int d8 = liVar.d();
            if (d8 == 1) {
                A2.v(2);
            } else if (d8 == 2) {
                A2.v(3);
            }
            int b8 = liVar.b();
            if (b8 == 1) {
                A2.u(2);
            } else if (b8 == 2) {
                A2.u(3);
            }
        }
        this.f6026g = (om) A2.i();
        this.f6027h = faceDetectorV2Jni;
        this.f6028i = bVar;
        this.f6029j = zh.a(context);
    }

    private final List d0(ByteBuffer byteBuffer, hi hiVar, int i8) {
        rk b8;
        g5 A = h6.A();
        A.o(hiVar.d());
        A.m(hiVar.a());
        A.q(f(hiVar.c()));
        A.p(i8);
        if (hiVar.e() > 0) {
            A.n(hiVar.e() * 1000);
        }
        h6 h6Var = (h6) A.i();
        if (byteBuffer.isDirect()) {
            b8 = this.f6027h.d(this.f6030k, byteBuffer, h6Var);
        } else if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            b8 = this.f6027h.b(this.f6030k, byteBuffer.array(), h6Var);
        } else {
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            b8 = this.f6027h.b(this.f6030k, bArr, h6Var);
        }
        return b8 != null ? e(b8) : new ArrayList();
    }

    private final List e(rk rkVar) {
        float f8;
        float f9;
        float f10;
        ArrayList arrayList;
        ArrayList arrayList2;
        int i8;
        int i9;
        boolean z7;
        ArrayList arrayList3 = new ArrayList();
        for (on onVar : rkVar.C().C()) {
            int i10 = 1;
            int i11 = -1;
            if (this.f6026g.J() == 3) {
                float f11 = -1.0f;
                float f12 = -1.0f;
                float f13 = -1.0f;
                for (bn bnVar : onVar.L()) {
                    String C = bnVar.C();
                    int hashCode = C.hashCode();
                    if (hashCode == -1940789646) {
                        if (C.equals("left_eye_closed")) {
                            z7 = true;
                        }
                        z7 = true;
                    } else if (hashCode != -1837755075) {
                        if (hashCode == 105428 && C.equals("joy")) {
                            z7 = false;
                        }
                        z7 = true;
                    } else {
                        if (C.equals("right_eye_closed")) {
                            z7 = true;
                        }
                        z7 = true;
                    }
                    if (!z7) {
                        f13 = bnVar.A();
                    } else if (z7) {
                        f11 = 1.0f - bnVar.A();
                    } else if (z7) {
                        f12 = 1.0f - bnVar.A();
                    }
                }
                f8 = f11;
                f9 = f12;
                f10 = f13;
            } else {
                f8 = -1.0f;
                f9 = -1.0f;
                f10 = -1.0f;
            }
            int i12 = 9;
            int i13 = 4;
            if (this.f6026g.K() == 3) {
                List<nn> M = onVar.M();
                ArrayList arrayList4 = new ArrayList();
                for (nn nnVar : M) {
                    int D = nnVar.D() - 1;
                    if (D == 0) {
                        i9 = 4;
                    } else if (D == i10) {
                        i9 = 10;
                    } else if (D != i12) {
                        switch (D) {
                            case 11:
                                i9 = 0;
                                break;
                            case 12:
                                i9 = 5;
                                break;
                            case 13:
                                i9 = 11;
                                break;
                            default:
                                switch (D) {
                                    case 238:
                                        i9 = 1;
                                        break;
                                    case 239:
                                        i9 = 7;
                                        break;
                                    case 240:
                                        i9 = 3;
                                        break;
                                    case 241:
                                        i9 = 9;
                                        break;
                                    case 242:
                                        i9 = 2;
                                        break;
                                    case 243:
                                        i9 = 8;
                                        break;
                                    default:
                                        f6023l.b("FaceDetector", "Unknown landmark type: ".concat(Integer.toString(D)));
                                        i9 = -1;
                                        break;
                                }
                        }
                    } else {
                        i9 = 6;
                    }
                    if (i9 >= 0) {
                        arrayList4.add(new vi(i9, new PointF(nnVar.A(), nnVar.B())));
                    }
                    i12 = 9;
                    i10 = 1;
                }
                arrayList = arrayList4;
            } else {
                arrayList = new ArrayList();
            }
            if (this.f6026g.K() == 4) {
                ArrayList arrayList5 = new ArrayList();
                for (si siVar : (List) onVar.B(i.f5369a)) {
                    int D2 = siVar.D() + i11;
                    switch (D2) {
                        case 1:
                            i8 = 1;
                            break;
                        case 2:
                            i8 = 2;
                            break;
                        case 3:
                            i8 = 3;
                            break;
                        case 4:
                            i8 = i13;
                            break;
                        case 5:
                            i8 = 5;
                            break;
                        case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                            i8 = 6;
                            break;
                        case 7:
                            i8 = 7;
                            break;
                        case 8:
                            i8 = 8;
                            break;
                        case 9:
                            i8 = 9;
                            break;
                        case 10:
                            i8 = 10;
                            break;
                        case 11:
                            i8 = 11;
                            break;
                        case 12:
                            i8 = 12;
                            break;
                        case 13:
                            i8 = 13;
                            break;
                        case 14:
                            i8 = 14;
                            break;
                        case 15:
                            i8 = 15;
                            break;
                        default:
                            f6023l.b("FaceDetector", "Unknown contour type: " + D2);
                            i8 = i11;
                            break;
                    }
                    if (i8 != i11) {
                        ArrayList arrayList6 = new ArrayList();
                        for (qf qfVar : siVar.C()) {
                            arrayList6.add(new PointF(qfVar.A(), qfVar.B()));
                        }
                        arrayList5.add(new ji(i8, arrayList6));
                        i13 = 4;
                        i11 = -1;
                    } else {
                        i13 = 4;
                    }
                }
                arrayList2 = arrayList5;
            } else {
                arrayList2 = new ArrayList();
            }
            dn I = onVar.I();
            arrayList3.add(new ni((int) onVar.H(), new Rect((int) I.A(), (int) I.C(), (int) I.B(), (int) I.D()), onVar.F(), onVar.E(), onVar.G(), f8, f9, f10, onVar.C() ? onVar.D() : -1.0f, arrayList, arrayList2));
        }
        return arrayList3;
    }

    private static int f(int i8) {
        if (i8 != 0) {
            if (i8 != 1) {
                if (i8 != 2) {
                    if (i8 == 3) {
                        return 2;
                    }
                    throw new IllegalArgumentException("Unsupported rotation degree: " + i8);
                }
                return 3;
            }
            return 4;
        }
        return 1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.qi
    public final void a() {
        this.f6030k = this.f6027h.a(this.f6026g, this.f6024e.getAssets());
        this.f6028i.c(this.f6025f);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.qi
    public final void b() {
        long j8 = this.f6030k;
        if (j8 > 0) {
            this.f6027h.f(j8);
            this.f6030k = -1L;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.qi
    public final List l(t3.a aVar, hi hiVar) {
        ByteBuffer a8;
        List d02;
        rk c8;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long currentTimeMillis = System.currentTimeMillis();
        int b8 = hiVar.b();
        if (b8 == -1) {
            a8 = d2.a((Bitmap) t3.b.f(aVar), true);
        } else if (b8 != 17) {
            if (b8 == 35) {
                Image.Plane[] planes = ((Image) t3.b.f(aVar)).getPlanes();
                ByteBuffer buffer = planes[0].getBuffer();
                ByteBuffer buffer2 = planes[1].getBuffer();
                ByteBuffer buffer3 = planes[2].getBuffer();
                g5 A = h6.A();
                A.o(hiVar.d());
                A.m(hiVar.a());
                A.q(f(hiVar.c()));
                if (hiVar.e() > 0) {
                    A.n(hiVar.e() * 1000);
                }
                h6 h6Var = (h6) A.i();
                if (buffer.isDirect()) {
                    c8 = this.f6027h.e(this.f6030k, buffer, buffer2, buffer3, planes[0].getPixelStride(), planes[1].getPixelStride(), planes[2].getPixelStride(), planes[0].getRowStride(), planes[1].getRowStride(), planes[2].getRowStride(), h6Var);
                } else if (buffer.hasArray() && buffer.arrayOffset() == 0) {
                    c8 = this.f6027h.c(this.f6030k, buffer.array(), buffer2.array(), buffer3.array(), planes[0].getPixelStride(), planes[1].getPixelStride(), planes[2].getPixelStride(), planes[0].getRowStride(), planes[1].getRowStride(), planes[2].getRowStride(), h6Var);
                } else {
                    byte[] bArr = new byte[buffer.remaining()];
                    buffer.get(bArr);
                    buffer.get(bArr);
                    buffer.get(bArr);
                    c8 = this.f6027h.c(this.f6030k, bArr, new byte[buffer2.remaining()], new byte[buffer3.remaining()], planes[0].getPixelStride(), planes[1].getPixelStride(), planes[2].getPixelStride(), planes[0].getRowStride(), planes[1].getRowStride(), planes[2].getRowStride(), h6Var);
                }
                d02 = c8 != null ? e(c8) : new ArrayList();
            } else if (b8 != 842094169) {
                String str = "Unsupported image format " + hiVar.b() + " at API " + Build.VERSION.SDK_INT;
                Log.e("FaceDetector", str);
                this.f6029j.c(25503, 1, currentTimeMillis, System.currentTimeMillis());
                throw c.a(str);
            } else {
                d02 = d0((ByteBuffer) t3.b.f(aVar), hiVar, 7);
            }
            List list = d02;
            this.f6028i.a(this.f6025f, hiVar, list, SystemClock.elapsedRealtime() - elapsedRealtime);
            this.f6029j.c(25503, 0, currentTimeMillis, System.currentTimeMillis());
            return list;
        } else {
            a8 = (ByteBuffer) t3.b.f(aVar);
        }
        d02 = d0(a8, hiVar, 2);
        List list2 = d02;
        this.f6028i.a(this.f6025f, hiVar, list2, SystemClock.elapsedRealtime() - elapsedRealtime);
        this.f6029j.c(25503, 0, currentTimeMillis, System.currentTimeMillis());
        return list2;
    }
}
