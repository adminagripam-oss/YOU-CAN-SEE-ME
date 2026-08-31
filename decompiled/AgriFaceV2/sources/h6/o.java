package h6;

import i6.j;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    public final i6.j f7311a;

    /* renamed from: b  reason: collision with root package name */
    private h f7312b;

    /* renamed from: c  reason: collision with root package name */
    final j.c f7313c;

    /* loaded from: classes.dex */
    class a implements j.c {
        a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:71:0x0144 A[Catch: JSONException -> 0x0249, TryCatch #9 {JSONException -> 0x0249, blocks: (B:6:0x002b, B:7:0x002f, B:57:0x00dc, B:58:0x00e1, B:59:0x00ec, B:60:0x00f1, B:61:0x0105, B:62:0x010a, B:63:0x011a, B:65:0x011e, B:69:0x0138, B:71:0x0144, B:67:0x0123, B:72:0x014d, B:73:0x0157, B:82:0x0189, B:83:0x0194, B:111:0x0215, B:116:0x022f, B:80:0x0180, B:81:0x0184, B:90:0x01b8, B:97:0x01d6, B:102:0x01f2, B:109:0x020f, B:114:0x0229, B:119:0x0243, B:9:0x0034, B:12:0x003f, B:15:0x004a, B:18:0x0056, B:21:0x0062, B:24:0x006d, B:27:0x0078, B:30:0x0082, B:33:0x008c, B:36:0x0096, B:39:0x00a0, B:42:0x00aa, B:45:0x00b5, B:48:0x00c0, B:51:0x00cb, B:99:0x01db), top: B:132:0x002b, inners: #0, #2, #4, #5 }] */
        @Override // i6.j.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onMethodCall(i6.i r6, i6.j.d r7) {
            /*
                Method dump skipped, instructions count: 708
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: h6.o.a.onMethodCall(i6.i, i6.j$d):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f7315a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f7316b;

        /* renamed from: c  reason: collision with root package name */
        static final /* synthetic */ int[] f7317c;

        static {
            int[] iArr = new int[k.values().length];
            f7317c = iArr;
            try {
                iArr[k.LEAN_BACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7317c[k.IMMERSIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7317c[k.IMMERSIVE_STICKY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7317c[k.EDGE_TO_EDGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[l.values().length];
            f7316b = iArr2;
            try {
                iArr2[l.TOP_OVERLAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7316b[l.BOTTOM_OVERLAYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr3 = new int[f.values().length];
            f7315a = iArr3;
            try {
                iArr3[f.PORTRAIT_UP.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f7315a[f.PORTRAIT_DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f7315a[f.LANDSCAPE_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f7315a[f.LANDSCAPE_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final int f7318a;

        /* renamed from: b  reason: collision with root package name */
        public final String f7319b;

        public c(int i8, String str) {
            this.f7318a = i8;
            this.f7319b = str;
        }
    }

    /* loaded from: classes.dex */
    public enum d {
        LIGHT("Brightness.light"),
        DARK("Brightness.dark");
        

        /* renamed from: e  reason: collision with root package name */
        private String f7323e;

        d(String str) {
            this.f7323e = str;
        }

        static d e(String str) {
            d[] values;
            for (d dVar : values()) {
                if (dVar.f7323e.equals(str)) {
                    return dVar;
                }
            }
            throw new NoSuchFieldException("No such Brightness: " + str);
        }
    }

    /* loaded from: classes.dex */
    public enum e {
        PLAIN_TEXT("text/plain");
        

        /* renamed from: e  reason: collision with root package name */
        private String f7326e;

        e(String str) {
            this.f7326e = str;
        }

        static e e(String str) {
            e[] values;
            for (e eVar : values()) {
                if (eVar.f7326e.equals(str)) {
                    return eVar;
                }
            }
            throw new NoSuchFieldException("No such ClipboardContentFormat: " + str);
        }
    }

    /* loaded from: classes.dex */
    public enum f {
        PORTRAIT_UP("DeviceOrientation.portraitUp"),
        PORTRAIT_DOWN("DeviceOrientation.portraitDown"),
        LANDSCAPE_LEFT("DeviceOrientation.landscapeLeft"),
        LANDSCAPE_RIGHT("DeviceOrientation.landscapeRight");
        

        /* renamed from: e  reason: collision with root package name */
        private String f7332e;

        f(String str) {
            this.f7332e = str;
        }

        static f e(String str) {
            f[] values;
            for (f fVar : values()) {
                if (fVar.f7332e.equals(str)) {
                    return fVar;
                }
            }
            throw new NoSuchFieldException("No such DeviceOrientation: " + str);
        }
    }

    /* loaded from: classes.dex */
    public enum g {
        STANDARD(null),
        LIGHT_IMPACT("HapticFeedbackType.lightImpact"),
        MEDIUM_IMPACT("HapticFeedbackType.mediumImpact"),
        HEAVY_IMPACT("HapticFeedbackType.heavyImpact"),
        SELECTION_CLICK("HapticFeedbackType.selectionClick");
        

        /* renamed from: e  reason: collision with root package name */
        private final String f7339e;

        g(String str) {
            this.f7339e = str;
        }

        static g e(String str) {
            g[] values;
            for (g gVar : values()) {
                String str2 = gVar.f7339e;
                if ((str2 == null && str == null) || (str2 != null && str2.equals(str))) {
                    return gVar;
                }
            }
            throw new NoSuchFieldException("No such HapticFeedbackType: " + str);
        }
    }

    /* loaded from: classes.dex */
    public interface h {
        void a();

        void e(boolean z7);

        CharSequence f(e eVar);

        void g(List list);

        void h(c cVar);

        void i();

        void j(g gVar);

        void k();

        void l(String str);

        void m(j jVar);

        void n(int i8);

        void o(k kVar);

        void p(String str);

        void q(i iVar);

        boolean r();
    }

    /* loaded from: classes.dex */
    public enum i {
        CLICK("SystemSoundType.click"),
        ALERT("SystemSoundType.alert");
        

        /* renamed from: e  reason: collision with root package name */
        private final String f7343e;

        i(String str) {
            this.f7343e = str;
        }

        static i e(String str) {
            i[] values;
            for (i iVar : values()) {
                if (iVar.f7343e.equals(str)) {
                    return iVar;
                }
            }
            throw new NoSuchFieldException("No such SoundType: " + str);
        }
    }

    /* loaded from: classes.dex */
    public static class j {

        /* renamed from: a  reason: collision with root package name */
        public final Integer f7344a;

        /* renamed from: b  reason: collision with root package name */
        public final d f7345b;

        /* renamed from: c  reason: collision with root package name */
        public final Boolean f7346c;

        /* renamed from: d  reason: collision with root package name */
        public final Integer f7347d;

        /* renamed from: e  reason: collision with root package name */
        public final d f7348e;

        /* renamed from: f  reason: collision with root package name */
        public final Integer f7349f;

        /* renamed from: g  reason: collision with root package name */
        public final Boolean f7350g;

        public j(Integer num, d dVar, Boolean bool, Integer num2, d dVar2, Integer num3, Boolean bool2) {
            this.f7344a = num;
            this.f7345b = dVar;
            this.f7346c = bool;
            this.f7347d = num2;
            this.f7348e = dVar2;
            this.f7349f = num3;
            this.f7350g = bool2;
        }
    }

    /* loaded from: classes.dex */
    public enum k {
        LEAN_BACK("SystemUiMode.leanBack"),
        IMMERSIVE("SystemUiMode.immersive"),
        IMMERSIVE_STICKY("SystemUiMode.immersiveSticky"),
        EDGE_TO_EDGE("SystemUiMode.edgeToEdge");
        

        /* renamed from: e  reason: collision with root package name */
        private String f7356e;

        k(String str) {
            this.f7356e = str;
        }

        static k e(String str) {
            k[] values;
            for (k kVar : values()) {
                if (kVar.f7356e.equals(str)) {
                    return kVar;
                }
            }
            throw new NoSuchFieldException("No such SystemUiMode: " + str);
        }
    }

    /* loaded from: classes.dex */
    public enum l {
        TOP_OVERLAYS("SystemUiOverlay.top"),
        BOTTOM_OVERLAYS("SystemUiOverlay.bottom");
        

        /* renamed from: e  reason: collision with root package name */
        private String f7360e;

        l(String str) {
            this.f7360e = str;
        }

        static l e(String str) {
            l[] values;
            for (l lVar : values()) {
                if (lVar.f7360e.equals(str)) {
                    return lVar;
                }
            }
            throw new NoSuchFieldException("No such SystemUiOverlay: " + str);
        }
    }

    public o(a6.a aVar) {
        a aVar2 = new a();
        this.f7313c = aVar2;
        i6.j jVar = new i6.j(aVar, "flutter/platform", i6.f.f7534a);
        this.f7311a = jVar;
        jVar.e(aVar2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public c g(JSONObject jSONObject) {
        int i8 = jSONObject.getInt("primaryColor");
        if (i8 != 0) {
            i8 |= -16777216;
        }
        return new c(i8, jSONObject.getString("label"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0053 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int h(org.json.JSONArray r10) {
        /*
            r9 = this;
            r0 = 0
            r1 = r0
            r2 = r1
            r3 = r2
        L4:
            int r4 = r10.length()
            r5 = 4
            r6 = 2
            r7 = 1
            if (r1 >= r4) goto L38
            java.lang.String r4 = r10.getString(r1)
            h6.o$f r4 = h6.o.f.e(r4)
            int[] r8 = h6.o.b.f7315a
            int r4 = r4.ordinal()
            r4 = r8[r4]
            if (r4 == r7) goto L30
            if (r4 == r6) goto L2d
            r6 = 3
            if (r4 == r6) goto L2a
            if (r4 == r5) goto L27
            goto L32
        L27:
            r2 = r2 | 8
            goto L32
        L2a:
            r2 = r2 | 2
            goto L32
        L2d:
            r2 = r2 | 4
            goto L32
        L30:
            r2 = r2 | 1
        L32:
            if (r3 != 0) goto L35
            r3 = r2
        L35:
            int r1 = r1 + 1
            goto L4
        L38:
            if (r2 == 0) goto L57
            r10 = 9
            r1 = 8
            switch(r2) {
                case 2: goto L56;
                case 3: goto L4d;
                case 4: goto L4c;
                case 5: goto L4a;
                case 6: goto L4d;
                case 7: goto L4d;
                case 8: goto L49;
                case 9: goto L4d;
                case 10: goto L46;
                case 11: goto L45;
                case 12: goto L4d;
                case 13: goto L4d;
                case 14: goto L4d;
                case 15: goto L42;
                default: goto L41;
            }
        L41:
            goto L53
        L42:
            r10 = 13
            return r10
        L45:
            return r6
        L46:
            r10 = 11
            return r10
        L49:
            return r1
        L4a:
            r10 = 12
        L4c:
            return r10
        L4d:
            if (r3 == r6) goto L56
            if (r3 == r5) goto L55
            if (r3 == r1) goto L54
        L53:
            return r7
        L54:
            return r1
        L55:
            return r10
        L56:
            return r0
        L57:
            r10 = -1
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.o.h(org.json.JSONArray):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public j i(JSONObject jSONObject) {
        return new j(!jSONObject.isNull("statusBarColor") ? Integer.valueOf(jSONObject.getInt("statusBarColor")) : null, !jSONObject.isNull("statusBarIconBrightness") ? d.e(jSONObject.getString("statusBarIconBrightness")) : null, !jSONObject.isNull("systemStatusBarContrastEnforced") ? Boolean.valueOf(jSONObject.getBoolean("systemStatusBarContrastEnforced")) : null, !jSONObject.isNull("systemNavigationBarColor") ? Integer.valueOf(jSONObject.getInt("systemNavigationBarColor")) : null, !jSONObject.isNull("systemNavigationBarIconBrightness") ? d.e(jSONObject.getString("systemNavigationBarIconBrightness")) : null, !jSONObject.isNull("systemNavigationBarDividerColor") ? Integer.valueOf(jSONObject.getInt("systemNavigationBarDividerColor")) : null, jSONObject.isNull("systemNavigationBarContrastEnforced") ? null : Boolean.valueOf(jSONObject.getBoolean("systemNavigationBarContrastEnforced")));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public k j(String str) {
        int i8 = b.f7317c[k.e(str).ordinal()];
        return i8 != 1 ? i8 != 2 ? i8 != 3 ? i8 != 4 ? k.EDGE_TO_EDGE : k.EDGE_TO_EDGE : k.IMMERSIVE_STICKY : k.IMMERSIVE : k.LEAN_BACK;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List k(JSONArray jSONArray) {
        l lVar;
        ArrayList arrayList = new ArrayList();
        for (int i8 = 0; i8 < jSONArray.length(); i8++) {
            int i9 = b.f7316b[l.e(jSONArray.getString(i8)).ordinal()];
            if (i9 == 1) {
                lVar = l.TOP_OVERLAYS;
            } else if (i9 == 2) {
                lVar = l.BOTTOM_OVERLAYS;
            }
            arrayList.add(lVar);
        }
        return arrayList;
    }

    public void l(h hVar) {
        this.f7312b = hVar;
    }

    public void m(boolean z7) {
        z5.b.f("PlatformChannel", "Sending 'systemUIChange' message.");
        this.f7311a.c("SystemChrome.systemUIChange", Arrays.asList(Boolean.valueOf(z7)));
    }
}
