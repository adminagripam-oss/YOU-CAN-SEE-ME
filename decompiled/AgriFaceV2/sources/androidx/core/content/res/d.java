package androidx.core.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Base64;
import android.util.Xml;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {
        static int a(TypedArray typedArray, int i8) {
            return typedArray.getType(i8);
        }
    }

    /* loaded from: classes.dex */
    public interface b {
    }

    /* loaded from: classes.dex */
    public static final class c implements b {

        /* renamed from: a  reason: collision with root package name */
        private final C0023d[] f2939a;

        public c(C0023d[] c0023dArr) {
            this.f2939a = c0023dArr;
        }

        public C0023d[] a() {
            return this.f2939a;
        }
    }

    /* renamed from: androidx.core.content.res.d$d  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0023d {

        /* renamed from: a  reason: collision with root package name */
        private final String f2940a;

        /* renamed from: b  reason: collision with root package name */
        private final int f2941b;

        /* renamed from: c  reason: collision with root package name */
        private final boolean f2942c;

        /* renamed from: d  reason: collision with root package name */
        private final String f2943d;

        /* renamed from: e  reason: collision with root package name */
        private final int f2944e;

        /* renamed from: f  reason: collision with root package name */
        private final int f2945f;

        public C0023d(String str, int i8, boolean z7, String str2, int i9, int i10) {
            this.f2940a = str;
            this.f2941b = i8;
            this.f2942c = z7;
            this.f2943d = str2;
            this.f2944e = i9;
            this.f2945f = i10;
        }

        public String a() {
            return this.f2940a;
        }

        public int b() {
            return this.f2945f;
        }

        public int c() {
            return this.f2944e;
        }

        public String d() {
            return this.f2943d;
        }

        public int e() {
            return this.f2941b;
        }

        public boolean f() {
            return this.f2942c;
        }
    }

    /* loaded from: classes.dex */
    public static final class e implements b {

        /* renamed from: a  reason: collision with root package name */
        private final f1.e f2946a;

        /* renamed from: b  reason: collision with root package name */
        private final int f2947b;

        /* renamed from: c  reason: collision with root package name */
        private final int f2948c;

        /* renamed from: d  reason: collision with root package name */
        private final String f2949d;

        public e(f1.e eVar, int i8, int i9, String str) {
            this.f2946a = eVar;
            this.f2948c = i8;
            this.f2947b = i9;
            this.f2949d = str;
        }

        public int a() {
            return this.f2948c;
        }

        public f1.e b() {
            return this.f2946a;
        }

        public String c() {
            return this.f2949d;
        }

        public int d() {
            return this.f2947b;
        }
    }

    private static int a(TypedArray typedArray, int i8) {
        return a.a(typedArray, i8);
    }

    public static b b(XmlPullParser xmlPullParser, Resources resources) {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return d(xmlPullParser, resources);
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static List c(Resources resources, int i8) {
        if (i8 == 0) {
            return Collections.emptyList();
        }
        TypedArray obtainTypedArray = resources.obtainTypedArray(i8);
        try {
            if (obtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (a(obtainTypedArray, 0) == 1) {
                for (int i9 = 0; i9 < obtainTypedArray.length(); i9++) {
                    int resourceId = obtainTypedArray.getResourceId(i9, 0);
                    if (resourceId != 0) {
                        arrayList.add(h(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(h(resources.getStringArray(i8)));
            }
            return arrayList;
        } finally {
            obtainTypedArray.recycle();
        }
    }

    private static b d(XmlPullParser xmlPullParser, Resources resources) {
        xmlPullParser.require(2, null, "font-family");
        if (xmlPullParser.getName().equals("font-family")) {
            return e(xmlPullParser, resources);
        }
        g(xmlPullParser);
        return null;
    }

    private static b e(XmlPullParser xmlPullParser, Resources resources) {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), c1.c.f4728h);
        String string = obtainAttributes.getString(c1.c.f4729i);
        String string2 = obtainAttributes.getString(c1.c.f4733m);
        String string3 = obtainAttributes.getString(c1.c.f4734n);
        int resourceId = obtainAttributes.getResourceId(c1.c.f4730j, 0);
        int integer = obtainAttributes.getInteger(c1.c.f4731k, 1);
        int integer2 = obtainAttributes.getInteger(c1.c.f4732l, 500);
        String string4 = obtainAttributes.getString(c1.c.f4735o);
        obtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlPullParser.next() != 3) {
                g(xmlPullParser);
            }
            return new e(new f1.e(string, string2, string3, c(resources, resourceId)), integer, integer2, string4);
        }
        ArrayList arrayList = new ArrayList();
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("font")) {
                    arrayList.add(f(xmlPullParser, resources));
                } else {
                    g(xmlPullParser);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new c((C0023d[]) arrayList.toArray(new C0023d[0]));
    }

    private static C0023d f(XmlPullParser xmlPullParser, Resources resources) {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), c1.c.f4736p);
        int i8 = obtainAttributes.getInt(obtainAttributes.hasValue(c1.c.f4745y) ? c1.c.f4745y : c1.c.f4738r, 400);
        boolean z7 = 1 == obtainAttributes.getInt(obtainAttributes.hasValue(c1.c.f4743w) ? c1.c.f4743w : c1.c.f4739s, 0);
        int i9 = obtainAttributes.hasValue(c1.c.f4746z) ? c1.c.f4746z : c1.c.f4740t;
        String string = obtainAttributes.getString(obtainAttributes.hasValue(c1.c.f4744x) ? c1.c.f4744x : c1.c.f4741u);
        int i10 = obtainAttributes.getInt(i9, 0);
        int i11 = obtainAttributes.hasValue(c1.c.f4742v) ? c1.c.f4742v : c1.c.f4737q;
        int resourceId = obtainAttributes.getResourceId(i11, 0);
        String string2 = obtainAttributes.getString(i11);
        obtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            g(xmlPullParser);
        }
        return new C0023d(string2, i8, z7, string, i10, resourceId);
    }

    private static void g(XmlPullParser xmlPullParser) {
        int i8 = 1;
        while (i8 > 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i8++;
            } else if (next == 3) {
                i8--;
            }
        }
    }

    private static List h(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(Base64.decode(str, 0));
        }
        return arrayList;
    }
}
