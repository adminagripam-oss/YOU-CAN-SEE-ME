package m5;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class l {
    private static final y3.i A;
    private static final y3.i B;

    /* renamed from: a  reason: collision with root package name */
    public static final h3.c[] f9160a = new h3.c[0];

    /* renamed from: b  reason: collision with root package name */
    public static final h3.c f9161b;

    /* renamed from: c  reason: collision with root package name */
    public static final h3.c f9162c;

    /* renamed from: d  reason: collision with root package name */
    public static final h3.c f9163d;

    /* renamed from: e  reason: collision with root package name */
    public static final h3.c f9164e;

    /* renamed from: f  reason: collision with root package name */
    public static final h3.c f9165f;

    /* renamed from: g  reason: collision with root package name */
    public static final h3.c f9166g;

    /* renamed from: h  reason: collision with root package name */
    public static final h3.c f9167h;

    /* renamed from: i  reason: collision with root package name */
    public static final h3.c f9168i;

    /* renamed from: j  reason: collision with root package name */
    public static final h3.c f9169j;

    /* renamed from: k  reason: collision with root package name */
    public static final h3.c f9170k;

    /* renamed from: l  reason: collision with root package name */
    public static final h3.c f9171l;

    /* renamed from: m  reason: collision with root package name */
    public static final h3.c f9172m;

    /* renamed from: n  reason: collision with root package name */
    public static final h3.c f9173n;

    /* renamed from: o  reason: collision with root package name */
    public static final h3.c f9174o;

    /* renamed from: p  reason: collision with root package name */
    public static final h3.c f9175p;

    /* renamed from: q  reason: collision with root package name */
    public static final h3.c f9176q;

    /* renamed from: r  reason: collision with root package name */
    public static final h3.c f9177r;

    /* renamed from: s  reason: collision with root package name */
    public static final h3.c f9178s;

    /* renamed from: t  reason: collision with root package name */
    public static final h3.c f9179t;

    /* renamed from: u  reason: collision with root package name */
    public static final h3.c f9180u;

    /* renamed from: v  reason: collision with root package name */
    public static final h3.c f9181v;

    /* renamed from: w  reason: collision with root package name */
    public static final h3.c f9182w;

    /* renamed from: x  reason: collision with root package name */
    public static final h3.c f9183x;

    /* renamed from: y  reason: collision with root package name */
    public static final h3.c f9184y;

    /* renamed from: z  reason: collision with root package name */
    public static final h3.c f9185z;

    static {
        h3.c cVar = new h3.c("vision.barcode", 1L);
        f9161b = cVar;
        h3.c cVar2 = new h3.c("vision.custom.ica", 1L);
        f9162c = cVar2;
        h3.c cVar3 = new h3.c("vision.face", 1L);
        f9163d = cVar3;
        h3.c cVar4 = new h3.c("vision.ica", 1L);
        f9164e = cVar4;
        h3.c cVar5 = new h3.c("vision.ocr", 1L);
        f9165f = cVar5;
        f9166g = new h3.c("mlkit.ocr.chinese", 1L);
        f9167h = new h3.c("mlkit.ocr.common", 1L);
        f9168i = new h3.c("mlkit.ocr.devanagari", 1L);
        f9169j = new h3.c("mlkit.ocr.japanese", 1L);
        f9170k = new h3.c("mlkit.ocr.korean", 1L);
        h3.c cVar6 = new h3.c("mlkit.langid", 1L);
        f9171l = cVar6;
        h3.c cVar7 = new h3.c("mlkit.nlclassifier", 1L);
        f9172m = cVar7;
        h3.c cVar8 = new h3.c("tflite_dynamite", 1L);
        f9173n = cVar8;
        h3.c cVar9 = new h3.c("mlkit.barcode.ui", 1L);
        f9174o = cVar9;
        h3.c cVar10 = new h3.c("mlkit.smartreply", 1L);
        f9175p = cVar10;
        f9176q = new h3.c("mlkit.image.caption", 1L);
        f9177r = new h3.c("mlkit.docscan.detect", 1L);
        f9178s = new h3.c("mlkit.docscan.crop", 1L);
        f9179t = new h3.c("mlkit.docscan.enhance", 1L);
        f9180u = new h3.c("mlkit.docscan.ui", 1L);
        f9181v = new h3.c("mlkit.docscan.stain", 1L);
        f9182w = new h3.c("mlkit.docscan.shadow", 1L);
        f9183x = new h3.c("mlkit.quality.aesthetic", 1L);
        f9184y = new h3.c("mlkit.quality.technical", 1L);
        f9185z = new h3.c("mlkit.segmentation.subject", 1L);
        y3.h hVar = new y3.h();
        hVar.a("barcode", cVar);
        hVar.a("custom_ica", cVar2);
        hVar.a("face", cVar3);
        hVar.a("ica", cVar4);
        hVar.a("ocr", cVar5);
        hVar.a("langid", cVar6);
        hVar.a("nlclassifier", cVar7);
        hVar.a("tflite_dynamite", cVar8);
        hVar.a("barcode_ui", cVar9);
        hVar.a("smart_reply", cVar10);
        A = hVar.b();
        y3.h hVar2 = new y3.h();
        hVar2.a("com.google.android.gms.vision.barcode", cVar);
        hVar2.a("com.google.android.gms.vision.custom.ica", cVar2);
        hVar2.a("com.google.android.gms.vision.face", cVar3);
        hVar2.a("com.google.android.gms.vision.ica", cVar4);
        hVar2.a("com.google.android.gms.vision.ocr", cVar5);
        hVar2.a("com.google.android.gms.mlkit.langid", cVar6);
        hVar2.a("com.google.android.gms.mlkit.nlclassifier", cVar7);
        hVar2.a("com.google.android.gms.tflite_dynamite", cVar8);
        hVar2.a("com.google.android.gms.mlkit_smartreply", cVar10);
        B = hVar2.b();
    }

    public static void a(Context context, String str) {
        b(context, y3.f.n(str));
    }

    public static void b(Context context, List list) {
        if (h3.e.f().a(context) >= 221500000) {
            c(context, d(A, list));
            return;
        }
        Intent intent = new Intent();
        intent.setClassName("com.google.android.gms", "com.google.android.gms.vision.DependencyBroadcastReceiverProxy");
        intent.setAction("com.google.android.gms.vision.DEPENDENCY");
        intent.putExtra("com.google.android.gms.vision.DEPENDENCIES", TextUtils.join(",", list));
        intent.putExtra("requester_app_package", context.getApplicationInfo().packageName);
        context.sendBroadcast(intent);
    }

    public static void c(Context context, final h3.c[] cVarArr) {
        n3.c.a(context).d(n3.f.d().a(new i3.g() { // from class: m5.a0
            @Override // i3.g
            public final h3.c[] b() {
                h3.c[] cVarArr2 = l.f9160a;
                return cVarArr;
            }
        }).b()).c(new f4.f() { // from class: m5.b0
            @Override // f4.f
            public final void a(Exception exc) {
                Log.e("OptionalModuleUtils", "Failed to request modules install request", exc);
            }
        });
    }

    private static h3.c[] d(Map map, List list) {
        h3.c[] cVarArr = new h3.c[list.size()];
        for (int i8 = 0; i8 < list.size(); i8++) {
            cVarArr[i8] = (h3.c) k3.q.g((h3.c) map.get(list.get(i8)));
        }
        return cVarArr;
    }
}
