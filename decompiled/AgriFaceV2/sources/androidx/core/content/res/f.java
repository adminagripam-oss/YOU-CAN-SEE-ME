package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.core.content.res.f;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public abstract class f {

    /* renamed from: a  reason: collision with root package name */
    private static final ThreadLocal f2950a = new ThreadLocal();

    /* renamed from: b  reason: collision with root package name */
    private static final WeakHashMap f2951b = new WeakHashMap(0);

    /* renamed from: c  reason: collision with root package name */
    private static final Object f2952c = new Object();

    /* loaded from: classes.dex */
    static class a {
        static Drawable a(Resources resources, int i8, Resources.Theme theme) {
            return resources.getDrawable(i8, theme);
        }

        static Drawable b(Resources resources, int i8, int i9, Resources.Theme theme) {
            return resources.getDrawableForDensity(i8, i9, theme);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b {
        static int a(Resources resources, int i8, Resources.Theme theme) {
            return resources.getColor(i8, theme);
        }

        static ColorStateList b(Resources resources, int i8, Resources.Theme theme) {
            return resources.getColorStateList(i8, theme);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        final ColorStateList f2953a;

        /* renamed from: b  reason: collision with root package name */
        final Configuration f2954b;

        /* renamed from: c  reason: collision with root package name */
        final int f2955c;

        c(ColorStateList colorStateList, Configuration configuration, Resources.Theme theme) {
            this.f2953a = colorStateList;
            this.f2954b = configuration;
            this.f2955c = theme == null ? 0 : theme.hashCode();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class d {

        /* renamed from: a  reason: collision with root package name */
        final Resources f2956a;

        /* renamed from: b  reason: collision with root package name */
        final Resources.Theme f2957b;

        d(Resources resources, Resources.Theme theme) {
            this.f2956a = resources;
            this.f2957b = theme;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || d.class != obj.getClass()) {
                return false;
            }
            d dVar = (d) obj;
            return this.f2956a.equals(dVar.f2956a) && g1.c.a(this.f2957b, dVar.f2957b);
        }

        public int hashCode() {
            return g1.c.b(this.f2956a, this.f2957b);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class e {
        public static Handler e(Handler handler) {
            return handler == null ? new Handler(Looper.getMainLooper()) : handler;
        }

        public final void c(final int i8, Handler handler) {
            e(handler).post(new Runnable() { // from class: androidx.core.content.res.h
                @Override // java.lang.Runnable
                public final void run() {
                    f.e.this.f(i8);
                }
            });
        }

        public final void d(final Typeface typeface, Handler handler) {
            e(handler).post(new Runnable() { // from class: androidx.core.content.res.g
                @Override // java.lang.Runnable
                public final void run() {
                    f.e.this.g(typeface);
                }
            });
        }

        /* renamed from: h */
        public abstract void f(int i8);

        /* renamed from: i */
        public abstract void g(Typeface typeface);
    }

    /* renamed from: androidx.core.content.res.f$f  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0024f {

        /* renamed from: androidx.core.content.res.f$f$a */
        /* loaded from: classes.dex */
        static class a {

            /* renamed from: a  reason: collision with root package name */
            private static final Object f2958a = new Object();

            /* renamed from: b  reason: collision with root package name */
            private static Method f2959b;

            /* renamed from: c  reason: collision with root package name */
            private static boolean f2960c;

            static void a(Resources.Theme theme) {
                synchronized (f2958a) {
                    if (!f2960c) {
                        try {
                            Method declaredMethod = Resources.Theme.class.getDeclaredMethod("rebase", new Class[0]);
                            f2959b = declaredMethod;
                            declaredMethod.setAccessible(true);
                        } catch (NoSuchMethodException e8) {
                            Log.i("ResourcesCompat", "Failed to retrieve rebase() method", e8);
                        }
                        f2960c = true;
                    }
                    Method method = f2959b;
                    if (method != null) {
                        try {
                            method.invoke(theme, new Object[0]);
                        } catch (IllegalAccessException | InvocationTargetException e9) {
                            Log.i("ResourcesCompat", "Failed to invoke rebase() method via reflection", e9);
                            f2959b = null;
                        }
                    }
                }
            }
        }

        /* renamed from: androidx.core.content.res.f$f$b */
        /* loaded from: classes.dex */
        static class b {
            static void a(Resources.Theme theme) {
                theme.rebase();
            }
        }

        public static void a(Resources.Theme theme) {
            if (Build.VERSION.SDK_INT >= 29) {
                b.a(theme);
            } else {
                a.a(theme);
            }
        }
    }

    private static void a(d dVar, int i8, ColorStateList colorStateList, Resources.Theme theme) {
        synchronized (f2952c) {
            WeakHashMap weakHashMap = f2951b;
            SparseArray sparseArray = (SparseArray) weakHashMap.get(dVar);
            if (sparseArray == null) {
                sparseArray = new SparseArray();
                weakHashMap.put(dVar, sparseArray);
            }
            sparseArray.append(i8, new c(colorStateList, dVar.f2956a.getConfiguration(), theme));
        }
    }

    private static ColorStateList b(d dVar, int i8) {
        c cVar;
        Resources.Theme theme;
        synchronized (f2952c) {
            SparseArray sparseArray = (SparseArray) f2951b.get(dVar);
            if (sparseArray != null && sparseArray.size() > 0 && (cVar = (c) sparseArray.get(i8)) != null) {
                if (cVar.f2954b.equals(dVar.f2956a.getConfiguration()) && (((theme = dVar.f2957b) == null && cVar.f2955c == 0) || (theme != null && cVar.f2955c == theme.hashCode()))) {
                    return cVar.f2953a;
                }
                sparseArray.remove(i8);
            }
            return null;
        }
    }

    public static ColorStateList c(Resources resources, int i8, Resources.Theme theme) {
        d dVar = new d(resources, theme);
        ColorStateList b8 = b(dVar, i8);
        if (b8 != null) {
            return b8;
        }
        ColorStateList h8 = h(resources, i8, theme);
        if (h8 != null) {
            a(dVar, i8, h8, theme);
            return h8;
        }
        return b.b(resources, i8, theme);
    }

    public static Drawable d(Resources resources, int i8, Resources.Theme theme) {
        return a.a(resources, i8, theme);
    }

    public static Drawable e(Resources resources, int i8, int i9, Resources.Theme theme) {
        return a.b(resources, i8, i9, theme);
    }

    public static Typeface f(Context context, int i8, TypedValue typedValue, int i9, e eVar) {
        if (context.isRestricted()) {
            return null;
        }
        return j(context, i8, typedValue, i9, eVar, null, true, false);
    }

    private static TypedValue g() {
        ThreadLocal threadLocal = f2950a;
        TypedValue typedValue = (TypedValue) threadLocal.get();
        if (typedValue == null) {
            TypedValue typedValue2 = new TypedValue();
            threadLocal.set(typedValue2);
            return typedValue2;
        }
        return typedValue;
    }

    private static ColorStateList h(Resources resources, int i8, Resources.Theme theme) {
        if (i(resources, i8)) {
            return null;
        }
        try {
            return androidx.core.content.res.c.a(resources, resources.getXml(i8), theme);
        } catch (Exception e8) {
            Log.w("ResourcesCompat", "Failed to inflate ColorStateList, leaving it to the framework", e8);
            return null;
        }
    }

    private static boolean i(Resources resources, int i8) {
        TypedValue g8 = g();
        resources.getValue(i8, g8, true);
        int i9 = g8.type;
        return i9 >= 28 && i9 <= 31;
    }

    private static Typeface j(Context context, int i8, TypedValue typedValue, int i9, e eVar, Handler handler, boolean z7, boolean z8) {
        Resources resources = context.getResources();
        resources.getValue(i8, typedValue, true);
        Typeface k8 = k(context, resources, typedValue, i8, i9, eVar, handler, z7, z8);
        if (k8 == null && eVar == null && !z8) {
            throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i8) + " could not be retrieved.");
        }
        return k8;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.graphics.Typeface k(android.content.Context r17, android.content.res.Resources r18, android.util.TypedValue r19, int r20, int r21, androidx.core.content.res.f.e r22, android.os.Handler r23, boolean r24, boolean r25) {
        /*
            r0 = r18
            r1 = r19
            r4 = r20
            r11 = r22
            r12 = r23
            java.lang.String r13 = "ResourcesCompat"
            java.lang.CharSequence r2 = r1.string
            if (r2 == 0) goto Lae
            java.lang.String r14 = r2.toString()
            java.lang.String r2 = "res/"
            boolean r2 = r14.startsWith(r2)
            r15 = -3
            r16 = 0
            if (r2 != 0) goto L25
            if (r11 == 0) goto L24
            r11.c(r15, r12)
        L24:
            return r16
        L25:
            int r2 = r1.assetCookie
            r7 = r21
            android.graphics.Typeface r2 = androidx.core.graphics.h.f(r0, r4, r14, r2, r7)
            if (r2 == 0) goto L35
            if (r11 == 0) goto L34
            r11.d(r2, r12)
        L34:
            return r2
        L35:
            if (r25 == 0) goto L38
            return r16
        L38:
            java.lang.String r2 = r14.toLowerCase()     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L93
            java.lang.String r3 = ".xml"
            boolean r2 = r2.endsWith(r3)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L93
            if (r2 == 0) goto L6f
            android.content.res.XmlResourceParser r2 = r0.getXml(r4)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L93
            androidx.core.content.res.d$b r2 = androidx.core.content.res.d.b(r2, r0)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L93
            if (r2 != 0) goto L59
            java.lang.String r0 = "Failed to find font-family tag"
            android.util.Log.e(r13, r0)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L93
            if (r11 == 0) goto L58
            r11.c(r15, r12)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L93
        L58:
            return r16
        L59:
            int r6 = r1.assetCookie     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L93
            r1 = r17
            r3 = r18
            r4 = r20
            r5 = r14
            r7 = r21
            r8 = r22
            r9 = r23
            r10 = r24
            android.graphics.Typeface r0 = androidx.core.graphics.h.c(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L93
            return r0
        L6f:
            int r5 = r1.assetCookie     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L93
            r1 = r17
            r2 = r18
            r3 = r20
            r4 = r14
            r6 = r21
            android.graphics.Typeface r0 = androidx.core.graphics.h.d(r1, r2, r3, r4, r5, r6)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L93
            if (r11 == 0) goto L89
            if (r0 == 0) goto L86
            r11.d(r0, r12)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L93
            goto L89
        L86:
            r11.c(r15, r12)     // Catch: java.io.IOException -> L8a org.xmlpull.v1.XmlPullParserException -> L93
        L89:
            return r0
        L8a:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Failed to read xml resource "
            goto L9b
        L93:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Failed to parse xml resource "
        L9b:
            r1.append(r2)
            r1.append(r14)
            java.lang.String r1 = r1.toString()
            android.util.Log.e(r13, r1, r0)
            if (r11 == 0) goto Lad
            r11.c(r15, r12)
        Lad:
            return r16
        Lae:
            android.content.res.Resources$NotFoundException r2 = new android.content.res.Resources$NotFoundException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "Resource \""
            r3.append(r5)
            java.lang.String r0 = r0.getResourceName(r4)
            r3.append(r0)
            java.lang.String r0 = "\" ("
            r3.append(r0)
            java.lang.String r0 = java.lang.Integer.toHexString(r20)
            r3.append(r0)
            java.lang.String r0 = ") is not a Font: "
            r3.append(r0)
            r3.append(r1)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.f.k(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, androidx.core.content.res.f$e, android.os.Handler, boolean, boolean):android.graphics.Typeface");
    }
}
