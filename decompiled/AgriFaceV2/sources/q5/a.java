package q5;

import a4.c2;
import a4.e4;
import a4.fe;
import a4.je;
import a4.mc;
import a4.qe;
import a4.xe;
import a4.ye;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final Rect f10353a;

    /* renamed from: b  reason: collision with root package name */
    private int f10354b;

    /* renamed from: c  reason: collision with root package name */
    private final float f10355c;

    /* renamed from: d  reason: collision with root package name */
    private final float f10356d;

    /* renamed from: e  reason: collision with root package name */
    private final float f10357e;

    /* renamed from: f  reason: collision with root package name */
    private final float f10358f;

    /* renamed from: g  reason: collision with root package name */
    private final float f10359g;

    /* renamed from: h  reason: collision with root package name */
    private final float f10360h;

    /* renamed from: i  reason: collision with root package name */
    private final SparseArray f10361i = new SparseArray();

    /* renamed from: j  reason: collision with root package name */
    private final SparseArray f10362j = new SparseArray();

    public a(e4 e4Var, Matrix matrix) {
        mc[] mcVarArr;
        c2[] c2VarArr;
        int length;
        float f8 = e4Var.f496c;
        float f9 = e4Var.f498e / 2.0f;
        float f10 = e4Var.f497d;
        float f11 = e4Var.f499f / 2.0f;
        Rect rect = new Rect((int) (f8 - f9), (int) (f10 - f11), (int) (f8 + f9), (int) (f10 + f11));
        this.f10353a = rect;
        if (matrix != null) {
            p5.b.d(rect, matrix);
        }
        this.f10354b = e4Var.f495b;
        for (mc mcVar : e4Var.f503j) {
            if (o(mcVar.f832d)) {
                PointF pointF = new PointF(mcVar.f830b, mcVar.f831c);
                if (matrix != null) {
                    p5.b.b(pointF, matrix);
                }
                SparseArray sparseArray = this.f10361i;
                int i8 = mcVar.f832d;
                sparseArray.put(i8, new f(i8, pointF));
            }
        }
        for (c2 c2Var : e4Var.f507n) {
            int i9 = c2Var.f301b;
            if (n(i9)) {
                PointF[] pointFArr = c2Var.f300a;
                pointFArr.getClass();
                long length2 = pointFArr.length + 5 + (length / 10);
                ArrayList arrayList = new ArrayList(length2 > 2147483647L ? Integer.MAX_VALUE : (int) length2);
                Collections.addAll(arrayList, pointFArr);
                if (matrix != null) {
                    p5.b.c(arrayList, matrix);
                }
                this.f10362j.put(i9, new b(i9, arrayList));
            }
        }
        this.f10358f = e4Var.f502i;
        this.f10359g = e4Var.f500g;
        this.f10360h = e4Var.f501h;
        this.f10357e = e4Var.f506m;
        this.f10356d = e4Var.f504k;
        this.f10355c = e4Var.f505l;
    }

    private static boolean n(int i8) {
        return i8 <= 15 && i8 > 0;
    }

    private static boolean o(int i8) {
        return i8 == 0 || i8 == 1 || i8 == 7 || i8 == 3 || i8 == 9 || i8 == 4 || i8 == 10 || i8 == 5 || i8 == 11 || i8 == 6;
    }

    public Rect a() {
        return this.f10353a;
    }

    public b b(int i8) {
        return (b) this.f10362j.get(i8);
    }

    public float c() {
        return this.f10358f;
    }

    public float d() {
        return this.f10359g;
    }

    public float e() {
        return this.f10360h;
    }

    public f f(int i8) {
        return (f) this.f10361i.get(i8);
    }

    public Float g() {
        float f8 = this.f10357e;
        if (f8 < 0.0f || f8 > 1.0f) {
            return null;
        }
        return Float.valueOf(this.f10356d);
    }

    public Float h() {
        float f8 = this.f10355c;
        if (f8 < 0.0f || f8 > 1.0f) {
            return null;
        }
        return Float.valueOf(f8);
    }

    public Float i() {
        float f8 = this.f10357e;
        if (f8 < 0.0f || f8 > 1.0f) {
            return null;
        }
        return Float.valueOf(f8);
    }

    public Integer j() {
        int i8 = this.f10354b;
        if (i8 == -1) {
            return null;
        }
        return Integer.valueOf(i8);
    }

    public final SparseArray k() {
        return this.f10362j;
    }

    public final void l(SparseArray sparseArray) {
        this.f10362j.clear();
        for (int i8 = 0; i8 < sparseArray.size(); i8++) {
            this.f10362j.put(sparseArray.keyAt(i8), (b) sparseArray.valueAt(i8));
        }
    }

    public final void m(int i8) {
        this.f10354b = -1;
    }

    public String toString() {
        xe a8 = ye.a("Face");
        a8.c("boundingBox", this.f10353a);
        a8.b("trackingId", this.f10354b);
        a8.a("rightEyeOpenProbability", this.f10355c);
        a8.a("leftEyeOpenProbability", this.f10356d);
        a8.a("smileProbability", this.f10357e);
        a8.a("eulerX", this.f10358f);
        a8.a("eulerY", this.f10359g);
        a8.a("eulerZ", this.f10360h);
        xe a9 = ye.a("Landmarks");
        for (int i8 = 0; i8 <= 11; i8++) {
            if (o(i8)) {
                a9.c("landmark_" + i8, f(i8));
            }
        }
        a8.c("landmarks", a9.toString());
        xe a10 = ye.a("Contours");
        for (int i9 = 1; i9 <= 15; i9++) {
            a10.c("Contour_" + i9, b(i9));
        }
        a8.c("contours", a10.toString());
        return a8.toString();
    }

    public a(je jeVar, Matrix matrix) {
        Rect h8 = jeVar.h();
        this.f10353a = h8;
        if (matrix != null) {
            p5.b.d(h8, matrix);
        }
        this.f10354b = jeVar.g();
        for (qe qeVar : jeVar.j()) {
            if (o(qeVar.a())) {
                PointF b8 = qeVar.b();
                if (matrix != null) {
                    p5.b.b(b8, matrix);
                }
                this.f10361i.put(qeVar.a(), new f(qeVar.a(), b8));
            }
        }
        for (fe feVar : jeVar.i()) {
            int a8 = feVar.a();
            if (n(a8)) {
                List b9 = feVar.b();
                b9.getClass();
                ArrayList arrayList = new ArrayList(b9);
                if (matrix != null) {
                    p5.b.c(arrayList, matrix);
                }
                this.f10362j.put(a8, new b(a8, arrayList));
            }
        }
        this.f10358f = jeVar.f();
        this.f10359g = jeVar.b();
        this.f10360h = -jeVar.d();
        this.f10357e = jeVar.e();
        this.f10356d = jeVar.a();
        this.f10355c = jeVar.c();
    }
}
