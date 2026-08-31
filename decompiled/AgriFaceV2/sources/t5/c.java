package t5;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import f4.f;
import f4.g;
import i6.i;
import i6.j;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import q5.e;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c implements j.c {

    /* renamed from: a  reason: collision with root package name */
    private final Context f11904a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f11905b = new HashMap();

    public c(Context context) {
        this.f11904a = context;
    }

    private void c(i iVar) {
        String str = (String) iVar.a("id");
        q5.d dVar = (q5.d) this.f11905b.get(str);
        if (dVar == null) {
            return;
        }
        dVar.close();
        this.f11905b.remove(str);
    }

    private List d(q5.a aVar, int i8) {
        q5.b b8 = aVar.b(i8);
        if (b8 != null) {
            List a8 = b8.a();
            ArrayList arrayList = new ArrayList();
            for (int i9 = 0; i9 < a8.size(); i9++) {
                arrayList.add(new double[]{((PointF) a8.get(i9)).x, ((PointF) a8.get(i9)).y});
            }
            return arrayList;
        }
        return null;
    }

    private Map e(q5.a aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("face", d(aVar, 1));
        hashMap.put("leftEyebrowTop", d(aVar, 2));
        hashMap.put("leftEyebrowBottom", d(aVar, 3));
        hashMap.put("rightEyebrowTop", d(aVar, 4));
        hashMap.put("rightEyebrowBottom", d(aVar, 5));
        hashMap.put("leftEye", d(aVar, 6));
        hashMap.put("rightEye", d(aVar, 7));
        hashMap.put("upperLipTop", d(aVar, 8));
        hashMap.put("upperLipBottom", d(aVar, 9));
        hashMap.put("lowerLipTop", d(aVar, 10));
        hashMap.put("lowerLipBottom", d(aVar, 11));
        hashMap.put("noseBridge", d(aVar, 12));
        hashMap.put("noseBottom", d(aVar, 13));
        hashMap.put("leftCheek", d(aVar, 14));
        hashMap.put("rightCheek", d(aVar, 15));
        return hashMap;
    }

    private Map f(q5.a aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("bottomMouth", j(aVar, 0));
        hashMap.put("rightMouth", j(aVar, 11));
        hashMap.put("leftMouth", j(aVar, 5));
        hashMap.put("rightEye", j(aVar, 10));
        hashMap.put("leftEye", j(aVar, 4));
        hashMap.put("rightEar", j(aVar, 9));
        hashMap.put("leftEar", j(aVar, 3));
        hashMap.put("rightCheek", j(aVar, 7));
        hashMap.put("leftCheek", j(aVar, 1));
        hashMap.put("noseBase", j(aVar, 6));
        return hashMap;
    }

    private void g(i iVar, final j.d dVar) {
        o5.a a8 = s5.b.a((Map) iVar.a("imageData"), this.f11904a, dVar);
        if (a8 == null) {
            return;
        }
        String str = (String) iVar.a("id");
        q5.d dVar2 = (q5.d) this.f11905b.get(str);
        if (dVar2 == null) {
            Map map = (Map) iVar.a("options");
            if (map == null) {
                dVar.error("FaceDetectorError", "Invalid options", null);
                return;
            } else {
                dVar2 = q5.c.a(k(map));
                this.f11905b.put(str, dVar2);
            }
        }
        dVar2.o(a8).d(new g() { // from class: t5.a
            @Override // f4.g
            public final void a(Object obj) {
                c.this.h(dVar, (List) obj);
            }
        }).c(new f() { // from class: t5.b
            @Override // f4.f
            public final void a(Exception exc) {
                c.i(j.d.this, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(j.d dVar, List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            q5.a aVar = (q5.a) it.next();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            Rect a8 = aVar.a();
            hashMap2.put("left", Integer.valueOf(a8.left));
            hashMap2.put("top", Integer.valueOf(a8.top));
            hashMap2.put("right", Integer.valueOf(a8.right));
            hashMap2.put("bottom", Integer.valueOf(a8.bottom));
            hashMap.put("rect", hashMap2);
            hashMap.put("headEulerAngleX", Float.valueOf(aVar.c()));
            hashMap.put("headEulerAngleY", Float.valueOf(aVar.d()));
            hashMap.put("headEulerAngleZ", Float.valueOf(aVar.e()));
            if (aVar.i() != null) {
                hashMap.put("smilingProbability", aVar.i());
            }
            if (aVar.g() != null) {
                hashMap.put("leftEyeOpenProbability", aVar.g());
            }
            if (aVar.h() != null) {
                hashMap.put("rightEyeOpenProbability", aVar.h());
            }
            if (aVar.j() != null) {
                hashMap.put("trackingId", aVar.j());
            }
            hashMap.put("landmarks", f(aVar));
            hashMap.put("contours", e(aVar));
            arrayList.add(hashMap);
        }
        dVar.success(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void i(j.d dVar, Exception exc) {
        dVar.error("FaceDetectorError", exc.toString(), null);
    }

    private double[] j(q5.a aVar, int i8) {
        q5.f f8 = aVar.f(i8);
        if (f8 != null) {
            return new double[]{f8.a().x, f8.a().y};
        }
        return null;
    }

    private e k(Map map) {
        int i8 = 2;
        int i9 = ((Boolean) map.get("enableClassification")).booleanValue() ? 2 : 1;
        int i10 = ((Boolean) map.get("enableLandmarks")).booleanValue() ? 2 : 1;
        int i11 = ((Boolean) map.get("enableContours")).booleanValue() ? 2 : 1;
        String str = (String) map.get("mode");
        str.hashCode();
        if (!str.equals("accurate")) {
            if (!str.equals("fast")) {
                throw new IllegalArgumentException("Not a mode:" + map.get("mode"));
            }
            i8 = 1;
        }
        e.a g8 = new e.a().c(i9).e(i10).d(i11).f((float) ((Double) map.get("minFaceSize")).doubleValue()).g(i8);
        if (((Boolean) map.get("enableTracking")).booleanValue()) {
            g8.b();
        }
        return g8.a();
    }

    @Override // i6.j.c
    public void onMethodCall(i iVar, j.d dVar) {
        String str = iVar.f7535a;
        str.hashCode();
        if (str.equals("vision#startFaceDetector")) {
            g(iVar, dVar);
        } else if (!str.equals("vision#closeFaceDetector")) {
            dVar.notImplemented();
        } else {
            c(iVar);
            dVar.success(null);
        }
    }
}
